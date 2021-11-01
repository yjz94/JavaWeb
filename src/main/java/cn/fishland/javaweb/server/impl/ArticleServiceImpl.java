package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.bean.Praise;
import cn.fishland.javaweb.dao.ArticleDao;
import cn.fishland.javaweb.dao.AttachmentDao;
import cn.fishland.javaweb.dao.PraiseDao;
import cn.fishland.javaweb.dao.impl.ArticleDaoImpl;
import cn.fishland.javaweb.dao.impl.AttachmentDaoImpl;
import cn.fishland.javaweb.dao.impl.PraiseDaoImpl;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.util.FunctionUtils;
import cn.fishland.javaweb.util.RedisUtil;
import cn.fishland.javaweb.util.StaticField;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
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
    private static final AttachmentDao attachmentDao;

    static {
        articleDao = new ArticleDaoImpl();
        attachmentDao = new AttachmentDaoImpl();
        praiseDao = new PraiseDaoImpl();
    }

    @Override
    public boolean save(Article article) {
        // 保存文章内容
        articleDao.save(article);

        // 删除未使用附件
        List<String> attachmentNames =
                FunctionUtils.matchAllString(article.getContent(), "[\\&|\\?]attachmentName\\=(.{32})");
        List<String> strings = RedisUtil.listAll(StaticField.ARTICLE_TEMP_ATTACHMENT_ID_LIST);
        strings.removeAll(attachmentNames);
        attachmentDao.deleteAttachment(strings.toArray(new String[0]));

        // 生成交互数据
        Praise praise = new Praise();
        praise.setMaster(article.getArticleId());
        praise.setStatus(1);
        praise.setCreateDate(new Timestamp(System.currentTimeMillis()));
        praiseDao.savePraise(praise);

        // 删除附件redis
        RedisUtil.del(StaticField.ARTICLE_TEMP_ATTACHMENT_ID_LIST);
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
    public Map<String, Integer> articleCount() {
        int count = articleDao.countArticle();
        int pageNum = (count + 10) / 10;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("count", count);
        map.put("pageNum", pageNum);
        return map;
    }

}
