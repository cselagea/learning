package csp.learning.java.lambdas.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * A single release of music, comprising several tracks.
 */
public class Album implements Performance {

    private String name;
    private Set<Track> tracks = new HashSet<>();
    private Set<Artist> musicians = new HashSet<>();

    private Album(String name) {
        this.name = name;
    }

    public static Album named(String name) {
        return new Album(name);
    }

    public Album with(Track track) {
        tracks.add(track);
        return this;
    }

    public Album by(Artist artist) {
        musicians.add(artist);
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public Stream<Track> getTracks() {
        return tracks.stream();
    }

    @Override
    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("tracks", tracks)
                .append("musicians", musicians)
                .toString();
    }

}
