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

    /**
     * 根据用户邮箱和密码查询用户信息
     *
     * @param user 被查询用户信息
     * @return 被查询用户信息，不存在返回null
     */
    User queryByEmailAndPassword(User user);

    /**
     * 根据用户邮箱查询用户可存在
     *
     * @param email 用户邮箱
     * @return 是否存在
     */
    boolean queryByEmail(String email);

}
