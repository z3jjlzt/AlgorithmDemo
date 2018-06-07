package com.kkk.algorithm;

import java.util.*;

/**
 * 深度优先 广度优先搜索
 * Created by kkk on 2016/6/25.
 */
public class BFSTest {
    static class node {
        boolean isVisited = false;
        char txt;

        public node(char txt, boolean isVisited) {
            this.isVisited = isVisited;
            this.txt = txt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            node node = (node) o;
            return txt == node.txt;
        }

        @Override
        public int hashCode() {
            return Objects.hash(txt);
        }

        @Override
        public String toString() {
            return "node{" +
                    "isVisited=" + isVisited +
                    ", txt=" + txt +
                    '}';
        }
    }


    /**
     * 1.建立一个fifo队列queue 。加入开始节点start，标记start已访问
     * while(!queue.isEmpty()){
     *     1.取出队首top = queue.poll()
     *     2.把top的相邻的未访问的节点加入queue并标记
     * }
     * @param graph 节点的邻接链表
     * @param start 起点
     */
    void BFS(HashMap<Character, LinkedList<node>> graph, node start) {
        Queue<node> queue = new LinkedList<>();
        Queue<node> vq = new LinkedList<>();// is visited
        queue.add(start);
        vq.add(start);
        while (!queue.isEmpty()) {
            node top = queue.poll();//取出队首
            System.out.println(top.toString());
            LinkedList<node> adjList = graph.get(top.txt);//得到邻接链表
            for (node n : adjList) {
                if (!vq.contains(n)) {
                    queue.add(n);
                    vq.add(n);
                }
            }
        }
    }

    /**
     * 1.把起点start加入visited
     * 2.得到start的所有邻接点tmp，如果tmp未访问，以tmp为起点迭代dfs.
     * @param graph 邻接链表
     * @param start 起点
     * @param visited 标记是否访问
     */
    void DFS(HashMap<Character, LinkedList<node>> graph, node start, ArrayList<node> visited) {//递归方式
        visited.add(start);
        System.out.print(start.txt + "-");
        LinkedList<node> lnklst = graph.get(start.txt);
        for (int i = 0; i < lnklst.size(); i++) {
            node tmp = lnklst.get(i);
            if (!visited.contains(tmp)) {
                DFS(graph, tmp, visited);
            }
        }
    }

    /**
     * 1.新建mstack,start入栈。标记start已访问
     * 2.nxt = getNxtIndex
     * while(!mstack.isEmpty()){
     *     1.如果nxt不为空 ，nxt入栈，nxt标记已访问
     *     2.否则nxt出栈mstack.pop()
     *     3.如果栈不为空，nxt = getNxtIndex
     * }
     * @param graph
     * @param start
     */
    void DFSstack(HashMap<Character, LinkedList<node>> graph, node start) {//栈方式
        Stack<node> mstack = new Stack<>();
        mstack.push(start);
        ArrayList<node> isvisited = new ArrayList<>();
        isvisited.add(start);
        System.out.print(start+"-");
        node nxt = getNextIndex(isvisited, graph, start);
        while (!mstack.isEmpty()) {
            if(nxt != null){
                mstack.push(nxt);
                System.out.print(nxt.txt+"-");
                isvisited.add(nxt);
            }else {
                System.out.println("pop"+mstack.pop().txt);
            }
            if(!mstack.isEmpty()){
                nxt = getNextIndex(isvisited,graph,mstack.peek());
            }
        }
    }

    /**
     * 返回start节点的下一个未访问节点
     * @param isvisited 标记
     * @param graph
     * @param start
     * @return start节点的下一个未访问节点
     */

    private node getNextIndex(ArrayList<node> isvisited, HashMap<Character, LinkedList<node>> graph, node start) {
        LinkedList<node> lst = graph.get(start.txt);
        for (int i = 0; i < lst.size(); i++) {
            if (!isvisited.contains(lst.get(i))) {
                return lst.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<Character, LinkedList<node>> graph = new HashMap<>();
        LinkedList<node> node_0 = new LinkedList<>();
        node_0.add(new node('1', false));
        node_0.add(new node('B', false));
        node_0.add(new node('A', false));
        LinkedList<node> node_1 = new LinkedList<>();
        node_1.add(new node('0', false));
        // node_1.add(new node('B', false));
        node_1.add(new node('2', false));
        node_1.add(new node('5', false));
        LinkedList<node> node_B = new LinkedList<>();
        // node_B.add(new node('0', false));
        //  node_B.add(new node('1', false));
        //  node_B.add(new node('A', false));
        LinkedList<node> node_A = new LinkedList<>();
        node_A.add(new node('0', false));
        //  node_A.add(new node('B', false));
        node_A.add(new node('6', false));
        node_A.add(new node('9', false));

        LinkedList<node> node_2 = new LinkedList<>();
        node_2.add(new node('1', false));
        // node_2.add(new node('5', false));
        node_2.add(new node('3', false));


        LinkedList<node> node_5 = new LinkedList<>();
        node_5.add(new node('1', false));
        // node_5.add(new node('2', false));
        // node_5.add(new node('6', false));
        node_5.add(new node('4', false));
        LinkedList<node> node_6 = new LinkedList<>();
        node_6.add(new node('A', false));
        //  node_6.add(new node('5', false));
        //  node_6.add(new node('9', false));
        node_6.add(new node('7', false));
        LinkedList<node> node_9 = new LinkedList<>();
        node_9.add(new node('A', false));
        //   node_9.add(new node('6', false));
        node_9.add(new node('8', false));

        LinkedList<node> node_3 = new LinkedList<>();
        node_3.add(new node('2', false));
        //    node_3.add(new node('4', false));

        LinkedList<node> node_4 = new LinkedList<>();
        node_4.add(new node('5', false));
        //   node_4.add(new node('3', false));
        //  node_4.add(new node('7', false));

        LinkedList<node> node_7 = new LinkedList<>();
        node_7.add(new node('6', false));
        // node_7.add(new node('4', false));
        //  node_7.add(new node('8', false));

        LinkedList<node> node_8 = new LinkedList<>();
        node_8.add(new node('9', false));
        //   node_8.add(new node('7', false));

        graph.put('0', node_0);
        graph.put('1', node_1);
        graph.put('B', node_B);
        graph.put('A', node_A);
        graph.put('2', node_2);
        graph.put('5', node_5);
        graph.put('6', node_6);
        graph.put('9', node_9);
        graph.put('3', node_3);
        graph.put('4', node_4);
        graph.put('7', node_7);
        graph.put('8', node_8);
   //     new BFSTest().DFS(graph, new node('0', false), new ArrayList<>());
        new BFSTest().DFSstack(graph, new node('0', false));
       // new BFSTest().BFS(graph, new node('0', false));
    }
}
