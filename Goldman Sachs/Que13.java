/*
You are given an m x n integer matrix grid​​​.

A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:


Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.

Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.

 

Example 1:


Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
Output: [228,216,211]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 20 + 3 + 200 + 5 = 228
- Red: 200 + 2 + 10 + 4 = 216
- Green: 5 + 200 + 4 + 2 = 211
Example 2:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: [20,9,8]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 4 + 2 + 6 + 8 = 20
- Red: 9 (area 0 rhombus in the bottom right corner)
- Green: 8 (area 0 rhombus in the bottom middle)
Example 3:

Input: grid = [[7,7,7]]
Output: [7]
Explanation: All three possible rhombus sums are the same, so return [7].
*/
    public int[] getBiggestThree(int[][] grid) {
        final int n = grid.length;
        final int m = grid[0].length;
        final Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                set.add(grid[i][j]);
                for (int L = 1; L <= 25; L++) {
                    final int curr = f(grid, L, i, j, n, m);
                    if (curr != (int) 1e9) {
                        set.add(curr);
                    }
                }
            }
        }
        return set.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).toArray();
    }

    private static int f(int[][] g, int size, int i, int j, int n, int m) {
        if (i + size >= n || i - size < 0 || (j + 2 * size) >= m) {
            return (int) 1e9;
        }
        int sum = 0;
        for (int k = 1; k < size; k++) {
            sum += g[i - k][j + k];
            sum += g[i + k][j + k];
            sum += g[i - k][j + 2 * size - k];
            sum += g[i + k][j + 2 * size - k];
        }
        sum += g[i][j];
        sum += g[i][j + 2 * size];
        sum += g[i + size][j + size];
        sum += g[i - size][j + size];
        return sum;
    }
