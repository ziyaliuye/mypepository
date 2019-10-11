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
 * 不能保证元素的排列顺序、HashSet 不是线程安全的、集合元素可以是 null
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
        /* HashSet继承自Set， 拥有其特点：无序的、不可重复的, Set集合存储的元素都是无序、不可重复的，底层还是用数组实现， 添加的元素并非按照数组的索引的顺序添加，而是根据元素的HashCode决定存储位置  */
        Set set = new HashSet();
        set.add("1");
        set.add("1");
        set.add("AA");
        System.out.println(set);
    }
}
