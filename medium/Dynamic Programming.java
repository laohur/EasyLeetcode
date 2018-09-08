1.Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length<=1) return true;
        int max=nums[0];  //累计总共能跑多远
        for(int i=0; i<nums.length; i++){
            if(max<=i && nums[i]==0)  return false;  //跑不动了
            if(nums[i]+i>max)  max=nums[i]+i;
            if(max>=nums.length-1)  return true;
        }
        return false;
    }
}

3. Coin Change

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1

Note:
You may assume that you have an infinite number of each kind of coin.

//类似背包问题
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount==0)  return 0;
        int[] dp=new int[amount+1];
        for(int i=1;i<=amount;i++)   dp[i]=amount+1;
        dp[0]=0;
        for(int i=1; i<=amount; i++){
            for(int coin:coins){
                if(coin<=i){
                    // System.out.println("12:"+i+" "+dp[i]+" "+dp[i-coin]+1);
                    dp[i]=Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }
}

4.Longest Increasing Subsequence

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 

Note:

    There may be more than one LIS combination, it is only necessary for you to return the length.
    Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
//https://www.programcreek.com/2014/04/leetcode-longest-increasing-subsequence-java/

import java.util.LinkedList;
import java.util.List;

class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length==0)  return 0;
        List<Integer> list = new LinkedList<>();
        for(int num:nums){
            if(list.isEmpty() || num>list.get(list.size()-1)){
                list.add(num);
                continue;
            }
//                      replace the element in the list which is the smallest but bigger than num
            int i=0;
            int j=list.size()-1;
            while(i<j){
                int mid=(i+j)/2;
                if(list.get(mid)<num)  i=mid+1;
                else j=mid;
            }  
            list.set(j,num);
        }
        return list.size();
    }
}