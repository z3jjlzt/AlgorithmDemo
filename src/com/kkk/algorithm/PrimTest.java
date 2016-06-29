package com.kkk.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 最小生成树普里姆算法
 * Created by kkk on 2016/6/26.
 */
public class PrimTest {

    /**
     * 1.加入起始点，初始化nesrest,lowcost,mark
     * while{
     * 1.修正nearest和lowcost
     * 2.加入下一个点}
     *
     * @param sides   边集合 == -1 表示 无这条边
     * @param dot_num 顶点个数
     * @param start   起始顶点
     */
    void Prim(int[][] sides, int dot_num, int start) {
        int[] nearest = new int[dot_num];//下一个未加入的最近的顶点
        int[] lowcost = new int[dot_num];//下一个未加入的最近的顶点到已访问集合的最短距离
        boolean[] mark = new boolean[dot_num];//标记顶点是否已访问
        int[] order = new int[dot_num];//辅助输出数组

        init(dot_num, nearest, lowcost, mark);
        mark[start] = true;
        nearest[start] = start;
        lowcost[start] = 0;
        order[0] = start;

        int k = start;//已访问的待考察的点K
        for (int i = 0; i < dot_num - 1; i++) {// 修正
            for (int j = 0; j < dot_num; j++) {
                if (sides[k][j] > -1 && mark[j] == false && sides[k][j] < lowcost[j]) {
                    lowcost[j] = sides[k][j];
                    nearest[j] = k;
                }
            }

            int min = Integer.MAX_VALUE;
            for (int j = 0; j < dot_num; j++) {
                if (!mark[j] && lowcost[j] < min) {
                    min = lowcost[j];
                    k = j;
                }
            }
            mark[k] = true;
            order[i + 1] = k;
        }

        for (int i = 0; i < nearest.length; i++) {
            System.out.println("(" + order[i] + "," + nearest[order[i]] + "," + lowcost[order[i]] + ")");
        }
    }

    private void init(int dot_num, int[] nearest, int[] lowcost, boolean[] mark) {
        for (int i = 0; i < dot_num; i++) {
            nearest[i] = -1;
            lowcost[i] = Integer.MAX_VALUE;
            mark[i] = false;
        }
    }

    public static void main(String[] args) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("e://in1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fis);
        System.out.println("input dot num :");
        int num = sc.nextInt();

        int[][] sidess = new int[num][num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                sidess[i][j] = -1;
            }
        }

        System.out.println("input side num :");
        int num_side = sc.nextInt();
        int tmpA, tmpB, tmpV;
        for (int i = 0; i < num_side; i++) {
            tmpA = sc.nextInt();
            tmpB = sc.nextInt();
            tmpV = sc.nextInt();
            sidess[tmpA][tmpB] = tmpV;
            sidess[tmpB][tmpA] = tmpV;
        }

        new PrimTest().Prim(sidess, num, 0);
    }

}
//        for (int i = 0; i < vlen - 1; i++) {//循环加入n-1条边
//            int nxtV = -1;//下一个加入的顶点
//            for (int j = 0; j < vlen; j++) {//j 已访问 K 未访问
//                if (mark[j]) {
//                    for (int k = 0; k < vlen; k++) {
//                        if (sides.contains(new Side(j, k, 0)) && mark[k] == false) {
//                            int index = sides.indexOf(new Side(j, k, 0));
//                            if (sides.get(index).weight < lowcost[i]) {
//                                lowcost[i] = sides.get(index).weight;
//                                nearest[i] = k;
//                                nxtV = k;
//                            }
//                        }
//                    }
//                }
//            }
//            if (nxtV != -1) {
//                mark[nxtV] = true;
//                System.out.println("add" + nxtV);
//            }
//        }