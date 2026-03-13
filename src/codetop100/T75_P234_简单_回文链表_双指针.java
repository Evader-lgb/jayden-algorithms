//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
//
// Related Topics 栈 递归 链表 双指针 👍 2198 👎 0


package codetop100;


/**
 * 回文链表
 * @author Jayden
 * @date 2026-02-15 22:10:14
 */
public class T75_P234_简单_回文链表_双指针 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T75_P234_简单_回文链表_双指针().new Solution();
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
    public boolean isPalindrome(Solution.ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        Solution.ListNode slow = head;
        Solution.ListNode fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Solution.ListNode reverse = reverseList(slow);

        Solution.ListNode first = head;
        Solution.ListNode second = reverse;
        while (second!=null){
            if (second.val != first.val){
                return false;
            }
            first = first.next;
            second = second.next;
        }

        return true;
    }

    public Solution.ListNode reverseList(Solution.ListNode node){
        Solution.ListNode prev = null;
        Solution.ListNode current = node;

        while (current!=null){
            Solution.ListNode temp = current.next;
            current.next =  prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    class ListNode {
        int val;
        Solution.ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, Solution.ListNode next) { this.val = val; this.next = next; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
