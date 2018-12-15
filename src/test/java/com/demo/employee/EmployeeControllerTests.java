package com.demo.employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import com.demo.TestUtil;
import com.demo.api.rest.NumbersDto;
import com.demo.app.Application;
import com.demo.employee.jpa.EmployeeRepo;
import com.demo.employee.jpa.entity.Employee;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.sql.SQLException;

@AutoConfigureMockMvc
public class EmployeeControllerTests extends EmployeeTestsBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("PUT with not existing id should create a new employee")
    public void putNewEmployee() throws Exception {
        Object updated = EmployeeFactory.william();
        mockMvc.perform(put("/employees/{id}", 41113L)
                    .contentType(APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(updated)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT with existing id should edit an existing employee")
    public void putExistingEmployee() throws Exception {
        Long empJamesId = flushTestEmployees();
        Object updated = EmployeeFactory.william();
        mockMvc.perform(put("/employees/{id}", empJamesId)
                .contentType(APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updated)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET with existing id should return the requested employee")
    public void getExistingEmployee() throws Exception {
        Long empJamesId = flushTestEmployees();
        this.mockMvc.perform(get("/employees/{id}", empJamesId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET with not existing id should return status 404")
    public void getNotExistingEmployee() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/employees/{id}", 51161L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST calculator sum 2 numbers")
    public void postCalcSum() throws Exception {
        NumbersDto numbers = new NumbersDto();
        numbers.setNumber1(new BigDecimal("2.0"));
        numbers.setNumber2(new BigDecimal("1.0"));
        mockMvc.perform(post("/calc/sum")
                .contentType(APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(numbers)))
                .andDo(print())
                //.andExpect(jsonPath("$.body").value("3.0"))
                .andExpect(status().isOk());
    }
}
