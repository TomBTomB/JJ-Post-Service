package com.tombtomb.jjpostservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.metrics.export.datadog.EnableDatadogMetrics;

@SpringBootApplication
@EnableDatadogMetrics
public class JjPostServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JjPostServiceApplication.class, args);
    }

}
