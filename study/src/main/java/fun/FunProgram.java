package fun;


import com.odd.common.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
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
    }

    public static void stream(){
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
                .min(Comparator.comparing(x->x.getBytes()[0]))
                .get();
        System.out.println("最小值：" + min);
    }

    public static void javaFun(){
        // Predicate -> 传入 T , 返回boolean
        System.out.println(JavaFun.predicate.test(7));

        // Consumer -> 传入T ， 无返回值
        JavaFun.consumer.accept(10);

        // Function -> 传入 T，返回 R
        System.out.println(JavaFun.function.apply(99));

        // UnaryOperator —> 传入T，返回T
        System.out.println(JavaFun.unaryOperator.apply(false));
    }

}
