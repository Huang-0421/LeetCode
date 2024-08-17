package Array;

import java.util.Arrays;
import java.util.Scanner;

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
     * @param [nums, val]
     * @return int
     * @define x往前找，k始终在最后一个不同数字
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
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
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
     * @param [nums]
     * @return int[]
     * @define 找到正负分界线，从小打到排列，类似归并
     */
    public static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int i = 0;
        int count = 0;
        while (i < nums.length && nums[i] < 0)
            i++;
        int j = i - 1;
        while (j >= 0 && i < nums.length) {
            if ((-nums[j]) < nums[i]) {
                result[count] = nums[j] * nums[j];
                count++;
                j--;
            } else {
                result[count] = nums[i] * nums[i];
                count++;
                i++;
            }
        }
        while (j >= 0) {
            result[count] = nums[j] * nums[j];
            count++;
            j--;
        }
        while (i < nums.length) {
            result[count] = nums[i] * nums[i];
            count++;
            i++;
        }
        return result;
    }

    /**
     * @param [nums]
     * @return int[]
     * @define 与其去找中间的分界，不如从两端最大的开始，反着插入result
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
        int[] nums = {-4, -1, 0, 3, 10};
        int[] nums1 = Solution_3.sortedSquares(nums);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
        }
    }
}

//长度最小的子数组  https://leetcode.cn/problems/minimum-size-subarray-sum/description/
//超时算法
@SuppressWarnings("all")
class Solution_4_1 {
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        return findCount(nums, left, right, target, 0);
    }

    //  target = 7, nums = [2,3,1,2,4,3]
    public int findCount(int[] nums, int left, int right, int target, int count_now) {
        if (left > right) {
            return count_now;
        }
        int sum = getSum(nums, left, right);
        if (sum >= target) {
            count_now = right - left + 1;
            int leftcount = findCount(nums, left, right - 1, target, count_now);
            int rightcount = findCount(nums, left + 1, right, target, count_now);
            if (leftcount < rightcount)
                return leftcount;
            else
                return rightcount;
        } else {
            return count_now;
        }
    }

    public int getSum(int[] nums, int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 4};
        int target = 4;
        Solution_4_1 obj = new Solution_4_1();
        System.out.print(obj.minSubArrayLen(target, nums));
    }
}

//滑动窗口 O(n)
@SuppressWarnings("all")
class Solution_4_2 {
    //  target = 7, nums = [2,3,1,2,4,3]
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        int sum = 0;
        int j = 0;
        int result = length + 1;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            while (sum >= target) {
                result = i - j + 1 < result ? i - j + 1 : result;
                sum -= nums[j];
                j++;
            }
        }
        return result > length ? 0 : result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        Solution_4_2 obj = new Solution_4_2();
        System.out.print(obj.minSubArrayLen(target, nums));
    }
}

//构造前缀和数组 + 二分查找
@SuppressWarnings("all")
class Solution_4_3 {
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        int result = Integer.MAX_VALUE;
        int[] sums = new int[length + 1];
        sums[0] = 0;
        for (int i = 1; i < length + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i < length + 1; i++) {
            int s = sums[i - 1] + target;
            int bound = Arrays.binarySearch(sums, s);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound > length) {
                break;
            }
            result = bound - i + 1 < result ? bound - i + 1 : result;
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}

//螺旋矩阵  https://leetcode.cn/problems/spiral-matrix-ii/
@SuppressWarnings("all")
class Solution_5 {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int value = 1;
        int count = n - 1;
        int direct = 0; //下左为 0，上右为 1
        int i = 0, j = 0;
        //处理第一排
        for (; j < n; j++) {
            result[0][j] = value++;
        }
        j--;
        //开始螺旋  i = 0; j = n - 1;
        while (count > 0) {
            if (direct == 0) {
                //往下填充
                for (int k = 0; k < count; k++) {
                    result[++i][j] = value++;
                }
                //往左填充
                for (int k = 0; k < count; k++) {
                    result[i][--j] = value++;
                }
            }
            if (direct == 1) {
                //往上填充
                for (int k = 0; k < count; k++) {
                    result[--i][j] = value++;
                }
                //往右填充
                for (int k = 0; k < count; k++) {
                    result[i][++j] = value++;
                }
            }
            count--;
            direct = direct == 0 ? 1 : 0;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution_5 solution = new Solution_5();
        int[][] x = solution.generateMatrix(4);
    }
}

//区间和   https://kamacoder.com/problempage.php?pid=1070
@SuppressWarnings("all")
class Solution_6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] nums = new int[n + 1];
        nums[0] = 0;
        for (int i = 1; i <= n; i++) {
            nums[i] = scan.nextInt() + nums[i - 1];
        }
        while (scan.hasNext()) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            System.out.println(nums[b + 1] - nums[a]);
        }
    }
}

//开发商购买土地   https://kamacoder.com/problempage.php?pid=1044
@SuppressWarnings("all")
class Solution_7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int count = 0;
        int[] row = new int[n];
        int[] column = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = scan.nextInt();
                count += x;
                row[i] += x;
                column[j] += x;
            }
        }
        //for row
        int result_row = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        for (int i = 0; i < n - 1; i++) {
            left += row[i];
            right = count - left;
            int differ = Math.abs(right - left);
            result_row = differ < result_row ? differ : result_row;
        }
        //for column
        int result_column = Integer.MAX_VALUE;
        left = 0;
        right = 0;
        for (int i = 0; i < m - 1; i++) {
            left += column[i];
            right = count - left;
            int differ = Math.abs(right - left);
            result_column = differ < result_column? differ : result_column;
        }
        System.out.print(result_row<result_column?result_row:result_column);
    }
}