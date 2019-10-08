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
        // 通过 字面量赋值 String对象创建， 数据存储在常量池中
        String str1 = "hello";
        // 本质上 this.value = new char[0];  凡是new出来的对象都是存储在堆空间中
        String str2 = new String();
        // 本质上 this.value = "hello".value;
        String str3 = new String("hello");
        // 本质上 this.value = Arrays.copyOf(value, value.lenth)
        String str4 = new String(new char[]{'a', 'b', 'c'});
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);

        // 字符串字面量赋值和new方式的区别
        String str11 = "abc";
        String str12 = "abc";
        String str13 = new String("abc");
        String str14 = new String("abc");
        // 通过字面量赋值的字符串， 只会在常量池中新建一个对象存储真实数据
        System.out.println(str11 == str12);
        // 通过new关键字创建的字符串， 有两个对象产生
        // 会在堆中有一个new结构， 存的是地址， 地址指向常量池中的字符串存储的真实位置
        System.out.println(str13 == str14);

        // 字符串拼接过程中， 如果有对象参与， 那么拼接时字符串也会被当成对象类处理， 会在堆空间中生成一个new结构
        String s1 = "da";
        String s2 = "shabi";
        String s3 = "dashabi";
        // 只要有对象参与， 拼接后的结果还是存储在堆中（地址在堆中， 拼接后的真实数据还是在常量池）
        String s4 = s1 + "shabi";
        // 常量池与常量池的拼接结果还是在常量池
        String s5 = "da" + "shabi";
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        // 如果想拼接后的结果在常量池中， 就需要调用拼接后的对象的intern()方法将其强制放到常量池中
        String s6 = (s1 + "shabi").intern();
        System.out.println(s3 == s6);
    }
}
