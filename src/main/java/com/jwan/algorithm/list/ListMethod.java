package com.jwan.algorithm.list;

import com.jwan.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ListMethod {

    /**
     * 链表逆序
     * 206. Reverse Linked List
     *
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head==null) return null;
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp!=null){
            stack.push(temp);
            temp = temp.next;
        }
        head = stack.peek();
        while (!stack.isEmpty()) {
            ListNode t = stack.pop();
            if (stack.isEmpty()) t.next = null;
            else t.next = stack.peek();
        }
        return head;
    }


    /**
     *
     * 2个排序链表的归并
     * 21. Merge Two Sorted Lists
     *
     * You are given the heads of two sorted linked lists list1 and list2.
     *
     * Merge the two lists in a one sorted list. The list should be
     * made by splicing together the nodes of the first two lists.
     *
     * Return the head of the merged linked list.
     *
     * Input: list1 = [1,2,4], list2 = [1,3,4]
     * Output: [1,1,2,3,4,4]
     * Example 2:
     *
     * Input: list1 = [], list2 = []
     * Output: []
     * Example 3:
     *
     * Input: list1 = [], list2 = [0]
     * Output: [0]
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        List<ListNode> buff = new ArrayList<>();
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    buff.add(list1);
                    list1 = list1.next;
                }else{
                    buff.add(list2);
                    list2 = list2.next;
                }
            } else if (list1 != null) {
                buff.add(list1);
                list1 = list1.next;
            }
            else if (list2!= null) {
                buff.add(list2);
                list2 = list2.next;
            }



        }
        list1 = buff.get(0);
        while (!buff.isEmpty()){
            ListNode temp = buff.remove(0);
            if (buff.isEmpty()) temp.next = null;
            else temp.next = buff.get(0);
        }
        return list1;
    }

    /**
     * k个排序链表的归并
     * 23. Merge k Sorted Lists
     *
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     *
     * Merge all the linked-lists into one sorted linked-list and return it.
     *
     *
     *
     * Example 1:
     *
     * Input: lists = [[1,4,5],[1,3,4],[2,6]]
     * Output: [1,1,2,3,4,4,5,6]
     * Explanation: The linked-lists are:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * merging them into one sorted list:
     * 1->1->2->3->4->4->5->6
     * Example 2:
     *
     * Input: lists = []
     * Output: []
     * Example 3:
     *
     * Input: lists = [[]]
     * Output: []
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0 || lists[0] == null) return null;

        List<ListNode> buff = new ArrayList<>();
        ListNode l = null;

        while (lists[0]!=null){
            mergeKListsHelper(lists);
            buff.add(lists[0]);
            if (lists[0].next != null){
                lists[0] = lists[0].next;
            }else {
                lists[0] = null;
                for (int i = 1; i < lists.length; i++) {
                    if (lists[i] != null){
                        lists[i-1] = lists[i];
                        lists[i] = null;
                    }
                }
            }
        }

        l = buff.get(0);
        while (!buff.isEmpty()){
            ListNode temp = buff.remove(0);
            if (buff.isEmpty()) temp.next = null;
            else temp.next = buff.get(0);
        }
        return l;
    }

    public void mergeKListsHelper (ListNode[] lists) {
        int len = 0;
        while (len < lists.length && lists[len] != null) {
            len++;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len-1-i; j++) {
                if (lists[j].val > lists[j+1].val) {
                    ListNode temp = lists[j];
                    lists[j] = lists[j+1];
                    lists[j+1] = temp;
                }
            }
        }
    }

    /**
     * 链表求交点
     * 160. Intersection of Two Linked Lists
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode listA = headA;
        ListNode listB = headB;
        while (listA != listB){
            if (listA == null) listA = headA;
            else listA = listA.next;
            if (listB == null) listB = headB;
            else listB = listB.next;
        }

        return listA;
    }

    /**
     * 判断是否有交点
     * 141 Linked List Cycle
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     *
     * There is a cycle in a linked list if there is some node in the list that can be reached
     * again by continuously following the next pointer. Internally, pos is used to denote the
     * index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
     *
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     *
     * 题解：快慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        do {
            if (fast == null || fast.next == null || fast.next.next == null ) return false;
            else fast = fast.next.next;
            if (slow == null || slow.next == null) return false;
            else slow = slow.next;
        }while (fast != slow);

        return true;
    }

    /**
     * 链表求环
     * 142. Linked List Cycle II
     * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
     *
     * Do not modify the linked list.
     *
     * 题解：快慢指针；找到交点后，交点到环入口的步数等于起点到环入口的步数
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        do {
            if (fast == null || fast.next == null || fast.next.next == null ) return null;
            else fast = fast.next.next;
            if (slow == null || slow.next == null) return null;
            else slow = slow.next;
        }while (fast != slow);

        fast = head;

        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }


    /**复杂链表的复制 https://leetcode.com/problems/copy-list-with-random-pointer/
     *
     * Copy List with Random Pointer
     *
     * A linked list of length n is given such that each node contains an additional random pointer,
     * which could point to any node in the list, or null.
     *
     * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
     * where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
     *
     * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for
     * the corresponding two nodes x and y in the copied list, x.random --> y.
     *
     * Return the head of the copied linked list.
     *
     * The linked list is represented in the input/output as a list of n nodes. Each node is represented
     * as a pair of [val, random_index] where:
     *
     * val: an integer representing Node.val
     * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null
     * if it does not point to any node.
     * Your code will only be given the head of the original linked list.
     *
     * 题解：https://github.com/CyC2018/CS-Notes/blob/master/notes/35.%20%E5%A4%8D%E6%9D%82%E9%93%BE%E8%A1%A8%E7%9A%84%E5%A4%8D%E5%88%B6.md
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        copyRandomListStepOne(head);
        copyRandomListStepTwo(head);
        return copyRandomListStepThree(head);
    }

    //第一步，在每个节点的后面插入复制的节点。
    public void copyRandomListStepOne(Node head) {
        Node list = head;
        while (list != null) {
            Node temp = new Node(list.val);
            //insert
            temp.next = list.next;
            list.next = temp;

            list = temp.next;
        }
    }

    //第二步，对复制节点的 random 链接进行赋值。
    public void copyRandomListStepTwo(Node head) {
        Node list = head;
        while (list != null) {
            if (list.random != null)
            list.next.random = list.random.next;
            list = list.next.next;
        }
    }

    //第三步，拆分。
    public Node copyRandomListStepThree(Node head) {

        Node copy = head.next;
        Node cur = head;
        while (cur.next != null) {
            Node next = cur.next;
            cur.next = next.next;
            cur = next;
        }

        return copy;
    }
}
