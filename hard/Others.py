1.Queue Reconstruction by Height

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.
 

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

 
   Hide Hint #1  
What can you say about the position of the shortest person?
If the position of the shortest person is i, how many people would be in front of the shortest person?
   Hide Hint #2  
Once you fix the position of the shortest person, what can you say about the position of the second shortest person?


from typing import List


class Solution:
    def reconstructQueue(self, people: List[List[int]]) -> List[List[int]]:
        people=sorted(people,key=lambda x:(x[0],-x[1]),reverse=True )
        re=[]
        for p in people:
            re.insert(p[1],p)
        return re

if __name__ == '__main__':
    people=[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
    sln = Solution()
    d = sln.reconstructQueue(people)
    print(d)

2.Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6


from typing import List
class Solution:
    def trap(self, height: List[int]) -> int:
        n=len(height)
        topl=[0]*n
        topr=[0]*n
        for i in range(1,n):
            topl[i]=max(topl[i-1],height[i-1])
            j=n-1-i
            topr[j]=max(topr[j+1],height[j+1])
        re=0
        print(topl)
        print(topr)
        for i in range(n):
            volume=min(topl[i],topr[i])-height[i]
            re+=max(0,volume)
        return re

if __name__ == '__main__':
    input=[5,4,1,2]
    sln=Solution()
    d=sln.trap(input)
    print(d)

4. The Skyline Problem

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
Buildings Skyline Contour

The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]


from typing import List
class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        n=len(buildings)
        points=[[x[0],-x[2]] for x in buildings]+[[x[1],x[2]] for x in buildings]
        points=sorted(points)
        print(points)
        ordered={}
        ordered[0]=1
        premax=0
        re=[]
        for p in points:
            if p[1]<0:
                if -p[1] not in ordered:
                    ordered[-p[1]]=1
                else :
                    ordered[-p[1]]+=1
            else:
                ordered[p[1]]-=1
                if ordered[p[1]]==0:
                    del ordered[p[1]]
            nowmax=sorted(ordered)[-1]
            if nowmax!=premax:
                re.append([p[0],nowmax])
                premax=nowmax
        return re

if __name__ == '__main__':
    input=[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
    # input=[[1,2,1],[1,2,2],[1,2,3]]
    sln=Solution()
    d=sln.getSkyline(input)
    print(d)

3.Largest Rectangle in Histogram

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 


The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10


from typing import List


class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        re=0
        tops=[]
        heights.append(0)
        # for i in range(len(heights)):
        i=0
        while i<len(heights):
            if len(tops)==0 or heights[i]>=heights[tops[-1]]:
                tops.append(i)
            else:
                index=tops[-1]
                del tops[-1]
                end=tops[-1]+1 if len(tops)>0 else 0
                re=max(re,heights[index]*(i-end))
                i-=1
            i+=1
        return re

if __name__ == '__main__':
    # input=[2,1,5,6,2,3]
    # input=[1,1]
    input=[2,1,2]
    sln=Solution()
    d=sln.largestRectangleArea(input)
    print(d)
        