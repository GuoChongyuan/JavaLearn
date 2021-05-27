package streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LimitSortTest {
    public static void main(String[] args) {
        List<String> collect = Stream.of("one", "two", "three", "four")
                .sorted()
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
