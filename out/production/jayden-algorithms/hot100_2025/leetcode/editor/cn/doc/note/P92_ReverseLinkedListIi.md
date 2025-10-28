# 92. 反转链表 II

## 📝 解题思路（总分结构）

### 总思路
使用哑节点和多个指针，定位到要反转区间的前后位置，然后反转指定区间，最后重新连接链表。

### 分步骤详解
1. **创建哑节点**：简化头节点可能被反转的情况
2. **定位前驱节点**：找到第 left-1 个节点（反转区间的前一个节点）
3. **定位反转区间**：找到第 left 个节点（反转区间的开始）和第 right 个节点（反转区间的结束）
4. **反转区间链表**：反转从 left 到 right 的区间
5. **重新连接**：将反转后的区间重新连接到原链表中
6. **返回结果**：返回哑节点的下一个节点

## 🏷️ 算法归类

**主要归类**：链表操作、链表反转、指针操作  
**相关知识点**：区间反转、哑节点技巧、多指针操作  
**难度级别**：中等  
**相似题型**：反转链表、K个一组翻转链表、旋转链表

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 步骤1：创建哑节点，简化头节点可能被反转的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 步骤2：定位前驱节点（第left-1个节点）
        // pre指针将指向反转区间的前一个节点
        ListNode pre = dummy;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        
        // 步骤3：定位反转区间的开始节点（第left个节点）
        ListNode start = pre.next;
        
        // 步骤4：定位反转区间的结束节点（第right个节点）
        ListNode end = pre;
        for (int i = left; i <= right; i++) {
            end = end.next;
        }
        
        // 步骤5：记录反转区间后的第一个节点
        ListNode successor = end.next;
        
        // 步骤6：断开反转区间与后续节点的连接
        end.next = null;
        
        // 步骤7：反转指定区间
        ListNode reversedHead = reverseList(start);
        
        // 步骤8：重新连接链表
        pre.next = reversedHead;    // 前驱节点连接反转后的头节点
        start.next = successor;     // 原开始节点（现在是反转后的尾节点）连接后续节点
        
        // 步骤9：返回结果
        return dummy.next;
    }
    
    /**
     * 反转链表
     * @param head 要反转的链表头节点
     * @return 反转后的链表头节点
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        
        return prev;
    }
}
```

## 📚 学习建议

### 1. 理解算法的四个关键步骤

**步骤1：定位前驱节点**
```java
// pre指向反转区间的前一个节点
ListNode pre = dummy;
for (int i = 1; i < left; i++) {
    pre = pre.next;
}
```

**步骤2：定位反转区间**
```java
// start指向反转区间的第一个节点
ListNode start = pre.next;

// end指向反转区间的最后一个节点  
ListNode end = pre;
for (int i = left; i <= right; i++) {
    end = end.next;
}
```

**步骤3：断开并反转**
```java
// 断开连接，便于反转
end.next = null;
ListNode reversedHead = reverseList(start);
```

**步骤4：重新连接**
```java
// 将反转后的区间重新接入原链表
pre.next = reversedHead;
start.next = successor;
```

### 2. 算法过程可视化

**示例**：链表 `1 → 2 → 3 → 4 → 5`，`left = 2`, `right = 4`

**执行过程**：
```
初始: dummy(0) → 1 → 2 → 3 → 4 → 5

步骤1：定位前驱节点pre
  pre从dummy移动1次 → pre指向节点1

步骤2：定位反转区间
  start = pre.next = 节点2
  end从pre移动3次(2→3→4) → end指向节点4

步骤3：断开连接
  end.next = null → 链表变为：1 → 2 → 3 → 4 → null
  后续部分：5 → null

步骤4：反转区间
  反转 2 → 3 → 4 → null 得到 4 → 3 → 2 → null

步骤5：重新连接
  pre(1) → reversedHead(4) → 1 → 4 → 3 → 2
  start(2) → successor(5) → 2 → 5

最终: 1 → 4 → 3 → 2 → 5
```

### 3. 复杂度分析
- **时间复杂度**：O(n) - 最坏情况下需要遍历整个链表
- **空间复杂度**：O(1) - 只使用了常数级别的额外空间

### 4. 一次遍历的优化解法

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        
        // 定位到前驱节点
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        
        // 使用头插法一次遍历完成反转
        ListNode curr = pre.next;
        for (int i = left; i < right; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        
        return dummy.next;
    }
}
```

### 5. 学习路径建议

1. **先掌握基础解法**：理解四步法的逻辑
2. **掌握链表反转**：这是核心操作
3. **理解指针定位**：学会精确定位链表中的位置
4. **练习相似题目**：
    - 206. 反转链表
    - 25. K个一组翻转链表
    - 61. 旋转链表

### 6. 关键技巧详解

**指针定位的精确定位**：
```java
// 定位前驱节点：移动到第left-1个节点
ListNode pre = dummy;
for (int i = 1; i < left; i++) {
    pre = pre.next;
}

// 定位结束节点：从pre开始移动right-left+1次
ListNode end = pre;
for (int i = left; i <= right; i++) {
    end = end.next;
}
```

**重新连接的逻辑**：
```java
// pre → reversedHead → ... → start → successor
pre.next = reversedHead;    // 前驱连接反转后的头
start.next = successor;     // 原头（现尾）连接后续
```

### 7. 调试技巧

**添加调试信息**：
```java
public ListNode reverseBetween(ListNode head, int left, int right) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    System.out.println("初始链表: " + listToString(dummy));
    
    // 定位pre
    ListNode pre = dummy;
    for (int i = 1; i < left; i++) {
        pre = pre.next;
    }
    System.out.println("前驱节点pre: " + pre.val);
    
    ListNode start = pre.next;
    System.out.println("反转区间开始start: " + start.val);
    
    ListNode end = pre;
    for (int i = left; i <= right; i++) {
        end = end.next;
    }
    System.out.println("反转区间结束end: " + end.val);
    
    ListNode successor = end.next;
    System.out.println("后续节点successor: " + (successor == null ? "null" : successor.val));
    
    // 断开连接
    end.next = null;
    System.out.println("断开后反转区间: " + listToString(start));
    
    // 反转
    ListNode reversedHead = reverseList(start);
    System.out.println("反转后区间: " + listToString(reversedHead));
    
    // 重新连接
    pre.next = reversedHead;
    start.next = successor;
    System.out.println("最终结果: " + listToString(dummy));
    
    return dummy.next;
}
```

### 8. 常见错误避免

```java
// 错误：索引计算错误
for (int i = 0; i < left; i++)  // 应该是 i < left-1

// 错误：忘记断开连接
// 必须先断开 end.next = null 再反转

// 错误：重新连接顺序错误
start.next = successor;  // 必须在 pre.next = reversedHead 之后

// 错误：边界情况处理
// 必须处理 left == right 的情况（不需要反转）
```

### 9. 边界情况处理

**特殊测试用例**：
```java
// 反转整个链表
left=1, right=链表长度

// 只反转一个节点
left=2, right=2

// 反转头节点
left=1, right=3

// 反转尾节点
left=链表长度-1, right=链表长度

// 单节点链表
[1], left=1, right=1
```

## 🎯 核心思想总结

**反转链表II的精髓**：

### 1. 问题分解
将复杂问题分解为：
- 定位区间边界
- 反转区间链表
- 重新连接

### 2. 哑节点技巧
- 统一处理头节点可能被反转的情况
- 简化边界条件处理

### 3. 精确定位
- 使用循环精确定位前后边界
- 确保指针指向正确的位置

### 4. 断开-反转-连接
- 先断开区间与后续的连接
- 反转区间链表
- 重新连接到原链表

## 💡 实际应用场景
- 文本编辑器的选择反转
- 数据重排操作
- 游戏中的顺序调整
- 算法教学中的经典案例

**重要提示**：这道题是链表操作的综合练习，掌握后可以解决许多复杂的链表重排问题。建议多练习几个变种题目来加深理解！

通过这道题，你将全面掌握链表的区间操作技巧，这是解决复杂链表问题的重要基础！