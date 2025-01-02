package com.calculator.controller;

import com.calculator.service.CalculatorService;
import com.calculator.model.CalculationEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/calculator")
@RequiredArgsConstructor
public class CalculatorController {
    
    private static final Logger log = LoggerFactory.getLogger(CalculatorController.class);
    private final CalculatorService calculatorService;
    private final KafkaTemplate<String, CalculationEvent> kafkaTemplate;
    private static final String TOPIC = "calculation-events";

    @GetMapping("/sum")
    public ResponseEntity<CalculationResponse> sum(
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b) {
        String requestId = UUID.randomUUID().toString();
        log.info("Request {} - Sum operation: {} + {}", requestId, a, b);
        
        BigDecimal result = calculatorService.sum(a, b);
        
        // Send event to Kafka
        CalculationEvent event = new CalculationEvent("SUM", a, b, result, requestId);
        kafkaTemplate.send(TOPIC, requestId, event);
        
        return ResponseEntity.ok()
                .header("X-Request-ID", requestId)
                .body(new CalculationResponse(result));
    }

    @GetMapping("/subtract")
    public ResponseEntity<CalculationResponse> subtract(
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b) {
        String requestId = UUID.randomUUID().toString();
        log.info("Request {} - Subtract operation: {} - {}", requestId, a, b);
        
        BigDecimal result = calculatorService.subtract(a, b);
        return ResponseEntity.ok()
                .header("X-Request-ID", requestId)
                .body(new CalculationResponse(result));
    }

    @GetMapping("/multiply")
    public ResponseEntity<CalculationResponse> multiply(
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b) {
        String requestId = UUID.randomUUID().toString();
        log.info("Request {} - Multiply operation: {} * {}", requestId, a, b);
        
        BigDecimal result = calculatorService.multiply(a, b);
        return ResponseEntity.ok()
                .header("X-Request-ID", requestId)
                .body(new CalculationResponse(result));
    }

    @GetMapping("/divide")
    public ResponseEntity<CalculationResponse> divide(
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b) {
        String requestId = UUID.randomUUID().toString();
        log.info("Request {} - Divide operation: {} / {}", requestId, a, b);
        
        try {
            BigDecimal result = calculatorService.divide(a, b);
            return ResponseEntity.ok()
                    .header("X-Request-ID", requestId)
                    .body(new CalculationResponse(result));
        } catch (ArithmeticException e) {
            log.error("Request {} - Division by zero error", requestId);
            return ResponseEntity.badRequest()
                    .header("X-Request-ID", requestId)
                    .build();
        }
    }
}

record CalculationResponse(BigDecimal result) {}