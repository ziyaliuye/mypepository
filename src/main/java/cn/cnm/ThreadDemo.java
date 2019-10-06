package cn.cnm;

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

import javax.sound.midi.Soundbank;

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/6 17:34
 */
public class ThreadDemo {
    public static void main(String[] args) {
        // 初始化多线程对象
        MyThread myThread = new MyThread();
        // 调用对象的start()方法开始执行run()方法中的代码
        myThread.start();
        System.out.println("主线程执行了.....");
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
        int loopNum = 100;
        // 需要多线程执行的代码
        for (int i = 0; i < loopNum; i++) {
            System.out.println(i);
        }
    }
}

