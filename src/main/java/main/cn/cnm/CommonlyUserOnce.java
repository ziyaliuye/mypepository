package main.cn.cnm;

import javax.sound.midi.Soundbank;

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

        // String的常用方法
        String s10 = "HelloWorld";
        // 返回字符串长度
        System.out.println(s10.length());
        // 返回某索引（从0开始， 注意获取不存在的字符会数组越界）处的字符
        System.out.println(s10.charAt(0));
        // 判断是否是空字符串
        System.out.println(s10.isEmpty());
        // 使用默认语言环境， 将String中所有字符转换为小写
        System.out.println(s10.toLowerCase());
        // 使用默认语言环境， 将String中所有字符转换为大写
        System.out.println(s10.toUpperCase());
        // 返回字符串的副本， 忽略前后的空格（中间的不去）
        System.out.println(s10.trim());
        // 对比字符串字符是否一样
        System.out.println("HelloWorld".equals(s10));
        // 忽略大小写对比字符串字符是否一样
        System.out.println("helloworld".equalsIgnoreCase(s10));
        // 拼接字符串串， 等价与 +
        System.out.println(s10.concat("...."));
        // String实现了compareTo方法，可以进行字符串大小比较, 正数表示大于/负数表示小于/0表示相等
        // 使用ASCII码值比， 一旦有一处比较后不等于0， 则返回这一处的差值，直到比完所有字符
        System.out.println(s10.compareTo("hEeloWorld"));
        // 返回一个新的字符串， 它是次字符串从 beginIndex开始截取， 一直截取到最后
        System.out.println(s10.substring(3));
        // 返回一个新的字符串， 它是次字符串从 beginIndex开始截取， 截取到endIndex(不包含这个索引的字符)
        // [beginIndex, endIndex)
        System.out.println(s10.substring(3, 5));

        // 判断字符串是否以指定字符串结尾
        System.out.println(s10.endsWith("ld"));
        // 判断字符串是否以指定字符串开头
        System.out.println(s10.startsWith("He"));
        // 判断字符串从指定索引位置开始是否以指定字符串开头
        System.out.println(s10.startsWith("lo", 3));
        // 判断字符串中是否包含指定字符串
        System.out.println(s10.contains("lo"));
        // 返回字符串中指定元素第一次出现的索引值, 找不到返回-1（从左往右找）
        System.out.println(s10.indexOf("lo"));
        // （重载）返回字符串中指定索引后的字符串， 指定元素第一次出现的索引值, 找不到返回-1（从左往右找）
        System.out.println(s10.indexOf("lo", 3));
        // 返回字符串中指定索引后的字符串，注意索引位置还是按左边的第一个字符索引值返回（从右往左找）
        System.out.println(s10.lastIndexOf("lo"));
        // （重载）返回字符串中指定索引后的字符串， 指定元素第一次出现的索引值, 找不到返回-1（从左往右找）
        System.out.println(s10.lastIndexOf("lo", 3));

        // 将字符串指定字符串（所有符合条件的字符）替换成指定字符串， 返回一个新的字符串
        // 注意这个方法有重载， 都是两个参数， 只不过参数的类型不一样
        System.out.println(s10.replace("o", "l"));
        // 使用给定的字符串， 替换字符串中所有匹配的正则表达式的字符串， 最终返回一个新的字符串
        System.out.println(s10.replaceAll("o", "l"));
        // 使用给定的字符串， 替换字符串中第一个匹配的正则表达式的字符串， 最终返回一个新的字符串
        System.out.println(s10.replaceFirst("o", "l"));
        // 告知此字符串是否匹配指定正则表达式
        System.out.println(s10.matches("\\s"));

        // 根据给定的正则表达式匹配拆分字符串, 返回一个字符串数组
        String[] s10s = s10.split("o");
        for (String tmp : s10s) {
            System.out.println(tmp);
        }
        System.out.println("============");
        // 根据给定的正则表达式匹配拆分字符串， 返回的数组长度最多不超过指定个数， 超过后索引位置后的字符串不拆分
        s10s = s10.split("o", 2);
        for (String tmp : s10s) {
            System.out.println(tmp);
        }


    }
}
