package main.cn.cnm;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lele
 * @version 1.0
 * @Description JDK5.0提供了线程池相关的API：ExecutorService、Executors：
 * <p>
 * ExecutorService ：真正的线程池接口， 常用子类有 ThreadPoolExecutor
 * void execute(Runnable command)  执行任务/命令， 没有返回值， 一般用执行 Runnable
 * <T>Future<T>submit(Callable<T> task)    执行任务， 有返回值， 一般用来执行 Callable
 * void shutdown() 关闭连接池
 * <p>
 * Executors：工具类、线程池的工程类， 用于创建并返回不同类型的线程池
 * Executors.newCachedThreadPool()     创建一个可根据需要创建新线程的线程池
 * Executors.newFixedThreadPool(n)     创建一个可重用固定线程数的线程池
 * Executors..newSingleThreadExecutor() 创建一个只有一个线程的线程池
 * Executors..newScheduledThreadPool(n) 创建一个线程池， 它可以安排在给定的延迟后运行命令或者定期执行
 * @Email 414955507@qq.com
 * @date 2019/10/7 22:46
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 先使用工具类Executors创建一个需要的线程池， 这里拿固定数量线程的线程池举例
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 在线程池启动新线程之前可以设置一些属性
        // 因为接口是没有设置属性的方法的， 只能通过它的子类， 例如ThreadPoolExecutor
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) service;
        threadPoolExecutor.setCorePoolSize(15);

        // 适用于Runnable
        service.execute(new NumberThread());
        // 想要多个线程就直接调用多次即可， 具体的线程安全还是和之前一样进行设置
        service.execute(new NumberThread());
        // 适用于Callable
        // service.submit();
        // 关闭线程池， 正常情况下是一直开着的
        service.shutdown();
    }
}

// 创建Runnable的实现类指定具体要干什么
class NumberThread implements Runnable {
    @Override
    public void run() {
        // 随便写点逻辑， 这里是求100以内偶数和
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "：" + i);
            }
        }
    }
}
