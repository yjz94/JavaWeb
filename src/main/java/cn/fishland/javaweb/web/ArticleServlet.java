package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.server.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * 文章相关控制类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/22 5:44 下午
 */
public class ArticleServlet extends HttpServlet {

    private static ArticleService articleService;

    static {
        articleService = new ArticleServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        switch (action) {
            case "insertArticle":
                insertArticle(req, resp);
                break;
        }

    }

    private void insertArticle(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("title");
        String tags = req.getParameter("tags");
        String content = req.getParameter("content");

        Article article = new Article();
        article.setStatus(1);
        article.setContent(content);
        article.setTitle(title);
        article.setTags(tags);
        article.setCreateDate(new Timestamp(System.currentTimeMillis()));
        article.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        articleService.save(article);
    }
}
