package com.calculator.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.calculator.model.CalculationEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalculationEventListener {

    @KafkaListener(topics = "calculation-events", groupId = "calculator-group")
    public void listen(CalculationEvent event) {
        log.info("Received calculation event: {} {} {} = {} (Request ID: {})",
            event.getA(), 
            event.getOperation(), 
            event.getB(), 
            event.getResult(),
            event.getRequestId()
        );
    }
} 