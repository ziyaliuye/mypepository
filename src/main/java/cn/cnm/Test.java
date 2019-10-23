package cn.cnm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < 301; i++) {
            list.add(new Book(1001, "倚天屠龙记", 199));
            list.add(new Book(1002, "射雕英雄传", 299));
            list.add(new Book(1003, "欢乐英雄", 99));
            list.add(new Book(1004, "浣花洗剑录", 399));
            list.add(new Book(1005, "七种武器", 599));
            list.add(new Book(1006, "神雕侠侣", 298));
            list.add(new Book(1007, "福尔摩斯探案集", 1998));
            list.add(new Book(1008, "金瓶梅", 2998));
            list.add(new Book(1009, "金鳞岂是池中物", 19));
            list.add(new Book(1009, "金鳞岂是池中物", 19));
            list.add(new Book(1010, "大傻子", 59998));
            list.add(new Book(1010, "大傻子", 59998));
            list.add(new Book(1011, "斗破苍穹", 36));
            list.add(new Book(1012, "斗罗大陆", 69));
            list.add(new Book(1013, "名侦探柯南", 7));
            list.add(new Book(1014, "笑傲江湖", 699));
            list.add(new Book(1015, "哆啦A梦", 899));
            list.add(new Book(1016, "Python从入门到放弃", 298));
            list.add(new Book(1017, "MySQL从删库到跑路", 398));
            list.add(new Book(1018, "Java从精通到不会", 598));
        }
        System.out.println(new Gson().toJson(list));
    }
}
