package csp.learning.java.java8.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static csp.learning.java.java8.model.MusicTestData.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ArtistsTest {

    private static final List<Artist> artistList = asList(johnMayer, fooFighters, theShins, theTallestManOnEarth);
    private static final Artists artists = new Artists(artistList);
    private static final String UNKNOWN = "unknown";

    @ParameterizedTest(name = "[{index}] index = {0}")
    @MethodSource("indices")
    void getArtistWithInBoundsIndex(int index) {
        Optional<Artist> artist = artists.getArtist(index);
        assertThat(artist).isNotEmpty();
    }

    @Test
    void getArtistWithOutOfBoundsIndexReturnsEmptyOptional() {
        Optional<Artist> artist = artists.getArtist(-1);
        assertThat(artist).isEmpty();
    }

    @ParameterizedTest(name = "[{index}] index = {0}")
    @MethodSource("indices")
    void getArtistNameWithInBoundsIndex(int index) {
        String artistName = artists.getArtistName(index);
        assertThat(artistName).isNotEqualTo(UNKNOWN);
    }

    @Test
    void getArtistNameWithOutOfBoundsIndexReturnsUnknown() {
        String artistName = artists.getArtistName(-1);
        assertThat(artistName).isEqualTo(UNKNOWN);
    }

    private static IntStream indices() {
        return IntStream.range(0, artistList.size());
    }

}
