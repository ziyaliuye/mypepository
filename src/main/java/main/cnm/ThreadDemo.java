package main.cnm;

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
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/6 17:34
 */
public class ThreadDemo {
    public static void main(String[] args) {
        // 打印当前线程名
        String threadName = Thread.currentThread().getName();
        // 初始化多线程对象
        MyThread myThread = new MyThread();
        // 调用对象的start()方法开始启动一个新的线程并执行run()方法中的代码
        myThread.start();
        int loopNum = 100;
        // 需要多线程执行的代码
        for (int i = 0; i < loopNum; i++) {
            System.out.println(threadName + "：" + i);
        }

        // 创建一个Thread的匿名子类对象然后调用， 节省开发步骤
        new Thread() {
            @Override
            public void run() {
                // 设置当前线程的优先级
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                // 打印当前线程名
                String threadName = Thread.currentThread().getName();
                int loopNum = 100;
                // 需要多线程执行的代码
                for (int i = 0; i < loopNum; i++) {
                    System.out.println(threadName + "：" + i);
                }
            }
        }.start();
    }
}

/**
 * @author liu_jl
 * @version 1.0
 * @Description 多线程类示例， 新建一个类继承Thread
 * @Email 414955507@qq.com
 * @date 2019/10/6 17:38
 */
class MyThread extends Thread {

    @Override
    public void run() {
        // 设置当前线程的优先级
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        // 打印当前线程名
        String threadName = Thread.currentThread().getName();
        int loopNum = 100;
        // 需要多线程执行的代码
        for (int i = 0; i < loopNum; i++) {
            System.out.println(threadName + "：" + i);
        }
    }
}

