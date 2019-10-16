package src.main.java.cn.cn;

/*
                       .::::.
                     .::::::::.
                    :::::::::::
                 ..:::::::::::'    美女保佑、永无Bug
              '::::::::::::'
                .::::::::::
           '::::::::::::::..
                ..::::::::::::.
              ``::::::::::::::::
               ::::``:::::::::'        .:::.
              ::::'   ':::::'       .::::::::.
            .::::'      ::::     .:::::::'::::.
           .:::'       :::::  .:::::::::' ':::::.
          .::'        :::::.:::::::::'      ':::::.
         .::'         ::::::::::::::'         ``::::.
     ...:::           ::::::::::::'              ``::.
    ```` ':.          ':::::::::'                  ::::..
                       '.:::::'                    ':'````..
*/

import java.util.TreeMap;

/**
 * @author lele
 * @version 1.0
 * @Description 保证按照添加的Key-Value进行排序， 实现排序遍历， 需要考虑Key的自然排序或定制排序
 * 底层使用的红黑树结构, 需要进行大小比较， 和TreeSet有一点区别：TreeSet后添加的元素比先添加的元素小则添加失败， 而TreeMap可以正常添加
 * 添加的对象必须是同一个类new出来的
 * @Email 414955507@qq.com
 * @date 2019/10/12 16:33
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("AA", 123);
        treeMap.put("CC", 123);
        // 比先添加的元素小， 可以正常添加进来
        treeMap.put("BB", 123);
        // 拥有Map接口的特点， 重复添加后面的会将前面的覆盖
        treeMap.put("AA", 321);
        // 遍历时按从小到大顺序遍历
        System.out.println(treeMap);
    }
}
