package com.kkk.algorithm;

/**
 * Created by kkk on 2016/4/18.
 */
public class QuickMethod {

    /**
     * @param arr
     * @param start
     * @param end
     */
    public static void quicksort(int[] arr, int start, int end) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            quicksort(arr, start, pivot - 1);
            quicksort(arr, pivot + 1, end);
        }
    }

    /**
     * 返回枢轴下标
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];//枢轴
        int i = start;
        int j = end;
        while (i < j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i < j)
                swap(arr, i, j);
        }
        if (arr[start] > arr[j])
            swap(arr, start, j);
        return j;
    }


    private static void swap(int[] arr, int i, int i1) {
        int tmp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = tmp;
    }

    /**
     * 归并排序
     * @param arr  源数组
     * @param tmp  辅助数组
     * @param start
     * @param end
     */
    public static void mergeSort(int[] arr, int[] tmp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, tmp, start, mid);
            mergeSort(arr, tmp, mid + 1, end);
            merge(arr, tmp, start, mid, end);
        }
    }

    /**
     * @param arr
     * @param tmp
     * @param start
     * @param mid
     * @param end
     */
    private static void merge(int[] arr, int[] tmp, int start, int mid, int end) {
        int i = start;//2个游标
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j])
                tmp[k++] = arr[i++];
            else
                tmp[k++] = arr[j++];
        }
        while (i <= mid) tmp[k++] = arr[i++];//如果左序列有剩
        while (j <= end) tmp[k++] = arr[j++];
        for (int l = 0; l < k; l++) {
            arr[start + l] = tmp[l];
        }
    }

    public static void main(String[] args) {
        int[] arr ={1, 4, 66, 2, 444, 34, 67, 78, 23, 45};
           QuickMethod.quicksort(arr,0,arr.length-1);
        //  com.kkk.algorithm.QuickMethod.mergeSort(arr,tmp,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
