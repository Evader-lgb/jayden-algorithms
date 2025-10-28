# 143. 重排链表

## 📝 解题思路（总分结构）

### 总思路
使用快慢指针找到链表中点，将链表分成两半，反转后半部分，然后交替合并两个链表。

### 分步骤详解
1. **找到链表中点**：使用快慢指针法找到链表中间节点
2. **分割链表**：将链表分成前后两部分
3. **反转后半部分**：将后半部分链表反转
4. **合并链表**：将前半部分和反转后的后半部分交替合并

## 🏷️ 算法归类

**主要归类**：链表操作、双指针、链表反转  
**相关知识点**：快慢指针、链表反转、链表合并  
**难度级别**：中等  
**相似题型**：反转链表、回文链表、旋转链表

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
    public void reorderList(ListNode head) {
        // 边界情况处理：空链表或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return;
        }
        
        // 步骤1：使用快慢指针找到链表中点
        // 慢指针每次走一步，快指针每次走两步
        ListNode slow = head;
        ListNode fast = head;
        
        // 快指针走到末尾时，慢指针正好在中间（或中间前一个）
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 步骤2：分割链表为前后两部分
        // 后半部分的头节点是慢指针的下一个节点
        ListNode secondHalf = slow.next;
        // 断开前后两部分的连接
        slow.next = null;
        
        // 步骤3：反转后半部分链表
        secondHalf = reverseList(secondHalf);
        
        // 步骤4：合并两个链表（前半部分和反转后的后半部分）
        mergeLists(head, secondHalf);
    }
    
    /**
     * 反转链表
     * @param head 要反转的链表头节点
     * @return 反转后的链表头节点
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;    // 前驱节点
        ListNode curr = head;    // 当前节点
        
        while (curr != null) {
            ListNode nextTemp = curr.next;  // 保存下一个节点
            curr.next = prev;               // 反转指针方向
            prev = curr;                    // 移动prev指针
            curr = nextTemp;                // 移动curr指针
        }
        
        return prev;  // 返回新的头节点
    }
    
    /**
     * 合并两个链表（交替合并）
     * @param l1 第一个链表（前半部分）
     * @param l2 第二个链表（反转后的后半部分）
     */
    private void mergeLists(ListNode l1, ListNode l2) {
        // 交替合并两个链表
        while (l1 != null && l2 != null) {
            // 保存两个链表的下一个节点
            ListNode l1Next = l1.next;
            ListNode l2Next = l2.next;
            
            // 将l2插入到l1和l1Next之间
            l1.next = l2;
            l2.next = l1Next;
            
            // 移动指针到下一组要合并的位置
            l1 = l1Next;
            l2 = l2Next;
        }
    }
}
```

## 📚 学习建议

### 1. 理解算法的四个关键步骤

**步骤1：找到链表中点（快慢指针）**
```java
// 慢指针每次走1步，快指针每次走2步
// 当快指针到末尾时，慢指针在中间
ListNode slow = head, fast = head;
while (fast.next != null && fast.next.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

**步骤2：分割链表**
```java
ListNode secondHalf = slow.next;  // 后半部分
slow.next = null;                 // 断开连接
```

**步骤3：反转后半部分**
```java
// 使用三指针法反转链表
ListNode prev = null, curr = head;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
```

**步骤4：交替合并**
```java
// 将两个链表像拉链一样交替合并
while (l1 != null && l2 != null) {
    ListNode l1Next = l1.next;
    ListNode l2Next = l2.next;
    
    l1.next = l2;
    l2.next = l1Next;
    
    l1 = l1Next;
    l2 = l2Next;
}
```

### 2. 算法过程可视化

**示例**：链表 `1 → 2 → 3 → 4 → 5`

**步骤1：找到中点**
```
初始: slow=1, fast=1
第1轮: slow=2, fast=3
第2轮: slow=3, fast=5 (fast.next=null, 停止)
中点: 节点3
```

**步骤2：分割链表**
```
前半部分: 1 → 2 → 3 → null
后半部分: 4 → 5 → null
```

**步骤3：反转后半部分**
```
4 → 5 → null 反转后: 5 → 4 → null
```

**步骤4：合并链表**
```
合并过程：
1 → 2 → 3 → null 和 5 → 4 → null

第1轮: 1 → 5 → 2 → 3 → null, 剩余: 4 → null
第2轮: 2 → 4 → 3 → null, 剩余: null

结果: 1 → 5 → 2 → 4 → 3 → null
```

### 3. 复杂度分析
- **时间复杂度**：O(n) - 每个节点被访问常数次
- **空间复杂度**：O(1) - 只使用了常数级别的额外空间

### 4. 关键技巧详解

**快慢指针的精确定位**：
```java
// 循环条件确保快指针可以安全移动两步
while (fast.next != null && fast.next.next != null)
```

**链表反转的三步法**：
```java
ListNode nextTemp = curr.next;  // 1. 保存下一个
curr.next = prev;               // 2. 反转指针
prev = curr;                    // 3. 移动prev
curr = nextTemp;                // 4. 移动curr
```

**交替合并的拉链模式**：
```java
// 像拉链一样交替连接节点
l1 → l2 → l1Next → l2Next → ...
```

### 5. 调试技巧

**添加调试信息**：
```java
public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;
    
    // 打印原始链表
    System.out.println("原始链表: " + listToString(head));
    
    // 步骤1：找中点
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    System.out.println("中点: " + slow.val);
    
    // 步骤2：分割
    ListNode secondHalf = slow.next;
    slow.next = null;
    System.out.println("前半部分: " + listToString(head));
    System.out.println("后半部分: " + listToString(secondHalf));
    
    // 步骤3：反转
    secondHalf = reverseList(secondHalf);
    System.out.println("反转后的后半部分: " + listToString(secondHalf));
    
    // 步骤4：合并
    mergeLists(head, secondHalf);
    System.out.println("最终结果: " + listToString(head));
}

private String listToString(ListNode head) {
    StringBuilder sb = new StringBuilder();
    while (head != null) {
        sb.append(head.val).append(" -> ");
        head = head.next;
    }
    sb.append("null");
    return sb.toString();
}
```

### 6. 常见错误避免

```java
// 错误：快慢指针初始位置错误
ListNode slow = head.next, fast = head.next;  // 错误！

// 错误：忘记断开前后部分的连接
// 必须执行 slow.next = null;

// 错误：反转链表时丢失指针
// 必须先保存 next = curr.next

// 错误：合并时指针移动错误
l1 = l1.next;  // 错误！应该是 l1 = l1Next;
```

## 🎯 核心思想总结

**重排链表问题的精髓**：

### 1. 问题分解
将复杂问题分解为三个子问题：
- 找到中点
- 反转后半部分
- 交替合并

### 2. 快慢指针技巧
- 快速定位链表中点
- 时间复杂度O(n)，空间复杂度O(1)

### 3. 链表操作基本功
- 链表反转
- 链表分割
- 链表合并

### 4. 原地操作
- 不创建新节点
- 只改变节点间的连接关系

## 💡 实际应用场景
- 数据结构的教学演示
- 链表操作的综合性练习
- 面试中检验链表操作能力
- 某些特定场景下的数据重组

通过这道题，你将全面掌握链表的各项基本操作，这是解决复杂链表问题的重要基础！建议多练习几个链表相关的题目来巩固这些技巧。