package com.me.jdbc.sample;


import java.sql.*;

/**
 * 标准JDBC操作五步骤
 */
public class StandardJDBCSample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // 1.加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.创建数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/me?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root", "1q1q1q1q"
            );
            // 3.创建Statement对象
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            // 4.遍历查询结果
            while (resultSet.next()) { // 遍历新的行
                // 提取指定的列
                Integer eno = resultSet.getInt(1); // 第一列字段eno
                String ename = resultSet.getString("ename");// 直接传字段名也可以
                Float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.关闭连接，释放资源
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
