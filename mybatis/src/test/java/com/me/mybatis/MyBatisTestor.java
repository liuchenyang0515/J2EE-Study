package com.me.mybatis;

import com.me.mybatis.dto.GoodsDTO;
import com.me.mybatis.entity.Goods;
import com.me.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JUNIT单元测试类
public class MyBatisTestor {
    @Test
    public void testSqlSessionFactory() throws IOException {
        // 利用Reader加载classpath下的mybatis-config.xml核心配置文件
        Reader resourceAsReader = Resources.getResourceAsReader("mybatis-config.xml");
        // 初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
        System.out.println("SessionFactory加载成功");
        // =====到此mybatis只是做了初始化的工作
        SqlSession sqlSession = null;
        try {
            // 创建SqlSession对象，SqlSession是JDBC的扩展类，用于数据库的交互
            sqlSession = sqlSessionFactory.openSession();
            // 创建数据库连接(测试用)，这句完全可以不要
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                // 如果dataSource的type="POOLED",代表使用连接池，close则是将连接回收到连接池
                // 如果dataSource的type="UNPOOLED",代表直连，close则会调用Connection.close()方法关闭连接
                // 这些是配置的底层机制不同
                sqlSession.close();
            }
        }
    }

    @Test
    public void testMyBatisUtils() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Goods> list = sqlSession.selectList("goods.selectAll");// 语法：namespace命名空间前缀.SQL语句对应标签的id
            for (Goods g : list) {
                System.out.println(g.getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 1602); // 第二个参数要和goods.xml的parameterType保持一致
            System.out.println(goods.getTitle());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectByPriceRange() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Map<String, Integer> param = new HashMap<>();
            param.put("min", 100);
            param.put("max", 500);
            param.put("limit", 10);
            // 当没有写命名空间的时候，必须保证sql的id全局唯一，否则就需要加上goods
            List<Goods> list = sqlSession.selectList("selectByPriceRange", param);
            for (Goods g : list) {
                System.out.println(g.getTitle() + ":" + g.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectGoodsMap() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Map> list = sqlSession.selectList("goods.selectGoodsMap");
            for (Map map : list) {
                System.out.println(map);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectGoodsDTO() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<GoodsDTO> list = sqlSession.selectList("goods.selectGoodsDTO");
            for (GoodsDTO g : list) {
                // 这里用调试来看，只打印了一个title，调试看到所有字段比较方便
                System.out.println(g.getGoods().getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("测试商品");
            goods.setSubTitle("测试子标题");
            goods.setOriginalCost(200f);
            goods.setCurrentPrice(100f);
            goods.setDiscount(0.5f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(43);
            // insert()方法返回值代表本次成功插入的记录总数
            int insertNum = sqlSession.insert("goods.insert", goods);
            sqlSession.commit(); // 提交事务数据
            System.out.println(goods.getGoodsId());
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback(); // 回滚事务
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            // 获取对应商品原始信息，在此基础上修改
            Goods goods = sqlSession.selectOne("goods.selectById", 739);
            goods.setTitle("更新测试商品");
            sqlSession.update("goods.update", goods);
            sqlSession.commit(); // 提交事务数据
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback(); // 回滚事务
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            // 获取对应商品原始信息，在此基础上修改
            int deleteNum = sqlSession.delete("goods.delete", 739);
            sqlSession.commit(); // 提交事务数据
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback(); // 回滚事务
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectByTitle() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Map<String, String> param = new HashMap<>();
            // 如果SQL没做预处理，可以SQL攻击，${}文本转换改为#{}预编译转换即可防止
            /**
             * ${}原文传值
             * select * from t_goods
             *      where title = '' or 1=1 or title='【德国】爱他美婴幼儿配方奶粉1段800g*2罐 铂金版'
             *
             * #{}预编译
             * select * from t_goods
             *      where title = "'' or 1=1 or title='【德国】爱他美婴幼儿配方奶粉1段800g*2罐 铂金版'"
             */

//            param.put("title", "'' or 1=1 or title='【德国】爱他美婴幼儿配方奶粉1段800g*2罐 铂金版'");
            param.put("title", "【德国】爱他美婴幼儿配方奶粉1段800g*2罐 铂金版");
            param.put("order", " order by title desc"); // 这就必须${}原文传，否则就当做字符串了，没法排序
            // 这只是一个演示，实际排序不会写在这里，注意${}决不允许前端输入控制
            List<Goods> list = sqlSession.selectList("goods.selectByTitle", param);
            for (Goods g : list) {
                System.out.println(g.getTitle() + ":" + g.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testDynamicSQL() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Map<String, Integer> param = new HashMap<>();
            param.put("categoryId", 44);
            param.put("currentPrice", 500);
            // 查询条件
            List<Goods> list = sqlSession.selectList("goods.dynamicSQL", param);
            for (Goods g : list) {
                System.out.println(g.getTitle() + ":" + g.getCategoryId() + ":"
                        + g.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testLv1Cache() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            // 获取对应商品原始信息，在此基础上修改
            Goods goods = sqlSession.selectOne("goods.selectById", 1603);
            // 第一次查询后把结果缓存到JVM内存中，第二次直接从内存获取
            Goods goods1 = sqlSession.selectOne("goods.selectById", 1603);
            System.out.println(goods.hashCode() + ":" + goods1.hashCode()); // hash一样，相同的内存区域
            System.out.println(goods.getTitle());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

        // 一级缓存的生存周期范围是sqlSession，这里是第二个sqlSession，会重新查询
        try {
            sqlSession = MyBatisUtils.openSession();
            // 获取对应商品原始信息，在此基础上修改
            Goods goods = sqlSession.selectOne("goods.selectById", 1603);
            sqlSession.commit(); // commit提交时对该namespace缓存强制清空，这样两个goods的hashCode就不一样
            // 第一次查询后把结果缓存到JVM内存中，第二次直接从内存获取
            Goods goods1 = sqlSession.selectOne("goods.selectById", 1603);
            System.out.println(goods.hashCode() + ":" + goods1.hashCode()); // 同一个会话相同，两个会话中的hashcode就不会相同，
            System.out.println(goods.getTitle());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    @Test
    public void testLv2Cache() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            // 获取对应商品原始信息，在此基础上修改
            Goods goods = sqlSession.selectOne("goods.selectById", 1603);
            System.out.println(goods.hashCode());
            System.out.println(goods.getTitle());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

        // 二级缓存已开启，缓存周期namespace, 缓存命中率提高
        try {
            sqlSession = MyBatisUtils.openSession();
            // 获取对应商品原始信息，在此基础上修改
            Goods goods = sqlSession.selectOne("goods.selectById", 1603);
            System.out.println(goods.hashCode());
            System.out.println(goods.getTitle());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
}
