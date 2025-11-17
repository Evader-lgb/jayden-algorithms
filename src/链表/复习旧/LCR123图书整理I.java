package 链表.复习旧;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCR123图书整理I {

    public static void main(String[] args) {
        // 测试输入是[3,6,4,1]
        LCR123图书整理I solution = new LCR123图书整理I();
        ListNode head = solution.new ListNode(3);
        head.next = solution.new ListNode(6);
        head.next.next = solution.new ListNode(4);
        head.next.next.next = solution.new ListNode(1);
        int[] result = solution.reverseBookList(head);
        System.out.println(Arrays.toString(result)); // 输出应为[1,4,6,3]
    }

    /**
     * 自己的解法：先反转链表，再遍历链表存入数组
     * 时间复杂度O（n）
     * 空间复杂度O（n）
     *
     * 其他解法：使用栈或者递归，时间复杂度空间复杂度都是一样的，就是递归可能有递归深度的问题
     * @param head
     * @return
     */
    public int[] reverseBookList(ListNode head) {

        ListNode cur = head;
        ListNode pre = null;

        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }


        List<Integer> resList = new ArrayList<>();
        while (pre != null){
            resList.add(pre.val);
            pre = pre.next;
        }

        int index = 0;
        int[] res = new int[resList.size()];
        for (Integer i : resList) {
            res[index] = i;
            index++;
        }

        return res;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
