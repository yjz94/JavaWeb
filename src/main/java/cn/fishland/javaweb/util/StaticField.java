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
    /** 存放草稿文章text的字段 */
    public static String ARTICLE_TEMP_TEXT_FIELD = "text";

    /** 用户登录token存放key (谷歌验证码,字段固定不要随意改动) */
    public static String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";

}
