package cn.fishland.javaweb.util;

import java.util.UUID;

/**
 * 一些独立功能类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/18 12:13 上午
 */
public class FunctionUtils {

    /**
     * 获得UUID字符串，不含特殊符号（只含数字字母）
     *
     * @return UUID字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
