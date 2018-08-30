1.  Fizz Buzz
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]

class Solution {
    public List<String> fizzBuzz(int n) {
        if(n<=0)  return null;
        String s="";
        List<String> list=new ArrayList<String>();
        for(int i=0; i<n; i++){
             s=""+(i+1);
            if((i+1)%3==0)   s="Fizz";
            if((i+1)%5==0)   s="Buzz";
            if((i+1)%15==0)   s="FizzBuzz";
            list.add(s);
        }
        return list;
    }
}

2.  Count Primes
Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

class Solution {
    public int countPrimes(int n) {
        boolean[] mark=new boolean[n];
        Arrays.fill(mark,true);
        for(int i=2; i*i<n; i++){
            if(!mark[i]) continue;
            for(int j=i*i; j<n; j+=i){
                mark[j]=false;
                //System.out.println(Arrays.toString(mark));
            }
        }
        int count=0;
        for(int j=2; j<n; j++){
            if(mark[j])  count++;
        }
        return count;
    }
}

3.  Power of Three
Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true

Example 2:

Input: 0
Output: false

Example 3:

Input: 9
Output: true

Example 4:

Input: 45
Output: false

Follow up:
Could you do it without using any loop / recursion?

class Solution {
    public boolean isPowerOfThree(int n) {
        double res=Math.log10(n)/Math.log(3);
        return  (n>0 && (res-(int)res)==0);
    }
}


4.   Roman to Integer
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, two is written as II in Roman numeral, just two one s added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9. 
    X can be placed before L (50) and C (100) to make 40 and 90. 
    C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3

Example 2:

Input: "IV"
Output: 4

Example 3:

Input: "IX"
Output: 9

Example 4:

Input: "LVIII"
Output: 58
Explanation: C = 100, L = 50, XXX = 30 and III = 3.

Example 5:

Input: "    "
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
iv ix vx lc dm
class Solution {
    public int romanToInt(String s) {
        if(s.contains("DM"))        
    }
}