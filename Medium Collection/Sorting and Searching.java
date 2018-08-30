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