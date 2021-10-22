package cn.fishland.javaweb.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 文章相关控制类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/22 5:44 下午
 */
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> parameterMap = req.getParameterMap();

        for (String s : parameterMap.keySet()) {
            System.out.println(s + ":" + Arrays.toString(parameterMap.get(s)));
        }

        System.out.println(parameterMap.keySet().size());
    }
}
