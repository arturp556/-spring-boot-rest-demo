package com.demo.api.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.demo.calc.service.CalculatorService;
import com.demo.employee.service.EmployeeDto;
import com.demo.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/employees")
    List<EmployeeDto> allEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    EmployeeDto getEmployee(@PathVariable Long id) {
        Optional<EmployeeDto> emp = employeeService.findById(id);
        return emp.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/employees/{id}")
    EmployeeDto putEmployee(@PathVariable Long id, @RequestBody EmployeeDto employee) {
        employee.setId(id);
        EmployeeDto saveEmpResult = employeeService.saveOrUpdate(employee);
        return saveEmpResult;
    }

    @PostMapping("/calc/sum")
    BigDecimal sumNumbers(@RequestBody NumbersDto numbersDto) {
        return calculatorService.sum(numbersDto.getNumber1(), numbersDto.getNumber2());
    }
}
