package csp.learning.java.java8.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A single piece of music.
 */
public class Track {

    private String name;

    private Track(String name) {
        this.name = name;
    }

    public static Track named(String name) {
        return new Track(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .toString();
    }

}
