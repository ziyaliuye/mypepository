package cn.cnm;

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

import java.util.Optional;

/**
 * @author lele
 * @version 1.0
 * @Description Optional 类：
 * 到目前为止，臭名昭著的空指针异常是导致Java应用程序失败的最常见原因
 * 以前，为了解决空指针异常，Google公司著名的Guava项目引入了Optional类，Guava通过使用检查空值的方式来防止代码污染
 * 它鼓励程序员写更干净的代码, 受到Google Guava的启发， Optional类已经成为Java 8类库的一部分
 * <p>
 * Optional<T> 类(java.util.Optional)是一个容器类， 它可以保存类型T的值，代表这个值存在, 或者仅仅保存null，表示这个值不存在
 * 原来用null表示一个值不存在，现在Optional可以更好的表达这个概念, 并且可以避免空指针异常
 * Optional提供很多有用的方法，这样我们就不用显式进行空值检测, 例如如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
 * @Email 414955507@qq.com
 * @date 2019/10/21 16:01
 */
public class OptionalDemo {
    public static void main(String[] args) {
        /* 创建Optional类对象的方法： */
        // Optional.of(T t)：创建一个Optional实例， t必须非空, 否则报错
        Optional<PersonTest> optional = Optional.of(new PersonTest());
        // Optional.empty()：创建一个空的Optional实例
        Optional optional_null = Optional.empty();
        // Optional.ofNullable(T t)：t可以为null
        Optional optional_any = Optional.ofNullable(null);
        System.out.println(optional);
        System.out.println(optional_null);
        System.out.println(optional_any);

        /* Optional的使用 */
        // 通常写法
        PersonTest p = new PersonTest();
        if (p != null) {
            String str = p.getName();
            if (str != null) {
                System.out.println(str.toUpperCase());
            }
        }
        // Optional写法
        Optional<PersonTest> optional1 = Optional.ofNullable(p);
        if (optional1.isPresent()) {
            Optional<String> str = Optional.ofNullable(p.getName());
            if (str.isPresent()) {
                System.out.println(str.get().toUpperCase());
            }
        }

        // Optional备胎（创建一个备胎， Optional内容为空时返回备用的内容）
        Optional<PersonTest> optional2 = Optional.ofNullable(p);
        // T orElse(T other)：如果有值则将其返回，否则返回指定的other对象（备胎）
        PersonTest p1 = optional2.orElse(new PersonTest("备胎"));
        /* 此时p1一定是非空的 */
        System.out.println("p1：" + p1);

        /*
         * 其他方法：
         * 判断Optional容器中是否包含对象：
         *	boolean isPresent()：判断是否包含对象
         *	void ifPresent(Consumer<? super T> consumer)：如果有值，就执行Consumer接口的实现代码，并且该值会作为参数传给它。
         * 获取Optional容器的对象：
         *	T get(): 如果调用对象包含值，返回该值，否则抛异常
         *	T orElse(T other)：如果有值则将其返回，否则返回指定的other对象。
         *	T orElseGet(Supplier<? extends T> other)：如果有值则将其返回，否则返回由Supplier接口实现提供的对象.
         *	T orElseThrow(Supplier<? extends X> exceptionSupplier)：如果有值则将其返回，否则抛出由Supplier接口实现提供的异常。
         */
    }
}
