/*
n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly. But after that, the rest of the passengers will:

Take their own seat if it is still available, and
Pick other seats randomly when they find their seat occupied
Return the probability that the nth person gets his own seat.

 

Example 1:

Input: n = 1
Output: 1.00000
Explanation: The first person can only get the first seat.
Example 2:

Input: n = 2
Output: 0.50000
Explanation: The second person has a probability of 0.5 to get the second seat (when first person gets the first seat).
 

Constraints:

1 <= n <= 105*/
/*The idea for dp solution is that:

When n=1, answer will be 1.
When n=2, answer will be 0.5.
For other n, answer will be equal to (1/n) + ((n-2)/n)(answer of previous n). For eg for n=3, answer will be
(1/3) + (1/3)(0.5) = 0.5.*/
 //dp solution
class Solution {
    public double nthPersonGetsNthSeat(int n) {
        if(n==1)
            return 1;
        double[] strg = new double[n];
        strg[0] = 1.0;
        strg[1] = 0.5;
        for(int i=2;i<strg.length;i++){
            double d = (1/(1.0*n)) + ((n-2)/(1.0*n))*(strg[i-1]);
            strg[i] = d;
        }
        return strg[n-1];
    }
}
// math solution
class Solution {
     public double nthPersonGetsNthSeat(int n) {
         if(n==1)
             return 1;
         return 0.5;
     }
 }
