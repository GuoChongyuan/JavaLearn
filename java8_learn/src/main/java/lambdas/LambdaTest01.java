package lambdas;

public class LambdaTest01 {
    // 接口声明
    interface MathOperation {
        int operation(int a, int b);
    }
    // 接口使用
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        // 匿名内部类实现

        LambdaTest01 lambdaTest01 = new LambdaTest01();
        int res = lambdaTest01.operate(10, 20, new MathOperation() {

            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        });

        System.out.println(res);

    }
}
