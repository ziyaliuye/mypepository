package cn.cnm;

/*
                                       ,s555SB@@&
                                     :9H####@@@@@Xi
                                    1@@@@@@@@@@@@@@8
                                  ,8@@@@@@@@@B@@@@@@8
                                 :B@@@@X3hi8Bs;B@@@@@Ah,
            ,8i                  r@@@B:     1S ,M@@@@@@#8;
           1AB35.i:               X@@8 .   SGhr ,A@@@@@@@@S
           1@h31MX8                18Hhh3i .i3r ,A@@@@@@@@@5
           ;@&i,58r5                 rGSS:     :B@@@@@@@@@@A
            1#i  . 9i                 hX.  .: .5@@@@@@@@@@@1
             sG1,  ,G53s.              9#Xi;hS5 3B@@@@@@@B1
              .h8h.,A@@@MXSs,           #@H1:    3ssSSX@1
              s ,@@@@@@@@@@@@Xhi,       r#@@X1s9M8    .GA981
              ,. rS8H#@@@@@@@@@@#HG51;.  .h31i;9@r    .8@@@@BS;i;
               .19AXXXAB@@@@@@@@@@@@@@#MHXG893hrX#XGGXM@@@@@@@@@@MS
               s@@MM@@@hsX#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,
             :GB@#3G@@Brs ,1GM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B,
           .hM@@@#@@#MX 51  r;iSGAM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@8
         :3B@@@@@@@@@@@&9@h :Gs   .;sSXH@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:
     s&HA#@@@@@@@@@@@@@@M89A;.8S.       ,r3@@@@@@@@@@@@@@@@@@@@@@@@@@@r
  ,13B@@@@@@@@@@@@@@@@@@@5 5B3 ;.         ;@@@@@@@@@@@@@@@@@@@@@@@@@@@i
 5#@@#&@@@@@@@@@@@@@@@@@@9  .39:          ;@@@@@@@@@@@@@@@@@@@@@@@@@@@;
 9@@@X:MM@@@@@@@@@@@@@@@#;    ;31.         H@@@@@@@@@@@@@@@@@@@@@@@@@@:
  SH#@B9.rM@@@@@@@@@@@@@B       :.         3@@@@@@@@@@@@@@@@@@@@@@@@@@5
    ,:.   9@@@@@@@@@@@#HB5                 .M@@@@@@@@@@@@@@@@@@@@@@@@@B
          ,ssirhSM@&1;i19911i,.             s@@@@@@@@@@@@@@@@@@@@@@@@@@S
             ,,,rHAri1h1rh&@#353Sh:          8@@@@@@@@@@@@@@@@@@@@@@@@@#:
           .A3hH@#5S553&@@#h   i:i9S          #@@@@@@@@@@@@@@@@@@@@@@@@@A.
             ..............抽死你吖的.....................
*/

import java.net.*;

/**
 * @author lele
 * @version 1.0
 * @Description UDP网络通信：
 * DatagramSocket和DatagramPacket实现了基于UDP协议网络程序（datagram ：数据报）
 * UDP数据报通过数据报套接字 DatagramSocket 发送和接收， 系统不保证UDP数据报一定能够安全送到目的地，也不能确定什么时候可以抵达。
 * DatagramPacket 对象封装了UDP数据报，在数据报中包含了发送端的IP地址和端口号以及接收端的IP地址和端口号。
 * UDP协议中每个数据报都给出了完整的地址信息，因此无须建立发送方和接收方的连接。 如同发快递包裹一样
 * @Email 414955507@qq.com
 * @date 2019/10/17 11:43
 */
public class UDPDemo {
    public static void main(String[] args) throws Exception {
        UDPDemo udpDemo = new UDPDemo();
        new Thread(() -> {
            try {
                udpDemo.receiver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        // 保证客户端先启动
        Thread.sleep(1000);
        udpDemo.send();
    }

    // 发送端
    private void send() throws Exception {
        DatagramSocket datagramSocket;
        String message = "hello...sb....";
        InetAddress inetAddress = InetAddress.getLocalHost();

        // 实例化一个DatagramSocket实例， 用于发送数据， 不需要指定任何信息
        datagramSocket = new DatagramSocket();
        // 实例化一个DatagramPacket实例， 用于存储接收端的信息
        // 第一个参数是准备发送的字节数组， 第二三个参数是发送字节数组的哪些内容， 第三个参数是地址对象实例， 第四个是发送端的端口号
        DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), 0, message.length(), inetAddress, 8088);
        // 发送数据
        datagramSocket.send(datagramPacket);
        System.out.println("发送消息成功....");

        // 关闭资源
        datagramSocket.close();
    }

    // 接收端
    private void receiver() throws Exception {
        DatagramSocket datagramSocket;
        DatagramPacket datagramPacket;
        byte[] bytes = new byte[1024];

        // 实例化一个DatagramSocket实例， 用于接受数据， 需要指定接收端端口号
        datagramSocket = new DatagramSocket(8088);
        // 实例化一个DatagramPacket实例， 用于存储数据， 存储在定义好的字节数组中（长度比数组长就行）
        datagramPacket = new DatagramPacket(bytes, 0, 1024);
        // 接收消息
        datagramSocket.receive(datagramPacket);
        /* 不能用数组的长度， 接收的数据少于数组长度时， 后面的都是数组的初始数据， 会导致打印正常数据后后面跟着一堆符号 */
        System.out.println("接受消息：" + new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
        // 关闭资源
        datagramSocket.close();
    }
}
