package com.atguigu.redis.jedis;

import com.sun.media.sound.SoftTuning;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @PACKAGE_NAME: com.atguigu.redis.jedis
 * @CLASSNAME: PhoneCode1
 * @AUTHOR: zhangsan
 * @DATE: 2024/4/11 18:56
 * @SINCE 17.0.7
 * @DESCRIPTION: PhoneCode1
 */
public class PhoneCode1 {
    public static void main(String[] args) {
        //1,输入手机号
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入手机号:");
        String inputPhoneNum = scanner.next();
        //2,验证手机号
        boolean b = verifyPhoneNum(inputPhoneNum);
        if (!b) {
            System.out.println("手机号码格式错误,请重新输入");
        }
        //说明手机号码格式正确
        //3,点击发送手机验证码
        System.out.println("获取手机验证码[y/n]");
        String next2 = scanner.next();

        //4,获取验证码
        Jedis jedis = getJedis();
        String code = sendPhoneCode(jedis, inputPhoneNum);
        if ("-1".equals(code)) {
            System.out.println("每个手机每天最多只能获取三次验证码,请明天再试");
            return;
        }
        //说明服务器发送验证码成功
        System.out.println("请输入验证码:");
        String inputCode = scanner.next();

        boolean isCodeTrue = verifyCode(jedis, inputPhoneNum, inputCode.trim());
        if (!isCodeTrue) {
            //验证码错误
            System.out.println("验证码错误,请重新输入");
        } else {
            System.out.println("验证码正确,正在登录...");
        }

        //关闭jedis
        jedis.close();
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

    /**
     * 获取验证码
     * @return
     */
    public static String getCode() {
        String string = UUID.randomUUID().toString();
        String code = string.substring(0, 6);
        return code;
    }

    /**
     * 验证手机号码
     * @return
     */
    public static boolean verifyPhoneNum(String inputPhoneNum) {
        String regex = "^1[3|4|5|8]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputPhoneNum.trim());
        if (!matcher.find()) {
            //没找到
            System.out.println("手机号码格式错误,请输入正确的手机号码");
            return false;
        } else {
            //找到了
            String group0 = matcher.group(0);
            System.out.println("group0 = " + group0);
            return true;
        }
    }

    /**
     * 服务器给用户发送验证码,在redis中生成验证码
     * @param jedis
     * @param phoneNum
     * @return
     */
    public static String sendPhoneCode(Jedis jedis,String phoneNum) {
        String codeKey = "code:" + phoneNum;
        String countKey = "count:" + phoneNum;
        //查看当前phoneNum是否在2分钟之内发送过验证码,如果发送过验证码,则提示请勿频繁获取验证码
        //60秒之后,才能获取
        Boolean exists = jedis.exists(codeKey);
        Long ttl = jedis.ttl(codeKey);
        if (exists && ttl >= 5) {
            //如果验证码存在,且过期时间大于5秒,则提示请勿重复获取验证码
            System.out.println("验证码未过期,请勿重复获取验证码");
            return null;
        } else {
            //如果验证码存在,过期时间小于5秒,则重新发送验证码
            //1,服务器端生成验证码
            String code = getCode();
            //2,将验证码存在reids中过期时间2分钟
            jedis.setex(codeKey, 120, code);

            //3,每天至多获取三次,每个验证码至多只能输入10次
            String count = jedis.get(countKey);
            if (count == null || "".equals(count)) {
                jedis.setex(countKey, 24*60*60, "1");
            } else if (Integer.parseInt(count) <= 3) {
                jedis.incr(countKey);
            } else {
                code = "-1";
            }
            return code;
        }
    }

    /**
     * 验证输入的验证码
     * @return
     */
    public static boolean verifyCode(Jedis jedis,String phoneNum,String inputCode) {
        //1,先验证验证码的格式是否正确 6位数字字母组合
        String regex = "^[0-9a-zA-Z]{6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputCode.trim());
        if (!matcher.find()) {
            //没找到,说明验证码格式错误
            System.out.println("验证码格式错误,请重新输入");
        }
        //说明验证码格式正确,从redis中获取验证码进行验证
        String codeKey = "code:" + phoneNum;
        String code = jedis.get(codeKey);
        if (inputCode.trim().equalsIgnoreCase(code)) {
            return true;
        } else {
            return false;
        }
    }

}
