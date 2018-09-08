1.Sort Colors

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:

    A rather straight forward solution is a two-pass algorithm using counting sort.
    First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
    Could you come up with a one-pass algorithm using only constant space?

class Solution {
    public void sortColors(int[] nums) {
        if(nums==null || nums.length==0)  return;
        int red=0,blue=nums.length-1;
        for(int i=0; i<=blue; i++){
            if(nums[i]==0){
                nums[i]=nums[red];
                nums[red]=0;
                red++;
            }else if(nums[i]==2){  //此时回退
                nums[i]=nums[blue];
                nums[blue]=2;
                blue--;
                i--;
            }
        }

    }
}

2.Top K Frequent Elements

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:

Input: nums = [1], k = 1
Output: [1]

Note:

    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Your algorithm's time complexity must be better than O(n log n), where n is the array's size.


import java.util.*;

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map=new TreeMap<>();
        for(int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        int size=map.size();

        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.count-b.count;
            }
        }
        );
        
        for( Map.Entry<Integer,Integer> entry:map.entrySet()){
            Pair p=new Pair(entry.getKey(),entry.getValue());
            queue.offer(p);
            if(queue.size()>k){
                queue.poll();   //小顶堆
            }
        }
        
        List<Integer>  result=new ArrayList<>();
        while(queue.size()>0){
            result.add(queue.poll().num);
        }
        Collections.reverse(result);
        return result;
    }
    class Pair{
        int num;
        int count;
        Pair(int num,int count){
            this.num=num;
            this.count=count;
        }
    }
}
3. Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>(k);
        for(int i=0; i<k; i++)    queue.add(Integer.MIN_VALUE);
        for(int num:nums){
            if(num>queue.peek()){
                queue.poll();
                queue.add(num);
            }
        }
        return queue.poll();
    }
}

4. Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.

Note:

Your solution should be in logarithmic complexity.
class Solution {
    public int findPeakElement(int[] nums) {
        for(int i=1; i<nums.length; i++){
            if(nums[i]<nums[i-1])  return i-1;
        }
        return nums.length-1;
    }
}

6.Search for a Range

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int front=search(nums,target,"front");
        int rear=search(nums,target,"rear");
        return new int[]{front,rear};
    }
    public int search(int[] nums,int target,String type){
        int min=0,max=nums.length-1;
        while(min<=max){
            int mid=(min+max)/2;
            if(nums[mid]>target){ max=mid-1; }
            else if(nums[mid]<target){ min=mid+1; }
            else{  //此时相等
                if(type.equals("front")){
                    if(mid==0)  return 0;
                    if(nums[mid-1]!=target)  return mid;   //返回完美结果
                    max=mid-1;
                }else{
                    if(mid==nums.length-1)  return mid;
                    if(nums[mid+1]!=target)  return mid;
                    min=mid+1;
                }
            }
        }
        return -1;
    }

}

7. Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considerred overlapping.

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size()<=1)  return intervals;
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });
        for(int i=0; i<intervals.size()-1;){
            if(intervals.get(i).end<intervals.get(i+1).start ){
                i++;
                continue;
            }
            while(i+1<intervals.size() && ( intervals.get(i).end>=intervals.get(i+1).start ) ){
                if(intervals.get(i).end<intervals.get(i+1).end)   intervals.get(i).end=intervals.get(i+1).end;
                if(intervals.get(i).start>intervals.get(i+1).start)     intervals.get(i).start=intervals.get(i+1).start;
                intervals.remove(i+1);
            }
            i++;
        }
        return intervals;
    }

}

8.Search in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1


class Solution {
    public static int index;
    public int search(int[] nums, int target) {
        return find(nums,target,0,nums.length-1);
    }
    public int find(int[] nums,int target,int left,int right){
        if(left>right){
            index=-1;
              return index;
        }
        int middle=(left+right)/2;
              System.out.println("7: "+middle+"  "+nums[left]+" "+nums[middle]+" "+nums[right]);
        if(nums[middle]==target){
            index=middle;
           //        System.out.println("9: "+middle);
            return index;
        }
        if(nums[left]<=nums[middle]){
           //        System.out.println("nums[left]<=nums[middle]");
           //        System.out.println("11: "+middle+"  "+nums[left]+" "+nums[middle]+" "+nums[right]);

            if(target>=nums[left] && target<nums[middle])  {
               //        System.out.println("target>=nums[left] && target<=nums[middle]");
                find(nums,target,left,middle-1);
            }else{
               //        System.out.println("target<nums[left] || target>nums[middle]");
               //        System.out.println("18: "+middle+"  "+nums[left]+" "+nums[middle]+" "+nums[right]);
                find(nums,target,middle+1,right);
            }
        }else{
           //        System.out.println("nums[left]>nums[middle]");
            if(target>nums[middle] && target<=nums[right])  find(nums,target,middle+1,right);
            else find(nums,target,left,middle-1);
        }
        return index;
    }
}

8.Search a 2D Matrix II

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.

Given target = 20, return false.

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0)  return false;
        int m=matrix.length;
        int n=matrix[0].length;
        if(n==0)  return false;
        for(int i=0; i<m; i++){
            System.out.println(" 6: "+i+" "+n+" "+matrix.length);
            if(matrix[i][0]>target || matrix[i][n-1]<target)  continue;
            if( find(matrix,i,target,0,n-1) )  return true;
        }
        return false;
    }
    boolean find(int[][] matrix,int row,int target, int left, int right){
        if(left>right)  return false;
        int middle=(left+right)/2;
        if(matrix[row][middle]==target)  return true;
        if(matrix[row][middle]>target)  return find(matrix,row,target,left,middle-1);
        else return find(matrix,row,target,middle+1,right);
    }
}