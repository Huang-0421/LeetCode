package LeetCode;
//二分查找
public class Solution {
    /*
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
    */
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
    /*
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     */
    public int searchInsert(int[] nums, int target) {
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
        return i;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        if(nums.length == 0){
            return res;
        }
        int i = 0;
        int j = nums.length - 1;
        int avg;
        while(i <= j){
            avg = (i + j) / 2;
            if(nums[avg] == target){
                int left = avg;
                int right = avg;
                while(left >= 1 && nums[left - 1] == target){
                    left--;
                }
                
                
                while(right <= nums.length - 2 && nums[right + 1] == target){
                    right++;
                }

                res[0] = left;
                res[1] = right;
                return res;
            }
            else if(nums[avg] < target){
                i = avg + 1;
            }
            else
                j = avg - 1;
        }
        return res;
    }
}

