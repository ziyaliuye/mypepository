package main.cnm;

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

/**
 * @author lele
 * @version 1.0
 * @Description 整体步骤很固定、通用、这些步骤在父类中已经写好了， 但是某些易变的部分可以抽象出来， 让不同的子类实现， 这就是模板设计模式
 * @Email 414955507@qq.com
 * @date 2019/10/4 22:08
 */
public class TemplateMethod {
    public static void main(String[] args) {
        PersonTemplate personTemplate = new Chineses();
        personTemplate.process();
        personTemplate = new Foreigners();
        personTemplate.process();
    }
}

//抽象类
abstract class PersonTemplate {
    private void sitDown() {
        System.out.println("坐下...");
    }

    private void eat() {
        System.out.println("开吃...");
    }

    // 将不确定的部分抽象出来
    public abstract void tableware();

    // 模板方法， 不允许修改， 整个流程是固定的
    final void process() {
        sitDown();
        // 调用抽象的方法， 像个钩子一样，在实际执行时勾不同子类的方法
        tableware();
        eat();
    }
}

// 实现类一
class Chineses extends PersonTemplate {
    @Override
    public void tableware() {
        System.out.println("拿出筷子...");
    }
}

// 实现类二
class Foreigners extends PersonTemplate {
    @Override
    public void tableware() {
        System.out.println("拿出叉子...");
    }
}
