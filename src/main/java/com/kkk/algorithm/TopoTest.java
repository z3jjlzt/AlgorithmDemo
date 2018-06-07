package com.kkk.algorithm;

import java.util.*;

/**
 * 拓扑排序 一个工程只有当它前面的活动都结束了才可以进行。即入度=0。
 * Created by kkk on 2016/6/26.
 */
public class TopoTest {
    /**
     * 找到每个节点的入度数目
     *
     * @param graph     key 节点 value 对应节点的邻接点集合
     * @param node_mark 标记每个节点的入度数和是否已经访问
     */
    void InDEgree(HashMap<Integer, LinkedList<Integer>> graph, ArrayList<node> node_mark) {
        Collection<LinkedList<Integer>> values = graph.values();
        for (LinkedList<Integer> lnk : values) {
            for (int nde : lnk) {
                node_mark.get(nde).num++;//入度+1
            }
        }
    }

    /**
     * 得到下一个入度=0且未访问的节点，这里从小到大排序
     *
     * @param node_mark 标记数组
     * @return 下一个入度=0且未访问的节点的下标 没有返回-1
     */
    int getNext(ArrayList<node> node_mark) {
        for (int i = 0; i < node_mark.size(); i++) {
            if (node_mark.get(i).num == 0 && node_mark.get(i).isV == false)
                return i;
        }
        return -1;
    }

    /**
     * 1.找一个入度nxt=0
     * while（nxt >= 0）{
     *     1.nxt标记为已访问
     *     2.所有nxt的邻接节点入度-1
     *     3.nxt < 0 退出
     * }
     * @param graph
     * @param arr
     */
    void topo(HashMap<Integer, LinkedList<Integer>> graph, ArrayList<node> arr) {
        InDEgree(graph, arr);
        int nxt = getNext(arr);
        while (nxt >= 0) {
            System.out.println(nxt);
            arr.get(nxt).isV = true;
            LinkedList<Integer> lnk = graph.get(nxt);
            for (int i = 0; i < lnk.size(); i++) {
                arr.get(lnk.get(i)).num--;//所有nxt的邻接节点入度-1
            }
            nxt = getNext(arr);
            if (nxt < 0)
                break;
        }
    }

    static class node {
        int num;
        boolean isV;

        public node(int num, boolean isV) {
            this.num = num;
            this.isV = isV;
        }

        @Override
        public String toString() {
            return "node{" +
                    "num=" + num +
                    ", isV=" + isV +
                    '}';
        }
    }


    public static void main(String[] args) {
        HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
        LinkedList<Integer> node_0 = new LinkedList<>();
        node_0.add(7);
        node_0.add(2);
        LinkedList<Integer> node_1 = new LinkedList<>();
        node_1.add(4);
        node_1.add(3);
        node_1.add(2);
        LinkedList<Integer> node_2 = new LinkedList<>();
        node_2.add(3);
        LinkedList<Integer> node_3 = new LinkedList<>();
        node_3.add(6);
        node_3.add(5);
        LinkedList<Integer> node_4 = new LinkedList<>();
        node_4.add(5);
        LinkedList<Integer> node_5 = new LinkedList<>();
        LinkedList<Integer> node_6 = new LinkedList<>();
        LinkedList<Integer> node_7 = new LinkedList<>();
        node_7.add(8);
        LinkedList<Integer> node_8 = new LinkedList<>();
        node_8.add(6);
        graph.put(0, node_0);
        graph.put(1, node_1);
        graph.put(2, node_2);
        graph.put(3, node_3);
        graph.put(4, node_4);
        graph.put(5, node_5);
        graph.put(6, node_6);
        graph.put(7, node_7);
        graph.put(8, node_8);

        ArrayList<node> narr = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            narr.add(new node(0, false));
        }
        new TopoTest().topo(graph, narr);
    }
}
