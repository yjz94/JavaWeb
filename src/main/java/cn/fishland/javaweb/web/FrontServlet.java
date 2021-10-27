package cn.fishland.javaweb.web;

import cn.fishland.javaweb.util.FunctionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前端展示控制类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/26 12:32 上午
 */
public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = FunctionUtils.getUriTail(req);

        switch (action) {
            case "/index":
                index(req, resp);
                break;
        }
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/web/front/index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
