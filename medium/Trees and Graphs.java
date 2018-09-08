Binary Tree Inorder Traversal

Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]

Follow up: Recursive solution is trivial, could you do it iteratively?

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if(root==null)  return list;
        inOrder(root,list);
        return  list;
    }
    public void inOrder( TreeNode root, List<Integer> list ){
        if(root==null)  return ;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right,list);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root==null)  return list;
        TreeNode p = root; 
        while( p!=null || !stack.isEmpty() ){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else{
                p=stack.pop();
                list.add(p.val);
                p=p.right;
            }
        }
        
        return  list;
    }
    public void inOrder( TreeNode root, List<Integer> list ){
        if(root==null)  return ;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right,list);
    }
}

2.Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<TreeNode> list =new LinkedList<>() ;
        list.add(root);
        List<List<Integer>>  result = new LinkedList<>();
        if(root==null)  return result;
        int even = 1;
        while(!list.isEmpty()){
            result.add(values(list,even));
            list=next(list);
            even *= -1;
        }
        return result;
    }
    public List<TreeNode> next(List<TreeNode> nodes ){
        List<TreeNode> list =new LinkedList<>() ;
        if(nodes==null || nodes.size()==0)  return list;
        for(TreeNode node: nodes){
            if(node==null)  continue;
            if(node.left!=null)  list.add(node.left);
            if(node.right!=null)  list.add(node.right);
        }
        return list;
    }
    public List<Integer> values( List<TreeNode> nodes ,int even){
        List<Integer> list = new LinkedList<Integer>();
        if(even>0){
            for(int i=0; i<nodes.size(); i++){
                list.add(nodes.get(i).val);
            }
        }else{
            for(int i=nodes.size()-1; i>=0; i--) {
                list.add(nodes.get(i).val);
            }
        }
        return list;
    }
}

3.Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]

Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0 && inorder.length==0)  return null;
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    public TreeNode build(int[] preorder,int pl, int pr, int[] inorder, int il, int ir){
        if(pl>pr || il>ir)  return null;
        TreeNode root= new TreeNode(preorder[pl]);
        int location;
        for(location=il; location<=ir; location++ ){
            if(inorder[location]==preorder[pl])  break;
        }
        root.left=build(preorder,pl+1,pl+location-il,inorder,il,location-1);
        root.right=build(preorder,pl+location-il+1,pr,inorder,location+1,ir);
        return root;        
    }
}

4.Populating Next Right Pointers in Each Node

Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

    You may only use constant extra space.
    Recursive approach is fine, implicit stack space does not count as extra space for this problem.
    You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).

Example:

Given the following perfect binary tree,

     1
   /  \
  2    3
 / \  / \
4  5  6  7

After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null)  return ;
        List<TreeLinkNode> list = new LinkedList<>();
        list.add(root);
        while(list!=null || !list.isEmpty() ||list.size()==0){
            for(int i=0; i<list.size()-1; i++){
                list.get(i).next=list.get(i+1);
            }
            if(list.size()<1)  return;
            list.get(list.size()-1).next=null;
            list=next(list);
        }
    }
    public List<TreeLinkNode> next(List<TreeLinkNode> list){
        if(list==null)  return list;
        List<TreeLinkNode> result = new LinkedList<>();
        for(TreeLinkNode node : list){
            if(node.left!=null)  result.add(node.left);
            if(node.right!=null)  result.add(node.right);
        }
        return result;
    }

}

5. Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int[] result = new int[k+1];
        for(int e:result)  e=Integer.MAX_VALUE;
                System.out.println(Arrays.toString(result));
        for(int i=0; i<result.length; i++)  result[i]=Integer.MAX_VALUE;
                System.out.println(Arrays.toString(result));
        inOrder(root,result);
        return result[k-1];
    }
    public void inOrder(TreeNode root, int[] result){
        if(root==null)  return ;
        inOrder(root.left,result);
        result[result.length-1]=root.val;
        Arrays.sort(result);
       // System.out.println(Arrays.toString(result));
        inOrder(root.right,result);
    }
}

6.Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3




class Solution {
    public int numIslands(char[][] grid) {
        if(grid==null ||grid.length==0 )  return 0;
        int sum=0;
        int m = grid.length;
        int n=grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                // System.out.print(m+" "+n+" ");                
                // System.out.print(grid.length+" "+grid[0].length+ "  . ");
                // System.out.print(i+" "+j+ " ");
                // System.out.println(visited.length+" "+visited[0].length+ "  . ");
                if(grid[i][j]=='1' && !visited[i][j] ){
                    sum++;
                    dfs(grid,visited,i,j);
                }
            }
        }
        return sum;
    }

    public void dfs(char[][] grid, boolean[][] visited, int i, int j ){
        if(grid==null ||grid.length==0  )  return ;
        if(i<0 || i>=grid.length)  return;
        if(j<0 || j>=grid[0].length)  return;
        if(grid[i][j]=='0'  || visited[i][j])  return ;
        visited[i][j]=true;
        dfs(grid,visited,i-1,j);
        dfs(grid,visited,i+1,j);
        dfs(grid,visited,i,j-1);
        dfs(grid,visited,i,j+1);
    }


}