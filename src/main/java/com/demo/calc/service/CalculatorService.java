package com.demo.calc.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class CalculatorService {

    public BigDecimal sum(Number n1, Number n2) {
        Objects.requireNonNull(n1);
        Objects.requireNonNull(n2);
        return new BigDecimal(n1.toString()).add(new BigDecimal(n2.toString()));
    }
}
