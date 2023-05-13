package org.example.twitter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.StatusListener;

@Slf4j
@Component
public class TwitterStatusKafkaListener extends StatusAdapter {

    @Override
    public void onStatus(Status status) {
        log.info(String.valueOf(status));
    }
}
