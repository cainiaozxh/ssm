package com.atguigu.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @PACKAGE_NAME: com.atguigu.redis.jedis
 * @CLASSNAME: JedisDemo01
 * @AUTHOR: zhangsan
 * @DATE: 2024/4/10 17:51
 * @SINCE 17.0.7
 * @DESCRIPTION: JedisDemo01
 */
public class JedisDemo01 {
    public static void main(String[] args) {
        Jedis jedis = getJedis();
        //testString(jedis);
        //testList(jedis);
        //testSet(jedis);
        //testSet2(jedis);
        //testHash(jedis);
        //testHash2(jedis);
        //testZset(jedis);
        testZset2(jedis);
        closeJedis(jedis);
    }

    public static void testString(Jedis jedis) {
        jedis.set("k1", "v111");
        jedis.set("k2", "100");
        jedis.set("k3", "true");
        Set<String> keys = jedis.keys("*");
        Long dbsize = jedis.dbSize();
        System.out.println("dbsize = " + dbsize);
        int size = keys.size();
        System.out.println("size = " + size);
        keys.forEach(System.out::println);
    }

    public static Jedis getJedis() {
        //指定jedis连接池的配置信息
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMaxIdle(32);
        jedisPoolConfig.setMaxWaitMillis(100*1000);
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setTestOnBorrow(true);

        //根据配置创建jedis连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.74.200", 6379);
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public static void testList(Jedis jedis) {
        List<String> course1 = jedis.lrange("course1", 0, -1);
        course1.forEach(System.out::println);
        System.out.println("==========================");
        List<String> course2 = jedis.lrange("course2", 0, -1);
        System.out.println(course2);
    }
    public static void testSet(Jedis jedis) {
        Set<String> girls = jedis.smembers("girls");
        girls.forEach(System.out::println);
        System.out.println("===================");
        Set<String> boys = jedis.smembers("boys");
        boys.forEach(System.out::println);
    }

    public static void testSet2(Jedis jedis) {
        jedis.sadd("orders", "order1", "order2", "order3");
        Set<String> orders = jedis.smembers("orders");
        orders.forEach(System.out::println);
    }

    public static void testHash(Jedis jedis) {
        Map<String, String> map = jedis.hgetAll("1001");
        map.forEach((s, s2) -> System.out.println(s + " = " + s2));
        System.out.println("==================");
        Map<String, String> map1 = jedis.hgetAll("1002");
        map1.forEach((key, value) -> System.out.println(key + " = " + value));
    }
    public static void testHash2(Jedis jedis) {
        Map<String, String> map = new HashMap<>(5);
        map.put("name", "wangwu");
        map.put("gender", "male");
        map.put("salary", "2000");
        map.put("age", "25");
        map.put("skill", "python");
        Long hset = jedis.hset("1003", map);
        System.out.println("hset = " + hset);
        System.out.println("=====================");
        Map<String, String> map1 = jedis.hgetAll("1003");
        map1.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
    }

    public static void testZset(Jedis jedis) {
        Set<String> movies = jedis.zrange("movie", 0, -1);
        movies.forEach(System.out::println);
        System.out.println("==========倒序===========");
        Set<String> movies2 = jedis.zrevrange("movie", 0, -1);
        movies2.forEach(System.out::println);
        System.out.println("==========分数正序==============");
        Set<Tuple> movies3 = jedis.zrangeByScoreWithScores("movie", 0, 100, 0, 5);
        movies3.forEach(System.out::println);
        System.out.println("==========分数倒叙=========");
        Set<Tuple> movies4 = jedis.zrevrangeByScoreWithScores("movie", 100, 0, 0, 5);
        movies4.forEach(System.out::println);
    }

    public static void testZset2(Jedis jedis) {
        Map<String, Double> scoreMembers = new HashMap<>(5);
        scoreMembers.put("article1", 5.96);
        scoreMembers.put("article2", 6.66);
        scoreMembers.put("article3", 9.89);
        scoreMembers.put("article4", 7.87);
        scoreMembers.put("article5", 8.88);
        Long article = jedis.zadd("article", scoreMembers);
        System.out.println("article = " + article);
        System.out.println("========================");
        Set<Tuple> article1 = jedis.zrevrangeByScoreWithScores("article", 10, 0, 0, 5);
        article1.forEach(System.out::println);
    }


    public static void closeJedis(Jedis jedis) {
        jedis.close();
    }

}
