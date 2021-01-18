package com.imooc.mybatis.dao;

import com.imooc.mybatis.dto.GoodsDTO;
import com.imooc.mybatis.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用注解开发适合小型敏捷开发的项目，用xml适合大型团队协作的项目
 */
public interface GoodsDAO {
    @Select("select * from t_goods where current_price between  #{min} and #{max} order by current_price limit 0,#{limt}")
    public List<Goods> selectByPriceRange(@Param("min") Float min, @Param("max") Float max, @Param("limt") Integer limt);

    @Insert("INSERT INTO t_goods(title, sub_title, original_cost, current_price, discount, is_free_delivery, category_id) VALUES (#{title} , #{subTitle} , #{originalCost}, #{currentPrice}, #{discount}, #{isFreeDelivery}, #{categoryId})")
    //<selectKey>
    @SelectKey(statement = "select last_insert_id()", before = false, keyProperty = "goodsId", resultType = Integer.class)
    public int insert(Goods goods);

    @Select("select * from t_goods")
    //<resultMap>
    @Results({
            // 最后id = true相当于<id>标签设置主键
            @Result(column = "goods_id", property = "goodsId", id = true),
            //<result>
            @Result(column = "title", property = "title"),
            @Result(column = "current_price", property = "currentPrice")
    })
    public List<GoodsDTO> selectAll();
}
