package com.me.mybatis;

import com.me.mybatis.entity.Goods;
import com.me.mybatis.utils.MyBatisUtils;
import com.mysql.cj.Session;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ParameterMetaData;
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
}
