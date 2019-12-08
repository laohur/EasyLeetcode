1. LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

from typing import List


class Node:
    def __init__(self,key=0,val=0):
        self.key=key
        self.val=val
        self.prev=None
        self.next=None


class LRUCache:

    def __init__(self, capacity: int):
        self.capacity=capacity
        self.dict=dict()
        self.head,self.tail=Node(),Node()
        self.head.next=self.tail
        self.tail.prev=self.head

    def _remove(self,node):
        node.next.prev=node.prev
        node.prev.next=node.next

    def _append(self,node):
        self.tail.prev.next=node
        node.prev=self.tail.prev
        self.tail.prev=node
        node.next=self.tail

    def get(self, key: int) -> int:
        if key not in self.dict:
            return -1
        node=self.dict[key]
        self._remove(node)
        self._append(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key not in self.dict:
            node=Node(key,value)
            self.dict[key]=node
        else:
            node=self.dict[key]
            node.val=value
            self._remove(node)
        self._append(node)

        if len(self.dict)>self.capacity:
            key=self.head.next.key
            node=self.dict[key]
            del self.dict[key]
            # self.dict.pop(key)
            self._remove(node)
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

if __name__ == '__main__':
    lru= LRUCache(2)
    lru.put(1,1)
    lru.put(2,2)
    lru.get(1)
    lru.put(3,3)
    lru.get(2)
orderdict 
movetoend

2. Implement Trie (Prefix Tree)

Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true

Note:

    You may assume that all inputs are consist of lowercase letters a-z.
    All inputs are guaranteed to be non-empty strings.

class Trie:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.children = [None] * 26
        self.tail=False

    def insert(self, word: str) -> None:
        """
        Inserts a word into the trie.
        """
        if len(word) == 0:
            return
        idx=ord(word[0])-ord('a')
        if not self.children[idx]:
            self.children[idx] = Trie()
        if len(word)==1:
            self.children[idx].tail=True
        else:
            self.children[idx].insert(word[1:])

    def search(self, word: str) -> bool:
        """
        Returns if the word is in the trie.
        """
        if len(word)==0:
            return True
        idx=ord(word[0])-ord('a')
        if not self.children[idx]:
            return False
        if len(word) == 1:
            return self.children[idx].tail

        return self.children[idx].search(word[1:])

    def startsWith(self, prefix: str) -> bool:
        """
        Returns if there is any word in the trie that starts with the given prefix.
        """
        word=prefix
        if len(word) == 0 :
            return True
        idx=ord(word[0])-ord('a')
        if self.children[idx] is  None:
            return False
        return self.children[idx].startsWith(word[1:])
    
# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)

3.Flatten Nested List Iterator

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].

Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#
#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """

class NestedIterator:

    def __init__(self, nestedList: [NestedInteger]):
        self.list=[]
        self.flatten(nestedList)
        self.pos=0
        print(self.list)

    def flatten(self,nestedList: [NestedInteger]):
        for lst in nestedList:
            if lst.isInteger():
                self.list.append(lst.getInteger())
            else:
                self.flatten(lst.getList())
        
    def next(self) -> int:
        re=self.list[self.pos]
        self.pos+=1
        return re
    
    def hasNext(self) -> bool:
        return self.pos<len(self.list)
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

3.Find Median from Data Stream

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
For example,

[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.

 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

 

Follow up:

    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?


import bisect

class MedianFinder:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.list=[]

    def addNum(self, num: int) -> None:
        pos=bisect.bisect_left(self.list,num)
        self.list.insert(pos,num)
        # print(num,pos,self.list)
    def findMedian(self) -> float:
        i=len(self.list)//2
        j= (len(self.list)-1)//2
        # print(i,j,len(self.list))
        return (self.list[i]+self.list[j])/2



# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()