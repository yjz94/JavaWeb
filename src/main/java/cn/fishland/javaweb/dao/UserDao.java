package cn.fishland.javaweb.dao;

import cn.fishland.javaweb.bean.User;

/**
 * User操作数据库相关
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 3:55 下午
 */
public interface UserDao {

    User queryByEmailAndPassword(User user);

}
