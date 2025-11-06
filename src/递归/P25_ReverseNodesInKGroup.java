//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 
//提示：
//
// 
// 链表中的节点数目为 n 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？ 
//
// 
// 
//
// Related Topics 递归 链表 👍 2665 👎 0


package 递归;

/**
 * K 个一组翻转链表
 * @author Jayden
 * @date 2025-10-14 19:46:31
 */
public class P25_ReverseNodesInKGroup{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P25_ReverseNodesInKGroup().new Solution();
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
    public ListNode reverseKGroup(ListNode head, int k) {
        // 边界情况处理：如果链表为空或k=1（不需要翻转），直接返回头节点
        if(head == null || k==1){
            return head;
        }

        // 步骤1:检查当前部分是否有k个节点
        // 使用指针遍历k个节点，如果不足k个则直接返回当前头节点（不翻转）
        ListNode check = head;
        for (int i = 0; i < k; i++) {
            // 如果当前节点为空，说明剩余节点不足k个，直接返回头节点
            if (check == null){
                return head;
            }
            check = check.next;
        }

        // 步骤2:翻转当前k个节点
        // 使用头插法进行翻转
        ListNode prev = null; // 前驱节点，初始为null
        ListNode curr = head; // 当前节点，从head开始
        ListNode next = null; // 后继节点，用于临时存储

        // 翻转k个节点
        for (int i = 0; i < k; i++) {
            // 保存当前节点的下一个节点
            next = curr.next;
            // 将当前节点的next指向前驱节点，实现翻转
            curr.next = prev;
            // 移动前驱节点到当前节点
            prev = curr;
            // 移动当前节点到下一个节点
            curr = next;
        }

        // 步骤3:递归处理剩余部分，并连接起来
        // 此时：
        // -prev是当前翻转后的新头节点
        // -head是当前翻转后的尾节点
        // -curr是下一组的头节点

        // 将当前组的尾节点（愿头节点）连接到下一组的翻转后的头节点
        head.next = reverseKGroup(curr,k);

        // 返回当前组翻转后的新头节点
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
