package 链表;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/">相交链表</a>
 * <p>
 * 编写一个程序，找到两个单链表相交的起始节点。
 * </p>
 * <p>
 * 如下面的两个链表：
 * </p>
 * <img src="https://assets.leetcode.com/uploads/2021/03/05/
 * ex1.jpg" style="width: 500px; height: 200px;" />
 * <p>
 *     在节点 c1 开始相交。
 *     </p>
 *     <p>
 *         示例 1：
 *         </p>
 *         <pre>
 *             输入：intersectVal = 8, listA = [4,1,
 *             8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 *             输出：Reference of the node with value = 8
 *             输入解释：相交节点的值为 8 （注意，如果两个列表相
 *             交则不能为 0）。从各自的表头开始算起，链表 A 为
 *             [4,1,8,4,5]，链表 B 为 [
 *             5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；
 *             在 B 中，相交节点前有 3 个节点。
 *             </pre>
 *             <p>
 *
 */
public class P160_相交链表 {
    public static void main(String[] args) {

    }

    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> nodeSet = new HashSet<>();
            ListNode temp = headA;
            while (temp != null){
                nodeSet.add(temp);
                temp = temp.next;
            }

            temp = headB;
            while (temp != null){
                if (nodeSet.contains(temp)){
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
