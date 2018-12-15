package com.demo.api.rest;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.util.Objects;

public class NumbersDto {
    private Number number1;
    private Number number2;

    public NumbersDto() {}

    public NumbersDto(BigDecimal number1, BigDecimal number2) {
        this.number1 = Objects.requireNonNull(number1);
        this.number2 = Objects.requireNonNull(number2);
    }

    public Number getNumber1() {
        return number1;
    }

    public void setNumber1(BigDecimal number1) {
        this.number1 = number1;
    }

    public Number getNumber2() {
        return number2;
    }

    public void setNumber2(BigDecimal number2) {
        this.number2 = number2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumbersDto)) return false;
        NumbersDto that = (NumbersDto) o;
        return Objects.equals(number1, that.number1) &&
                Objects.equals(number2, that.number2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number1, number2);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("number1", number1)
                .add("number2", number2)
                .toString();
    }
}
