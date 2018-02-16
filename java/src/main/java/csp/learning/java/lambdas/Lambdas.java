package csp.learning.java.lambdas;

import csp.learning.java.lambdas.model.Album;
import csp.learning.java.lambdas.model.Artist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Lambdas {

    public static final ThreadLocal<DateFormat> threadLocalDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy"));

    public static int addUp(Stream<Integer> numbers) {
        return numbers.mapToInt(number -> number)
                      .sum();
    }

    public static Map<String, String> byOrigin(Stream<Artist> artists) {
        return artists.collect(toMap(Artist::getName, Artist::getOrigin));
    }

    public static List<Album> filterIfContainsAtMostThreeTracks(Stream<Album> albums) {
        return albums.filter(album -> album.getTracks().count() <= 3)
                     .collect(toList());
    }

}
