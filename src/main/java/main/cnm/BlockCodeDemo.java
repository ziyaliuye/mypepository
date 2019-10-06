package main.cnm;

/**
 * @author lele
 * @version 1.0
 * @Description 代码块（Block），也叫初始化块，是类的一种重要结构，类似于构造器的替代品， 通常是用来初始化对象
 * 一个类中可以定义多个静态代码块和非静态代码块， 静态代码块要比非静态先执行， 其他的则按上下顺序来决定执行顺序
 * @Email 414955507@qq.com
 * @date 2019/10/4 17:40
 */
public class BlockCodeDemo {
    public static void main(String[] args) {
        System.out.println(BlockCode.stackStr);
        BlockCode blockCode = new BlockCode();
        BlockCode blockCode1 = new BlockCode();
    }
}

class BlockCode {
    public BlockCode() {
        System.out.println("无参构造器执行...");
    }

    public String str;
    public static String stackStr;

    // 代码块
    // 随着对象的创建而执行, 每创建一个对象就会执行一次代码块的内容
    {
        System.out.println("Block code...");
        str = "非静态代码块测试类...";
    }

    // 可以使用static修饰， 被static修饰后的的代码块叫做静态代码块
    // 随着类的加载而执行（加载就是类的第一次被调用静态资源或第一次实例化对象时）
    // 并且在整个程序运行过程中只会被执行一次
    static {
        System.out.println("Static Block code...");
        stackStr = "静态代码块测试类...";
    }
}
