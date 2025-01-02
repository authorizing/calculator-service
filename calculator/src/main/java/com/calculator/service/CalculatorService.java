package com.calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalculatorService {
    
    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        log.info("Performing sum: {} + {}", a, b);
        return a.add(b);
    }
    
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        log.info("Performing subtraction: {} - {}", a, b);
        return a.subtract(b);
    }
    
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        log.info("Performing multiplication: {} * {}", a, b);
        return a.multiply(b);
    }
    
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        log.info("Performing division: {} / {}", a, b);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a.divide(b, 10, RoundingMode.HALF_UP);
    }
} 