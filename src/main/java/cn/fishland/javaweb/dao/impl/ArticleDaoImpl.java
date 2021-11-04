package cn.fishland.javaweb.dao.impl;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.dao.ArticleDao;
import cn.fishland.javaweb.dao.BaseDao;
import cn.fishland.javaweb.dao.PraiseDao;
import cn.fishland.javaweb.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 实现文章数据库操作
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 10:53 上午
 */
public class ArticleDaoImpl extends BaseDao<Article> implements ArticleDao {

    private static final PraiseDao praiseDao;

    static {
        praiseDao = new PraiseDaoImpl();
    }

    @Override
    public int save(Article article) {
        String sql = "insert into article(`articleId`,`title`,`content`,`text`,`createDate`,`tags`,`status`)" +
                " values(?,?,?,?,?,?,?)";
        return insert(sql, article.getArticleId(), article.getTitle(), article.getContent(), article.getText(),
                article.getCreateDate(), article.getTags(), article.getStatus());
    }

    @Override
    public int delete(String articleId) {
        String sql = "delete from article where articleId = ?";
        boolean delete = delete(sql, articleId);
        if (delete) {
            return 1;
        }
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

    @Override
    public List<Article> queryArticleByPage(int page, int num) {
        int offset = (page - 1) * num;
        String sql = "SELECT * FROM article WHERE `status` = 1 GROUP BY id DESC LIMIT " + offset + "," + num;
        return queryList(sql, null);
    }

    @Override
    public int countArticle() {
        try {
            String sql = "select count(1) from article where status = 1";
            Connection connection = JdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
