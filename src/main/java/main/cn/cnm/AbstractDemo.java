package main.cn.cnm;

/*
_ooOoo_
        o8888888o
88" . "88
(| -_- |)
O\ = /O
___/`---'\____
 .   ' \\| |// `.
        / \\||| : |||// \
 / _||||| -:- |||||- \
 | | \\\ - /// | |
        | \_| ''\---/'' | |
 \ .-\__ `-` ___/-. /
___`. .' /--.--\ `. . __
        ."" '< `.___\_<|>_/___.' >'"".
        | | : `- \`.;`\ _ /`;.`/ - ` : | |
 \ \ `-. \_ __\ /__ _/ .-` / /
 ======`-.____`-.___\_____/___.-`____.-'======
 `=---='
 .............................................
佛曰：bug泛滥，我已瘫痪！
*/

/**
 * @author lele
 * @version 1.0
 * @Description 抽象类：有时候设计一个类非常的抽象， 以至于它没有具体的实例， 这样的类叫做抽象类
 * 抽象方法：在抽象类中，用 abstract 修饰的方法就是抽象方法，格式 权限修饰符 abstract  返回值类型 方法名;
 * @Email 414955507@qq.com
 * @date 2019/10/4 20:56
 */
public class AbstractDemo {
    public  static void main(String[] args) {
        // 抽象类的匿名子类
        AbstractPerson abstractPerson = new AbstractPerson() {
            @Override
            public void eat() {
                System.out.println("匿名实现类重写的方法....");
            }
        };
        abstractPerson.eat();
    }
}

abstract class AbstractPerson {
    public abstract void eat();

    public void beatPeople() {
        System.out.println("揍人....");
    }
}

