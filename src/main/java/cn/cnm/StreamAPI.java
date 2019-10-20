package cn.cnm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lele
 * @version 1.0
 * @Description Stream API:
 * Stream API ( java.util.stream) 把真正的函数式编程风格引入到Java中。这是目前为止对Java类库最好的补充
 * 因为Stream API可以极大提供Java程序员的生产力，让程序员写出高效率、干净、简洁的代码
 * <p>
 * Stream 是Java8中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作
 * 使用Stream API 对集合数据进行操作，就类似于使用SQL执行的数据库查询
 * 也可以使用 Stream API 来并行执行操作。简言之， Stream API 提供了一种高效且易于使用的处理数据的方式
 * <p>
 * Stream是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列：“集合讲的是数据， Stream讲的是计算！”
 * 注意：
 * ①Stream 自己不会存储元素。
 * ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 * @Email 414955507@qq.com
 * @date 2019/10/20 18:35
 */
public class StreamAPI {
    public static void main(String[] args) {
        /*
         * Stream操作的三个步骤（编写代码和调用操作）：
         *   ①创建Stream：一个数据源（例如集合、数组）， 获取一个流
         *   ②中间操作：一个中间操作链， 对数据源的数据进行如何的处理
         *   ③终止操作（终端操作）：一旦执行终止操作， 就会执行②中间操作链， 并产生结果， 之后就不会再被调用
         * 注意执行操作是从第③步才开始的， 并且执行完后就不能再被调用了， 需要重新创建
         */

        /* 创建方式一：通过集合 */
        List<Book> list = getList();
        // stream()：返回一个顺序流， 从集合中取数据时是单线程按顺序来
        Stream<Book> stream = list.stream();
        // parallelStream()：返回一个并行流， ， 从集合中取数据时是多线程并行取
        Stream<Book> parallelStream = list.parallelStream();

        /*
         * 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作
         * 否则中间操作不会执行任何的处理！而在终止操作时一次性全部处理，称为《惰性求值》
         *
         *
         *
         *
         *
         *
         * 《排序》：
         *   sorted()：产生一个新流，其中按自然顺序排序
         *   sorted(Comparator com)：产生一个新流，其中按比较器顺序排序
         */
        /* 《筛选与切片方法》： */
        // filter(Predicate p)：接收Lambda ， 从流中排除某些元素
        System.out.println("排除价格<=500的Book：");
        stream.filter(e -> e.getPrice() > 500).forEach(System.out::println);
        // limit(n)：截断流：使其元素不超过指定的数量
        System.out.println("截断前10个元素：");
        /* 注意再使用之前的Stream对象会报错IllegalStateException， 先前说了流操作一旦执行完终止操作就需要重新创建 */
        // 重新生成一个Stream, 然后再使用
        list.stream().limit(10).forEach(System.out::println);
        // distinct()：筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        System.out.println("筛除重复数据后的集合：");
        list.stream().distinct().forEach(System.out::println);
        // skip(long n):跳过元素， 返回一个扔掉了前N个元素的流， 若流中元素不足n个， 则返回一个空流， 与limit(n)互补
        System.out.println("跳过前3个元素后的集合：");
        list.stream().skip(3).forEach(System.out::println);

        List<String> list1 = Arrays.asList("aa", "cc", "bb", "dd", "e");
        /* 《映射》： */
        // map(Function f)：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
        System.out.println("集合的所有元素都转为大写：");
        list1.stream().map(String::toUpperCase).forEach(System.out::println);
        // flatMap(Function f)：接收一个函数作为参数，将流中的每个值都换成另一个流， 然后把所有转换后的流连接成一个新的流
        /*
         * map类似于集合中的add()方法：不管怎么操作都是将每一个元素修改后组成一个新的集合
         * flatMap()类似于addAll()方法：和map()方法同样都是作用于每个元素， 但是生成的结果每一个元素就是一个集合， 最终返回由N个集合组成的集合
         * 从这点看出来越来越像scala的语法， 两个差不多多操作的方法返回的结果却是不同维度（一维二维甚至三维）的集合
         */
        System.out.println("flatMap方法将集合的所有元素都转为大写并拆成一个个的字符打印：");
        // flatMap()方法内的返回值其实是“一个集合”的Stream， 然后它可以对这个Stream再做其他遍历操作
        // 如果是map()做同样的遍历操作（集合中的元素也是集合的情况）需要做几步操作
        list1.stream().flatMap(StreamAPI::toUpperCase).forEach(System.out::println);
        // mapToDouble(ToDoubleFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream
        // mapToInt(ToIntFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream

        // mapToLong(ToLongFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream

        /* 创建方式二：通过数组（基本数据类型） */
        // 从过Arrays工具类的静态方法stream(T[] array)可以返回一个流（基本数据类型）
        // 需要用BaseStream的实现类根据类型来接收， 例如IntStream、DoubleStream， 引用类型使用Stream即可
        IntStream intStream = Arrays.stream(new int[]{1, 2, 3, 6, 5, 4, 7, 8, 9});

        /* 创建方式三：调用Stream类的静态方法of()返回一个流 */
        Stream<Integer> stringStream = Stream.of(1, 2, 3, 6, 5, 4, 9, 8, 7);

        /* 创建方式四：创建无限流， 前三者创建的都是有限的 */
        /*
         * iterate()：迭代, 遍历前10个偶数, 第二个参数就是一个函数用于将参数+2然后将结果返回
         * 第二个参数是JDK1.8新增的UnaryOperator函数式接口， 继承自Function
         *   用于对类型为T结果。 包含方法T的对象进行一元运算为：T apply(T t); 并返回T类型的
         * limit() 限制无限流的长度， 不然就是变成无限迭代
         * forEach()相当于就是“终止操作”， 调用它时才开始启动“中间链”环节, 然后用方法引用将结果打印
         */
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
        /*
         * generate()：生成, 同样默认是无限制生成
         * limit() 限制无限流的长度， 不然就是变成无限生成
         * forEach()相当于就是“终止操作”， 调用它时才开始启动“中间链”环节, 然后用方法引用将结果打印
         */
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    // 定义一个函数（方法）
    private static Stream<Character> toUpperCase(String str) {
        // flatMap()就是用来处理集合的元素也是集合的情况， 将元素拆成一个集合来模拟
        List<Character> list = new ArrayList<>();
        for (char c : str.toUpperCase().toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    // 返回一个集合
    private static List<Book> getList() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(1001, "倚天屠龙记", 199));
        list.add(new Book(1002, "射雕英雄传", 299));
        list.add(new Book(1003, "欢乐英雄", 99));
        list.add(new Book(1004, "浣花洗剑录", 399));
        list.add(new Book(1005, "七种武器", 599));
        list.add(new Book(1006, "神雕侠侣", 298));
        list.add(new Book(1007, "福尔摩斯探案集", 1998));
        list.add(new Book(1008, "金瓶梅", 2998));
        list.add(new Book(1009, "金鳞岂是池中物", 19));
        list.add(new Book(1009, "金鳞岂是池中物", 19));
        list.add(new Book(1010, "大傻子", 59998));
        list.add(new Book(1010, "大傻子", 59998));
        list.add(new Book(1011, "斗破苍穹", 36));
        list.add(new Book(1012, "斗罗大陆", 69));
        list.add(new Book(1013, "名侦探柯南", 7));
        list.add(new Book(1014, "笑傲江湖", 699));
        list.add(new Book(1015, "哆啦A梦", 899));
        list.add(new Book(1016, "Python从入门到放弃", 298));
        list.add(new Book(1017, "MySQL从删库到跑路", 398));
        list.add(new Book(1018, "Java从精通到不会", 598));
        return list;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class Book {
    private int id;
    private String name;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (price != book.price) return false;
        return name.equals(book.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        return result;
    }
}