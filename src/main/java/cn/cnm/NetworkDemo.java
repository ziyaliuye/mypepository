package cn.cnm;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lele
 * @version 1.0
 * @Description 网络编程的目的：
 * 直接或间接地通过网络协议与其它计算机实现数据交换，进行通讯
 * 一、网络编程中有两个主要的问题：
 * 1.如何准确地定位网络上一台或多台主机；定位主机上的特定的应用
 * 2.找到主机后如何可靠高效地进行数据传输
 * <p>
 * 二、网络编程中的两个要素：
 * 1.对应问题一：IP和端口号
 * 2.对应问题二：提供网络通信协议：TCP/IP参考模型（应用层、传输层、网络层、物理+数据链路层）
 * <p>
 * <p>
 * 三、通信要素一：IP和端口号
 * <p>
 * 1. IP:唯一的标识 Internet 上的计算机（通信实体）
 * 2. 在Java中使用InetAddress类代表IP
 * 3. IP分类：IPv4 和 IPv6 ; 万维网 和 局域网
 * 4. 域名:   www.baidu.com   www.mi.com  www.sina.com  www.jd.com
 * www.vip.com
 * 5. 本地回路地址：127.0.0.1 对应着：localhost
 * <p>
 * 6. 如何实例化InetAddress:两个方法：getByName(String host) 、 getLocalHost()
 * 两个常用方法：getHostName() / getHostAddress()
 * <p>
 * 7. 端口号：正在计算机上运行的进程。
 * 要求：不同的进程有不同的端口号
 * 范围：被规定为一个 16 位的整数 0~65535。
 * <p>
 * 8. 端口号与IP地址的组合得出一个网络套接字：Socket
 * @Email 414955507@qq.com
 * @date 2019/10/16 22:10
 */
public class NetworkDemo {
    public static void main(String[] args) {
        /*
         * InetAddress类：InetAddress类主要表示IP地址，两个子类：Inet4Address、 Inet6Address
         * InetAddress类对象含有一个Internet主机地址的域名和IP地址：www.laoda.com 和 192.168.168.168
         * 域名容易记忆，当在连接网络时输入一个主机的域名后，域名服务器(DNS)
         * 负责将域名转化成IP地址，这样才能和主机建立连接（域名解析）
         */
        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.3.136");
            System.out.println(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
