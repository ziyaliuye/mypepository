package cn.cnm;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lele
 * @version 1.0
 * @Description 构造器引用和方法引用类似（只看构造器的参数列表， 并且也包含类似于柯里化函数）
 * 数组引用：和构造器引用没什么区别， 数组本身就是一个特殊的类
 * <p>
 * 格式：类名::new
 * @Email 414955507@qq.com
 * @date 2019/10/20 15:48
 */
public class ConstructorReferenceDemo {
    public static void main(String[] args) {
        /*
         * Supplier中的T get()
         * PersonTest的无参构造器PersonTest()
         */
        // 通常写法
        Supplier<PersonTest> supplier = new Supplier<PersonTest>() {
            @Override
            public PersonTest get() {
                // 直接返回一个PersonTest实例
                return new PersonTest();
            }
        };
        // Lambda写法
        Supplier<PersonTest> supplier1 = () -> new PersonTest();

        // 构造器引用写法：类名::new
        Supplier<PersonTest> supplier2 = PersonTest::new;

        System.out.println(supplier.get().getClass());
        System.out.println(supplier1.get().getClass());
        System.out.println(supplier2.get().getClass());

        /*
         * Function中的R apply(T t)
         * PersonTest的构造器PersonTest(String name)
         */
        // Lambda写法
        Function<String, PersonTest> function = (name) -> new PersonTest(name);
        // 构造器引用
        Function<String, PersonTest> function1 = PersonTest::new;
        System.out.println(function.apply("狗蛋子"));
        System.out.println(function1.apply("狗蛋子"));

        /*
         * BiFunction（这些玩意全是JDK1.8增加的内置函数）中的R apply(T t, U u)
         * PersonTest的构造器PersonTest(String name, int age)
         */
        // Lambda写法
        BiFunction<String, Integer, PersonTest> function2 = (name, age) -> new PersonTest(name, age);
        /* 构造器引用， 柯里化类似于 new Person(name, age), java自动将3个参数分成两部分 */
        BiFunction<String, Integer, PersonTest> function3 = PersonTest::new;
        System.out.println(function2.apply("二傻子", 21));
        System.out.println(function3.apply("二傻子", 21));

        /*
         * 数组引用：
         *   Function中的R apply(T t)
         */
        // Lambda写法
        Function<Integer, String[]> function6 = lengths -> new String[lengths];
        // 创建一个长度是5的空数组
        String[] strings = function6.apply(5);
        /* 数组引用写法：和构造器的写法级别没区别， 数组本身就是一个特殊的类 */
        Function<Integer, String[]> function7 = String[]::new;
        // 创建一个长度是5的空数组
        String[] strings1 = function7.apply(5);
        System.out.println(Arrays.toString(strings));

        System.out.println(Arrays.toString(strings1));
    }
}
