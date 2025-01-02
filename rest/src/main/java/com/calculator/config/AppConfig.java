package com.calculator.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.calculator.service", "com.calculator.controller"})
public class AppConfig {
} 