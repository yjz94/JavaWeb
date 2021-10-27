package cn.fishland.javaweb.util;

/**
 * 程序所用固定字段
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/17 11:30 下午
 */
public class StaticField {

    /** 存放草稿文章id的key */
    public static String ARTICLE_TEMP_UUID_KEY = "articleId";
    /** 存放草稿文章title的字段 */
    public static String ARTICLE_TEMP_TITLE_FIELD = "title";
    /** 存放草稿文章content的字段 */
    public static String ARTICLE_TEMP_CONTENT_FIELD = "content";
    /** 存放草稿文章tags的字段 */
    public static String ARTICLE_TEMP_TAGS_FIELD = "tags";

    /** 未上传文章上传附件的id（这里面存在删除和不用的） */
    public static String ARTICLE_TEMP_ATTACHMENT_ID_LIST = "articleAttachmentList";

}
