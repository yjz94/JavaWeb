package cn.fishland.javaweb.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
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

    /**
     * 根据条数，页数和每页显示数获得分页显示内容
     * 返回值：
     * previous：上一页，为null表示没有
     * item：需要显示的index
     * previous：下一页，为null表示没有
     *
     * @param count 内容行数（条数）
     * @param page  当前页数
     * @param num   每页显示数
     * @return 内容双列集合
     */
    public static Map<String, Object> pagination(int count, int page, int num) {
        int pageNum = (count + num - 1) / num;
        if (pageNum == 0) {
            info("table count is zero");
            return null;
        }
        Map<String, Object> map = new HashMap<>(3);

        map.put("previous", page - 1 < 1 ? null : page - 1);

        int begin = page;
        int end = page;
        if (pageNum <= 5) {
            begin = 1;
            end = pageNum;
        } else {
            for (int i = 0; i < 4; i++) {
                end += 1;
                if (end > pageNum) {
                    end -= 1;
                    begin -= 1;
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            list.add(i);
        }
        map.put("item", list);

        map.put("next", page + 1 > pageNum ? null : page + 1);

        map.put("disabled", page);

        return map;
    }
}
