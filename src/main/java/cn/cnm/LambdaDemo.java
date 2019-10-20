package cn.cnm;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author lele
 * @version 1.0
 * @Description Lambda表达式 是一个匿名函数，我们可以把 Lambda 表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）
 * 使用它可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升
 * <p>
 * 在Java中Lambda表达式本质就是“一个函数式接口的实例”
 * 函数式接口：如果一个接口只有一个抽象方法时， 就成为函数式接口（当有类继承函数式接口时， 这个类也能使用Lambda表达式）
 * 一般函数式接口上面会有一个注解@FunctionalInterface， 它就明确的表示这个接口是一个函数式接口（注解只是标识， 类似于 @Override， 当加上注解后接口有多个方法则编译报错）
 * @Email 414955507@qq.com
 * @date 2019/10/20 11:44
 */
public class LambdaDemo {
    public static void main(String[] args) {
        /*
         * Lambda表达式只适用于“接口或者类（从父类中继承过来的抽象方法）中只有一个抽象方法的情况下”
         *  格式 (o1, o2) -> { return Integer.compare(o1, o2); };
         *  ->：Lambda操作符（箭头操作符）
         *  ->左边的内容：Lambda形参列表（就是接口抽象方法的形参名）
         *      参数类型可以省略由编译器自动推断
         *      当只有一个参数时， 形参的括号可以省略
         *  ->右边的内容：Lambda体（就是接口抽象方法的方法体）
         *      如果Lambda体只有一条执行语句时，可以省略大括号和return关键字（要省一起省）
         *      多条语句时不能省略大括号
         */

        /* 一共有6种形式， 第一种：无参、无返回值 */
        // 通常写法
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("新的线程启动1...");
            }
        };
        thread.start();

        // Lambda表达式， 因为Thread是类不是接口， 所以Lambda表达式需要写到new的结构里(形参的地方)
        Thread thread1 = new Thread(() -> {
            System.out.println("新的线程启动2...");
        });

        /* 第二种：有一个参数、无返回值 */
        // 通常写法
        TestInterface testInterface = new TestInterface() {
            @Override
            public void say(String speak) {
                System.out.println(speak);
            }
        };
        testInterface.say("your are shabi...");

        // Lambda表达式
        TestInterface testInterface1 = (String speak) -> {
            System.out.println(speak);
        };
        testInterface1.say("your are shabi...");

        /* 第三种：数据类型可以省略， 可由编译器推断得出类型， 称为“类型推断” */
        TestInterface testInterface2 = (speak) -> {
            System.out.println(speak);
        };
        testInterface2.say("your are shabi...");

        /* 第四种：如果抽象方法的形参只有一个参数时， 小括号可以省略 */
        TestInterface testInterface3 = speak -> {
            System.out.println(speak);
        };
        testInterface3.say("your are shabi...");

        /* 第五种：如果抽象方法的形参需要两个或者以上的参数， 多条执行语句， 并且有返回值时 */
        // 通常写法
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("第五种方式啥都不能省");
                return o1.compareTo(02);
            }
        };
        System.out.println(comparator.compare(12, 31));
        // Lambda表达式
        Comparator<Integer> comparator1 = (o1, o2) -> {
            System.out.println("第五种方式啥都不能省");
            return o1.compareTo(02);
        };
        System.out.println(comparator1.compare(12, 31));

        /* 第六种：当Lambda体只有一条语句时， 如果有return和大括号， 可以省略return和大括号 */
        // 通常写法
        Comparator<Integer> comparator2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(02);
            }
        };
        System.out.println(comparator2.compare(12, 31));
        // Lambda表达式
        Comparator<Integer> comparator3 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator3.compare(12, 31));


        /* 核心函数的使用 */
        CoreFunctionClass coreFunctionClass = new CoreFunctionClass();
        // 消费型接口， 第二个参数Consumer<Double>就是需要传递一个类的实例, 通常写法
        coreFunctionClass.happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("通常消费" + aDouble + "元");
            }
        });
        // Lambda表达式写法
        coreFunctionClass.happyTime(500, aDouble ->
                System.out.println("Lambda消费" + aDouble + "元")
        );
        // 供给型接口
        List<String> list = Arrays.asList("天上", "地下", "河里", "山里");
        // 通常写法
        List<String> resultList = coreFunctionClass.filterString(list, new Predicate<String>() {
            // 调用实例化Predicate时才写具体的逻辑
            @Override
            public boolean test(String s) {
                // 只返回集合中带"里"字的元素
                return s.contains("里");
            }
        });
        System.out.println("通常过滤：" + resultList);
        // Lambda表达式写法
        List<String> resultList1 = coreFunctionClass.filterString(list, s -> s.contains("里"));
        System.out.println("Lambda过滤：" + resultList1);

        /*
         *  当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
         *  方法引用可以看做是Lambda表达式深层次的表达。
         *  换句话说，方法引用就是Lambda表达式，也就是函数式接口的一个实例，通过方法的名字来指向一个方法，可以认为是Lambda表达式的一个语法糖。
         *  要求： 实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致（柯里化方法除外！）
         *  格式： 使用操作符 “::” 将类(或对象) 与 方法名分隔开来。
         *  如下三种主要使用情况：
         *      对象::实例方法名（非静态方法）
         *      类::静态方法名
         *      类::实例方法名
         */

        /*
         * 对象::实例方法名（非静态方法）
         *   Consumer中的void accept(T t)
         *   PrintStream中的void println(T t)
         *  此时两个方法的参数列表和返回值类型一致， 可以使用“方法引用”
         */
        // Lambda写法
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("宇宙");
        // 方法引用写法， 相当于一个对象调用另一个方法， 因为参数是统一的所以可以忽略
        PrintStream ps = System.out;
        Consumer consumer1 = ps::println;
        consumer1.accept("宇宙");
        // 方法引用的简写：System类中有一个静态的属性out， 类型就是PrintStream， 所以可以直接使用
        Consumer consumer2 = System.out::println;
        consumer2.accept("宇宙");

        // Supplier中的T get() 和 PersonTest中的String getDefaultName()
        /* 因为调用的方法返回的是String，泛型T的范围大于String， 可以看作是包含的关系， 可以使用“方法引用” */
        // Lambda写法
        PersonTest personTest = new PersonTest("李狗子", 123);
        Supplier<String> supplier = () -> personTest.getName();
        System.out.println(supplier.get());
        // 方法引用写法
        Supplier<String> supplier1 = personTest::getName;

        /*
         * 类::静态方法名
         *   Comparator中的int compare(T t1, T t2)
         *   Integer中的int compare(T t1, T t2)
         *  参数列表和返回值类型一致， 可以使用“方法引用”
         */
        // Lambda写法
        Comparator<Integer> comparator6 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println("静态方法Lambda版：" + comparator6.compare(12, 21));
        // 方法引用写法, Integer.compare()是一个静态的方法, 参数类型由于是一样， 所以省略
        Comparator<Integer> comparator7 = Integer::compare;
        System.out.println("静态方法方法引用版：" + comparator7.compare(12, 21));

        // Function中的R apply(T t) 和 Math中的Long round(Double d)
        // Lambda写法
        Function<Double, Long> function = d -> Math.round(d);
        // 方法引用写法, Math.round()是一个静态的方法
        Function<Double, Long> function1 = Math::round;

        /*
         * 类::实例方法名（特殊的地方， 一般情况下只能实例调用实例方法）
         * Comparator中int compare(T t1, T t2)
         * String中的int t1.compareTo(t2), 类似于一个方法接受了t1参数， 然后通过t1调用另一个方法， 参数为t2
         *  两者可以视为等同， 类似于Scala中的函数柯里化(Currying)：
         *  柯里化(Currying)指的是将原来接受两个参数的函数变成新的接受一个参数的函数的过程，新的函数返回一个以原有第二个参数为参数的函数
         */

        // Lambda写法
        Comparator<String> comparator8 = (s1, s2) -> s1.compareTo(s2);
        System.out.println("Lambda:" + comparator8.compare("AA", "BB"));
        // 方法引用的写法， 柯里化函数不同的写法是等价的， 那么相当于参数列表一样， 可以使用方法引用
        /* 此时两者虽然参数列表不一致， 但是在java看来是一样的， 能自动识 */
        Comparator<String> comparator9 = String::compareTo;
        System.out.println("类::实例方法名" + comparator8.compare("AA", "BB"));

        // Function中的R apply(T t) 和 PersonTest中的String getName()
        // Lambda写法
        Function<PersonTest, String> function2 = p -> p.getName();
        /* 方法引用写法， 虽然明面上参数列表不一致， 但在java开来底层一样的（第一个参数调用方法， 形参是第二个参数） */
        Function<PersonTest, String> function3 = PersonTest::getName;

    }
}

interface TestInterface {
    void say(String speak);
}

class CoreFunctionClass {
    /*
     * Java内置4大核心函数：
     *      消费型接口（有参数、无返回值）void Consumer<T> ， 例如 void accept(T t)
     *      供给型接口（无参数、有返回值）Supplier<T> ， 例如 int get()
     *      函数型接口（有参数、有返回值 Function<T, R>， 并且参数和返回值还可以不一样）R apply(T t) ， 例如 int apply(String str)
     *      断定型接口（有参数、有一个boolean类型的返回值）Predicate<T>， 例如 boolean test(String str)
     */
    // 消费型接口举例
    public void happyTime(double money, Consumer<Double> consumer) {
        // 可以直接拿内置的函数进行使用
        consumer.accept(money);
    }

    // 供给型接口举例
    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        // 根据给定的规则过滤集合中的字符串， 规则由Predicate的方法来决定
        // 这里相当于啥也没写， 只是调用了一下方法， 具体的逻辑在调用时写
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                arrayList.add(s);
            }
        }
        return arrayList;
    }
    // 函数型接口
    // 断定型接口
}
