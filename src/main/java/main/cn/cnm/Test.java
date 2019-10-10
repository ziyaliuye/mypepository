package main.cn.cnm;

import java.util.Calendar;
import java.util.Date;

@MyAnnotation(value = "hello")
public class Test {

    public static void main(String[] args) {
        Integer in1 = 1;
        Integer in2 = 1;
        System.out.println(in1 == in2);

        Integer in3 = 1000;
        Integer in4 = 1000;
        System.out.println(in3 == in4);
    }
}
