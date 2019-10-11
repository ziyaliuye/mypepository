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

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @author lele
 * @version 1.0
 * @Description 底层使用 双向链表 来存储， 对于频繁的插入、删除操作使用效率比 ArrayList 高
 * 例如有1万个元素， 需要删除第三个元素， ArrayList 需要将第三个以后的元素都往前挪一位， 效率非常低
 * 而 LinkedList 是链表形式， 每个元素都存着上一个元素和下一个元素的地址
 * 例如：删除第三个元素只需要将第二个元素的下一个元素改成第四个元素， 第四个元素的上一个元素改成第二个元素， 其他元素都不需要动
 * @Email 414955507@qq.com
 * @date 2019/10/10 22:15
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        /**
         底层源码大致流程：
         添加第一个元素时， 对象中有两个Node类型属性first,last分别用来存储当前集合中的第一个和最后一个元素。
         添加第二个元素时先维护第一个元素的Node类型属性：将last改为第二个元素，next也赋值第二个元素
         在维护维护第二个元素的Node类型属性，将prev的赋值为第一个元素，last赋值为第二个元素表示是最后一个元素
         后面添加元素依次类推
         */
        // 除了底层结构和ArrayList不一样， 其他使用方式和方法都差不多
        LinkedList list = new LinkedList();
        list.add(1);
        list.add("dddd");
        list.add(3);
        System.out.println(list);
    }
}
