//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
//
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 3083 👎 0


package codetop100;

import java.util.PriorityQueue;

/**
 * 合并 K 个升序链表
 * @author Jayden
 * @date 2025-10-18 22:33:08
 */
public class T26_P23_MergeKSortedLists {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T26_P23_MergeKSortedLists().new Solution();
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
    public ListNode mergeKLists(ListNode[] lists) {
        // 边界情况处理：如果链表数组为空或者长度为0，直接返回null
        if(lists == null || lists.length == 0){
            return null;
        }

        // 创建最小堆（优先队列），按节点的值进行排序
        // 使用lambda表达式定义比较器
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> a.val - b.val);

        // 步骤一：将所有链表的头节点加入最小堆
        for (ListNode list : lists) {
            if (list != null){
                minHeap.offer(list);
            }
        }

        // 创建哑节点，简化链表操作
        // dummy节点不存储实际数据，它的next指向合并后后链表的头节点
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 步骤2:循环从堆中取出最小节点，构建结果列表
        while (!minHeap.isEmpty()){
            // 从最小堆中取出当前最小的节点
            ListNode minNode = minHeap.poll();

            // 将取出的最小节点连接到结果链表
            current.next = minNode;
            current = current.next;// 移动current指针

            // 步骤3:如果取出的节点还有下一个节点，将下一个节点加入堆中
            if (minNode.next != null){
                minHeap.offer(minNode.next);
            }
        }

        // 返回合并后链表的头节点
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
