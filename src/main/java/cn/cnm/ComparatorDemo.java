package cn.cnm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lele
 * @version 1.0
 * @Description 当元素的类型没有实现 java.lang.Comparable接口又不方便修改带时 或者
 * 实现了 java.lang.Comparable接口的排序规则不适应当前的操作时，
 * 可以考虑使用 Comparator类的对象来进行排序， 强行对多个对象进行整体排序比较， 实现方式
 * @Email 414955507@qq.com
 * @date 2019/10/9 21:17
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        // 比较一个字符串数组的从大到小的顺序排序
        String[] array = new String[]{"DD", "EE", "BB", "CC", "AA"};
        // 第二个参数就是临时比较器， 指定比较规则, 也不影响String本身的排序规则
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 重写 Compare(Object o1， Object o2) 方法， 比较o1和o2的大小
                // 如果方法返回正整数， 则表示o1大于o2， 返回负整数， 则表示小于， 返回0表示相等
                if (o1 instanceof String && o2 instanceof String) {
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    // 比较反着来，在前面加个-， 大的在前面， 小的在后面
                    return -s1.compareTo(s2);
                } else {
                    throw new RuntimeException("输入的类型不正确....");
                }
            }
        });
        System.out.println(Arrays.toString(array));
    }
}
