## 第一题
题目链接：移除链表元素 https://leetcode.cn/problems/remove-linked-list-elements/

题目描述：给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回新的头节点。

### 总结

注意创建dummy指针，更方便操作，使操作更有规律



## 第三题
题目链接：两两交换链表中的节点 https://leetcode.cn/problems/swap-nodes-in-pairs/description/

题目描述：给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，==只能进行节点交换==）。

### 总结

链表涉及到多个指针操作时，退出的条件不好判断，奇数和偶数个元素的情况需要分开讨论，如果使用虚拟头节点dummy就能很好的解决这种情况。
