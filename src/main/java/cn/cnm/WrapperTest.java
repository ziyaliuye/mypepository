package cn.cnm;

/*
      ┌─┐       ┌─┐ + +
   ┌──┘ ┴───────┘ ┴──┐++
   │                 │
   │       ───       │++ + + +
   ███████───███████ │+
   │                 │+
   │       ─┴─       │
   │                 │
   └───┐         ┌───┘
       │         │
       │         │   + +
       │         │
       │         └──────────────┐
       │                        │
       │                        ├─┐
       │                        ┌─┘
       │                        │
       └─┐  ┐  ┌───────┬──┐  ┌──┘  + + + +
         │ ─┤ ─┤       │ ─┤ ─┤
         └──┴──┘       └──┴──┘  + + + +
        前方高能
        BUG闪开...
*/

/**
 * @author lele
 * @version 1.0
 * @Description 针对八种基本基础类型定义相应的引用类型（包装类）, 使得基本数据类型变量变得具有类的特征
 * @Email 414955507@qq.com
 * @date 2019/10/4 13:39
 */
public class WrapperTest {
    public static void main(String[] args) {
        int num1 = 10;
        // 基础数据类型 转换为 包装类
        // 调用包装类的构造器
        Integer in1 = new Integer(num1);
        System.out.println(in1.toString());
        // 也可以传入字符串调用相应的构造器
        // 但是要注意确保字符串能正确的转换成对应的数值， 否则会报错
        Integer in2 = new Integer("100");
        System.out.println(in2.toString());

        // Boolean比较特殊， 主要传入的参数不是true或者忽略大小写的 "true" 则都为 false
        Boolean b1;
        b1 = Boolean.valueOf("true123");
        System.out.println(b1);

        // 包装类转换为基本数据类型  调用包装类的 xxxValue()
        // 可以省略也最好省略， 因为有自动拆箱，这一步是多余的， 编译器还会警告
        int num11 = in1.intValue();

        // 自动装箱：功能就是替代包装类使用构造器来初始化值
        int num10 = 10;
        Integer in10 = num10;
        // 自动拆箱：替换原来繁琐的使用包装类的方法来进行转换
        int num12 = in10;

        // 基本数据类型/包装类 转换为 String
        int num100 = 100;
        // 方式一：连接运算符
        String str1 = "" + num100;
        // 方式二：调用 String 的重载方法 valueOf() 将基础数据类型转换为 String
        String str2 = String.valueOf(num100);
        // 利用包装类的自动拆箱功能, 可以直接将包装类转换为String
        String str3 = String.valueOf(new Integer(100));

        // String 转换为 基本数据类型/包装类
        String str5 = "123";
        // 方式一：调用包装类的 parseXXX() 将字符串转换成对应的基础数据类型
        int in5 = Integer.parseInt(str5);
        // 利用自动装箱， 又将基础数据类型装成包装类， 要知道parseXXX的返回类型是基础数据类型
        Integer in6 = Integer.parseInt(str5);

        // 偏离题
        System.out.println("无聊的人出的题：");
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        // 这里是纯粹的对象之间的比较， 比较的是地址值
        System.out.println(i == j);

        Integer m = 1;
        Integer n = 1;
        // 装箱时， jdk为了提升效率， 定义了一个int的数组作为缓存， 范围是-128到127， 只要是这个范围的数据就会直接返回对应的int数据
        // 所以这里最终返回的是一个int而不是包装类， 结果就变成了数值比较， 所以为true
        System.out.println(m == n);

        Integer x = 128;
        Integer y = 128;
        // 超出-128到127的范围后则会返回包装类， 比较是时按对象地址进行比较， 所以结果为 false
        System.out.println(x == y);

    }
}
