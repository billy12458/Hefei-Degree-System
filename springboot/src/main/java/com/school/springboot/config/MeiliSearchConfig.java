package com.school.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties(prefix = "com.meili")
@SuppressWarnings("all")
@Slf4j
@Data
public class MeiliSearchConfig {

    private String hostUrl;

    private String apiKey;

    private String mainIndex;

    private String secondaryIndex;
    
    @Bean(value = "userIndex")
    public Index getUserIndex() throws MeilisearchException {
        Config config = new Config(hostUrl, apiKey);
        Client client = new Client(config);
        Index userIndex = client.index(mainIndex);
        return userIndex;
    }

}
