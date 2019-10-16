package cn.cnm;

import java.util.*;

/**
 * @author lele
 * @version 1.0
 * @Description 集合框架
 * |----Collection接口：单列集合，用来存储一个一个的对象
 * |----List接口：存储有序的、可重复的数据。  -->“动态”数组
 * |----ArrayList、LinkedList、Vector
 * <p>
 * |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
 * |----HashSet、LinkedHashSet、TreeSet
 * <p>
 * |----Map接口：双列集合，用来存储一对(key - value)一对的数据   -->高中函数：y = f(x)
 * |----HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 * @Email 414955507@qq.com
 * @date 2019/10/10 16:21
 */
public class CollectionDemo {
    public static void main(String[] args) {
        // Collection接口常用方法（继承于它的类都会有这些方法）
        Collection collection1 = new ArrayList();
        Collection collection2 = new ArrayList();
        // 添加数据
        collection1.add("AA");
        collection1.add(new Date());
        collection2.add("wocao");
        collection1.add(new MyThread());
        // 这里会自动装箱， 注意集合是用来装对象的
        collection1.add(1234);
        // 将集合collection2添加到集合collection1中
        collection1.addAll(collection2);
        // 集合的长度
        System.out.println(collection1.size());
        // Collection重写了toString()方法
        System.out.println(collection1);
        // 判断集合内容是否为空（是否有元素）， 有则返回false， 没有则返回true
        System.out.println(collection1.isEmpty());
        // 清空集合的元素， 但对象还是存在的， 只是没有元素了
        collection2.clear();
        System.out.println(collection2.size());

        // 判断集合中是否包含对象， 返回boolean
        // 比较的时候默认根据对象的地址（调用的equals()方法）， 有需要需要重写对象的equals()方法
        // String、包装类都是重写过compareTo()方法的， 可以直接拿来用
        // 类型不一致返回也是false， 例如String的"1234"和Integer的1234返回就是false
        System.out.println(collection1.contains("1234"));
        System.out.println(collection1.contains(1234));
        System.out.println(collection1.contains(new MyThread()));
        // 判断形参中的集合的所有元素是否都包含与集合中
        System.out.println(collection1.containsAll(collection2));

        // 移出集中指定元素， 同样需要进行对比， 有必要时重写对象equals()方法
        collection1.remove(1234);
        System.out.println(collection1);
        collection2.add("wocao");
        // 移除现有集合中形参中的集合的所有元素（求差集）
        collection1.removeAll(collection2);
        System.out.println(collection1);
        collection2.add("AA");
        // 移除现有集合中形参中的集合的所有元素除外的元素（求交集）
        collection1.retainAll(collection2);
        System.out.println(collection1);

        // 比较两个集合是否一样, 注意是完全一样， ArrayList是有序集合， 所以顺序也要是一样
        Collection collection3 = new ArrayList();
        Collection collection4 = new ArrayList();
        collection3.add("AA");
        collection3.add("BB");
        collection4.add("BB");
        collection4.add("AA");
        System.out.println(collection3.equals(collection4));

        // 返回集合对象的hashCode
        System.out.println(collection3.hashCode());

        // 将集合转为数组， 由于Collection中是可以有多种类型，所以只能转为Object数组
        Object[] array = collection3.toArray();
        for (Object obj : array) {
            System.out.println(obj);
        }

        // 将数组转为ArrayList
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);
        // 将数组整个当成ArrayList中的一个元素（汗。。注意泛型是int[]）
        List<int[]> array1 = Arrays.asList(new int[]{1, 2, 3});
        System.out.println(array1);

        /* 集合元素的遍历， iterator方法 返回的是Iterator接口的实例， 通过实例的方法可以遍历集合 */
        // 获取集合的迭代器, 集合对象每次调用 iterator()方法 都得到一个全新的迭代器对象，默认游标都在集合 的第一个元素之前
        Iterator iterator = list.iterator();
        // hasNext() 判断迭代器中是否还有下一个元素（指针不会向下移）， 和next() 搭配使用
        while (iterator.hasNext()) {
            // next()  获取迭代器中下一个元素（指针指向下一个元素）， 如果没有元素则会报错
            System.out.println(iterator.next());
        }

        // 迭代时删除集合中的数据（是集合本身的数据， 迭代器只是获取了一个可以迭代集合的类， 实际数据还在集合中）
        // 如果迭代器的指针指向第一个元素之前或者指针指向的元素已经remove()了一次， 那么再调用 remove() 则会报 IllegalStateException 异常
        Collection lists = new ArrayList();
        lists.add("AA");
        lists.add("BB");
        lists.add("CC");
        Iterator iterator1 = lists.iterator();
        while (iterator1.hasNext()) {
            String tmp = String.valueOf(iterator1.next());
            if ("BB".equals(tmp)) {
                iterator1.remove();
            }
        }
        /*  并且注意这个remove()方法不要随便用， 最好用集合本身的remove() 方法
            如果 ArrayList 是由 Arrays工具类的 asList() 方法产生，调用它的迭代器的 remove方法就会报 java.lang.UnsupportedOperationException 异常
            因为这个产生 ArrayList不是Java.util.ArrayList, 而是Arrays的内部类（继承 AbstractList后自己写的） */
        System.out.println(lists);


        /*
         JDK5.0后提供了比 while 循环更方便的遍历方式：foreach （增强 for循环）
         遍历操作不需要获取 Collection 或 数组 的长度， 无需使用索引访问元素， 遍历集合的底层是调用 Iterator 完成操作
         */
        for (Object str : lists) {
            System.out.println(str);
        }

        /* 也可以直接调用集合的forEach()方法， 可以在方法内编写逻辑， ::是jdk8的新特性 */
        lists.forEach(System.out::println);

    }
}
