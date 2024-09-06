==一般来说哈希表都是用来快速判断一个元素是否出现集合里。==

## 第二题

题目链接：两个数组的交集  https://leetcode.cn/problems/intersection-of-two-arrays/description/

题目描述：给定两个数组 nums1 和 nums2 ，返回它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序

### 总结

使用HashSet，唯一存储在nums1出现过的数字，然后遍历nums2，如果nums2中的数字出现在HashSet中，就加入结果list，最后list转int[]



## 第三题

题目链接：快乐数  https://leetcode.cn/problems/happy-number/description/

题目描述：编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」 定义为：

对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。

然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。

如果这个过程 结果为 1，那么这个数就是快乐数。

### 总结

之前的尝试一直没有把计算各个位数的平方和抽离出来，导致双层循环结构很乱。第二次尝试通过输出100次2的结果，发现如果重复出现sum

就说明是无限循环，由此得到可以用HashSet来记录出现的sum。



## 第四题

题目链接：两数之和 https://leetcode.cn/problems/two-sum/description/

题目描述：给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target  的那两个整数，并返回它们的数组下标。

### 总结

这道题最直接的做法是两层循环，但是时间复杂度达到了O(n^2)，所以更快的方法是使用HashMap，存储数组中每一个数的另一半，

如果在遍历数组的过程中发现该数字在HashMap中出现过，说明该数字与之前的数字是一对，再返回Map中存储的下标以及当前下标即可。


## 第五题

题目链接：四数相加II https://leetcode.cn/problems/4sum-ii/description/

题目描述：给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：

0 <= i, j, k, l < n

nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

### 总结

这道题目刚拿到没有什么思路，是因为陷入一种误区，所有题都想追求低时间复杂度，但其实这道题可能没有低复杂度的算法。使用两次双层循环，将四个数组分为

两个大数组，再用Map查找看目标值是否出现过，注意计数的count不是每次加一，而是加上第一个大数组的目标出现个数，相当于乘法。



## 第七题

题目链接：三数相加 https://leetcode.cn/problems/3sum/description/

题目描述：给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，

同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且**不重复**的三元组。：

### 总结

1. 这道题难就难在如何去重，对于一个杂乱的数组，重复的搭配可能出现在任何地方，所以首先想到应该进行排序，这样相同的数字就放在了一起，在处理重复时就更有规律。

2. 首先是得到符合条件的搭配，用三个指针，i、j、k分别指向第一二三个元素，对于每一个i，控制j和k找到相加为0的，方法就是比大小，小了往后移，大了往前移。

3. 然后就是去重，那对于两个重复的数字，是选择前一个还是后一个呢。答案应该是选择前一个。因为如果选择后一个跳过前一个的话，就会忽略这两个重复数字成为一组搭配的情况，比如[0,0,0],[-1,-1,2]。对于j、k处理相同。

4. 所以是一遍边找符合条件的搭配，一边进行去重，关键还是要先排序。
