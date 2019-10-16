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
             ...........这特么哪个大神写的代码.....................
*/

/**
 * @author lele
 * @version 1.0
 * @Description 接口是一个规范， 本质就是契约、标准、规范， 定义了一组的规则， 可以这么理解：继承是一个“是不是的关系”， 而接口则是“能不能的关系
 * JDK1.7及以前只能定义 全局常量（public static final xxx） 和 抽象方法（public abstract xxx）
 * JDK1.8以后：增加了静态方法、默认方法
 * @Email 414955507@qq.com
 * @date 2019/10/4 22:46
 */
public class InterfaceDemo {
    public static void main(String[] args) {
        // 创建接口的非匿名实现类的非匿名对象
        Flyable flyable = new Aircraft();
        flyable.fly();
        // 可以打印接口中的全局常量
        System.out.println(Flyable.SPEED);

        // 创建了接口的非匿名实现类的匿名对象（就是直接实例化实现类）
        new Aircraft().fly();

        // 创建接口的匿名实现类的非匿名对象
        Flyable flyableanonymous = new Flyable() {
            @Override
            public void fly() {
                System.out.println("匿名实现类飞....");
            }
        };
        flyableanonymous.fly();

        // 创建接口的匿名实现类的匿名对象
        new Flyable() {
            @Override
            public void fly() {
                System.out.println("匿名实现类飞....");
            }
        }.fly();

        // JDK1.8中静态、默认方法的使用
        NewCharacter newCharacter = new NewCharacterImpl();
        newCharacter.method2();
        // 接口的静态方法只能通过接口来调用， 不能通过实现类来调用
        NewCharacter.Method1();
    }
}

interface Flyable {
    // 定义全局常量, 默认修饰符 public static final
    int SPEED = 8000;

    // 省略修饰符， 默认就是 public abstract
    void fly();
}

class Aircraft implements Flyable {
    @Override
    public void fly() {
        System.out.println("飞机飞....");
    }
}

// 接口支持多重继承
interface AA {
    void a();
};

interface BB {
    void a();
};

interface CC extends AA, BB {
    void a();
};

// JDK1.8后接口可以定义静态方法、默认方法
interface NewCharacter {
    // 定义静态方法
    public static void Method1() {
        System.out.println("接口中的静态方法");
    }

    // 定义默认方法
    public default void method2() {
        System.out.println("接口中的默认方法");
    }
}

// 实现了结构的类也等同于有了接口中的默认方法, 但是没有静态方法
class NewCharacterImpl implements NewCharacter {

}