package 链表;

public class P2_两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 初始化返回值
        ListNode init = new ListNode(0);
        ListNode cur = init;

        // 定义进位值
        int carry = 0;

        // 循环处理两个节点，只要有一个节点不为空都要处理，长度不一样补0
        while (l1 != null || l2 != null){
            // 补0
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            // 计算两个节点的和
            int sum = x + y + carry;

            carry = sum / 10;

            // 计算个位上的结果
            sum = sum / 10;

            // 创建下一个节点
            cur.next = new ListNode(sum);
            cur = cur.next;

            // 两个节点往后移动一位
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }

        // 如果有进位值，需要进位
        if (carry == 1){
            cur.next = new ListNode(carry);
        }

        // 返回返回值
        return init.next;
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
}
