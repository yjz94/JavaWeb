package cn.fishland.javaweb.dao.impl;

import cn.fishland.javaweb.bean.Attachment;
import cn.fishland.javaweb.dao.AttachmentDao;
import cn.fishland.javaweb.dao.BaseDao;
import cn.fishland.javaweb.util.JdbcUtils;

import java.sql.*;

/**
 * 附件数据库操作实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 11:59 下午
 */
public class AttachmentDaoImpl extends BaseDao<Attachment> implements AttachmentDao {

    @Override
    public boolean insertAttachment(Attachment attachment) {
        Connection connection = JdbcUtils.getConnection();

        String sql = "insert into attachment(`name`,`createDate`,`status`,`type`,`file`,`contentType`,`master`) values(?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, attachment.getName());
            statement.setTimestamp(2, attachment.getCreateDate());
            statement.setInt(3, attachment.getStatus());
            statement.setInt(4, attachment.getType());
            statement.setBlob(5, attachment.getFile());
            statement.setString(6, attachment.getContentType());
            statement.setString(7, attachment.getMaster());

            statement.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return false;
    }

    @Override
    public Attachment queryAttachment(String attachmentName) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();

            String sql = "select * from attachment where name = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, attachmentName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp createDate = resultSet.getTimestamp("createDate");
                Timestamp updateDate = resultSet.getTimestamp("updateDate");
                int status = resultSet.getInt("status");
                int type = resultSet.getInt("type");
                Blob file = resultSet.getBlob("file");
                String contentType = resultSet.getString("contentType");

                Attachment attachment = new Attachment();
                attachment.setId(id);
                attachment.setName(name);
                attachment.setCreateDate(createDate);
                attachment.setUpdateDate(updateDate);
                attachment.setStatus(status);
                attachment.setType(type);
                attachment.setFile(file);
                attachment.setContentType(contentType);

                return attachment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    @Override
    public int deleteAttachment(String... names) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("delete from attachment where 1=0 ");
        for (String name : names) {
            stringBuilder.append("or name = ? ");
        }
        boolean delete = delete(stringBuilder.toString(), names);
        if (delete) {
            return names.length;
        }
        return -1;
    }

}
