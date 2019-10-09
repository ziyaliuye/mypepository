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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lele
 * @version 1.0
 * @Description JDK 1.0中包含了一个 java.util.Date 类，但是它的大多数方法已经在JDK 1.1引入Calendar类之后被弃用了
 * 而 Calendar 并不比 Date 好多少。它们面临的问题是：
 * 可变性：像日期和时间这样的类应该是不可变的
 * 偏移性： Date中的年份是从1900开始的，而月份都从0开始
 * 格式化：格式化只对Date有用， Calendar 则不行
 * 此外，它们也不是线程安全的；不能处理闰秒等
 * <p>
 * 新的 java.time 中包含了所有关于
 * 本地日期： LocalDate
 * 本地时间： LocalTime
 * 本地日期时间：LocalDateTime
 * 时区：ZonedDateTime
 * 持续时间：Duration
 * 历史悠久的 Date 类新增了 toInstant() 方法，用于把 Date 转换成新的表示形式。这些新增的本地化时间日期 API 大大简化了日期时间和本地化的管理
 * @Email 414955507@qq.com
 * @date 2019/10/9 11:21
 */
public class DateDemo {
    public static void main(String[] args) throws ParseException {
        // java.lang.System类提供了currentTimeMillis()方法返回当前时间与1970年1月1日0时0分0秒之间以毫秒（ms）为单位的时间差
        // 这个方法适用于计算时间差(称为时间戳)
        long start = System.currentTimeMillis();
        // Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("时间差：" + (end - start) + "ms");

        // java.util.Date 表示特定的瞬间， 精确到毫秒，创建指定年月日操作的方法已经过时不建议使用
        // 使用无参构造器创建的对象， 表示本地当前时间
        // Date(long date) 传入指定毫秒数
        Date sysdate = new Date();
        // 返回自1970年1月1日0时0分0秒之间对象表示的毫秒数
        System.out.println(sysdate.getTime());
        // 把对象转换成指定形式
        // dow mon dd  hh:mm:ss zzz yyyy 其中： dow 是一周中的某一天 (Sun, Mon, Tue, Wed, Thu, Fri, Sat)， zzz是时间标准
        System.out.println(sysdate.toString());

        /*
         * Date类的API不易于国际化， 大部分都被废弃
         * java.text.SimpleDateFormat  是一个与语言环境有关的方式来格式化和解析日期的具体类, 它允许：
         * 格式化：日期=》文本
         * 解析：文本=》日期
         */
        Date date = new Date();
        // 使用默认的模式和语言环境创建对象
        SimpleDateFormat sdf = new SimpleDateFormat();
        // 格式化 日期=》字符串, 结果返回一个字符串
        String format = sdf.format(date);
        System.out.println(format);
        // 解析 字符串=》日期， 返回一个Date类型实例, 注意识别不了字符串的情况下会报错
        Date date1 = sdf.parse("19-10-9 下午2:57");
        System.out.println(date1);

        // 使用通过有参构造器传入指定格式的对象
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MMMM.dd GGG hh:mm aaa");
        // 格式化 日期=》字符串, 结果返回一个字符串
        String format1 = sdf1.format(date);
        System.out.println(format1);
        // 解析 字符串=》日期， 返回一个Date类型实例, 注意使用指定的格式字符串， 否则识别不了
        Date date2 = sdf1.parse("2019.十月.09 公元 03:04 下午");
        System.out.println(date2);

        // 使用示例：将"2019-10-08"转为java.sql.date格式
        String birthday = "2019-10-08";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        // 将字符串转为Date java.lang.Date
        Date date3 = sdf2.parse(birthday);
        // 将java.lang.Date转为java.sql.date
        java.sql.Date birthdayDate = new java.sql.Date(date3.getTime());
        System.out.println(birthdayDate);


        /*
         * @description java.util.Calendar 日历类， 是一个抽象基类， 主要用于完成日期字段之间相互操作的功能
         *  Calendar是一个抽象类， 是不能被实例化的， 通常使用它的子类GregorianCalendar
         */
        // 获取Calendar的实例， 通过它的静态方法获取GregorianCalendar的实例
        Calendar calendar = Calendar.getInstance();
        Date date_c = new Date();
        // GregorianCalendar对象创建完以后默认是当前时间， 可以通过setTime()重新设置时间
        calendar.setTime(date_c);
        // 打印子类的实际类路径
        System.out.println(calendar.getClass());
        // 获取当前时间的月份（从0开始） Calendar中定义了很多常量， 可以根据常量名字来获取
        int sys_month = calendar.get(Calendar.MONTH);
        System.out.println("当前时间月份：" + (sys_month + 1));
        // 获取当前时间是当月的第几天
        System.out.println("当前时间月份的第：" + calendar.get(Calendar.DAY_OF_MONTH) + "天");

        // 修改Calendar实例的时间， 修改为9月, 注意返回值是void， 说明它将对象的值修改了
        calendar.set(Calendar.MONTH, 8);
        // 将日期类转为Date类并打印
        System.out.println(calendar.getTime());

        // 对时间进行加减操作, 例如时间往前推一个月
        calendar.add(Calendar.MONTH, -1);
        System.out.println(calendar.getTime());
        // 对时间进行加减操作, 例如时间往前推3天
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        System.out.println(calendar.getTime());

        /*
         *  LocalDate/LocalTime/LocalDateTime 是JDK1.8后提供的重要的几个类，它们的实例是不可变的对象
         *  本地日期： LocalDate， 代表IOS格式（yyyy-MM-dd）的日期， 可以存储生日、纪念日等日期
         *  本地时间： LocalTime， 表示一个时间， 而不是日期
         *  本地日期时间：LocalDateTime ， 表示日期和时间， 这是最常用的类
         */
        // 实例化一个当前本地日期, 调用的是静态方法
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        // 实例化一个当前本地时间, 调用的是静态方法
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        // 实例化一个当前本地日期日间, 调用的是静态方法
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        // of()静态方法可以手工创建一个指定的时间， 格式yyyy,MM,dd,hh,mm,ss, 不再有偏移量
        LocalDateTime ofDateTimce = LocalDateTime.of(2019, 10, 9, 18, 30, 30);
        System.out.println(ofDateTimce);
        // 获取日期时间是当年的第几天
        System.out.println("当年的第几天：" + ofDateTimce.getDayOfYear());
        // 获取日期时间是月份的第几天
        System.out.println("当月的第几天：" + ofDateTimce.getDayOfMonth());
        // 获取日期时间的年份
        System.out.println("年：" + ofDateTimce.getYear());
        // 获取日期时间的月份， 这里返回的是英文（OCTOBER）
        System.out.println("月：" + ofDateTimce.getMonth());
        // 获取日期时间的月份， 这里返回的月份的值
        System.out.println("月：" + ofDateTimce.getMonthValue());

        /*
         * withXXX方法都是设置操作
         * plusXXX方法 年月日 的加（计算）操作
         * minusXXX方法 时间 的减（计算）操作（这里比较坑爹的是负数是往后推， 正数才是往前推）
         */
        // 指定日期是当月的第几天（指定为1号）， 返回一个新的日期对象（对象的不可变性体现：操作完都会有一个对象返回）
        LocalDate localDate1 = localDate.withDayOfMonth(1);
        System.out.println("日期月份加1前：" + localDate);
        System.out.println("指定当月第几天后日期：" + localDate1);

        // 指定时间（指定为18点， 24小时制）， 返回一个新的日期时间对象（对象的不可变性体现：操作完都会有一个对象返回）
        LocalDateTime localDate2 = localDateTime.withHour(18);
        System.out.println(localDate2);

        // 日期往前推3个月（对象的不可变性体现：操作完都会有一个对象返回）
        LocalDateTime localDateTime3 = localDateTime.plusMonths(-3);
        System.out.println("时间往前推3个月：" + localDateTime3);

        // 时间往前推6小时，这里比较坑爹的是负数是往后推， 正数才是往前推（对象的不可变性体现：操作完都会有一个对象返回）
        LocalDateTime localDateTime4 = localDateTime.minusHours(6);
        System.out.println("时间往前推6小时：" + localDateTime4);

        /*
         *  Instant:瞬时， 时间线上的一个瞬时点， 这可能被用来记录应用程序中的时间戳, 类似于Date类
         *  java.time 包通过值类型 Instant 提供机器视图，不提供处理人类意义上的时间单位。 Instant 表示时间线上的一点，而不需要任何上下文信息，例如，时区。
         *  概念上讲， 它只是简单的表示自1970年1月1日0时0分0秒（ UTC）开始的秒数。 因为 java.time 包是基于 纳秒计算的，所以 Instant 的精度可以达到 纳秒级
         */
        // 创建一个Instant对象， 通过 Instant对象的静态方法获取
        Instant instance = Instant.now();
        // 输出时间会晚八小时（东八区时间）
        System.out.println(instance);
        // 设置时区的偏移量8小时
        OffsetDateTime offsetDateTime = instance.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // 返回时间戳的毫秒数
        System.out.println(instance.toEpochMilli());

        // 通过毫秒数创建一个时间戳
        Instant instance1 = Instant.ofEpochMilli(1570611677160L);
        System.out.println(instance1);

        /*
         *  java.time.format.DateTimeFormatter 格式化和解析日期/时间， 该类提供了三中格式化方法
         */
        // 预定义标准格式化, 常量， 总共有三种
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 格式化日期/时间（传入localDateTime/localTime/localDate其中一种）， 日期/时间=》字符串
        String formateDate1 = dateTimeFormatter.format(localDateTime);
        System.out.println(formateDate1);
        // 解析， 字符串=》日期时间，
        // 返回值可以用接口接收TemporalAccessor接口（localDateTime/localTime/localDate都实现了这个接口）
        TemporalAccessor parse = dateTimeFormatter.parse(formateDate1);
        System.out.println(parse);

        // 本地化相关格式化方式ofLocalizedDateTime()：有三种常量格式(LONG/SHORT/MEDIUM)
        // 创建一个格式化的对象， 指定其格式化的格式
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        // 将日期/时间格式化
        String formateDate2 = dateTimeFormatter1.format(localDateTime);
        System.out.println(formateDate2);
        // 创建一个格式化的对象， 指定其格式化的格式 localDate 多了一个FULL
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String formateDate3 = dateTimeFormatter2.format(LocalDate.now());
        System.out.println(formateDate3);

        // 自定义相关格式化
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        // 格式化
        String patternDate = dateTimeFormatter3.format(localDateTime);
        System.out.println(patternDate);

        // 解析
        TemporalAccessor customDate = dateTimeFormatter3.parse("2019-10-09 08:04:41");
        System.out.println(customDate);
    }
}
