# 82. 删除排序链表中的重复元素 II

## 📝 解题思路（总分结构）

### 总思路
使用哑节点(dummy node)简化边界情况处理，遍历链表，跳过所有重复出现的元素，只保留出现一次的元素。

### 分步骤详解
1. **创建哑节点**：简化头节点可能被删除的情况
2. **初始化指针**：使用prev指针指向当前已处理部分的末尾，curr指针用于遍历
3. **遍历链表**：当curr和curr.next都存在时循环
4. **检测重复**：如果当前节点与下一个节点值相同，找到所有重复节点
5. **跳过重复**：将prev.next指向重复段后的第一个不同节点
6. **处理不重复**：如果当前节点不重复，移动prev指针
7. **移动curr**：每次循环curr都向前移动

## 🏷️ 算法归类

**主要归类**：链表操作、双指针  
**相关知识点**：哑节点技巧、链表删除、重复元素处理  
**难度级别**：中等  
**相似题型**：删除排序链表中的重复元素、移除链表元素

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
    public ListNode deleteDuplicates(ListNode head) {
        // 步骤1：创建哑节点，简化头节点可能被删除的情况
        // 哑节点的next指向原始链表的头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 步骤2：初始化两个指针
        // prev指向当前已处理部分的最后一个不重复节点
        // curr用于遍历整个链表
        ListNode prev = dummy;
        ListNode curr = head;
        
        // 步骤3：遍历链表
        while (curr != null) {
            // 步骤4：检测当前节点是否有重复
            // 如果当前节点与下一个节点值相同，说明有重复
            if (curr.next != null && curr.val == curr.next.val) {
                // 步骤5：找到所有重复的节点
                // 一直向后遍历，直到找到不同的值或链表末尾
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                // 步骤6：跳过所有重复节点
                // 将prev.next指向重复段后的第一个不同节点
                prev.next = curr.next;
            } else {
                // 步骤7：当前节点没有重复，移动prev指针
                prev = prev.next;
            }
            
            // 步骤8：移动curr指针到下一个节点
            curr = curr.next;
        }
        
        // 步骤9：返回处理后的链表头节点（哑节点的next）
        return dummy.next;
    }
}
```

## 📚 学习建议

### 1. 理解哑节点的重要性

**为什么需要哑节点？**
```java
// 如果没有哑节点，当头节点需要被删除时处理会很复杂
// 比如链表：1->1->2->3，头节点1需要被删除

// 有哑节点：
dummy(0) → 1 → 1 → 2 → 3
prev ↑   curr ↑

// 检测到重复后：
dummy(0) → 2 → 3
prev ↑   curr ↑
```

### 2. 算法过程可视化

**示例**：`1 → 2 → 3 → 3 → 4 → 4 → 5`

**执行过程**：
```
初始: dummy(0) → 1 → 2 → 3 → 3 → 4 → 4 → 5
      prev     curr

第1步: curr=1, 检查1≠2 → 不重复 → prev移动到1, curr移动到2
第2步: curr=2, 检查2≠3 → 不重复 → prev移动到2, curr移动到3  
第3步: curr=3, 检查3=3 → 重复 → 找到所有3 → prev.next指向4, curr移动到4
第4步: curr=4, 检查4=4 → 重复 → 找到所有4 → prev.next指向5, curr移动到5
第5步: curr=5, 检查5=null → 不重复 → prev移动到5, curr移动到null

结果: 1 → 2 → 5
```

### 3. 复杂度分析
- **时间复杂度**：O(n) - 每个节点最多被访问两次
- **空间复杂度**：O(1) - 只使用了常数级别的额外空间

### 4. 与其他解法对比

**递归解法**：
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        
        if (head.val == head.next.val) {
            // 跳过所有重复值
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            // 递归处理剩余部分
            return deleteDuplicates(head.next);
        } else {
            // 当前节点不重复，保留并处理后续
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}
```

### 5. 学习路径建议

1. **先掌握迭代解法**：理解双指针的移动逻辑
2. **理解哑节点技巧**：这是链表问题的常用技巧
3. **练习相似题目**：
    - 83. 删除排序链表中的重复元素（保留一个重复元素）
    - 203. 移除链表元素
    - 19. 删除链表的倒数第N个节点
4. **掌握边界情况**：空链表、全重复链表、头节点重复等情况

### 6. 关键技巧详解

**重复检测的逻辑**：
```java
// 检查当前节点是否有重复
if (curr.next != null && curr.val == curr.next.val) {
    // 找到所有重复节点
    while (curr.next != null && curr.val == curr.next.val) {
        curr = curr.next;
    }
    // 跳过重复段
    prev.next = curr.next;
}
```

**指针移动的时机**：
```java
// 只有当前节点不重复时，才移动prev指针
if (没有重复) {
    prev = prev.next;  // prev移动到当前节点
}
// curr指针每次循环都要移动
curr = curr.next;
```

### 7. 调试技巧

**添加调试信息**：
```java
public ListNode deleteDuplicates(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy, curr = head;
    
    int step = 1;
    while (curr != null) {
        System.out.println("步骤" + step + ": curr=" + curr.val);
        
        if (curr.next != null && curr.val == curr.next.val) {
            System.out.println("  检测到重复，开始跳过重复节点");
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
                System.out.println("    跳过节点: " + curr.val);
            }
            prev.next = curr.next;
            System.out.println("  prev.next指向: " + (curr.next == null ? "null" : curr.next.val));
        } else {
            System.out.println("  没有重复，移动prev到: " + curr.val);
            prev = prev.next;
        }
        
        curr = curr.next;
        step++;
    }
    
    return dummy.next;
}
```

### 8. 常见错误避免

```java
// 错误：忘记检查curr.next是否为null
if (curr.val == curr.next.val)  // 可能NullPointerException

// 错误：指针移动逻辑错误
prev = curr;  // 错误！应该在非重复时才移动prev

// 错误：没有使用哑节点
// 当头节点需要删除时无法处理

// 错误：循环条件错误
while (curr != null && curr.val == curr.next.val)  // curr.next可能为null
```

### 9. 边界情况处理

**特殊测试用例**：
```java
// 空链表
null → null

// 单个节点
[1] → [1]

// 全重复节点
[1,1,1] → []

// 头节点重复
[1,1,2,3] → [2,3]

// 尾节点重复  
[1,2,3,3] → [1,2]

// 多个重复段
[1,1,2,3,3,4,5,5] → [2,4]
```

## 🎯 核心思想总结

**删除重复元素II的精髓**：

### 1. 哑节点技巧
- 简化头节点可能被删除的情况
- 统一所有节点的删除操作

### 2. 双指针配合
- **prev指针**：指向当前已确认不重复的最后一个节点
- **curr指针**：用于遍历检测重复

### 3. 重复检测策略
- 发现重复时，找到重复段的末尾
- 一次性跳过整个重复段

### 4. 指针移动时机
- 只有确认不重复时才移动prev指针
- curr指针始终向前移动

## 💡 实际应用场景
- 数据清洗中的去重操作
- 日志文件中的重复记录删除
- 数据库查询结果的去重处理
- 消息队列中的重复消息过滤

**重要提示**：这道题是链表操作的经典题目，理解后可以解决许多类似的链表删除问题。建议多练习几个变种题目来加深理解！

通过这道题，你将掌握链表删除操作的核心技巧，特别是处理边界情况和重复元素的策略，这是解决复杂链表问题的重要基础！