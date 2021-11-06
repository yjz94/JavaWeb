package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.bean.Attachment;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.server.AttachmentService;
import cn.fishland.javaweb.server.PraiseService;
import cn.fishland.javaweb.server.impl.ArticleServiceImpl;
import cn.fishland.javaweb.server.impl.AttachmentServiceImpl;
import cn.fishland.javaweb.server.impl.PraiseServiceImpl;
import cn.fishland.javaweb.util.FunctionUtils;
import cn.fishland.javaweb.util.RedisUtil;
import cn.fishland.javaweb.util.StaticField;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 后台控制类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/20 7:42 下午
 */
public class AdminServlet extends HttpServlet {

    public static ArticleService articleService;
    public static PraiseService praiseService;
    public static AttachmentService attachmentService;

    static {
        articleService = new ArticleServiceImpl();
        praiseService = new PraiseServiceImpl();
        attachmentService = new AttachmentServiceImpl();
    }

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
            case "/admin/login":
                login(req, resp);
                break;
            case "/admin/articleManager":
                articleManager(req, resp);
                break;
            default:
        }
    }

    private void articleManager(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 获得参数
            String pageStr = req.getParameter("page");
            int page = 1;
            if (StringUtils.isNotBlank(pageStr)) {
                page = Integer.parseInt(pageStr);
            }
            String numStr = req.getParameter("num");
            int num = 20;
            if (StringUtils.isNotBlank(numStr)) {
                num = Integer.parseInt(numStr);
            }

            // 获得文章
            List<Article> articles = articleService.articleList(page, num);

            // 获得页数信息
            Map<String, Object> map = articleService.articlePageNum(page, num);

            req.setAttribute("articles", articles);
            req.setAttribute("map", map);

            req.getRequestDispatcher("/WEB-INF/web/admin/articleManager.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/web/admin/login.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void article(HttpServletRequest req, HttpServletResponse resp) {

        // 判断是否是修改文章，修改文章会把草稿内容给覆盖，请注意
        String articleId = req.getParameter("articleId");

        String crudType = req.getParameter("crudType");
        if (StringUtils.isBlank(crudType)) {
            crudType = "insert";
        }

        String title = null;
        String content = null;
        String tags = null;
        String text = null;

        if (StringUtils.isBlank(articleId)) {
            // 先判断是否存在草稿（redis中）
            if (RedisUtil.exist(StaticField.ARTICLE_TEMP_UUID_KEY)) {
                // 文章articleId
                articleId = RedisUtil.getString(StaticField.ARTICLE_TEMP_UUID_KEY);
                // 文章标题
                title = RedisUtil.getHash(articleId, StaticField.ARTICLE_TEMP_TITLE_FIELD);
                // 文章内容
                content = RedisUtil.getHash(articleId, StaticField.ARTICLE_TEMP_CONTENT_FIELD);
                // 文章标签
                tags = RedisUtil.getHash(articleId, StaticField.ARTICLE_TEMP_TAGS_FIELD);
                // 文章文本
                text = RedisUtil.getHash(articleId, StaticField.ARTICLE_TEMP_TEXT_FIELD);
            } else {
                // 生成文章articleId
                articleId = FunctionUtils.getUUID();
            }
        } else {
            Article article = articleService.getArticleByArticleId(articleId);
            title = article.getTitle();
            content = article.getContent();
            tags = article.getTags();
            text = article.getText();
        }

        // 文章articleId
        req.setAttribute(StaticField.ARTICLE_TEMP_UUID_KEY, articleId);
        // 文章标题
        req.setAttribute(StaticField.ARTICLE_TEMP_TITLE_FIELD, title);
        // 文章内容
        req.setAttribute(StaticField.ARTICLE_TEMP_CONTENT_FIELD, content);
        // 文章标签
        req.setAttribute(StaticField.ARTICLE_TEMP_TAGS_FIELD, tags);
        // 文章文本
        req.setAttribute(StaticField.ARTICLE_TEMP_TEXT_FIELD, text);

        // 是修改还是新增
        req.setAttribute("crudType", crudType);

        try {
            req.getRequestDispatcher("/WEB-INF/web/admin/article.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void attachment(HttpServletRequest req, HttpServletResponse resp) {
        // 获得参数
        String pageStr = req.getParameter("page");
        int page = 1;
        if (StringUtils.isNotBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        String numStr = req.getParameter("num");
        int num = 20;
        if (StringUtils.isNotBlank(numStr)) {
            num = Integer.parseInt(numStr);
        }

        List<Attachment> list = attachmentService.attachmentList(page, num);

        Map<String, Object> map = attachmentService.attachmentPagination(page, num);

        req.setAttribute("list", list);
        req.setAttribute("map", map);

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
