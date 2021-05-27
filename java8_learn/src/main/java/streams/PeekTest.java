package streams;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeekTest {
    public static void main(String[] args) {
        List<String> collect = Stream.of("one", "two", "three", "four")
                .peek(e -> System.out.println("Before Filtered value: " + e))
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        System.out.println(collect);

        // 拆解
        ArrayList<String> arrayList = Lists.newArrayList("one", "two", "three", "four");
        ArrayList<String> list = Lists.newArrayList();
        for (String s : arrayList) {
            System.out.println("--> Before Filtered value: " + s);
            if(s.length()> 3){
                System.out.println("--> Filtered value: " + s);
                String s1 = s.toUpperCase();
                System.out.println("--> Mapped value: " + s1);
                list.add(s1);
            }
        }

        System.out.println(list);
    }
}
