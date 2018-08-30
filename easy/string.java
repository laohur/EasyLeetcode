

String
1.  Reverse String
class Solution {
public:
    string reverseString(string s) {
        reverse(s.begin(),s.end());
    }
};

class Solution {
public:
    string reverseString(string s) {
        int n=s.length();
        for(int i=0; i*2<n; i++){
            swap( s[i],s[n-1-i] );
        }
        return s;
    }
};

2. Reverse Integer
class Solution {
public:
    int reverse(int x) {
        int res=0;
        while(x!=0){
            if(abs(res)>INT_MAX/10)  return 0;
            res =10*res+x%10;
            x=x/10;
        }
        return res;
    }
};

3. First Unique Character in a String
class Solution {
public:
    int firstUniqChar(string s) {
        int n=s.length();
        vector<int> count(26);
        for(int i=0; i<n; i++){
            count[s[i]-'a']++;
        }
        for(int i=0; i<n; i++){
            if(count[s[i]-'a']==1)
            return i;
        }
        return -1;
    }
};

4.  Valid Anagram
aa  a  不算是
"aacc"  "ccac"  也不是

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        Character c;
        HashMap<Character,Integer> smap=new HashMap<>();
        HashMap<Character,Integer> tmap=new HashMap<>();
        for(int i=0; i<s.length(); i++){
            c=new Character(s.charAt(i));
            smap.put(c,smap.getOrDefault(c,0)+1);
            c=new Character(t.charAt(i));
            tmap.put(c,tmap.getOrDefault(c,0)+1);
        }
        for(Character d:tmap.keySet()){
            System.out.println("d "+d+ " s"+smap.get(d)+" t"+tmap.get(d));
            if( (int )smap.getOrDefault(d,0)!=(int)tmap.getOrDefault(d,0) ) return false;  //注意转换成int
        }
        // for(Character d:tmap.keySet()){
        //     System.out.println("d "+d+ " s"+smap.get(d)+" t"+tmap.get(d));
        //     if( (int )smap.getOrDefault(d,0)!=(int)tmap.getOrDefault(d,0) ) return false;  //注意转换成int
        // }
        return true;
    }
}

5.  Valid Palindrome
"0P" 不是 
class Solution {
    public boolean isPalindrome(String s) {
        s=s.toLowerCase();
        char[] chars=s.toCharArray();
        StringBuffer sb=new StringBuffer();
        for(int i=0; i<chars.length; i++){
            if(chars[i]>=19968 && chars[i]<=40869 || chars[i]>=97 && chars[i]<=122 || chars[i]>=65 && chars[i]<=90  || chars[i]>=48 && chars[i]<=57 ){
                sb=sb.append(chars[i]);  //只保留字母汉字数字
            }
        }
        s=new String(sb);
        System.out.println(s);
        for(int i=0; i<s.length()/2; i++){
            if(s.charAt(i)!=(s.charAt(s.length()-1-i)))  return false;  //char 是基本类型，用==比较
        }        
        return true;
    }
}

6.   String to Integer (atoi)
class Solution {
    public int myAtoi(String str) {
        str=str.trim();
        if(str.length()==0)  return 0;
        int sign=1;
        int i=0;
        if(str.charAt(0)=='+') {
            sign=1;
            i++;
        }
        else if( str.charAt(0)=='-' ) {
            sign=-1;
            i++;
        }
        //if( str.length()==1 || str.charAt(i)>57 || str.charAt(i)<48 ) return 0;
        double num=0;
        for(;i<str.length(); i++ ){
            if(str.charAt(i)>57 || str.charAt(i)<48) break;
            num=num*10+(str.charAt(i)-'0');
                    System.out.println("num"+num+" char"+(str.charAt(i)-'0'));
            if(num*sign>Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if(num*sign<Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        num=sign*num;
        return (int)num;
    }
}



7.  Implement strStr()
Input: haystack = "hello", needle = "ll"
Output: 2
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0;
        if(haystack.length()<needle.length()) return -1;
        for(int i=0; i<=haystack.length()-needle.length(); i++){
            StringBuffer sb=new StringBuffer();
            for(int j=0; j<needle.length(); j++ ){
                sb.append(haystack.charAt(i+j));
            }
            String s=new String(sb);
            System.out.println(s);
            if(s.equals(needle)) return i;
        }
        return -1;
    }
}

8.  Count and Say
class Solution {
    public String countAndSay(int n) {
        String str="1";
        for(int i=1; i<n; i++){
            str=count(str);
        }
        return str;
    }
    String count(String str){
        String res="";
        for(int i=0; i<str.length(); i++){
            int n=0;
            char c=str.charAt(i);
            for(;  i<str.length() && str.charAt(i)==c ; i++){
                n++;
            }
            i--;
            res+=String.valueOf(n)+c;
System.out.println(" str"+str+" i"+i+" c"+c+" res"+res);
        }
        return res;
    }
}

9.  Longest Common Prefix
class Solution {
	public static String longestCommonPrefix(String[] strs) {
		if(  strs==null ||strs.length==0)  return "";
		if(strs.length==1)  return strs[0];
		StringBuffer sb=new StringBuffer();
		for(int i=0; i<strs[0].length(); i++){  //每一行第几个字
			char c=strs[0].charAt(i);
			for(int j=1; j<strs.length ; j++){  //第几行

				System.out.println(" i:"+i+ " c:"+c+" j:"+j+" strs[j]:"+strs[j]+ " sb:"+sb);

				if( strs[j]==null )  return "";
				if(strs[j].length()<i+1)  return new String(sb);
				if(c!=strs[j].charAt(i)) return new String(sb);
			}
			sb.append(c);
		}
		return new String(sb);
	}
}