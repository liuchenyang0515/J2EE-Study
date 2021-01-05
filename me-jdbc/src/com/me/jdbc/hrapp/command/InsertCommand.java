package com.me.jdbc.hrapp.command;

import com.me.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertCommand implements Command {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入员工编号：");
        int eno = in.nextInt();
        System.out.println("请输入员工姓名：");
        String ename = in.next();
        System.out.println("请输入员工薪资：");
        float salary = in.nextFloat();
        System.out.println("请输入隶属部门：");
        String dname = in.next();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "insert into employee(eno, ename, salary, dname) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eno);
            preparedStatement.setString(2, ename);
            preparedStatement.setFloat(3, salary);
            preparedStatement.setString(4, dname);
            int cnt = preparedStatement.executeUpdate();// 只要是写操作(新增修改删除)，就是executeUpdate
            System.out.println("cnt: " + cnt);
            System.out.println(ename + "员工入职手续已办理");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeConnection(null, preparedStatement, connection);
        }
    }
}
