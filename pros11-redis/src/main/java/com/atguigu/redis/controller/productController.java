package com.atguigu.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Case;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Random;

/**
 * @PACKAGE_NAME: com.atguigu.redis.controller
 * @CLASSNAME: RedisController
 * @AUTHOR: zhangsan
 * @DATE: 2024/4/12 17:26
 * @SINCE 17.0.7
 * @DESCRIPTION: productController
 */
@Slf4j
@Controller
@RequestMapping("/prod")
public class productController {

    public String getUID() {
        String uid = new Random().nextInt(50000) + "";
        return uid;
    }

    public static Jedis getJedis() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMaxIdle(32);
        jedisPoolConfig.setMaxWaitMillis(100*1000);
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setTestOnBorrow(true);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.74.200", 6379);
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public static Jedis getJedis2() {
        Jedis jedis = new Jedis("192.168.74.200", 6379);
        return jedis;
    }

    /**
     * 第一版秒杀,没有事务版本
     * @param prodid
     * @return
     */
    @ResponseBody
    @RequestMapping("/doseckill1")
    public String seckill_1(@RequestParam("prodid") String prodid) {
        //1,用户id和商品id非空判断
        String uid = getUID();
        if (uid == null || prodid == null) {
            return "用户没有登录或者请选择要秒杀的商品";
        }
        //2,链接redis
        Jedis jedis = getJedis();

        //3,拼接key
        //3.1 库存key
        String kcKey = "sk:"+prodid+":kc";
        //3.2 秒杀成功 用户key,购买该商品的用户
        String userKey = "sk:" + prodid + ":user";

        //4,完成秒杀
        String result = "";
        String kc = jedis.get(kcKey);
        if (null == kc) {
            //kc 为null,秒杀还没有开始
            //System.out.println("秒杀还没有开始,请等待");
            log.debug("秒杀还没有开始,请等待");
            result="秒杀还没有开始,请等待";
        }else if (jedis.sismember(userKey, uid)) {
            //判断用户是否重复进行了秒杀操作
            result = "已经秒杀成功,请勿重复参与";
            log.debug("已经秒杀成功,请勿重复参加");
        }else if (Integer.parseInt(kc) <= 0) {
            //秒杀已经结束
            result = "秒杀已经结束";
        }else {
            //正常参加秒杀,减库存,增加秒杀用户id
            jedis.decr(kcKey);
            jedis.sadd(userKey, uid);
            log.debug("秒杀成功");
            result = "秒杀成功";
        }
        //关闭jedis
        jedis.close();
        return result;
    }

    /**
     * 解决seckill_1产生的超卖问题
     * @param prodid
     * @return
     */
    @RequestMapping("/doseckill2")
    @ResponseBody
    public String seckill_2(@RequestParam("prodid") String prodid) {
        //1,用户id和商品id非空判断
        String uid = getUID();
        if (uid == null || prodid == null) {
            return "用户没有登录或者请选择要秒杀的商品";
        }
        //2,连接redis
        Jedis jedis = getJedis2();
        //3,拼接库存key和秒杀到商品的用户key
        String kcKey = "sk:"+prodid+":kc";
        //4,秒杀成功的用户key,
        String userKey = "sk:" + prodid +":user";

        String result = "";
        //5,获取库存,监视库存,完成秒杀
        String kc = jedis.get(kcKey);
        jedis.watch(kcKey);

        if (null == kc) {
            //5.1 说明秒杀还没有开始
            log.debug("秒杀还没有开始,请等待");
            result = "秒杀还没有开始,请等待";
        } else if (jedis.sismember(userKey, uid)) {
            //5.2 判断用户是否重复下单
            log.debug("秒杀成功,请勿再次秒杀");
            result = "秒杀成功,请勿再次秒杀";
        } else if (Integer.parseInt(kc) <= 0) {
            //5.3 判断库存数量,秒杀结束
            log.debug("秒杀结束");
            result = "秒杀结束";
        } else {
            //5.4 进行秒杀
            //使用事务
            Transaction multi = jedis.multi();
            //排队
            multi.decr(kcKey);
            multi.sadd(userKey, uid);
            //指定
            List<Object> results = multi.exec();

            if (null == results || results.isEmpty()) {
                log.debug("秒杀失败了...");
                result = "秒杀失败了...";
            } else {
                log.debug("秒杀成功...");
                result = "秒杀成功...";
            }
        }
        jedis.close();
        return result;
    }

    /**
     * 已经秒杀过,返回2
     * 库存为0 秒杀结束 返回 0
     * 成功秒杀 返回 1
     */
    private static String secKillScript ="local userid=KEYS[1];\r\n" +
            "local prodid=KEYS[2];\r\n" +
            "local qtkey='sk:'..prodid..\":kc\";\r\n" +
            "local usersKey='sk:'..prodid..\":usr\";\r\n" +
            "local userExists=redis.call(\"sismember\",usersKey,userid);\r\n" +
            "if tonumber(userExists)==1 then \r\n" +
            "   return 2;\r\n" +
            "end\r\n" +
            "local num= redis.call(\"get\" ,qtkey);\r\n" +
            "if tonumber(num)<=0 then \r\n" +
            "   return 0;\r\n" +
            "else \r\n" +
            "   redis.call(\"decr\",qtkey);\r\n" +
            "   redis.call(\"sadd\",usersKey,userid);\r\n" +
            "end\r\n" +
            "return 1" ;

    @RequestMapping("/doseckill3")
    @ResponseBody
    public String seckill_3(@RequestParam("prodid") String prodid) {

        //1,用户id和商品id非空判断
        String uid = getUID();
        if (uid == null || prodid == null) {
            return "用户没有登录或者请选择要秒杀的商品";
        }
        //2,连接redis
        Jedis jedis = getJedis2();

        String sha1 = jedis.scriptLoad(secKillScript);
        Object result = jedis.evalsha(sha1, 2, uid, prodid);
        String respdata = "";
        String resultStr = String.valueOf(result);
        switch (resultStr) {
            case "0":
                //库存为0,秒杀结束
                log.debug("库存为0,秒杀结束...");
                respdata = "库存为0,秒杀结束...";
                break;
            case "1":
                log.debug("秒杀成功...");
                respdata = "秒杀成功...";
                break;
            case "2":
                log.debug("已经秒杀成功,请勿重复参加...");
                respdata = "已经秒杀成功,请勿重复参加...";
                break;
            default:
                log.debug("秒杀异常,请稍后再试...");
                respdata = "秒杀异常,请稍后再试...";
        }
        jedis.close();
        return respdata;
    }
}
