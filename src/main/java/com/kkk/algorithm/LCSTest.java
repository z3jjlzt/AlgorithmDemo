package com.kkk.algorithm;

/**
 * Created by kkk on 2016/8/30.
 */
public class LCSTest {
    /**
     * 先求出lcs的长度
     *
     * @param x
     * @param y
     * @return
     */
    int[][] lcslen(char[] x, char[] y) {
        int[][] arr = new int[x.length][y.length];
        for (int i = 0; i < x.length; i++) {
            arr[i][0] = 0;
        }
        for (int i = 0; i < y.length; i++) {
            arr[0][i] = 0;
        }
        for (int i = 1; i < x.length; i++) {
            for (int j = 1; j < y.length; j++) {
                if (x[i] == y[j]) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else if (arr[i - 1][j] >= arr[i][j - 1]) {
                    arr[i][j] = arr[i - 1][j];
                } else {
                    arr[i][j] = arr[i][j - 1];
                }
            }
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        return arr;
    }

    void lcs(int[][] arr, char[] x, char[] y, int i, int j) {
        if (i == 0 || j == 0)
            return;
        if (x[i] == y[j]) {
            System.out.println(x[i]);
            lcs(arr, x, y, i - 1, j - 1);
        } else if (arr[i - 1][j] >= arr[i][j - 1])
            lcs(arr, x, y, i - 1, j);
        else
            lcs(arr, x, y, i, j - 1);
    }


    public static void main(String[] args) {
        char[] x = {'0', 'a', 'b', 'c', 'b', 'd', 'a', 'b'};
        char[] y = {'0', 'b', 'd', 'c', 'a', 'b', 'a'};
        int[][] arr = new LCSTest().lcslen(x, y);
        new LCSTest().lcs(arr, x, y, x.length - 1, y.length - 1);
    }
}
