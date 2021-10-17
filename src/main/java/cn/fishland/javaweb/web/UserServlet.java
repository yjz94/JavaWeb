package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.User;
import cn.fishland.javaweb.server.UserService;
import cn.fishland.javaweb.server.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户相关小程序
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 12:52 上午
 */
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        boolean signInResult = userService.signIn(user);

        RequestDispatcher requestDispatcher;
        if (signInResult) {
            requestDispatcher = req.getRequestDispatcher("/index.jsp");
        } else {
            requestDispatcher = req.getRequestDispatcher("/web/signin.jsp");
        }
        requestDispatcher.forward(req, resp);
    }
}
