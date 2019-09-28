package cn.cnm;

/**
 * author lele
 * 需求：求1-100000之间质数个数
 * 质数：也叫素数,只能被1和它本身整除的自然数 例如 3、7
 */
public class OptimizeCycle {
    // 开始时间
    static long start = 0;
    // 结束时间
    static long end = 0;

    public static void main(String[] args) {
        // 原始版
        int count = 0;
        boolean isFlag = true;
        start = System.currentTimeMillis();
        for (int i = 2; i < 100000; i++) {
            // 循环比它小的数， 看是否有能整除的数
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isFlag = false;
                }
            }
            // 标识还是true表示该数为质数
            if (isFlag == true) {
                count++;
            }

            // 重置标识， 为下一次循环做准备
            isFlag = true;
        }
        System.out.println("质数个数：" + count);
        end = System.currentTimeMillis();
        System.out.println("执行耗时：" + (end - start));

        count = 0;
        isFlag = true;
        start = System.currentTimeMillis();

        // 极限优化版
        for (int i = 2; i < 100000; i++) {
            // 优化二：循环i时， 只需要循环从 2-开方i 之间的数即可
            // 例如循环到数9和11， 只需要循环 2-3 中是否有能整数的数，一种算法记住就行
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isFlag = false;
                    // 优化一：只有进来就说明当前数不是质数， 跳过
                    break;
                }
            }
            // 标识还是true表示该数为质数
            if (isFlag == true) {
                count++;
            }

            // 重置标识， 为下一次循环做准备
            isFlag = true;
        }
        System.out.println("质数个数：" + count);
        end = System.currentTimeMillis();
        System.out.println("执行耗时：" + (end - start));
    }
}
