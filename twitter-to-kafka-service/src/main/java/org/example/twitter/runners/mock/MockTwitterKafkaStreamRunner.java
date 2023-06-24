package org.example.twitter.runners.mock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.configuration.MockConfig;
import org.example.twitter.TwitterStatusKafkaListener;
import org.example.twitter.runners.StreamRunner;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class MockTwitterKafkaStreamRunner implements StreamRunner {

    private final TwitterStatusKafkaListener twitterStatusKafkaListener;
    private final MockConfig mockConfig;
    private final Function<List<String>, String> tweetGenerator;

    @Override
    public void start() {
        final List<String> keywordsConfig = mockConfig.getKeywords();
        final Integer minLength = mockConfig.getMinLength();
        final Integer maxLength = mockConfig.getMaxLength();
        final double sleepTime = mockConfig.getSleepTime();

        log.info("Starting twitter-mock with keywords:{} sleepTime: {}", keywordsConfig, sleepTime);

        startTwitterStream(keywordsConfig, minLength, maxLength, sleepTime);
    }

    private void startTwitterStream(List<String> keywordsConfig, Integer minLength, Integer maxLength, double sleepTime) {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                while(true) {
                    String jsonTweet = tweetGenerator.apply(keywordsConfig);
                    Status status = TwitterObjectFactory.createStatus(jsonTweet);

                    twitterStatusKafkaListener.onStatus(status);
                    Thread.sleep((long) sleepTime);
                }
            } catch (TwitterException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
