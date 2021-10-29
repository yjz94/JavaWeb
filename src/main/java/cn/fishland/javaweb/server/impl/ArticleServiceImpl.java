package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.dao.ArticleDao;
import cn.fishland.javaweb.dao.AttachmentDao;
import cn.fishland.javaweb.dao.impl.ArticleDaoImpl;
import cn.fishland.javaweb.dao.impl.AttachmentDaoImpl;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.util.FunctionUtils;

import java.util.List;

/**
 * 文章相关服务实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 11:30 上午
 */
public class ArticleServiceImpl implements ArticleService {

    private static ArticleDao articleDao;

    private static AttachmentDao attachmentDao;

    static {
        articleDao = new ArticleDaoImpl();
        attachmentDao = new AttachmentDaoImpl();
    }

    @Override
    public boolean save(Article article) {
        // 保存文章内容
        int save = articleDao.save(article);
        if (save == 1) {
            // 删除未使用附件
            List<String> attachmentNames =
                    FunctionUtils.matchAllString(article.getContent(), "\\&attachmentName\\=(.{32})");
            attachmentDao.deleteAttachment(attachmentNames.toArray(new String[0]));
            return true;
        }
        return false;
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleDao.queryById(id);
    }

}
