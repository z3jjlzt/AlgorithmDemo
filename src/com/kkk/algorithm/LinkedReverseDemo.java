package com.kkk.algorithm;

/**
 * 单向链表反转
 * Created by kkk on 2016/9/13.
 */

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "com.kkk.algorithm.ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

public class LinkedReverseDemo {
    /**
     * 1->2->3->4->5
     * 初始化要把p.next置为null。否则会陷入死循环。
     * p始终指向待反转节点的前一个节点
     * q始终指向待反转节点
     * r始终指向待反转节点的下一节点
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        ListNode p = head;
        ListNode q = p.next;
        p.next = null;
        ListNode r;
        while (q != null) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return p;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        ListNode tmp = l;
        for (int i = 2; i <= 5; i++) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        System.out.println(new LinkedReverseDemo().ReverseList(l));
    }
}
