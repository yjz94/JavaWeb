package cn.fishland.javaweb.dao;

import cn.fishland.javaweb.bean.Attachment;

/**
 * 附件数据库操作类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 11:57 下午
 */
public interface AttachmentDao {

    /**
     * 保存附件到数据库<br/>
     * 因为这个数据库操作比较特殊，所以要单独编写方法
     *
     * @param attachment 附件对象
     * @return true表示保存成功，反之为false
     */
    boolean insertAttachment(Attachment attachment);

    /**
     * 根据文件名称下载文件
     *
     * @param attachmentName 文件名称
     * @return 文件实体类
     */
    Attachment queryAttachment(String attachmentName);
}
