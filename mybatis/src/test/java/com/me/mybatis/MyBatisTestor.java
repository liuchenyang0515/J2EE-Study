package com.me.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

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
}