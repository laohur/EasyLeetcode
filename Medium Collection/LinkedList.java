1.  Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null || l2==null)      return null;
        ListNode left=l1;
        ListNode right=l2;
        ListNode dummy=new ListNode( -1  ) ;
        ListNode tail=dummy;
        int carry=0;
        while(left!=null || right!=null){
            int a=left==null?0:left.val;
            int b=right==null?0:right.val;
            int sum=a+b+carry;
            carry=sum/10;
            tail.next=new ListNode(sum%10);
            tail=tail.next;
            if(left!=null)  left=left.next;
            if(right!=null)  right=right.next;
        }
        if(carry>0) tail.next=new ListNode(carry);
        return dummy.next;
    }

}

2.Odd Even Linked List

Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL

Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL

Note:

    The relative order inside both the even and odd groups should remain as it was in the input.
    The first node is considered odd, the second node even and so on ...


理解题意

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next==null) return head;
        ListNode even = head, even_head=even, odd =head.next,odd_head=odd,orig=odd.next;
        while (orig != null) {
            // System.out.println("even"+orig.val);
            even.next=orig;
            even=even.next;
            orig=orig.next;
            if (orig==null)  {
                // System.out.println("null"+even.val+" odd " +odd.val +" odd.next"+odd.next.val);
                odd.next=null;
                break;
            }
            // System.out.println("odd"+orig.val);
            odd.next=orig;
            odd=odd.next;
            orig=orig.next;
        }
            // System.out.println("odd2"+orig.val);
        if(even==null)  even=odd_head;
        else even.next=odd_head;
        // while(odd_head!=null){
        //                 System.out.println("odd3"+odd_head.val);
        //     odd_head=odd_head.next;
        // }
        return even_head;
    }
}

Java Solution
要求两个都不为空
public ListNode oddEvenList(ListNode head) {
    if(head == null) 
        return head;
 
    ListNode result = head;
    ListNode p1 = head;
    ListNode p2 = head.next;
    ListNode connectNode = head.next;
 
    while(p1 != null && p2 != null){
            ListNode t = p2.next;
            if(t == null)
                break;
 
            p1.next = p2.next;
            p1 = p1.next;
 
            p2.next = p1.next;
            p2 = p2.next;
    }
 
    p1.next = connectNode;
 
    return result;
}

3. Intersection of Two Linked Lists

Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3

begin to intersect at node c1.

Notes:

    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
    Your code should preferably run in O(n) time and use only O(1) memory.

Credits:
Special thanks to @stellari for adding this problem and creating all test cases.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null)  return null;
        ListNode p=headA;
        ListNode q=headB;
        int len1=len(p);
        int len2=len(q);
        int shorten=0;
        if(len1<len2){
            shorten=len2-len1;
            p=headB;
            q=headA;
        }else{
            shorten=len1-len2;
        }
        for(int i=0; i<shorten; i++){
            p=p.next;
        }
        while(p!=null){
            if(p==q)  return p;
            else{
                p=p.next;
                q=q.next;
            }
        }
        return null;

    }
    public int len(ListNode p){
        int length=0;
        while(p!=null){
            length++;
            p=p.next;
        }
        return length;
    }
}