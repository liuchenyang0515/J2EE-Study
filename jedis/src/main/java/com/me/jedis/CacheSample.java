package com.me.jedis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class CacheSample {
    public CacheSample(){
        Jedis jedis = new Jedis("192.168.0.107", 6380);
        try {
            List<Goods> goodsList = new ArrayList<>();
            goodsList.add(new Goods(8818, "红富士苹果", "", 3.5f));
            goodsList.add(new Goods(8819, "进口脐橙", "", 5f));
            goodsList.add(new Goods(8820, "进口香蕉", "", 25f));
            jedis.auth("12345");
            jedis.select(3);
            for (Goods goods : goodsList) {
                String json = JSON.toJSONString(goods);
                System.out.println(json);
                String key = "goods:" + goods.getGoodsId();
                jedis.set(key, json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CacheSample();
    }
}
