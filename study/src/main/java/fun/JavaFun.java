package fun;

import java.util.function.*;

/**
 * Java类库中的函数接口
 */
public class JavaFun {

    // 传入一个T，返回boolean类型
    public static Predicate<Integer> predicate = x -> {
        System.out.print("Predicate自定义逻辑，参数为:" + x + "，返回值：");
        return x % 2 == 0 ? x > 5 : x > 10;
    };

    // 传入一个参数，没有返回值
    public static Consumer<Integer> consumer = x -> {
        System.out.println("Consumer自定义逻辑，参数为:" + x + "，无返回值");
    };

    // 传入一个参数 T，返回 R
    public static Function<Integer, String> function = x -> {
        System.out.print("Function自定义逻辑，参数为:" + x + "，返回值：");
        return String.valueOf(x);
    };

    public static Supplier<Integer> supplier = () -> {
        System.out.print("Supplier自定义逻辑，没有参数，返回T，返回值：" );
        return 0;
    };

    // 传入一个参数 T， 返回 T
    public static UnaryOperator<Boolean> unaryOperator = x -> {
        System.out.print("UnaryOperator自定义逻辑，参数为:" + x + "，返回值：");
        return !x;
    };

    public static BinaryOperator<Integer> binaryOperator = (x, y) -> {
        System.out.print("BinaryOperator自定义逻辑，逻辑为:" + x + "+" + y + "=" + (x + y) + "，返回值：");
        return x + y;
    };
}
