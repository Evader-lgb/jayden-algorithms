//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// 
//L0 → L1 → … → Ln - 1 → Ln
// 
//
// 请将其重新排列后变为： 
//
// 
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [1,2,3,4]
//输出：[1,4,2,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000 
// 
//
// Related Topics 栈 递归 链表 双指针 👍 1640 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 重排链表
 * @author Jayden
 * @date 2025-10-25 18:32:23
 */
public class P143_ReorderList{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P143_ReorderList().new Solution();
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
    public void reorderList(ListNode head) {
        // 边界情况处理
        if(head == null || head.next == null){
            return;
        }

        // 步骤一：快慢指针找到链表中间的点
        ListNode slow = head;
        ListNode fast = head;
        // 快指针走到末尾，慢指针走到中间，或者中间的前一个
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // 步骤二：分割链表为前后部分
        // 后半部分的头节点是中点的下一个节点
        ListNode secondHalf = slow.next;
        // 断开前后两部分的连接
        slow.next = null;

        // 步骤三：后半部分反转
        secondHalf = reverseList(secondHalf);

        // 步骤四：合并两个链表
        mergeLists(head,secondHalf);
    }

    private void mergeLists(ListNode l1, ListNode l2) {
        while (l1 !=null && l2 != null){
            // 保存两个链表的下一个节点
            ListNode l1Next = l1.next;
            ListNode l2Next = l2.next;

            // 将l2插入到l1和l1next之间
            l1.next = l2;
            l2.next = l1Next;

            // 移动指针到下一组合并的位置
            l1 = l1Next;
            l2 = l2Next;
        }

    }

    private Solution.ListNode reverseList(Solution.ListNode head) {
        ListNode prev = null; // 前驱节点
        ListNode curr = head; // 当前节点

        while (curr != null){
            ListNode nextTemp = curr.next; // 保存下一个节点
            curr.next = prev; // 反转指针方向
            prev = curr; // 移动prev指针
            curr = nextTemp;// 移动curr指针
        }

        return prev;
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
