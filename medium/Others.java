1. Sum of Two Integers

Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3

Example 2:

Input: a = -2, b = 3
Output: 1


//a+b可以看成 （a xor b）+ （(a and b) << 1）
class Solution {
    public int getSum(int a, int b) {
        while(b!=0){
            int c=a&b;
            a=a^b;
            b=c<<1;
        }
        return a;
    }
}

2.Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

    Division between two integers should truncate toward zero.
    The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.

Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        Map<String,Character> map=new HashMap<>();
        map.put("+",'+');
        map.put("-",'-');
        map.put("*",'*');
        map.put("/",'/');
        Stack<String> stack=new Stack<>();
        for(String str:tokens){

            if (map.containsKey(str)) {
                char p = map.get(str);
                int b=s2int(stack.pop());
                int a=s2int(stack.pop());
                int c=op(a,b,p);
                stack.push(c+"");
                System.out.println(stack.toString());
            } else {
                stack.push(str);
            }
        }



        return s2int(stack.pop());
    }
    int s2int(String s){
        int pos=1;
        if(s.startsWith("-")){
            s=s.substring(1,s.length());
            pos=-1;
        }
        int num=Integer.valueOf(s);
        return pos*num;
    }
    int op(int a,int b,char p){
        if(p=='+')  return a+b;
        if(p=='-')  return a-b;
        if(p=='*')  return a*b;
        if(p=='/')  return a/b;
        return -1;
    }
}

3.Majority Element

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3

Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2


import java.util.HashMap;
import java.util.Map;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int n:nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }
        System.out.println(map.toString());
        int re=0;
        int fre=0;
        for(Integer n:map.keySet()){
            if(map.get(n)>fre){
                re=n;
                fre=map.get(n);
            }
        }
        return re;
    }
}

4. Task Scheduler

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

Note:

    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].

// 最多次数为mf，按照a--a--排列，最少时间为mf×(n+1)-n,最后一个不用冷却，如果最高频率有s个，开头再插入书-1，其他不占位置
import java.util.Arrays;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] fre=new int[26];
        for(char c:tasks)  fre[c-'A']++;
        Arrays.sort(fre);
        int i=25;
        while(i>=0 && fre[i]==fre[25])  i--;
        return Math.max(tasks.length,fre[25]*(n+1)-n+(25-i)-1);
    }
}