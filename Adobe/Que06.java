/*
On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).

A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.


Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly k moves or has moved off the chessboard.

Return the probability that the knight remains on the board after it has stopped moving.

 

Example 1:

Input: n = 3, k = 2, row = 0, column = 0
Output: 0.06250
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
Example 2:

Input: n = 1, k = 0, row = 0, column = 0
Output: 1.00000
 

Constraints:

1 <= n <= 25
0 <= k <= 100
0 <= row, column <= n - 1

*/
class Solution {
    int [][] direction =new int[][]{{2,1},{-2,1},{2,-1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
   
    public double knightProbability(int N, int K, int r, int c) {
        double [][][] ways = new double[K+1][N][N];
        ways[0][r][c]=1;
        for(int k=1; k<=K;++k){
            for(int i=0; i<N;++i){
                for(int j=0; j<N;++j){
                    for(int [] dir: direction){
                        int oldR = i-dir[0];
                        int oldC = j-dir[1];
                        if(oldR>=0 && oldC>=0 && oldR<N && oldC<N){
                            ways[k][i][j]+=(ways[k-1][oldR][oldC]/8.0);
                        }
                    }
                }
            }
        }
        double res = 0;
        for(int i=0; i<N;++i){
            for(int j=0; j<N;++j){
                res+=ways[K][i][j];
            }
        }
        return res;
    }
}
