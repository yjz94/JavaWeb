package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.User;
import cn.fishland.javaweb.server.UserService;
import cn.fishland.javaweb.server.impl.UserServiceImpl;
import cn.fishland.javaweb.util.FunctionUtils;
import cn.fishland.javaweb.util.StaticField;

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

    private static final UserService userService;

    static {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String uriTail = FunctionUtils.getUriTail(req);

        switch (uriTail) {
            case "/API/user/verify":
                verify(req, resp);
                break;
            default:
        }
    }

    private void verify(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 获得验证码
            String token = (String) req.getSession().getAttribute(StaticField.KAPTCHA_SESSION_KEY);

            // 删除系统验证码
            req.getSession().removeAttribute(StaticField.KAPTCHA_SESSION_KEY);

            // 跳转类型
            byte resultType = 0;

            if (token == null) {
                req.setAttribute("message", "验证码错误，请刷登录新页面！");
            } else {
                // 获得提交提交验证码
                String code = req.getParameter("code");

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
                            req.setAttribute("message", "用户名或密码错误！");
                            break;
                        case 1:
                            req.getSession().setAttribute("user", user);
                            resultType = 1;
                            break;
                        case 2:
                            req.setAttribute("message", "用户名不存在！");
                            break;
                        default:
                            req.setAttribute("message", "登录失败，请稍后重试！");
                    }
                } else {
                    req.setAttribute("message", "验证码错误！");
                }
            }

            if (resultType == 0) {
                req.getRequestDispatcher("/WEB-INF/web/admin/login.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/JavaWeb/admin");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
