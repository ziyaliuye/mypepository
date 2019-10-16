package cn.cnm;

/**
 * @author lele
 * @version 1.0
 * @Description 多线程创建方式二：实现 Runnable接口, 此示例为卖票的例子
 * @Email 414955507@qq.com
 * @date 2019/10/6 22:12
 */
public class ThreadRunnableDemo {
    public static void main(String[] args) {
        // 创建一个实现类的对象
        MyRunnable myRunnable = new MyRunnable();
        // 创建多个Thread类的对象， 将实现类的对象传递到Thread的构造器中
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        Thread thread3 = new Thread(myRunnable);
        // 由Thread类来启动新的线程， 实际执行的是实现类的run()方法
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

// 创建一个类实现 Runnable接口并实现run()方法
class MyRunnable implements Runnable {
    // 总共100张票
    private int ticket = 100;
    // 随便定义一个对象（修饰符我随便加的， 不是必须）， 这个对象就可以充当锁， 注意定义位置不能定义在同步的代码块中
    // private static final Object ticket_lock = new Object();

    @Override
    public void run() {
        while (true) {
            // 同步代码块需要包裹住共享数据所涉及的所有代码, 实现Runnable接口的实现类只有一个， 所以this是唯一的
            // synchronized (this) {
            // 可以考虑使用 类对象 来充当锁， 类对象在整个程序运行中只会加载一次
            synchronized (MyRunnable.class) {
                if (ticket > 0) {
                    try {
                        // 此时休眠 100ms， 可能会出现错票的问题（别的线程将ticket--了）
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + Thread.currentThread().getName() + " 卖出票号：" + ticket);

                    try {
                        // 此时休眠 100ms， 可能会出现重票的问题（别的线程拿到ticket和现在输出是同一个）
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
