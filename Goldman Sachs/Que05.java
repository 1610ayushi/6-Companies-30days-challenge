/*
You are given an integer array nums that is sorted in non-decreasing order.

Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:

Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
All subsequences have a length of 3 or more.
Return true if you can split nums according to the above conditions, or false otherwise.

A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).

 

Example 1:

Input: nums = [1,2,3,3,4,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,5] --> 1, 2, 3
[1,2,3,3,4,5] --> 3, 4, 5
Example 2:

Input: nums = [1,2,3,3,4,4,5,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
[1,2,3,3,4,4,5,5] --> 3, 4, 5
Example 3:

Input: nums = [1,2,3,4,4,5]
Output: false
Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.
 

Constraints:

1 <= nums.length <= 104
-1000 <= nums[i] <= 1000
nums is sorted in non-decreasing order.

*/


class Solution {
    public boolean isPossible(int[] nums) {
        if (nums.length < 3) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(map.keySet().size());
        for (int num : map.keySet()) {
            queue.add(num);
        }

        while (!queue.isEmpty()) {
            int min = queue.peek();
            int count = 0;
            while (true) {
                if (!map.containsKey(min)) { 
				    // no consecutive number, if count is not 3 at least, return false immediately
                    if (count < 3) return false;
					// otherwise break because we cannot add more number to the current group since it would not be consecutive
                    break;
                }
                map.put(min, map.get(min) - 1);
                count++;
                if (map.get(min) == 0) {
				    // if the current minimum doesn't not match with the min in the heap, it means that the min value is gonna become isolated and we will never be able to add it to any group, so return false immediately
                    if (min != queue.peek()) return false;
					// no more occurrences in the map, so remove it also from the queue
                    queue.poll();
                }
				// if the occurrences of current value (min) (+1 because we just removed it from the map) are bigger than the next one, we cannot add numbers anymore to the current group because otherwise the current value would become isolated
                if (map.containsKey(min + 1) && map.get(min) + 1 > map.get(min + 1)) {
                    // I need to break, cannot add more numbers to the current group anymore
                    if (count < 3) return false; // when I break if, count is less than 3, again return immediately false
                    break;
                }

                min += 1; // update our minimum in search for another value to append to the current group
            }
        }
		// if false was never returned, we were able to build up the groups and can return true
        return true;
    }
}
