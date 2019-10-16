package cn.cnm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lele
 * @version 1.0
 * @Description 其他流的使用
 * @Email 414955507@qq.com
 * @date 2019/10/16 15:18
 */
public class OtherIODemo {
    public static void main(String[] args) {
        /*
         * System.in 标准的输入流， 默认从键盘输入（InputStream）
         * System.out 标准的输出流， 默认从控制台输出（PrintStream打印流）
         * 可以通过 setIn(InputStream is) / setOut(PrintStream ps) 方法重新指定输入、输出的流
         *
         * 打印流：PrintStream、PrintWriter
         *
         *  为了方便地操作Java语言的基本数据类型和String的数据，可以使用数据流。
         *  数据流有两个类： (用于读取和写出基本数据类型、 String类的数据）
         *	DataInputStream 和 DataOutputStream 分别“套接”在 InputStream 和 OutputStream 子类的流上
         *
         *
         * Demo：从键盘输入字符串， 将其转为大写输出，知道输入'e'或'exit'时退出程序
         * 1：可以使用Scanner实现，（底层就是System.in）简单
         * 2：使用System.in实现
         */
        // System.in获取到的是一个InputStream， 需要转换为Reader
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            // 每次获取一行输入
            String data;
            try {
                System.out.println("请输入要转换成大写的字符串：");
                data = bufferedReader.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    System.exit(0);
                }
                // 将输入的字符串转为大写
                System.out.println(data.toUpperCase());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
