package com.skillbox.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private Jedis jedis;
    private final static String KEY = "USERS";
    void init() {

        try {
            jedis = new Jedis();
        } catch (JedisException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
    }

    public void addToStorage(String s, int a) {
        double d = a;
        jedis.zadd(KEY, d, s);
    }

    public String getUser(int i) {
        Set<String> zrange = jedis.zrange(KEY, i, i);
        String firstRes = zrange.toString();
        return firstRes.substring(1, firstRes.length() - 1);
    }

    public int getKeyOfUser(String name) {
//        return (jedis.zrank(KEY, name)).intValue();
        return (jedis.zscore(KEY, name)).intValue();
    }

    public void userIncrement(double i, String name) {
        jedis.zincrby(KEY, i, name);
    }

    void shutdown() {
        jedis.shutdown();
    }

}
