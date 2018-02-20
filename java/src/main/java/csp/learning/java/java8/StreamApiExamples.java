package csp.learning.java.java8;

import csp.learning.java.java8.model.Album;
import csp.learning.java.java8.model.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamApiExamples {

    public static int addUp(Stream<Integer> numbers) {
        return numbers.mapToInt(number -> number)
                      .sum();
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream()
                      .flatMap(artist -> Stream.of(artist.getName(), artist.getOrigin()))
                      .collect(toList());
    }

    public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> albums) {
        return albums.stream()
                     .filter(album -> album.getTracks().count() <= 3)
                     .collect(toList());
    }

    public static long getTotalMembers(List<Artist> artists) {
        return artists.stream()
                      .flatMap(Artist::getMembers)
                      .count();
    }

    public static int countLowercaseLetters(String string) {
        return (int) string.chars()
                           .filter(Character::isLowerCase)
                           .count();
    }

    public static Optional<String> mostLowercaseLetters(List<String> strings) {
        return strings.stream()
                      .max(comparing(StreamApiExamples::countLowercaseLetters));
    }

    public static <T, R> Stream<R> map(Stream<T> stream, Function<? super T, ? extends R> mapper) {
        return stream.reduce(
                new ArrayList<R>(),
                (list, t) -> {
                    ArrayList<R> copy = new ArrayList<>(list);
                    copy.add(mapper.apply(t));
                    return copy;
                },
                (list1, list2) -> {
                    ArrayList<R> copy = new ArrayList<>(list1);
                    copy.addAll(list2);
                    return copy;
                }
        ).stream();
    }

    public static <T> Stream<T> filter(Stream<T> stream, Predicate<? super T> predicate) {
        return stream.reduce(
                new ArrayList<T>(),
                (list, t) -> {
                    if (predicate.test(t)) {
                        ArrayList<T> copy = new ArrayList<>(list);
                        copy.add(t);
                        return copy;
                    } else {
                        return list;
                    }
                },
                (list1, list2) -> {
                    ArrayList<T> copy = new ArrayList<>(list1);
                    copy.addAll(list2);
                    return copy;
                }
        ).stream();
    }

}
