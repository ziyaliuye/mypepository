package cn.cnm;

/*
               ii.                                         ;9ABH,
              SA391,                                    .r9GG35&G
              &#ii13Gh;                               i3X31i;:,rB1
              iMs,:,i5895,                         .5G91:,:;:s1:8A
               33::::,,;5G5,                     ,58Si,,:::,sHX;iH1
                Sr.,:;rs13BBX35hh11511h5Shhh5S3GAXS:.,,::,,1AG3i,GG
                .G51S511sr;;iiiishS8G89Shsrrsh59S;.,,,,,..5A85Si,h8
               :SB9s:,............................,,,.,,,SASh53h,1G.
            .r18S;..,,,,,,,,,,,,,,,,,,,,,,,,,,,,,....,,.1H315199,rX,
          ;S89s,..,,,,,,,,,,,,,,,,,,,,,,,....,,.......,,,;r1ShS8,;Xi
        i55s:.........,,,,,,,,,,,,,,,,.,,,......,.....,,....r9&5.:X1
       59;.....,.     .,,,,,,,,,,,...        .............,..:1;.:&s
      s8,..;53S5S3s.   .,,,,,,,.,..      i15S5h1:.........,,,..,,:99
      93.:39s:rSGB@A;  ..,,,,.....    .SG3hhh9G&BGi..,,,,,,,,,,,,.,83
      G5.G8  9#@@@@@X. .,,,,,,.....  iA9,.S&B###@@Mr...,,,,,,,,..,.;Xh
      Gs.X8 S@@@@@@@B:..,,,,,,,,,,. rA1 ,A@@@@@@@@@H:........,,,,,,.iX:
     ;9. ,8A#@@@@@@#5,.,,,,,,,,,... 9A. 8@@@@@@@@@@M;    ....,,,,,,,,S8
     X3    iS8XAHH8s.,,,,,,,,,,...,..58hH@@@@@@@@@Hs       ...,,,,,,,:Gs
    r8,        ,,,...,,,,,,,,,,.....  ,h8XABMMHX3r.          .,,,,,,,.rX:
   :9, .    .:,..,:;;;::,.,,,,,..          .,,.               ..,,,,,,.59
  .Si      ,:.i8HBMMMMMB&5,....                    .            .,,,,,.sMr
  SS       :: h@@@@@@@@@@#; .                     ...  .         ..,,,,iM5
  91  .    ;:.,1&@@@@@@MXs.                                       .,,:,:&S
  hS ....  .:;,,,i3MMS1;..,..... .  .     ...                     ..,:,.99
  ,8; ..... .,:,..,8Ms:;,,,...                                     .,::.83
   s&: ....  .sS553B@@HX3s;,.    .,;13h.                            .:::&1
    SXr  .  ...;s3G99XA&X88Shss11155hi.                             ,;:h&,
     iH8:  . ..   ,;iiii;,::,,,,,.                                 .;irHA
      ,8X5;   .     .......                                       ,;iihS8Gi
         1831,                                                 .,;irrrrrs&@
           ;5A8r.                                            .:;iiiiirrss1H
             :X@H3s.......                                .,:;iii;iiiiirsrh
              r#h:;,...,,.. .,,:;;;;;:::,...              .:;;;;;;iiiirrss1
             ,M8 ..,....,.....,,::::::,,...         .     .,;;;iiiiiirss11h
             8B;.,,,,,,,.,.....          .           ..   .:;;;;iirrsss111h
            i@5,:::,,,,,,,,.... .                   . .:::;;;;;irrrss111111
            9Bi,:,,,,......                        ..r91;;;;;iirrsss1ss1111
			...............我都不知道我写了啥------------------------------
*/

/**
 * @author lele
 * @version 1.0
 * @Description 代理模式就是为其他对象提供一种代理以达到控制这个对象的访问, 这里为代理模式的一种：静态代理
 * @Email 414955507@qq.com
 * @date 2019/10/5 12:44
 */
public class StaticProxyModel {
    public static void main(String[] args) {
        // 实例化出具体明细对象， 需要它跳舞
        RealStat realStat = new RealStat();
        // 通过代理类的构造器将实例交给代理人
        Stat stat = new Proxy(realStat);
        // 开始干活
        stat.confer();
        stat.signContract();
        stat.bookTicket();
        // 这里实际是执行的具体明星实例的方法， 从代码层面保护了真实的对象
        stat.sing();
        stat.collectMoney();
    }
}

// 首先需要一个接口类
interface Stat {
    // 面谈
    void confer();

    // 签合同
    void signContract();

    // 订票
    void bookTicket();

    // 唱歌
    void sing();

    // 收钱
    void collectMoney();
}

// 定义一个被代理类（接口实现类）， 就是是某一个具体的明星, 它实现了所有方法， 但是除了sing， 其他实际啥也没干， 光实现了
class RealStat implements Stat {
    @Override
    public void confer() {
    }

    @Override
    public void signContract() {
    }

    @Override
    public void bookTicket() {
    }

    @Override
    public void sing() {
        System.out.println("唱、跳、Rap、打篮球....");
    }

    @Override
    public void collectMoney() {
    }
}

// 定义一个代理类（实现类）， 除了实现所有的方法（sing啥逻辑也不写）， 还有一个明星的属性， 这个属性通过有参构造器来初始化， 将具体明细实例赋给属性
class Proxy implements Stat {
    // 定义好具体明星的属性
    private Stat stat;

    // 定义有参构造器， 接收具体的明细实例
    Proxy(Stat stat) {
        this.stat = stat;
    }

    @Override
    public void confer() {
        System.out.println("面谈好幸苦....");
    }

    @Override
    public void signContract() {
        System.out.println("签合同....");
    }

    @Override
    public void bookTicket() {
        System.out.println("跑腿去买票....");
    }

    // 主要在这里， 调用的是具体明星实例的方法
    @Override
    public void sing() {
        stat.sing();
    }

    @Override
    public void collectMoney() {
        System.out.println("帮忙数钱...");
    }
}