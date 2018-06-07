package com.kkk.algorithm;

/**
 * Created by kkk on 2016/8/30.
 */
public class NQueue {
    int a = 0;
    /**
     * @param n   num of queue
     * @param x   当前行的游标
     * @param arr
     */
    void Nqueues(int n, int x, int[] arr) {
        for (int i = 0; i < n; i++) {// 从第一行出发到最后一行结束
            if (check(x, i, arr)) {
                arr[x] = i;
                if (x == n - 1) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(arr[j] + " ");
                    }
                    System.out.println(a++);
                }
                else {
                    Nqueues(n, x + 1, arr);
                }
            }
        }
    }

    /**
     * @param x 当前行的游标
     * @param i 当前列的下标
     * @param arr
     * @return
     */
    private boolean check(int x, int i, int[] arr) {
        for (int j = 0; j < x; j++) {
            if (arr[j] == i || Math.abs(arr[j] - i) == Math.abs(j - x)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = -1;
        }
        new NQueue().Nqueues(8, 0, arr);
    }
}
