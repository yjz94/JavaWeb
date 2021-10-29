package cn.fishland.javaweb.web;

import cn.fishland.javaweb.util.FunctionUtils;
import cn.fishland.javaweb.util.RedisUtil;
import cn.fishland.javaweb.util.StaticField;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台控制类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 7:42 下午
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = FunctionUtils.getUriTail(req);

        switch (action) {
            case "/admin":
                admin(req, resp);
                break;
            case "/admin/main":
                adminMain(req, resp);
                break;
            case "/admin/attachment":
                attachment(req, resp);
                break;
            case "/admin/article":
                article(req, resp);
                break;
            default:
        }
    }

    private void article(HttpServletRequest req, HttpServletResponse resp) {

        // 先判断是否存在草稿（redis中）
        if (RedisUtil.exist(StaticField.ARTICLE_TEMP_UUID_KEY)) {
            // 文章articleId
            String articleId = RedisUtil.getString(StaticField.ARTICLE_TEMP_UUID_KEY);

            // 保存到与对象中

            // 文章articleId
            req.setAttribute(StaticField.ARTICLE_TEMP_UUID_KEY, articleId);

            // 文章标题
            req.setAttribute(StaticField.ARTICLE_TEMP_TITLE_FIELD,
                    RedisUtil.getHash(articleId, StaticField.ARTICLE_TEMP_TITLE_FIELD));

            // 文章内容
            req.setAttribute(StaticField.ARTICLE_TEMP_CONTENT_FIELD,
                    RedisUtil.getHash(articleId, StaticField.ARTICLE_TEMP_CONTENT_FIELD));

            // 文章标签
            req.setAttribute(StaticField.ARTICLE_TEMP_TAGS_FIELD,
                    RedisUtil.getHash(articleId, StaticField.ARTICLE_TEMP_TAGS_FIELD));

        } else {
            // 文章articleId
            req.setAttribute(StaticField.ARTICLE_TEMP_UUID_KEY, FunctionUtils.getUUID());
        }

        try {
            req.getRequestDispatcher("/WEB-INF/web/admin/article.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void attachment(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/web/admin/attachment.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void admin(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/web/admin/admin.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adminMain(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/web/admin/main.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
