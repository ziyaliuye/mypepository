package main.cn.cnm;

/**
 * @author lele
 * @version 1.0
 * @Description 常用类使用示例一
 * @Email 414955507@qq.com
 * @date 2019/10/8 16:56
 */
public class CommonlyUserOnce {
    public static void main(String[] args) {
        // String对象创建
        String str1 = "hello";
        // 本质上 this.value = new char[0];
        String str2 = new String();
        // 本质上 this.value = "hello".value;
        String str3 = new String("hello");
        // 本质上 this.value = Arrays.copyOf(value, value.lenth)
        String str4 = new String(new char[]{'a', 'b', 'c'});
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
    }
}
