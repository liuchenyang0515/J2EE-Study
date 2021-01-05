package com.me.jdbc.hrapp.command;

import com.me.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 员工调薪
 */
public class UpdateCommand implements Command {

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入员工编号：");
        int eno = in.nextInt();
        System.out.println("请输入员工新的薪资：");
        float salary = in.nextFloat();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "update employee set salary=? where eno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, salary);
            preparedStatement.setInt(2, eno);
            int cnt = preparedStatement.executeUpdate();
            if (cnt == 1) {
                System.out.println("员工新调整完毕");
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
