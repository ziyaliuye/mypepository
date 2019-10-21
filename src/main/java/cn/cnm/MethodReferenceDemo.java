package cn.cnm;

/*
                                       ,s555SB@@&
                                     :9H####@@@@@Xi
                                    1@@@@@@@@@@@@@@8
                                  ,8@@@@@@@@@B@@@@@@8
                                 :B@@@@X3hi8Bs;B@@@@@Ah,
            ,8i                  r@@@B:     1S ,M@@@@@@#8;
           1AB35.i:               X@@8 .   SGhr ,A@@@@@@@@S
           1@h31MX8                18Hhh3i .i3r ,A@@@@@@@@@5
           ;@&i,58r5                 rGSS:     :B@@@@@@@@@@A
            1#i  . 9i                 hX.  .: .5@@@@@@@@@@@1
             sG1,  ,G53s.              9#Xi;hS5 3B@@@@@@@B1
              .h8h.,A@@@MXSs,           #@H1:    3ssSSX@1
              s ,@@@@@@@@@@@@Xhi,       r#@@X1s9M8    .GA981
              ,. rS8H#@@@@@@@@@@#HG51;.  .h31i;9@r    .8@@@@BS;i;
               .19AXXXAB@@@@@@@@@@@@@@#MHXG893hrX#XGGXM@@@@@@@@@@MS
               s@@MM@@@hsX#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,
             :GB@#3G@@Brs ,1GM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B,
           .hM@@@#@@#MX 51  r;iSGAM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@8
         :3B@@@@@@@@@@@&9@h :Gs   .;sSXH@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:
     s&HA#@@@@@@@@@@@@@@M89A;.8S.       ,r3@@@@@@@@@@@@@@@@@@@@@@@@@@@r
  ,13B@@@@@@@@@@@@@@@@@@@5 5B3 ;.         ;@@@@@@@@@@@@@@@@@@@@@@@@@@@i
 5#@@#&@@@@@@@@@@@@@@@@@@9  .39:          ;@@@@@@@@@@@@@@@@@@@@@@@@@@@;
 9@@@X:MM@@@@@@@@@@@@@@@#;    ;31.         H@@@@@@@@@@@@@@@@@@@@@@@@@@:
  SH#@B9.rM@@@@@@@@@@@@@B       :.         3@@@@@@@@@@@@@@@@@@@@@@@@@@5
    ,:.   9@@@@@@@@@@@#HB5                 .M@@@@@@@@@@@@@@@@@@@@@@@@@B
          ,ssirhSM@&1;i19911i,.             s@@@@@@@@@@@@@@@@@@@@@@@@@@S
             ,,,rHAri1h1rh&@#353Sh:          8@@@@@@@@@@@@@@@@@@@@@@@@@#:
           .A3hH@#5S553&@@#h   i:i9S          #@@@@@@@@@@@@@@@@@@@@@@@@@A.
             ...........这特么哪个大神写的代码.....................
*/

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lele
 * @version 1.0
 * @Description 当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
 *  方法引用可以看做是Lambda表达式深层次的表达。
 *  换句话说，方法引用就是Lambda表达式，也就是函数式接口的一个实例，通过”方法的名字“来”指向（调用）一个方法“，可以认为是Lambda表达式的一个语法糖。
 *  要求： 实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致（柯里化函数除外！）
 *  格式： 使用操作符 “::” 将类(或对象) 与 方法名分隔开来。
 *  如下三种主要使用情况：
 *      对象::实例方法名（非静态方法）
 *      类::静态方法名
 *      类::实例方法名
 * @Email 414955507@qq.com
 * @date 2019/10/20 15:43
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
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
