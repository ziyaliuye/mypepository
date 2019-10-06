package main.cnm;

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

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println("线程：" + Thread.currentThread().getName() + " 卖出票号：" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}
