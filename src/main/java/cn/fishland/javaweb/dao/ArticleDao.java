package cn.fishland.javaweb.dao;

import cn.fishland.javaweb.bean.Article;

import java.util.List;

/**
 * 文章数据库相关操作
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 10:50 上午
 */
public interface ArticleDao {

    /**
     * 保存文章
     *
     * @param article 文章内容
     * @return 影响行数
     */
    int save(Article article);

    /**
     * 删除文章
     *
     * @param article 删除条件
     * @return 影响行数
     */
    int delete(Article article);

    /**
     * 修改文章内容
     *
     * @param article 需修改内容
     * @return 影响行数
     */
    int update(Article article);

    /**
     * 查询文章内容，根据id
     *
     * @param id 文章id
     * @return 文章对象
     */
    Article queryById(Integer id);

    /**
     * 根据文章articleId查询文章
     *
     * @param articleId 文章rticleId
     * @return 文章对象，不存在返回null
     */
    Article queryByArticleId(String articleId);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param num  每页个数
     * @return article列表
     */
    List<Article> queryArticleByPage(int page, int num);

}
