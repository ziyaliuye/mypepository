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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author lele
 * @version 1.0
 * @Description I/O是Input/Output的缩写， I/O技术是非常实用的技术， 用于处理设备之间的数据传输。 如读/写文件，网络通讯（服务器与客户端之间数据传输也是IO）等
 * java.io 包下提供了各种“流”类和接口，用以获取不同种类的数据，并通过标准的方法输入或输出数据
 * 输入和输出是针对程序（内存， 程序运行在内存中）来说的
 * <p>
 * 流的角色：
 * 字节流：直接从数据源/目的地读取数据
 * 处理流：不直接连接数据源/目的地， 而是“连接”在已存在的流（节点流或处理流）之上， 通过对数据的处理为程序提供更为强大的读写功能
 * @Email 414955507@qq.com
 * @date 2019/10/14 22:55
 */
public class IODemo {
    /*
     *  IO涉及40多个类， 都是从4个抽象基类派生出来的：
     *   输入流：InputStream（字节流）/ Reader（字符流）
     *   输出刘：OutputStream（字节流）/ Write（字符流）
     *  派生的子类可以根据名称的"后半段"来区分是字节流（InputStream/OutputStream）还是字符流（Reader/Write）
     *
     *  抽象基类            节点流（或文件流）       缓冲流（处理流的一种）
     *  InputStream     FileInputStream         BufferedInputStream
     *  OutputStream    FileOutputStream        BufferedOutputStream
     *  Reader          FileReader              BufferedReader
     *  Writer          FileWriter              BufferedWriter
     */
    public static void main(String[] args) {
        /* 读取示例一 */
        // File类初始化， 指定好对应的文件
        File file = new File("D:\\11.txt");
        // FileReader流的初始化
        FileReader fileReader = null;
        /* 涉及流处理的资源最好手动try-catch， 不要将异常抛出， 否则可能会出现内存泄露问题 */
        try {
            // 准备将文件的内容输入（Input）到内存中， 文件不存在则会抛异常
            fileReader = new FileReader(file);
            // 开始读取, read()返回读取第一个字符， 如果达到文件末尾， 则返回-1
            //        int data = fileReader.read();
            //        // 如果读取为-1则终止循环， 说明文件已经读取完毕
            //        while (data != -1) {
            //            // 由于读取的是字符对应的ASCII码， 所以需进转换
            //            System.out.print((char) data);
            //            // 读完一个字符就继续往下读取
            //            data = fileReader.read();
            //        }
            /* 这种写法和注释的等价， 看起来简约一些而已 */
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 不管是输入还是输出流， 操作完后一定要手动关闭资源
            // 并且必须先判断对象是否为空， 否则IOException是抓不到NullPointException的
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\n==================================");

        /* 读取示例二, 在示例一的基础上升级， 因为每次读取一个字符就处理效率太低, 使用一个数组进行缓冲 */
        // 1：File类初始化， 指定好对应的文件
        File file1 = new File("D:\\11.txt");
        // 2：FileReader流的初始化
        FileReader fileReader1 = null;
        try {
            fileReader1 = new FileReader(file1);
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        }
        // 3：读入的操作
        // 先创建一个字符数组， 用于接收一定量的字符数据
        char[] chars = new char[5];
        try {
            // 将数组传入FileReader的构造器， 之后返回每次读取的字符个数， 如果到文件末尾则返回-1
            int len;
            /*
             * 断言：assert[boolean 表达式]
             *   如果[boolean表达式]为true，则程序继续执行
             *   如果为false，则程序抛出AssertionError，并终止执行
             * assert[boolean 表达式 : 错误表达式 （日志）]
             *   如果[boolean表达式]为true，则程序继续执行
             *   如果为false，则程序抛出java.lang.AssertionError，输出[错误信息]
             */
            assert fileReader1 != null;
            while ((len = fileReader1.read(chars)) != -1) {
                /*
                 * 错误写法一：每次读进来几个就取几个， 不能直接取数组长度， 这个数组相当于一个载体：
                 *  假设数组长度为5， 最后一次到文件末尾了只取了3个字符， FileReader只会将数组的前3个索引位置的内容替换
                 *  而最后2个字符仍然是上一轮的内容， 那么就会导致读取的数据多了最后两个字符
                 */
//                for (int i = 0; i < chars.length; i++) {
//                    System.out.print(chars[i]);
//                }

                // 错误写法二：错误的原因和错位一一致, 最后一次输出会有问题
//                String str = new String(chars);
//                System.out.print(str);

                /* 正确写法 */
//                for (int i = 0; i < len; i++) {
//                    System.out.print(chars[i]);
//                }
                // 初始化字符串时， 每次从数组中取指定长度
                String str = new String(chars, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader1 != null) {
                try {
                    // 4：资源关闭
                    fileReader1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
