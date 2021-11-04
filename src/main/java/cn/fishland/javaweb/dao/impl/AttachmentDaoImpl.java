package cn.fishland.javaweb.dao.impl;

import cn.fishland.javaweb.bean.Attachment;
import cn.fishland.javaweb.dao.AttachmentDao;
import cn.fishland.javaweb.dao.BaseDao;
import cn.fishland.javaweb.util.FunctionUtils;
import cn.fishland.javaweb.util.JdbcUtils;

import java.sql.*;
import java.util.List;

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

            FunctionUtils.info(attachment.toString());

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
    public List<Attachment> queryAttachmentList(String master) {
        String sql = "select * from attachment where master = ?";
        return queryList(sql, master);
    }

    @Override
    public int deleteAttachments(String... names) {
        StringBuilder sql = new StringBuilder("delete from attachment where 0=1 ");
        if (names != null && names.length > 0) {
            for (int i = 0; i < names.length; i++) {
                sql.append("or name=? ");
            }
            sql.append(";");

            boolean delete = delete(sql.toString(), names);
            if (delete) {
                return names.length;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int deleteAttachmentByMaster(String... articleIds) {
        StringBuilder sql = new StringBuilder("delete from attachment where 0=1 ");
        if (articleIds != null && articleIds.length > 0) {
            for (int i = 0; i < articleIds.length; i++) {
                sql.append("or master=? ");
            }
            sql.append(";");

            boolean delete = delete(sql.toString(), articleIds);
            if (delete) {
                return articleIds.length;
            } else {
                return -1;
            }
        } else {
            return 0;
        }

    }

}
