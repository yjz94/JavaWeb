package cn.fishland.javaweb.server;

import cn.fishland.javaweb.bean.Article;

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


}
