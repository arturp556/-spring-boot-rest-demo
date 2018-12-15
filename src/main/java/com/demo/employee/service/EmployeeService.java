package com.demo.employee.service;

import com.demo.employee.jpa.entity.Employee;
import com.demo.employee.jpa.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepo employeeRepo;

    @Transactional
    public EmployeeDto saveOrUpdate(EmployeeDto employeeDto) {
        Objects.requireNonNull(employeeDto);
        logger.info("Saving employee: " + employeeDto);
        if (employeeDto.getId() == null) {
            // Create new employee
            Employee employee = Employee.fromDto(employeeDto);
            logger.info("Saving employee enitity: " + employee);
            return employeeRepo.save(employee).toDto();
        }
        Optional<Employee> byId = employeeRepo.findById(employeeDto.getId());
        if (byId.isPresent()) {
            // Edit existing employee
            Employee employee = byId.get();
            employee.setDescription(employeeDto.getDescription());
            employee.setName(employeeDto.getName());
            employee.getAddress().setCity(employeeDto.getAddress().getCity());
            employee.getAddress().setPostalCode(employeeDto.getAddress().getPostalCode());
            employee.getAddress().setStreet(employeeDto.getAddress().getStreet());
            return employee.toDto();
        } else {
            // Create new employee wiht given id
            Employee employee = Employee.fromDto(employeeDto);
            logger.info("Saving employee enitity: " + employee);
            return employeeRepo.save(employee).toDto();
        }
    }

    public List<EmployeeDto> findAll() {
        return employeeRepo.findAll().stream()
                .map(Employee::toDto)
                .collect(toList());
    }

    public Optional<EmployeeDto> findById(long employeeId) {
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (!employee.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(employee.get().toDto());
        }
    }
}
