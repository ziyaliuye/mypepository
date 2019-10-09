package main.cn.cnm;

/**
 * @author lele
 * @version 1.0
 * @Description 三者底层都是字符数组， 后两者没有将字符数组声明为 final， 每次操作完（例如 replace()）会将自身的数组内容改变并且后两者的字符数组并不是声明是字符串的长度， 例如 abc，用String存是3位， 使用StringBuffer会定义一个比声明的数组长度多16的数组， 只是将abc放到前3位
 * String          不可变的字符序列，
 * StringBuffer    可变的字符序列， 线程安全（源码加了 synchronized ）， 但是效率低， 多线程场景下使用
 * StringBuilder   可变的字符序列， 线程不安全， 但是效率高， 单线程场景下使用
 * 当 StringBuffer和StringBuilder在append时长度不够， 会自动根据字符串长度扩容（将现有的数组克隆一份， 创建一个新的数组）， 默认是再扩孔16个长度， 当字符串长度很长时则直接用字符串的长度当成数组长度， 所以实际开发中尽量避免扩孔， 效率会降低
 * @Email 414955507@qq.com
 * @date 2019/10/9 9:51
 */
public class StringBufferBuilderDemo {
    public static void main(String[] args) {
        // 源码分析
        String str = new String();  // char[] value = new char[0];
        String str1 = new String("abc"); // char[] value = new char[3]{'a', 'b', 'c'};

        StringBuffer sb1 = new StringBuffer();  // char[] value = new char[16];  无参构造器默认会创建长度16的数组
        sb1.append('a');  // value[0] = 'a' 往数组中的第一个元素赋值
        sb1.append('b');  // value[1] = 'b' 往数组中的第二个元素赋值
        // StringBuffer重写了length()方法， 返回的是一个计数器的值， 也就是实际字符串的长度值
        System.out.println(sb1.length());

        StringBuffer sb2 = new StringBuffer("abc");  // char[] value = new char["abc".lenth() + 16];  数组长度会比声明的字符串长度多16
        // StringBuffer重写了length()方法， 返回的是一个计数器的值， 也就是实际字符串的长度值
        System.out.println(sb2.length());

        // 使用StringBuffer和StringBuilder的时候要注意避免扩容， 在声明时手动指定长度
        StringBuffer sb3 = new StringBuffer(1024);
        sb3.append("aaaaabbbbbbbbbbbbbbcccccccccccdddddddddd");
        System.out.println(sb3);

        // StringBuffer和StringBuilder常用方法（两者方法基本一致）, StringBuffer拥有String的方法， 还额外添加了一些方法
        StringBuffer sb = new StringBuffer(1024);
        // 在原有字符串尾部添加内容
        sb.append("dashabi");
        sb.append("jiushini");
        System.out.println(sb);
        // 删除指定索引位置到指定位置的字符串， 左闭右开, 不包含end：[begin, end)
        sb.delete(2, 7);
        System.out.println(sb);
        // 替换字符串 [begein, end) 中的内容替换为指定字符串
        sb.replace(0, 2, "xiao");
        System.out.println(sb);
        // 在指定索引位置前插入给定字符串
        sb.insert(4, "shabi");
        System.out.println(sb);
        // 将字符串逆转, 字符串本身的值也就改变了
        sb.reverse();
        System.out.println(sb);
        sb.reverse();
        // 返回指定字符串首次出现的位置（索引）, 没有则返回-1
        System.out.println(sb.indexOf("xiao"));
        // 返回字符串长度（实际内容长度）， StringBuffer对象中有一个计数器
        System.out.println(sb.length());
        // 返回指定索引位置字符
        System.out.println(sb.charAt(0));
        // 从指定索引位置一直到最后， 返回一个新的字符串
        System.out.println(sb.substring(4));
        // 从begin索引位置到end索引位置 [begin, end)， 返回一个新的字符串
        System.out.println(sb.substring(4, 9));
        // 替换指定索引的字符， 注意这个方法返回值是void， 不能直接在放在输出语句里
        sb.setCharAt(8, 'b');
        System.out.println(sb);
    }
}
