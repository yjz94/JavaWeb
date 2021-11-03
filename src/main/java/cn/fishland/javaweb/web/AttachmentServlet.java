package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.Attachment;
import cn.fishland.javaweb.server.AttachmentService;
import cn.fishland.javaweb.server.impl.AttachmentServiceImpl;
import cn.fishland.javaweb.util.FunctionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils2.ConvertUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

/**
 * 附件相关网络请求处理程序
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 7:42 下午
 */
public class AttachmentServlet extends HttpServlet {

    public static AttachmentService attachmentService;

    static {
        attachmentService = new AttachmentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = FunctionUtils.getUriTail(req);

        switch (action) {
            case "/API/attachment/show":
                show(req, resp);
                break;
            case "getAttachmentList":
                queryAttachmentList(req, resp);
                break;
            default:
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FunctionUtils.info("AttachmentServlet 开始处理请求");

        req.setCharacterEncoding("UTF-8");

        String action = FunctionUtils.getUriTail(req);

        if (StringUtils.isNotBlank(action)) {
            switch (action) {
                case "uploadImg":
                    uploadImg(req, resp);
                    break;
                case "/admin/API/attachment/insert/editor":
                    editorUpload(req, resp);
                    break;
                default:
            }
        } else {
            FunctionUtils.info("AttachmentServlet 未能找到对应的请求路径！");
        }

    }

    /**
     * 富文本编辑器上传附件专用接口
     *
     * @param req  {@link javax.servlet.http.HttpServletRequest}
     * @param resp {@link javax.servlet.http.HttpServletResponse}
     */
    private void editorUpload(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Attachment attachment = parseAttachmentByRequest(req);
            JSONObject jsonObject = new JSONObject();
            int saveResult = 0;

            if (attachment != null) {
                attachment.setName(FunctionUtils.getUUID());
                attachment.setType(1);
                attachment.setStatus(1);
                attachment.setCreateDate(new Timestamp(System.currentTimeMillis()));
                attachment.setUpdateDate(new Timestamp(System.currentTimeMillis()));

                saveResult = attachmentService.saveAttachment(attachment);
            }

            if (saveResult == 1) {
                // 保存成功处理

                // 配置返回JSON
                jsonObject.put("errno", 0);
                JSONArray jsonArray = new JSONArray();
                jsonObject.put("data", jsonArray);
                JSONObject attachmentJsonObject = new JSONObject();
                attachmentJsonObject.put("url", req.getServletContext().getInitParameter("baseUrl")
                        + "API/attachment/show?attachmentName=" + attachment.getName());
                attachmentJsonObject.put("alt", "图片文件");
                attachmentJsonObject.put("href", "");
                jsonArray.add(attachmentJsonObject);
            } else {
                jsonObject.put("errno", 1);
                jsonObject.put("data", null);
            }

            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().print(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void uploadImg(HttpServletRequest req, HttpServletResponse resp) {
        // 判断是否是分段数据传输
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

                servletFileUpload.setFileSizeMax(1024 * 1024);
                servletFileUpload.setSizeMax(1024 * 1024 * 10);

                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                String name = null;
                Blob blob = null;
                String contentType = null;
                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        name = fileItem.getString("UTF-8");
                    } else {
                        InputStream inputStream = fileItem.getInputStream();
                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int n;
                        while (-1 != (n = inputStream.read(buffer))) {
                            output.write(buffer, 0, n);
                        }
                        blob = new SerialBlob(output.toByteArray());
                        output.close();

                        contentType = fileItem.getContentType();
                    }
                }

                Attachment attachment = new Attachment();
                attachment.setName(name);
                attachment.setFile(blob);
                attachment.setType(1);
                attachment.setStatus(1);
                attachment.setCreateDate(new Timestamp(System.currentTimeMillis()));
                attachment.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                attachment.setContentType(contentType);

                attachmentService.saveAttachment(attachment);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    protected void queryAttachmentList(HttpServletRequest req, HttpServletResponse resp) {
        int page = Integer.parseInt(req.getParameter("page"));
    }

    protected void show(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("attachmentName");
            Attachment attachment = attachmentService.queryAttachment(name);
            resp.setDateHeader("Expires", 0);
            resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
            resp.setHeader("Pragma", "no-cache");
            resp.setContentType(attachment.getContentType());
            Blob blob = attachment.getFile();
            ServletOutputStream out = resp.getOutputStream();
            out.write(blob.getBytes(1, (int) blob.length()));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传附件请求转换成Attachment对象，只是适用单文件上传请求。<br/>
     * 注意：上传文件时携带的普通参数不能出现file，要想赋值给{@link cn.fishland.javaweb.bean.Attachment},<br/>
     * 请求参数和类属性名必须保持一致，(属性值contentType和file不需要主动赋值)
     *
     * @param request {@link javax.servlet.http.HttpServletRequest}
     * @return 包含内容的Attachment对象，未能成功赋值返回null
     */
    private Attachment parseAttachmentByRequest(HttpServletRequest request) {
        try {
            Attachment attachment = null;
            // 判断是否为多段传输
            if (ServletFileUpload.isMultipartContent(request)) {

                // 创建文件解析工厂
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                // 限制每个文件大小
                servletFileUpload.setFileSizeMax(1024 * 1024);
                // 限制文件总量大小
                servletFileUpload.setSizeMax(1024 * 1024 * 10);

                // 获得每个字段解析对象
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);

                if (fileItems != null && fileItems.size() > 0) {
                    attachment = new Attachment();

                    for (FileItem fileItem : fileItems) {
                        if (fileItem.isFormField()) {
                            // 处理普通参数

                            // 通过反射自动为Attachment辅助
                            String value = fileItem.getString("UTF-8");
                            String fieldName = fileItem.getFieldName();

                            Field field = Attachment.class.getDeclaredField(fieldName);
                            field.setAccessible(true);

                            // 使用apache的beanutils工具包进行动态类型转换
                            field.set(attachment, ConvertUtils.convert(value, field.getType()));

                        } else {
                            // 处理文件

                            InputStream inputStream = fileItem.getInputStream();
                            ByteArrayOutputStream output = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int n;
                            while (-1 != (n = inputStream.read(buffer))) {
                                output.write(buffer, 0, n);
                            }
                            Blob blob = new SerialBlob(output.toByteArray());
                            output.close();

                            // 文件类型
                            String contentType = fileItem.getContentType();

                            attachment.setFile(blob);
                            attachment.setContentType(contentType);

                        }
                    }
                }
            }

            return attachment;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
