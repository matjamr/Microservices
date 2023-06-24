package org.example.twitter.runners.mock;

import org.example.config.MockConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetGeneratorTest {

    @InjectMocks
    private TweetGenerator tweetGenerator;

    @Mock
    private MockConfig mockConfig;

    @Test
    void sampleTestForTweetGeneration() {
        // given

        // when
        when(mockConfig.getKeywords()).thenReturn(List.of("anon1", "anon2"));

        var result = tweetGenerator.get();

        // then
        assertNotNull(result);
    }
}