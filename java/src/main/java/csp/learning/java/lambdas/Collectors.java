package csp.learning.java.lambdas;

import csp.learning.java.lambdas.model.Album;
import csp.learning.java.lambdas.model.Artist;
import csp.learning.java.lambdas.model.Track;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Collectors {

    public static Optional<Artist> findArtistWithMostMembers(Stream<Artist> artists) {
        Function<Artist, Long> numberOfMembers = artist -> artist.getMembers().count();
        return artists.collect(maxBy(comparing(numberOfMembers))); // alternative: artists.max(comparing(numberOfMembers));
    }

    public static double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                     .collect(averagingLong(album -> album.getTracks().count()));
    }

    public static Map<Boolean, List<Artist>> partitionByWhetherArtistIsGroup(Stream<Artist> artists) {
        return artists.collect(partitioningBy(Artist::isGroup));
    }

    public static String joinArtistNames(List<Artist> artists) {
        return artists.stream()
                      .map(Artist::getName)
                      .collect(joining(",", "[", "]"));
    }

    public static Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician, counting()));
    }

    public static Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician, mapping(Album::getName, toList())));
    }

    public static Map<Artist, Optional<String>> longestTrackNamePerArtist(Map<Artist, List<Track>> tracksByArtist) {
        return tracksByArtist.entrySet()
                             .stream()
                             .collect(toMap(
                                     Map.Entry::getKey,
                                     entry -> entry.getValue()
                                                   .stream()
                                                   .map(Track::getName)
                                                   .max(comparing(String::length))
                             ));
    }

    public static class StringConcatenator implements Collector<String, StringBuilder, String> {

        @Override
        public Supplier<StringBuilder> supplier() {
            return StringBuilder::new;
        }

        @Override
        public BiConsumer<StringBuilder, String> accumulator() {
            return StringBuilder::append;
        }

        @Override
        public BinaryOperator<StringBuilder> combiner() {
            return StringBuilder::append;
        }

        @Override
        public Function<StringBuilder, String> finisher() {
            return StringBuilder::toString;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }

    }

}
