package 哈希表;

import java.util.HashSet;
import java.util.Set;

public class P141_环形链表 {
    public static void main(String[] args) {

    }

    public boolean hasCycle1(ListNode head) {

        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 检测到空指针意味着链表无环，而永不遇到空指针（在有限步骤内）则意味着存在环，最终快慢指针会相遇
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        // 当快慢指针不相遇时继续循环
        while (slow != fast) {
            // 检测到空指针，说明无环
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
}
