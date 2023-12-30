package 力扣刷题之路.day1_231229;

/**
 * 【中等】
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class 两数相加 {

    public static void main(String[] args) {
        System.out.println(11/10);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2!=null){
            // 取出当前节点的值
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            // 算出两数相加的值
            int sum = x + y + carry;

            // 算出需要进位的值
            carry = sum / 10;

            // 算出下一个节点的值
            sum = sum%10;

            // 移动节点
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }

        // 如果carry最后为1，表示还要进位，加一个节点
        if (carry == 1){
            cur.next = new ListNode(carry);
        }

        return pre.next;
    }

}

class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) {
      this.val = val;
  }
  ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
  }
}