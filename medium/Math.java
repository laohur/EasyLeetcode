1. Happy Number

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set=new HashSet<>();
        while(n!=1 && next(n)!=n ){
            if(set.contains(n))  return false;
            set.add(n);
             n=next(n);
            System.out.println(n);
        }
        return n==1;
    }
    int[] flat(int n){
        char[] chars=String.valueOf(n).toCharArray();
        int[] nums=new int[chars.length];
        for(int i=0; i<chars.length; i++){
            nums[i]=chars[i]-'0';
        }
        return nums;
    }
    int party(int[] nums){
        int sum=0;
        for(int e:nums){
            sum+=e*e;
        }
        return sum;
    }
    int next(int n){
        int[] nums=flat(n);
        int sum=party(nums);
        return sum;
    }
}

2. Factorial Trailing Zeroes

Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Note: Your solution should be in logarithmic time complexity.
//25=585
class Solution {
    public int trailingZeroes(int n) {
     int re=0;
        while(n>4){
            re+=n/5;
            n=n/5;
        }

        return re;
    }
}

3.Excel Sheet Column Number

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...

Example 1:

Input: "A"
Output: 1

Example 2:

Input: "AB"
Output: 28

Example 3:

Input: "ZY"
Output: 701


class Solution {
    public int titleToNumber(String s) {
        char[] chars=s.toCharArray();
        int sum=0;
        for(char c:chars){
            sum=sum*26+(c-'A'+1);
            System.out.println(c-'A'+1);
        }
        return sum;
    }
}

1. Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000

Example 2:

Input: 2.10000, 3
Output: 9.26100

Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25

Note:

    -100.0 < x < 100.0
    n is a 32-bit signed integer, within the range [−231, 231 − 1]


import static  java.lang.System.*;
class Solution {

    public double myPow(double x, int n) {
        if(n==0 || x==1)  return 1;
        if(n>0)  return pow(x,n);
        return 1/pow(x,n*-1L);
    }
    public double pow(double x,long n){
        if(n==1)  return x;
        double sum=x;
        long i;
        for( i=2;i<=n; i*=2){
            if(Math.abs(sum-0L)<0.0000000001)  return 0;
            sum*=sum;
           // out.println("15: "+ sum+" "+i);
        }
        out.println("17: "+ sum+" "+i+ " "+n);
        for(i/=2;i<n;i++){
            //      out.println("19: "+ sum+" "+i);
            sum*=x;
        }
        return sum;
    }
}

5.Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.

class Solution {
    public int mySqrt(int x) {
        long i;
        long X=x*1L;
        for(i=0;;i++){
            if(i*i>X)  break;
        }
        i--;
        return (int)i;
            
    }
}

class Solution {
    public int divide(int dividend, int divisor) {
        //dv=2147483647
        if(dividend==Integer.MIN_VALUE && (divisor==1 || divisor==-1) )   
            return divisor==1?Integer.MIN_VALUE:Integer.MAX_VALUE;
        return (int)divideLong(dividend,divisor);
    }
    public long divideLong(long dd,long dv){
        boolean neg=(dd*dv)<0;
        dd=Math.abs(dd);
        dv=Math.abs(dv);
        int digits=0;
        while(dd>=dv){
            dv<<=1;
            digits++;
        }
        long re=0;
        dv>>=digits;
        digits--;
        while(digits>=0){
            if(dd>=(dv<<digits)){  
                dd-=dv<<digits;  //-dv^2i
                re+=1<<digits;
            }
            digits--;
        }
        return neg?-re:re;
    }
}

7. Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:

Input: numerator = 2, denominator = 1
Output: "2"

Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//  2/3
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator==0)  return "0";
        StringBuilder re=new StringBuilder();
        if( (numerator<0)^(denominator<0) )  re.append('-');
        long n=Math.abs(numerator*1L);
        long d=Math.abs(denominator*1L);
        re.append(String.valueOf(n/d));
        if(n%d==0)  return re.toString();
        re.append(".");
        Map<Long,Integer> map =new HashMap<>();
        for(long r=n%d; r!=0;r%=d){
            if(map.containsKey(r)){
                re.insert(map.get(r),"(");
                re.append(')');
                break;
            }
            map.put(r,re.length());
            r*=10;
//            re.append((int)r/d);
//            re.append(Character.forDigit((int)(r/d),10));
            re.append(Character.forDigit((int)(r/d), 10));
        }
        return re.toString();
    }
}