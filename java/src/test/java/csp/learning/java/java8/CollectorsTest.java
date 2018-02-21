package csp.learning.java.java8;

import csp.learning.java.java8.Collectors.StringConcatenator;
import csp.learning.java.java8.model.Artist;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static csp.learning.java.java8.Collectors.frequency;
import static csp.learning.java.java8.Collectors.longestNameByCollecting;
import static csp.learning.java.java8.model.MusicTestData.*;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class CollectorsTest {

    @Test
    void testStringConcatenation() {
        Stream<String> strings = Stream.of("first", "second", "third");
        String result = strings.collect(new StringConcatenator());
        assertThat(result).isEqualTo("first" + "second" + "third");
    }

    @Test
    void testLongestNameByCollecting() {
        Stream<Artist> artists = Stream.of(johnMayer, fooFighters, theShins, theTallestManOnEarth);
        Optional<Artist> artist = longestNameByCollecting(artists.collect(toList()));
        assertThat(artist).contains(theTallestManOnEarth);
    }

    @Test
    void testFrequency() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> frequencyOfNames = frequency(names);
        assertThat(frequencyOfNames).contains(
                entry("John", 3L),
                entry("Paul", 2L),
                entry("George", 1L)
        );
    }

}
