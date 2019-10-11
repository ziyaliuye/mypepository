package main.cn.cnm;

import java.util.TreeSet;

/**
 * @author lele
 * @version 1.0
 * @Description 可以按照添加的元素指定属性进行排序， 放入 TreeSet 集合的对象必须是同一个类 new 出来的
 * TreeSet底层采用红黑树的存储结构
 * @Email 414955507@qq.com
 * @date 2019/10/11 15:08
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        // treeSet.add(new Person());
        /* 涉及对象的排序， 对象需要重写compareTo方法（自然排序） 或者使用初始化TreeSet时传入有参构造器Comparator对象（定制排序）*/
        // 对象没有重写compareTo()方法则会报错
        System.out.println(treeSet);
    }
}
