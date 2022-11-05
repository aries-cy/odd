package fun;


import com.odd.common.entity.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FunProgram {

    private static List<User> users;

    static {
        users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(Long.parseLong(String.valueOf(i)));
            user.setName("name" + i);
            users.add(user);
        }
    }


    public static void main(String[] args) {
        // Java函数接口调用测试
        javaFun();

        // Stream操作
        stream();

        // reduce
        reduce();

        // SummaryStatistics -> 对集合中的数字进行统计
        summaryStatistics();

        // optional
        optional();
    }

    public static void optional(){
        String name = "aa";
        Optional<String> optional = Optional.ofNullable(name);
        String s = optional.orElse("小明");
        System.out.println(s);
        Optional<String> optional1 = optional.filter(x -> x.equals("aa"));
        Integer ans = optional1.map(x -> {
            if ("a".equals(x)) {
                return 0;
            }
            return 1;
        }).orElse(-1);
        System.out.println(ans);
    }

    public static void summaryStatistics() {
        // mapToLong
        LongSummaryStatistics summaryStatistics = users.stream().mapToLong(User::getId).summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d", summaryStatistics.getMax(), summaryStatistics.getMin(),
                summaryStatistics.getAverage(), summaryStatistics.getSum());
        System.out.println();
        // flatMapToLong
        LongSummaryStatistics statistics = Stream.of(users, users, users)
                .flatMapToLong(list -> list.stream().mapToLong(User::getId)).summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d", statistics.getMax(), statistics.getMin(),
                statistics.getAverage(), statistics.getSum());
    }

    public static void reduce() {
        Integer result = Stream.of(1, 2, 3, 4)
                // 传入参数 T 和 BinaryOperator<T> 函数接口
                // 将 T 和Stream中的每个元素 作为参数，执行 binaryOperator 的逻辑
                // 将结果只赋给 T ，并执行下一次循环
                .reduce(0, (x, y) -> {
                            System.out.println("BinaryOperator自定义逻辑，逻辑为:" + x + "+" + y + "=" + (x + y));
                            return x + y;
                        }
                );
        System.out.println(result);
    }

    public static void stream() {
        List<String> list1 = Stream.of("a", "b", "c").collect(Collectors.toList());
        List<String> list2 = Stream.of("a", "d", "e").collect(Collectors.toList());

        List<String> ans = Stream.of(list1, list2)
                // 将多个流转化为一个流，中间可以做一些流操作
                .flatMap(list -> list.stream().filter(x -> !x.equals("a")))
                // 对流中元素单独操作
                .map(String::toUpperCase).collect(Collectors.toList());
        ans.forEach(System.out::print);
        System.out.println("----------------------");
        String min = Stream.of(list1, list2)
                .flatMap(Collection::stream)
                .min(Comparator.comparing(x -> x.getBytes()[0]))
                .get();
        System.out.println("最小值：" + min);
    }

    public static void javaFun() {
        // Predicate -> 传入 T , 返回boolean
        System.out.println(JavaFun.predicate.test(7));

        // Consumer -> 传入T ， 无返回值
        JavaFun.consumer.accept(10);

        // Function -> 传入 T，返回 R
        System.out.println(JavaFun.function.apply(99));

        // Supplier -> 不传入参数，返回 T
        System.out.println(JavaFun.supplier.get());

        // UnaryOperator —> 传入T，返回T
        System.out.println(JavaFun.unaryOperator.apply(false));

        // BinaryOperator -> 传入T,T ;  返回 T
        System.out.println(JavaFun.binaryOperator.apply(3, 4));
    }

}
