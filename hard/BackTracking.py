1.Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

from typing import List


# !copy in python

class Solution:

    def partition(self, s: str) -> List[List[str]]:
        def isPalinDrome(s, i, j):
            while (i <= j):
                if s[i] != s[j]:
                    return False
                i += 1
                j -= 1
            return True

        n=len(s)
        # flag=[[False]*n]*n
        flag=[]
        for i in range(n):
            row=[False]*n
            for j in range(i,n):
                row[j]=(isPalinDrome(s, i, j))
                # flag[i][j]=isPalinDrome(s, i, j)
                # print(i,j,flag[i][j],flag)
            flag.append(row)
        print(flag)

        def bt(s,i,seq):
            if len(seq)>0 and i==len(s):
                re.append(seq)
                return
            for j in range(i,len(s)):
                if flag[i][j]:
                    bt(s,j+1,seq+[s[i:j+1]])
                # del seq[-1]

        re=[]
        # seq=[]
        bt(s,0,[])
        return re

sln=Solution()
d=sln.partition('aab')
print(d)


2.Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]

 

Note:

    All inputs are consist of lowercase letters a-z.
    The values of words are distinct.

2. Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]

 

Note:

    All inputs are consist of lowercase letters a-z.
    The values of words are distinct.

   Hide Hint #1  
You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
   Hide Hint #2  
If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
from typing import List


class Trie:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.children = [None] * 26
        self.tail = False

    def insert(self, word: str) -> None:
        """
        Inserts a word into the trie.
        """
        if len(word) == 0:
            return
        idx = ord(word[0]) - ord('a')
        if not self.children[idx]:
            self.children[idx] = Trie()
        if len(word) == 1:
            self.children[idx].tail = True
        else:
            self.children[idx].insert(word[1:])

    def search(self, word: str) -> bool:
        """
        Returns if the word is in the trie.
        """
        if len(word) == 0:
            return True
        idx = ord(word[0]) - ord('a')
        if not self.children[idx]:
            return False
        if len(word) == 1:
            return self.children[idx].tail  # switch for only once

        return self.children[idx].search(word[1:])

    def startsWith(self, prefix: str) -> bool:
        """
        Returns if there is any word in the trie that starts with the given prefix.
        """
        word = prefix
        if len(word) == 0:
            return True
        idx = ord(word[0]) - ord('a')
        if self.children[idx] is None:
            return False
        return self.children[idx].startsWith(word[1:])


class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        trie = Trie()
        for word in words:
            trie.insert(word)

        def bt(board, m, n, i, j, word, visited):
            if i < 0 or j < 0 or i >= m or j >= n or visited[i][j]:
                return
            word += board[i][j]
            if not trie.startsWith(word):
                return
            if trie.search(word):
                print(word)
                re.append(word)

                # return True

            visited[i][j] = True
            # print(i,j,word,visited)
            bt(board, m, n, i - 1, j, word, visited)
            bt(board, m, n, i + 1, j, word, visited)
            bt(board, m, n, i, j - 1, word, visited)
            bt(board, m, n, i, j + 1, word, visited)
            word=word[0:-1]
            visited[i][j]=False

        m = len(board)
        if m == 0:
            return []
        n = len(board[0])
        if n == 0:
            return []
        if len(words) == 0:
            return words
        re = []

        def gen_mask(m, n):
            mask = [0] * m
            # row=[False]*n
            # board=[row for i in range(m)]
            for i in range(m):
                mask[i] = [False] * n
            return mask

        # mask=gen_mask(m,n)

        for i in range(m):
            for j in range(n):
                if trie.children[ord(board[i][j]) - ord('a')]:
                    bt(board, m, n, i, j, '', gen_mask(m, n))
        return list(set(re))


if __name__ == '__main__':
    board = [
        ['o', 'a', 'a', 'n'],
        ['e', 't', 'a', 'e'],
        ['i', 'h', 'k', 'r'],
        ['i', 'f', 'l', 'v']
    ]
    words = ["oath", "pea", "eat", "rain"]

    # board = [["a","b"],["c","d"]]
    # words = ["cdba"]

    board = [["b", "a", "a", "b", "a", "b"], ["a", "b", "a", "a", "a", "a"], ["a", "b", "a", "a", "a", "b"],
             ["a", "b", "a", "b", "b", "a"], ["a", "a", "b", "b", "a", "b"], ["a", "a", "b", "b", "b", "a"],
             ["a", "a", "b", "a", "a", "b"]]
    words = ["bbaabaabaaaaabaababaaaaababb", "aabbaaabaaabaabaaaaaabbaaaba", "babaababbbbbbbaabaababaabaaa",
             "bbbaaabaabbaaababababbbbbaaa", "babbabbbbaabbabaaaaaabbbaaab", "bbbababbbbbbbababbabbbbbabaa",
             "babababbababaabbbbabbbbabbba", "abbbbbbaabaaabaaababaabbabba", "aabaabababbbbbbababbbababbaa",
             "aabbbbabbaababaaaabababbaaba", "ababaababaaabbabbaabbaabbaba", "abaabbbaaaaababbbaaaaabbbaab",
             "aabbabaabaabbabababaaabbbaab", "baaabaaaabbabaaabaabababaaaa", "aaabbabaaaababbabbaabbaabbaa",
             "aaabaaaaabaabbabaabbbbaabaaa", "abbaabbaaaabbaababababbaabbb", "baabaababbbbaaaabaaabbababbb",
             "aabaababbaababbaaabaabababab", "abbaaabbaabaabaabbbbaabbbbbb", "aaababaabbaaabbbaaabbabbabab",
             "bbababbbabbbbabbbbabbbbbabaa", "abbbaabbbaaababbbababbababba", "bbbbbbbabbbababbabaabababaab",
             "aaaababaabbbbabaaaaabaaaaabb", "bbaaabbbbabbaaabbaabbabbaaba", "aabaabbbbaabaabbabaabababaaa",
             "abbababbbaababaabbababababbb", "aabbbabbaaaababbbbabbababbbb", "babbbaabababbbbbbbbbaabbabaa"]

    # words=["ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"]
    # words=['ab']
    # board=[["a","b"],["c","d"]]
    sln = Solution()
    d = sln.findWords(board, words)
    print(d)

3.Remove Invalid Parentheses

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]

Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]

Example 3:

Input: ")("
Output: [""]

   Hide Hint #1  
Since we don't know which of the brackets can possibly be removed, we try out all the options!
   Hide Hint #2  
We can use recursion to try out all possibilities for the given expression. For each of the brackets, we have 2 options:

    We keep the bracket and add it to the expression that we are building on the fly during recursion.
    OR, we can discard the bracket and move on. 

   Hide Hint #3  
The one thing all these valid expressions have in common is that they will all be of the same length i.e. as compared to the original expression, all of these expressions will have the same number of characters removed. Can we somehow find the number of misplaced parentheses and use it in our solution?
   Hide Hint #4  
The one thing all these valid expressions have in common is that they will all be of the same length i.e. as compared to the original expression, all of these expressions will have the same number of characters removed. Can we somehow find the number of misplaced parentheses and use it in our solution?
   Hide Hint #5  
For every left parenthesis, we should have a corresponding right parenthesis. We can make use of two counters which keep track of misplaced left and right parenthesis and in one iteration we can find out these two values.

0 1 2 3 4 5 6 7
( ) ) ) ( ( ( )  
i = 0, left = 1, right = 0
i = 1, left = 0, right = 0
i = 2, left = 0, right = 1
i = 3, left = 0, right = 2
i = 4, left = 1, right = 2
i = 5, left = 2, right = 2
i = 6, left = 3, right = 2
i = 7, left = 2, right = 2

We have 2 misplaced left and 2 misplaced right parentheses.

from typing import List


class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        def isParentheses(line):
            count=0
            for x in line:
                if x=='(':
                    count+=1
                if x==')':
                    count-=1
                if count<0:
                    return False
            return  count==0

        def remove(s,i):
            if i+1==len(s):
                return s[:-1]
            return s[0:i]+s[i+1:]

        print(isParentheses(s))
        if isParentheses(s):
            return [s]
        # left,right = 0,0
        # for x in s:
        #     if x == '(':
        #         left += 1
        #     if x == ')':
        #         right -= 1
        #     # print(x,count)

        # c= '(' if count>0 else ')'

        re=set()
        bucket=set([s])
        for i in range(len(s)+1):
            print(i,bucket)
            tmp=set()
            if len(re)>0:
                return list(re)
            for t in bucket:
                for i in range(len(t)):
                    if t[i] not in ['(',')']:
                        continue
                    u=remove(t,i)
                    # del u[i]
                    if isParentheses(u):
                        re.add(u)
                    else:
                        tmp.add(u)
            bucket=tmp
        return ['']

if __name__ == '__main__':
    # line=  "(a)())()"
    # line= "()())()"
    line=")(f"
    line="n"
    sln = Solution()
    d = sln.removeInvalidParentheses(line)
    print(d)

4.Wildcard Matching

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

Note:

    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like ? or *.

Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false


just match all
from typing import List


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        i,j=0,0
        istar,jstar=-1,-1
        while(i<len(s)):
            if j<len(p) and (s[i]==p[j] or p[j]=='?'):  #general
                i+=1
                j+=1
            elif j<len(p) and p[j]=='*':  # init start p->
                istar=i
                jstar=j
                j+=1
            elif istar>=0:  # use * again,
                istar+=1
                i=istar
                j=jstar+1
            else:
                return False
        while(j<len(p) and p[j]=='*'):
            j+=1
        return j==len(p)

if __name__ == '__main__':
    s = "acdcb"
    p = "a*c?b"

    # s = "adceb"
    # p = "*a*b"

    sln = Solution()
    d = sln.isMatch(s,p)
    print(d)
4.Regular Expression Matching

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

Note:

    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false


from typing import List


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
       #  tokens=[]
       #  for i in range(len(p)):
       #      tokens.append(p[i])
       #      if p[i]=='.':
       #          if i+1==len(p) or p[i+1]!='*':
       #              tokens.append('1')
       #
       #  for i in range(1,len(tokens)):
       #      if tokens[i] in ["*",'1']:
       #          tokens[i]=':'+tokens[i]+','
       #  line=''.join(tokens)
       #  if line[-1]==',':
       #      line=line[:-1]
       #  patten=[  x.split(':') for x in line.split(',')]
       # # only match one char

        if len(p)==0:
            return len(s)==0
        match1= len(s)!=0  and  (s[0]==p[0] or p[0]=='.')
        if len(p)>=2 and p[1]=='*':
            return self.isMatch(s,p[2:]) or  ( match1 and self.isMatch(s[1:],p))

        return match1 and self.isMatch(s[1:],p[1:])


if __name__ == '__main__':
    s = "acdcb"
    p = "a*c?b"

    s = "aab"
    p = "c*a*b"

    # s = "mississippi"
    # p = "mis*is*p*."

    sln = Solution()
    d = sln.isMatch(s,p)
    print(d)

