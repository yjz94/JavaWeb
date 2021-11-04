package cn.fishland.javaweb.server;

import cn.fishland.javaweb.bean.Article;

import java.util.List;
import java.util.Map;

/**
 * 文章相关服务接口
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 11:28 上午
 */
public interface ArticleService {

    /**
     * 保存文章内容
     *
     * @param article 文章内容
     * @return 是否保存充成功
     */
    boolean save(Article article);

    /**
     * 根据articleId查询文章内容
     *
     * @param articleId 文章articleId
     * @return 内容对象
     */
    Article getArticleByArticleId(String articleId);


    /**
     * 根据时间排序分页查询文章
     *
     * @param page 页数
     * @param num  每页显示个数
     * @return 文章列表
     */
    List<Article> articleList(int page, int num);

    /**
     * 文章一些统计数据
     *
     * @return 双列集合
     */
    Map<String, Integer> articleCount();

    /**
     * 删除文章及相关内容
     *
     * @param articleId 文章id
     * @return 是否成功
     */
    boolean deleteArticle(String articleId);
}
