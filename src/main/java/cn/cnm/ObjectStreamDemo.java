package cn.cnm;

/*
                       .::::.
                     .::::::::.
                    :::::::::::
                 ..:::::::::::'    美女保佑、永无Bug
              '::::::::::::'
                .::::::::::
           '::::::::::::::..
                ..::::::::::::.
              ``::::::::::::::::
               ::::``:::::::::'        .:::.
              ::::'   ':::::'       .::::::::.
            .::::'      ::::     .:::::::'::::.
           .:::'       :::::  .:::::::::' ':::::.
          .::'        :::::.:::::::::'      ':::::.
         .::'         ::::::::::::::'         ``::::.
     ...:::           ::::::::::::'              ``::.
    ```` ':.          ':::::::::'                  ::::..
                       '.:::::'                    ':'````..
*/

import lombok.Data;

import java.io.*;

/**
 * @author lele
 * @version 1.0
 * @Description 对象流:
 * ObjectInputStream 和 OjbectOutputSteam
 * 用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 * 序列化： 用ObjectOutputStream类保存基本类型数据或对象的机制
 * 反序列化： 用ObjectInputStream类读取基本类型数据或对象的机制
 * ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量(修饰的变量会变成null)
 * <p>
 * 如果需要让某个对象支持序列化机制，则必须让对象所属的类及其属性是可序列化的，为了让某个类是可序列化的
 * 否则，会抛出 NotSerializableException 异常
 * 该类必须实现两个接口之一：
 * Serializable（相当于一个标识， 表明类是可以序列户的）
 * Externalizable
 * @Email 414955507@qq.com
 * @date 2019/10/16 16:59
 */
public class ObjectStreamDemo {
    public static void main(String[] args) {
        /* 序列化过程：将内存中的Java对象存储在磁盘中或者通过网络传输出去  */
        ObjectOutputStream objectOutputStream = null;
        try {
            // 初始化一个ObjectOutputStream对象准备将对象存储到磁盘中
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\1.txt"));
            // 将对象写入磁盘中（对象流可以处理基础数据类型和对象， 所以write方法有很多个， 例如writeInt()）
            /* 如果对象没有实现Serializable接口， 则会报java.io.NotSerializableException */
            objectOutputStream.writeObject(new PersonTest("二狗子", 18));
            // 传输多个对象后， 读取的顺序也要一样， 并且最好传输的对象是同一个类型
            objectOutputStream.writeObject(new PersonTest("蛋蛋", 20));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /* 反序列化过程：将磁盘中或网络传输过来的的字节流转换为Java中的对象  */
        ObjectInputStream objectInputStream;
        try {
            // 初始化一个ObjectInputStream对象准备读取字节内容并转换为Java对象
            objectInputStream = new ObjectInputStream(new FileInputStream("D:\\1.txt"));
            // 将字节流转为Java对象
            Object object1 = objectInputStream.readObject();
            // 文件存储多个对象读取时也要按存储的顺序来， 并且多个对象最好是同一类型
            Object object2 = objectInputStream.readObject();
            System.out.println("反序列化后的Object1路径:" + object1.toString());
            System.out.println("反序列化后的Object2路径:" + object2.toString());
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        } catch (ClassNotFoundException e) {
            System.out.println("对象类型转换错误...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

@Data
class PersonTest implements Serializable {
    /*
     * 对象想进行传输必须实现Serializable接口, 以及类的属性需要支持序列化:
     * 	    String 是内部已经实现Serializable接口的， 基本数据类型默认支持可序列化
     *      但是要注意属性中有自定义的类， 那么这个类也必须是可序列化的
     * 并且定义一个名字serialVersionUID（序列常量）的常量：
     * private/public static final long serialVersionUID
     *
     * serialVersionUID 用来表明类的不同版本间的兼容性（即使类被修改了， 依然可以还原回来）：
     *  没有加上这个唯一标识的情况下， 序列化类存储起来后，Java会自动有一个算法给类加上一个唯一标识 A
     *  期间内被修改了（例如加了属性、方法）， 在反序列化时就找不到这个类了
     *  类一旦被修改过， 新的类对应的唯一标识变成另外一个值B， 而存储在磁盘中的文件的唯一标识还是A）
     */
    public static final long serialVersionUID = 12345678L;

    public static String sex = "男";

    private String name;
    private int age;

    PersonTest() {
        this.name = "傻蛋";
        this.age = 18;
    }

    PersonTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void test(String name, int age) {
        System.out.println(name + age);
    }

    private static String staticTest() {
        System.out.println("静态方法被调用....");
        return "傻逼";
    }
}
