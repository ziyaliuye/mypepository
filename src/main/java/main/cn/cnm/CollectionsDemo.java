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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * @author lele
 * @version 1.0
 * @Description Collections是一个操作Set、List和Map等集合的工具类
 * 提供了一系列静态方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法
 * @Email 414955507@qq.com
 * @date 2019/10/12 17:29
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(2);
        list.add(1);
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list.add(6);
        list.add(5);
        list.add(4);

        // reverse反转List集合中的顺序
        Collections.reverse(list);
        System.out.println("reverse：" + list);
        // List集合中的元素进行排序排序
        Collections.shuffle(list);
        System.out.println("shuffle：" + list);
        // List集合中的元素进行自然顺序排序
        Collections.sort(list);
        System.out.println("sort：" + list);
        // 根据指定的Compareator对List集合中的元素进行排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println("ComparatorSort：" + list);
        // 将指定list集合中的索引0个元素和索引2处元素进行交换
        Collections.swap(list, 0, 2);
        System.out.println("swap：" + list);
        // 根据元素的自然顺序，返回给定集合中的最大元素
        System.out.println("List中最大元素：" + Collections.max(list));
        /*
         * Object max(Collection， Comparator) 根据Comparator指定的顺序，返回给定集合中的最大元素
         * Object min(Collection) 根据元素的自然顺序，返回给定集合中的最小元素
         * Object min(Collection， Comparator) 根据Comparator指定的顺序，返回给定集合中的最小元素
         */

        // 返回指定集合中指定元素的出现次数
        System.out.println("List中3出现的次数：" + Collections.frequency(list, 3));
        // 将list1中的内容复制到list中
        Collections.copy(list, list1);
        System.out.println("复制后的List：" + list);
        // 将list中的指定value替换成新的value（根据value的值来决定）
        Collections.replaceAll(list, 2, 3);
        System.out.println("替换后的List：" + list);
    }
}
