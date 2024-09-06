package HashTable;

import java.util.*;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/31 下午2:04
 */
@SuppressWarnings("all")
public class Solution {
}

@SuppressWarnings("all")
class Solution_1 {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        if (s.length() != t.length()) return false;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (map.getOrDefault(c, 0) == 0) return false;
            else map.put(c, map.getOrDefault(c, 0) - 1);
        }
        return true;
    }
}

@SuppressWarnings("all")
class Solution_2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.getOrDefault(nums1[i], 0) == 0)
                map.put(nums1[i], 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.getOrDefault(nums2[i], 0) == 1) {
                list.add(nums2[i]);
                map.put(nums2[i], 0);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

@SuppressWarnings("all")
class Solution_3 {
    public static int calculate(int n) {
        int result = 0;
        while (n > 0) {
            result += (n % 10) * (n % 10);
            n /= 10;
        }
        return result;
    }

    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        while (true) {
            n = calculate(n);
            if (n == 1)
                return true;
            else if (set.contains(n)) {
                return false;
            }
            set.add(n);
        }
    }

    public static void main(String[] args) {
        isHappy(2);
    }

}

@SuppressWarnings("all")
class Solution_4 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            Integer goal = map.get(nums[i]);
            if (goal != null) {
                res[0] = i;
                res[1] = goal;
                break;
            }
            map.put(target - nums[i], i);
        }
        return res;
    }
}

@SuppressWarnings("all")
class Solution_5 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int length = nums1.length;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                map.put(nums1[i] + nums2[j], map.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int target = nums3[i] + nums4[j];
                if (map.get(0 - target) != null) {
                    count += map.get(0 - target);
                    map.put(0 - target, map.get(0 - target) - 1);
                }
            }
        }
        return count;
    }
}

@SuppressWarnings("all")
class Solution_6 {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char a : magazine.toCharArray()) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (map.getOrDefault(c, 0) == 0)
                return false;
            map.put(c, map.getOrDefault(c, 0) - 1);
        }
        return true;
    }
}

@SuppressWarnings("all")
class Solution_7 {
    //该方法不方便处理查重
    public List<List<Integer>> threeSum_01(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int targetNum = 0 - (nums[i] + nums[j]);
                Integer target = map.get(targetNum);
                if (target != null) {
                    if (target > i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[target]);
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }

    //[-1,0,1,2,-1,-4] -> [-4,-1,-1,0,1,2]
    public List<List<Integer>> threeSum_02(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //先将数组排序(关键)
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int target = nums[i] + nums[j] + nums[k];
                if (target == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else if (target < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}

@SuppressWarnings("all")
// [1000000000,1000000000,1000000000,1000000000]   -294967296
class Solution_8 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int m = j + 1;
                int n = nums.length - 1;
                while (m < n) {
                    long sum = (long) nums[i] + nums[j] + nums[m] + nums[n];
                    if(sum == target) {
                        res.add(Arrays.asList(nums[i] + nums[j] + nums[m] + nums[n]));
                        m++;
                        n--;
                        while (m < n && nums[m] == nums[m - 1]) m++;
                        while (m < n && nums[n] == nums[n + 1]) n--;
                    }
                    else if(nums[i] + nums[j] + nums[m] + nums[n] < target) m++;
                    else n--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.print(2000000000 + 2000000000 + 1000000000 + 1000000000);
    }
}
