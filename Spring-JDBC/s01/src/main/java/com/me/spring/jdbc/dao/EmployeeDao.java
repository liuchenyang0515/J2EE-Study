package com.me.spring.jdbc.dao;

import com.me.spring.jdbc.entity.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public Employee findById(Integer eno) {
        String sql = "select * from employee where eno = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{eno}, new BeanPropertyRowMapper<>(Employee.class));
        return employee;
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
