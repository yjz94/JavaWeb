package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.Attachment;
import cn.fishland.javaweb.server.AttachmentService;
import cn.fishland.javaweb.server.impl.AttachmentServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
                attachment.setCreateDate(new Date(System.currentTimeMillis()));
                attachment.setUpdateDate(new Date(System.currentTimeMillis()));
                attachment.setContentType(contentType);

                attachmentService.saveAttachment(attachment);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "getAttachment":
                queryAttachment(req, resp);
                break;
            case "getAttachmentList":
                queryAttachmentList(req, resp);
                break;
        }

    }

    protected void queryAttachmentList(HttpServletRequest req, HttpServletResponse resp) {
        int page = Integer.parseInt(req.getParameter("page"));



    }

    protected void queryAttachment(HttpServletRequest req, HttpServletResponse resp) {
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
}
