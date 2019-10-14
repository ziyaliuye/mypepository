package main.cn.cnm;

import java.io.File;

/**
 * @author lele
 * @version 1.0
 * @Description 关于File类的使用：
 * File 能新建、删除、重命名文件和目录，但 File 不能访问文件内容本身
 * File类的一个对象， 代表一个文件或文件目录
 * @Email 414955507@qq.com
 * @date 2019/10/14 17:20
 */
public class FileDemo {
    public static void main(String[] args) {
        /*
         * 路径的问题, 路径分隔符和系统有关：
         *  windows和DOS系统默认使用“ \”来表示， UNIX和URL使用“ /”来表示
         * 为了解决这个隐患， File类提供了一个常量：File.separator
         */
        // 用构造器一创建File的实例， 这时候只是创建了一个对象， 和实际盘符中的文件没有任何关系
        File file = new File("D:" + File.separator + "1.txt");

        // 构造器二创建一个目录
        File file2 = new File("D:", "files");

        // 构造器三在目录下创建一个文件
        File file3 = new File("D:files", "1.txt");

        /* File的常用方法 */
        // 获取文件/目录的绝对路径
        System.out.println("绝对路径" + file.getAbsolutePath());
        // 获取文件/目录的相对路径（如果文件在当前程序运行的目录及子目录下则输出全路径）
        System.out.println("相对路径" + file.getPath());
        // 获取名称
        System.out.println("文件名称" + file.getName());
        // 获取上层文件目录路径。 若无， 返回null
        System.out.println("文件上级路径" + file.getParent());
    }
}