package 链表.复习旧;

import java.util.ArrayList;
import java.util.List;

public class P2_两数相加 {

    public static void main(String[] args) {
        System.out.println(addTwoNumbers(null, null).val);
        System.out.println(13 / 10);
        System.out.println(13 % 10);
    }


    /**
     * TODO 错题。虽然错了，但是思路是有的，这是23年刷的题了，跟新题没啥区别，也还算可以了
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return new ListNode(0);
        }

        List<Integer> l1List = new ArrayList<>();
        List<Integer> l2List = new ArrayList<>();
        // 遍历2个链表，放到数组里，只要一个节点有next就循环继续，没下一个节点的
        while (l1 != null || l2 != null){
            if (l1 != null){
                l1List.add(l1.val);
                l1 = l1.next;
            }
            if (l2 != null){
                l2List.add(l2.val);
                l2 = l2.next;
            }
        }

        // 把2个列表相加
        int m = l1List.size();
        int n = l2List.size();
        int max = Math.max(m,n);

        int[] result = new int[max+1];
        int carry = 0;
        int resultIndex = 0;
        int l1Index = 0;
        int l2Index = 0;
        while (l1Index < m || l2Index<n){
            Integer l1Num = l1Index < m ? l1List.get(l1Index) : 0;
            Integer l2Num = l2Index < n ? l2List.get(l2Index) : 0;
            Integer curSum = l1Num + l2Num + carry;

            // 进位
            Integer pos1 = curSum / 10;
            // 当前位
            Integer pos2 = curSum % 10;

            result[resultIndex] = pos2;
            result[resultIndex + 1] = result[resultIndex + 1] + pos1;

            resultIndex++;
        }


        return null;
    }

    /**
     * AI补全版，说明思路是通的，就是时间复杂度跟空间复杂度都比题解高
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return new ListNode(0);
        }

        List<Integer> l1List = new ArrayList<>();
        List<Integer> l2List = new ArrayList<>();
        // 遍历2个链表，放到数组里，只要一个节点有next就循环继续，没下一个节点的
        while (l1 != null || l2 != null){
            if (l1 != null){
                l1List.add(l1.val);
                l1 = l1.next;
            }
            if (l2 != null){
                l2List.add(l2.val);
                l2 = l2.next;
            }
        }

        // 把2个列表相加
        int m = l1List.size();
        int n = l2List.size();
        int max = Math.max(m,n);

        int[] result = new int[max+1];
        int carry = 0;
        int resultIndex = 0;
        int l1Index = 0;
        int l2Index = 0;

        while (l1Index < m || l2Index < n || carry != 0){
            Integer l1Num = l1Index < m ? l1List.get(l1Index) : 0;
            Integer l2Num = l2Index < n ? l2List.get(l2Index) : 0;
            Integer curSum = l1Num + l2Num + carry;

            // 当前位
            result[resultIndex] = curSum % 10;
            // 进位
            carry = curSum / 10;

            resultIndex++;
            l1Index++;
            l2Index++;
        }

        // 构建结果链表
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 检查是否最高位是0，如果是则忽略
        int endIndex = (result[result.length - 1] == 0) ? result.length - 1 : result.length;

        for (int i = 0; i < endIndex; i++) {
            current.next = new ListNode(result[i]);
            current = current.next;
        }

        return dummy.next;
    }


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        /**
         * TODO
         * 小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，
         * 该指针的下一个节点指向真正的头结点 head。使用预先指针的目的在于链表初始化时无可用节点值，
         * 而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
         * @param l1
         * @param l2
         * @return
         */
        public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
            ListNode pre = new ListNode(0);
            ListNode cur = pre;

            int carry = 0;
            while (l1 != null || l2!=null){
                int num1 = l1 != null ? l1.val : 0;
                int num2 = l2 != null ? l2.val : 0;
                int sum = num1 + num2 + carry;

                carry = sum/10;
                sum = sum % 10;
                cur.next = new ListNode(sum);
                cur = cur.next;

                if(l1 != null){
                    l1 = l1.next;
                }
                if (l2 != null){
                    l2 = l2.next;
                }
            }
            if (carry == 1){
                cur.next = new ListNode(carry);
            }

            return pre.next;
         }
    }


    static class ListNode {
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
