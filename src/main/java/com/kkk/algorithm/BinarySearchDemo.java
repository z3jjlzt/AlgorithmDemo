package com.kkk.algorithm;

/**
 * Created by kkk on 2016/4/18.
 */
public class BinarySearchDemo {
    public BinarySearchDemo(int[] arr) {
        binarySearch1(0, arr, 0, arr.length - 1);
    }

    private void binarySearch(int x, int[] arr, int left, int right) {//递归算法
        if (left < right) {
            int mid = (right - left) / 2 + left;
            if (x < arr[mid]) {
                System.out.println("<" + mid);
                binarySearch(x, arr, left, mid);
            } else if (x > arr[mid]) {
                System.out.println(">" + mid);
                binarySearch(x, arr, mid + 1, right);
            } else {
                System.out.println(mid);
            }
        } else {
            System.out.println("nofind");
        }

    }

    private void binarySearch1(int x, int[] arr, int left, int right) {//循环算法
        while (left <= right) {//注意等于号
            int mid = (right - left) / 2 + left;
            System.out.println("left:  " + left+"  right:  "+right);
            if (x < arr[mid]) {
                System.out.println("<" + mid);
                right = mid - 1;
            } else if (x > arr[mid]) {
                System.out.println(">" + mid);
                left = mid + 1;
            } else {
                System.out.println(mid);
                break;
            }
        }
        if(left>right){
            System.out.println("nofound");
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = 2 * i + 1;
        }

        new BinarySearchDemo(arr);
    }

}
