//给定一个头节点为 head 的链表用于记录一系列核心肌群训练项目编号，请查找并返回倒数第 cnt 个训练项目编号。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [2,4,7,8], cnt = 1
//输出：8 
//
// 
//
// 提示： 
//
// 
// 1 <= head.length <= 100 
// 0 <= head[i] <= 100 
// 1 <= cnt <= head.length 
// 
//
// 
//
// Related Topics 链表 双指针 👍 538 👎 0


package codetop100;

/**
 * 训练计划 II
 * @author Jayden
 * @date 2025-10-04 16:22:59
 */
public class T60_PLCR140_LianBiaoZhongDaoShuDiKgeJieDianLcof {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T60_PLCR140_LianBiaoZhongDaoShuDiKgeJieDianLcof().new Solution();
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
    public ListNode trainingPlan(ListNode head, int cnt) {
        /**
         * 快慢指针思想
         */
        // 先让快指针走cnt 步
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && cnt > 0){
            fast = fast.next;
            cnt--;
        }

        // 快慢指针一起走，当快指针到头了，慢指针就到了
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}

   class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
//leetcode submit region end(Prohibit modification and deletion)

}
