package com.demo.employee;

import com.demo.employee.service.EmployeeDto;

class EmployeeFactory {
    private EmployeeFactory() {}

    public static EmployeeDto james() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("James");
        employeeDto.setDescription("salesman");
        EmployeeDto.Address addressDto = new EmployeeDto.Address();
        addressDto.setCity("New York");
        addressDto.setPostalCode("01-234");
        addressDto.setStreet("Broadway");
        employeeDto.setAddress(addressDto);
        return employeeDto;
    }

    public static EmployeeDto william() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("William");
        employeeDto.setDescription("mangaer");
        EmployeeDto.Address addressDto = new EmployeeDto.Address();
        addressDto.setCity("Chicago");
        addressDto.setPostalCode("56-789");
        addressDto.setStreet("Michigan Avenue");
        employeeDto.setAddress(addressDto);
        return employeeDto;
    }

    public static EmployeeDto frank() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Frank");
        employeeDto.setDescription("mangaer");
        EmployeeDto.Address addressDto = new EmployeeDto.Address();
        addressDto.setCity("Los Angeles");
        addressDto.setPostalCode("53-306");
        addressDto.setStreet("Mulholland Drive");
        employeeDto.setAddress(addressDto);
        return employeeDto;
    }
}
