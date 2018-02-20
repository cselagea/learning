package csp.learning.java.java8;

import csp.learning.java.java8.model.Album;
import csp.learning.java.java8.model.Artist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static csp.learning.java.java8.StreamApiExamples.*;
import static csp.learning.java.java8.model.MusicTestData.*;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class StreamApiExamplesTest {

    @ParameterizedTest(name = "[{index}] sum = {1}")
    @MethodSource("numbers")
    void testAddUp(Stream<Integer> numbers, int expected) {
        int sum = addUp(numbers);
        assertThat(sum).isEqualTo(expected);
    }

    private static Stream<Arguments> numbers() {
        return Stream.of(
                Arguments.of(
                        Stream.empty(),
                        0
                ),
                Arguments.of(
                        Stream.of(1, 2, 3),
                        6
                ),
                Arguments.of(
                        Stream.of(2, -10, 3),
                        -5
                )
        );
    }

    @Test
    void testGetNamesAndOrigins() {
        List<Artist> artists = Stream.of(johnMayer, fooFighters, theShins, theTallestManOnEarth).collect(toList());
        List<String> namesAndOrigins = getNamesAndOrigins(artists);

        assertThat(namesAndOrigins).containsExactlyInAnyOrder(
                johnMayer.getName(),
                johnMayer.getOrigin(),
                fooFighters.getName(),
                fooFighters.getOrigin(),
                theShins.getName(),
                theShins.getOrigin(),
                theTallestManOnEarth.getName(),
                theTallestManOnEarth.getOrigin()
        );
    }

    @Test
    void excludeAlbumsWithMoreThanThreeTracks() {
        List<Album> albums = Stream.of(oneTrackAlbum, fourTrackAlbum, zeroTrackAlbum).collect(toList());
        List<Album> remainingAlbums = getAlbumsWithAtMostThreeTracks(albums);
        assertThat(remainingAlbums).containsExactlyInAnyOrder(oneTrackAlbum, zeroTrackAlbum);
    }

    @Test
    void testGetTotalMembers() {
        List<Artist> artists = Stream.of(johnMayer, fooFighters, theShins, theTallestManOnEarth).collect(toList());
        long memberCount = getTotalMembers(artists);
        assertThat(memberCount).isEqualTo(14);
    }

    @ParameterizedTest(name = "[{index}] \"{0}\" has {1} lowercase letter(s)")
    @MethodSource("strings")
    void countingLowercaseLetters(String string, int expectedCount) {
        int numberOfLowercaseLetters = countLowercaseLetters(string);
        assertThat(numberOfLowercaseLetters).isEqualTo(expectedCount);
    }

    private static Stream<Arguments> strings() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("   ", 0),
                Arguments.of("Hello, world", 9),
                Arguments.of("HELLO, WORLD", 0),
                Arguments.of("h3llo, W@RLd", 5)
        );
    }

    @Test
    void findingStringWithMostLowercaseLetters() {
        List<String> strings = Stream.of("First", "SECOND", "THiRD").collect(toList());
        Optional<String> stringWithMostLowercaseLetters = mostLowercaseLetters(strings);
        assertThat(stringWithMostLowercaseLetters).contains("First");
    }

    @Test
    void findingMostLowercaseStringInEmptyListReturnsEmptyOptional() {
        Optional<String> stringWithMostLowercaseLetters = mostLowercaseLetters(emptyList());
        assertThat(stringWithMostLowercaseLetters).isEmpty();
    }

    @Test
    void testMapFunction() {
        Stream<Artist> artists = Stream.of(johnMayer, fooFighters, theShins, theTallestManOnEarth);
        Stream<String> artistNames = map(artists, Artist::getName);
        assertThat(artistNames).contains(
                johnMayer.getName(),
                fooFighters.getName(),
                theShins.getName(),
                theTallestManOnEarth.getName()
        );
    }

    @Test
    void applyingMapFunctionToEmptyStreamReturnsEmptyStream() {
        Stream<String> resultStream = map(Stream.empty(), Artist::getName);
        assertThat(resultStream).isEmpty();
    }

    @Test
    void testFilterFunction() {
        Stream<Artist> artists = Stream.of(johnMayer, fooFighters, theShins, theTallestManOnEarth);
        Stream<Artist> groups = filter(artists, Artist::isGroup);
        assertThat(groups).contains(
                fooFighters,
                theShins
        );
    }

    @Test
    void applyingFilterFunctionToEmptyStreamReturnsEmptyStream() {
        Stream<Artist> resultStream = filter(Stream.empty(), Artist::isGroup);
        assertThat(resultStream).isEmpty();
    }

}
