package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.User;
import cn.fishland.javaweb.dao.UserDao;
import cn.fishland.javaweb.dao.impl.UserDaoImpl;
import cn.fishland.javaweb.server.UserService;

/**
 * 用户相关操作实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 11:41 上午
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public int signIn(User user) {
        // 查询用户是否存在
        boolean exist = userDao.queryByEmail(user.getEmail());

        if (exist) {
            User user1 = userDao.queryByEmailAndPassword(user);
            if (user1 != null && user1.getId() != null) {
                return 1;
            }
            return 0;
        }
        return 2;
    }

}
