package main.cnm;

/*
                       .::::.
                     .::::::::.
                    :::::::::::
                 ..:::::::::::'    美女保佑、永无Bug
              '::::::::::::'
                .::::::::::
           '::::::::::::::..
                ..::::::::::::.
              ``::::::::::::::::
               ::::``:::::::::'        .:::.
              ::::'   ':::::'       .::::::::.
            .::::'      ::::     .:::::::'::::.
           .:::'       :::::  .:::::::::' ':::::.
          .::'        :::::.:::::::::'      ':::::.
         .::'         ::::::::::::::'         ``::::.
     ...:::           ::::::::::::'              ``::.
    ```` ':.          ':::::::::'                  ::::..
                       '.:::::'                    ':'````..
*/

/**
 * @author lele
 * @version 1.0
 * @Description 多态性：可以理解为一个事务的多种形态
 * @Email 414955507@qq.com
 * @date 2019/10/3 19:28
 */
public class PolymorphicDemo {
    public static void main(String[] args) {
        Person person = new Person();
        //person.eat();
        // 多态的体现：父类的引用指向子类对象
        Person man = new Man();
        // 多态的使用：虚拟方法调用（执行的是子类重写父类方法之后的方法， 子类不重写父类方法没有意义）
        // 编译时看左边：看父类有哪些方法，调用父类不存在的方法当然编译也会不通过
        // 运行时看右边：取决于右侧具体是哪个对象， 会执行对象（子类）的方法
        //man.eat();

        A a = new B();
        a.dayin(1, 2, 3);

        B b = new B();
        b.dayin(1, 2, 3);
    }
}

class A {
    public void dayin(int i, int... j) {
        System.out.println("A");
    }
}

class B extends A {
    public void dayin(int i, int[] j) {
        System.out.println("B_1");
    }

    public void dayin(int i, int j, int y) {
        System.out.println("B_2");
    }
}

class Person {
    public void eat() {
        System.out.println("吃东西....");
    }
}

class Man extends Person {
    public void eat() {
        System.out.println("男人要多吃....");
    }
}

class Woman extends Person {
    public void eat() {
        System.out.println("女人要少吃....");
    }
}
