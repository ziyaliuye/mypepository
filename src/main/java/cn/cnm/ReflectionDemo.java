package cn.cnm;

/*
                                       ,s555SB@@&
                                     :9H####@@@@@Xi
                                    1@@@@@@@@@@@@@@8
                                  ,8@@@@@@@@@B@@@@@@8
                                 :B@@@@X3hi8Bs;B@@@@@Ah,
            ,8i                  r@@@B:     1S ,M@@@@@@#8;
           1AB35.i:               X@@8 .   SGhr ,A@@@@@@@@S
           1@h31MX8                18Hhh3i .i3r ,A@@@@@@@@@5
           ;@&i,58r5                 rGSS:     :B@@@@@@@@@@A
            1#i  . 9i                 hX.  .: .5@@@@@@@@@@@1
             sG1,  ,G53s.              9#Xi;hS5 3B@@@@@@@B1
              .h8h.,A@@@MXSs,           #@H1:    3ssSSX@1
              s ,@@@@@@@@@@@@Xhi,       r#@@X1s9M8    .GA981
              ,. rS8H#@@@@@@@@@@#HG51;.  .h31i;9@r    .8@@@@BS;i;
               .19AXXXAB@@@@@@@@@@@@@@#MHXG893hrX#XGGXM@@@@@@@@@@MS
               s@@MM@@@hsX#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,
             :GB@#3G@@Brs ,1GM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B,
           .hM@@@#@@#MX 51  r;iSGAM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@8
         :3B@@@@@@@@@@@&9@h :Gs   .;sSXH@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:
     s&HA#@@@@@@@@@@@@@@M89A;.8S.       ,r3@@@@@@@@@@@@@@@@@@@@@@@@@@@r
  ,13B@@@@@@@@@@@@@@@@@@@5 5B3 ;.         ;@@@@@@@@@@@@@@@@@@@@@@@@@@@i
 5#@@#&@@@@@@@@@@@@@@@@@@9  .39:          ;@@@@@@@@@@@@@@@@@@@@@@@@@@@;
 9@@@X:MM@@@@@@@@@@@@@@@#;    ;31.         H@@@@@@@@@@@@@@@@@@@@@@@@@@:
  SH#@B9.rM@@@@@@@@@@@@@B       :.         3@@@@@@@@@@@@@@@@@@@@@@@@@@5
    ,:.   9@@@@@@@@@@@#HB5                 .M@@@@@@@@@@@@@@@@@@@@@@@@@B
          ,ssirhSM@&1;i19911i,.             s@@@@@@@@@@@@@@@@@@@@@@@@@@S
             ,,,rHAri1h1rh&@#353Sh:          8@@@@@@@@@@@@@@@@@@@@@@@@@#:
           .A3hH@#5S553&@@#h   i:i9S          #@@@@@@@@@@@@@@@@@@@@@@@@@A.
             ................好烦啦.....................
*/

/**
 * @author lele
 * @version 1.0
 * @Description Reflection（反射）是被视为动态语言的关键，反射机制允许程序在执行期间借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法
 * 加载完类之后， 在堆内存的方法区中就产生了一个Class类型的对象（一个类只有一个Class对象） ， 这个对象就包含了完整的类的结构信息
 * 我们可以通过这个对象看到类的结构。 这个对象就像一面镜子， 透过这个镜子看到类的结构， 所以， 我们形象的称之为： 反射
 * <p>
 * Java不是动态语言， 但Java可以称之为“ 准动态语言” 。 即Java有一定的动态性， 我们可以利用反射机制、 字节码操作获得类似动态语言的特性。
 * Java 的动态性让编程的时候更加灵活！
 * @Email 414955507@qq.com
 * @date 2019/10/18 14:30
 */
public class ReflectionDemo {
    /*
     * 通过new的方式和反射的方式都可以调用类的公共结构， 一般情况下直接使用new的方式， 除非在编写时不确定要new哪个类的对象就需要用到反射
     * 反射机制和面向对象中的封装性是不矛盾的：
     *  封装性更多的是告诉他人， 私有的结构不建议调用，单例模式情况下只能有一个实例存在
     *  而反射机制是能不能拿到私有属性和创建对象实例的问题， 它能直接读取私有的结构和创建实例， 但不建议这么做
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        /*
         * java.lang.Class  称为反射的源头， 反射的操作第一步都是通过创建它来实现
         * 关于java.lang.Class类的理解（在Java中万物皆对象）：
         *  类的加载过程：
         *      代码经过javac.exe命令以后， 会生成一个或多个class文件（字节码文件）
         *      然后使用java.exe对字节码文件进行解释和运行， 相当于将字节码文件加载进了内存中（加载的过程就称为“类的加载”过程）
         *      加载进内存的类， 此时称为“运行时类”， 它就作为“Class的一个实例”（每个类只有一个）
         *          例如Person.class文件加载进行内存后， 它就充当Person类的一个实例
         *          由于语法限制不能直接使用Person（直接写Person只是表示一个类型）来表示它， 所以给”运行时类“增加了一个属性class, 这个属性就表示这个“运行时类”（Person.class）
         *  java.lang.Class类的一个实例就对应着一个“运行时类”
         */

        // 获取Class的实例方式一：调用运行时类的属性 .class  (编译时就写死了代码， 不常用， 反射是运行时获取)
        Class<PersonTest> clazz1 = PersonTest.class;
        System.out.println("方式一：" + clazz1);
        // 获取Class的实例方式二：通过运行时类的对象getClass()方法返回“运行时类” (不常用， 既然都实例化对象了， 还用什么反射)
        PersonTest p1 = new PersonTest("狗蛋", 123);
        Class clazz2 = p1.getClass();
        System.out.println("方式二：" + clazz2);
        // 获取Class的实例方式三：调用Class类的静态方法forName(String classPath)  (常用， 运行时获取”运行时类“)
        Class clazz3 = Class.forName("cn.cnm.PersonTest");
        System.out.println("方式三：" + clazz3);
        // 获取Class的实例方式四：使用类的加载器ClassLoader （不常用）
        ClassLoader classLoader = ReflectionDemo.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("cn.cnm.PersonTest");
        System.out.println("方式四：" + clazz4);
        /* 加载进内存中的”运行时类“会缓存一段时间， 一个类只有一个“运行时类”，不管通过哪种方式获取的“运行时类”， 都是同一个 */
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);
        System.out.println(clazz3 == clazz4);
        System.out.println(clazz2.getConstructor(String.class, int.class));

        /*
         * 以下类型均可以有Class对象：
         *（1） class：外部类， 成员(成员内部类， 静态内部类)， 局部内部类， 匿名内部类
         *（2） interface： 接口
         *（3） []：数组
         *（4） enum：枚举
         *（5） annotation：注解@interface
         *（6） primitive type：基本数据类型
         *（7） void
         */
    }
}
