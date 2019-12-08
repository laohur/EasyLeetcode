1.Wiggle Sort II

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].

Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?




class Solution:
    def wiggleSort(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        # def swap(nums,i,j):
        #     t=nums[i]
        #     nums[i]=nums[j]
        #     nums[j]=t
        #
        # def partition(nums, l, r):
        #     pivot = nums[l]
        #     i,j=l,r
        #     # print(len(nums),i,j)
        #     while (i<j):
        #         while (i<j and nums[j] >= pivot):
        #             j -= 1
        #         # nums[i]=nums[j]
        #         # swap(nums,i,j)
        #         while (i <j and nums[i] <= pivot):
        #             i += 1
        #         # nums[j]=nums[i]
        #         if i<j:
        #             swap(nums,i,j)
        #
        #         # t=nums[i]
        #         # nums[i] = nums[j]
        #         # nums[j] = t
        #     # nums[i]=pivot
        #     swap(nums,l,j)
        #     return j
        # python overtime

        # def findK(nums,l,r,k):
        #     if l==r:
        #         return nums[l]
        #     pos=partition(nums,l,r)
        #     if pos+1==k:
        #         return nums[pos]
        #     if pos+1<k:
        #         return findK(nums,pos+1,r,k)
        #     else:
        #         return findK(nums,l,pos-1,k)
        #
        if len(nums)<2:
            return nums
        mid_pos=(len(nums)+1)//2
        # mid=findK(nums,0,len(nums)-1,mid_pos)
        # re=[mid]*len(nums)
        # s,t=0,len(nums)-1
        # for i in range(len(nums)):
        #     if nums[i]<mid:
        #         re[s]=nums[i]
        #         s+=1
        #     elif nums[i]>mid:
        #         re[t]=nums[i]
        #         t-=1
        # 4556-> 5645
        # 4556-> 5645
        re=nums.copy()
        re.sort()
        s,t=mid_pos-1,len(nums)-1
        for i in range(len(nums)):
            if i&1==0:
                nums[i]=re[s]
                s-=1
            else:
                nums[i]=re[t]
                t-=1
        
        print(mid_pos,nums)
        # print(mid,mid_pos,nums)
if __name__ == '__main__':
    # nums = [4,5,5,6,4]
    nums=[5,3,1,2,6,7,8,5,5]
    # nums=[1,3,2,2,3,1]
    # nums=[1,1,3,1,3,2,1,3,2,1]
    # nums=[3,2,1,1,3,2]
    sln = Solution()
    d = sln.wiggleSort(nums)
    print(d)

2. Kth Smallest Element in a Sorted Matrix

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

Note:
You may assume k is always valid, 1 ≤ k ≤ n2.

class Solution:
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        
        def findk(seq,k):
            seq.sort()
            return seq[k-1]
        

        seq=[]
        for x in matrix:
            seq+=x
        value=findk(seq,k)

        return value

from typing import List


class Solution:
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        def order(matrix, value):  # 逐列计合 i*j  2n
            n = len(matrix)
            i = n - 1
            j = 0
            total = 0
            while (i >= 0 and j < n):
                if matrix[i][j] <= value:
                    total += i + 1
                    j += 1
                else:
                    i -= 1
            return total

        low = matrix[0][0]
        high = matrix[-1][-1]
        while (low < high):
            mid = (low + high) // 2
            count = order(matrix, mid)
            # if count==k:
            #     return mid
            if count < k:
                low = mid + 1
            else:
                high = mid
        return low

if __name__=='__main__':
    nums1 = [1, 2]
    nums2 = [3, 4]

    sln=Solution()
    d=sln.kthSmallest(nums1,nums2)
    print(d)

3.Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0

Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5


import bisect
from typing import List


class Solution(object):
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        def findk(nums1,l1,nums2, l2,k):
            if l1==len(nums1):
                return nums2[l2+k]
            if l2==len(nums2):
                return nums1[l1+k]
            if k==0:
                return min(nums1[l1],nums2[l2])
            mid1=min(len(nums1)-l1,(k+1)//2)
            mid2=min(len(nums2)-l2,(k+1)//2)

            if nums1[l1+mid1-1]<nums2[l2+mid2-1]:
                return findk(nums1,l1+mid1,nums2,l2,k-mid1)
            else:
                return findk(nums1,l1,nums2,l2+mid2,k-mid2)

        k=(len(nums1)+len(nums2))//2
        if (len(nums1)+len(nums2))%2==1:
            return findk(nums1,0,nums2,0,k)
        else:
            a= findk(nums1,0,nums2,0,k)
            b= findk(nums1,0,nums2,0,k-1)
            return (a+b)/2






if __name__ == '__main__':
    nums1 = [3]
    nums2 = [-2,-1]

    sln=Solution()
    d=sln.findMedianSortedArrays(nums1,nums2)
    print(d)
