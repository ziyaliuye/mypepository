package cn.cnm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
        // mapToDouble(ToDoubleFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream（操作流程类似于flatMap）
        // mapToInt(ToIntFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream（操作流程类似于flatMap）
        // mapToLong(ToLongFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream（操作流程类似于flatMap）

        /* 《排序》排序的类要实现Comparable接口或者给定定制排序器Comparator， 不然会报错： */
        // sorted()：产生一个新流，其中按自然顺序排序
        System.out.println("sorted()自然排序后结果：");
        list1.stream().sorted().forEach(System.out::println);
        // sorted(Comparator com)：产生一个新流，其中按比较器顺序排序（Lambda表达式写非常简短, 注意前面加了负号， 从大到小排序）
        System.out.println("sorted()定制排序从大到小结果：");
        list1.stream().sorted((e1, e2) -> -e1.compareTo(e2)).forEach(System.out::println);

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

        /*
         * 终端操作会从流的流水线生成结果。其结果可以是任何不是流的值，例如： List、 Integer，甚至是void
         * 流进行了终止操作后，不能再次使用
         */

        /* 《匹配与查找》：返回boolean类型结果 */
        // allMatch(Predicate p)：检查是否匹配所有元素（价格是否大于500）
        System.out.println("是否所有Book：" + list.stream().allMatch(e -> e.getPrice() > 500));
        // anyMatch(Predicate p)：检查是否至少匹配一个元素
        System.out.println("是否至少有一本Book超过5000块：" + list.stream().anyMatch(e -> e.getPrice() > 5000));
        // noneMatch(Predicate p)：检查是否没有匹配所有元素
        System.out.println("是否没有Book超过100000块：" + list.stream().noneMatch(e -> e.getPrice() > 100000));
        // findFirst()：返回第一个元素
        System.out.println("返回第一本Book：" + list.stream().findFirst());
        /* findAny()：返回当前流中的任意元素， 如果使用“顺序流”，则会一直返回第一个元素， 如果使用“并行流”，则会返回其中某个流中的第一个元素（不好用， 经常返回的都是同一个元素） */
        System.out.println("（有坑， 不是正常意义上随机）返回任意一本Book：" + list.parallelStream().findAny());
        // count()：返回流中元素总数
        System.out.println("Book总数：" + list.stream().count());
        // max(Comparator c)：返回流中最大值， 需要给定定制排序器（先获取Book的价格， 然后根据价格给定排序规则）
        System.out.println("Book总数：" + list.stream().map(Book::getPrice).max(Integer::compare));
        // min(Comparator c)：返回流中最小值， 需要给定定制排序器
        System.out.println("Book总数：" + list.stream().map(Book::getPrice).min(Integer::compare));
        // forEach(Consumer c)："内部迭代"(使用Collection接口需要用户去做迭代，称为外部迭代, 相反，Stream API使用内部迭代——它帮你把迭代做了)
        System.out.println("调用Stream内部迭代方法迭代遍历打印所有元素:");
        list.stream().forEach(System.out::println);

        /* 《规约》：可以理解为Stream做完映射操作后， 对产生的结果做一次处理（例如求和操作）*/
        // map和reduce的连接通常称为map-reduce模式，因Google用它来进行网络搜索而出名
        // reduce(T iden, BinaryOperator b)：可以将流中元素反复结合起来，得到一个值并返回 T, 例如求1-10的自然数之和
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        /* 第一个参数就是初始值的意思， 从初始值开始做某个计算， 和scala一毛一样, 会反复将前两个值相加然后得到一个结果， 再将结果往后循环加上初始值再相加 */
        System.out.println("1-10的和：" + list2.stream().reduce(0, Integer::sum));
        System.out.println("写法等同于：" + list2.stream().reduce((i1, i2) -> 0 + i1 + i2));
        // reduce(BinaryOperator b)：可以将流中元素反复结合起来，得到一个值并返回Optional<T>
        System.out.println("1-10的和：" + list2.stream().reduce(Integer::sum));
        System.out.println("写法等同于：" + list2.stream().reduce((i1, i2) -> i1 + i2));

        /*
         * collect(Collector c) 《收集》：简而言之就是接收一个Collector的实现将Stream的结果转换为一个集合：Set、List、Map
         * Collector 接口中方法的实现决定了如何对流执行收集的操作(如收集到 List、 Set、Map)
         * 另外， Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例：
         */
        List<Book> list3 = list.stream().filter(e -> e.getPrice() > 5000).collect(Collectors.toList());
        System.out.println("筛选价格大于5000的Book并转为一个List：");
        for (Book b : list3) {
            System.out.println(b);
        }
        /* 其他方法示例 */
        // toList List<T> 把流中元素收集到List
        // List<Employee> emps= list.stream().collect(Collectors.toList());

        // toSet Set<T> 把流中元素收集到Set
        // Set<Employee> emps= list.stream().collect(Collectors.toSet());

        // toCollection Collection<T> 把流中元素收集到创建的集合
        // Collection<Employee> emps =list.stream().collect(Collectors.toCollection(ArrayList::new));

        // counting Long 计算流中元素的个数
        // long count = list.stream().collect(Collectors.counting());

        // summingInt Integer 对流中元素的整数属性求和
        // int total=list.stream().collect(Collectors.summingInt(Employee::getSalary));

        // averagingInt Double 计算流中元素Integer属性的平均值
        // double avg = list.stream().collect(Collectors.averagingInt(Employee::getSalary));

        // summarizingInt IntSummaryStatistics 收集流中Integer属性的统计值。 如：平均值
        // int SummaryStatisticsiss= list.stream().collect(Collectors.summarizingInt(Employee::getSalary));

        // joining String 连接流中每个字符串
        // String str= list.stream().map(Employee::getName).collect(Collectors.joining());

        // maxBy Optional<T> 根据比较器选择最大值
        // Optional<Emp>max= list.stream().collect(Collectors.maxBy(comparingInt(Employee::getSalary)));

        // minBy Optional<T> 根据比较器选择最小值
        // Optional<Emp> min = list.stream().collect(Collectors.minBy(comparingInt(Employee::getSalary)));

        // reducing 归约产生的类型  从一个作为累加器的初始值开始，利用BinaryOperator与流中元素逐个结合，从而归约成单个值
        // int total=list.stream().collect(Collectors.reducing(0, Employee::getSalar, Integer::sum));

        // collectingAndThen 转换函数返回的类型 包裹另一个收集器，对其结果转换函数
        // int how= list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));

        // groupingBy Map<K, List<T>> 根据某属性值对流分组，属性为K，结果为V
        // Map<Emp.Status, List<Emp>> map= list.stream().collect(Collectors.groupingBy(Employee::getStatus));

        // partitioningBy Map<Boolean, List<T>> 根据true或false进行分区
        // Map<Boolean,List<Emp>> vd = list.stream().collect(Collectors.partitioningBy(Employee::getManage));
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