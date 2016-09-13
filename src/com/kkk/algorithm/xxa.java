package com.kkk.algorithm;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by kkk on 2016/9/12.
 */
public class xxa {
    public static void main(String[] args) {
        BigInteger b3 = new BigInteger("3");
        BigInteger b6 = new BigInteger("6");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
       // System.out.println(bi.pow(3));
         if (n == 1) {
             System.out.println("3");
             return;
         }
        if(n == 2){
            System.out.println("9");
            return;
        }
//        if(n == 3){
//            System.out.println("21");
//            return;
//        }
        int s = 1;
        BigInteger s1 = new BigInteger(s+"");
        for (int i = 2; i < n - 2; i++) {
                s1 = s1.multiply(new BigInteger(i+""));
        }
        BigInteger bs = new BigInteger(s+"");
        BigInteger a = b3.pow(n);
        BigInteger b = b3.pow(n - 3).multiply(b6).multiply(s1);
        System.out.println(a.subtract(b));

    }
}
