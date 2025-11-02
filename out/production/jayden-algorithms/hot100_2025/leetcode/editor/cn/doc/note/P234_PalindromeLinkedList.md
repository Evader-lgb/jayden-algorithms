# 回文链表（Palindrome Linked List）解题详解

## 解题思路

**总思路**：使用快慢指针找到链表中间节点，反转后半部分链表，然后比较前后两部分是否相同。

**分步骤**：
1. 使用快慢指针找到链表的中间节点
2. 反转链表的后半部分
3. 比较前半部分和反转后的后半部分是否相同
4. （可选）恢复链表的原始结构

## 归类说明
- **主要归类**：链表、双指针
- **算法技巧**：快慢指针、链表反转
- **相关题型**：反转链表、链表的中间节点、判断回文

## Java代码实现

```java
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class PalindromeLinkedList {
    /**
     * O(1)空间复杂度解法
     * @param head 链表头节点
     * @return 是否为回文链表
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // 步骤1：使用快慢指针找到链表的中间节点
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 步骤2：反转后半部分链表
        ListNode secondHalf = reverseList(slow);
        
        // 步骤3：比较前后两部分
        ListNode firstHalf = head;
        ListNode secondHalfCopy = secondHalf;
        boolean isPalin = true;
        
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                isPalin = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        // 步骤4：（可选）恢复链表原始结构
        reverseList(secondHalfCopy);
        
        return isPalin;
    }
    
    /**
     * 反转链表
     * @param head 要反转的链表头节点
     * @return 反转后的链表头节点
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        return prev;
    }
    
    /**
     * 使用栈的解法（O(n)空间复杂度）
     */
    public boolean isPalindromeWithStack(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // 使用栈存储链表前半部分的值
        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        
        // 快慢指针找到中间节点，同时将前半部分压入栈中
        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 如果链表长度为奇数，跳过中间节点
        if (fast != null) {
            slow = slow.next;
        }
        
        // 比较栈中元素和后半部分链表
        while (slow != null) {
            if (stack.pop() != slow.val) {
                return false;
            }
            slow = slow.next;
        }
        
        return true;
    }
    
    /**
     * 递归解法（O(n)空间复杂度）
     */
    private ListNode frontPointer;
    
    public boolean isPalindromeRecursive(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
    
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            // 递归到链表末尾
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            // 比较当前节点和前端指针
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            // 移动前端指针
            frontPointer = frontPointer.next;
        }
        return true;
    }
    
    // 辅助方法：创建链表
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
    
    // 测试方法
    public static void main(String[] args) {
        PalindromeLinkedList solution = new PalindromeLinkedList();
        
        // 测试用例
        int[][] testCases = {
            {1, 2, 2, 1},     // 预期: true
            {1, 2},            // 预期: false
            {1, 2, 3, 2, 1},   // 预期: true
            {1},               // 预期: true
            {1, 2, 3, 4, 2, 1}, // 预期: false
            {}                 // 预期: true (空链表)
        };
        
        System.out.println("快慢指针 + 反转链表解法:");
        for (int[] testCase : testCases) {
            ListNode head = solution.createList(testCase);
            System.out.print("链表: ");
            solution.printList(head);
            
            boolean result = solution.isPalindrome(head);
            System.out.println("是否为回文: " + result);
            System.out.println();
        }
        
        System.out.println("栈解法:");
        for (int[] testCase : testCases) {
            ListNode head = solution.createList(testCase);
            boolean result = solution.isPalindromeWithStack(head);
            System.out.println("是否为回文: " + result);
        }
        
        System.out.println("\n递归解法:");
        for (int[] testCase : testCases) {
            ListNode head = solution.createList(testCase);
            boolean result = solution.isPalindromeRecursive(head);
            System.out.println("是否为回文: " + result);
        }
    }
}
```

## 关键点解析

### 快慢指针 + 反转链表解法核心逻辑

1. **找到中间节点**：
    - 快指针每次走两步，慢指针每次走一步
    - 当快指针到达末尾时，慢指针正好在中间

2. **反转后半部分**：
    - 从慢指针位置开始反转后半部分链表
    - 使用标准的链表反转算法

3. **比较前后两部分**：
    - 从头部和反转后的后半部分头部开始比较
    - 如果所有对应节点的值都相同，则是回文

4. **恢复链表**：
    - 再次反转后半部分，恢复链表原始结构

### 执行过程示例（1->2->3->2->1）

```
步骤1：找到中间节点
  初始: slow=1, fast=1
  第1轮: slow=2, fast=3
  第2轮: slow=3, fast=1 (fast.next=null)
  中间节点: 3

步骤2：反转后半部分
  后半部分: 3->2->1
  反转后: 1->2->3

步骤3：比较前后部分
  前半部分: 1->2->3
  后半部分: 1->2->3
  比较: 1=1, 2=2 → 是回文

步骤4：恢复链表
  再次反转后半部分: 3->2->1
```

### 快慢指针的细节处理

**链表长度奇偶性的处理**：
- **奇数长度**：慢指针正好在中间节点
- **偶数长度**：慢指针在中间两个节点的第一个

在反转时，无论奇偶都从慢指针开始反转，这样能确保比较时覆盖所有需要比较的节点。

## 学习建议

1. **理解快慢指针**：
    - 掌握快慢指针找中间节点的原理
    - 理解不同链表长度下的指针位置

2. **掌握链表反转**：
    - 熟练实现链表反转算法
    - 理解指针操作的顺序

3. **处理边界情况**：
    - 空链表
    - 单节点链表
    - 双节点链表

4. **相关题目练习**：
    - 反转链表（基础）
    - 链表的中间结点
    - 重排链表

5. **复杂度分析**：
    - 时间复杂度：O(n)
    - 空间复杂度：
        - 快慢指针法：O(1)
        - 栈/递归法：O(n)

6. **调试技巧**：
    - 打印每个步骤后的链表状态
    - 使用小例子手动验证
    - 检查边界条件的处理

7. **算法选择**：
    - 面试推荐快慢指针法（空间最优）
    - 理解原理推荐栈法
    - 递归法展示不同的思维方式

## 三种解法对比

| 解法 | 时间复杂度 | 空间复杂度 | 优点 | 缺点 |
|------|------------|------------|------|------|
| 快慢指针+反转 | O(n) | O(1) | 空间最优 | 修改原链表 |
| 栈 | O(n) | O(n) | 不修改原链表 | 需要额外空间 |
| 递归 | O(n) | O(n) | 代码简洁 | 栈空间开销 |

## 常见错误

1. **快慢指针边界处理**：
   ```java
   // 错误：可能空指针
   while (fast.next != null && fast.next.next != null)
   // 正确：
   while (fast != null && fast.next != null)
   ```

2. **反转链表指针丢失**：
   ```java
   // 错误：丢失next指针
   current.next = prev;
   current = current.next; // 这里current.next已经是prev了
   // 正确：
   ListNode nextTemp = current.next;
   current.next = prev;
   current = nextTemp;
   ```

3. **比较时忽略链表恢复**：
   ```java
   // 如果没有恢复链表，原链表结构被破坏
   // 应该在比较后恢复链表结构
   ```

这道题综合了链表操作中的多个重要技巧，掌握它对解决其他链表问题很有帮助！