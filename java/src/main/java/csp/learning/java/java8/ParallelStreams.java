package csp.learning.java.java8;

import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreams {

    public static int serialSumOfSquares(IntStream integers) {
        return integers.map(x -> x * x)
                       .sum();
    }

    public static int parallelSumOfSquares(IntStream integers) {
        return integers.parallel()
                       .map(x -> x * x)
                       .sum();
    }

    // this reduction works fine sequentially, but has a bug when running in parallel because the identity value
    // must be an identity for the provided accumulator function, i.e. x * identity = x should hold for all x
    public static int serialProduct(List<Integer> integers) {
        return integers.stream()
                       .reduce(5, (acc, x) -> acc * x);
    }

    // correctly provides value of 1 as the identity and saves the multiplication by 5 until the end
    public static int parallelProduct(List<Integer> integers) {
        return 5 * integers.parallelStream()
                           .reduce(1, (acc, x) -> acc * x);
    }

}
