package cn.cnm;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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
    public static void main(String[] args) throws IOException {
        /*
         * 路径的问题, 路径分隔符和系统有关：
         *  windows和DOS系统默认使用“ \”来表示， UNIX和URL使用“ /”来表示
         * 为了解决这个隐患， File类提供了一个常量：File.separator
         */
        // 用构造器一创建File的实例， 这时候只是创建了一个对象， 和实际盘符中的文件没有任何关系
        File file = new File("D:" + File.separator + "1.txt");

        // 构造器二创建一个目录
        File files = new File("D:", "files");

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
        // 获取文件长度（ 即：字节数） ，注意：不能获取目录的长度
        System.out.println(file.length());
        // 获取最后一次的修改时间， 毫秒值, 可以用Date转换
        System.out.println(new Date(file.lastModified()));

        /* 适用于文件目录的方法 */
        // 获取指定目录下的所有文件或者文件目录的名称数组
        String[] list = files.list();
        for (String s : list) {
            System.out.println(s);
        }

        // 获取指定目录下的所有文件或者文件目录的File数组
        File[] filesList = files.listFiles();
        for (File f : filesList) {
            System.out.println(f);
        }

        /* 把文件重命名为指定的文件路径（剪切文件到某一文件夹下并重命名）， 返回boolean类型操作结果 */
        System.out.println(file.renameTo(new File("D:" + File.separator + "files" + File.separator + "11.txt")) ? "移动并重命名文件成功..." : "移动并重命名文件失败...");

        /* 创建文件/目录, 如果创建文件或者文件目录没有写盘符路径，那么，默认在项目路径下 */
        // 在D盘下创建1.txt的文件
        file.createNewFile();
        // 在D盘下创建tmp目录， 并创建logs目录， 如果上层目录不存在则自动创建
        File file1 = new File("D:\\tmp\\logs");
        System.out.println(file1.mkdirs() ? "目录创建成功" : "目录创建失败");
        // 删除文件或目录
        System.out.println(file.delete() ? "文件删除成功" : "文件删除失败");

        /* 文件/目录的判断功能 */
        // 判断是否是文件目录
        System.out.println("file is " + (file.isDirectory() ? "目录" : "文件"));
        // 判断是否是文件
        System.out.println("file is " + (file.isFile() ? "文件" : "目录"));
        // 判断是否存在
        System.out.println("file8 is " + (file.exists() ? "存在" : "不存在"));
        // 判断是否可读
        System.out.println("file is read：" + (file.canRead() ? "可读" : "不可读"));
        // 判断是否可写
        System.out.println("file is write：" + (file.canWrite() ? "可写" : "不可写"));
        // 判断是否隐藏
        System.out.println("file is hidden：" + (file.isHidden() ? "隐藏" : "非隐藏"));
    }
}