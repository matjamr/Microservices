package org.example.twitter.runners.mock;

import org.example.config.MockConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.twitter.TwitterStatusKafkaListener;
import org.example.twitter.runners.StreamRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Slf4j
@Component
@ConditionalOnProperty(name = "mock-config.enable", havingValue = "true")
@RequiredArgsConstructor
public class MockTwitterKafkaStreamRunner implements StreamRunner {

    private final TwitterStatusKafkaListener twitterStatusKafkaListener;
    private final MockConfig mockConfig;
    private final Supplier<String> tweetGenerator;

    @Override
    public void start() {

        log.info("Starting twitter-mock with keywords:{} sleepTime: {}", mockConfig.getKeywords(), mockConfig.getSleepTime());

        startTwitterStream();
    }

    private void startTwitterStream() {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                while(true) {
                    String jsonTweet = tweetGenerator.get();
                    Status status = TwitterObjectFactory.createStatus(jsonTweet);

                    twitterStatusKafkaListener.onStatus(status);
                    Thread.sleep(mockConfig.getSleepTime());
                }
            } catch (TwitterException | InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
}
