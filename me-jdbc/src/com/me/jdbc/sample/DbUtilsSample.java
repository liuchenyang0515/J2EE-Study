package com.me.jdbc.sample;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.me.jdbc.hrapp.entity.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Apache DBUtils + Druid联合使用
 */
public class DbUtilsSample {
    private static void query() {
        Properties properties = new Properties();
        String propertyFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            QueryRunner queryRunner = new QueryRunner(dataSource);
            // query查询完毕自动关闭连接，无需我们手动处理
            // 利用Apache DbUtils大幅简化了数据的提取过程
            List<Employee> list = queryRunner.query("select * from employee limit ?, 10",
                    new BeanListHandler<>(Employee.class),
                    new Object[]{20}); // 跳过前面20条记录
            for (Employee emp : list) {
                System.out.println(emp.getEname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        Properties properties = new Properties();
        String propertyFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
        Connection connection = null;
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            String sql1 = "update employee set salary=salary+1000 where eno=?";
            String sql2 = "update employee set salary=salary-500 where eno=?";
            QueryRunner queryRunner = new QueryRunner(); // 数据写入不用数据源对象
            queryRunner.update(connection, sql1, new Object[]{1000}); // 写入数据表操作，新增删除修改都算
            queryRunner.update(connection, sql2, new Object[]{1001});
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close(); // 对数据库连接进行回收，是回收不是关闭
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        query();
        update();
    }
}
