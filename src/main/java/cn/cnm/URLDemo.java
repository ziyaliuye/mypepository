package cn.cnm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author lele
 * @version 1.0
 * @Description URL类
 * URL(Uniform Resource Locator)：统一资源定位符，它表示Internet上某一资源的地址
 * 它是一种具体的URI，即URL可以用来标识一个资源，而且还指明了如何 locate （定位）这个资源
 * 通过URL我们可以访问Internet上的各种网络资源，比如最常见的 www、ftp 站点， 浏览器通过解析给定的URL可以在网络上查找相应的文件或其他资源
 * URL的基本结构由5部分组成：<传输协议>://<主机名>:<端口号>/<文件名>#片段名?参数列表
 * http://localhost:8080/examples/beauty.jpg?username=Tom
 * 协议   主机名    端口号  资源地址           参数列表
 * @Email 414955507@qq.com
 * @date 2019/10/17 13:48
 */
public class URLDemo {
    public static void main(String[] args) {
        // HttpURLConnection是URLConnection抽象类的一个子类
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 定义一个URL类指定一个具体的资源
            URL url = new URL("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=9fde2a60912f07084b082252884dd3fc/503d269759ee3d6d4df3bfe44c166d224f4adee3.jpg");
            // 返回一个URLConnection连接对象， 这里需要强转为HttpURLConnection
            urlConnection = (HttpURLConnection) url.openConnection();
            // 开始连接资源
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            // 准备将资源写到磁盘中
            fileOutputStream = new FileOutputStream("D:\\1.jpg");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
            System.out.println("图片下载成功");

            /* URL类的一些常用方法 */
            // public String getProtocol()  获取该URL的协议名
            System.out.println(url.getProtocol());
            // public String getHost()  获取该URL的主机名
            System.out.println(url.getHost());
            // public String getPort()  获取该URL的端口号
            System.out.println(url.getPort());
            // public String getPath()  获取该URL的文件路径
            System.out.println(url.getPath());
            // public String getFile()  获取该URL的文件名
            System.out.println(url.getFile());
            // public String getQuery() 获取该URL的查询名
            System.out.println(url.getQuery());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                // 关闭连接
                urlConnection.disconnect();
            }
        }
    }
}
