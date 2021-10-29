package cn.fishland.javaweb.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一些独立功能类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/18 12:13 上午
 */
public class FunctionUtils {

    /** 日志操作类 */
    private static final Logger logger = LogManager.getLogger(FunctionUtils.class);

    /**
     * 获得UUID字符串，不含特殊符号（只含数字字母）
     *
     * @return UUID字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获得str里与pattern匹配的第一个内容
     *
     * @param str     原始字符串
     * @param pattern 匹配规则
     * @return 符合规则内容，未成功匹配返回null
     */
    public static String matchFirstString(String str, String pattern) {
        try {
            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 匹配出字符串所以符合规则的字符串
     *
     * @param str     目标字符串
     * @param pattern 匹配规则
     * @return 集合，无符合的返回size为0的空集合
     */
    public static List<String> matchAllString(String str, String pattern) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(str);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }

    /**
     * 获得url中最后一个字符串,忽略参数 <br/>
     * 例：https://github.com/redis/jedis/wiki/Getting
     * 结果：Getting
     *
     * @param request http请求对象
     * @return 符合条件的字符串
     */
    public static String getUriTail(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri != null) {
            return uri.replace(request.getContextPath(), "");
        }
        return null;
    }

    public static void info(String log) {
        logger.log(Level.INFO, log);
    }
}
