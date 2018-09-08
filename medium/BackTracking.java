1. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.


import java.util.LinkedList;
import java.util.List;

class Solution {
    public static char[][] phone = new char[10][];

    Solution(){
        phone = new char[10][];
        phone[2] = new char[]{'a','b','c'};
        phone[3] = new char[]{'d','e','f'};
        phone[4] = new char[]{'g','h','i'};
        phone[5] = new char[]{'j','k','l'};
        phone[6] = new char[]{'m','n','o'};
        phone[7] = new char[]{'p','q','r','s'};
        phone[8] = new char[]{'t','u','v'};
        phone[9] = new char[]{'w','x','y','z'};
    }
   
    public List<String> letterCombinations(String digits) {
//        ArrayList<char[]> phone = new ArrayList<>(10);
//        phone.set(2,{"a","b","c"};

        char[] chars = digits.toCharArray();
        List<String> result = new LinkedList<>();
        if(digits==null || chars.length==0)  return result;
        result.add(digits);
        for(int i=0; i<digits.length(); i++){
            result = cycles(result,i);
        }
        return result;
    }
    
    //逐位替换数字为字母
    public List<String> cycle(String str, int index){
        char[] word = str.toCharArray();
        List<String> result =new LinkedList<>();
        char c = str.charAt(index);
        if(c>'9' || c<'0')  return result;
        char[] key = phone[c-'0'];
        for(int i=0; i<key.length; i++){
            word[index] = key[i];
            result.add(new String(word));
        }
        return result;
    }
    public List<String> cycles(List<String> strs, int index){
        List<String> result =new LinkedList<>();
        if(strs==null || strs.isEmpty())  return strs;
        for(String str : strs){
            List<String> one = cycle(str,index);
            result.addAll(one);
        }
        return result;
    }
}

2.Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        if(n<=0)  return result;
        Set<String> set = new HashSet<>();
        set.add("()");
        for(int i=2; i<=n; i++){
            set = insert(set);
        }
        result.addAll(set);
        return result;
    }

    public Set<String> insert(Set<String> strs){
        Set<String> newset = new HashSet<>();
        for(String str:strs){
            for(int i=0; i<=str.length(); i++){
                StringBuilder sb = new StringBuilder(str);
                newset.add(new String(sb.insert(i,"()")));
            }
        }
       return newset;
    }

}

3. Permutations

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
3.1
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;

        List<Integer> list = new ArrayList<Integer>();
        dfs(nums, list, result);

        return result;
    }

    private void dfs(int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) continue;
            list.add(nums[i]);
            dfs(nums, list, result);
            list.remove(list.size() - 1);
        }
    }
}

算了好几次超时
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new LinkedList<Integer>());
        for(int i=0; i<nums.length; i++){
            List<List<Integer>>  current = new ArrayList<>();
            for(List<Integer> list:result){
                for(int j=0; j<=list.size(); j++){
                    list.add(j,nums[i]);   //插入法
                    List<Integer> tmp = new ArrayList<>(list);
                    current.add(tmp);
                    list.remove(j);   //节省内存
                }
            }
            result = current;
        }
        return result;
    }

    public static final  void swap(int[] a, int i, int j){
        if(i<0 || j<0 || i>=a.length || j>=a.length)  return;
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}
递归实现  
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
                if (nums == null || nums.length == 0) return result;
        dfs(nums,list,result);
        return result;
    }

    public static void dfs(int[] nums,List<Integer> list, List<List<Integer>> result ){
        if(list.size()==nums.length){
            result.add(new ArrayList<Integer>(list));   //必须强制声明转换，然而写不报错，实际为空
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(list.contains(nums[i]))  continue;    //穷据递归插入
            list.add(nums[i]);
            dfs(nums,list,result);
            list.remove(list.size()-1);
        }
    }

4. Subsets

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
位图表示有没有
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int k =(int)Math.pow(2,n);
        List<List<Integer>> sets=new LinkedList<>();
        for(int i=0; i<k; i++){
            List<Integer> list=new LinkedList<>();
            for(int j=0; j<n; j++){
                if( (i&(1<<j))!=0 )  list.add(nums[j]);
            }
            sets.add(list);
        }
        return  sets;
    }
}

5.Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.


class Solution {
    public boolean exist(char[][] board, String word) {
        if(board==null)  return false;;
        int m=board.length;
        if(m==0)  return false;
        int n=board[0].length;
        if(n==0)  return false;
        boolean[][] visited=new boolean[m][n];
        char[] chars=word.toCharArray();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(find(board,visited,i,j,chars,0))  return true;
            }
        }
        return false;
    }
    boolean find(char[][] board, boolean[][] visited,int row,int column,char[] chars, int index){
        if(index==chars.length)  return true;
        int m=board.length;
        int n=board[0].length;
        if(row<0 || column<0 || row>=m ||column>=n || visited[row][column] || board[row][column]!=chars[index] )   return false;
        visited[row][column] =true;
        if(find(board,visited,row-1,column,chars,index+1))  return true;
        if(find(board,visited,row+1,column,chars,index+1))  return true;
        if(find(board,visited,row,column-1,chars,index+1))  return true;
        if(find(board,visited,row,column+1,chars,index+1))  return true;
        visited[row][column]=false;
        return false;
    }
}