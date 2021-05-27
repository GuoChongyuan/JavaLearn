package streams;

import java.util.Arrays;
import java.util.List;

public class ForEachTest {
    // Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // java7
        print7(strings);
        System.out.println("===========================");
        // java8
        strings.forEach(System.out::println);

    }

    private static void print7(List<String> strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
