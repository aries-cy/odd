package fun;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Java类库中的函数接口
 */
public class JavaFun {

    // 传入一个T，返回boolean类型
    public static Predicate<Integer> predicate = x -> {
        System.out.println("Predicate自定义逻辑，参数为:" + x);
        return x % 2 == 0 ? x > 5 : x > 10;
    };

    // 传入一个参数，没有返回值
    public static Consumer<Integer> consumer = x -> {
        System.out.println("Consumer自定义逻辑，参数为:" + x);
    };

    // 传入一个参数 T，返回 R
    public static Function<Integer,String> function = x -> {
        System.out.println("Function自定义逻辑，参数为:" + x);
        return String.valueOf(x);
    };

    // 传入一个参数 T， 返回 T
    public static UnaryOperator<Boolean> unaryOperator = x -> {
        System.out.println("UnaryOperator自定义逻辑，参数为:" + x);
        return !x;
    };
}
