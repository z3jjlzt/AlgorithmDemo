package com.kkk.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构造哈夫曼树
 * Created by kkk on 2016/6/23.
 */
public class hfmTest {
    public static class HfmNode<E> implements Comparable<HfmNode> {
        E data;//数据
        double weight;
        HfmNode left;
        HfmNode right;

        public HfmNode(E data, double weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "HfmNode{" +
                    "weight=" + weight +
                    ", data=" + data +
                    ", right=" + right +
                    ", left=" + left +
                    '}';
        }


        @Override
        public int compareTo(HfmNode o) {
            if (this.weight > o.weight)
                return 1;
            else if (this.weight == o.weight)
                return 0;
            else
                return -1;
        }
    }

    /**
     * while(nodes.size > 1){
     * 1.Collections.sort(nodes)排序。
     * 2.构造新节点parent ,权值为最大的两个节点之和。
     * 3.添加左右节点
     * 4.nodes移除最大两个节点，加入新节点parent
     * }
     *
     * @param nodes 初始节点数组
     * @return 构造的哈夫曼树
     */
    public static HfmNode createTree(List<HfmNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HfmNode left = nodes.get(nodes.size() - 1);
            HfmNode right = nodes.get(nodes.size() - 2);
            HfmNode parent = new HfmNode("", left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void main(String[] args) {
        List<HfmNode> list = new ArrayList<>();
        HfmNode A = new HfmNode("A", 9);
        HfmNode B = new HfmNode("B", 5);
        HfmNode C = new HfmNode("C", 7);
        HfmNode D = new HfmNode("D", 2);
        HfmNode E = new HfmNode("E", 6);
        HfmNode F = new HfmNode("F", 4);
        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);
        list.add(E);
        list.add(F);
        HfmNode tar = createTree(list);
        System.out.println(tar);
    }

}
