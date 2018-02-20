package csp.learning.java.java8;

import csp.learning.java.java8.Collectors.StringConcatenator;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CollectorsTest {

    @Test
    void testStringConcatenation() {
        Stream<String> strings = Stream.of("first", "second", "third");
        String result = strings.collect(new StringConcatenator());
        assertThat(result).isEqualTo("first" + "second" + "third");
    }

}
