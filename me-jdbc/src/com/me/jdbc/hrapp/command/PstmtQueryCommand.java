package com.me.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class PstmtQueryCommand implements Command {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public void execute() {
        System.out.println("请输入部门名称：");
        Scanner in = new Scanner(System.in);
        String pdname = in.nextLine();
        try {
            // 1.加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2.创建数据库连接
            String url = "jdbc:mysql://localhost:3306/me?userSSL=false&useUnicode=true&characterEncoding=UTF-8" +
                    "&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
            connection = DriverManager.getConnection(url, "root", "1q1q1q1q");

            // 3.创建preparedStatement对象
            String sql = "select * from employee where dname=? and eno > ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pdname); // 参数索引从1开始
            preparedStatement.setInt(2, 3500);
            // 结果集
            // SQL注入攻击无效，' or 1=1 or '的单影号被转义处理，输入当成一个整体，\' or 1=1 or \' ，这样查询不到任何记录
            resultSet = preparedStatement.executeQuery();

            // 4.遍历查询结果
            // rs.next()返回布尔值，代表是否存在下一条记录
            // 如果有，返回true，同时结果集提取下一条记录
            // 如果没有，返回false。循环就会停止
            while (resultSet.next()) {
                Integer eno = resultSet.getInt(1); // JDBC中字段索引从1开始，而非0
                String ename = resultSet.getString("ename");
                Float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5.关闭连接，释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
}
