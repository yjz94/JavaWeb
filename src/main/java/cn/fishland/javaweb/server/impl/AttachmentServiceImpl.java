package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.Attachment;
import cn.fishland.javaweb.dao.AttachmentDao;
import cn.fishland.javaweb.dao.impl.AttachmentDaoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 附件服务操作实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 11:56 下午
 */
public class AttachmentServiceImpl implements cn.fishland.javaweb.server.AttachmentService {

    public static AttachmentDao attachmentDao;

    static {
        attachmentDao = new AttachmentDaoImpl();
    }

    @Override
    public int saveAttachment(Attachment attachment) {
        boolean result = attachmentDao.insertAttachment(attachment);
        if (result) {
            return 1;
        }
        return 0;
    }

    @Override
    public Attachment queryAttachment(String name) {
        return attachmentDao.queryAttachment(name);
    }

    @Override
    public List<String> queryAttachmentNameByMaster(String master) {
        List<Attachment> attachments = attachmentDao.queryAttachmentList(master);
        List<String> list = null;
        if (attachments != null && attachments.size() > 0) {
            list = new ArrayList<>();
            for (Attachment attachment : attachments) {
                list.add(attachment.getName());
            }
        }
        return list;
    }

    @Override
    public int removeAttachmentByMaster(String... names) {
        return attachmentDao.deleteAttachments(names);
    }

    @Override
    public int deleteAttachment(String... articleIds) {
        return attachmentDao.deleteAttachmentByMaster(articleIds);
    }

}
