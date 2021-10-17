package cn.fishland.javaweb.dao.impl;

import cn.fishland.javaweb.bean.User;
import cn.fishland.javaweb.dao.BaseDao;
import cn.fishland.javaweb.dao.UserDao;

/**
 * 用户相关数据库操作
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 5:12 下午
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User queryByEmailAndPassword(User user) {
        String sql = "select * from user where email = ? and password = ?";
        return query(sql, user.getEmail(), user.getPassword());
    }

    @Override
    public boolean queryByEmail(String email) {
        String sql = "select id from user where email = ?";
        User query = query(sql, email);
        if (query != null) {
            return true;
        }
        return false;
    }

}
