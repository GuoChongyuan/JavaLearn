package functions;

import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<String> supplier = String::new;
        String str = supplier.get();
        System.out.println(str);
    }
}
