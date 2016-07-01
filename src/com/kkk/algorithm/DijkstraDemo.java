package com.kkk.algorithm;

/**
 * 狄杰斯算法单源最短问题
 * Created by kkk on 2016/7/1.
 */
public class DijkstraDemo {
    /**
     * 1.初始化 d[],path[],add[]。d[start]=0,add[start]=true
     * 2.for(num -1次){
     * 1.getNext()得到下一个最近的点nxt
     * 2.if(nxt存在）{
     * 1.add[nxt]= true
     * 2.修正d[],path[]
     * }
     * }
     *
     * @param graph
     * @param start
     * @param num
     */
    void digkstra(int[][] graph, int start, int num) {
        int[] d = new int[num];//d[i]从点i到起点start的（当前）最短路径
        int[] path = new int[num];//path[i]从起点start到i点的最短路径上，位于i点前面那个点
        boolean[] add = new boolean[num];//标记数组

        for (int i = 0; i < num; i++) {//init
            d[i] = graph[start][i];
            if (i != start && d[i] < Integer.MAX_VALUE) path[i] = start;
            else
                path[i] = -1;
            add[i] = false;
        }
        d[start] = 0;
        add[start] = true;

        for (int i = 0; i < num - 1; i++) {
            int nxt = getNext(num, d, add);
            System.out.println("nxt is :" + nxt);
            if (nxt > -1) {
                add[nxt] = true;
                for (int j = 0; j < num; j++) {//修正
                    if (!add[j] && graph[nxt][j] != Integer.MAX_VALUE &&
                            d[nxt] + graph[nxt][j] < d[j]) {//如果j未加入且边[nxt][j]存在

                        d[j] = d[nxt] + graph[nxt][j];
                        path[j] = nxt;
                    }
                }
            }
        }
        for (int i = 0; i < num; i++) {
            int tmp = path[i];
            System.out.print(i + "<--");
            while (tmp != -1 && tmp != start) {
                System.out.print(tmp + "<--");
                tmp = path[tmp];
            }
            System.out.println(start + "");
        }

    }

    /**
     * 返回下一个未访问的最近的点
     *
     * @param num
     * @param d
     * @param add
     * @return 没有返回 -1
     */
    private int getNext(int num, int[] d, boolean[] add) {
        int min = Integer.MAX_VALUE;
        int nxt = -1;
        for (int j = 0; j < num; j++) {
            if (d[j] < min && !add[j]) {
                min = d[j];
                nxt = j;
            }
        }
        return nxt;
    }

    public static void main(String[] args) {
        int[][] graph = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
        graph[0][1] = 50;
        graph[0][2] = 10;
        graph[0][4] = 70;
        graph[1][2] = 15;
        graph[1][4] = 10;
        graph[2][0] = 20;
        graph[2][3] = 15;
        graph[3][1] = 20;
        graph[3][4] = 35;
        graph[4][3] = 30;
        graph[5][3] = 3;
        //       graph[0][1] = 10;
        //       graph[0][3] = 30;
        //       graph[0][4] = 100;
        //       graph[1][2] = 50;
        //       graph[2][4] = 10;
        //       graph[3][2] = 20;
        //       graph[3][4] = 60;
        new DijkstraDemo().digkstra(graph, 0, 6);
    }
}
