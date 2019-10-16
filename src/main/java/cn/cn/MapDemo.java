package src.main.java.cn.cn;

/*
               ii.                                         ;9ABH,
              SA391,                                    .r9GG35&G
              &#ii13Gh;                               i3X31i;:,rB1
              iMs,:,i5895,                         .5G91:,:;:s1:8A
               33::::,,;5G5,                     ,58Si,,:::,sHX;iH1
                Sr.,:;rs13BBX35hh11511h5Shhh5S3GAXS:.,,::,,1AG3i,GG
                .G51S511sr;;iiiishS8G89Shsrrsh59S;.,,,,,..5A85Si,h8
               :SB9s:,............................,,,.,,,SASh53h,1G.
            .r18S;..,,,,,,,,,,,,,,,,,,,,,,,,,,,,,....,,.1H315199,rX,
          ;S89s,..,,,,,,,,,,,,,,,,,,,,,,,....,,.......,,,;r1ShS8,;Xi
        i55s:.........,,,,,,,,,,,,,,,,.,,,......,.....,,....r9&5.:X1
       59;.....,.     .,,,,,,,,,,,...        .............,..:1;.:&s
      s8,..;53S5S3s.   .,,,,,,,.,..      i15S5h1:.........,,,..,,:99
      93.:39s:rSGB@A;  ..,,,,.....    .SG3hhh9G&BGi..,,,,,,,,,,,,.,83
      G5.G8  9#@@@@@X. .,,,,,,.....  iA9,.S&B###@@Mr...,,,,,,,,..,.;Xh
      Gs.X8 S@@@@@@@B:..,,,,,,,,,,. rA1 ,A@@@@@@@@@H:........,,,,,,.iX:
     ;9. ,8A#@@@@@@#5,.,,,,,,,,,... 9A. 8@@@@@@@@@@M;    ....,,,,,,,,S8
     X3    iS8XAHH8s.,,,,,,,,,,...,..58hH@@@@@@@@@Hs       ...,,,,,,,:Gs
    r8,        ,,,...,,,,,,,,,,.....  ,h8XABMMHX3r.          .,,,,,,,.rX:
   :9, .    .:,..,:;;;::,.,,,,,..          .,,.               ..,,,,,,.59
  .Si      ,:.i8HBMMMMMB&5,....                    .            .,,,,,.sMr
  SS       :: h@@@@@@@@@@#; .                     ...  .         ..,,,,iM5
  91  .    ;:.,1&@@@@@@MXs.                                       .,,:,:&S
  hS ....  .:;,,,i3MMS1;..,..... .  .     ...                     ..,:,.99
  ,8; ..... .,:,..,8Ms:;,,,...                                     .,::.83
   s&: ....  .sS553B@@HX3s;,.    .,;13h.                            .:::&1
    SXr  .  ...;s3G99XA&X88Shss11155hi.                             ,;:h&,
     iH8:  . ..   ,;iiii;,::,,,,,.                                 .;irHA
      ,8X5;   .     .......                                       ,;iihS8Gi
         1831,                                                 .,;irrrrrs&@
           ;5A8r.                                            .:;iiiiirrss1H
             :X@H3s.......                                .,:;iii;iiiiirsrh
              r#h:;,...,,.. .,,:;;;;;:::,...              .:;;;;;;iiiirrss1
             ,M8 ..,....,.....,,::::::,,...         .     .,;;;iiiiiirss11h
             8B;.,,,,,,,.,.....          .           ..   .:;;;;iirrsss111h
            i@5,:::,,,,,,,,.... .                   . .:::;;;;;irrrss111111
            9Bi,:,,,,......                        ..r91;;;;;iirrsss1ss1111
			...............我都不知道我写了啥------------------------------
*/

import java.util.*;

/**
 * @author lele
 * @version 1.0
 * @Description Map的实现类的结构：
 * |----Map:双列数据，存储key-value对的数据   ---类似于高中的函数：y = f(x)
 * |----HashMap:作为Map的主要实现类；线程不安全的，效率高；存储null的key和value
 * |----LinkedHashMap:保证在遍历map元素时，可以按照添加的顺序实现遍历。
 * 原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
 * 对于频繁的遍历操作，此类执行效率高于HashMap。
 * |----TreeMap:保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
 * 底层使用红黑树
 * |----Hashtable:作为古老的实现类；线程安全的，效率低；不能存储null的key和value
 * |----Properties:常用来处理配置文件。key和value都是String类型
 * <p>
 * <p>
 * HashMap的底层：数组+链表  （jdk7及之前）
 * 数组+链表+红黑树 （jdk 8）
 * @Email 414955507@qq.com
 * @date 2019/10/11 20:49
 */
public class MapDemo {
    public static void main(String[] args) {
        /*
         * Map对象在存储时是以Entry为单位的
         * key是无序、不可重复的， 使用Set集合存储
         * value是无序、可重复的， 使用Collection存储
         * 一个键值对（Key-Value）够成一个 Entry对象， Entry对象中有两个属性， 属性存的地址分别指向Key和Value
         * 由于Key都是无序、不可重复的， 所以一个Map集合中的Entry也是无序、不可重复的
         */

        /* Map常用方法， 以HashMap为例 */
        Map map = new HashMap();
        // 往map集合中添加一个key-value对
        // key通常使用 String或者其他对象，不建议使用基本数据类型（不符合特性啊， 不是对象没有equals方法）
        map.put("AA", 123);
        map.put("BB", 123);
        // 从底层源码分析， 这里相当于是一个替换操作
        map.put("AA", 123456);
        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("AA", 123);
        map.put("CC", 567);
        // 将一个Map集合的所有元素添加进来, 同样的有相同的元素会被替换
        map.putAll(map1);
        System.out.println(map);

        // 根据key移除元素
        map.remove("AA");
        System.out.println(map);

        // 清空Map集合, 注意依然是清空数据， 集合对象还存在
        map1.clear();
        System.out.println(map1);

        // 根据key获取集合中的元素
        System.out.println(map.get("AA"));

        // 判断集合是否存在指定的key
        System.out.println(map.containsKey("BB"));
        // 判断集合是否存在指定的value， 由于value可能重复， 所以只会返回第一个符合条件的元素
        System.out.println(map.containsValue(567));
        // 判断集合是否为空集合
        System.out.println(map.isEmpty());
        // 判断两个集合是否完全一致, 无关顺序
        System.out.println(map.equals(map1));

        /*
         * 元视图操作的方法, 迭代器是针对于List集合来说的, Map并不能使用迭代器
         * 可以通过调用Map的一个方法返回一个Set， 然后再使用迭代器, 但是注意这个Set集合是一个HashMap中的内部类， 所以最好用Set接口接收变量
         *      Set keySet -- 返回所有key构成的一个Set集合
         *      Collection values() -- 返回所有value构成的Collection集合
         *      Set entrySet() -- 返回所有key-value对构成的Set集合
         */
        // 返回所有key构成的一个Set集合
        Set set = map.keySet();
        System.out.println("Map返回的Set类：" + set.getClass());
        set.forEach(System.out::println);

        // 返回所有value构成的Collection集合
        Collection collection = map.values();
        collection.forEach(System.out::println);

        // 返回所有key-value对构成的Set集合, 元素类型是Map中的内部接口Entry， 所以使用时格式Map.Entry
        Set set1 = map.entrySet();
        set1.forEach(System.out::println);
        // 从Entry对象中寻找对应的key和value
        for (Object obj : set1) {
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
