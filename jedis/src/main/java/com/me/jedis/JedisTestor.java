package com.me.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            // Hash
            jedis.hset("student:3312", "name", "张晓明");// 一个学号3312的学生数据
            String name = jedis.hget("student:3312", "name");
            System.out.println(name);
            Map<String, String> studentMap = new HashMap<>();
            studentMap.put("name", "李兰"); // redis用Unicode显示，"\xe6\x9d\x8e\xe5\x85\xb0"，这里是UTF-8编码1个字符是3个字节
            studentMap.put("age", "18");
            studentMap.put("id", "3313");
            jedis.hmset("student:3313", studentMap);
            Map<String, String> smap = jedis.hgetAll("student:3313");
            System.out.println(smap);

            // List
            jedis.del("letter"); // 避免后续持续rpush和lpush的影响
            jedis.rpush("letter", new String[]{"d", "e", "f"}); // rpush letter d e f
            jedis.lpush("letter", new String[]{"c", "b", "a"});
            // lrange letter 0 -1
            List<String> letterList = jedis.lrange("letter", 0, -1); // 这里是瞬时状态，后续操作这个list是不变的
            jedis.lpop("letter");
            jedis.rpop("letter");
            letterList = jedis.lrange("letter", 0, -1); // 还需要再获得一次瞬时状态list
            System.out.println(letterList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
