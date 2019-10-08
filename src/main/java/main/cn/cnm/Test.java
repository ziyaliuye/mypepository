package main.cn.cnm;

import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        Date sysdate = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(sysdate);
        rightNow.add(Calendar.MONTH, -1);
        String year = String.valueOf(rightNow.get(Calendar.YEAR));
        String month = String.valueOf(rightNow.get(Calendar.MONTH)).length() > 1 ? String.valueOf(rightNow.get(Calendar.MONTH) + 1) : "0" + (rightNow.get(Calendar.MONTH) + 1);
        System.out.println(year + month);
    }
}
