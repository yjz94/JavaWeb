package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.bean.Praise;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.server.PraiseService;
import cn.fishland.javaweb.server.impl.ArticleServiceImpl;
import cn.fishland.javaweb.server.impl.PraiseServiceImpl;
import cn.fishland.javaweb.util.FunctionUtils;
import cn.fishland.javaweb.util.RedisUtil;
import cn.fishland.javaweb.util.StaticField;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    private static final PraiseService praiseService;

    static {
        articleService = new ArticleServiceImpl();
        praiseService = new PraiseServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String tail = FunctionUtils.getUriTail(req);
        switch (tail) {
            case "/admin/API/article/insert":
                insert(req, resp);
                break;
            case "/admin/API/article/insert/draft":
                draftSave(req, resp);
                break;
            default:
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String tail = FunctionUtils.getUriTail(req);
        switch (tail) {
            case "/API/article/get":
                getArticle(req, resp);
                break;
            case "/admin/API/article/show":
                show(req, resp);
                break;
            case "/admin/API/article/delete":
                delete(req, resp);
                break;
            default:
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String articleId = req.getParameter("articleId");
            Map<String, Object> map = new HashMap<>(3);
            if (StringUtils.isBlank(articleId)) {
                map.put("error", 1);
            } else {
                boolean b = articleService.deleteArticle(articleId);
                if (b) {
                    map.put("error", 0);
                } else {
                    map.put("error", 1);
                }
            }
            resp.getWriter().println(JSONObject.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setCharacterEncoding("UTF-8");

            String articleId = req.getParameter("articleId");
            Map<String, Object> map = new HashMap<>(3);
            if (StringUtils.isBlank(articleId)) {
                map.put("error", 1);

            } else {
                Article article = articleService.getArticleByArticleId(articleId);

                // 获得文章praise
                Praise praise = new Praise();
                Praise queryPraise = praiseService.getPraiseByMaster(articleId);
                if (queryPraise != null) {
                    praise = queryPraise;
                }
                map.put("error", 0);
                map.put("article", article);
                map.put("praise", praise);
            }

            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.print(JSONObject.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
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
            String text = req.getParameter("text");

            // 保存到redis中
            if (StringUtils.isNotBlank(articleId)) {
                RedisUtil.setString(StaticField.ARTICLE_TEMP_UUID_KEY, articleId);

                Map<String, String> hashMap = new HashMap<>(3);
                hashMap.put(StaticField.ARTICLE_TEMP_TITLE_FIELD, title);
                hashMap.put(StaticField.ARTICLE_TEMP_CONTENT_FIELD, content);
                hashMap.put(StaticField.ARTICLE_TEMP_TAGS_FIELD, tags);
                hashMap.put(StaticField.ARTICLE_TEMP_TEXT_FIELD, text);
                RedisUtil.setHash(articleId, hashMap);
            }

            article(req, resp);
        } catch (Exception e) {
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
        try {
            // 获得字段内容
            String title = req.getParameter("title");
            String tags = req.getParameter("tags");
            String content = req.getParameter("content");
            String articleId = req.getParameter("articleId");
            String text = req.getParameter("text");

            // 判断是修改还是新增
            String crudType = req.getParameter("crudType");

            Article article = new Article();
            article.setArticleId(articleId);
            article.setStatus(1);
            article.setContent(content);
            article.setText(text);
            article.setTitle(title);
            article.setTags(tags);
            article.setCreateDate(new Timestamp(System.currentTimeMillis()));
            article.setUpdateDate(new Timestamp(System.currentTimeMillis()));

            // 保存内容到数据库中
            boolean save = articleService.save(article, crudType);

            // 删除redis中草稿
            if ("insert".equals(crudType)) {
                RedisUtil.del(articleId);
                RedisUtil.del(StaticField.ARTICLE_TEMP_UUID_KEY);
            }

            article(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getArticle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 获得文章articleId
            String articleId = req.getParameter("articleId");
            Article article = articleService.getArticleByArticleId(articleId);

            // 获得文章praise
            Praise praise = new Praise();
            Praise queryPraise = praiseService.getPraiseByMaster(articleId);
            if (queryPraise != null) {
                praise = queryPraise;
            }

            req.setAttribute("article", article);
            req.setAttribute("praise", praise);

            System.out.println(article);
            System.out.println(praise);

            req.getRequestDispatcher("/WEB-INF/web/common/showArticle.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
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

        req.setAttribute("crudType", "insert");

        try {
            req.getRequestDispatcher("/WEB-INF/web/admin/article.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
