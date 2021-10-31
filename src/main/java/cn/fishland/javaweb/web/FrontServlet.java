package cn.fishland.javaweb.web;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.bean.Praise;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.server.PraiseService;
import cn.fishland.javaweb.server.impl.ArticleServiceImpl;
import cn.fishland.javaweb.server.impl.PraiseServiceImpl;
import cn.fishland.javaweb.util.FunctionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端展示控制类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/26 12:32 上午
 */
public class FrontServlet extends HttpServlet {


    private static final ArticleService articleService;
    private static final PraiseService praiseService;

    static {
        articleService = new ArticleServiceImpl();
        praiseService = new PraiseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = FunctionUtils.getUriTail(req);
        switch (action) {
            case "/index":
                index(req, resp);
                break;
            case "/article":
                article(req, resp);
                break;
            default:
        }
    }

    private void article(HttpServletRequest req, HttpServletResponse resp) {
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

            req.getRequestDispatcher("/WEB-INF/web/front/article.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) {

        // 获得参数
        String pageStr = req.getParameter("page");
        int page = 1;
        if (StringUtils.isNotBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        }

        String numStr = req.getParameter("num");
        int num = 10;
        if (StringUtils.isNotBlank(numStr)) {
            num = Integer.parseInt(numStr);
        }

        // 获得文章列表
        List<Article> articleList = articleService.articleList(page, num);

        Map<String, String> coverMap = new HashMap<>(articleList.size());
        List<String> list = new ArrayList<>();
        // articleId参数
        for (Article article : articleList) {
            list.add(article.getArticleId());

            // 获得文章的封面
            String attachmentName = FunctionUtils.matchFirstString(article.getContent(), "[\\?|\\&]attachmentName\\=(.{32})");
            if (StringUtils.isBlank(attachmentName)) {
                attachmentName = "http://localhost:8080/JavaWeb/imgs/default.jpeg";
            } else {
                attachmentName = "http://localhost:8080/JavaWeb/API/attachment/show?attachmentName=" + attachmentName;
            }
            coverMap.put(article.getArticleId(), attachmentName);
        }

        // 获得交互参数
        List<Praise> praises = praiseService.praiseListByMaster(list);

        Map<String, Praise> praiseMap = new HashMap<>(praises.size());
        for (Praise praise : praises) {
            praiseMap.put(praise.getMaster(), praise);
        }

        req.setAttribute("articleList", articleList);
        req.setAttribute("praiseMap", praiseMap);
        req.setAttribute("coverMap", coverMap);

        try {
            req.getRequestDispatcher("/WEB-INF/web/front/index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
