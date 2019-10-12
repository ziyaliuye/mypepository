package main.cn.cnm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author lele
 * @version 1.0
 * @Description 继承自Hashtable， 常用来处理Properties配置文件， Key-Value都是String类型
 * 存取数据时，建议使用setProperty(String key,String value)方法和getProperty(String key)方法
 * @Email 414955507@qq.com
 * @date 2019/10/12 17:08
 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        // 初始化一个Properties集合对象
        Properties properties = new Properties();
        // 初始化一个文件流， 加载指定的配置文件
        FileInputStream fileInputStream = new FileInputStream("db.properties");
        // 用集合对象加载流， 配置文件中的配置加载成一个个的key-value对
        properties.load(fileInputStream);
        // 通过key获取value
        String jdbcUrl = properties.getProperty("jdbc.url");
        System.out.println(jdbcUrl);
        // 关闭流
        fileInputStream.close();
    }
}
