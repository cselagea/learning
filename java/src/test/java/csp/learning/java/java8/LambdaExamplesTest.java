package csp.learning.java.java8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static java.util.Calendar.AUGUST;
import static java.util.Calendar.DECEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class LambdaExamplesTest {

    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @MethodSource("dates")
    void testDateFormatting(Date date, String expectedFormat) {
        String formattedDate = LambdaExamples.threadLocalDateFormat.get().format(date);
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

}
