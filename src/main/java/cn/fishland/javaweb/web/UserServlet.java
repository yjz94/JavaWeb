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

        // 获得验证码
        String token = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        // 删除系统验证码
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");

        // 获得提交提交验证码
        String code = req.getParameter("code");

        RequestDispatcher requestDispatcher = null;
        if (token.equals(code)) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            int signInStatus = userService.signIn(user);

            req.setAttribute("email", user.getEmail());
            req.setAttribute("password", user.getPassword());

            switch (signInStatus) {
                case 0:
                    req.setAttribute("SignInResult", "用户名或密码错误！");
                    requestDispatcher = req.getRequestDispatcher("/admin/signIn.jsp");
                    break;
                case 1:
                    resp.sendRedirect("http://localhost:8080/JavaWeb/admin/admin.jsp");
                    return;
                case 2:
                    req.setAttribute("SignInResult", "用户名不存在！");
                    requestDispatcher = req.getRequestDispatcher("/admin/signIn.jsp");
                    break;
                default:
                    req.setAttribute("SignInResult", "登录失败，请稍后重试！");
                    requestDispatcher = req.getRequestDispatcher("/admin/signIn.jsp");
            }
        } else {
            req.setAttribute("SignInResult", "验证码错误！");
            requestDispatcher = req.getRequestDispatcher("/admin/signIn.jsp");
        }

        requestDispatcher.forward(req, resp);
    }
}
