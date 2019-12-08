1.Largest Number

Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"

Example 2:

Input: [3,30,34,5,9]
Output: "9534330"

Note: The result may be very large, so you need to return a string instead of an integer.

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int noZero(String s){
        if(s.isEmpty())
            return -1;
        int index=-1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='0')
                return i;
        }
        return index;
    }
    public String largestNumber(int[] nums) {
        if(nums.length==0)
            return "";

        String[] strs=new String[nums.length];
        for(int i=0;i<nums.length;i++)
            strs[i]=String.valueOf(nums[i]);
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s, String t) {
                String a=s+t;
                String b=t+s;
                return b.compareTo(a);   //直击本质
            }
        });
//        String re=new String();
        StringBuilder sb=new StringBuilder();
        for(String s:strs)
            sb.append(s);

        String s= sb.toString();
        return s.charAt(0)=='0'?"0":s;

    }
}


2.Max Points on a Line

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4

Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

from typing import List, Set


class Solution:
    def gcd(self,a,b): # for percision
        return a if b==0 else self.gcd(b,a%b)


    def maxPoints(self, points: List[List[int]]) -> int:
        if not points or  len(points)==0 or len(points[0])==0:
            return 0
        if len(points)<=2:
            return len(points)
        most=1
        for i in range(len(points)):
            counter = {}
            same = 0
            peak=0
            for j in range(i+1,len(points)):
                dx=points[i][0]-points[j][0]
                dy=points[i][1]-points[j][1]
                if dx==0 and dy==0:
                    same+=1
                    continue
                d=self.gcd(dx,dy)
                key=str(dx//d)+'_'+str(dy//d)
                if key not in counter:
                    counter[key] =0
                counter[key]+=1
                peak=max(peak,counter[key])
            most=max(most,peak+same+1) #same+1
        return most


# points=[[1,1],[1,1],[1,1]]
# points=[[560,248],[0,16],[30,250],[950,187],[630,277],[950,187],[-212,-268],[-287,-222],[53,37],[-280,-100],[-1,-14],[-5,4],[-35,-387],[-95,11],[-70,-13],[-700,-274],[-95,11],[-2,-33],[3,62],[-4,-47],[106,98],[-7,-65],[-8,-71],[-8,-147],[5,5],[-5,-90],[-420,-158],[-420,-158],[-350,-129],[-475,-53],[-4,-47],[-380,-37],[0,-24],[35,299],[-8,-71],[-2,-6],[8,25],[6,13],[-106,-146],[53,37],[-7,-128],[-5,-1],[-318,-390],[-15,-191],[-665,-85],[318,342],[7,138],[-570,-69],[-9,-4],[0,-9],[1,-7],[-51,23],[4,1],[-7,5],[-280,-100],[700,306],[0,-23],[-7,-4],[-246,-184],[350,161],[-424,-512],[35,299],[0,-24],[-140,-42],[-760,-101],[-9,-9],[140,74],[-285,-21],[-350,-129],[-6,9],[-630,-245],[700,306],[1,-17],[0,16],[-70,-13],[1,24],[-328,-260],[-34,26],[7,-5],[-371,-451],[-570,-69],[0,27],[-7,-65],[-9,-166],[-475,-53],[-68,20],[210,103],[700,306],[7,-6],[-3,-52],[-106,-146],[560,248],[10,6],[6,119],[0,2],[-41,6],[7,19],[30,250]]
points=[[1,1],[2,2],[3,3]]
sln=Solution()
d=sln.maxPoints(points)
print(d)