package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsTest {
    public static void main(String[] args) {
        //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}
