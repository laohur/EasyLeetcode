1.Word Ladder

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

超时，c++只会遍历了。。。
#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <unordered_set>
/* 每一步都能续接
 */
using namespace std;

class Solution {
public:
    int editDistance(string s, string t) {
        if (s.length() != t.length())
            return -1;
        int len = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s[i] == t[i])
                len--;
        }
        return len;
    }


    int ladderLength(string beginWord, string endWord, vector<string> &wordList) {
        // 判断交接处可持续性
        set<string> candidate(wordList.begin(), wordList.end());
        if (candidate.find(endWord) == candidate.end())
            return 0;
        int size = candidate.size();
        set<string> beginset = {beginWord};
        int level = 1;
        set<string> nowset;
        for (int i = 0; i < size; i++) {
            level++;
            if (candidate.size() == 0 || beginset.size() == 0)
                return 0;
            for (string b:beginset) {
                for (set<string>::iterator  itr = candidate.begin(); itr != candidate.end(); ) {
                    if (editDistance(b, *itr) == 1) {
                        string s = *itr;
                        if (*itr == endWord)
                            return level;
                        nowset.insert(*itr);
                        itr=candidate.erase(itr);
                    }else{
                        itr++;
                    }
                }
            }
            beginset.clear();
            beginset.swap(nowset);
        }
        return 0;
    }
};

2.Surrounded Regions

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <unordered_set>
/* 层次遍历不好存储，深度遍历,先从边界扫，翻牌子；扫完换牌子
 */
using namespace std;

class Solution {
public:
    // <0 invalid; ==0 edge; >= inner.
    int location(int i, int j, int m, int n){
        if(i<0 || j<0  || i>=m || j>=n)
            return -1;
        if(i==0 || j==0  || i==m-1 || j==n-1)
            return 0;
        return 1;
    }
    void printMatrix(vector<vector<char>>& board,int m, int n){
        for( int i=0; i<m; i++){
            for( int j=0; j<n; j++){
                printf("%c",board[i][j]);
            }
            printf("\n");
        }
        printf("\n\n\n");
    }

    void dps(vector<vector<char>>& board,int i,int j,int m, int n) {
        int type=location(i,j,m,n);
        if(type<0)
            return;
        if( board[i][j]!='O' )
            return;
        board[i][j]='o';
        dps(board,i+1,j,m,n);
        dps(board,i-1,j,m,n);
        dps(board,i,j+1,m,n);
        dps(board,i,j-1,m,n);

    }
    void solve(vector<vector<char>>& board) {
        int m=board.size();
        if(m==0)
            return ;
        int n=board[0].size();
        if(n==0)
            return ;
        //1 dps from edge
        for( int i=0; i<m; i++){
            if(i==3)
                int c=0;
            for( int j=0; j<n; j++){
                int t=location(i, j, m, n);
                if(t==0)
                    dps(board,i,j,m,n);
            }
        }
        printMatrix(board,m,n);
        //2 swap mask
        for( int i=0; i<m; i++){
            for( int j=0; j<n; j++){
                int t=location(i, j, m, n);
                if(t>=0 && board[i][j]!='o' )
                    board[i][j]='X';
            }
        }
        printMatrix(board,m,n);

        // 3 revert mask
        for( int i=0; i<m; i++){
            for( int j=0; j<n; j++){
                if (board[i][j]=='o')
                    board[i][j]='O';
            }
        }
        // 4 print
        printMatrix(board,m,n);

    }

};

int main() {
    vector<vector<char>> board={{'O','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
    Solution sln;
    sln.solve(board);
//    printf("%d \n", len);

}

3.    Explore
    Problems
    Mock
    Contest
    Articles
    Discuss
     Store 

     Premium
    New Playground
    1
    laohur

  Back to Chapter
 Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

 

Note:

    All of the nodes' values will be unique.
    p and q are different and both values will exist in the binary tree.

#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <unordered_set>
/* 也可以查找路径存起来
 */
using namespace std;


 struct TreeNode {
     int val;
     TreeNode *left;
     TreeNode *right;
     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    TreeNode* ancestor= nullptr;
    bool descendant(TreeNode* root, TreeNode* p, TreeNode* q ){
        if(root== nullptr)
            return false;
        bool now=(root->val==p->val || root->val==q->val );
        bool l=descendant(root->left,p,q);
        bool r=descendant(root->right,p,q);
        if(now&&l || now&&r || l&&r)
            ancestor=root;
        return now||l||r;
    }

    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        descendant(root,p,q);
        return ancestor;
    }
};


int main() {
    vector<vector<char>> board={{'O','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
    Solution sln;
    sln.solve(board);
//    printf("%d \n", len);

}

4.Binary Tree Maximum Path Sum

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <unordered_set>
/* 也可以查找路径存起来
 */
using namespace std;


 struct TreeNode {
     int val;
     TreeNode *left;
     TreeNode *right;
     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    int maxsum=INT_MIN;
    int sum(TreeNode*root){
        if(root== nullptr)
            return 0;
        int now=root->val;
        int left=sum(root->left);
        int right=sum(root->right);
        if(left<0)
            left=0;
        if(right<0)
            right=0;
        if(now+left+right>maxsum)
            maxsum=now+left+right;
        return left>right?now+left:now+right;
    }
    int maxPathSum(TreeNode* root) {
        sum(root);
        return maxsum;
    }
};

int main() {
    vector<vector<char>> board={{'O','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
    Solution sln;
    sln.solve(board);
//    printf("%d \n", len);

}

5.Friend Circles

There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:

Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.

Example 2:

Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <list>
/* 也可以查找路径存起来
 */
using namespace std;

class Solution {
public:
    vector<int> flag;
    vector<bool> visited;
    void dfs(vector<vector<int>>& M,int idx,int n){
        if(visited[idx])
            return;
        visited[idx]=true;
        for(int i=0;i<n;i++){
            if(idx==i)
                continue;
            if(M[idx][i]==1 || M[i][idx]==1) {
                flag[i]=-1;  //this person not flag
                dfs(M,i,n);
            }
        }
    }

    int findCircleNum(vector<vector<int>>& M) {
        int n=M.size();
        for(int i=0;i<n;i++){
            flag.push_back(0);
            visited.push_back(false);
        }
        int count=0;
        for(int i=0;i<n;i++){
            if(flag[i]==0){
                count++;
                dfs(M,i,n);
            }
        }
        return count;
    }
};

int main() {
//    vector<vector<int>> M={{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
vector<vector<int>> M1={{1,1,0},{1,1,0},{0,0,1}};
    Solution sln;
    sln.findCircleNum(M1);
//    printf("%d \n", len);

}

6.Course Schedule

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.



// 不断去依赖

class Solution {
public:
    bool canFinish(int numCourses, vector<vector<int>> &prerequisites) {
        vector<vector<int>> adjugate(numCourses, vector<int>(0));
        vector<int> indegrees(numCourses, 0);
        for (vector<int> pair: prerequisites) {
            adjugate[pair[0]].push_back(pair[1]);
            indegrees[pair[1]]++;
        }
        queue<int> start;  // for while
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0)
                start.push(i);
        }
        while (start.size() > 0) {
            int begin = start.front();
            start.pop();
            for (int fellower:adjugate[begin]) {
                indegrees[fellower]--;
                if(indegrees[fellower]==0)
                    start.push(fellower);
            }
        }
        for(int indegree:indegrees){
            if(indegree>0)
                return false;
        }
        return true;
    }
};

7.Course Schedule II

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .

Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.

#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <list>
#include <queue>
using namespace std;

// 不断去依赖

class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> requires(numCourses, vector<int>(0));
        vector<vector<int>> adjugate(numCourses, vector<int>(0));
        vector<int> indegrees(numCourses, 0);
        for (vector<int> pair: prerequisites) {
            requires[pair[0]].push_back(pair[1]);
            adjugate[pair[1]].push_back(pair[0]);
            indegrees[pair[0]]++;
        }
        vector<int> order;
        queue<int> start;  // for while
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0)
                start.push(i);
        }
        while (start.size() > 0) {
            int begin = start.front();
            start.pop();
            order.push_back(begin);
            for (int fellower:adjugate[begin]) {
                indegrees[fellower]--;
                if(indegrees[fellower]==0)
                    start.push(fellower);
            }
        }
        if(order.size()!=numCourses)
            order.clear();
        return order;
    }
};

int main() {
//    vector<vector<int>> M={{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
    vector<vector<int>> M1 = {{1,0}};
    Solution sln;
    vector<int> order= sln.findOrder(2,M1);
    for(int i:order)
        printf("%d \t",i);
}

8. Longest Increasing Path in a Matrix

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

//用改用动态规划存储已知深度，避免存储访问标记

class Solution {
public:

    bool valid(int i,int j,int m,int n){
        if(i>=0 && j>=0 && i<m && j<n)
            return true;
        return false;
    }
    vector<vector<int>> bias={{0,1},{0,-1},{1,0},{-1,0}};
    int dps(vector<vector<int>> &matrix,vector<vector<int>> depth ,int i,int j,int m,int n){
        if(!valid(i,j,m,n)  )
            return 0;
        if(depth[i][j]>0)
            return depth[i][j];
        int len=1,now=matrix[i][j];
        for(vector<int> pair:bias){
            int x=i+pair[0], y=j+pair[1];
            if(valid(x,y,m,n) && matrix[x][y]>now)
                len=max(len,1+dps(matrix,depth,x,y,m,n));
        }
        return len;
    }

    int longestIncreasingPath(vector<vector<int>> &matrix) {
        if (matrix.size() == 0 || matrix[0].size() == 0)
            return 0;
        int longest = 1, m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> depth(m,vector<int>(n,0));
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                longest=max(longest,dps(matrix,depth,i,j,m,n));
            }
        }
        return longest;
    }
};

9.
Longest Increasing Path in a Matrix

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.


class Solution {
public:

    bool valid(int i,int j,int m,int n){
        if(i>=0 && j>=0 && i<m && j<n)
            return true;
        return false;
    }
    vector<vector<int>> bias={{0,1},{0,-1},{1,0},{-1,0}};
    // & is required
    int dps(vector<vector<int>> &matrix,vector<vector<int>> &depth ,int i,int j,int m,int n){
        if( depth[i][j]>0 )  //all entered are needed
            return depth[i][j];
        int len=1,now=matrix[i][j];
        for(vector<int> pair:bias){
            int x=i+pair[0], y=j+pair[1];
            if(valid(x,y,m,n) && matrix[x][y]>now)
                len=max(len,1+dps(matrix,depth,x,y,m,n));
        }
        depth[i][j]=len;
        return len;
    }

    int longestIncreasingPath(vector<vector<int>> &matrix) {
        if (matrix.size() == 0 || matrix[0].size() == 0)
            return 0;
        int longest = 1, m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> depth(m,vector<int>(n,0));
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                longest=max(longest,dps(matrix,depth,i,j,m,n));
            }
        }
        return longest;
    }
};

10.Count of Smaller Numbers After Self

You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

from typing import List


import bisect
class Solution:
    def countSmaller(self, nums: List[int]) -> List[int]:
        n=len(nums)
        if n==0:
            return []
        re=[0]*n
        end=n-1
        seq=[nums[-1]]
        for i in range(n-2,-1,-1):
            s= bisect.bisect_left(seq,nums[i])
            seq.insert(s,nums[i])
            re[i]=s
        return re

if __name__ == '__main__':
    # input=[2,1,5,6,2,3]
    input=[1,1]
    input= [5,2,6,1]
    sln=Solution()
    d=sln.countSmaller(input)
    print(d)
