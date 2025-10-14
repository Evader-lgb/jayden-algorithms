//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// Related Topics 链表 双指针 👍 3193 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.List;

/**
 * 删除链表的倒数第 N 个结点
 * @author Jayden
 * @date 2025-10-14 19:49:30
 */
public class P19_RemoveNthNodeFromEndOfList{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P19_RemoveNthNodeFromEndOfList().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 步骤1:创建虚拟头节点，用于处理删除头节点的特殊情况
        // 虚拟头节点指向实际的头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 步骤2:初始化快慢指针，均指向虚拟头节点
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 步骤3:快指针先向前移动n步
        // 这样快慢指针之间的距离为n
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 步骤4:同时移动快慢指针，直到快指针到达链表末尾
        // 当快指针指向最后一个节点时，慢指针正好指向要删除节点的前一个节点
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        // 步骤5:删除目标节点
        // slow.next就是要删除的节点，将其跳过即可
        slow.next = slow.next.next;

        // 步骤6:返回新的头节点（虚拟节点的下一个节点）
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
