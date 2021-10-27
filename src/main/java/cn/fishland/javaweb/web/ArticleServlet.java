package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.server.impl.ArticleServiceImpl;
import cn.fishland.javaweb.util.FunctionUtils;
import cn.fishland.javaweb.util.RedisUtil;
import cn.fishland.javaweb.util.StaticField;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 文章相关控制类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/22 5:44 下午
 */
public class ArticleServlet extends HttpServlet {

    private static final ArticleService articleService;

    static {
        articleService = new ArticleServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String tail = FunctionUtils.getUriTail(req);
        switch (tail) {
            case "/API/article/insert":
                insert(req, resp);
                break;
            case "/API/article/draftSave":
                draftSave(req, resp);
                break;
            default:
        }

    }

    /**
     * 草稿保存到redis中
     *
     * @param req  {@link javax.servlet.http.HttpServletRequest}
     * @param resp {@link javax.servlet.http.HttpServletResponse}
     */
    private void draftSave(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String articleId = req.getParameter("articleId");
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String tags = req.getParameter("tags");

            // 保存到redis中
            if (StringUtils.isNotBlank(articleId)) {
                RedisUtil.setString(StaticField.ARTICLE_TEMP_UUID_KEY, articleId);

                Map<String, String> hashMap = new HashMap<>(3);
                hashMap.put(StaticField.ARTICLE_TEMP_TITLE_FIELD, title);
                hashMap.put(StaticField.ARTICLE_TEMP_CONTENT_FIELD, content);
                hashMap.put(StaticField.ARTICLE_TEMP_TAGS_FIELD, tags);
                RedisUtil.setHash(articleId, hashMap);
            }

            resp.getWriter().print("{'err':0,'msg':''}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存article内容
     *
     * @param req  {@link javax.servlet.http.HttpServletRequest}
     * @param resp {@link javax.servlet.http.HttpServletResponse}
     */
    private void insert(HttpServletRequest req, HttpServletResponse resp) {
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

        boolean save = articleService.save(article);

        if (save) {
            req.setAttribute("result", "{'error':1,'msg':'保存文章失败！'}");
        }
    }


}
