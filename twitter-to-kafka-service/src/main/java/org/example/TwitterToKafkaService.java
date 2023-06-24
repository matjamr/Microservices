package org.example;

import org.example.config.JavaKeywordsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.twitter.runners.StreamRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

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