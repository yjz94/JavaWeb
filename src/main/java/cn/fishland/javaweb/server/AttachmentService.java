package cn.fishland.javaweb.server;

import cn.fishland.javaweb.bean.Attachment;

import java.util.List;

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

    /**
     * 通过查询获得Attachment所以name
     *
     * @param master 主id
     * @return name集合
     */
    List<String> queryAttachmentNameByMaster(String master);

    /**
     * 删除已存但未使用附件
     *
     * @param names 需要删除附件名称
     * @return 执行成功条数
     */
    int removeAttachmentByMaster(String... names);

    /**
     * 删除与主相关附件
     *
     * @param articleIds 主id
     * @return 影响行数
     */
    int deleteAttachment(String... articleIds);
}
