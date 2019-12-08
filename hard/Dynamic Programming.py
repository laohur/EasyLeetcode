1. Maximum Product Subarray

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


import bisect
from typing import List

class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        n=len(nums)  # untili
        pos=nums[0]
        neg=nums[0]
        peak=nums[0]
        for i in range(1,n):
            pos0=nums[i]*pos
            neg0=nums[i]*neg
            pos=max(nums[i],pos0,neg0)
            neg=min(nums[i],pos0,neg0)
            peak=max(pos,peak)
        return peak



if __name__ == '__main__':
    nums = [2,3,-2,4]
    nums=[-2,0,-1]
    nums=[-4,-3,-2]
    sln=Solution()
    d=sln.maxProduct(nums)
    print(d)

2.Decode Ways

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


import bisect
from typing import List

class Solution:
    def numDecodings(self, s: str) -> int:
        if len(s)==0 or s[0]=='0':
            return 0
        ways=[0]*len(s)
        ways[0]=1

        for i in range(1,len(s)):
            if i+1<len(s) and s[i+1]=='0':
                ways[i]=ways[i-1]
                i+=1
                ways[i]=ways[i-1]
                continue
            merge_char=False


            if s[i]=='0':
                if s[i-1] not in ['1','2']:
                    return 0
                ways[i]=ways[i-2] if i-2>=0 else 1
                continue
            elif s[i-1:i+1]<='26' and s[i-1:i+1]>='11':
                su = 1 if i - 2 < 0 else ways[i - 2]
                ways[i]=ways[i-1]+su
                continue
            ways[i]=ways[i-1] if i-2>=0 else 1

        return ways[-1]

if __name__ == '__main__':
    s= "226"
    # s='100'
    # s='110'
    # s='10'
    # s='227'
    # s="12120"
    sln=Solution()
    d=sln.numDecodings(s)
    print(d)

2. Best Time to Buy and Sell Stock with Cooldown

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

    You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]

import bisect
from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        if n < 2:
            return 0
        buy = [0] * n
        buy[0] = -prices[0]
        sell = [0] * n
        for i in range(1,n):
            sell[i] = max(sell[i - 1], buy[i - 1] + prices[i])
            r = sell[i - 2] if i >= 2 else 0
            buy[i] = max(buy[i - 1], r - prices[i])
        return sell[-1]


if __name__ == '__main__':
    # prices = [1, 2, 3, 0, 2]
    prices=[1,2]
    sln = Solution()
    d = sln.maxProfit(prices)
    print(d)
3.Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
import bisect
import math
from typing import List


class Solution:
    def numSquares(self, n: int) -> int:
        while n%4==0:
            n/=4
        if n%8==7:
            return 4
        a=0
        while(a**2<=n):
            b=int((n-a**2)**0.5)
            if b**2==n:
                return 1
            if a**2+b**2==n:
                return 2
            a+=1
        return 3
if __name__ == '__main__':
    # prices = [1, 2, 3, 0, 2]
    prices=[1,2]
    sln = Solution()
    d = sln.numSquares(6)
    print(d)

import bisect
import math
from typing import List


class Solution:
    def numSquares(self, n: int) -> int:
        # n=int(n)
        while n%4==0:
            n//=4
        dp=[-1]*(n+1)
        dp[0]=0
        for i in range(n+1):
            for j in range(1,n+1):
                index=i+j**2
                if index>n:
                    break
                if dp[index]<0:
                    dp[index]=dp[i]+1
                else:
                    dp[index]=min(dp[index],dp[i]+1)
        return dp[-1]

if __name__ == '__main__':
    # prices = [1, 2, 3, 0, 2]
    prices=[1,2]
    sln = Solution()
    d = sln.numSquares(13)
    print(d)
5.Word Break

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false


import bisect
import math
from typing import List


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        words=set(wordDict)
        n=len(s)
        if n==0:
            return True
        breaked=[False]*n
        i=0

        for i in range(n):
            if i>0 and not breaked[i-1]:
                continue
            for w in wordDict:
                if i+len(w)>n:
                    continue
                if s[i:i+len(w)]==w:
                    breaked[i+len(w)-1]=True
            i+=1
        return breaked[-1]
if __name__ == '__main__':
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]

    s="applepenapple"
    wordDict = ["apple", "pen"]
    sln = Solution()
    d = sln.wordBreak(s,wordDict)
    print(d)
6.Word Break II

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]



from typing import List

class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        n=len(s)
        wordDict = set(wordDict)
        if n==0:
            []
        if len(wordDict) == 0:
            return []
        if n==1 and s in wordDict:
            return [s]
        def breaked(s: str, wordDict: List[str]) -> bool:
            n=len(s)
            if n==0:
                return True
            breaked=[False]*n
            i=0

            for i in range(n):
                if i>0 and not breaked[i-1]:
                    continue
                for w in wordDict:
                    if i+len(w)>n:
                        continue
                    if s[i:i+len(w)]==w:
                        breaked[i+len(w)-1]=True
                i+=1
            return breaked
        tokened=breaked(s,wordDict)
        if not tokened[-1]:
            return []
        re=[]
        def dfs(s,start,head):
            if start==n:
                re.append(head)
            for i in range(start,n):
                if tokened[i] :
                    w=s[start:i+1]
                    if w in wordDict :
                        dfs(s,i+1,head+[w])

        dfs(s,0,[])
        for i in range(len(re)):
            re[i]=' '.join(re[i])
        return re

if __name__ == '__main__':
    s = "catsanddog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]

    # s = "catsanddog"
    # wordDict = ["cat", "cats", "and", "sand", "dog"]
    # s='a'
    # wordDict=[]
    # s="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    # wordDict=["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    sln = Solution()
    d = sln.wordBreak(s,wordDict)
    print(d)
7.Burst Balloons

Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

    You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
    0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

from typing import List


class Solution:
    def maxCoins(self, nums: List[int]) -> int:
        nums=[1]+nums+[1]
        n=len(nums)
        dp=[]
        for i in range(n):
            dp.append([0]*n)

        def burst(nums,dp,start,end):
            if start>end:
                return 0
            if dp[start][end]>0:
                return dp[start][end]
            re=0
            for k in range(start,end+1):
                re=max( re, nums[start-1]*nums[k]*nums[end+1]+ burst(nums,dp,k+1,end)+burst(nums,dp,start,k-1)  )
            dp[start][end]=re
            return re
        re=burst(nums,dp,1,n-2)
        return re

if __name__ == '__main__':

    nums=[3,1,5,8]
    sln = Solution()
    d = sln.maxCoins(nums)
    print(d)
