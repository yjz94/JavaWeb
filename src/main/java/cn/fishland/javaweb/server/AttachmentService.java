package cn.fishland.javaweb.server;

import cn.fishland.javaweb.bean.Attachment;

/**
 * 附件相服务
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 11:50 下午
 */
public interface AttachmentService {

    /**
     * 保存附件到数据库
     *
     * @param attachment 附件内容
     * @return 1:表示存储成功，0：表示失败
     */
    int saveAttachment(Attachment attachment);

    /**
     * 向数据库中查询文件
     *
     * @param name 文件名称（唯一）
     * @return 附件对象
     */
    Attachment queryAttachment(String name);

}
