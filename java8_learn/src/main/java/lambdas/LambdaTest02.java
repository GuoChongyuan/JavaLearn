package lambdas;

public class LambdaTest02 {

    // 接口使用
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        // 匿名内部类实现

        LambdaTest02 lambdaTest01 = new LambdaTest02();
        int res = lambdaTest01.operate(10, 20,(a,b)->a+b);

        System.out.println(res);

    }
    // 接口声明
    interface MathOperation {
        int operation(int a, int b);
    }
}

