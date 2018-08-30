1. Hamming Distance

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.
Example:
Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
The above arrows point to positions where the corresponding bits are different.
class Solution {
    public int hammingDistance(int x, int y) {
        int count=0;
        for(int i=0; i<count; i++){
	        int j=1<<i;
	        if(!( (x&j) & (y&j)) )   count++;
        }
        return count;
    }
}

3.  Reverse Bits

Reverse bits of a given 32 bits unsigned integer.

Example:

Input: 43261596
Output: 964176192
Explanation: 43261596 represented in binary as 00000010100101000001111010011100, 
             return 964176192 represented in binary as 00111001011110000010100101000000.

Follow up:
If this function is called many times, how would you optimize it?
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int m=0;
        //取低位做高位
        for(int i=0; i<32; i++){
            int j=1<<i;  //0或者1
            if((n&j)!=0)
                m+=1<<(31-i);
            // m+=(n&j)<<(31-i);
            // m+=n&j;
        }
        return m;
    }
}

4. Pascal's Triangle

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

class Solution {
    public List<List<Integer>> generate(int numRows) {
	    List<List<Integer>> result=new LinkedList();
        if(numRows<1)   return result;
        List<Integer> first=new LinkedList<Integer>();
        first.add(0,new Integer(1));
        List<Integer> now=first;
        result.add(0,now);
        for(int i=1; i<numRows; i++){
	        now=next(now);
	        result.add(i,now);
        }
        return result;
    }
    public List<Integer> next(List<Integer> prev){
	    int n=prev.size();
	    List<Integer> post=new LinkedList<Integer>();
	    post.add(prev.get(0));
	    for(int i=1; i<n; i++){
            int j=prev.get(i-1).intValue()+prev.get(i).intValue();
		    post.add(new Integer(j));
	    }
	    post.add(prev.get(n-1));
	    return post;
    }
}

5. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true

Example 2:

Input: "()[]{}"
Output: true

Example 3:

Input: "(]"
Output: false

Example 4:

Input: "([)]"
Output: false

Example 5:

Input: "{[]}"
Output: true

        Stack<Integer> st = new Stack<Integer>();

class Solution {
    public boolean isValid(String s) {
        char[] chars=s.toCharArray();
        Stack<Character> stack=new Stack<Character>();
        for(int i=0; i<chars.length; i++){
	        if( !stack.isEmpty() && isPair(stack.peek(),chars[i]) ){
		         stack.pop();
	        }else{
            	stack.push(chars[i]);	        
	        }
        }
        return stack.isEmpty();
    }
    public boolean isPair(char a, char b){
        if(a=='(')   return b==')';
        if(a=='[')  return b==']';
        if(a=='{')  return b=='}';
        return false;
    }
}        

6.Missing Number
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2

Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

class Solution {
    public int missingNumber(int[] nums) {
        int n=nums.length;
        int[] mirror=new int[n+1];
        for(int i=0; i<n; i++){
            mirror[nums[i]]=1;
        }
        for(int i=0; i<=n; i++){
            System.out.println(mirror[i]);
            if(mirror[i]!=1)
                return i;
        }
        return 0;
    }
}