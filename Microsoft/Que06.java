/*
Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle. The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).

Return true if all the rectangles together form an exact cover of a rectangular region.

 

Example 1:


Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
Output: true
Explanation: All 5 rectangles together form an exact cover of a rectangular region.
Example 2:


Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
Output: false
Explanation: Because there is a gap between the two rectangular regions.
Example 3:


Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
Output: false
Explanation: Because two of the rectangles overlap with each other.
 

Constraints:

1 <= rectangles.length <= 2 * 104
rectangles[i].length == 4
-105 <= xi, yi, ai, bi <= 105
*/
class Solution {

public boolean isRectangleCover(int[][] rectangles) {
    //base check
    if (rectangles.length == 0 || rectangles[0].length == 0) return false;
    Set<String> points = new HashSet<String>();
    int area = 0;
    //full rectangle coordinates
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxA = Integer.MIN_VALUE;
    int maxB = Integer.MIN_VALUE;
    //iterate all the rectangles
    for (int[] r : rectangles){
        //get the points, find area and add to previous area
        int x = r[0];
        int y = r[1];
        int a = r[2];
        int b = r[3];
        area += (x-a) * (y-b);
        //record min and max points each time
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxA = Math.max(maxA, a);
        maxB = Math.max(maxB, b);
        //get the string to store in the set
        String bottomLeft = getHash(x,y);
        String topLeft = getHash(x,b);
        String bottomRight = getHash(a,y);
        String topRight = getHash(a,b);
        //remove all intermediate points if it exists
        //finally only 4 coordinates will be left behind to form the full rectangle
        checkInSet(points, bottomLeft);
        checkInSet(points, topLeft);
        checkInSet(points, bottomRight);
        checkInSet(points, topRight);
    }
    //get the string to read from the set
    String fullBottomLeft = getHash(minX,minY);
    String fullTopLeft = getHash(minX,maxB);
    String fullBottomRight = getHash(maxA,minY);
    String fullTopRight = getHash(maxA,maxB);
    //make sure the size is 4 and all the coordinate has exists in the set
    if (points.size() != 4 || !points.contains(fullBottomLeft) 
        || !points.contains(fullTopLeft) || !points.contains(fullBottomRight) || !points.contains(fullTopRight)) return false;
    //compute the full rectangle area
    int fullArea = (minX-maxA) * (minY-maxB);
    // this should be equal
    return area == fullArea;
}
// method to check if the hash value exist in the set or not
// if it exists then it means its a duplicate coordinate of previous rectangle added, so remove it
// finally the set will contain only 4 corner coordinates
private void checkInSet(Set<String> points, String hash){
    if (!points.contains(hash)) points.add(hash);
    else points.remove(hash);
}
//helper to return simple hash to store in the set
private String getHash(int x, int y){
    return x + ":" + y;
}
}
