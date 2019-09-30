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
 * @Description 数组（Array ）， 是多个相同类型的数据按一定顺序排列的集合， 使用一个名字来命名， 通过编号的方式对数据进行统一管理
 * @Email 414955507@qq.com
 * @date 2019/9/30 10:23
 */
public class ArrayDemo {
    /*
     * @description  创建数组对象会在内存中（堆）开辟一整块连续的空间, 数组的长度一旦确定就不能修改
     * @Author lele
     * @param args :
     * @return void
     * @throws
     * @Date 2019/9/30 10:31
     */
    public static void main(String[] args) {
        // 一维数组
        // 声明数组
        int[] ids;
        // 静态初始化：数组的初始化和数组元素的赋值操作同时进行
        ids = new int[]{1001, 1002, 1003, 1004, 1005, 1006};
        // 动态初始化：数组的初始化和数组元素的赋值操作分开进行， 先初始化长度， 再赋值
        String[] names = new String[5];
        // 通过数组的角标（索引）来调用数组元素获取值或赋值
        System.out.println(ids[1]);
        names[0] = "big shabi...";

        // 数组长度
        System.out.println("ids数组长度：" + ids.length);

        // 遍历数组
        for (int id : ids) {
            System.out.println(id);
        }

        // 遍历数组
        for (String name : names) {
            System.out.println(name);
        }

        // 二维数组：元素的类型是一个一维数组, 打印第一层数据的时候发现都是引用地址， 第二层才存的真实数据
        // 从数组底层的运行机制来看， 其实不存在多维数组， 在内存中还是一块连续的内存空间
        // 静态初始化
        int[][] nos;
        nos = new int[][]{{1, 2, 3}, {4, 5, 6}};
        // 动态初始化
        int[][] sexs = new int[2][2];
        sexs[0][0] = 100;
        // 调用二维数组元素
        System.out.println(nos[0][1]);

        // 遍历二维数组
        for (int[] no : nos) {
            for (int i : no) {
                System.out.println(i);
            }
        }
        for (int[] sex : sexs)
            for (int s : sex) {
                System.out.println(s);
            }
    }
}
