package cn.fishland.javaweb.dao.impl;

import cn.fishland.javaweb.bean.Praise;
import cn.fishland.javaweb.dao.BaseDao;
import cn.fishland.javaweb.dao.PraiseDao;

/**
 * 文章交互数据数据库操作实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/29 10:48 下午
 */
public class PraiseDaoImpl extends BaseDao<Praise> implements PraiseDao {

    @Override
    public Praise queryByMaster(String master) {
        String sql = "select * from praise where master = ?";
        return query(sql, master);
    }

}
