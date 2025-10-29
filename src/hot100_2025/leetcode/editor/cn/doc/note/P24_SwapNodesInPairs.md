# 两两交换链表中的节点（Swap Nodes in Pairs）解题详解

## 解题思路

**总思路**：使用迭代或递归方法，每次处理两个相邻节点，交换它们的位置，然后继续处理后续节点。

**分步骤**：
1. 创建虚拟头节点，简化头节点的处理
2. 使用三个指针：prev（前一个节点）、first（第一个节点）、second（第二个节点）
3. 每次交换first和second节点
4. 更新指针关系，继续处理下一对节点
5. 返回虚拟头节点的下一个节点

## 归类说明
- **主要归类**：链表、节点交换
- **算法技巧**：指针操作、递归
- **相关题型**：反转链表、K个一组翻转链表、重排链表

## Java代码实现

```java
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class SwapNodesInPairs {
    /**
     * 迭代解法
     * @param head 链表头节点
     * @return 交换后的链表头节点
     */
    public ListNode swapPairs(ListNode head) {
        // 创建虚拟头节点，简化边界情况处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        
        // 当存在至少两个节点时进行交换
        while (prev.next != null && prev.next.next != null) {
            // 获取当前要交换的两个节点
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            // 执行交换操作
            first.next = second.next;  // first指向second的下一个节点
            second.next = first;       // second指向first
            prev.next = second;        // prev指向second
            
            // 移动prev指针到下一对的前一个位置
            prev = first;
        }
        
        return dummy.next;
    }
    
    /**
     * 递归解法
     */
    public ListNode swapPairsRecursive(ListNode head) {
        // 递归终止条件：没有节点或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }
        
        // 获取当前要交换的两个节点
        ListNode first = head;
        ListNode second = head.next;
        
        // 递归处理剩余节点
        ListNode rest = swapPairsRecursive(second.next);
        
        // 执行交换
        second.next = first;
        first.next = rest;
        
        return second; // 新的头节点
    }
    
    /**
     * 另一种迭代写法（更易理解）
     */
    public ListNode swapPairsIterative2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        
        while (current.next != null && current.next.next != null) {
            ListNode node1 = current.next;
            ListNode node2 = current.next.next;
            
            // 交换节点
            node1.next = node2.next;
            node2.next = node1;
            current.next = node2;
            
            // 移动到下一对
            current = node1;
        }
        
        return dummy.next;
    }
    
    // 辅助方法：打印链表
    private void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    // 辅助方法：创建测试链表
    private ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        
        return dummy.next;
    }
    
    // 测试方法
    public static void main(String[] args) {
        SwapNodesInPairs solution = new SwapNodesInPairs();
        
        // 测试用例
        int[][] testCases = {
            {1, 2, 3, 4},     // 预期: 2->1->4->3
            {1},               // 预期: 1
            {},                // 预期: null
            {1, 2, 3},         // 预期: 2->1->3
            {1, 2, 3, 4, 5}    // 预期: 2->1->4->3->5
        };
        
        System.out.println("迭代解法:");
        for (int[] testCase : testCases) {
            ListNode head = solution.createList(testCase);
            System.out.print("输入: ");
            solution.printList(head);
            
            ListNode result = solution.swapPairs(head);
            System.out.print("输出: ");
            solution.printList(result);
            System.out.println();
        }
        
        System.out.println("递归解法:");
        for (int[] testCase : testCases) {
            ListNode head = solution.createList(testCase);
            System.out.print("输入: ");
            solution.printList(head);
            
            ListNode result = solution.swapPairsRecursive(head);
            System.out.print("输出: ");
            solution.printList(result);
            System.out.println();
        }
    }
}
```

## 关键点解析

### 迭代解法核心逻辑

1. **虚拟头节点**：
    - 简化头节点的特殊处理
    - 统一所有节点的交换逻辑

2. **指针操作顺序**：
   ```java
   // 正确的交换顺序：
   first.next = second.next;  // 步骤1：first指向second的下一个
   second.next = first;       // 步骤2：second指向first
   prev.next = second;        // 步骤3：prev指向second
   ```

3. **指针更新**：
    - 交换完成后，prev移动到first（原第一个节点）
    - 因为first现在是这一对的最后一个节点

### 执行过程示例（1->2->3->4）

```
初始状态: dummy -> 1 -> 2 -> 3 -> 4
prev = dummy, first = 1, second = 2

第一次交换:
步骤1: 1.next = 2.next = 3  (1->3)
步骤2: 2.next = 1           (2->1)
步骤3: dummy.next = 2       (dummy->2)
结果: dummy -> 2 -> 1 -> 3 -> 4

更新prev: prev = 1 (原first节点)

第二次交换:
prev = 1, first = 3, second = 4
步骤1: 3.next = 4.next = null  (3->null)
步骤2: 4.next = 3              (4->3)
步骤3: 1.next = 4              (1->4)
结果: dummy -> 2 -> 1 -> 4 -> 3

最终: 2->1->4->3
```

### 递归解法核心逻辑

1. **递归终止条件**：
    - 没有节点或只有一个节点时，直接返回

2. **递归处理**：
    - 先递归处理剩余节点
    - 再处理当前两个节点

3. **交换操作**：
    - 与迭代解法类似，但顺序更清晰

## 学习建议

1. **理解指针操作**：
    - 在纸上画出指针变化过程
    - 理解为什么需要三个指针（prev, first, second）

2. **掌握两种解法**：
    - 迭代解法：性能更好，适合理解指针操作
    - 递归解法：代码简洁，体现递归思维

3. **处理边界情况**：
    - 空链表
    - 单节点链表
    - 奇数长度链表

4. **相关题目练习**：
    - 反转链表（基础指针操作）
    - K个一组翻转链表（扩展）
    - 重排链表（复杂指针操作）

5. **复杂度分析**：
    - 时间复杂度：O(n)，每个节点访问一次
    - 空间复杂度：
        - 迭代：O(1)
        - 递归：O(n)（递归栈深度）

6. **调试技巧**：
    - 打印每个步骤的链表状态
    - 使用小例子手动验证
    - 检查指针是否为空

7. **常见错误**：
    - 指针操作顺序错误
    - 忘记更新prev指针
    - 没有处理边界情况

## 指针操作技巧

**关键技巧**：
1. **使用虚拟头节点**：避免头节点的特殊处理
2. **先保存引用再修改**：防止丢失节点引用
3. **按正确顺序操作**：避免形成环或断链

```java
// 错误的操作顺序（会形成环）：
prev.next = second;  // 先改prev
second.next = first; // 再改second
first.next = second.next; // 这里second.next已经指向first了！
```

这道题很好地训练了链表指针操作的基本功，掌握它对解决其他链表问题很有帮助！