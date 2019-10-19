package cn.cnm;

/*
     ┏┛ ┻━━━━━┛ ┻┓
     ┃　　　　　　 ┃
     ┃　　　━　　　┃
     ┃　┳┛　  ┗┳　┃
     ┃　　　　　　 ┃
     ┃　　　┻　　　┃
     ┃　　　　　　 ┃
     ┗━┓　　　┏━━━┛
       ┃　　　┃   神兽保佑
       ┃　　　┃   永无BUG..
       ┃　　　┗━━━━━━━━━┓
       ┃　　　　　　　    ┣┓
       ┃　　　　         ┏┛
       ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
         ┃ ┫ ┫   ┃ ┫ ┫
         ┗━┻━┛   ┗━┻━┛
*/

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lele
 * @version 1.0
 * @Description 代理设计模式的原理:
 * 使用一个代理将对象包装起来, 然后用该代理对象取代原始对象。任何对原始对象的调用都要通过代理, 代理对象决定是否以及何时将方法调用转到原始对象上
 * 动态代理是指客户通过代理类来调用其它对象的方法，并且是在程序运行时根据需要动态创建目标类的代理对象, 使用场合：调试/远程方法调用
 * <p>
 * 抽象角色中（接口）声明的所有方法都被转移到调用处理器一个集中的方法中处理，我们可以更加灵活和统一的处理众多的方法
 * @Email 414955507@qq.com
 * @date 2019/10/19 18:01
 */
public class DynamicProxyDemo {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        // 代理类的对象, 只能用接口接口：
        // 因为第一不知道生成的类时什么， 第二这个类和SuperMan只是实现了同一个接口， 并不能相互转换
        Human human = (Human) ProxyFactory.getProxyInstance(superMan);
        // 调用“代理类”实例的方法其实内部就是调用的“被代理类”的方法， 只是从外部看不出来， 从而形成了一种保护
        human.eat();
        human.fly();
    }
}

/* 定义一个接口 */
interface Human {
    void eat();

    void fly();
}

/* 被代理类 */
class SuperMan implements Human {
    @Override
    public void eat() {
        System.out.println("吃屎吧...");
    }

    @Override
    public void fly() {
        System.out.println("i can fly...");
    }
}

/* 创建一个统一的生成代理类工厂 */
class ProxyFactory {
    // 形参就是“被代理类对象”， 返回值则是动态生成的“代理类对象”
    static Object getProxyInstance(Object obj) {
        // 创建“调用助手”的实例
        MyInvocationHandler handler = new MyInvocationHandler();
        /* 先将“被代理类”对象实例交给“调用助手”方便后面调用 */
        handler.bind(obj);
        /*
         * 调用JDK自带的Proxy工具类的newProxyInstance方法开始生成“代理对象
         * 参数和“调用助手”的invoke()一一对应
         *  参数一：调用的类加载器
         *  参数二：“被代理类”实现的接口， 这样“代理类”也会自动去实现这些接口
         *  参数三：我们自定义的“调用助手”的实例
         */
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

/* 创建一个调用助手， 实现JDK自带的InvocationHandler， 并重写invoke()方法 */
class MyInvocationHandler implements InvocationHandler {
    /* 定义一个属性， 用于绑定“被代理类”对象（就是用于调用“被代理类”的方法） */
    private Object obj;

    void bind(Object obj) {
        this.obj = obj;
    }

    /*
     * 参数一：调用的类加载器， 这里一般写“被代理类”类加载器
     * 参数二：“被代理类”实现的接口， 这样“代理类”也会自动去实现这些接口
     * 参数三：我们自定义的“调用助手”的实例
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /* 模拟Spring中的AOP:第二步 */
        HumanUitl humanUitl = new HumanUitl();

        humanUitl.before();
        /* 调用方法：调用者实际为“被代理对象”， 具体调用哪个方法和传递参数“调用助手”会做处理 */
        Object returnValue = method.invoke(obj, args);
        humanUitl.after();

        return returnValue;
    }
}

/* 模拟Spring中的AOP:第一步 */
class HumanUitl {
    // 调用“被代理类”方法之前调用的方法
    void before() {
        System.out.println("摆好Poss...");
    }

    // 调用“被代理类”方法之之后调用的方法
    void after() {
        System.out.println("拍拍肚子...");
    }
}

