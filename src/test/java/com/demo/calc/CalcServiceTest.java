package com.demo.calc;

import com.demo.SpringBootTestBase;
import com.demo.calc.service.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;




import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;

public class CalcServiceTest extends SpringBootTestBase {

    @Autowired
    private CalculatorService calculatorService;

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @MethodSource("sumProvider")
    void sum(Number a, Number b, Number expectedSum) {
        BigDecimal sum = calculatorService.sum(a, b);
        assertEquals(0, new BigDecimal(expectedSum.toString()).compareTo(sum));
    }

    private static Stream<Arguments> sumProvider() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 3, 5),
                Arguments.of(-2L, BigInteger.TEN, 8)
        );
    }
}
