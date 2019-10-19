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
             ................好烦啦.....................
*/

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author lele
 * @version 1.0
 * @Description Reflection（反射）是被视为动态语言的关键，反射机制允许程序在执行期间借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法
 * 加载完类之后， 在堆内存的方法区中就产生了一个Class类型的对象（一个类只有一个Class对象） ， 这个对象就包含了完整的类的结构信息
 * 我们可以通过这个对象看到类的结构。 这个对象就像一面镜子， 透过这个镜子看到类的结构， 所以， 我们形象的称之为： 反射
 * <p>
 * Java不是动态语言， 但Java可以称之为“ 准动态语言” 。 即Java有一定的动态性， 我们可以利用反射机制、 字节码操作获得类似动态语言的特性。
 * Java 的动态性让编程的时候更加灵活！
 * @Email 414955507@qq.com
 * @date 2019/10/18 14:30
 */
public class ReflectionDemo {
    /*
     * 通过new的方式和反射的方式都可以调用类的公共结构， 一般情况下直接使用new的方式， 除非在编写时不确定要new哪个类的对象就需要用到反射
     * 反射机制和面向对象中的封装性是不矛盾的：
     *  封装性更多的是告诉他人， 私有的结构不建议调用，单例模式情况下只能有一个实例存在
     *  而反射机制是能不能拿到私有属性和创建对象实例的问题， 它能直接读取私有的结构和创建实例， 但不建议这么做
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        /*
         * java.lang.Class  称为反射的源头， 反射的操作第一步都是通过创建它来实现
         * 关于java.lang.Class类的理解（在Java中万物皆对象）：
         *  类的加载过程：
         *      代码经过javac.exe命令以后， 会生成一个或多个class文件（字节码文件）
         *      然后使用java.exe对字节码文件进行解释和运行， 相当于将字节码文件加载进了内存中（加载的过程就称为“类的加载”过程）
         *      加载进内存的类， 此时称为“运行时类”， 它就作为“Class的一个实例”（每个类只有一个）
         *          例如Person.class文件加载进行内存后， 它就充当Person类的一个实例
         *          由于语法限制不能直接使用Person（直接写Person只是表示一个类型）来表示它， 所以给”运行时类“增加了一个属性class, 这个属性就表示这个“运行时类”（Person.class）
         *  java.lang.Class类的一个实例就对应着一个“运行时类”
         */

        // 获取Class的实例方式一：调用运行时类的属性 .class  (编译时就写死了代码， 不常用， 反射是运行时获取)
        Class<PersonTest> clazz1 = PersonTest.class;
        System.out.println("方式一：" + clazz1);
        // 获取Class的实例方式二：通过运行时类的对象getClass()方法返回“运行时类” (不常用， 既然都实例化对象了， 还用什么反射)
        PersonTest p1 = new PersonTest("狗蛋", 123);
        Class clazz2 = p1.getClass();
        System.out.println("方式二：" + clazz2);
        // 获取Class的实例方式三：调用Class类的静态方法forName(String classPath)  (常用， 运行时获取”运行时类“)
        Class clazz3 = Class.forName("cn.cnm.PersonTest");
        System.out.println("方式三：" + clazz3);
        // 获取Class的实例方式四：使用类的加载器ClassLoader （不常用）
        ClassLoader classLoader = ReflectionDemo.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("cn.cnm.PersonTest");
        System.out.println("方式四：" + clazz4);
        /* 加载进内存中的”运行时类“会缓存一段时间， 一个类只有一个“运行时类”，不管通过哪种方式获取的“运行时类”， 都是同一个 */
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);
        System.out.println(clazz3 == clazz4);

        /*
         * 以下类型均可以有Class对象：
         *（1） class：外部类， 成员(成员内部类， 静态内部类)， 局部内部类， 匿名内部类
         *（2） interface： 接口
         *（3） []：数组
         *（4） enum：枚举
         *（5） annotation：注解@interface
         *（6） primitive type：基本数据类型
         *（7） void
         */
        // 通过反射创建运行时类的对象（不再是通过关键字new）
        Class<PersonTest> clazz = PersonTest.class;

        /*
         * newInstance() 表示调用类的无参构造器, 返回”运行时类“对应类的一个实例, 也可以调用带参数构造器（通常都是调用无参构造器）
         * 而且构造器的权限必须够，通常设置为public或缺省， 在 JavaBean 中要求提供一个 public 的无参构造器，原因：
         *  便于通过反射，创建运行时类的实例
         *  便于子类继承次运行类时，默认调用 super() ，保证父类中有此构造器，不然会创建实例失败
         */
        Object object = clazz.newInstance();
        System.out.println(object);

        /*
         * 通过反射获取“运行时类”的属性（注意有的方法只能获取public修饰的结构， 缺省类型也不能获取）
         */
        // 通过getFields()获取类的public修饰的属性结构， 返回一个Field数组
        // 数组总包含了类中所有（包括同父类继承而来的属性）public的属性结构
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println("getFields()：" + f.getName());
        }
        // 通过getDeclaredFields()获取当前类所有（不考虑修饰权限）声明过的属性结构（不包含父类）， 返回一个Field数组
        Field[] declaredFiles = clazz.getDeclaredFields();
        for (Field f : declaredFiles) {
            System.out.println("getDeclaredFields()：" + f.getName());
        }
        // 通过Field类的实例获取：权限修饰符、数据类型、变量名
        for (Field f : declaredFiles) {
            /* 权限修饰符定义的只是一个数值， 需要调用Modifier的toString()方法返回具体的含义 */
            System.out.print("权限修饰符：" + Modifier.toString(f.getModifiers()) + "  ");
            /* 数据类型对应的就是一个Class类的实例， 可以用Class接收 */
            System.out.print("数据类型：" + f.getType() + "  ");
            System.out.print("变量名：" + f.getName() + "  \n");
        }

        /*
         * 通过反射获取“运行时类”的方法
         */
        // 通过getFields()获取类的public修饰的方法结构， 返回一个Method数组
        // 数组总包含了类中所有（包括同父类继承而来的方法）public的方法结构
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println("getMethods()：" + m.getName());
        }
        // 通过getDeclaredFields()获取当前类所有（不考虑修饰权限）声明过的属性结构（不包含父类）， 返回一个Field数组
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println("getDeclaredMethods()：" + m.getName());
        }
        // 通过Method类的实例获取：
        // @Annotation 权限修饰符、返回值类型、方法名(参数类型1 形参名1, ....) throws XxxException{}
        /* 获取方法声明的注解（生命周期是runtimer的注解才能获取到）, 这里是获取所有注解中的第一个 */
        for (Method m : declaredMethods) {
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println("方法的注解：" + a);
            }
        }
        /* 获取权限修饰符， 同样需要调用Modifier的toString()方法返回具体的含义 */
        for (Method m : declaredMethods) {
            System.out.println("方法的权限修饰符：" + Modifier.toString(m.getModifiers()));
        }
        /* 方法的返回值类型， 类型对应的就是一个Class类的实例， 可以用Class接收， 包括void */
        for (Method m : declaredMethods) {
            System.out.println("方法的返回值类型：" + m.getReturnType());
        }
        for (Method m : declaredMethods) {
            System.out.println("方法名：" + m.getName());
        }
        /* 形参列表和异常类型 */
        for (Method m : declaredMethods) {
            // 获取参数列表
            Class[] parameterTypes = m.getParameterTypes();
            // 判断参数是否为空
            for (Class p : parameterTypes) {
                System.out.println("参数：" + p.getName());
            }
            // 获取异常类型
            Class[] exceptions = m.getExceptionTypes();
            // 判断参数是否为空
            for (Class e : exceptions) {
                System.out.println("抛出异常：" + e.getName());
            }
        }

        /*
         * 通过反射获取“运行时类”的构造器
         */
        // 获取当前“运行时类”中声明为public的构造器（构造器和方法、属性区别就是不能拿到父类的构造器）
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            System.out.println("getConstructors()：" + c);
        }
        // 获取当前“运行时类”中所有声明的构造器
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor d : declaredConstructors) {
            System.out.println("getDeclaredConstructors()：" + d);
        }
        /*
         * 通过反射获取“运行时类”的父类
         */
        Class superClass = clazz.getSuperclass();
        System.out.println("运行时类的父类：" + superClass);
        // 获取带泛型的父类， 如果没有泛型， 则返回普通的类
        Type type = clazz.getGenericSuperclass();
        System.out.println("运行时类带泛型的父类：" + type);
        // 获取父类泛型参数列表, 这里如果父类没有泛型则会强转失败 ClassCastException
        try {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type a : actualTypeArguments) {
                System.out.println("泛型参数：" + a.getTypeName());
                /* 也可以将泛型类型转换为Class */
                Class genericClass = (Class) a;
                System.out.println("泛型参数：" + genericClass.getName());
            }
        } catch (ClassCastException e) {
            System.out.println("父类没有声明泛型...");
        }

        /*
         * 通过反射获取“运行时类”实现的接口
         */
        // 获取当前“运行时类”所实现的接口
        Class[] interfaces = clazz.getInterfaces();
        // 获取当前“运行时类”的"父类"所实现的接口
        Class[] superInterfaces = clazz.getSuperclass().getInterfaces();
        for (Class c : interfaces) {
            System.out.println("interfaces：" + c);
        }
        for (Class s : superInterfaces) {
            System.out.println("superInterfaces：" + s);
        }

        /*
         * 通过反射获取“运行时类”所处的包
         */
        Package aPackagee = clazz.getPackage();
        System.out.println("当前运行时类的包路径：" + aPackagee);

        /*
         * 反射操作示例
         */
        /* 获取“运行时类”指定的属性 */
        Field name = clazz.getDeclaredField("name");
        /* 不能直接操作非public修饰的属性， 否则会提示权限不够异常， 需要设置属性为无障碍模式 */
        name.setAccessible(true);

        // 参数一：指明设置哪个对象的属性 参数二：将此属性值设置为多少
        name.set(object, "二傻蛋");
        /* 注意参数如果是静态的， 那么它就不属于具体的哪个对象了， 第一个参数可以写成null或者“运行时类” */
        Field sex = clazz.getDeclaredField("sex");
        // 还需要注意静态属性不能修饰final， 这是常识
        sex.set(null, "女");
        // 等价于：
        // sex.set(PersonTest.class, "女");

        // 获取属性的值, 参数指定是哪个实例
        System.out.println("object属性name：" + name.get(object));
        // 获取静态属性的值， 参数同样可以写null或者“运行时类”
        System.out.println("object静态属性serialVersionUID：" + sex.get(PersonTest.class));

        /* 获取“运行时类”指定的方法, 第一个参数是方法名， 后面的参数则是方法的参数类型 */
        Method test = clazz.getDeclaredMethod("test", String.class, int.class);
        Method staticTest = clazz.getDeclaredMethod("staticTest");
        // 如果方法不是public修饰的， 则需要设置无障碍操作
        test.setAccessible(true);
        /* invoke()的方法值就是调用对应方法的返回值 */
        // 调用invoke()开始调用指定实例的方法， 参数以：方法的调用者， 后续参数则是方法的实参
        test.invoke(object, "王二蛋", 123);
        /* 同样的， 静态方法的调用者可以写null或"运行时类" */
        staticTest.setAccessible(true);
        String result = staticTest.invoke(PersonTest.class).toString();
        System.out.println("静态方法的返回值：" + result);

        /* 获取“运行时类”指定的构造器, 参数则是构造器的参数类型 */
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
        // 设置无障碍访问
        constructor.setAccessible(true);
        // 调用构造器获取一个实例
        PersonTest personTest = (PersonTest) constructor.newInstance("康大傻子", 28);
        System.out.println(personTest);
        /* 这种方式很繁琐， 可以调用“运行时类”的newInstance()方法即可， 指定有这么回事就可以 */
    }
}
