package streams;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapTest {
    // map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // java7
        toSquare(numbers);
        System.out.println("==============================");
        //java8
        List<Integer> result = numbers.stream().map(i -> i * i).collect(Collectors.toList());
        System.out.println(result);

        // 确保要执行的任务对线程环境没有依赖
        // 任务消耗时间长/数据量大到不用思考是否要用parallel
        // 结果没有顺序要求
        System.out.println("==============================");
        List<Integer> result1 = numbers.parallelStream().map(i -> i * i).collect(Collectors.toList());
        System.out.println(result1);
    }

    private static void toSquare(List<Integer> numbers) {
        ArrayList<Integer> result = Lists.newArrayList();
        for (Integer number : numbers) {
            result.add(number * number);
        }
        System.out.println(result);
    }
}
