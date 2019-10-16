package src.main.java.cn.cn;

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/7 15:12
 */
public class SyncMethodDemo {
    public static void main(String[] args) {
        TicketThread ticketThread1 = new TicketThread();
        TicketThread ticketThread2 = new TicketThread();
        TicketThread ticketThread3 = new TicketThread();
        ticketThread1.start();
        ticketThread2.start();
        ticketThread3.start();
    }
}

class TicketThread extends Thread {
    // 总共100张票， 静态方法想使用， 那么变量也必须是静态的
    private static int ticket = 100;

    // 同步方法, 只包裹需要的代码， 包多包少都会有问题
    private static synchronized void sell() {
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
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while (true) {
            // 调用卖票的方法
            sell();
        }
    }
}
