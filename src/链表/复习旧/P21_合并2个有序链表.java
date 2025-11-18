package 链表.复习旧;

/**
 * 思路
 *
 */
public class P21_合并2个有序链表 {

    public static void main(String[] args) {
        // 模拟list1 =
        //[1,2,4]
        //list2 =
        //[1,3,4]
        ListNode list1 = new ListNode(1);
//        list1.next = new ListNode(2);
//        list1.next.next = new ListNode(4);
        ListNode list2 = new ListNode(2);
//        list2.next = new ListNode(3);
//        list2.next.next = new ListNode(4);
        ListNode res = mergeTwoLists2(list1, list2);
        System.out.println(res.val);
        System.out.println(res.next.val);
    }

    /**
     * 自己复习的解法ac了，但是判断感觉太复杂了
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while(list1 != null || list2 != null){
            int num1 = list1 != null ? list1.val : 0;
            int num2 = list2 != null ? list2.val : 0;

            if (num1 >= num2 && list2 != null){
                curr.next = new ListNode(list2.val);
                list2 = list2.next;
            }else if(list1 != null){
                curr.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                curr.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            curr = curr.next;
        }

        return dummy.next;
    }


    /**
     * 这道题的标准解法之一
     * 先压栈的应该是比较大的值这是考虑到的
     *
     * 没考虑到的是每次返回的是什么list还是list.next？
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }

        if(list2 == null){
            return list1;
        }

        if (list2.val>list1.val){
            list1.next = mergeTwoLists2(list1.next,list2);
            return list1;
        }else {
            list2.next = mergeTwoLists2(list1,list2.next);
            return list2;
        }

    }


    public static class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

}
