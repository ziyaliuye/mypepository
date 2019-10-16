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

/**
 * @author lele
 * @version 1.0
 * @Description 枚举类， 是一个类， 使用关键字 enum 修饰的类， 使用场景：
 * 当类的对象只有有限个， 确定的， 例如 星期、性别、季节等等
 * 当需要定义一组常量时， 强烈建议使用枚举类
 * 如果枚举类中只有一个对象， 则可以作为单例模式的实现方式
 * @Email 414955507@qq.com
 * @date 2019/10/10 10:44
 */
public class EnumDemo {
    public static void main(String[] args) {
        // 自定义枚举类使用, 直接获取对象
        Season spring = Season.SPRING;
        System.out.println(spring);

        // 使用enum的关键字修饰的枚举类
        EnumSeason enumSeason = EnumSeason.SPRING;
        System.out.println(enumSeason);

        /* Enum类的常用方法 */
        // 可以把一个字符串转为对应的枚举类对象， 要求字符串必须是枚举类对象的“名字”
        // 否则会出现 LLLegalArgumentException 异常
        System.out.println(EnumSeason.valueOf("SPRING"));
        // 返回枚举类型的对象数组， 该方法可以遍历所有的枚举值
        EnumSeason[] array = EnumSeason.values();
        for (EnumSeason tmp : array) {
            System.out.println(tmp);
        }
        // 得到当前枚举常量的名称， 有要求时可以重写
        System.out.println(EnumSeason.valueOf("SUMMER").toString());
    }
}

// 自定义枚举类
class Season {
    // 声明对象的属性 private final, 直接赋值的方式就是每次拿到枚举类的属性值都一样
    private final String seasonName;
    private final String seasonDesc;

    // 如果想每次实例化类的属性值都不一样则需要通过构造器赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 提供当前枚举类的多个不可更改静态对象
    static final Season SPRING = new Season("春天", "交配的好季节...");
    static final Season SUMMER = new Season("夏天", "蒸桑拿的好季节...");
    static final Season AUTUMN = new Season("秋天", "喝西北风的好季节...");
    static final Season WINTER = new Season("冬天", "感冒的好季节...");

    // 提供get方法
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    // 提供toString方法
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

// 自定义一个接口
interface TestInfo {
    void show();
}

// 使用enum修饰的枚举类
enum EnumSeason implements TestInfo {
    // 首先就定义返回的对象（比较怪异的要求， 返回的对象必须放最上面）， 多个对象用逗号,隔开
    SPRING("春天", "交配的好季节...") {
        // 重写对象的方法
        @Override
        public void show() {
            System.out.println("春天啦啦啦");
        }
    },
    SUMMER("夏天", "蒸桑拿的好季节...") {
        // 重写对象的方法
        @Override
        public void show() {
            System.out.println("夏天啦啦啦");
        }
    },
    AUTUMN("秋天", "喝西北风的好季节...") {
        // 重写对象的方法
        @Override
        public void show() {
            System.out.println("秋天啦啦啦");
        }
    },
    WINTER("冬天", "感冒的好季节...") {
        // 重写对象的方法
        @Override
        public void show() {
            System.out.println("冬天啦啦啦");
        }
    };

    // 声明对象的属性 private final, 直接赋值的方式就是每次拿到枚举类的属性值都一样
    private final String seasonName;
    private final String seasonDesc;

    // 如果想每次实例化类的属性值都不一样则需要通过构造器赋值
    private EnumSeason(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 提供get方法
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    // 重写接口的方法
    @Override
    public void show() {
        System.out.println("啦啦啦");
    }
}