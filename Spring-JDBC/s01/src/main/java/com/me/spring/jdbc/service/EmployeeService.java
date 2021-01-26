package com.me.spring.jdbc.service;

import com.me.spring.jdbc.dao.EmployeeDao;
import com.me.spring.jdbc.entity.Employee;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;

public class EmployeeService {
    private EmployeeDao employeeDao;
    private DataSourceTransactionManager dataSourceTransactionManager;

    public void batchImport() {
        // 定义了事务默认的标准配置
        TransactionDefinition definition = new DefaultTransactionDefinition();
        // 开始一个事务，返回事务状态，事务状态说明当前事务的执行阶段
        TransactionStatus status = dataSourceTransactionManager.getTransaction(definition);

        try {
            for (int i = 1; i <= 10; ++i) {
//                if (i == 3) {
//                    throw new RuntimeException("意料之外的异常");
//                }
                Employee employee = new Employee();
                employee.setEno(8000 + i);
                employee.setEname("员工" + i);
                employee.setSalary(4000f);
                employee.setDname("市场部");
                employee.setHiredate(new Date(new java.util.Date().getTime()));
                employeeDao.insert(employee);
            }
            // 提交事务
            dataSourceTransactionManager.commit(status);
        } catch (RuntimeException e) {
            // 回滚事务
            dataSourceTransactionManager.rollback(status);
            throw e;
        }
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

    public DataSourceTransactionManager getDataSourceTransactionManager() {
        return dataSourceTransactionManager;
    }
}