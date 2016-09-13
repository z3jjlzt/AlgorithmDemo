package com.kkk.algorithm;

        import java.util.Scanner;

/**
 * Created by kkk on 2016/9/12.
 */
public class xxx {
    public static String rev(int src){
        StringBuffer sb = new StringBuffer();
        sb.append(src);
        sb.reverse();
        int start = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (!(sb.charAt(i) == '0')) {
                start = i;
                break;
            }
        }
        String r = sb.substring(start,sb.length());
        return r;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        String sa = rev(a);
        String sb = rev(b);
        int c = Integer.valueOf(sa) + Integer.valueOf(sb);
        System.out.println(rev(c));

    }
}
