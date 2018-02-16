package csp.learning.java.lambdas;

import csp.learning.java.lambdas.model.Album;
import csp.learning.java.lambdas.model.Artist;
import csp.learning.java.lambdas.model.Track;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static csp.learning.java.lambdas.Lambdas.*;
import static java.util.Calendar.AUGUST;
import static java.util.Calendar.DECEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LambdasTest {

    private final Artist johnMayer = Artist.named("John Mayer").from("Bridgeport");
    private final Artist fooFighters = Artist.named("Foo Fighters").from("Seattle");
    private final Artist theShins = Artist.named("The Shins").from("Albuquerque");
    private final Artist theTallestManOnEarth = Artist.named("The Tallest Man on Earth").from("Dalarna");

    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @MethodSource("dates")
    void testDateFormatting(Date date, String expectedFormat) {
        String formattedDate = threadLocalDateFormat.get().format(date);
        assertThat(formattedDate).isEqualTo(expectedFormat);
    }

    private static Stream<Arguments> dates() {
        return Stream.of(
                Arguments.of(
                        getDate(8, AUGUST, 2018),
                        "08-Aug-2018"
                ),
                Arguments.of(
                        getDate(28, DECEMBER, 1878),
                        "28-Dec-1878"
                )
        );
    }

    private static Date getDate(int dayOfMonth, int month, int year) {
        return new GregorianCalendar(year, month, dayOfMonth).getTime();
    }

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
                        Stream.of(-2, 10, 3),
                        11
                )
        );
    }

    @Test
    void testOrganizationOfArtistsByOrigin() {
        Stream<Artist> artists = Stream.of(johnMayer, fooFighters, theShins, theTallestManOnEarth);
        Map<String, String> artistNameToOrigin = byOrigin(artists);

        assertThat(artistNameToOrigin).contains(
                entry("John Mayer", "Bridgeport"),
                entry("Foo Fighters", "Seattle"),
                entry("The Shins", "Albuquerque"),
                entry("The Tallest Man on Earth", "Dalarna")
        );
    }

    @Test
    void excludeAlbumsWithMoreThanThreeTracks() {
        Album wastingLight = Album.named("Wasting Light")
                                  .by(fooFighters)
                                  .with(Track.named("These Days"));

        Album continuum = Album.named("Continuum")
                               .by(johnMayer)
                               .with(Track.named("Belief"))
                               .with(Track.named("Gravity"))
                               .with(Track.named("Vultures"))
                               .with(Track.named("In Repair"));

        Album chutesTooNarrow = Album.named("Chutes Too Narrow");

        Stream<Album> albums = Stream.of(wastingLight, continuum, chutesTooNarrow);
        List<Album> filtered = filterIfContainsAtMostThreeTracks(albums);
        assertThat(filtered).contains(wastingLight, chutesTooNarrow);
    }

}
