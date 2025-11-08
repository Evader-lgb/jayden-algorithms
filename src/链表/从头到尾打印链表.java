package 链表;

import java.util.Stack;

public class 从头到尾打印链表 {
    // 声明一个栈
    static Stack<ListNode> stack = new Stack<ListNode>();

    public int[] reversePrint(ListNode head) {
        // 将链表压栈
        recurrence(head);

        // 初始化返回数组
        int[] result = new int[stack.size()];

        // 弹栈将元素放入返回数组中
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop().val;
        }

        // 返回结果
        return result;
    }

    public static void recurrence(ListNode node){
        if (node != null){
            stack.push(node);
            recurrence(node.next);
        }
    }

    public class ListNode { int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }
}
