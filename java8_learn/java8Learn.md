## Java8新特性
Java8的特性比较多，用的比较多的几个特性如下：
- 1、Lambda表达式；  
Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）
- 2、方法引用；  
方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
- 3、默认方法；  
默认方法就是一个在接口里面有了一个实现的方法
- 4、新编译工具；  
新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps
- 5、Stream API；  
新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中
- 6、Date Time API；  
加强对日期与时间的处理
- 7、Optional；  
Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常
- 8、Nashorn javascript引擎  
Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用
- 9、方法引用  
通过方法的名字来指向一个方法

下面重点介绍下常用的类型
## Java 8 Lambda 表达式
Java8之前的用法：  
- 匿名内部类写法实现：[LambdaTest01](../main/java/lambdas/LambdaTest01.java)

Java8及以后的写法，使用lambda表达式
```
(parameters) -> expression 或 (parameters) ->{ statements; }  
```
- 表达式写法  
（1）形参列表，如果只有一个参数可以省略括号，当无参数类型时可以使用（）或者obj来代替。  
（2）箭头（->）  
（3）代码块部分，如果代码只有一行则可以省略掉花括号，不然使用花括号将lambda表达式的代码部分标记出来。  
（4）lambda表达式的目标类型必须是只有一个抽象方法的接口（接口则只有一个函数，抽象类则只能有一个抽象方法）或者只有一个默认方法,表达式创建出来的目标对象就是这个接口  

lambda表达式实践：[LambdaTest02](../main/java/lambdas/LambdaTest02.java)

- 变量作用域  
lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在lambda 内部修改定义在域外的局部变量，否则会编译错误。    
作用域实践：[LambdaTest03](../main/java/lambdas/LambdaTest03.java)

- lambda表达式的几种写法  
  -  无需声明类型，一个参数不需要圆括号
  - 声明类型，多个参数需要圆括号
  - 不声明类型，多个参数需要圆括号，只包含一个语句，无需大括号
  - 大括号，需要返回值
  
实践：[LambdaTest04](../main/java/lambdas/LambdaTest04.java)
```
package lambdas;

public class LambdaTest04 {
    public static void main(String[] args) {
        // 1 无需声明类型，一个参数不需要圆括号
        LambdaTest1 lambdaTest1 = a -> 2 * a;
        // 2 声明类型，多个参数需要圆括号
        LambdaTest2 lambdaTest21 = (int a, int b) -> a + b;
        // 3 不声明类型，多个参数需要圆括号，只包含一个语句，无需大括号
        LambdaTest2 lambdaTest22 = (a, b) -> {
            return a - b;
        };
        // 4 大括号，需要返回值
        LambdaTest2 lambdaTest23 = (a, b) -> {
            a = a * a;
            return a * b;
        };

        System.out.println(lambdaTest1.multi2(3));
        System.out.println(lambdaTest21.operation(3, 2));
        System.out.println(lambdaTest22.operation(3, 2));
        System.out.println(lambdaTest23.operation(3, 2));
    }

    interface LambdaTest1 {
        int multi2(int i);
    }

    interface LambdaTest2 {
        int operation(int i, int j);
    }
}

```

- 在java.util.function包预定下了大量函数式接口，典型的包含如下4类接口  
...Function：这类接口通常包含一个apply抽象方法，对参数进行处理转换，然后返回一个新的值。  
case  [FunctionTest](../main/java/functions/FunctionTest.java)   
...Consumer：这类接口通常包含一个accept抽象方法，用于对参数进行处理，但是不返回一个新的值。   
case  [ConsumerTest](../main/java/functions/ConsumerTest.java)   
...Predicate：这类接口通常包含一个test抽象方方法，通过对参数的处理计算，然后返回一个boolean值  
case  [PredicateTest](../main/java/functions/PredicateTest.java)  
...Supplier:这类接口通常包含一个getAs***抽象方法，这种方法无参数，按照某种逻辑运算返回一个数据值。   
case  [SupplierTest](../main/java/functions/SupplierTest.java)  

## Java 8 对象方法引用
方法引用可以理解为Lambda表达式的另外一种表现形式    
- 方法引用简介
方法引用通过方法的名字来指向一个方法。  
方法引用可以使语言的构造更紧凑简洁，减少冗余代码。  
方法引用使用一对冒号 :: 。
- 方法引用分类

类型|语法|对应的Lambda表达式
---|---|---
静态方法引用|类名::staticMethod|(args) -> 类名.staticMethod(args)
实例方法引用(有参)|inst::instMethod|(args) -> inst.instMethod(args)
对象方法引用（有参）|类名::instMethod|(inst,args) -> 类名.instMethod(args)
构建方法引用|类名::new|(args) -> new 类名(args)

实战：[QuatoTest01](../main/java/quatos/QuatoTest01.java)
```
package quatos;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;

import java.util.ArrayList;


public class QuatoTest01 {

    public static void main(String[] args) {
        // 构造函数调用
        Supplier<TestInter> testInter = TestInter::new;

        ArrayList<TestInter> testInters = Lists.newArrayList(testInter.get());
        // 静态方法调用
        testInters.forEach(TestInter::staticTest);

        // 有参方法引用
        TestInter testInter1 = testInter.get();
        testInters.forEach(testInter1::voidTest1);

        // 无参方法调用
        testInters.forEach(TestInter::voidTest2);

    }

    static class TestInter{
        public TestInter() {
        }

        public static void staticTest(TestInter testInter){
            System.out.println("staticTest is " + testInter.toString());
        }

        public void voidTest1(TestInter testInter){
            System.out.println("voidTest is " + testInter.toString());
        }

        public void voidTest2(){
            System.out.println("VoidTest1 is " + this.toString());
        }

    }
}

```
## Java 8 Optional类
- Optional类  
Optional 类的引入很好的解决空指针异常。  
Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。  
Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。   

实战：[OptionalTest01](../main/java/optionals/OptionalTest01.java)
```
package optionals;

import java.util.Optional;

public class OptionalTest01 {
    public static void main(String[] args) {
        OptionalTest01 optionalTest01 = new OptionalTest01();
        Integer value1 = null;
        Integer value2 = new Integer(10);
        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);
        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(optionalTest01.sum(a, b));
    }


    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());
        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));
        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}

```
## Java 8 Stream API
Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。  
Stream使用一种类似用SQL语句从数据库查询数据的直观方式来提供一种对Java集合运算和表达的高阶抽象。  
Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。  
这种风格将要处理的元素集合看作一种流，流在管道中传输，并且可以在管道的节点上进行处理，比如筛选，排序，聚合等。  
元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
实战[streams](../main/java/streams)

- 两个特征  
Pipelining:：中间操作都会返回流对象本身。这样多个操作可以串联成一个管道，如同流式风格（fluent style）。这样做可以对操作进行优化，比如延迟执行(laziness)和短路( short-circuiting)。  
内部迭代：以前对集合遍历都是通过Iterator或者For-Each的方式,显式的在集合外部进行迭代，这叫做外部迭代。Stream提供了内部迭代的方式，通过访问者模式(Visitor)实现。

- 生成流：  
stream() −为集合创建串行流。  
parallelStream() − 为集合创建并行流。
- forEach  
Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。

- map  
map 方法用于映射每个元素到对应的结果

- filter  
filter 方法用于通过设置条件过滤出元素。

- 并行（parallel）程序  
parallelStream 是流并行处理程序的代替方法。

- limit  
limit 方法用于获取指定数量的流。

- sorted  
sorted 方法用于对流进行排序。

-  Collectors  
Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。

- peek  
执行环节遍历一遍所有的元素，可以用来打印日志（debugging）

- 统计  
一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
```
List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
System.out.println("列表中最大的数 : " + stats.getMax());
System.out.println("列表中最小的数 : " + stats.getMin());
System.out.println("所有数之和 : " + stats.getSum());
System.out.println("平均数 : " + stats.getAverage());
```

使用方法参考case：[StreamTests](./java/streams/StreamTests.java)

## Java 8 Date Time API
Java 8通过发布新的Date-Time API (JSR 310)来进一步加强对日期与时间的处理。  
在旧版的Java 中，日期时间API 存在诸多问题，其中有：
- 非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
- 设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
- 时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。

Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
- Local(本地) − 简化了日期时间的处理，没有时区的问题。实战[LocalDateTimeTest](../main/java/dates/LocalDateTimeTest.java)
```
// 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);
        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);
        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();
        System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);
        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);
        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);
        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
```
- Zoned(时区) − 通过制定的时区处理日期时间。实战[ZonedTest](../main/java/dates/ZonedTest.java)
```
// 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);
        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
```

联合使用case：[DateTests](./java/dates/DateTest.java)