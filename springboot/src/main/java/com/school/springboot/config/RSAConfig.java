package com.school.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "rsa.encrypt")
@SuppressWarnings("all")
public class RSAConfig {

    private String publicKey;

    private String privateKey;

    @Bean(value = "rsaBean")
    public RSA getRsa() {
        RSA rsa = new RSA(privateKey, publicKey);
        return rsa;
    }
    
}
