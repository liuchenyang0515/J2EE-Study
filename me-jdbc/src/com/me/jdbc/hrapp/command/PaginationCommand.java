package com.me.jdbc.hrapp.command;

import com.me.jdbc.common.DbUtils;
import com.me.jdbc.hrapp.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 分页查询员工数据
 */
public class PaginationCommand implements Command {

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入页号：");
        int page = in.nextInt();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Employee> list = new ArrayList<>();
        try {
            connection = DbUtils.getConnection();
            // 注意，limit是mysql数据库独有的方言，其他的比如Oracle是没有这个关键字的
            String sql = "select * from employee limit ?, 10"; // 从第?行开始后面的的10条数据
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (page - 1) * 10);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer eno = resultSet.getInt("eno");
                String ename = resultSet.getString("ename");
                Float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                Employee emp = new Employee();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setDname(dname);
                list.add(emp); // 放到集合，即使结果集关闭，数据仍然存在，且集合方便java进行后续处理
            }
            System.out.println(list.size());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }
}
