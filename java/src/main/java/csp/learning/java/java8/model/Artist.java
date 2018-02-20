package csp.learning.java.java8.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * An individual or group who creates music.
 */
public class Artist {

    private String name;
    private Set<Artist> members = new HashSet<>();
    private String origin;

    private Artist(String name) {
        this.name = name;
    }

    public static Artist named(String name) {
        return new Artist(name);
    }

    public Artist with(Artist member) {
        members.add(member);
        return this;
    }

    public Artist from(String origin) {
        this.origin = origin;
        return this;
    }

    public String getName() {
        return name;
    }

    public Stream<Artist> getMembers() {
        return members.stream();
    }

    public String getOrigin() {
        return origin;
    }

    public boolean isGroup() {
        return members.size() > 1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("members", members)
                .append("origin", origin)
                .toString();
    }

}
