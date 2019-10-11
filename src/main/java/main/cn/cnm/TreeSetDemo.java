package main.cn.cnm;

import java.util.Comparator;
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
        // 定制排序比较器
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Mouse && o2 instanceof Mouse) {
                    Mouse m1 = (Mouse) o1;
                    Mouse m2 = (Mouse) o2;
                    if (m1.getPrice() > m2.getPrice()) {
                        return 1;
                    } else if (m1.getPrice() > m2.getPrice()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } else {
                    throw new RuntimeException("传入类型不匹配....");
                }
            }
        };

        // 将定时比较器传入有参构造器， 比较时覆盖Mouse对象中原有的compareTo()
        // 注意TreeSet元素必须要同一个类new出来的， 否则报错
        TreeSet treeSet = new TreeSet(comparator);
        treeSet.add(new Mouse("罗技", 321));
        treeSet.add(new Mouse("闪电侠", 999));
        treeSet.add(new Mouse("超人", 2000));
        treeSet.add(new Mouse("罗技", 3211));

        /* 涉及对象的排序， 对象需要重写compareTo方法（自然排序） 或者使用初始化TreeSet时传入有参构造器Comparator对象（定制排序）*/
        System.out.println(treeSet);
    }
}
