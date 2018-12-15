package com.demo.employee;


import com.demo.app.Application;
import com.demo.employee.service.EmployeeDto;
import com.demo.employee.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class EmployeeServiceTests extends EmployeeTestsBase{

    @Autowired
    private EmployeeService employeeService;

    @Test
    @DisplayName("Find an employee by id")
    public void findEmployeesById() {
//        given:
        EmployeeDto empJames = EmployeeFactory.james();
        Long empJamesId = flushTestEmployees();

//        when:
        flushTestEmployees();
        Optional<EmployeeDto> findEmpByJamesIdResult = employeeService.findById(empJamesId);

//        then:
        assertTrue(findEmpByJamesIdResult.isPresent());
        EmployeeDto foundEmp = findEmpByJamesIdResult.get();

        assertEquals(empJames.getName(), foundEmp.getName());
        assertEquals(empJames.getDescription(), foundEmp.getDescription());
        assertEquals(empJames.getAddress().getCity(), foundEmp.getAddress().getCity());
        assertEquals(empJames.getAddress().getPostalCode(), foundEmp.getAddress().getPostalCode());
        assertEquals(empJames.getAddress().getStreet(), foundEmp.getAddress().getStreet());
    }

    @Test
    @DisplayName("Save new employee should returns emplyee with given id")
    public void saveEmployeeWithAddress() {
//        given:
        EmployeeDto empWilliam = EmployeeFactory.william();

//        when:
        flushTestEmployees();
        EmployeeDto empWilliamSaveResult = employeeService.saveOrUpdate(empWilliam);

        Optional<EmployeeDto> empWilliamFindByIdResult = Optional.empty();
        if (empWilliamSaveResult != null && empWilliamSaveResult.getId() != null) {
            empWilliamFindByIdResult = employeeService.findById(empWilliamSaveResult.getId());
        }

//        then:
        assertNotNull(empWilliamSaveResult);
        assertNotNull(empWilliamSaveResult.getId());
        assertEquals(empWilliam.getName(), empWilliamSaveResult.getName());
        assertEquals(empWilliam.getDescription(), empWilliamSaveResult.getDescription());
        assertEquals(empWilliam.getAddress().getCity(), empWilliamSaveResult.getAddress().getCity());
        assertEquals(empWilliam.getAddress().getPostalCode(), empWilliamSaveResult.getAddress().getPostalCode());
        assertEquals(empWilliam.getAddress().getStreet(), empWilliamSaveResult.getAddress().getStreet());

        assertTrue(empWilliamFindByIdResult.isPresent());
    }

    @Test
    @DisplayName("Update employee street and description")
    public void updateEmployee() {
//        given:
        EmployeeDto empWilliam = EmployeeFactory.william();
        String newDescription = "consultant";
        String newStreet = "Bowery";

//        when:
        Long empJamesId = flushTestEmployees();
        empWilliam.setId(empJamesId);
        empWilliam.setDescription(newDescription);
        empWilliam.getAddress().setStreet(newStreet);
        EmployeeDto empWilliamUpdateResult = employeeService.saveOrUpdate(empWilliam);

//        then:
        assertNotNull(empWilliam.getId());
        assertEquals(empWilliam.getName(), empWilliamUpdateResult.getName());
        assertEquals(empWilliam.getDescription(), newDescription);
        assertEquals(empWilliam.getAddress().getCity(), empWilliamUpdateResult.getAddress().getCity());
        assertEquals(empWilliam.getAddress().getPostalCode(), empWilliamUpdateResult.getAddress().getPostalCode());
        assertEquals(empWilliam.getAddress().getStreet(), empWilliamUpdateResult.getAddress().getStreet());
    }
}
