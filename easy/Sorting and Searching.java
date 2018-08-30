1.  Merge Sorted Array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1==null || nums2==null || nums1.length==0 ||n==0 || nums2.length==0 || m+n>nums1.length )  return ;
        System.out.println("m"+m+"n"+n);

        for(int i=0; i<n && m<nums1.length ; i++){
            System.out.println("m"+m+" i"+i+ " "+nums2[i]);
            insert(nums1, m, nums2[i]);
            m++;
        }
    }
    public void insert(int[] nums1, int m, int now){
        int i;
        for( i=m; i>0 && nums1[i-1]>now ; i--){
            nums1[i]=nums1[i-1];
            System.out.println("i"+i+" "+nums1[i-1]);
        }
        nums1[i]=now;
        System.out.println("i"+i+" "+nums1[i]);
    }
}

2.  First Bad Version
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left=1; 
        int right=n;
        while(left<right){
            int mid=left+(right-left)/2;
            if(isBadVersion(mid))  right=mid;
            else  left=mid+1;
        }
        return left;
    }
}