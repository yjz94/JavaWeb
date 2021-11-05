package cn.fishland.javaweb.server.impl;

import cn.fishland.javaweb.bean.Article;
import cn.fishland.javaweb.bean.Praise;
import cn.fishland.javaweb.dao.ArticleDao;
import cn.fishland.javaweb.dao.PraiseDao;
import cn.fishland.javaweb.dao.impl.ArticleDaoImpl;
import cn.fishland.javaweb.dao.impl.PraiseDaoImpl;
import cn.fishland.javaweb.server.ArticleService;
import cn.fishland.javaweb.server.AttachmentService;
import cn.fishland.javaweb.server.PraiseService;
import cn.fishland.javaweb.util.FunctionUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.*;

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

    static {
        articleDao = new ArticleDaoImpl();
        praiseDao = new PraiseDaoImpl();
        praiseService = new PraiseServiceImpl();
        attachmentService = new AttachmentServiceImpl();
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
        dbAttachmentNames.removeAll(attachmentNames);
        attachmentService.removeAttachmentByMaster(dbAttachmentNames.toArray(new String[0]));

        // 生成交互数据
        Praise praise = new Praise();
        praise.setMaster(article.getArticleId());
        praise.setStatus(1);
        praise.setCreateDate(new Timestamp(System.currentTimeMillis()));
        praiseDao.savePraise(praise);

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
    public Map<String, Object> articlePageNum(String baseUrl, int page, int num) {
        int i = articleDao.countArticle();
        int pageCount = (i + num) / num;
        Map<String, Object> hashMap = new HashMap<>(3);

        if (pageCount == 1) {
            hashMap.put("previous", null);
            hashMap.put("next", null);
            hashMap.put("item", Collections.singletonList(1));
        }

        if (pageCount > 1 && pageCount <= 5) {
            if (page == 1) {
                hashMap.put("previous", null);
            } else {
                hashMap.put("previous", page - 1);
                List<Integer> list = new ArrayList<>();
                for (int i1 = 1; i1 <= pageCount; i1++) {
                    list.add(i1);
                }
                hashMap.put("item", list);
                hashMap.put("next", null);
            }
        } else {
            if (page == 1) {
                hashMap.put("previous", null);
            } else {
                hashMap.put("previous", page - 1);
                List<Integer> list = new ArrayList<>();
                for (int i1 = page; i1 <= ((pageCount - page) > 5 ? page + 5 : pageCount); i1++) {
                    list.add(i1);
                }
                hashMap.put("item", list);
                if (pageCount == page) {
                    hashMap.put("next", null);
                } else {
                    hashMap.put("next", page + 1);
                }
            }

        }
        return hashMap;
    }

}
