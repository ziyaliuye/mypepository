package src.main.java.cn.cn;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author lele
 * @version 1.0
 * @Description 可以按照添加的元素指定属性进行排序， 放入 TreeSet 集合的对象必须是同一个类 new 出来的
 * TreeSet底层采用红黑树的存储结构， 一个元素左右对应两个元素（左小右大）， 所以添加元素时也必须按从小到大的顺序添加， 否则数据添加不进去
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

        /* 涉及对象的排序， 对象需要重写compareTo方法（自然排序） 或者使用初始化TreeSet时传入有参构造器Comparator对象（定制排序）*/
        // 将定时比较器传入有参构造器， 比较时覆盖Mouse对象中原有的compareTo()
        // 注意TreeSet元素必须要同一个类new出来的， 否则报错
        TreeSet treeSet = new TreeSet(comparator);
        treeSet.add(new Mouse("罗技", 321));
        treeSet.add(new Mouse("闪电侠", 999));
        treeSet.add(new Mouse("超人", 2000));
        /* 注意这里是以price的大小进行排序的， 从小到大， 那么添加元素时顺序也要是从小到大， 如果后面的数据比前面的小或者相等则数据添加不进来 */
        treeSet.add(new Mouse("雷蛇", 200));
        // 输出时就会发现最后一个元素<雷蛇:200>并没有添加进来
        System.out.println(treeSet);
    }
}
