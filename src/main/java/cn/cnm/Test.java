package cn.cnm;

import java.io.IOException;

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
             ...........这特么哪个大神写的代码.....................
*/

/**
 * @author lele
 * @version 1.0
 * @Description
 * @Email 414955507@qq.com
 * @date 2019/9/29 23:37
 */
public class Test {
    /*
     * @description 这是程序的入口
     * @Author lele
     * @param args : 输入参数
     * @return void
     * @throws
     * @Date 2019/9/29 22:47
     */
    public static void main(String[] args) throws IOException {
        int[] array5 = new int[]{1, 2, 3, 4, 5, 6};
        int dest = 2;
        // 二分法查找， 通常来说比线性查找速度要快， 但是前提是数组的数据必须是有序的, 比较时一定要注意顺序
        // 初始的首索引
        int head = 0;
        // 初始末索引
        int end = array5.length - 1;
        while (head <= end) {
            // 将索引折中， 取出折中的值（第二次循环过来再折中）
            int middle = (head + end) / 2;
            if (array5[middle] > dest) {
                // 如果中间的值比查找的值大则查找前半段， 一定要注意大小， 别搞反了
                end = middle - 1;
            } else if (array5[middle] < dest) {
                // 如果中间的值比查找的值大则查找后半段， 一定要注意大小， 别搞反了
                head = middle + 1;
            } else {
                System.out.println("二分法查找找到了：" + array5[middle] + " 索引：" + middle);
                // 找到后当然就退出循环
                break;
            }
        }
    }
}
