#define NDEBUG
#include <assert.h>
#include <iostream>
using namespace std;

Array
1  Remove Duplicates from Sorted Array
名称写错，[]位置不对
int removeDuplicates(int nums[], int numsSize) {
    if(nums==NULL || numsSize==0) return 0;
    if(numsSize==1)  return 1;
    int count=0;
    for(int i=1; i<numsSize; i++ ){
        if(nums[count]!=nums[i]){
            count++;
            nums[count]=nums[i];
        }
    }
    return count+1;
}

2.  Best Time to Buy and Sell Stock II
注意，只需给出利润总额，不用记忆怎么买卖
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int profit=0;
        for(int i=1; i<prices.size(); i++){
            if(prices[i-1]<prices[i]){
                profit+=prices[i]-prices[i-1];
            }
        }
        return profit;
    }
};

3.Rotate Array
复制  [1,2] 3  [2,1]
class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        vector<int> old(nums);  //复制
        int tmp;
        int n=nums.size();
        k=k%n;  //虽然大多数题目不用检查参数，但这个出现了
        for(int i=0; i<nums.size(); i++){
            nums[i]=old[(n-k+i)%n];
        }
    }
};

4  Contains Duplicate
class Solution {
    public boolean containsDuplicate(int[] nums) {
		HashMap<Integer,Integer> map =new HashMap< >(nums.length);
		for(int i=0; i<nums.length; i++){
			if (map.containsKey(nums[i]))  return true;
			map.put(nums[i],1);
		}
		return false;
    }
}

5.  Single Number
class Solution {
    public int singleNumber(int[] nums) {
        		for(int i=0; i<nums.length; i++){
			int k=nums[i];
			for(int j=i+1; j<nums.length; j++){
				if(nums[j]==k){
					nums[j]=0;
					nums[i]=0;
				}
			}
		}
		for(int i=0; i<nums.length; i++){
			if(nums[i]!=0) return nums[i];
		}
		return 0;   
    }
}
xor，相同的都会消掉
class Solution {
    public int singleNumber(int[] nums) {
		int num=0;
		for(int i=0; i<nums.length; i++){
			num=num^nums[i];
		}
		return num;
    }
}

6.  Intersection of Two Arrays II
class Solution {
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer,Integer> map1=new HashMap<>();
		for( int num:nums1){
			map1.put(num,map1.getOrDefault(num,0)+1);
		}
		List<Integer> list=new ArrayList<>();
		for(int num:nums2){
			if(map1.containsKey(nums2[num]) && map1.get(num)!=0 ){
				list.add(num);
				map1.put(num,map1.get(num)-1);
			}
		}
		int[] result=new int[list.size()];
		int i=0;
		for(int num:list){
			result[i]=num;
			i++;
		}
		return result;
	}
    
}

7.  Plus One
class Solution {
    public int[] plusOne(int[] digits) {
		long value=0;
		for(int i=0; i<digits.length; i++){
			value+=digits[digits.length-1-i]*Math.pow(10,i);
            System.out.println("i"+i+" "+ value);
		}
		value++;
		List<Integer> list=new LinkedList<>();
		while(value>0){
			list.add((int)value%10);
			value/=10;
		}
		int[] result=new int[list.size()];
		for(int i=0; i<list.size(); i++){
			result[i]=list.get(list.size()-i-1);
		}
		return result;
	}
}

class Solution {
    public int[] plusOne(int[] digits) {
		long more=0;
		digits[digits.length-1]++;
		for(int i=0; i<digits.length; i++){
			if(more>0){
				digits[digits.length-1-i]++;
				more=0;
			}
			if(digits[digits.length-1-i]>9){
				digits[digits.length-1-i]%=10;
				more=1;
			}
		}
		if(more==0)  return digits;
		int[] result=new int[digits.length+1];
		result[0]=1;
		for(int i=0; i<digits.length; i++){
			result[i+1]=digits[i];
		}
		return result;
	}
}

8.  Move Zeroes
class Solution {
	public void moveZeroes(int[] nums) {
		for(int i=0; i<getEnd(nums); i++){
			int end=getEnd(nums);
			if(end<0) return;
			if(nums[i]==0){
				for(int j=i; j<end; j++){
					nums[j]=nums[j+1];
				}
				nums[end]=0;
			}
		}
		for(int k=0; k<getEnd(nums); k++){
			if(nums[k]==0) moveZeroes(nums);
		}
	}
	int getEnd(int[] nums){
		int end=nums.length-1;
		for( ; end>=0; end--){
			if(nums[end]!=0) return end;  //end为右起第一个非零
		}
		return -1;
	}


}

}

class Solution {
	public void moveZeroes(int[] nums) {
		for(int i=0,j=0; i<nums.length; i++){
			if(nums[i]!=0){
				int tmp=nums[i];
				nums[i]=nums[j];
				nums[j]=tmp;
				j++;  //j记录非零,出现一个0，j停i走，互换
			}
		}
	}
}

9.  Two Sum
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        int i,j;
        for(i=0; i<nums.length; i++){
            for(j=i+1; j<nums.length; j++ ){
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    return result;
                }
            }
		}
		return result;
    }
}

10.  Valid Sudoku
class Solution {
	public boolean isValidSudoku(char[][] board) {

		char[] line1=new char[9];
		char[] line2=new char[9];
//纵横验证
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
//				if(board[i][j]<='9' && board[i][j]>='1' ) line1[j]= board[i][j];
//				if(board[j][i]<='9' && board[j][i]>='1' ) line1[j]= board[j][i];
				line1[j]= board[i][j];
				line2[j]= board[j][i];
			}
			if(!validateLine(line1) || !validateLine(line2)) {
				System.out.println("!line1"+line2String(line1));
				System.out.println("!line2"+line2String(line2));
				return false;
			}
		}
//宫格验证
		int[] row={0,0,0,3,3,3,6,6,6};
		int[] column={0,3,6,0,3,6,0,3,6};
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				line1[j]=board[row[i]+j/3][column[i]+j%3];
//				if(j<3) line1[j]=board[row[i]][column[j]+j];
//				else if(j<6) line1[j]=board[row[i]+1][column[j]+j%3];
//				else line1[j]=board[row[i]+2][column[j]+j%3];
				System.out.println("row"+(row[i]+j/3)+ "column"+(column[i]+j%3));

			}
			System.out.println("cell"+i+"\t"+line2String(line1));
			if(!validateLine(line1)) {
				System.out.println("!cell"+line2String(line1));
				return false;
			}
		}
		return true;
	}
	boolean validateLine(char[] line){
		Map<Character,Integer> count=new HashMap<>(9);
		for(int i=0; i<9; i++){
			if(line[i]>='0' && line[i]<='9')  count.put(line[i],count.getOrDefault(line[i],0)+1);
			//System.out.printf(line[i]+"\t");
		}
		//System.out.println();
		for( char key:count.keySet() ){
			if(count.get(key)>1) {
				//printLine(line);
				return false;
			}
		}
		return true;
	}
	String  line2String(char[] line){
		String s="";
		for(char c:line){
			s+=c+"\t";
		}
		return s;
	}
}

11.  Rotate Image
class Solution {
	public void rotate(int[][] matrix) {
		int n=matrix.length;//边长

		//转一圈
		int[] line=new int[n-1];
		for(int i=0; i<n-1; i++){
			line[i]=matrix[0][i];
		}
		for(int i=0; i<n; i++){
			matrix[0][i]=matrix[n-1-i][0];
			matrix[n-1-i][0]=matrix[n-1][n-1-i];
			matrix[n-1][n-1-i]=matrix[i][n-1];
			matrix[i][n-1]=line[i];
		}

//i起点，此圈边长n/2-i
		for(int i=0; i<n/2; i++){
			int[] line=new int[n-2*i-1];
			for(int j=0; j<(n-2*i-1); j++){
				line[j]=matrix[i][i+j];
			}
			for(int j=0; j<(n-2*i-1); j++){
				
			}
		}

	}
}

class Solution {
    public void rotate(int[][] matrix) {
		int n=matrix.length;
		System.out.println(" n"+n);
        for(int i=0; i<(n+1)/2; i++){ //[i,i]到[n-1-i,n-1-i]  圈的起点
			int l=n-2*i; //边长n-1-2i  
			System.out.println(" i"+i+" l"+l);
			for(int j=0; j<l-1; j++){  //留最后一个用于轮换
				// line[j]=matrix[i][i+j];
				int d=matrix[i][i+j];  //暂存北边一个元素
				//北[i,i+j]东向  西[n-i-1-j,i]北向  南[n-1-i,n-1-i-j]西向  东[i+j,n-1-i]南向
				matrix[i][i+j]=matrix[n-1-i-j][i];  //北换成西
				matrix[n-1-i-j][i]=matrix[n-1-i][n-1-i-j];  //西换成南
				matrix[n-1-i][n-1-i-j]=matrix[i+j][n-1-i];  //南换成东
				// matrix[i][n-1]=line[i];  //东换成北
				matrix[i+j][n-1-i]=d;  //东换成北
			}
		}
    }
}

[[15,13,2,5]
,[14,4,8,1]
,[12,3,6,9]
,[16,7,10,11]]