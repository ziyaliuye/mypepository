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
 * @Description 新建一个 Annotation  （等同于 @Interface ， 但注意和 Interface 没有半毛钱关系， 相当于继承了 java.lang.annotation.Annotation 接口）
 * 可以在定义 Annotation 的成员变量时为其指定初始值, 指定成员变量的初始值可使用 default 关键字
 * 如果只有一个参数成员， 建议使用参数名为value
 * 如果定义的注解含有配置参数， 那么使用时必须指定参数值， 除非它有默认值。 格式是“参数名 = 参数值” ， 如果只有一个参数成员， 且名称为 value，可以省略 “ value=”
 * 没有成员定义的Annotation称为标记, 例如 @Override
 * 含成员变量的Annotation称为元数据Annotation
 * @Email 414955507@qq.com
 * @date 2019/10/10 14:36
 */
public @interface MyAnnotation {
    // Annotation 的成员变量 在 Annotation 定义中以无参数方法的形式来声明
    // 可以设置默认值
    String value() default "hello";
}