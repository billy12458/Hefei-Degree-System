package com.school.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import cn.dev33.satoken.SaManager;
import io.micrometer.core.instrument.MeterRegistry;

//确定了1.34.0的starter兼容springboot3
@EnableConfigurationProperties
@EnableAutoConfiguration
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAsync(mode = AdviceMode.PROXY)
@MapperScan(basePackages = "com.school.springboot.mapper")
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
		System.err.println(SaManager.getConfig());
	}

	@Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "degree-system");
    }

}
