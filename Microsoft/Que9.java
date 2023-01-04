/*
You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number of pairs (i, j) such that:

0 <= i < j <= n - 1 and
nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
Return the number of pairs that satisfy the conditions.

 

Example 1:

Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
Output: 3
Explanation:
There are 3 pairs that satisfy the conditions:
1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair satisfies the conditions.
2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair satisfies the conditions.
3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair satisfies the conditions.
Therefore, we return 3.
Example 2:

Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
Output: 0
Explanation:
Since there does not exist any pair that satisfies the conditions, we return 0.
 

Constraints:

n == nums1.length == nums2.length
2 <= n <= 105
-104 <= nums1[i], nums2[i] <= 104
-104 <= diff <= 104
*/
class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        int[] nums3 = new int[n];
        for (int i = 0; i < n; i++) {
            nums3[i] = nums1[i] - nums2[i];
        }
        long count = 0;
        int[] arr = new int[100000];
        int offset = 50000;
        Fenwick f = new Fenwick(arr);
        for (int j = 0; j < n; j++) {
            int key = offset + nums3[j] + diff;
            count += f.sum(key);
            f.update(offset + nums3[j], 1);
        }
        return count;
    }
}

public class Fenwick {
    long[] bit;
 
    public Fenwick(int arr[]) {
        bit = new long[arr.length + 1]; // index starts from 1 not 0.
 
        for (int i = 0; i < arr.length; i++) {
            int index = i+1;
            bit[index] += arr[i];
            int parent = index + (index & -index); // index of parent
            if (parent < bit.length) {
                bit[parent] += bit[index];
            }
        }
    }
 
    // return sum of arr[0..index].
    public long sum(int index) {
        long totalSum = 0;
        index = index + 1;
 
        while (index > 0) {
            totalSum += bit[index];
            index -= index & (-index);
        }
        return totalSum;
    }

    public void update(int index, int val) {
        // index in BIT[] starts from 1
        index = index + 1;
 
        while (index < bit.length) {
            bit[index] += val;
           index += index & (-index);
        }
    }
}
