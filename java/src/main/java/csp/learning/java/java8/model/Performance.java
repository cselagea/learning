package csp.learning.java.java8.model;

import java.util.ArrayList;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

/**
 * A performance by some musicians. For example, an album or gig.
 */
public interface Performance {

    String getName();

    Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusiciansUsingCollect() {
        return getMusicians().collect(
                ArrayList<Artist>::new,
                (artists, artist) -> {
                    artists.add(artist);
                    artists.addAll(artist.getMembers().collect(toList()));
                },
                ArrayList::addAll
        ).stream();
    }

    // cleaner approach
    default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> concat(Stream.of(artist), artist.getMembers()));
    }

}
