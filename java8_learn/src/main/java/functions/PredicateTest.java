package functions;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateTest {
    private static final Predicate<Integer> EVEN = t -> {return t % 2 ==0;};

    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> list = integers.stream().filter(EVEN).collect(Collectors.toList());
        System.out.println(list);
    }
}
