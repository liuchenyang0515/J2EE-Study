package com.me.jdbc.common;

import java.sql.*;

public class DbUtils {
    /**
     * 创建新的数据库连接
     *
     * @return 新的Connection对象
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 1.加载并注册JDBC驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.创建数据库连接
        String url = "jdbc:mysql://localhost:3306/me?userSSL=false&useUnicode=true&characterEncoding=UTF-8" +
                "&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        return DriverManager.getConnection(url, "root", "1q1q1q1q");
    }

    /**
     * 关闭连接，释放资源
     *
     * @param resultSet  结果集对象
     * @param statement  Statement对象
     * @param connection Connection对象
     */
    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
        // 5.关闭连接，释放资源
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close(); //  其实connection关闭之后，statement和resultSet会自动关闭
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
