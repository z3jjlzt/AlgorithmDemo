package com.kkk.algorithm;

/**
 * Created by kkk on 2016/7/1.
 */
public class HanoiTest {
    void hanoi(int n ,int src,int tmp,int desc){
        if(n == 1){
            move(n,src,desc);
            return;
        }
        else {
            hanoi(n-1,src,desc,tmp);
            move(n,src,desc);
            hanoi(n-1,tmp,src,desc);

        }
    }

    private void move(int n, int from, int to) {
        System.out.println(n+" from" + from + "  to "+ to);
    }

    public static void main(String[] args) {
        new HanoiTest().hanoi(3,1,2,3);
    }
}
