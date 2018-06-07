package com.kkk.algorithm;

/**
 * Created by kkk on 2016/4/17.
 */
public class BubbleMethod {//冒泡排序

    public BubbleMethod(int[] arr) {
        int len = arr.length;
        boolean noswap;
        for (int i = 0; i < len-1; i++) {//循环n-1次
             noswap=true;//本轮是否循环

            for (int j = len-1; j > i; j--){//从后向前循环
                if (arr[j] < arr[j-1]){
                    swap(arr,j,j-1);
                    noswap=false;
                }
            }
            if(noswap)
                break;
        }
        for (int k = 0; k < len; k++) {
            System.out.print(arr[k]+", ");
        }

    }
    private void swap(int[] arr,int i, int i1) {//注意传引用
        int tmp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = tmp;
    }
    public static void main(String[] args){
        int[] arr = {1,4,66,2,444,34,67,78,23,45,123,51,33,456,457};
        new  BubbleMethod(arr);
    }
}
