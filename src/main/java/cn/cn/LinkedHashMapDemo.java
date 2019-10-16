package src.main.java.cn.cn;

import java.util.LinkedHashMap;

/**
 * @author lele
 * @version 1.0
 * @Description 在原有的HashMap底层基础结构上， 添加了一对指针， 指向前一个和后一个元素， 对于有频繁的遍历操作， 此类的执行效率高于HashMap
 *      不管JDK1.7还是JDK1.8， LinkedHashMap底层都是叫Entry（JDK1.8的HashMap叫Node）
 * @Email 414955507@qq.com
 * @date 2019/10/12 14:45
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(123, "AA");
        linkedHashMap.put(234, "BB");
        linkedHashMap.put(012, "CC");
        System.out.println(linkedHashMap);
    }
}
