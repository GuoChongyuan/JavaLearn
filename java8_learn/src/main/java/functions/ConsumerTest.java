package functions;

import java.util.function.Consumer;

public class ConsumerTest {
    static class Foo {
        private Integer first;

        public Integer getFirst() {
            return first;
        }

        public void setFirst(Integer first) {
            this.first = first;
        }
    }

    public static void main(String[] args) {
        Foo f = new Foo();
        Consumer<Foo> consumer_fun = foo->foo.setFirst(1);
        consumer_fun.accept(f);
        System.out.println(f.getFirst());
    }
}
