package csp.learning.java.lambdas.model;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static csp.learning.java.lambdas.model.MusicTestData.theShins;
import static org.assertj.core.api.Assertions.assertThat;

class PerformanceTest {

    @Test
    void testGetAllMusicians() {
        Album chutesTooNarrow = Album.named("Chutes Too Narrow").by(theShins);
        Stream<Artist> allMusicians = chutesTooNarrow.getAllMusicians();
        assertThat(allMusicians).extracting(Artist::getName)
                                .contains(
                                        "The Shins",
                                        "James Mercer",
                                        "Yuuki Matthews",
                                        "Mark Watrous",
                                        "Casey Foubert",
                                        "John Sortland",
                                        "Patti King"
                                );
    }

}
