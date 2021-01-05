package com.me.jdbc.sample;

import com.me.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionSample {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            // JDBC默认使用自动提交模式
            connection.setAutoCommit(false); // 关闭自动提交
            String sql = "insert into employee(eno, ename, salary, dname) values(?, ?, ?, ?)";
            for (int i = 1000; i < 2000; ++i) {
                if (i == 1005) {
                    throw new RuntimeException("插入失败"); // 注释掉即可全部插入成功
                }
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "员工" + i);
                preparedStatement.setFloat(3, 4000f);
                preparedStatement.setString(4, "市场部");
                preparedStatement.executeUpdate();
            }
            connection.commit(); // 提交数据
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
}
