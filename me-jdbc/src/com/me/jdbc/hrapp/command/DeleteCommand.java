package com.me.jdbc.hrapp.command;

import com.me.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 删除员工数据
 */
public class DeleteCommand implements Command {

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入员工编号：");
        int eno = in.nextInt();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "delete from employee where eno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eno);
            int cnt = preparedStatement.executeUpdate();
            if (cnt == 1) {
                System.out.println("员工离职手续已完成");
            } else {
                System.out.println("未找到" + eno + "编号员工数据");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeConnection(null, preparedStatement, connection);
        }
    }
}
