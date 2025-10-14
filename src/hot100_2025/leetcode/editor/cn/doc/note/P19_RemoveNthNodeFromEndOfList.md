# 19. 删除链表的倒数第 N 个结点

## 📝 解题思路（总分结构）

### 总思路
使用双指针技巧，让快指针先走 N 步，然后快慢指针同时移动，当快指针到达链表末尾时，慢指针正好指向要删除节点的前一个节点。

### 分步骤详解
1. **创建虚拟头节点**：处理删除头节点的特殊情况
2. **初始化快慢指针**：都指向虚拟头节点
3. **快指针先走 N 步**：创建快慢指针之间的距离
4. **同时移动快慢指针**：直到快指针到达链表末尾
5. **删除目标节点**：慢指针的下一个节点就是要删除的节点
6. **返回结果**：返回虚拟头节点的下一个节点

## 🏷️ 算法归类

**主要归类**：链表操作、双指针技巧  
**相关知识点**：快慢指针、虚拟头节点、链表删除  
**难度级别**：中等  
**相似题型**：链表中倒数第k个节点、环形链表、相交链表

## 💻 Java代码实现

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 步骤1：创建虚拟头节点，用于处理删除头节点的特殊情况
        // 虚拟头节点的下一个节点指向真正的头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 步骤2：初始化快慢指针，都指向虚拟头节点
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // 步骤3：快指针先向前移动n步
        // 这样快慢指针之间就保持了n个节点的距离
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        
        // 步骤4：同时移动快慢指针，直到快指针到达链表末尾
        // 当fast指向最后一个节点时，slow指向要删除节点的前一个节点
        while (fast.next != null) {
            fast = fast.next;  // 快指针每次移动一步
            slow = slow.next;  // 慢指针每次移动一步
        }
        
        // 步骤5：删除目标节点
        // slow.next就是要删除的节点，将其跳过
        slow.next = slow.next.next;
        
        // 步骤6：返回虚拟头节点的下一个节点（即新的头节点）
        return dummy.next;
    }
}
```

## 📚 学习建议

### 1. 理解双指针的核心思想

**为什么快指针要先走 N 步？**
- 创建快慢指针之间的固定距离（N个节点）
- 当快指针到达末尾时，慢指针正好在倒数第N个节点的前一个位置

**虚拟头节点的作用：**
```java
// 如果没有虚拟头节点，删除头节点时需要特殊处理
// 有虚拟头节点后，所有节点的删除操作都统一了
ListNode dummy = new ListNode(0);
dummy.next = head;
```

### 2. 关键步骤分析

**指针移动过程示例**（链表：1→2→3→4→5，n=2）：
```
初始状态：
dummy(0) → 1 → 2 → 3 → 4 → 5
slow ↑
fast ↑

快指针先走2步：
dummy(0) → 1 → 2 → 3 → 4 → 5
slow ↑         fast ↑

同时移动直到fast到达末尾：
dummy(0) → 1 → 2 → 3 → 4 → 5 → null
               slow ↑         fast ↑

删除slow的下一个节点（4）：
dummy(0) → 1 → 2 → 3 → 5
```

### 3. 复杂度分析
- **时间复杂度**：O(L) - 只需遍历链表一次，L是链表长度
- **空间复杂度**：O(1) - 只使用了常数级别的额外空间

### 4. 边界情况处理

**代码自动处理了以下边界情况：**
- 删除头节点（n = 链表长度）
- 删除尾节点（n = 1）
- 链表只有一个节点
- 链表为空

### 5. 其他解法对比

**两次遍历解法**：
```java
// 第一次遍历获取链表长度
// 第二次遍历找到要删除节点的前一个位置
// 时间复杂度O(2L) = O(L)，但需要遍历两次
```

**栈解法**：
```java
// 将所有节点入栈，然后弹出n个节点
// 栈顶元素就是要删除节点的前一个节点
// 时间复杂度O(L)，空间复杂度O(L)
```

### 6. 学习路径建议

1. **先掌握双指针解法**：这是最优解法，需要熟练掌握
2. **理解虚拟头节点**：这是处理链表问题的常用技巧
3. **练习相似题目**：
    - 剑指 Offer 22. 链表中倒数第k个节点
    - 141. 环形链表
    - 160. 相交链表
4. **手动模拟过程**：用具体例子跟踪指针移动

### 7. 常见错误避免

```java
// 错误：没有使用虚拟头节点，处理删除头节点时复杂
if (n == length) {
    return head.next;  // 需要单独处理
}

// 错误：快指针移动步数错误
for (int i = 0; i <= n; i++) {  // 应该移动n步，不是n+1步
    fast = fast.next;
}

// 错误：循环条件错误
while (fast != null) {  // 这样slow会指向要删除的节点，而不是前一个节点
    // ...
}
```

### 8. 调试技巧

**添加调试输出**：
```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode fast = dummy, slow = dummy;
    
    System.out.println("初始状态：");
    printList(dummy);
    
    for (int i = 0; i < n; i++) {
        fast = fast.next;
        System.out.println("快指针移动第" + (i+1) + "步后，fast指向: " + fast.val);
    }
    
    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
        System.out.println("同时移动：fast=" + fast.val + ", slow=" + slow.val);
    }
    
    System.out.println("删除节点: " + slow.next.val);
    slow.next = slow.next.next;
    
    return dummy.next;
}
```

### 9. 实际应用场景
- 操作系统中的进程调度
- 数据库查询优化
- 网络数据包处理

通过这道题，你将掌握链表操作中的双指针技巧和虚拟头节点技术，这是解决链表问题的核心技能！建议多练习类似题目来巩固理解。