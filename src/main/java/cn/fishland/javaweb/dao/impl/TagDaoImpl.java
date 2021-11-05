package cn.fishland.javaweb.dao.impl;

import cn.fishland.javaweb.bean.Tag;
import cn.fishland.javaweb.dao.BaseDao;
import cn.fishland.javaweb.dao.TagDao;
import cn.fishland.javaweb.util.FunctionUtils;

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
    public List<Tag> queryAllTag() {
        String sql = "select * from tag";
        return queryList(sql, null);
    }

    @Override
    public List<Tag> queryAllByMaster(String master) {
        String sql = "select * from tage where status = 1 and master = ?";
        return queryList(sql, master);
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from tag where id=?";
        boolean delete = delete(sql, id);
        if (delete) {
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteByMaster(String master) {
        String sql = "delete from tag where master = ?";
        boolean delete = delete(sql, master);
        if (delete) {
            return 1;
        }
        return 0;
    }

    @Override
    public int insert(Tag tag) {
        try {
            String sql = "insert into tag(`createDate` ,`updateDate` ,`name` ,`master` ,`status`) " +
                    "values(?,?,?,?,?);";
            return insert(sql, tag.getCreateDate(), tag.getUpdateDate(), tag.getName(), tag.getMaster(), tag.getStatus());
        } catch (Exception e) {
            FunctionUtils.info("insert tag error=[" + e.getMessage() + "] tag=[" + tag.toString() + "]");
            return -1;
        }
    }

}
