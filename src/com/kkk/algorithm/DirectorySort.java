package com.kkk.algorithm;

import java.util.*;

/**
 * 12345
 * 找到最后一个正序排列的两个数,p,q
 * 再找到p后最后一个大于p的数tmp,交换p,tmp
 * 把tmp之后的逆序,结束本轮.
 * Created by kkk on 16-9-14.
 */
public class DirectorySort {
    static ArrayList<String> sort(String src) {
        ArrayList<String> res = new ArrayList<>();
        char[] chars = src.toCharArray();
        Arrays.sort(chars);
        res.add(new String(chars));
        int p, q, tmp;
        p = q = tmp = 0;
        int len = chars.length;
        while (true) {
            for (int i = len - 1; i > 0; i--) {//find p,q
                if (chars[i] > chars[i - 1]) {
                    p = i - 1;
                    q = i;
                    break;
                }
            }
            for (int i = len - 1; i > p; i--) {//find tmp
                if (chars[i] > chars[p]) {
                    tmp = i;
                    break;
                }
            }
            if (p != tmp) {
                swap(chars, p, tmp);
                reverse(chars, q, len - 1);
                res.add(new String(chars));
                //System.out.println(Arrays.toString(chars));
            } else {//if p == tmp; 说明已经最大
                break;
            }
            p = tmp = 0;
        }

        return res;
    }

    private static void reverse(char[] chars, int q, int len) {
        while (q <= len) {
            swap(chars, q++, len--);
        }
    }

    static void swap(char[] chars, int i, int j) {
        char tmp;
        tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
    /*
        12345
       递归，每次取下一个字符，插入之前的子串中，用set取重。
     */

    static ArrayList<String> sort1(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        Set<String> tar = new HashSet<>();
        tar.add(chars[0] + "");
        StringBuffer sb = new StringBuffer();
        ArrayList<String> lst = new ArrayList<>();
        for (int i = 1; i < chars.length; i++) {
            f(chars[i], tar, i + 1,sb,lst);
        }
        Iterator<String> iterator = tar.iterator();
        while (iterator.hasNext())
            lst.add(iterator.next());
        Collections.sort(lst);
        return lst;

    }

    static void f(char x, Set<String> tar, int n, StringBuffer sb,ArrayList<String> lst ) {

        Iterator<String> iterator = tar.iterator();
        while (iterator.hasNext()) {
            lst.add(iterator.next());
        }
        tar.clear();
        char[] tchar;
        for (int m = 0; m < lst.size(); m++) {
            tchar = lst.get(m).toCharArray();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j)
                        sb.append(x);
                    if (j < n - 1)
                        sb.append(tchar[j]);
                }
                tar.add(sb.toString());
                //System.out.print(sb.toString() + " -- ");
                sb.delete(0, sb.length());
            }
        }
        lst.clear();
        //System.out.println("****");

    }


    public static void main(String[] args) {
        ArrayList<String> lst = sort("1234");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i));
        }
    }
}
