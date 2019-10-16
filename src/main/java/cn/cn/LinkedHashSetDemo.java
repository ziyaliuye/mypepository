package src.main.java.cn.cn;

import java.util.LinkedHashSet;

/**
 * @author lele
 * @version 1.0
 * @Description inkedHashSet是HashSet的子类， 在HashSet的基础上给元素增加了元素的前后的指针（双向链表）
 * 遍历集合时可以按照添加顺序来遍历（Set是无序的,  LinkedHashSet 并没有改变这一本质）
 * <p>
 * inkedHashSet 根据元素的 hashCode值来决定元素的存储位置，但它同时使用双向链表维护元素的次序，这使得元素看起来是以插入顺序保存的
 * <p>
 * LinkedHashSet  插入性能略低于 HashSet， 但在迭代访问 Set 里的全部元素时有很好的性能
 * @Email 414955507@qq.com
 * @date 2019/10/11 11:56
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        // 使用方式和方法和 HashSet 没有区别， 底层通过用是一个HashMap对象
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("1");
        linkedHashSet.add("1");
        linkedHashSet.add(123);
        System.out.println(linkedHashSet);
    }
}
