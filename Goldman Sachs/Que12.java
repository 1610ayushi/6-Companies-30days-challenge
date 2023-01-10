/*
Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.

 

Example 1:


Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
Example 2:


Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]
*/

class Solution {

    List<Integer> result = new ArrayList<>();

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

        if(root1 == null && root2 == null)
            return result;

        helper(root1);
        helper(root2);
        Collections.sort(result);

        return result;
    }


    private void helper(TreeNode root){
        if(root == null)
            return;
        else {
            result.add(root.val);
            helper(root.left);
            helper(root.right);
        }
    }
