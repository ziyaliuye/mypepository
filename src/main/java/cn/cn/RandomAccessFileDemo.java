package src.main.java.cn.cn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author lele
 * @version 1.0
 * @Description RandomAccessFile：随机数据读写流，RandomAccessFile 声明在java.io包下，但直接继承于java.lang.Object类
 * 它实现了DataInput、DataOutput 这两个接口, 也就意味着这个类既可以读也可以写
 * andomAccessFile 类支持 “随机访问” 的方式，程序可以直接跳到文件的任意地方来读、写文件支持只访问文件的部分内容, 也可以可以向已存在的文件后追加内容
 * @Email 414955507@qq.com
 * @date 2019/10/16 20:27
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) {
        /*
         * 初始化RandomAccessFile对象, 创建对象需要指定一个mode参数， 该参数指明访问方式：
         *      "r": 以只读方式打开
         *      "rw"：打开以便读取和写入
         *      "rwd":打开以便读取和写入；同步文件内容的更新
         *      "rws":打开以便读取和写入； 同步文件内容和元数据的更新
         */
        // 用于输入的流
        RandomAccessFile rafr = null;
        // 用于输出的流
        RandomAccessFile rafw = null;
        try {
            rafr = new RandomAccessFile("D:\\1.txt", "rw");
            rafw = new RandomAccessFile("D:\\2.txt", "rw");
            byte[] bytes = new byte[1024];
            int len;
            /*
             * 如果RandomAccessFile作为输出流
             * 它的特点之处是可以对文本文件指定部分内容进行覆盖，可以实现多线程断点下载的功能（其他流只能直接替换文件或者追加）：
             *      写出到的文件如果不存在则自动创建
             *      写出到的文件存在则默认在头部进行追加（原文件第一个字符前追加）
             *          通过getFilePointer()获取文件记录指针的当前位置
             *          通过seek()方法修改指针位置到指定的位置（索引位置）
             */
            while ((len = rafr.read(bytes)) != -1) {
                // 如果文件已经存在且有文本内容则将指针调到索引位置3上
                // 如果想覆盖原有内容只能用循环操作将原内容读取出来然后重新写入
                rafw.seek(3);
                rafw.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (rafr != null) {
                try {
                    rafr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (rafw != null) {
                try {
                    rafr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
