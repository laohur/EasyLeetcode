

Trees
1.  Maximum Depth of Binary Tree

class Solution {
public:
    int maxDepth(TreeNode* root) {
        if(!root) return 0;
        return 1+max(maxDepth(root->left),maxDepth(root->right));
    }
};
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

2.  Validate Binary Search Tree
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isValidBST(TreeNode* root) {
        return validateBST(root,INT_MIN,INT_MAX);
    }
    bool validateBST(TreeNode* root, int minVal, int maxVal){
        if(!root)  return true;
        if(root->val<minVal || root->val>maxVal )  return false;
        return validateBST(root->left,minVal,root->val) && validateBST(root->right,root->val,maxVal);
    }
};

中序遍历
bool isValidBST(TreeNode8 root){
    int curMax=INT_MIN;
    return validateBST(root,curMax);
}
bool validateBST(TreeNode* root, int& curMax){
    if(!root) return true;
    if(!validateBST(root->left,curMax)) return false;
    if(curMax>=root->val) return false;
    curMax=root->val;
    return validateBST(root->right,curMax);
}

2.  Validate Binary Search Tree
//老爱出一些边界值，逼的我用long
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
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        if( root.left==null && root.right==null )  return true;
        return valid(root,Long.MIN_VALUE,Long.MAX_VALUE);      
    }
    public boolean valid(TreeNode root,long low, long high){
        if(root==null)  return true;
        if( root.val<=low || root.val>=high ) return false;
        return valid(root.left,low,root.val) && valid(root.right,root.val,high);
    }
}

3.  Symmetric Tree
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
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return valid(root.left,root.right);
    }
    public boolean valid(TreeNode left, TreeNode right){
        if(left==null && right==null)  return true;
        if( left==null || right==null ||left.val!=right.val)  return false;
        return valid(left.left,right.right)  && valid(left.right,right.left);
    }
}

4.  Binary Tree Level Order Traversal
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode>  tlist=new LinkedList<TreeNode>();
        List<List<Integer>> lists=new LinkedList<List<Integer>>();  
        if(root==null)  return lists;    
        tlist.add(root);
        List<Integer> ilist=new LinkedList<Integer>();
        ilist.add(root.val);
        lists.add(ilist );
        while( !tlist.isEmpty() ){
            tlist=nextLevel(tlist);
            ilist=new LinkedList<Integer>();
            for(int i=0; i<tlist.size(); i++){
                if(tlist.get(i)!=null)  ilist.add(tlist.get(i).val);
            }
            if(!ilist.isEmpty())  lists.add(ilist);
        }
        return lists;
    }
    public List<TreeNode> nextLevel( List<TreeNode> list0) {
        if(list0==null)  return null;
        List<TreeNode>  list1=new LinkedList<TreeNode>();
        for( int i=0; i<list0.size(); i++ ){
            TreeNode node=list0.get(i);
            if(node==null) continue;
            if(node.left!=null)  list1.add(node.left);
            if(node.right!=null)  list1.add(node.right);
        }
        return list1;
    }

}

5.  Convert Sorted Array to Binary Search Tree
有序，有结构
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null  || nums.length==0)  return null;
        return sortedArrayToBST(nums,0,nums.length-1);
    }
    public TreeNode sortedArrayToBST( int[] nums, int left, int right ){
        if(left>right)  return null;
        int middle=(left+right)/2;
        System.out.println(middle);
        TreeNode root=new TreeNode(nums[middle]);
        root.left=sortedArrayToBST(nums,left,middle-1);
        root.right=sortedArrayToBST(nums,middle+1,right);
        return root;
    }
}