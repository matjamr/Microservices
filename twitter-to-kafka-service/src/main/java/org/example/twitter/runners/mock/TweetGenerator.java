package org.example.twitter.runners.mock;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class TweetGenerator implements Function<List<String>, String> {

    private final static List<String> SAMPLE_WORDS = List.of(
            "SAMPLE",
            "cos",
            "AAAAA",
            "test",
            "asdadczs",
            "wkakawdwae",
            "cos",
            "test"
    );

    private final static String JSON_TEMPLATE = "{" +
            "  \"created_at\": \"{0}\"," +
            "  \"id\": \"{1}\"," +
            "  \"text\": \"{2}\"," +
            "  \"user\": {\n" +
            "    \"id\": \"{3}\"" +
            "  }" +
            "}";


    @Override
    public String apply(List<String> strings) {
        return JSON_TEMPLATE.replace;
    }
}
