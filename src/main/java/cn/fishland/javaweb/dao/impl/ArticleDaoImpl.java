package cn.fishland.javaweb.dao.impl;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.dao.ArticleDao;
import cn.fishland.javaweb.dao.BaseDao;

/**
 * 实现文章数据库操作
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 10:53 上午
 */
public class ArticleDaoImpl extends BaseDao<Article> implements ArticleDao {

    @Override
    public int save(Article article) {
        String sql = "insert into article(`articleId`,`title`,`content`,`createDate`,`tags`,`status`) values(?,?,?,?,?,?)";
        return insert(sql, article.getArticleId(), article.getTitle(), article.getContent(), article.getCreateDate(),
                article.getTags(), article.getStatus());
    }

    @Override
    public int delete(Article article) {
        return 0;
    }

    @Override
    public int update(Article article) {
        return 0;
    }

    @Override
    public Article queryById(Integer id) {
        String sql = "select * from article where id = ?";
        return query(sql, id);
    }

    @Override
    public Article queryByArticleId(String articleId) {
        String sql = "select * from article where articleId = ?";
        return query(sql, articleId);
    }
}
