# 103. 二叉树的锯齿形层序遍历

## 📝 解题思路（总分结构）

### 总思路
使用层次遍历（BFS）结合双端队列或列表反转，在偶数层（从0开始计数）进行反转操作，实现锯齿形遍历。

### 分步骤详解
1. **边界情况处理**：如果根节点为空，直接返回空列表
2. **初始化队列**：创建队列用于BFS遍历，初始加入根节点
3. **层次遍历**：当队列不为空时，循环处理每一层
4. **处理当前层**：记录当前层节点数量，遍历这些节点
5. **锯齿形控制**：根据层数的奇偶性决定节点值的添加顺序
6. **添加子节点**：将当前节点的左右子节点加入队列
7. **保存结果**：将当前层的结果加入最终结果列表

## 🏷️ 算法归类

**主要归类**：二叉树、广度优先搜索、层次遍历  
**相关知识点**：锯齿形遍历、双端队列、层次遍历变种  
**难度级别**：中等  
**相似题型**：二叉树的层序遍历、二叉树的层序遍历 II

## 💻 Java代码实现

### 解法一：使用双端队列（推荐）

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 创建结果列表，用于存储锯齿形遍历的结果
        List<List<Integer>> result = new ArrayList<>();
        
        // 边界情况处理：如果根节点为空，直接返回空结果
        if (root == null) {
            return result;
        }
        
        // 创建队列，用于广度优先搜索（BFS）
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列
        queue.offer(root);
        
        // 层数计数器，用于判断当前层是奇数层还是偶数层
        // 从第0层开始（根节点所在层）
        int level = 0;
        
        // 当队列不为空时，说明还有节点需要处理
        while (!queue.isEmpty()) {
            // 创建当前层的值列表
            List<Integer> currentLevel = new ArrayList<>();
            
            // 记录当前层的节点数量
            int levelSize = queue.size();
            
            // 遍历当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                // 从队列中取出当前节点
                TreeNode currentNode = queue.poll();
                
                // 根据层数的奇偶性决定添加顺序
                if (level % 2 == 0) {
                    // 偶数层（0, 2, 4...）：从左到右，顺序添加
                    currentLevel.add(currentNode.val);
                } else {
                    // 奇数层（1, 3, 5...）：从右到左，逆序添加（添加到列表开头）
                    currentLevel.add(0, currentNode.val);
                }
                
                // 如果当前节点有左子节点，将左子节点加入队列
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                
                // 如果当前节点有右子节点，将右子节点加入队列
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            // 将当前层的值列表加入最终结果
            result.add(currentLevel);
            // 层数加1，进入下一层
            level++;
        }
        
        // 返回锯齿形遍历的结果
        return result;
    }
}
```

### 解法二：使用两个栈（另一种思路）

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        // 使用两个栈交替处理
        Stack<TreeNode> stack1 = new Stack<>();  // 从左到右的层
        Stack<TreeNode> stack2 = new Stack<>();  // 从右到左的层
        
        stack1.push(root);
        
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            
            if (!stack1.isEmpty()) {
                // 当前层从左到右处理
                while (!stack1.isEmpty()) {
                    TreeNode node = stack1.pop();
                    currentLevel.add(node.val);
                    
                    // 下一层要从右到左，所以先左后右入栈
                    if (node.left != null) stack2.push(node.left);
                    if (node.right != null) stack2.push(node.right);
                }
            } else {
                // 当前层从右到左处理
                while (!stack2.isEmpty()) {
                    TreeNode node = stack2.pop();
                    currentLevel.add(node.val);
                    
                    // 下一层要从左到右，所以先右后左入栈
                    if (node.right != null) stack1.push(node.right);
                    if (node.left != null) stack1.push(node.left);
                }
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
}
```

## 📚 学习建议

### 1. 理解锯齿形遍历的核心思想

**遍历顺序**：
- **偶数层**（0, 2, 4...）：从左到右
- **奇数层**（1, 3, 5...）：从右到左

**示例**：
```
二叉树：
    3
   / \
  9  20
     / \
    15  7

锯齿形遍历：
第0层（偶数）：[3]           ← 从左到右
第1层（奇数）：[20, 9]       ← 从右到左  
第2层（偶数）：[15, 7]       ← 从左到右
```

### 2. 算法过程可视化

**示例二叉树**：
```
    3
   / \
  9  20
     / \
    15  7
```

**执行过程**：
```
初始状态：
队列: [3], level=0

第0层（偶数层）：
处理节点3 → currentLevel = [3]
加入子节点9,20 → 队列: [9, 20]
保存结果: result = [[3]]

第1层（奇数层）：
level=1, 处理节点9,20
节点9 → currentLevel = [20] (逆序添加)
节点20 → currentLevel = [20, 9] (继续逆序添加)
加入子节点15,7 → 队列: [15, 7]
保存结果: result = [[3], [20,9]]

第2层（偶数层）：
level=2, 处理节点15,7
节点15 → currentLevel = [15]
节点7 → currentLevel = [15, 7] (顺序添加)
保存结果: result = [[3], [20,9], [15,7]]
```

### 3. 复杂度分析
- **时间复杂度**：O(n) - 每个节点被访问一次
- **空间复杂度**：O(n) - 队列最多存储n个节点

### 4. 关键技巧详解

**层数判断与添加顺序**：
```java
if (level % 2 == 0) {
    // 偶数层：顺序添加（从左到右）
    currentLevel.add(currentNode.val);
} else {
    // 奇数层：逆序添加（从右到左）
    currentLevel.add(0, currentNode.val);
}
```

**为什么在奇数层添加到列表开头？**
```java
// 假设当前层节点按队列顺序是：A, B, C
// 我们希望结果是：C, B, A

// 处理过程：
// 处理A: currentLevel = [A] → 添加到开头: [A]
// 处理B: currentLevel = [B, A] → 添加到开头: [B, A]  
// 处理C: currentLevel = [C, B, A] → 添加到开头: [C, B, A]
// 得到逆序结果
```

### 5. 学习路径建议

1. **先掌握普通层次遍历**：理解BFS在二叉树中的应用
2. **理解锯齿形需求**：明白奇偶层不同遍历方向的意义
3. **掌握双端队列技巧**：学会在列表开头和结尾添加元素
4. **练习相似题目**：
    - 102. 二叉树的层序遍历
    - 107. 二叉树的层序遍历 II
    - 199. 二叉树的右视图

### 6. 调试技巧

**添加调试信息**：
```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int level = 0;
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        
        System.out.println("处理第 " + level + " 层，有 " + levelSize + " 个节点");
        System.out.println("当前层是 " + (level % 2 == 0 ? "偶数层（从左到右）" : "奇数层（从右到左）"));
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode currentNode = queue.poll();
            System.out.println("  处理节点: " + currentNode.val);
            
            if (level % 2 == 0) {
                currentLevel.add(currentNode.val);
                System.out.println("    顺序添加到列表: " + currentLevel);
            } else {
                currentLevel.add(0, currentNode.val);
                System.out.println("    逆序添加到列表: " + currentLevel);
            }
            
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
                System.out.println("    加入左子节点: " + currentNode.left.val);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
                System.out.println("    加入右子节点: " + currentNode.right.val);
            }
        }
        
        result.add(currentLevel);
        System.out.println("第 " + level + " 层结果: " + currentLevel);
        level++;
    }
    
    return result;
}
```

### 7. 常见错误避免

```java
// 错误：层数计数错误
// 应该在每层处理完后才level++

// 错误：添加顺序判断错误
if (level % 2 == 1)  // 应该用 level % 2 == 0 判断偶数层

// 错误：忘记记录levelSize
// 必须 int levelSize = queue.size() 在循环前记录

// 错误：在逆序层错误地添加子节点
// 添加子节点的顺序始终应该是先左后右，与当前层遍历方向无关
```

## 🎯 核心思想总结

**锯齿形层序遍历的精髓**：

### 1. 层次遍历基础
- 使用队列进行BFS遍历
- 记录每层的节点数量

### 2. 方向控制
- 根据层数的奇偶性决定遍历方向
- 偶数层：从左到右（顺序添加）
- 奇数层：从右到左（逆序添加）

### 3. 列表操作技巧
- 使用 `add(0, element)` 在列表开头插入实现逆序
- 或者使用双端队列在两端操作

### 4. 层数管理
- 从0开始计数层数
- 每处理完一层，层数加1

## 💡 实际应用场景
- 图形化显示二叉树结构
- 数据序列化的特殊格式
- 某些特定算法的输入要求
- 树形结构的可视化工具

**重要提示**：这是层次遍历的经典变种，掌握后可以解决许多类似的树形遍历问题。建议先彻底理解普通层次遍历，再学习这个变种。

通过这道题，你将掌握BFS在复杂遍历需求中的应用，这是解决许多树形结构问题的重要基础！