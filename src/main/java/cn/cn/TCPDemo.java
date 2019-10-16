package src.main.java.cn.cn;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lele
 * @version 1.0
 * @Description TCP/IP协议簇
 * 传输层协议中有两个非常重要的协议：
 * 传输控制协议TCP(Transmission Control Protocol)
 * 用户数据报协议UDP(User Datagram Protocol)
 * TCP/IP 以其两个主要协议：传输控制协议(TCP)和网络互联协议(IP)而得名，实际上是一组协议，包括多个具有不同功能且互为关联的协议。
 * IP(Internet Protocol)协议是网络层的主要协议，支持网间互连的数据通信。
 * TCP/IP协议模型从更实用的角度出发，形成了高效的四层体系结构，即物理链路层、 IP层、传输层和应用层
 * @Email 414955507@qq.com
 * @date 2019/10/16 22:37
 */
public class TCPDemo {
    public static void main(String[] args) {
        /*
         * TCP协议：
         *  使用TCP协议前，须先建立TCP连接，形成传输数据通道
         *  传输前，采用“三次握手” 方式，点对点通信， 是可靠的
         *  TCP协议进行通信的两个应用进程：客户端、 服务端
         *  在连接中可进行大数据量的传输, 传输完毕，需释放已建立的连接， 效率低
         *
         *  UDP协议：
         *  将数据、源、目的封装成数据包， 不需要建立连接, 每个数据报的大小限制在64K内
         *  发送不管对方是否准备好，接收方收到也不确认， 故是不可靠的, 可以广播发送
         *  发送数据结束时无需释放资源，开销小，速度快
         */
        // 客户端发送消息
        new Thread(){
            @Override
            public void run() {
                TCPDemo tcpDemo = new TCPDemo();
                tcpDemo.client();
            }
        }.start();
        // 服务端接受消息
        server();
    }

    // 客户端
    private void client() {
        // 获取指定IP的域名（根据hosts、DNS的顺序获取）
        InetAddress inetAddress;
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            inetAddress = InetAddress.getByName("127.0.0.1");
            // 初始化一个Socket实例, 有多个构造器， 这里用的是域名和端口的构造器
            socket = new Socket(inetAddress, 8088);
            // 通过Socket实例获取一个输出流， 将指定字节向网络另一端输出
            outputStream = socket.getOutputStream();
            // 开始输出字节（另一端需要解码才能看到真实的内容）
            outputStream.write("hello ".getBytes());
            System.out.println("客户端发送一条消息：hello");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 服务端
    private static void server() {
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        Socket socket = null;
        try {
            // 初始化一个ServerSocket实例, 有多个构造器， 这里只需要端口号用于接收客户端发送的消息
            serverSocket = new ServerSocket(8088);
            // 将接收到的内容封装到一个Socket对象中
            socket = serverSocket.accept();
            // 通过Socket对象获取输入流
            inputStream = socket.getInputStream();
            // 定义一个字节数组输出流来处理输出（客户端发送过来的是字节， 如果长度超过定义的数组可能会乱码， 所以使用这个流来处理）
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                // 通过字节数组输出流来处理， 当读取文本的长度超过它内部定义的数组， 它会自动扩容而不会因数组长度太短导致乱码
                byteArrayOutputStream.write(bytes, 0, len);
                // 调用ByteArrayOutputStream的toString()方法将字节转换为字符
                System.out.println("获取来自IP" + socket.getInetAddress() + "的消息:" + byteArrayOutputStream.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}