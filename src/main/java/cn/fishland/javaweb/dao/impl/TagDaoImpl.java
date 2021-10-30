package cn.fishland.javaweb.dao.impl;

import cn.fishland.javaweb.bean.Tag;
import cn.fishland.javaweb.dao.BaseDao;
import cn.fishland.javaweb.dao.TagDao;

import java.util.List;

/**
 * 标签数据库操作实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/30 10:34 上午
 */
public class TagDaoImpl extends BaseDao<Tag> implements TagDao {

    @Override
    public List<Tag> queryByMaster(String master) {
        String sql = "select * from tag where master = ?";
        return queryList(sql, master);
    }

}
