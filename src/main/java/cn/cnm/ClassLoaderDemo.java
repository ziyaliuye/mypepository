package cn.cnm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/10/18 16:27
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // 对于自定义类， 使用”系统类加载器“进行加载
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        // 打印第一层：类的加载(Load)， 它负责将类的class文件读入内存，并为之创建一个java.lang.Class对象。此过程由类加载器完成
        System.out.println(classLoader);
        // 打印第二层：类的链接(Link)， 它负责将类的二进制数据合并到JRE中
        ClassLoader classLoader_parent = classLoader.getParent();
        System.out.println(classLoader_parent);
        // 打印第三层：类的初始化(Initialize)， 它JVM负责对类进行初始化
        // 由于我们不能直接获取， 所以打印null， 并不意味着它不存在
        ClassLoader classLoader_parentParent = classLoader_parent.getParent();
        System.out.println(classLoader_parentParent);

        /* ClassLoader的其他应用， 它的getResourceAsStream()方法可以读取文件获取一个输入流 */
        // 使用FileInputStream读取文件
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("db.properties");
            properties.load(fileInputStream);
            System.out.println("URL：" + properties.getProperty("jdbc.url"));
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        properties.clear();

        // 使用ClassLoader的getResourceAsStream()方法读取文件
        try {
            // 从编译路径src目录下开始寻找, maven项目放入src/main/resources目录下
            properties.load(classLoader.getResourceAsStream("db.properties"));
            System.out.println(properties.getProperty("jdbc.url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
