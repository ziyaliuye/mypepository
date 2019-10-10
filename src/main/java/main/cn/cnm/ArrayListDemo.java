package main.cn.cnm;

/*
     ┏┛ ┻━━━━━┛ ┻┓
     ┃　　　　　　 ┃
     ┃　　　━　　　┃
     ┃　┳┛　  ┗┳　┃
     ┃　　　　　　 ┃
     ┃　　　┻　　　┃
     ┃　　　　　　 ┃
     ┗━┓　　　┏━━━┛
       ┃　　　┃   神兽保佑
       ┃　　　┃   永无BUG..
       ┃　　　┗━━━━━━━━━┓
       ┃　　　　　　　    ┣┓
       ┃　　　　         ┏┛
       ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
         ┃ ┫ ┫   ┃ ┫ ┫
         ┗━┻━┛   ┗━┻━┛
*/

/**
 * @author lele
 * @version 1.0
 * @Description 作为List接口的主要实现类，线程不安全的， 效率高， 底层使用 Object[] 来存储
 * JDK1.7：初始化时无参构造器创建一个长度是10的 Object[]，添加满10个元素时会进行扩容， 默认扩1.5倍， 若果添加的数据量很大则用数据量的长度来进行扩容，将原有数组中的数据克隆到新的数组
 * *****    结论就是和 StringBuffer 一样， 建议使用带参数的构造器， 减少克隆次数
 * JDK1.8：初始化时无参构造器创建一个没有长度（空的）的Object[]，第一次调用 add() 方法时，根据数据量的长度进行扩容，默认扩容10，数据量超过10时按数据量长度来扩容， 后续操作和 JDK1.7操作一致。
 * *****    结论就是JDK1.8 的对象创建类似于单例懒汉式创建，延迟了数组的创建， 节省了内存。
 * @Email 414955507@qq.com
 * @date 2019/10/10 21:53
 */
public class ArrayListDemo {
    public static void main(String[] args) {

    }
}
