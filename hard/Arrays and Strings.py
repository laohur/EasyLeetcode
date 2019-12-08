1.Product of Array Except Self

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        left = [1] * n
        tmp = 1
        for i in range(n):
            left[i] = tmp
            tmp *= nums[i]
        output = [1] * n
        tmp = 1
        for i in range(n):
            output[n - 1 - i] = left[n - 1 - i] * tmp
            tmp *= nums[n - 1 - i]
        return output


2.  Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        output=[]
        m=len(matrix)
        if m<=0:
            return output
        n=len(matrix[0])
        north=0
        south=m-1
        west=0
        east=n-1
        while north<=south and west<=east:
            for x in range(west,east+1):
                output.append(matrix[north][x])
            north += 1
            if north>south: break
            for x in range(north,south+1):
                output.append(matrix[x][east])
            east -= 1
            if east<west: break
            for x in range(east,west-1,-1):
                output.append(matrix[south][x])
            south-=1
            if south<north:break
            for x in range(south,north-1,-1):
                output.append(matrix[x][west])
            west+=1
        return output

3. 4Sum II

Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0


class Solution:
    def fourSumCount(self, A: List[int], B: List[int], C: List[int], D: List[int]) -> int:
        sum = 0
        dict=collections.defaultdict(int)
        for a in A:
            for b in B:
                dict[a+b]+=1
        for c in C:
            for d in D:
                sum+=dict[-(c+d)]

        return sum
4.Container With Most Water

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.



The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.



Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49

Python3

两边走向中间，宽度一次减少1，高度却至少增加1。

def area(height: List[int]):
    # 不要加边界判断，要暴露错误
    if len(height) <= 1:
        return 0
    a = len(height) - 1
    b = min(height[0], height[-1])
    s = a * b
    # print(height, a, b)
    return s


class Solution:

    def maxArea(self, height: List[int]) -> int:
        biggest = 0
        left, right = 0, len(height) - 1
        while left < right:
            s = area(height[left:right + 1])
            if s > biggest:
                biggest = s
            if height[left] <= height[right]:
                left += 1
            else:
                right -= 1
        return biggest

# class Solution0:

#     def maxArea(self, height: List[int]) -> int:
#         biggest = 0
#         # left, right = 0, len(height)
#         for left in range(len(height) - 1):
#             for right in range(left + 1, len(height) + 1):
#                 # print("start end", left, right)
#                 s = area(height[left:right])
#                 if s > biggest:
#                     biggest = s
#         return biggest

5. Game of Life

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]

Follow up:

    Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
    In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?


坑 pyyhon列表对象复制共用
from typing import List


class Solution:
    def gameOfLife(self, board: List[List[int]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        m = len(board)
        n = len(board[0])
        # flags = [[None] * n] * m  #共用对象
        flags = []
        # print(flags)
        for x in range(m):
            # print(x, flags)
            flags.append([None] * n)
            for y in range(n):
                count = lives(board, m, n, x, y)
                if board[x][y] == 1 and count < 2:
                    flags[x][y] = 0
                elif board[x][y] == 1 and count in [2, 3]:
                    flags[x][y] = 1
                elif board[x][y] == 1 and count > 3:
                    flags[x][y] = 0
                elif board[x][y] == 0 and count == 3:
                    flags[x][y] = 1
                else:
                    flags[x][y] = board[x][y]
                # print(x, y, "count", count, flags[x][y])
            # print(x, flags)

        for x in range(m):
            for y in range(n):
                board[x][y] = flags[x][y]


def lives(board: List[List[int]], m, n, x, y) -> int:
    count = 0
    for s in [x - 1, x, x + 1]:
        for t in [y - 1, y, y + 1]:
            if s == x and t == y:
                continue
            if s >= 0 and s < m and t >= 0 and t < n:
                if board[s][t] == 1:
                    count += 1
    return count


def main():
    a = [
        [0, 1, 0],
        [0, 0, 1],
        [1, 1, 1],
        [0, 0, 0]
    ]

    s = Solution()
    s.gameOfLife(a)
    print(a)


if __name__ == "__main__":
    main()

264. Ugly Number II
Medium

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note:  

    1 is typically treated as an ugly number.
    n does not exceed 1690.
class Solution1 {
    public static int nthUglyNumber(int n) {
        if (n <= 0) {
            return -1;
        }
        int[] ugly = new int[n];
        ugly[0] = 1;

        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < n; i++) {
            //2 3 4 5 6 8...只可能是这些丑数因子相乘
            ugly[i] = Math.min(ugly[i2] * 2, Math.min(ugly[i3] * 3, ugly[i5] * 5));
//            System.out.println(i + " " + ugly[i] + " " + i2 + " " + i3 + " " + i5);
            if (ugly[i] == ugly[i2] * 2) {
                i2++;
            }
            if (ugly[i] == ugly[i3] * 3) {
                i3++;
            }
            if (ugly[i] == ugly[i5] * 5) {
                i5++;
            }
        }
        return ugly[n - 1];
    }

    public static void main(String[] args) {
        nthUglyNumber(14);

        for (int i = 0; i < 100; i++) {
//            nthUglyNumber(i);
        }
    }
}

6. First Missing Positive

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3

Example 2:

Input: [3,4,-1,1]
Output: 2

Example 3:

Input: [7,8,9,11,12]
Output: 1

Note:

Your algorithm should run in O(n) time and uses constant extra space.

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums.length==0)
            return 1;
        Set<Integer> set= new HashSet<Integer>();
        for(int n:nums)
            set.add(n);
        for(int i=1; i<=nums.length+1; i++){
            if( !set.contains(i))
                return i;
        }
        return nums.length;
    }
}

7. Longest Consecutive Sequence

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

import java.util.HashMap;

class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>(); //未排序，只能逐个记录,上下两个方向搜索
        for (int n : nums)
            map.put(n, false);
        int longest = 0;
        for (int n : nums) {
            if (!map.containsKey(n))
                return 0;
            if (map.get(n)==true)
                continue;
            int len = 1;
            map.put(n, true);
            for (int i = 1; i < nums.length; i++) {
                if (map.containsKey(n + i) && map.get(n + i) == false) {
                    len++;
                    map.put(n + i, true);
                }else
                    break;
            }
            for (int i = 1; i < nums.length; i++) {
                if (map.containsKey(n - i) && map.get(n - i) == false) {
                    len++;
                    map.put(n - i, true);
                }else
                    break;
            }
            longest = longest > len ? longest : len;
        }
        return longest;
    }
}

8.Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2

Example 2:

Input: [3,1,3,4,2]
Output: 3

Note:

    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n2).
    There is only one duplicate number in the array, but it could be repeated more than once.
值域1~n-，序域1~n
二分查找，分布不匀之处。
链表环，序值不对应之处。
慢指针经过a+k1*r+c步骤，快指针分别表示为2*慢=a+k2*r+c,得出a=d*r+c。双慢指针分别从0，c位置出发，路程相差dr，必然环起点相遇。
class Solution {
    public int findDuplicate(int[] nums) {
        //定位环位置
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast=nums[nums[fast]];
        }while(slow!=fast);
        //找到环起始
        fast=0;
        while(fast!=slow){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;
    }
}
9.Basic Calculator II

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7

Example 2:

Input: " 3/2 "
Output: 1

Example 3:

Input: " 3+5 / 2 "
Output: 5

Note:

    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
先保存数字，再保存运算符
import java.util.Stack;

class Solution {
    public static int calculate(String s) {
        char[] chars = s.replace(" ", "").toCharArray();
        long res = 0, num = 0;
        char op = '+';
        Stack<Long> st = new Stack();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0')
                num = num * 10 + chars[i] - '0';
            if (chars[i] < '0' || i == chars.length - 1) {
                //switch case
                if (op == '+')
                    st.push(num);
                else if (op == '-')
                    st.push(-num);
                else if (op == '*' || op == '/') {
                    Long tmp = (op == '*') ? st.pop() * num : st.pop() / num;
                    st.push(tmp);
                }
                op = chars[i];
                num = 0;
            }
        }
        while (!st.empty()) {
            res += st.pop();
        }
        return (int) res;
    }
}

10.Sliding Window Maximum

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Note:
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

import java.util.LinkedList;

class Solution {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[0];
        LinkedList<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k)
                deque.poll();              //新数进来且为队首下标，移除最左数字
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.removeLast();              //丢弃队尾比新数字小的数字
            deque.offerLast(i);            // 必然是个大数
            if ((i + 1) >= k)
                res[i - k + 1] = nums[deque.peek()];
        }
        return res;
    }
    public static void main(String[] args){
        int[] nums={1,3,-1,-3,5,3,6,7};
        System.out.println(maxSlidingWindow(nums,3).toString());
    }
}

11.Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:

    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
https://leetcode.com/problems/minimum-window-substring/discuss/312971/A-straightforward-Java-solution-with-detailed-explanation
左右两指针，每次分别前进缩放。使用count验证匹配。

import java.util.HashMap;

class Solution {
    public String minWindow(String s, String t) {
        int start=0,count=0;
        String res="";
        HashMap<Character,Integer> map=new HashMap<>();
        for(char c:t.toCharArray())
            map.put(c,map.getOrDefault(c,0)+1);
        for(int i=0; i<s.length();i++){
            char c=s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c,map.get(c)-1);
                if(map.get(c)>=0)
                    count++; //有多少匹配
            }
            while(count==t.length()){ //已经匹配出模式串
                if(res.length()==0 || i-start+1<res.length())
                    res=s.substring(start,i+1);
                c=s.charAt(start);
                if(map.containsKey(c)){
                    map.put(c,map.get(c)+1);
                    if(map.get(c)>0)
                        count--; //加多了，存在即可
                }
                start++; //左指针缩进
            }
        }
        return res;
    }
}