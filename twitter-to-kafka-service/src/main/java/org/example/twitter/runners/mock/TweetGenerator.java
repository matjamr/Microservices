package org.example.twitter.runners.mock;

import org.example.config.MockConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class TweetGenerator implements Supplier<String> {

    private final MockConfig mockConfig;

    private static final String JSON_TEMPLATE = "{" +
            "  \"created_at\": \"{0}\"," +
            "  \"id\": \"{1}\"," +
            "  \"text\": \"{2}\"," +
            "  \"user\": {\n" +
            "    \"id\": \"{3}\"" +
            "  }" +
            "}";

    private static final String TWITTER_STATUS_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    @Override
    public String get() {
        return JSON_TEMPLATE.replace("{0}", ZonedDateTime.now().format(DateTimeFormatter.ofPattern(TWITTER_STATUS_DATE_FORMAT, Locale.ENGLISH)))
                .replace("{1}", String.valueOf(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE / 3)))
                .replace("{2}", mockConfig.getKeywords().get(ThreadLocalRandom.current().nextInt(0, mockConfig.getKeywords().size())))
                .replace("{3}", String.valueOf(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE / 3)));
    }
}
