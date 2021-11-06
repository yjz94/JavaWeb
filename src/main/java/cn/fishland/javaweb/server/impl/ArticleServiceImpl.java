package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.bean.Praise;
import cn.fishland.javaweb.bean.Tag;
import cn.fishland.javaweb.dao.ArticleDao;
import cn.fishland.javaweb.dao.PraiseDao;
import cn.fishland.javaweb.dao.impl.ArticleDaoImpl;
import cn.fishland.javaweb.dao.impl.PraiseDaoImpl;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.server.AttachmentService;
import cn.fishland.javaweb.server.PraiseService;
import cn.fishland.javaweb.server.TagService;
import cn.fishland.javaweb.util.FunctionUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章相关服务实现类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/23 11:30 上午
 */
public class ArticleServiceImpl implements ArticleService {

    private static final ArticleDao articleDao;
    private static final PraiseDao praiseDao;
    private static final PraiseService praiseService;
    private static final AttachmentService attachmentService;
    private static final TagService tagService;

    static {
        articleDao = new ArticleDaoImpl();
        praiseDao = new PraiseDaoImpl();
        praiseService = new PraiseServiceImpl();
        attachmentService = new AttachmentServiceImpl();
        tagService = new TagServiceImpl();
    }

    @Override
    public boolean save(Article article, String crudType) {
        // 保存文章内容
        if ("insert".equals(crudType)) {
            articleDao.save(article);
        } else if ("update".equals(crudType)) {
            articleDao.update(article);
        }

        // 删除未使用附件
        List<String> attachmentNames =
                FunctionUtils.matchAllString(article.getContent(), "[\\&|\\?]attachmentName\\=(.{32})");
        List<String> dbAttachmentNames = attachmentService.queryAttachmentNameByMaster(article.getArticleId());
        if (CollectionUtils.isNotEmpty(dbAttachmentNames)) {
            dbAttachmentNames.removeAll(attachmentNames);
            attachmentService.removeAttachmentByMaster(dbAttachmentNames.toArray(new String[0]));
        }

        // 生成交互数据
        Praise praise = new Praise();
        praise.setMaster(article.getArticleId());
        praise.setStatus(1);
        praise.setCreateDate(new Timestamp(System.currentTimeMillis()));
        praiseDao.savePraise(praise);

        // 保存标签
        String tags = article.getTags();
        if (StringUtils.isNotBlank(tags)) {
            List<Tag> list = new ArrayList<>();
            String[] split = tags.split(",");
            for (String name : split) {
                list.add(new Tag(new Timestamp(System.currentTimeMillis()), name, article.getArticleId(), 1));
            }
            if (CollectionUtils.isNotEmpty(list)) {
                for (Tag tag : list) {
                    tagService.insert(tag);
                }
            }
        }

        return true;
    }

    @Override
    public Article getArticleByArticleId(String articleId) {
        if (StringUtils.isNotBlank(articleId)) {
            return articleDao.queryByArticleId(articleId);
        }
        return null;
    }

    @Override
    public List<Article> articleList(int page, int num) {
        // 获得所有文章
        return articleDao.queryArticleByPage(page, num);
    }

    @Override
    public Map<String, Object> articleCount(int page, int num) {
        int count = articleDao.countArticle();
        return FunctionUtils.pagination(count, page, num);
    }

    @Override
    public boolean deleteArticle(String articleId) {
        try {
            articleDao.delete(articleId);
            praiseService.deletePraise(articleId);
            attachmentService.deleteAttachment(articleId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<String, Object> articlePageNum(int page, int num) {
        int count = articleDao.countArticle();
        return FunctionUtils.pagination(count, page, num);
    }

}
