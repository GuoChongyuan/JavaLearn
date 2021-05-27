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
