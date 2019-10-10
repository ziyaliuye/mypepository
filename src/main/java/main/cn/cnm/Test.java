package main.cn.cnm;

import java.util.Calendar;
import java.util.Date;

@MyAnnotation(value = "hello")
public class Test {

    public static void main(String[] args) {
        String s1 = "da";
        String s2 = "shabi";
        String s3 = "dashabi";
        String s4 = s1 + "shabi";
        String s5 = "da" + "shabi";
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
    }
}
