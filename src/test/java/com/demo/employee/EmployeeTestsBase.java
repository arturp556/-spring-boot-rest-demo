package com.demo.employee;

import com.demo.SpringBootTestBase;
import com.demo.employee.jpa.EmployeeRepo;
import com.demo.employee.jpa.entity.Employee;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public abstract class EmployeeTestsBase extends SpringBootTestBase {

    @Autowired
    protected EmployeeRepo employeeRepo;

    @Before
    public void cleanDB() throws SQLException {
        employeeRepo.deleteAll();
    }

    protected Long flushTestEmployees() {
        Employee employee = Employee.fromDto(EmployeeFactory.james());
        return employeeRepo.save(employee).getId();
        /*
        EmployeeDto empJames = EmployeeFactory.james();
        jdbcTemplate.update("INSERT INTO employee(id, description, name) VALUES(?, ?, ?)",1L, empJames.getDescription(), empJames.getName());
        jdbcTemplate.update("INSERT INTO employee_address(city, postal_code, street, employee_id) values (?, ?, ?, ?)",
                empJames.getAddress().getCity(), empJames.getAddress().getPostalCode(), empJames.getAddress().getStreet(), 1L);
        */
    }
}
