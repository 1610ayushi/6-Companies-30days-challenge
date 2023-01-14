/*
Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.

 

Example 1:

Input: n = 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
Example 2:

Input: n = 1
Output: 2
Example 3:

Input: n = 2
Output: 3
 

Constraints:

1 <= n <= 109
*/
class Solution {
    public int findIntegers(int n) {
        int countOnes = helper(n);
        return n - countOnes + 1;
    } 
    public int helper(int n){
        int[] dp = new int[33];
        dp[0] = 0;
        dp[1] = 0;
        for(int i=2;i<33;i++){
            dp[i] = dp[i-1] + dp[i-2] + (int)Math.pow(2,i-2); 
        }
        double pow = Math.log(n)/Math.log(2);
        if(pow == (int)pow){
           return dp[(int)pow];
        }

        pow = Math.floor(pow);
        int res = dp[(int) pow];
        
        int lowestNo = (int)Math.pow(2,pow);
        
        int remains = n - lowestNo;
        
        if(remains >= lowestNo/2){
           res+= dp[(int)pow - 1] + (remains - lowestNo/2 + 1);
        }
        else res+=helper(remains);
        return res;
    }  
}
