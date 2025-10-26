# 23. 合并K个升序链表

## 📝 解题思路（总分结构）

### 总思路
使用最小堆（优先队列）来维护当前所有链表的最小节点，每次取出最小的节点加入结果链表，然后将该节点的下一个节点加入堆中。

### 分步骤详解
1. **边界情况处理**：如果链表数组为空或长度为0，直接返回null
2. **创建最小堆**：使用优先队列存储链表节点，按节点值排序
3. **初始化堆**：将所有链表的头节点加入堆中
4. **合并过程**：循环从堆中取出最小节点，连接到结果链表
5. **维护堆**：将取出节点的下一个节点加入堆中
6. **返回结果**：返回合并后链表的头节点

## 🏷️ 算法归类

**主要归类**：链表操作、堆（优先队列）、分治算法  
**相关知识点**：最小堆、多路归并、链表合并  
**难度级别**：困难  
**相似题型**：合并两个有序链表、数据流的中位数、滑动窗口最大值

## 💻 Java代码实现

### 解法一：最小堆（优先队列）

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
    public ListNode mergeKLists(ListNode[] lists) {
        // 边界情况处理：如果链表数组为空或长度为0，直接返回null
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // 创建最小堆（优先队列），按节点的值进行排序
        // 使用lambda表达式定义比较器： (a, b) -> a.val - b.val
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // 步骤1：将所有链表的头节点加入最小堆
        for (ListNode list : lists) {
            // 注意：需要检查链表是否为空
            if (list != null) {
                minHeap.offer(list);
            }
        }
        
        // 创建哑节点（dummy node），简化链表操作
        // dummy节点不存储实际数据，它的next指向合并后链表的头节点
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;  // current指针用于构建结果链表
        
        // 步骤2：循环从堆中取出最小节点，构建结果链表
        while (!minHeap.isEmpty()) {
            // 从最小堆中取出当前最小的节点
            ListNode minNode = minHeap.poll();
            
            // 将取出的最小节点连接到结果链表
            current.next = minNode;
            current = current.next;  // 移动current指针
            
            // 步骤3：如果取出的节点还有下一个节点，将下一个节点加入堆中
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }
        
        // 返回合并后链表的头节点（dummy的下一个节点）
        return dummy.next;
    }
}
```

### 解法二：分治法（递归合并）

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // 边界情况处理
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // 使用分治法合并链表数组
        return mergeLists(lists, 0, lists.length - 1);
    }
    
    /**
     * 分治法合并链表数组
     * @param lists 链表数组
     * @param left 左边界
     * @param right 右边界
     * @return 合并后的链表头节点
     */
    private ListNode mergeLists(ListNode[] lists, int left, int right) {
        // 递归终止条件：当左右边界相等时，返回单个链表
        if (left == right) {
            return lists[left];
        }
        
        // 计算中间位置
        int mid = left + (right - left) / 2;
        
        // 递归合并左半部分链表
        ListNode leftList = mergeLists(lists, left, mid);
        // 递归合并右半部分链表
        ListNode rightList = mergeLists(lists, mid + 1, right);
        
        // 合并两个有序链表
        return mergeTwoLists(leftList, rightList);
    }
    
    /**
     * 合并两个有序链表（递归版本）
     * @param l1 第一个链表
     * @param l2 第二个链表
     * @return 合并后的链表头节点
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 递归终止条件：如果某个链表为空，返回另一个链表
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        // 比较两个链表的头节点值
        if (l1.val < l2.val) {
            // l1的头节点较小，将其作为合并后链表的头节点
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            // l2的头节点较小或相等，将其作为合并后链表的头节点
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```

### 解法三：分治法（迭代合并）

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // 使用迭代的方式进行分治合并
        int interval = 1;
        int n = lists.length;
        
        while (interval < n) {
            for (int i = 0; i < n - interval; i += interval * 2) {
                // 合并相邻的两个链表
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        
        return lists[0];
    }
    
    /**
     * 合并两个有序链表（迭代版本）
     * @param l1 第一个链表
     * @param l2 第二个链表
     * @return 合并后的链表头节点
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 创建哑节点简化操作
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // 同时遍历两个链表
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        // 将剩余部分连接到结果链表
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }
        
        return dummy.next;
    }
}
```

## 📚 学习建议

### 1. 理解最小堆解法的核心思想

**最小堆的作用**：
```java
// 最小堆始终保持当前所有链表中最小的节点在堆顶
PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
```

**算法过程可视化**：
```
输入链表：
[1->4->5, 1->3->4, 2->6]

初始堆：包含所有链表头节点 [1, 1, 2]

第1步：取出最小节点1，加入结果，将其下一个节点4加入堆 → 堆：[1, 2, 4]
第2步：取出最小节点1，加入结果，将其下一个节点3加入堆 → 堆：[2, 3, 4]  
第3步：取出最小节点2，加入结果，将其下一个节点6加入堆 → 堆：[3, 4, 6]
第4步：取出最小节点3，加入结果，将其下一个节点4加入堆 → 堆：[4, 4, 6]
... 以此类推
```

### 2. 复杂度分析

**最小堆解法**：
- **时间复杂度**：O(N log K) - N是总节点数，K是链表个数
- **空间复杂度**：O(K) - 堆的大小最多为K

**分治解法**：
- **时间复杂度**：O(N log K) - 每层合并需要O(N)时间，共log K层
- **空间复杂度**：O(log K) - 递归栈的深度

### 3. 不同解法的适用场景

| 解法 | 时间复杂度 | 空间复杂度 | 优点 | 缺点 |
|------|------------|------------|------|------|
| 最小堆 | O(N log K) | O(K) | 代码简洁，易于理解 | 需要额外空间 |
| 分治递归 | O(N log K) | O(log K) | 空间效率高 | 递归可能栈溢出 |
| 分治迭代 | O(N log K) | O(1) | 空间最优 | 代码稍复杂 |

### 4. 学习路径建议

1. **先掌握最小堆解法**：思路直观，代码简洁
2. **理解分治思想**：掌握递归和迭代两种实现
3. **练习相似题目**：
    - 21. 合并两个有序链表
    - 264. 丑数 II
    - 373. 查找和最小的K对数字
4. **掌握链表操作**：哑节点的使用，指针移动

### 5. 关键技巧详解

**哑节点（Dummy Node）的使用**：
```java
// 创建哑节点简化链表操作
ListNode dummy = new ListNode(0);
ListNode current = dummy;

// 构建链表时不需要特殊处理头节点
current.next = newNode;
current = current.next;

// 最终返回dummy.next就是真正的头节点
return dummy.next;
```

**优先队列的比较器**：
```java
// 三种写法等价：
// 1. lambda表达式（最简洁）
PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

// 2. 方法引用
PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

// 3. 匿名内部类
PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
    public int compare(ListNode a, ListNode b) {
        return a.val - b.val;
    }
});
```

### 6. 调试技巧

**添加调试信息**：
```java
public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
    
    System.out.println("初始链表数组:");
    for (int i = 0; i < lists.length; i++) {
        if (lists[i] != null) {
            minHeap.offer(lists[i]);
            System.out.println("链表" + i + ": " + listToString(lists[i]));
        }
    }
    
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    int step = 1;
    
    while (!minHeap.isEmpty()) {
        ListNode minNode = minHeap.poll();
        System.out.println("步骤" + step + ": 取出节点 " + minNode.val);
        
        current.next = minNode;
        current = current.next;
        
        if (minNode.next != null) {
            minHeap.offer(minNode.next);
            System.out.println("  加入下一个节点 " + minNode.next.val + " 到堆中");
        }
        
        step++;
    }
    
    return dummy.next;
}

private String listToString(ListNode head) {
    StringBuilder sb = new StringBuilder();
    while (head != null) {
        sb.append(head.val).append("->");
        head = head.next;
    }
    sb.append("null");
    return sb.toString();
}
```

### 7. 常见错误避免

```java
// 错误：忘记检查空链表
for (ListNode list : lists) {
    minHeap.offer(list);  // 如果list为null会抛出异常
}
// 正确：先检查是否为null
if (list != null) minHeap.offer(list);

// 错误：堆的比较器写反
PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> b.val - a.val);
// 这样会变成最大堆，应该用 a.val - b.val

// 错误：没有使用哑节点，处理头节点复杂
ListNode head = null;
ListNode current = null;
// 需要额外判断head是否为null
```

### 8. 实际应用场景
- 多路归并排序
- 外部排序（大数据处理）
- 多服务器日志合并
- 实时数据流处理

## 🎯 核心思想总结

**最小堆解法的精髓**：
1. **维护候选集**：堆中始终保持每个链表当前的最小节点
2. **贪心选择**：每次选择最小的节点加入结果
3. **动态更新**：将取出节点的后继加入堆中，保持候选集的完整性
4. **哑节点技巧**：简化链表头节点的处理

**分治解法的优势**：
1. **递归分解**：将大问题分解为小问题
2. **合并有序**：利用合并两个有序链表的已知解法
3. **空间效率**：避免使用额外数据结构

**推荐使用最小堆解法**，因为它思路清晰，代码简洁，是面试中最常考察的解法。分治解法也值得掌握，特别是在空间受限的场景。

通过这道题，你将深入掌握堆数据结构的应用和分治思想在链表问题中的运用，这是解决复杂链表问题的重要基础！