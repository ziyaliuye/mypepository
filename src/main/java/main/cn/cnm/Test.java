package main.cn.cnm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@MyAnnotation(value = "hello")
public class Test {

    public static void main(String[] args) {
        Mouse m1 = new Mouse("AA", 1001);
        Mouse m2 = new Mouse("BB", 1002);

    }

    public static void updatelist(List list) {
        list.remove(2);
    }
}
