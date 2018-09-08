  3Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result=new LinkedList();
        if(nums==null || nums.length<3)  return result ;
        Arrays.sort(nums);
        int n=nums.length;
        for(int i=0; i<n-2; i++){
            if(i>0 && nums[i]==nums[i-1]  )  continue;
            find(nums,nums[i],i+1,n-1,result);   //固定i位
        }
        return result;
    }

    public static void find(int[] nums, int now, int left, int right,List<List<Integer>> result){
        while(left<right){
            // System.out.println(Arrays.asList(new Integer[] { now,nums[left], nums[right]}).toString()) ;
            if(now+nums[left]+nums[right]==0){
                result.add( Arrays.asList(new Integer[] { now, nums[left], nums[right]})  );
                while( left<right && nums[left]==nums[left+1] ) left++;
                while( left<right && nums[right]==nums[right-1] )  right--;
                left++;
                right--;
            }else if(now+nums[left]+nums[right]<0){
                left++;
            }else{
                right--;
            }

        }
    }
}


2.Set Matrix Zeroes

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]

Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]

Follow up:

    A straight forward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
若递归，则几乎全为0
class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix==null || matrix.length==0 ) return ;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] column = new boolean[n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j]==0) {
                    row[i]=true;
                    column[j]=true;
                }
            }
        }
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(row[i] || column[j] ) matrix[i][j]=0; 
            }
        }
    }
}

3. Group Anagrams

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:

    All inputs will be in lowercase.
    The order of your output does not matter.
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new LinkedList();
        if(strs==null || strs.length==0)  return result;
        int len = strs.length;
        String[] strs2=new String[len];
        for(int i=0; i<len; i++){
            strs2[i]=anagram(strs[i]);
        }
        boolean[] visited=new boolean[len];
        for(int i=0; i<len; i++){
            // System.out.println(i+" i "+strs[i]);
            if(visited[i])  continue;
            visited[i]=true;
            List<String> list=new LinkedList<>();
            list.add(strs[i]);
            for(int j=i+1; j<len; j++){
                if(visited[j])  continue;
                // System.out.println(j+" j "+strs[j]);
                if(strs2[i].equals(strs2[j]))  {
                    list.add(strs[j]);
                    visited[j]=true;
                }
            }
            result.add(list);
        }
        return result;
    }
    public String anagram(String a){
        if(a.length()==0) return "";
        char[] s=a.toCharArray();
        Arrays.sort(s);
        return new String(s);
    }
}

4. Longest Substring Without Repeating Characters

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", which the length is 3.

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0)  return 0;
        int n=s.length();
        int longest=1;
        for(int i=0; i<n; i++){
            Set<Character> set = new HashSet<>();
            for(int j=i; j<n; j++){
                if(!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                } else       break;
            }
            longest=set.size()>longest?set.size():longest;
        }
        return longest;
    }
}


5.  Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"

class Solution {
    public String longestPalindrome(String s) {
        if(s==null || s.length()<=1) return s;
        int len=s.length();
        int longest=0;
        int start=0;
        int end=0;
        for( int i=0; i<len; i++){
            int length=0;  //临时长度  
            for(int j=1; i-j>=0 && i+j<len; j++){
                if(s.charAt(i-j)==s.charAt(i+j)) length++;
                else break;
            }
            if(2*length+1>longest){
                longest=2*length+1;
                start=i-length;
                end=i+length;
            }
            length=0;  //临时长度
            for(int j=0; i-j>=0 && i+1+j<len; j++){
                if(s.charAt(i-j)==s.charAt(i+1+j)) length++;
                else break;
            }
            if(2*length>longest){
                longest=2*length;
                start=i-length+1;
                end=i+length;
            }

        }
        System.out.println(start+" "+end);
        return s.substring(start,end+1);
    }
}

6.Increasing Triplet Subsequence

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

    Return true if there exists i, j, k
    such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.

Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true

Example 2:

Input: [5,4,3,2,1]
Output: false


class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums==null || nums.length<3)  return false;
        int left=0x7fffffff;
        int middle=0x7fffffff;
        for(int num:nums){
            if(num<=left) left=num;
            else if(num<=middle) middle=num;
            else return true;
        }
        return false;
    }
}

