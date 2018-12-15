package com.demo.employee.jpa.entity;

import com.demo.employee.service.EmployeeDto;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class EmployeeAddress {

    @Id
    private Long id;

    private String city;
    private String street;
    private String postalCode;

    @OneToOne
    @MapsId
    private Employee employee;

    // no-argument constructor required by hibernate
    EmployeeAddress() {}

    public static EmployeeAddress fromDto(Employee employee, EmployeeDto.Address dto) {
        Objects.requireNonNull(employee);
        Objects.requireNonNull(dto);
        EmployeeAddress address = new EmployeeAddress();
        address.id = dto.getId();
        address.city = dto.getCity();
        address.street = dto.getStreet();
        address.postalCode = dto.getPostalCode();
        address.employee = employee;
        return address;
    }

    public EmployeeDto.Address toDto() {
        EmployeeDto.Address addressDto = new EmployeeDto.Address();
        addressDto.setId(getId());
        addressDto.setCity(getCity());
        addressDto.setStreet(getStreet());
        addressDto.setPostalCode(getPostalCode());
        return addressDto;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("city", city)
                .add("street", street)
                .add("postalCode", postalCode)
                .add("employee.id", employee.getId())
                .toString();
    }
}
