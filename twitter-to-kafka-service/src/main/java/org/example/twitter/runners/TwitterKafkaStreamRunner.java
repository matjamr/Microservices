package org.example.twitter.runners;

import org.example.config.JavaKeywordsConfig;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.twitter.TwitterStatusKafkaListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@ConditionalOnProperty(name = "mock-config.enable", havingValue = "false", matchIfMissing = true)
@RequiredArgsConstructor
public class TwitterKafkaStreamRunner implements StreamRunner {

    private TwitterStream twitterStream;
    private final TwitterStatusKafkaListener twitterStatusKafkaListener;
    private final JavaKeywordsConfig javaKeywordsConfig;

    @Override
    public void start() {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterStatusKafkaListener);

        String[] keywords = javaKeywordsConfig.getKeywords().toArray(new String[0]);

        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
    }

    @PreDestroy
    private void destroy() {
        if (nonNull(twitterStream)) {
            log.info("Closing twitter stream connection....");
            twitterStream.shutdown();
        }
    }
}
