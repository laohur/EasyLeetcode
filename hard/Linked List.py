1.Merge k Sorted Lists

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        if lists == None:
            return None
        result = ListNode(0)
        root = result
        empties = 0
        while empties < len(lists):
            min_idx = None
            empties = 0
            for i in range(len(lists)):
                if lists[i] == None:
                    empties += 1
                    continue
                if min_idx == None:
                    min_idx = i
                    # print(lists[min_idx])
                if lists[i].val <= lists[min_idx].val:
                    min_idx = i
            if min_idx != None:
                result.next = ListNode(lists[min_idx].val)
                result = result.next
                lists[min_idx] = lists[min_idx].next
        return root.next
超时 用堆排序   归并分治
from heapq import *


from heapq import *


class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        if lists == None:
            return None
        a = []
        ans = None
        p = ans
        for i in range(len(lists)):
            if lists[i]:
                a.append((lists[i].val, i))
        heapify(a)
        while a:
            (val, idx) = heappop(a)
            if not p:
                p = ListNode(val)
                ans = p
            else:
                p.next = ListNode(val)
                p = p.next
            lists[idx] = lists[idx].next
            if lists[idx]:
                heappush(a, (lists[idx].val, idx))
        return ans

2. Sort List

Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4

Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5

java
import java.util.concurrent.atomic.AtomicInteger;

class ListNode {
    int val;
    ListNode next;


    ListNode(int x) {
        val = x;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public static void main(String[] args) {
        int[] nums = {4, 2, 1, 3};
//        int[] nums = {3,2,4};
        ListNode head = new ListNode(nums[0]);
        ListNode tail = head;
        for (int i = 1; i < nums.length; i++) {
            tail.next = new ListNode(nums[i]);
            tail = tail.next;
        }
        tail = sortList(head);
        while (tail != null) {
            System.out.println(tail.val);
            tail = tail.next;
        }
    }
//1.取两队首,归并排序 2.队长1->2i

    public static ListNode sortList(ListNode head) {
        ListNode left=head;
        ListNode right=null;
        for (int i = 1; left!=null; i *= 2) {  // i=list length
            left=head;
            boolean inner=false;
            right=nextN(left,i);
            if(right==null)
                break;
            while(right!=null && left!=null ){
                left=merge(left,right,i);
                right=nextN(left,i);
            }

        }
        return head;
    }

    static ListNode nextN(ListNode start,int n){
        ListNode now=start;
        for(int i=0;i<n;i++){
            if(now!=null)
                now=now.next;
            else
                return null;
        }
        return now;
    }

    static ListNode merge(ListNode left, ListNode right, int length) {  //return tail
        ListNode middle=right;
        ListNode now=left;


        int n=0;
        if(left.val>right.val){
            int value=left.val;
            left.val=right.val;
            right.val=value;
        }
        while(left!=middle && right!=null){
            if(left.val<=right.val){
                now.next=left;
                left=left.next;
            }else{
                now.next=right;
                right=right.next;
                n++;
            }
            if(n>length)
                return right;
            now=now.next;
        }
        return now;
    }
}

多值返回麻烦，练习C++
#include <iostream>
struct ListNode {
      int val;
      ListNode* next;
      ListNode(int x) : val(x), next(NULL) {}
  };

//计数不如断开
ListNode* splitN(ListNode* start,int n){
    while(--n && start)
        start=start->next;
    ListNode* target=start?start->next: nullptr; //倒数第二
    if(start) start->next= nullptr;
    return target;
}
//给出一条链表的中的左右链表起点，两链表归并排序，返回首尾
std::pair<ListNode*,ListNode* >merge(ListNode* left, ListNode* right){
    ListNode dummy(0);
    ListNode* tail=&dummy;
    while(left && right){
        if(left->val>right->val)
            std::swap(left,right);
        tail->next=left;
        left=left->next;
        tail=tail->next;
    }
    tail->next=left?left:right;  //左捷径
    while(tail->next) tail=tail->next;  //配合捷径
    return {dummy.next, tail};
}

class Solution {
public:
    ListNode* sortList(ListNode* head) {
        if(head== nullptr || head->next== nullptr)
            return head;
        ListNode* tail= head;
        int len=1;
        while(tail=tail->next)
            len++;
        ListNode* dummy =new ListNode(0);
        dummy->next=head;
        ListNode* left;
        ListNode* right;
        ListNode* now;
        for(int i=1;i<len;i*=2){
            now =dummy->next;
            tail=dummy;
            while(now){
                left=now;
                right=splitN(now,i);
                now=splitN(right,i);  //断开+循环 同时干
                auto pair=merge(left,right);
                tail->next=pair.first; //续接
                tail=pair.second;
            }
        }
        return dummy->next;
    }
};

int main() {
    int nums[4] = {4, 2, 1, 3};
//        int[] nums = {3,2,4};
    ListNode* head = new ListNode(nums[0]);
    ListNode* tail = head;
    for (int i = 1; i < 4; i++) {
        tail->next = new ListNode(nums[i]);
        tail = tail->next;
    }
    Solution sln;
    tail = sln.sortList(head);
    while (tail != nullptr) {
        printf(" %d ",tail->val);
        tail = tail->next;
    }    return 0;
}


3.Copy List with Random Pointer

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

 

Example 1:

Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.

 

Note:

    You must return the copy of the given head as a reference to the cloned list

更简单的做法是邻近复制插入再拆开

#include <iostream>
class Node {
public:
    int val;
    Node* next;
    Node* random;

    Node() {}

    Node(int _val, Node* _next, Node* _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
class Solution {
public:
    Node* copyRandomList(Node* head) {
        if(head== nullptr)
            return nullptr;
        Node* dummy=new Node(head->val, nullptr, nullptr);
        int len=addNext(head,dummy);
        addRandom(dummy,head,len);
        return dummy;
    }
    int addNext(Node* src,Node* head1){
        Node* a=src;
        Node* b=head1;
        int len=1;
        while(a->next!= nullptr){
            a=a->next;
            b->next=new Node(a->val, nullptr, nullptr);
            b=b->next;
            len++;
        }
        return len;
    }
    int location(Node* a, Node* head, int len){
        if(a->random== nullptr)
            return -1;
        int dis=0;
        Node* rand=head;
        for(;dis<len;dis++){
            if(a->random==rand)
                return dis;
            rand=rand->next;
        }
        return -2;
    }
    int addRandom(Node* head, Node* src,int len){
        if(src==nullptr)
            return -1;
        Node* a=head;
        Node* c =src;
        while(a!= nullptr){
            Node* b=head;
            int index=location(c,src,len);
            if(index==-1)
                b= nullptr;
            else{
                while(--index>=0)
                    b=b->next;
            }
            a->random=b;
            a=a->next;
            c=c->next;
        }
        return 0;
    }

};

int main() {
    int nums[4] = {4, 2, 1, 3};

    Node* a = new Node(1, nullptr, nullptr);
//    Node* b = new Node(2, nullptr, nullptr);
//    a->next=b;
//    a->random=b;
//    b->random=b;
    Node* t0;
    Solution sln;
    t0 = sln.copyRandomList(a);
    Node* t=t0;
    while (t != nullptr) {
        printf(" %d ",t->val);
        t = t->next;
    }    return 0;
}    