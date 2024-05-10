package LeetCode;
//二分查找
public class Solution {
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        int avg;
        while(i <= j){
            avg = (i + j) / 2;
            if(nums[avg] == target)
                return avg;
            else if(nums[avg] < target){
                i = avg + 1;
            }
            else
                j = avg - 1;
        }
        return 0;
    }
}

