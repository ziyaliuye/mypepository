package main.cn.cnm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
        // 这里会自动装箱， 注意集合是用来装对象的
        collection1.add(123);
        // 将集合collection2添加到集合collection1中
        collection1.addAll(collection2);
        // 集合的长度
        System.out.println(collection1.size());
        // Collection重写了toString()方法
        System.out.println(collection1);
        // 判断集合内容是否为空（是否有元素）， 有则返回false， 没有则返回true
        System.out.println(collection1.isEmpty());
        // 清空集合的元素， 但对象还是存在的， 只是没有元素了
        collection1.clear();
        System.out.println(collection1.size());
    }
}
