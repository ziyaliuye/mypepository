package main.cn.cnm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;

/**
 * @author lele
 * @version 1.0
 * @Description Java中的对象， 正常情况下， 只能进行比较：== 和 !=  ， 不能使用 > <, 但是在开发中又有这种场景， 这时候就实现比较器接口
 * @Email 414955507@qq.com
 * @date 2019/10/9 20:24
 */
public class CompareableDemo {
    public static void main(String[] args) {
        Mouse[] array = new Mouse[4];
        array[0] = new Mouse("雷蛇", 199);
        array[1] = new Mouse("罗技", 299);
        array[2] = new Mouse("双飞燕", 99);
        array[3] = new Mouse("联想", 99);
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
    }
}

// 自定义一个类实现Comparable接口并重写compareTo()方法
@Data // get/set
@AllArgsConstructor // 全参构造
@NoArgsConstructor // 无参构造
@Accessors(chain = true)
// 链式风格访问
class Mouse implements Comparable {
    // 定义名称
    private String name;
    // 定义价格
    private int price;

    // 指定如果进行排序， 比较逻辑可以任意写，有多个字段比较则嵌套比较， 确认最终能返回int正数即可
    @Override
    public int compareTo(Object o) {
        // 判断对象是否是Mouse的实例
        if (o instanceof Mouse) {
            Mouse mouse = (Mouse) o;
            if (this.price < mouse.price) {
                // 比较规则， 如果当前对象的价格小于传入的对象的价格则返回-1
                return -1;
            } else if ((this.price > mouse.price)) {
                // 比较规则， 如果当前对象的价格大于传入的对象的价格则返回1
                return 1;
            } else {
                // 当价格一致时嵌套一层比较， 比较名称大小， 这里直接调用String中已经写好的字符串比较规则
                return this.name.compareTo(mouse.name);
            }
        } else {
            throw new RuntimeException("传入的类型不正确...");
        }

    }
}