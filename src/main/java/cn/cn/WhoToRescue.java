package src.main.java.cn.cn;

/**
 * @author lele
 * @version 1.0
 * @Description JDK1.8中接口可以定义默认方法和静态方法， 由于默认方法会被继承到实现类中， 可能会出现冲突的情况， 以下场景
 * Man继承Father同时实现了Filial（孝顺）和Spoony（痴情）两个接口，这种情况下会默认选择继承类的方法（类优先原则）， 但是去掉继承， 则编译报错， 编译器不知道选择Filial（孝顺）还是Spoony（痴情）
 * @Email 414955507@qq.com
 * @date 2019/10/5 15:00
 */
public class WhoToRescue {
    public static void main(String[] args) {
        HardMan hardMan = new HardMan();
        hardMan.help();
    }
}

interface Filial {
    default void help() {
        System.out.println("help you mammy...");
    }
}

interface Spoony {
    default void help() {
        System.out.println("help you wife...");
    }
}

class Father {
    public void help() {
        System.out.println("help your wife, your man, I'll save your mother....");
    }
}

class HardMan extends Father implements Filial, Spoony {
//    @Override
//    public void help() {
//        System.out.println("help who？？");
//    }
}
