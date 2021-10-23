package cn.fishland.javaweb.dao;

import cn.fishland.javaweb.bean.Article;

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

}