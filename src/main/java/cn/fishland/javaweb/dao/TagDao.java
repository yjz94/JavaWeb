package cn.fishland.javaweb.dao;

import cn.fishland.javaweb.bean.Tag;

import java.util.List;

/**
 * 标签数据库操作类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/30 10:29 上午
 */
public interface TagDao {

    /**
     * 获得所有标签（包括未禁用）
     *
     * @return 返回标签集合
     */
    List<Tag> queryAllTag();

    /**
     * 根据主id查询标签
     *
     * @param master 主id
     * @return 返回标签集合
     */
    List<Tag> queryAllByMaster(String master);

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return 删除是否成功
     */
    int deleteById(Integer id);

    /**
     * 根据主id删除标签
     *
     * @param master 主id
     * @return 是否删除成功
     */
    int deleteByMaster(String master);

    /**
     * 新增标签
     *
     * @param tag 标签对象
     * @return 新增条数
     */
    int insert(Tag tag);

}
