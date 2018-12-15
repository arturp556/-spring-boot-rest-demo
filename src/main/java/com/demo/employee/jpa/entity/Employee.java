package com.demo.employee.jpa.entity;

import com.demo.employee.service.EmployeeDto;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMPLOYEE_ID")
    private Long id;

    private String description;
    private String name;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private EmployeeAddress address;

    // no-argument constructor required by hibernate
    Employee() {}

    public static Employee fromDto(EmployeeDto dto) {
        Objects.requireNonNull(dto);
        Employee employee = new Employee();
        employee.id = dto.getId();
        employee.description = dto.getDescription();
        employee.name = dto.getName();

        if (dto.getAddress() != null) {
            employee.address = EmployeeAddress.fromDto(employee, dto.getAddress());
        }
        return employee;
    }

    public EmployeeDto toDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(getId());
        employeeDto.setName(getName());
        employeeDto.setDescription(getDescription());
        if (getAddress() != null) {
            employeeDto.setAddress(getAddress().toDto());
        }
        return employeeDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeAddress getAddress() {
        return address;
    }

    public void setAddress(EmployeeAddress address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .add("name", name)
                .add("address", address)
                .toString();
    }

}
