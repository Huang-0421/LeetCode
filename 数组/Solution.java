//二分查找  https://leetcode.cn/problems/binary-search/
@SuppressWarnings("all")
public class Solution {
    /*
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     */
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        int avg;
        while (i <= j) {
            avg = (i + j) / 2;
            if (nums[avg] == target)
                return avg;
            else if (nums[avg] < target) {
                i = avg + 1;
            } else
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
        while (i <= j) {
            avg = (i + j) / 2;
            if (nums[avg] == target)
                return avg;
            else if (nums[avg] < target) {
                i = avg + 1;
            } else
                j = avg - 1;
        }
        return i;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length == 0) {
            return res;
        }
        int i = 0;
        int j = nums.length - 1;
        int avg;
        while (i <= j) {
            avg = (i + j) / 2;
            if (nums[avg] == target) {
                int left = avg;
                int right = avg;
                while (left >= 1 && nums[left - 1] == target) {
                    left--;
                }
                while (right <= nums.length - 2 && nums[right + 1] == target) {
                    right++;
                }
                res[0] = left;
                res[1] = right;
                return res;
            } else if (nums[avg] < target) {
                i = avg + 1;
            } else
                j = avg - 1;
        }
        return res;
    }

    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int i = 0;
        int j = x - 1;
        while (i <= j) {
            int avg = (i + j) / 2;
            if (avg * avg == x) {
                return avg;
            } else if (avg * avg < x) {
                i = avg + 1;
            } else
                j = avg - 1;
        }
        return j;
    }
}

//移除元素  https://leetcode.cn/problems/remove-element/
@SuppressWarnings("all")
class Solution_2 {
    /**
    *   @define x往前找，k始终在最后一个不同数字
    *   @param [nums, val]
    *   @return int
    */
    public static int removeElement(int[] nums, int val) {
        int x = 0; // 用于遍历数组
        int k = 0; // 记录非 val 元素的数量

        while (x < nums.length) {
            if (nums[x] != val) {
                nums[k] = nums[x]; // 将非 val 元素前移
                k++;
            }
            x++;
        }
        return k;
    }
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int k = Solution_2.removeElement(nums, val);
        System.out.println(k);
        for (int i = 0; i < k; i++) {
            System.out.println(nums[i]);
        }
    }
}

//有序数组的平方   https://leetcode.cn/problems/squares-of-a-sorted-array/
@SuppressWarnings("all")
class Solution_3 {
    /**
    *   @define 找到正负分界线，从小打到排列，类似归并
    *   @param [nums]
    *   @return int[]
    */
    public static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int i = 0;
        int count = 0;
        while(i < nums.length && nums[i] < 0)
            i++;
        int j = i - 1;
        while(j >= 0 && i < nums.length){
            if((-nums[j]) < nums[i]){
                result[count] = nums[j] * nums[j];
                count++;
                j--;
            }
            else {
                result[count] = nums[i] * nums[i];
                count++;
                i++;
            }
        }
        while(j >= 0){
            result[count] = nums[j] * nums[j];
            count++;
            j--;
        }
        while(i < nums.length){
            result[count] = nums[i] * nums[i];
            count++;
            i++;
        }
        return result;
    }
    /**
    *   @define 与其去找中间的分界，不如从两端最大的开始，反着插入result
    *   @param [nums]
    *   @return int[]
    */
    public int[] sortedSquares_02(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                // 正数的相对位置是不变的， 需要调整的是负数平方后的相对位置
                result[index--] = nums[left] * nums[left];
                ++left;
            } else {
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        int[] nums1 = Solution_3.sortedSquares(nums);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
        }
    }
}
