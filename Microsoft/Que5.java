/*Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
*/
class Solution {
public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);
    int lds[] = new int[nums.length]; //fill the lds array with 1 because the minimum length subset we create is 1
    Arrays.fill(lds,1);
    int max=1;
    int prev=nums[0]; //prev tracks the last element of the longest subset
    for(int i=1;i<nums.length;i++){
        for(int j=0;j<i;j++){
		
		
		//if we find a pair(a,b) at (i,j) where a % b gives 0 and the length of the subset till 'a' (index 'i')
		//is less then or equal to 'b'(index 'j') ,we update the length of the largest divisible subset found till  'a' at lds[i]
		
            if(nums[i]%nums[j]==0 && lds[i]<=lds[j]){  
                lds[i]=lds[j]+1;
				
                // max=Math.max(max,lds[i]);
				//track the maximum length of divisible subset found and store the last element of the subset in prev
              
			        if(max<lds[i]){
                    max=lds[i];
                    prev=nums[i];
                }
            }
        }
    }
    List<Integer> list = new ArrayList();
    for(int i=nums.length-1;i>=0;i--){
	
	//now we iterate the lds array and pick elements one by one if their lds ends with 'max' length
	//and also check if it is divisible by the previous element
	
        if(max==lds[i]  && prev%nums[i]==0){
            list.add(nums[i]);
                max-=1;
            prev=nums[i];
        }
            
    }
    return list;
    
}
}
