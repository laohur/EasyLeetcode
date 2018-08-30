1.  Climbing Stairs
class Solution {
    public int climbStairs(int n) {
        if(n==0 || n==1 || n==2)  return n;
        int[] dp=new int[n+1];
        //这三条是核心
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        for(int i=3; i<n+1; i++){
            dp[i]=dp[i-1]+dp[i-2];  ///两种上一步情形:一步、两步
        }
        return dp[n];
    }
}

2.Best Time to Buy and Sell Stock
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.

Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
这是多次交易总计利润
class Solution {
    public int maxProfit(int[] prices) {
        int profit=0;
        for(int i=1; i<prices.length; i++){
            if( prices[i]>prices[i-1])   profit+=prices[i]-prices[i-1];
        }
        return profit;
    }
}
至多一次交易，选出最大值，最小值
class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length<2)  return 0;
        int profit=0;
        int buy=prices[0];
        for(int i=1; i<prices.length; i++){
            if(prices[i]<buy)     buy=prices[i];
            if(profit<prices[i]-buy)  profit=prices[i]-buy;  //买还不不买利润更高
        }
        return profit;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int profit=0;
        int buy=Integer.MAX_VALUE;
        for(int price:prices){
            buy=Math.min(buy,price);
            profit=Math.max(profit,price-buy);
        }
        return profit;
    }
}

3.  Maximum Subarray
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
穷举 太太复杂了
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums==null || nums.length==0)  return 0;
        // int start,end;
        // for(start=0;start<nums.length; start++){
        //     if(nums[start]>0)  break;
        // }
        // for(end=nums.length-1; end>=0; end--){
        //     if(nums[end]>0)  break;
        // }
        // if(start>end)  return 0;
        // if(start==end)  return nums[start];

        // int[] sums=new int[end-start+1];
        int start=0;
        int end=nums.length-1;
        int total=nums[start];
        for(int i=start; i<=end; i++){
            int sum=nums[i];
            for(int j=end; j>=start; j--){
                int accu=nums[i];
                for(int k=i; k<=j; k++){
                    accu+=nums[k];
                }
                if(accu>sum) sum=accu;
            }
            if(sum>total) total=sum;
        }
        return total;
    }
}
区域 全局标注很清晰
public class Solution {
    public int maxSubArray(int[] nums){
        if(nums==null || nums.length==0)  return 0;
        int global=nums[0];
        int local=nums[0];
        for(int i=1; i<nums.length; i++){
            local=Math.max(nums[i],local+nums[i]);  //若变小，则取当前值
            global=Math.max(global,local);
        }
        return global;
    }
}

4.  House Robber
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
class Solution {
    public int rob(int[] nums) {
        if(nums==null || nums.length==0)  return 0;
        if(nums.length==1)  return nums[0];
        if(nums.length==2)  return Math.max(nums[0],nums[1]);
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(dp[0],nums[1]);
        for(int i=2; i<nums.length; i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]); //打劫，还是不打劫这家
        }
        return dp[dp.length-1];
    }
}

class Solution {
    public int rob(int[] nums) {
        if(nums==null || nums.length==0)  return 0;
        int[][] dp=newint[nums.length+1][2];
        for(int i=1; i<=nums.length; i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1]=nums[i-1]+dp[i-1][0];
        }
        return global;        
    }
}