package com.kkk.algorithm;

/**
 * Created by kkk on 2016/6/22.
 */
public class heapMax {
    /**
     * 创建最小堆
     *
     * @param arr
     */
    void create(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }
    }

    /**
     * 1.取出堆顶元素arr[0]
     * 2.用末尾元素替换堆顶元素
     * 3.向下调整
     * 4.堆大小=0结束
     *
     * @param arr
     */
    void sort(int[] arr) {
        int len = arr.length;//当前堆大小
        int[] tar = new int[len];
        for (int i = 0; i < arr.length; i++) {
            tar[i] = arr[0];
            swap(arr, 0, len - 1);
            len--;
            if (len > 1)
                adjust(arr, 0, len);
        }
        for (int i = 0; i < tar.length; i++) {
            System.out.println(tar[i]);
        }
    }

    /**
     * 向下调整算法
     * while(true){
     * 1.标记start为tmp
     * 2.如果有左孩子且值小于tmp，tmp = left
     * 3.如果有右孩子且值小于tmp，tmp = right
     * 4.如果 tmp != start，交换tmp和start,start = tmp,
     * 否则break
     * }
     *
     * @param arr
     * @param start 要调整的节点下标
     * @param len   当前堆大小
     */
    void adjust(int[] arr, int start, int len) {
        if (start >= 0) {
            while (true) {
                int tmp = start;
                int left = 2 * start + 1;
                int right = 2 * start + 2;
                if (left <= len - 1 && arr[left] < arr[tmp]) {
                    tmp = left;
                }
                if (right <= len - 1 && arr[right] < arr[tmp]) {
                    tmp = right;
                }
                if (tmp != start) {
                    swap(arr, start, tmp);
                    start = tmp;
                } else {
                    break;
                }
            }
        }
    }

    private void swap(int[] arr, int i, int min) {
        int tmp = arr[i];
        arr[i] = arr[min];
        arr[min] = tmp;
    }

    public static void main(String[] args) {
        heapMax heapMax = new heapMax();
        int[] arr = {18, 30, 50, 62, 46, 75, 70, 24, 99, 43, 68, 97};
        heapMax.create(arr);
        heapMax.sort(arr);
    }

}
