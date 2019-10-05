package cn.cnm;

/*
      ┌─┐       ┌─┐ + +
   ┌──┘ ┴───────┘ ┴──┐++
   │                 │
   │       ───       │++ + + +
   ███████───███████ │+
   │                 │+
   │       ─┴─       │
   │                 │
   └───┐         ┌───┘
       │         │
       │         │   + +
       │         │
       │         └──────────────┐
       │                        │
       │                        ├─┐
       │                        ┌─┘
       │                        │
       └─┐  ┐  ┌───────┬──┐  ┌──┘  + + + +
         │ ─┤ ─┤       │ ─┤ ─┤
         └──┴──┘       └──┴──┘  + + + +
        前方高能
        BUG闪开...
*/

/**
 * @author lele
 * @version 1.0
 * @Description 内部类不要和匿名类搞混， 当一个事务的内部， 还有一个部分需要一个完整的结构进行描述， 而这个内部的完整结构又只为外部事务提供服务， 那么整个内部的完整机构最好使用内部类
 * 内部类分类：成员内部类（static 成员内部类和 非static 成员内部类）、局部内部类（不谈修饰符）、匿名内部类
 * 说白就是看内部类定义的位置和修饰符（是否为 static ）
 * @Email 414955507@qq.com
 * @date 2019/10/5 15:22
 */
public class InnerClassDemo {
    /*
     * @author liu_jl
     * @Description 内部类和普通类一样可以正常定义属性和方法， 也可以继承类和实现接口，内部类和外部类可以相互使用属性和方法（不管是不是私有的都可以使用）， 使用方式：
                    外部类直接使用内部类属性和方法：内部类名.this.xxx
                    内部类直接使用外部类属性和方法：
                        属性和方法没有重名时可以直接写法属性和方法
                        内部类和外部类属性有重名时 外部类名.this.xxx  ，如果直接用则默认使用内部类自身的属性和方法
     * @version 1.0
     * @Email 414955507@qq.com
     * @date 2019/10/5 15:48
     */
    private String externalStr = "外部类属性";
    public String name = "外部类";
    public static final String Identification = "标识";

    private void externalMethod() {
        System.out.println("外部类方法");
        // 外部类不能直接使用内部类的属性和方法
        // System.out.println(InnerPerson.this.FINALSTR);
        // System.out.println(InnerStaticPerson.this.FINALSTR);
        // System.out.println(innerStr);
        // System.out.println(InnerStaticPerson.this.FINALSTR);
    }

    // 外部类不能调用内部类的任何结构


    // 成员内部类
    class InnerPerson {
        public String innerStr = "成员内部类属性";
        private String name = "外部类";

        // 成员内部类的方法可以调用外部类属性和方法（还没实例化呢）
        private void innerMethod() {
            System.out.println(externalStr);
            externalMethod();

            // 当内部类属性和外部类属性重名时， 默认是 this.属性， 也就是内部类的属性为准
            // 想使用外部类属性时使用 外部类.this.属性 来调用
            System.out.println(name);
            System.out.println(InnerClassDemo.this.name);
        }
    }

    // 静态成员内部类
    static class InnerStaticPerson {
        public String innerStaticstr = "静态成员内部类属性";
        public final String FINALSTR = "静态成员内部类常量";

        // 静态成员内部类的方法不能调用外部类属性和方法（还没实例化呢）
        private void innerStaticMethod() {
            // System.out.println(externalStr);
            // externalMethod();
        }
    }
}

// 内部类的一般使用场景：在一个方法中返回一个实现了某个接口的对象
class InnerClassExample {
    public Flyable fly() {
        // 创建一个内部类， 实现了某个接口
        class SuperMan implements Flyable {
            @Override
            public void fly() {
                System.out.println("fly....");
            }
        }
        // 返回一个实例
        return new SuperMan();
    }
}

// 创建静态成员内部类 和 非静态成员内部类 的对象
class InnerClassTest {
    public static void main(String[] args) {
        // 调用 InnerClassDemo 的静态内部类 InnerStaticPerson 的属性和方法
        // 语法：外部类名.静态内部类名 变量名 = new 外部类名.静态内部类名();  然后直接使用变量来调用结构
        InnerClassDemo.InnerStaticPerson innerStaticPerson = new InnerClassDemo.InnerStaticPerson();
        System.out.println(innerStaticPerson.FINALSTR);
        // 调用 InnerClassDemo 的非静态内部类 InnerPerson 的属性和方法
        // 语法
        //      1：需要先实例化外部类， 赋给一个变量1
        //      2：外部类名.非静态内部类名 变量2 = 外部类实例.new 非静态内部类名();
        //      3：通过调用 变量2 来调用非静态内部类的结构
        InnerClassDemo innerClassDemo = new InnerClassDemo();
        InnerClassDemo.InnerPerson InnerPerson = innerClassDemo.new InnerPerson();
        System.out.println(InnerPerson.innerStr);
    }
}