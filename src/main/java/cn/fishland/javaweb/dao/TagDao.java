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
     * 根据主id查询标签
     *
     * @param master 主id
     * @return 返回标签对象，不存在返回null
     */
    List<Tag> queryByMaster(String master);
}
