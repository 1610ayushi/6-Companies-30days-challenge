/*
Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.

The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.

A valid square has four equal sides with positive length and four equal angles (90-degree angles).

 

Example 1:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: true
Example 2:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
Output: false
Example 3:

Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
Output: true
 

Constraints:

p1.length == p2.length == p3.length == p4.length == 2
-104 <= xi, yi <= 104
*/

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] arr = new int[6];
        arr[0] = (int)Math.pow(p1[0] - p2[0],2) + (int)Math.pow(p1[1] - p2[1], 2);
        arr[1] = (int)Math.pow(p1[0] - p3[0],2) + (int)Math.pow(p1[1] - p3[1], 2);
        arr[2] = (int)Math.pow(p1[0] - p4[0],2) + (int)Math.pow(p1[1] - p4[1], 2);
        arr[3] = (int)Math.pow(p2[0] - p3[0],2) + (int)Math.pow(p2[1] - p3[1], 2);
        arr[4] = (int)Math.pow(p2[0] - p4[0],2) + (int)Math.pow(p2[1] - p4[1], 2);
        arr[5] = (int)Math.pow(p3[0] - p4[0],2) + (int)Math.pow(p3[1] - p4[1], 2);
        
        int min =  arr[0];
        for(int i=1;i<6;i++){
            if(min > arr[i]){
                min = arr[i];
            }
        }
        
        int count1 = 0, count2 = 0;
        for(int i=0;i<6;i++){
            if(arr[i] == min) count1++;
            else if(arr[i] == 2 * min) count2++;
        }
        
        return count1==4 && count2==2;
    }
}
