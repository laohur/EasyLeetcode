1.Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// * Definition for a binary tree node.
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
public class Codec {
    String spliter=",";
    String NULL="#";
    void buildString(StringBuilder sb, TreeNode root){
        if(root==null){
            sb.append(NULL).append(spliter);
            return;
        }
        sb.append(root.val).append(spliter);
        buildString(sb,root.left);
        buildString(sb,root.right);
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        buildString(sb,root);
        return sb.toString();
    }
    TreeNode buildTree(Deque<String> nodes){
        String node=nodes.remove();
        if(node.equals(NULL))  return null;
        TreeNode root=new TreeNode(Integer.valueOf(node));
        root.left=buildTree(nodes);
        root.right=buildTree(nodes);
        return root;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes =new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

2.Insert Delete GetRandom O(1)

Design a data structure that supports all following operations in average O(1) time.

    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
import java.util.*;

class RandomizedSet {
    Map<Integer,Integer> meta;
    List<Integer> data;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        meta=new HashMap<>();
        data=new LinkedList<>();
        random=new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(meta.keySet().contains(val))  return false;
        meta.put(val,data.size());
        data.add(val);
        // System.out.println("18: insert "+val+" ");
        // System.out.println("data:"+data.toString());
        // System.out.println("meta:"+meta.toString());
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!meta.keySet().contains(val))  return false;
        int tmp=data.get(data.size()-1);
        int index=meta.get(val);
        // System.out.println("32: remove "+val);
        // System.out.println("data:"+data.toString());
        // System.out.println("meta:"+meta.toString());
        // System.out.println("swap: "+val+" "+tmp);
//         索引值都要换
        data.set(index,tmp);
        meta.put(tmp,meta.get(val));
        data.remove(data.size()-1);
        meta.remove(val);
        // System.out.println("data1:"+data.toString());
        // System.out.println("meta1:"+meta.toString());
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int num=random.nextInt(data.size());
        return data.get(num);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */