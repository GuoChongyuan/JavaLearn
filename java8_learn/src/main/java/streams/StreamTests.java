package streams;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTests {

    public static void main(String[] args) {

        Order order01 = new Order(1, 10, "20190301",
                new Date(), "上海市-浦东区", new Date(), new Date(), 4, 1, 100.0);
        Order order02 = new Order(2, 30, "20190302",
                new Date(), "北京市四惠区", new Date(), new Date(), 1, 1, 2000.0);
        Order order03 = new Order(3, 20, "20190303",
                new Date(), "北京市-朝阳区", new Date(), new Date(), 4, 1, 500.0);
        Order order04 = new Order(4, 40, "20190304",
                new Date(), "北京市-大兴区", new Date(), new Date(), 4, 1, 256.0);
        Order order05 = new Order(5, 40, "20190305",
                new Date(), "上海市-松江区", new Date(), new Date(), 4, 0, 1000.0);
        List<Order> ordersList = Lists.newArrayList(order01, order02, order03, order04, order05);

        // 筛选和切片
        System.out.println("======================过滤有效订单==================================");
        ordersList.stream().filter(order->order.getIsValid()==1)
                .forEach(System.out::println);

        System.out.println("======================过滤有效订单 取第一页数据(每页2条记录) ==================================");
        ordersList.stream().filter(order->order.getIsValid()==1)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("======================过滤有效订单 取第2页数据(每页2条记录) 并打印到控制台==================================");
        ordersList.stream().filter(order->order.getIsValid()==1)
                .skip((2-1) * 2)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("======================筛选无效订单去除重复订单号记录==================================");
        ordersList.stream().filter(order->order.getIsValid()==0)
                .distinct()
                .forEach(System.out::println);

        // 映射
        System.out.println("======================过滤有效订单，获取所有订单编号==================================");
        ordersList.stream().filter(order->order.getIsValid()==1)
                .map(order -> order.getOrderNo())
                .forEach(System.out::println);


        // 排序
        System.out.println("======================过滤有效订单 根据用户id 进行排序==================================");
        ordersList.stream().filter(order->order.getIsValid()==1)
                .sorted(Comparator.comparing(Order::getUserId))
                .forEach(System.out::println);

        // 查找与匹配
        System.out.println("======================allMatch匹配结果==================================");
        boolean b = ordersList.stream().filter(order -> order.getIsValid() == 1)
                .allMatch(o -> o.getStatus() != 0);
        System.out.println(b);

        System.out.println("====================== 筛选有效订单 全部未完成订单==================================");
        boolean noneMatch = ordersList.stream().filter(order -> order.getIsValid() == 1)
                .noneMatch(o -> o.getStatus() == 5);
        System.out.println(noneMatch);

        System.out.println("======================  筛选有效订单 返回第一条订单==================================");
        Order order1 = ordersList.stream().filter(order -> order.getIsValid() == 1)
                .findAny().get();
        System.out.println(order1);

        System.out.println("======================  筛选所有有效订单 返回订单总数==================================");
        long count = ordersList.stream().filter(order -> order.getIsValid() == 1)
                .count();
        System.out.println(count);

        // Collector数据收集
        System.out.println("====================== 筛选所有有效订单并收集订单列表==================================");
        ordersList.stream()
                .filter((order) -> order.getIsValid() == 1)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("====================== 筛选所有有效订单 并收集订单号 与 订单金额==================================");
        Map<String,Double> map=ordersList.stream().filter((order) -> order.getIsValid() == 1)
                .collect(Collectors.toMap(Order::getOrderNo, Order::getTotal));
        // java8 下对map进行遍历操作 如果 Map的Key重复,会报错
        map.forEach((k,v)->{
            System.out.println("k:"+k+":v:"+v);
        });


        //最值 先map,后比较大小
        System.out.println("======================筛选所有有效订单 并计算最小订单金额==================================");
        Optional<Double> collect = ordersList.stream()
                .filter((order) -> order.getIsValid() == 1)
                .map(Order::getTotal)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(collect.get());

        // 分组
        System.out.println("======================根据有效订单支付状态进行分组操作==================================");
        Map<Integer,List<Order>> g01=ordersList.stream()
                .filter((order) -> order.getIsValid() == 1)
                .collect(Collectors.groupingBy(Order::getStatus));
        g01.forEach((status,order)->{
            System.out.println("----------------");
            System.out.println("订单状态:"+status);
            order.forEach(System.out::println);
        });

        // 数据统计
        System.out.println("======================对金额进行数据统计==================================");
        DoubleSummaryStatistics dss = ordersList.stream().collect(Collectors.summarizingDouble(Order::getTotal));
        System.out.println("max:"+dss.getMax());
        System.out.println("min:"+dss.getMin());
        System.out.println("sum:"+dss.getSum());
        System.out.println("average:"+dss.getAverage());
        System.out.println("count:"+dss.getCount());

        // 数据统计和处理
        System.out.println("======================reduce() 对订单号进行拼接==================================");
        String str = ordersList.stream().map(order -> order.getOrderNo()).reduce((str1, str2) -> str1 + "," + str2).get();
        System.out.println(str);

        String str1 = ordersList.stream().map(order -> order.getOrderNo()).reduce("",(str3, str4) -> str3 + "," + str4);
        System.out.println(str1);
    }
}
