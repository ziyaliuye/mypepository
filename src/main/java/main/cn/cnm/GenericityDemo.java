package main.cn.cnm;

/*
     ┏┛ ┻━━━━━┛ ┻┓
     ┃　　　　　　 ┃
     ┃　　　━　　　┃
     ┃　┳┛　  ┗┳　┃
     ┃　　　　　　 ┃
     ┃　　　┻　　　┃
     ┃　　　　　　 ┃
     ┗━┓　　　┏━━━┛
       ┃　　　┃   神兽保佑
       ┃　　　┃   永无BUG..
       ┃　　　┗━━━━━━━━━┓
       ┃　　　　　　　    ┣┓
       ┃　　　　         ┏┛
       ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
         ┃ ┫ ┫   ┃ ┫ ┫
         ┗━┻━┛   ┗━┻━┛
*/

import org.omg.CORBA.Object;

import java.util.*;

/**
 * @author lele
 * @version 1.0
 * @Description 所谓泛型， 就是允许在定义类、 接口时通过一个标识表示类中某个属性的类型或者是某个方法的返回值及参数类型。
 * 这个类型参数将在使用时（例如，继承或实现这个接口， 用这个类型声明变量、创建对象时）确定（即传入实际的类型参数，也称为类型实参）
 * 需要注意泛型使用的类型只能是引用数据类型（只能用基本数据类型对应的包装类）， 如果没有指定泛型的类型， 则默认按Object类型处理(但不等同于Object)
 * 总结：泛型要用， 则所有地方都用， 要不用， 则所有地方都不用
 * @Email 414955507@qq.com
 * @date 2019/10/14 14:01
 */
public class GenericityDemo {
    public static void main(String[] args) {
        /*
         * 在集合中使用泛型：
         *  JDK1.5后可以在接口或集合类可以使用泛型结构
         *  在实例化集合类时， 可以指明具体的泛型类型
         *  指明后， 在集合类或接口中凡是定义类或接口时， 内部结构（例如方法、构造器、属性等）的泛型的位置都指定为相同的泛型类型
         *  并且后续操作不需要再进行类型强转， 可以直接使用
         */
        // List泛型使用示例：定义一个泛型是Integer类型的List
        /* JDK1.7新特性《类型推断》， 左侧指明了泛型类型，右侧可以不写 */
        List<Integer> list = new ArrayList<>();
        // 添加数据的时候同样只能使用同类型数据， 否则编译报错（数值型数据会自动装箱成包装类）
        list.add(1);
        list.add(3);
        list.add(2);
        // 迭代器同样只能使用Integer类型, <Integer>可以省略， 但是不能写其他的类型
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("List：" + iterator.next());
        }

        // Map使用泛型示例：定义key和value的泛型
        Map<String, Integer> map = new HashMap<>();
        // 添加数据的key-value都需要泛型类型一致（数值型数据会自动装箱成包装类）
        map.put("AA", 1);
        map.put("BB", new Integer(2));
        map.put("CC", 3);
        // 有泛型的Map返回的Set同样是有泛型, Map.Entry<String, Integer>同样可以省略， 但是不能写其他的类型
        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        entry.forEach(System.out::println);

        // 类中定义了泛型， 实例化时没有指定类型， 则T表示Object类型
        // 如果类中定义了泛型， 那么实例化时最好一定要加上泛型， 避免很多不必要的麻烦
        GenericityClass<String> genericityClass = new GenericityClass("wocao");
        genericityClass.test("nimei");

        /*
         * 其他情况：
         *      如果子类继承了父类， 父类中指定了泛型， 可以根据实际情况决定是否需要使用泛型（子类可以全部保留、部分保留、自己增加泛型）
         *      泛型类型不同的引用不能相互赋值， 例如 ArrayList<String>和ArrayList<Integer>的引用就不能相互赋值
         */
    }
}

/*
 * 自定义泛型类示例, 在类的后面加上<>，<>中的标识并不是一个具体的类， 而是一个参数
 * 一般写一个大写的字母表示类型， 例如：
 *      K：key
 *      K：key
 *      V：value
 *      E：Element (在集合中使用，因为集合中存放的是元素)
 *      T：Type（普通Java类）
 *      N：Number（数值包装类型）
 *      ？：表示不确定的java类型
 *
 * 异常类不能使用泛型：继承异常类， 子类不能使用泛型， try-catch， catch的形参中不能使用泛型
 */
class GenericityClass<T> {
    // 定义类的泛型后， 类的内部就可以直接使用这个T
    private T order;
    // 泛型类型创建数组的方式， 可以暂用Object来创建， 然后强转
    T[] array = (T[]) new Object[10];

    // 构造器中T当成形参
    GenericityClass(T order) {
        this.order = order;
    }

    // 普通方法中可以使用类的泛型T， 但这个方法不是泛型方法
    void test(T order) {
        System.out.println(this.order.toString() + order);
    }

    /*
     * 泛型方法， 注意普通方法和泛型方法是不一样的， 泛型方法是在方法中自己定义了泛型， 不是拿类的泛型来使用
     *      泛型方法跟所属的类是不是泛型类没有一毛钱关系
     * 定义方式：在方法返回值前面定义泛型， 然后形参中可以直接使用（不定义编译器是误认为K和V是一个具体的类）
     *      泛型方法在调用时指定泛型的具体类型
     * 泛型方法是可以定义为静态的， 因为泛型参数是在调用时确认的， 并非实例化时确认的
     */
    public <K, V> void genericityMethoed(K key, V value) {
        System.out.println(key.toString() + value.toString());
    }

    /* 静态方法中不能使用类的泛型（类的泛型是在创建对象实例化时才指定的， 静态方法程序一启动就需要创建） */
//    public static void staitcTest(T order){
//        System.out.println("static method...");
//    }
}
