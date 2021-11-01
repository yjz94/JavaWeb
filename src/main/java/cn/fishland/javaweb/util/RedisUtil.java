package cn.fishland.javaweb.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * redis相关操作工具类
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/25 9:59 下午
 */
public class RedisUtil {

    private static final JedisPool POOL;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1024);
        poolConfig.setMaxIdle(100);
        poolConfig.setMaxWaitMillis(100);
        //jedis 第一次启动时，会报错
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(true);
        POOL = new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

    public static Jedis getJedis() {
        try {
            return POOL.getResource();
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

    public static boolean exist(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return false;
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

    public static List<String> listAll(String key) {
        Jedis jedis = null;
        List<String> list = null;
        try {
            jedis = getJedis();
            Long len = listLen(key);
            list = null;
            if (len > 0) {
                list = new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    list.add(jedis.lindex(key, i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return list;
    }

    public static String getHash(String key, String filed) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hmget(key, filed).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            close(jedis);
        }
    }

    public static void setHash(String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hmset(key, map);
        } catch (Exception e) {
            e.printStackTrace();
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
