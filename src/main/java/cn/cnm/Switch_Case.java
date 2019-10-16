package cn.cnm;

public class Switch_Case {

    public static void main(String[] args) {
        // 当执行逻辑有重复时可以考虑合并逻辑， 可以提高阅读效率
        // 输出月份所属季度
        switch (5) {
            case 1:
            case 2:
            case 3:
                System.out.println("第一季度....");
                break;
            case 4:
            case 5:
            case 6:
                System.out.println("第二季度....");
                break;
            case 7:
            case 8:
            case 9:
                System.out.println("第三季度....");
                break;
            case 10:
            case 11:
            case 12:
                System.out.println("第四季度....");
                break;
            default:
                System.out.println("月份有误....");
                break;
        }
    }
}
