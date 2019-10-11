package main.cn.cnm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lele
 * @version 1.0
 * @Description HashSet 是Set接口的典型实现，大多数时候使用Set集合时都使用这个实现类
 * HashSet按Hash算法来存储集合中的元素，因此具有很好的存取、查找、删除性能
 * <p>
 * HashSet 具有以下特点：
 * 不能保证元素的排列顺序、不是线程安全的、集合元素可以是null
 * <p>
 * HashSet 集合判断两个元素相等的标准：
 * 两个对象通过 hashCode() 方法比较相等，并且两个对象的 equals() 方法返回值也相等
 * <p>
 * 对于存放在Set容器中的对象，对应的类一定要重写equals()和hashCode(Objectobj)方法，以实现对象相等规则。即：“相等的对象必须具有相等的散列码”
 * @Email 414955507@qq.com
 * @date 2019/10/11 10:36
 */
public class HashSetDemo {
    public static void main(String[] args) {
        /* HashSet继承自Set， 拥有其特点：无序的、不可重复的, Set集合存储的元素都是无序、不可重复的，底层还是用（数组 + 链表）实现， 添加的元素并非按照数组的索引的顺序添加，而是根据元素的HashCode决定存储位置  */
        Set set = new HashSet();
        set.add("1");
        set.add("1");
        set.add("AA");
        System.out.println(set);

        /*
         *  HashSet存储元素过程（底层：数组 + 链表）：
         *      当向HashSet集合中存入一个元素时， HashSet会调用该对象的hashCode()方法来得到该对象的hashCode值， 然后根据hashCode值， 通过某种散列函数决定该对象在HashSet底层数组中的存储位置
         *      如果HashCode()不想等， 那么直接认为是不同的两个元素，添加元素成功， 如果两个元素的hashCode()值相等， 会再继续调用equals方法，
         *      如果equals方法结果为true， 添加失败
         *      如果equals方法结果为false， 那么会保存该元素， 但是该数组的位置已经有元素了，那么会通过链表的方式继续链接
         *      （七上八下）同HashCode不同equals的情况是通过链表的方式存储后进来的元素， JDK7是存储在原来数据的上面，  JDK8则将数据存储在原来数据的下面）
         */

    }
}
