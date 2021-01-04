package com.me.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSample {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 如果出现Connection refused很可能是端口号错了，如果本地没问题，部署到服务器却连不上，可能是服务器防火墙把3306屏蔽了
            String url = "jdbc:mysql://localhost:3306/me?userSSL=false&useUnicode=true&characterEncoding=UTF-8" +
                    "&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
            Connection conn = DriverManager.getConnection(url, "root", "1q1q1q1q");
            System.out.println(conn); // 具体jdbc实现类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
