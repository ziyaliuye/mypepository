package main.cn.cnm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@MyAnnotation(value = "hello")
public class Test {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updatelist(list);
        System.out.println(list);
    }

    public static void updatelist(List list) {
        list.remove(2);
    }
}
