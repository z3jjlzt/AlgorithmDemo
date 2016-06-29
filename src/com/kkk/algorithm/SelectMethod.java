package com.kkk.algorithm;

/**
 * 选择排序
 * Created by kkk on 2016/4/17.
 */
public class SelectMethod {
    public SelectMethod(int[] arr) {
        int len = arr.length;

        for (int k = 1; k < len; k++) {
            int index = k;//每次从下表k开始循环
            for (int j = k + 1; j < len; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            if (k != index)//如果本轮最小值未归位则交换
                swap(arr, k, index);
        }
        for (int k = 0; k < len; k++) {
            System.out.print(arr[k] + ", ");
        }
    }

    private void swap(int[] arr, int i, int i1) {//注意传引用
        int tmp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 66, 2, 444, 34, 67, 78, 23, 45};
        new SelectMethod(arr);
    }

}
