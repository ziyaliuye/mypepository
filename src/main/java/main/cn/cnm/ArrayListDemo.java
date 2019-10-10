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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lele
 * @version 1.0
 * @Description 作为List接口的主要实现类，线程不安全的， 效率高， 底层使用 Object[] 来存储
 * JDK1.7：初始化时无参构造器创建一个长度是10的 Object[]，添加满10个元素时会进行扩容， 默认扩1.5倍， 若果添加的数据量很大则用数据量的长度来进行扩容，将原有数组中的数据克隆到新的数组
 * *****    结论就是和 StringBuffer 一样， 建议使用带参数的构造器， 减少克隆次数
 * JDK1.8：初始化时无参构造器创建一个没有长度（空的）的Object[]，第一次调用 add() 方法时，根据数据量的长度进行扩容，默认扩容10，数据量超过10时按数据量长度来扩容， 后续操作和 JDK1.7操作一致。
 * *****    结论就是JDK1.8 的对象创建类似于单例懒汉式创建，延迟了数组的创建， 节省了内存。
 * @Email 414955507@qq.com
 * @date 2019/10/10 21:53
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        /*
         关于线程安全问题：Collections 工具类有个 synchronizedList(List<T> list)  可以将ArrayList 转为一个线程安全 List 集合
         所以一般即使有线程安全问题也不会用Vector集合
         */


        List list = new ArrayList<>();
        list.add(123);
        list.add("ABC");
        list.add(new Person());
        list.add(true);
        System.out.println(list);
        /* ArrayList常用方法 */
        // 添加元素操作， 默认往最后位置添加
        list.add("wocao");
        System.out.println(list);
        // 指定索引位置添加元素， 添加后后续的元素每个都往后挪
        list.add(1, "danteng");
        System.out.println(list);
        // 将一个集合的内容添加进来， 默认往最后位置添加
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
        System.out.println(list);
        // 指定索引位置将一个集合的内容添加进来， 添加后后续的元素每个都往后挪
        List list2 = Arrays.asList(3, 2, 1);
        list.addAll(5, list2);
        System.out.println(list);

        // 获取指定索引位置元素
        System.out.println(list.get(1));
        // 返回元素在集合中首次出现的位置, 没有则返回-1
        System.out.println(list.indexOf("wocao"));
        // 返回元素在集合中最后一次出现的位置, 没有则返回-1
        System.out.println(list.lastIndexOf("wocao"));

        // 移除指定索引位置元素， 并且返回这个元素（删除的元素对象）
        System.out.println(list.remove(3));
        // 移除指定内容元素， 并且返回删除结果（boolean类型）
        System.out.println(list.remove("wocao"));

        // 设置指定索引位置的元素值， 并返回被替换的值（替换前的值）
        System.out.println(list.set(0, 999));

        // 返回从begin到end位置的子集合[begin, end)
        System.out.println(list.subList(0, 3));
    }
}
