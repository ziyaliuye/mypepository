package main.cn.cnm;

/*
_ooOoo_
o8888888o
88" . "88
(| -_- |)
O\ = /O
___/`---'\____
 .   ' \\| |// `.
        / \\||| : |||// \
 / _||||| -:- |||||- \
 | | \\\ - /// | |
        | \_| ''\---/'' | |
 \ .-\__ `-` ___/-. /
___`. .' /--.--\ `. . __
        ."" '< `.___\_<|>_/___.' >'"".
        | | : `- \`.;`\ _ /`;.`/ - ` : | |
 \ \ `-. \_ __\ /__ _/ .-` / /
 ======`-.____`-.___\_____/___.-`____.-'======
 `=---='
 .............................................
佛曰：bug泛滥，我已瘫痪！
*/

/**
 * @author lele
 * @version 1.0
 * @Description 线程通信：就是 wait / notify / notifyALl 通知其他线程
 * 示例为两个线程轮询打印内容， 一个线程执行完一次打印后wait， 然后另一个线程再执行， 再wait...
 * @Email 414955507@qq.com
 * @date 2019/10/7 18:10
 */
public class ThreadInteractDemo {
    public static void main(String[] args) {
        // 实现类实例化
        PollingPrint pollingPrint = new PollingPrint();
        // 实例化Thread类并将实现类对象传入构造器（run()实际调用对象为实现类对象）
        Thread thread1 = new Thread(pollingPrint);
        Thread thread2 = new Thread(pollingPrint);
        thread1.start();
        thread2.start();
    }
}

class PollingPrint implements Runnable {
    // 定义共享变量
    private int number = 1;

    @Override
    public void run() {
        while (true) {
            // 同步， 这里传this的原因是需要使用wait和notify方法， 其他对象来调会报错
            synchronized (this) {
                // 进来后先唤醒其他进入wait状态的线程， 由于现在已经拿到锁， 其他线程只能等待
                this.notify();

                if (number < 100) {
                    // 阻塞一下， 提高线程不安全几率
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(number);
                    number++;

                    // 执行完后进入wait， 并释放当前锁
                    // wait()和notify()只能通过同步监视器对象类调用， 否则报错
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}