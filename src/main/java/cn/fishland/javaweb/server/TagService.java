package cn.fishland.javaweb.server;

import cn.fishland.javaweb.bean.Tag;

import java.util.List;

/**
 * 标签服务类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/11/5 7:35 下午
 */
public interface TagService {

    /**
     * 获得所有标签名称
     *
     * @return 名称集合
     */
    List<String> getAllTagName();

    /**
     * 新增标签
     *
     * @param tag 标签集合
     * @return true表示新增成功否则为false
     */
    boolean insert(Tag tag);

    /**
     * 根据id删除所有标签名称
     *
     * @param ids 标签id集合
     * @return 删除是否成功
     */
    boolean deleteAll(int... ids);

    /**
     * 根据主id删除标签
     *
     * @param masters 主id集合
     * @return 删除是否成功
     */
    boolean deleteAllByMaster(String... masters);
}
