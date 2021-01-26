package com.me.spring.jdbc.dao;

import com.me.spring.jdbc.entity.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    // spring-jdbc查询的3种核心方法
    public Employee findById(Integer eno) {
        String sql = "select * from employee where eno = ?";
        // 查询单条数据
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{eno}, new BeanPropertyRowMapper<>(Employee.class));
        return employee;
    }

    public List<Employee> findByDname(String dname) {
        String sql = "select * from employee where dname = ?";
        // new BeanPropertyRowMapper<>(Employee.class) 是属性和字段之前的映射
        // 查询复合数据
        List<Employee> list = jdbcTemplate.query(sql, new Object[]{dname}, new BeanPropertyRowMapper<>(Employee.class));
        return list;
    }

    public List<Map<String, Object>> findMapByDname(String dname) {
        String sql = "select eno as empno, salary as s from employee where dname = ?";
        // Map<字段名，字段值>，在这里就是{empno, s}，没有对应实体类，但是查询结果封装到了Map集合作为键值对
        // 将查询结果作为Map进行封装，这种要掌握
        // 假如无法进行实体类的映射，可以用这种方法，在工作中非常常见
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new Object[]{dname});
        return maps;
    }

    // 数据写入-新增
    public void insert(Employee employee) {
        String sql = "insert into employee (eno, ename, salary, dname, hiredate) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                employee.getEno(), employee.getEname(), employee.getSalary(), employee.getDname(), employee.getHiredate()
        });
    }

    // 数据写入-更新
    public int update(Employee employee) {
        String sql = "UPDATE employee SET ename = ?, salary = ?, dname = ?, hiredate = ? WHERE eno = ?";
        int count = jdbcTemplate.update(sql, new Object[]{employee.getEname(), employee.getSalary(), employee.getDname(), employee.getHiredate(), employee.getEno()});
        return count;
    }

    // 数据写入-删除
    public int delete(Integer eno) {
        String sql = "delete from employee where eno = ?";
        return jdbcTemplate.update(sql, new Object[]{eno});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
