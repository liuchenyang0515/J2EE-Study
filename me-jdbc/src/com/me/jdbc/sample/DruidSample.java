package com.me.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.me.jdbc.common.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Druid连接池配置与使用
 */
public class DruidSample {
    public static void main(String[] args) {
        // 1.加载属性文件
        Properties properties = new Properties();
        String propertyFile = DruidSample.class.getResource("/druid-config.properties").getPath();
        // 空格->%20，比如c:\java code会变成c:\java%20code
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 2.获取DataSource数据源对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 3.创建数据库连接
            connection = dataSource.getConnection();
            // 可以在下面打断点，然后在navicat上“工具”->“服务器监控”->MYSQL，勾选对应数据库刷新查看连接数目，正好是properties中设置的数目10
//            for(int i = 0; i < 21; ++i) {
//                // 测试连接分配最大数目，因为maxActive=20，这里会卡死，控制台不打印结果，因为第21个连接拿不到，程序不往下走了
//                connection = dataSource.getConnection();
//            }
            preparedStatement = connection.prepareStatement("select * from employee limit 0, 10");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer eno = resultSet.getInt(1); // JDBC中字段索引从1开始，而非0
                String ename = resultSet.getString("ename");
                Float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /**
             * 不使用连接池：connection.close()关闭连接
             * 使用连接池：connection.close()将连接回收至连接池
             */
            DbUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }
}
