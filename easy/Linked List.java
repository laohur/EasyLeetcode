
Linked List
1.    Delete Node in a Linked List
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    void deleteNode(ListNode* node) {
        node->val=node->next->val;
        ListNode* tmp=node->next;
        node->next=tmp->next;
        delete tmp;
    }
};

2.  Remove Nth Node From End of List
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        if(head==NULL  || n<1)            return NULL;
        ListNode* cursor=head;
        ListNode* flag=head;

        for(int i=0; i<n; i++){
            cursor=cursor->next;
        } 
        if(!cursor) return head->next;
        while(cursor->next!=NULL){
            cursor=cursor->next;
            flag=flag->next;
        }
        ListNode* tmp=flag->next;
        flag->next=tmp->next;
        delete tmp;
        return head;
    }
};

3.  Reverse Linked List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev=head;
        ListNode current=null;
        while(head!=null){
            prev=head.next;
            head.next=current;
            current=head;
            head=prev;
        }
        return current;
    }
}

4.  Merge Two Sorted Lists
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3=new ListNode(-1);
        ListNode now=l3;
        while(l1!=null || l2!=null){
            if(l1!=null && l2!=null ){
                if(l2.val<l1.val){
                    now.next=l2;
                    l2=l2.next;
                }else{
                    now.next=l1;
                    l1=l1.next;
                }
                now=now.next;               
            }else if(l1!=null){
                now.next=l1;
                break;
            }else if(l2!=null){
                now.next=l2;
                break;
            }
        }
        return l3.next;
    }
}

5.  Palindrome Linked List

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null) return true;
        ListNode p=head;
        ListNode prev=new ListNode(head.val);
        while(p.next!=null){
            ListNode temp=new ListNode(p.next.val);
            temp.next=prev;
            prev=temp;
            p=p.next;
        }

        ListNode p1=head;
        ListNode p2=prev;
        while(p1!=null){
            if(p1.val!=p2.val) return false;
            p1=p1.next;
            p2=p2.next;
        }
        return true;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null)  return true;
        ListNode middle=findMiddle(head);
        middle.next=reverse(middle.next);
        ListNode p1=head,p2=middle.next;
        // while(p1!=null && p2!=null && p1.val==p2.val){
        while(p2!=null ){
            System.out.println(" p1 "+p1.val+" p2 "+p2.val);
            if(p1.val!=p2.val)  return false;
            p1=p1.next;
            p2=p2.next;
        }
        return true;
    }
    private ListNode findMiddle(ListNode head){
        if(head==null)  return null;
        ListNode slow=head, fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    private ListNode reverse(ListNode head){
        ListNode prev=null;
        ListNode current=null;
        while(head!=null){
            prev=head.next;
            head.next=current;
            current=head;
            head=prev;
        }
        return current;
    }
}

6.  Linked List Cycle
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null)  return false;
        ListNode slow=head;
        ListNode fast=head.next;
        while(fast!=null && fast.next!=null){
            if(slow==fast) return true;
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow==fast;       
    }
}