  1.Shuffle an Array
  huffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

class Solution {
    int[] origin;
    int[] current;

    public Solution(int[] nums) {
        origin=nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        current=new int[origin.length];
        for(int i=0; i<origin.length; i++){
            current[i]=origin[i];
        }
        int pos;
        int tmp;
        Random randomer=new Random();
        for(int i=origin.length-1; i>=0; i--){
            pos=randomer.nextInt(i+1);
            tmp=current[pos];
            current[pos]=current[i];
            current[i]=tmp;
        }
        return current;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

2.  Min Stack
 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

class MinStack {
    private Stack<Integer> stack;
    /** initialize your data structure here. */
    private int min;
    public MinStack() {
        stack=new Stack<>();
        min=Integer.Max_VALUE;
    }
    
    public void push(int x) {
        if(x<=min){
            stack.push(min);  //最小值夹在栈一起压进去
            min=x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if(stack.pop()==min)  min=stack.pop();
    }
    
    public int top() {
        return stack.top();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */