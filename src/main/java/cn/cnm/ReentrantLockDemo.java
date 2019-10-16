package cn.cnm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lele
 * @version 1.0
 * @Description java.util.concurrent.locks.Lock接口 是控制多个线程对共享资源进行访问的工具， 锁提供了对共享资源的独占访问， 每次只能有一个线程对 Lock对象 加锁， 线程开始访问共享资源之前应先获得 Lock对象
 * ReentrantLock类 实现了 Lock， 它拥有与 synchronized 相同的并发性和内存语义， 在实现现场安全的控制中， 比较常用的就是 ReentrantLock， 可以显示加锁、释放锁
 * @Email 414955507@qq.com
 * @date 2019/10/7 16:51
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Window window = new Window();

        Thread thread1 = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Window implements Runnable {
    // 定义票数
    private int ticket = 100;

    // 定义一个ReentrantLoack对象， 同样的要注意定义的位置， 如果是继承Thread类实现多线的就需要定义static或者其他处理方式
    // 可以传入空参，fair（公平的）就默认为 false了， 传入true后则表示开启公平原则， 线程先进先出
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                // 加锁
                lock.lock();

                if (ticket > 0) {
                    // 提高出现安全问题几率
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + Thread.currentThread().getName() + " 卖出票号：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                // 不管怎么样都最终都执行一次解锁
                lock.unlock();
            }
        }
    }
}