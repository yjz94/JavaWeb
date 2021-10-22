package cn.fishland.javaweb.util;

import cn.fishland.javaweb.bean.Attachment;
import cn.fishland.javaweb.dao.impl.AttachmentDaoImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;

/**
 * TODO
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 10:33 上午
 */
public class JdbcUtilsTest {

    @Test
    public void getConnectionTest() {

    }

    @Test
    public void getUser() {
        Connection connection = JdbcUtils.getConnection();

        System.out.println(connection);
    }

    @Test
    public void insetAttachment() {
        try {
            File file = new File("/Users/yujiangzhong/IdeaProjects/Java/JavaWeb/temp.png");

            Attachment attachment = new Attachment();
            attachment.setName("attachment2");
            attachment.setStatus(1);
            attachment.setType(1);
            attachment.setCreateDate(new Date(System.currentTimeMillis()));

            Blob blob = new SerialBlob(Files.readAllBytes(file.toPath()));
            attachment.setFile(blob);

            AttachmentDaoImpl attachmentDao = new AttachmentDaoImpl();

            boolean b = attachmentDao.insertAttachment(attachment);

            System.out.println(b);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryAttachment() {
        try {
            File file = new File("/Users/yujiangzhong/IdeaProjects/Java/JavaWeb/uploadFile.png");

            AttachmentDaoImpl attachmentDao = new AttachmentDaoImpl();

            Attachment attachment1 = attachmentDao.queryAttachment("attachment2");

            Blob file1 = attachment1.getFile();

            byte[] bytes = file1.getBytes(1, (int) file1.length());

            FileOutputStream out = new FileOutputStream(file);

            out.write(bytes);

            out.close();

            System.out.println(attachment1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testJava() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errno", 0);

        JSONArray jsonArray = new JSONArray();
        jsonObject.put("data", jsonArray);

        JSONObject attachmentJsonObject = new JSONObject();
        attachmentJsonObject.put("url", "http://loaclhost:8080/JavaWeb/getAttachment?attachmentName=****");
        attachmentJsonObject.put("alt", "图片文件");
        attachmentJsonObject.put("href", "");

        jsonArray.add(attachmentJsonObject);

        System.out.println(jsonObject.toString());
    }
}