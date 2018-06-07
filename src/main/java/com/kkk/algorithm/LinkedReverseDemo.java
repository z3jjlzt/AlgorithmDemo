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
        return "Node{" +
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
        if(head == null ||head.next == null)
            return head;
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

    /**
     * 插入法，每次在首个元素后面插入下一个元素，最后再把第一个
     * 元素插入最后。
     * @param head
     * @return
     */
    public ListNode ReverseByInsert(ListNode head){
        if(head == null ||head.next == null)
            return head;
        int top = head.val;
        ListNode tar = new ListNode(head.val);
        head = head.next;
        ListNode tmp;
        while(head != null){
            tmp = new ListNode(head.val);
            tmp.next = tar.next;
            tar.next = tmp;
            head = head.next;
        }
        tar = tar.next;
        tmp = tar;
        while(tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = new ListNode(top);
        return tar;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        ListNode tmp = l;
        for (int i = 2; i <= 5; i++) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        System.out.println(new LinkedReverseDemo().ReverseByInsert(l));
    }
}
