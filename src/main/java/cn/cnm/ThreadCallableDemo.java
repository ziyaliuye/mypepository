package cn.cnm;

/*
                       .::::.
                     .::::::::.
                    :::::::::::
                 ..:::::::::::'    美女保佑、永无Bug
              '::::::::::::'
                .::::::::::
           '::::::::::::::..
                ..::::::::::::.
              ``::::::::::::::::
               ::::``:::::::::'        .:::.
              ::::'   ':::::'       .::::::::.
            .::::'      ::::     .:::::::'::::.
           .:::'       :::::  .:::::::::' ':::::.
          .::'        :::::.:::::::::'      ':::::.
         .::'         ::::::::::::::'         ``::::.
     ...:::           ::::::::::::'              ``::.
    ```` ':.          ':::::::::'                  ::::..
                       '.:::::'                    ':'````..
*/

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lele
 * @version 1.0
 * @Description 多线程的创建方式三：实现 Callable接口， 与 Runnable 接口相比， Callable 功能更强大
 * 使用FutureTask获取Callable的结果
 * @Email 414955507@qq.com
 * @date 2019/10/7 21:29
 */
public class ThreadCallableDemo {
    public static void main(String[] args) {
        // 创建一个实现Callable接口的实例
        NumberCallable numberCallable;
        numberCallable = new NumberCallable();
        // 将实例交给Future接口的实心类FutureTask, 方便获取线程方法的返回值
        // 如果不想获取Callable的返回值， 则可以省略这一步， 直接将Callable的实现类对象给Thread
        // 泛型是返回值的类型
        FutureTask<Integer> futureTask;
        futureTask = new FutureTask<Integer>(numberCallable);
        // 创建Thread类准备启动线程, 将Future传入构造器
        Thread thread = new Thread(futureTask);
        thread.start();
        // 通过FutureTask获取返回值, get()返回值， 即为Callable实现类的返回值
        Integer sum = null;
        try {
            sum = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("最终执行结果：" + sum);

    }
}

// 实现Callable接口并重写call()方法
class NumberCallable implements Callable {
    @Override
    public Object call() {
        // 随便写点逻辑， 这里是求100以内偶数和
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}