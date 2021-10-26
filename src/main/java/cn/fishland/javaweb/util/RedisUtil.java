package cn.fishland.javaweb.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis相关操作工具类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/25 9:59 下午
 */
public class RedisUtil {

    private static JedisPool pool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1024);
        poolConfig.setMaxIdle(100);
        poolConfig.setMaxWaitMillis(100);
        //jedis 第一次启动时，会报错
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(true);
        pool = new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

    public static Jedis getJedis() {
        try {
            return pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setString(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
    }

    public static String getString(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            close(jedis);
        }
    }

    public static void del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
    }

    public static void setLPush(String key, String... params) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpush(key, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }

    }

    public static Long listLen(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        } finally {
            close(jedis);
        }
    }

    public static String listRPop(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            close(jedis);
        }
    }

    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
