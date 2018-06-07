package com.kkk.algorithm;

/**
 * Created by kkk on 2016/8/12.
 */
public class DPTest {

    /**
     * d 为
     * @param arr
     * @return
     */
    public static int dp(int[] arr) {
        int sum = Integer.MIN_VALUE;
        int d = 0;
        for (int i = 0; i < arr.length; i++) {
            d = max(arr[i], arr[i] + d);
            System.out.println("d" + d);
            sum = max(sum, d);
        }
        return sum;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(dp(arr));
    }

}
