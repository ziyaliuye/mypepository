package cn.cnm;

import javax.print.DocFlavor;

/**
 * @author lele
 * @version 1.0
 * @Description static 可以修饰 属性、方法、代码块， 被static修饰的内容在内存中只会有一份， 并且在程序启动时会生成
 * @Email 414955507@qq.com
 * @date 2019/10/4 15:52
 */
public class StaticDemo {
    public static void main(String[] args) {
        System.out.println(Chinese.nationality);
        // 静态结构在内存中只有一份， 当被修改后， 所有地方用到这个结构的地方就会跟着修改
        Chinese.nationality = "USA";
        System.out.println(Chinese.nationality);
    }
}

class Chinese {
    // static 修饰属性, 称为静态变量
    static String nationality = "china";
    // static 修饰方法
    // static 代码块
}