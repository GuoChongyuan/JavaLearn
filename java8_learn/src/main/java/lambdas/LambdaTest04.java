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
