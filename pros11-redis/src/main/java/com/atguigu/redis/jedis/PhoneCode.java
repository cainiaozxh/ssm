package com.atguigu.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @PACKAGE_NAME: com.atguigu.redis.jedis
 * @CLASSNAME: PhoneCode
 * @AUTHOR: zhangsan
 * @DATE: 2024/4/11 15:16
 * @SINCE 17.0.7
 * @DESCRIPTION: PhoneCode
 * 手机验证码
 */
public class PhoneCode {
    public static void main(String[] args) {
        //1,输入手机号
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入手机号码: ");
        String inputPhoneNum = scanner.next();
        //2,验证手机号码
        boolean b = verifyPhoneNum(inputPhoneNum);
        if (!b) {
            System.out.println("手机号码 格式错误");
            return;
        }
        //说明手机号码 格式正确
        //3,点击发送手机验证码
        System.out.println("获取验证码[y/n]:");
        String next2 = scanner.next();
        //4,获取验证码
        Jedis jedis = getJedis();
        String code = sendPhoneCode(jedis, inputPhoneNum);
        if ("-1".equals(code)) {
            System.out.println("今天获取验证码已经超过3次,请求明天再试");
            return;
        }
        System.out.println("请输入验证码:");
        String inputCode = scanner.next();

        //说明验证码不是-1,则进行验证 验证码是否正确
        boolean isCodeTrue = verifyCode(jedis, inputPhoneNum, inputCode.trim());
        if (!isCodeTrue) {
            //验证失败
            System.out.println("验证码错误,请重新输入");
        } else {
            System.out.println("验证码正确,正在登录...");
        }

        //关闭jedis
        jedis.close();
    }

    public static boolean verifyPhoneNum(String inputPhoneNum) {
        String regex = "^1[3|4|5|8]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputPhoneNum.trim());
        if (!matcher.find()) {
            //没找到
            System.out.println("group0 = " + matcher.group(0));
            return false;
        } else {
            return true;
        }
    }

    public static String getCode() {
        String string = UUID.randomUUID().toString();
        String substring = string.substring(0, 6);
        return substring;
    }

    public static boolean verifyCode(Jedis jedis,String phone, String inputCode) {
        //首先判断输入的验证码的格式,只能是6位的字母或者数字
        String regex = "^[0-9a-zA-Z]{6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputCode);
        if (!matcher.find()) {
            //没找到,说明格式不正确
            System.out.println("请输入正确格式的验证码");
            return false;
        }
        //找到了,说明验证码格式正确
        //从redis中拿出进行比对
        String codeKey = "code:" + phone;
        String code = jedis.get(codeKey);
        if (inputCode.trim().equalsIgnoreCase(code)) {
            return true;
        } else {
            return false;
        }
    }

    public static String sendPhoneCode(Jedis jedis,String phone) {
        //1获取验证码
        String code = getCode();
        //2将验证码保存到redis中.两分钟有效
        String codeKey = "code:" + phone;
        jedis.setex(codeKey, 120, code);
        //3要求每个手机号,每天只能输入三次
        String countKey = "count:" + phone;
        //从redis中获取,不存在就添加
        String count = jedis.get(countKey);
        if (count == null || "".equals(count)) {
            jedis.setex(countKey, 24*60*60, "1");
        } else if (Integer.parseInt(count) <= 3){
            jedis.incr(countKey);
        } else {
            //如果输入的次数 大于3次,则将code置为-1
            code = "-1";
        }
        //jedis.close();
        return code;
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
}
