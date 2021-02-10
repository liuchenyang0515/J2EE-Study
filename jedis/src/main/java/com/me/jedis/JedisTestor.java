package com.me.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisTestor {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.43.92", 6380); // 服务器IP,这里是本地ip，用于本地学习
        try {
            jedis.auth("12345"); // jedis登录密码
            String select = jedis.select(2);
            System.out.println("jedis连接成功");
            // 字符串
            jedis.set("sn", "7781-9938");
            String sn = jedis.get("sn");
            System.out.println(sn);
            jedis.mset(new String[] {"title", "婴幼儿奶粉", "num", "20"});
            List<String> goods = jedis.mget(new String[]{"sn", "title", "num"});
            System.out.println(goods);
            Long num = jedis.incr("num");
            System.out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
