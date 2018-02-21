package csp.learning.java.java8;

import csp.learning.java.java8.model.Album;
import csp.learning.java.java8.model.Artist;
import csp.learning.java.java8.model.Track;

import java.util.*;
import java.util.function.*;
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

    public static Optional<Artist> longestNameByCollecting(List<Artist> artists) {
        Function<Artist, Integer> nameLength = artist -> artist.getName().length();

        return artists.stream()
                      .filter(Objects::nonNull)
                      .max(comparing(nameLength));
    }

    public static Optional<Artist> longestNamebyReduce(List<Artist> artists) {
        return artists.stream()
                      .filter(Objects::nonNull)
                      .reduce((artist1, artist2) -> artist1.getName().length() > artist2.getName().length() ? artist1 : artist2);
    }

    public static Optional<Artist> longestNameByReduceAlternative(List<Artist> artists) {
        Comparator<Artist> nameLength = comparing(artist -> artist.getName().length());

        return artists.stream()
                      .filter(Objects::nonNull)
                      .reduce((artist1, artist2) -> nameLength.compare(artist1, artist2) >= 0 ? artist1 : artist2);
    }

    public static Map<String, Long> frequency(Stream<String> strings) {
        return strings.collect(groupingBy(Function.identity(), counting()));
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

    public static class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

        private static final Set<Characteristics> CHARACTERISTICS = new HashSet<>();

        static {
            CHARACTERISTICS.add(Characteristics.IDENTITY_FINISH);
        }

        private final Function<? super T, ? extends K> classifier;

        public GroupingBy(Function<? super T, ? extends K> classifier) {
            this.classifier = classifier;
        }

        @Override
        public Supplier<Map<K, List<T>>> supplier() {
            return HashMap::new;
        }

        @Override
        public BiConsumer<Map<K, List<T>>, T> accumulator() {
            return (map, element) -> {
                K key = classifier.apply(element);
                List<T> value = map.computeIfAbsent(key, k -> new ArrayList<>());
                value.add(element);
            };
        }

        @Override
        public BinaryOperator<Map<K, List<T>>> combiner() {
            BiFunction<List<T>, List<T>, List<T>> mergeLists = (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };

            return (map1, map2) -> {
                map2.forEach((key, value) -> map1.merge(key, value, mergeLists));
                return map1;
            };
        }

        @Override
        public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return CHARACTERISTICS;
        }
    }

}
