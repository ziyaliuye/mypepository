package src.main.java.cn.cn;

/**
 * @author lele
 * @version 1.0
 * @Description 死锁示例， 在同一个锁的情况下不会发生
 * 多个线程执行时，锁的拥有者都需要对方的锁时就进入了死锁状态
 * @Email 414955507@qq.com
 * @date 2019/10/7 15:59
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        // 第一个锁对象
        StringBuffer sb1 = new StringBuffer("a");
        // 第二个锁对象
        StringBuffer sb2 = new StringBuffer("A");

        // 第一个线程执行 获取锁的顺序 1 =》 2
        new Thread(() -> {
            // 第一个进来的线程先拿到第一把锁
            synchronized (sb1) {
                sb1.append("b");
                sb2.append("B");

                // 线程暂停一会， 提高死锁几率
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 拿到第二把锁开始执行
                synchronized (sb2) {
                    sb1.append("c");
                    sb2.append("C");

                    System.out.println("Thread1:" + sb1);
                    System.out.println("Thread2:" + sb2);
                }
            }
        }).start();

        // 第二个线程执行 获取锁的顺序 2 =》 1
        new Thread(() -> {
            // 第一个进来的线程先拿到第二把锁
            synchronized (sb2) {
                sb1.append("b");
                sb2.append("B");

                // 线程暂停一会， 提高死锁几率
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 拿到第一把锁开始执行
                synchronized (sb1) {
                    sb1.append("c");
                    sb2.append("C");

                    System.out.println("Thread2:" + sb1);
                    System.out.println("Thread2:" + sb2);
                }
            }
        }).start();
    }
}
