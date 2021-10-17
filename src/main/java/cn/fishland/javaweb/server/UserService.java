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
     * @return 0：登录失败，账户或密码错误，1：登录成功，2：用户不存在
     */
    int signIn(User user);
}
