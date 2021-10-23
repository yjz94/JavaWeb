package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.dao.ArticleDao;
import cn.fishland.javaweb.dao.impl.ArticleDaoImpl;
import cn.fishland.javaweb.server.ArticleService;

/**
 * 文章相关服务实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 11:30 上午
 */
public class ArticleServiceImpl implements ArticleService {

    private static ArticleDao articleDao;

    static {
        articleDao = new ArticleDaoImpl();
    }

    @Override
    public boolean save(Article article) {
        int save = articleDao.save(article);
        if (save == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleDao.queryById(id);
    }

}
