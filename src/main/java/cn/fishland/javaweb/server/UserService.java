package cn.fishland.javaweb.server;

import cn.fishland.javaweb.bean.User;

/**
 * 用户相关服务接口
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 10:55 上午
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return true表示登陆成功，反之为失败
     */
    boolean signIn(User user);
}
