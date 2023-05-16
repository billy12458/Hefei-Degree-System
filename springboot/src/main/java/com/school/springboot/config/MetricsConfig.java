package com.school.springboot.config;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
@SuppressWarnings("all")
@SuppressAjWarnings
public class MetricsConfig {

}
