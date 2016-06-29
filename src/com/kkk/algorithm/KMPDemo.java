package com.kkk.algorithm;

/**
 * Created by kkk on 2016/4/19.
 */
public class KMPDemo {

    /**
     * @param s    source
     * @param p    子串
     * @return 下标index ,未找到返回-1
     */
    int KMP(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int i = 0;//s游标
        int j = 0;//子串游标
        while (sLen - i >= pLen - j && j < pLen) {//当子串游标小于子串长度
            if (j == -1 || s.charAt(i) == p.charAt(j)) {//如果子串游标在起点
                i++;
                j++;
            } else {
                j = fail(j, p);
            }
        }
        if (j == pLen) {
            return (i - pLen);
        }
        return -1;
    }

    /**
     * 返回值一定小于j
     * @param j p子串 开始匹配位置
     * @param p 匹配串
     * @return p回朔点下标
     */
    int fail(int j, String p) {
        int num = 0;
        if (j == 0) {
            return -1;
        }
        for (int i = 0; i < j - 1; i++) {//对比j-1次
            int len = i + 1;
            int left = 0, right = j - len;//2个对比字符串起始位置
            while (left < len) {//循环对比len次
                if (p.charAt(left) == p.charAt(right)) {
                    left++;
                    right++;
                } else//不等就跳出循环
                    break;
            }
            if (left == len)//如果最后left右移了len，说明两字符串相等
                num = len;
        }
        return num;
    }


    public static void main(String[] args) {
        KMPDemo kmpDemo = new KMPDemo();
        System.out.println(kmpDemo.KMP("123456abcabcabbabcabcabca", "abffcabcab"));

    }
}
