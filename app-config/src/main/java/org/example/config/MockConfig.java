package org.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "mock-config")
public class MockConfig {
    private Integer minLength;
    private Integer maxLength;
    private Integer sleepTime;
    private List<String> keywords;
}
