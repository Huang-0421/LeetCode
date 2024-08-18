package List;
/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/15 下午4:58
 */
public class Solution {
}

// 移除链表元素 https://leetcode.cn/problems/remove-linked-list-elements/
@SuppressWarnings("all")
class Solution_1 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else
                prev = prev.next;
        }
        return dummy.next;
    }
}

//反转列表 https://leetcode.cn/problems/reverse-linked-list/submissions/555827846/
@SuppressWarnings("all")
class Solution_2 {
    //双指针法
    public static ListNode reverseList_01(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode a = head.next;
        ListNode b = a.next;
        head.next = null;
        while (b != null) {
            a.next = head;
            head = a;
            a = b;
            b = b.next;
        }
        a.next = head;
        return a;
    }

    //递归法
    public ListNode reverseList_02(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode temp = null;
        temp = cur.next;// 先保存下一个节点
        cur.next = prev;// 反转
        return reverse(cur, temp);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode node = reverseList_01(head);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}

//两两交换链表中的节点 https://leetcode.cn/problems/swap-nodes-in-pairs/description/
@SuppressWarnings("all")
class Solution_3 {
    //没有使用虚拟头结点
    public ListNode swapPairs_01(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode two = head.next;
        ListNode newHead = head.next;
        ListNode three = two;
        while (three.next != null) {
            if (three.next.next != null) {
                three = three.next.next;
                head.next = three;
                ListNode temp = two.next;
                two.next = head;
                head = temp;
                two = head.next;
            } else{
                three = three.next;
                head.next = three;
                two.next = head;
            }
        }
        if(two == three){
            two.next = head;
            head.next = null;
        }
        return newHead;
    }
    //使用虚拟头结点
    public static ListNode swapPairs_02(ListNode head){
        if (head == null || head.next == null)
            return head;
        ListNode newHead = head.next;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = head;
        while(head != null && head.next != null){
            prev = head.next;
            dummy.next = prev;
            ListNode temp = prev.next;
            prev.next = head;
            head.next = temp;
            dummy = head;
            head = dummy.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        ListNode node = swapPairs_02(listNode);
    }
}

// 删除链表倒数第n个节点  https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
@SuppressWarnings("all")
class Solution_4{
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        while(n != 1){
            head= head.next;
            n--;
        }
        while(head.next != null){
            head = head.next;
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return dummy.next;
    }
}

@SuppressWarnings("all")
class Solution_5{
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        ListNode newHeadA = headA;
        ListNode newHeadB = headB;
        while(headA.next != null && headB.next != null){
            headA = headA.next;
            headB = headB.next;
        }
        int count = 0;
        boolean sign = true;//true 代表A长
        while(headA.next != null){
            headA = headA.next;
            count++;
            sign = true;
        }
        while(headB.next != null){
            headB = headB.next;
            count++;
            sign = false;
        }
        if(sign){
            while(count > 0){
                newHeadA = newHeadA.next;
                count--;
            }
        }
        else{
            while(count > 0){
                newHeadB = newHeadB.next;
                count--;
            }
        }
        while(newHeadA != newHeadB){
            newHeadA = newHeadA.next;
            newHeadB = newHeadB.next;
        }
        return newHeadA;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        ListNode A1 = new ListNode(1);
        headA.next = A1;
        ListNode A2 = new ListNode(8);
        A1.next = A2;
        ListNode A3 = new ListNode(4);
        A2.next = A3;
        ListNode A4 = new ListNode(5);
        A3.next = A4;
        ListNode headB = new ListNode(5);
        ListNode B1 = new ListNode(0);
        headB.next = B1;
        ListNode B2 = new ListNode(1);
        B1.next = B2;
        B2.next = A2;
        getIntersectionNode(headA,headB);
    }
}

// 环形链表 https://leetcode.cn/problems/linked-list-cycle-ii/
@SuppressWarnings("all")
class Solution_6{
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        if(head == null)
            return null;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast)
                break;
        }
        if(fast.next == null || fast.next.next == null){
            return null;
        }
        ListNode slow1 = head;
        while(fast != slow1){
            fast = fast.next;
            slow1 = slow1.next;
        }
        return slow1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode h1 = new ListNode(2);
        head.next = h1;
        h1.next = new ListNode(0);
        h1.next.next = new ListNode(-4);
        h1.next.next.next = h1;
        detectCycle(head);
    }
}