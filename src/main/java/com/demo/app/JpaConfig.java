package com.demo.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.demo.employee.jpa"}, considerNestedRepositories = true)
@EntityScan(basePackages = {"com.demo.employee.jpa.entity"})
public class JpaConfig {

}