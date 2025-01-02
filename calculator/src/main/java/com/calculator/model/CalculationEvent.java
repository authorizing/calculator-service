package com.calculator.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculationEvent {
    private String operation;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal result;
    private String requestId;
} 