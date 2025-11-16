package 链表.复习旧;

public class P206_反转链表 {

      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }


      // TODO 错题一
    public ListNode reverseList(ListNode head) {
//          ListNode curr = null;
//          ListNode end =
//          while(head != null){
//              ListNode temp = head;
//              head.next = head;
//          }
//          return curr;
        return null;
    }

    /**
     * 双指针解法
     * 时间复杂度O（n）
     * 空间复杂度O（1）
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
          ListNode curr = head;
          ListNode pre = null;
          while(curr != null){
              ListNode temp = curr.next;
              curr.next = pre;
              pre = curr;
              curr = temp;
          }
          return pre;
    }

    /**
     * 递归解法
     * 时间复杂度O（n）
     * 空间复杂度O（n）
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        return digui(head,null);

    }

    /**
     * 递归方法
     * @param curr
     * @param pre
     * @return
     */
    public ListNode digui(ListNode curr,ListNode pre) {
        if (curr == null){
            return pre;
        }
        ListNode res = digui(curr.next,curr);
        curr.next = pre;
        return res;
    }

}
