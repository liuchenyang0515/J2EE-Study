package com.me.jdbc.sample;


import com.me.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class BatchSample {
    // 标准方式插入若干数据
    private static void tc1() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            long startTime = new Date().getTime();
            connection = DbUtils.getConnection();
            // JDBC默认使用自动提交模式
            connection.setAutoCommit(false); // 关闭自动提交
            String sql = "insert into employee(eno, ename, salary, dname) values(?, ?, ?, ?)";
            for (int i = 100000; i < 200000; ++i) {
                preparedStatement = connection.prepareStatement(sql); // 每次都会创建prepareStatement对象去解析一条sql
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "员工" + i);
                preparedStatement.setFloat(3, 4000f);
                preparedStatement.setString(4, "市场部");
                preparedStatement.executeUpdate();
            }
            connection.commit(); // 提交数据
            long endTime = new Date().getTime();
            System.out.println("tc1()执行时长：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.rollback(); // 数据回滚
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            DbUtils.closeConnection(null, preparedStatement, connection);
        }
    }

    // 使用批处理插入若干数据
    private static void tc2() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            long startTime = new Date().getTime();
            connection = DbUtils.getConnection();
            // JDBC默认使用自动提交模式
            connection.setAutoCommit(false); // 关闭自动提交
            String sql = "insert into employee(eno, ename, salary, dname) values(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 200000; i < 300000; ++i) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "员工" + i);
                preparedStatement.setFloat(3, 4000f);
                preparedStatement.setString(4, "市场部");
                preparedStatement.addBatch(); // 将参数加入到批处理任务中
//                preparedStatement.executeUpdate();
            }
            // 10W数据一次打包到mysql，10W数据一次性执行插入
            preparedStatement.executeBatch(); // 执行批处理任务
            connection.commit(); // 提交数据
            long endTime = new Date().getTime();
            System.out.println("tc2()执行时长：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.rollback(); // 数据回滚
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            DbUtils.closeConnection(null, preparedStatement, connection);
        }
    }

    public static void main(String[] args) {
        tc1(); // 时间主要浪费在不断创建preparedStatement和解析10W次sql
        tc2(); // 创建一个preparedStatement，解析一次sql，带上10W组参数
        /**
         * 个人机器处理时间，如果是复杂的sql，这个差距会更加明显
         * tc1()执行时长：29422
         * tc2()执行时长：18409
         */
    }
}
