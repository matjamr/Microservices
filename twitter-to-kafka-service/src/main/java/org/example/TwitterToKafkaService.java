package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.configuration.JavaKeywordsConfig;
import org.example.twitter.runners.StreamRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class TwitterToKafkaService implements CommandLineRunner {

    private final JavaKeywordsConfig javaKeywordsConfig;
    private final StreamRunner twitterStreamRunner;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaService.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(javaKeywordsConfig.getKeywords().toString());
        twitterStreamRunner.start();
    }
}