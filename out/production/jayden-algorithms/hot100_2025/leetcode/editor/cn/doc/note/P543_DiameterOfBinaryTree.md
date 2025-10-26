# 543. 二叉树的直径

## 📝 解题思路（总分结构）

### 总思路
使用深度优先搜索（DFS）遍历二叉树，在计算每个节点深度的同时，计算经过该节点的最长路径（左子树深度 + 右子树深度），并更新全局最大直径。

### 分步骤详解
1. **定义全局变量**：记录当前找到的最大直径
2. **深度优先搜索**：递归计算每个节点的深度
3. **更新直径**：对于每个节点，计算经过该节点的路径长度（左深度 + 右深度）
4. **返回深度**：返回当前节点的深度（左右子树深度的最大值 + 1）
5. **最终结果**：返回全局最大直径

## 🏷️ 算法归类

**主要归类**：二叉树、深度优先搜索、递归  
**相关知识点**：树的高度、路径计算、后序遍历  
**难度级别**：简单  
**相似题型**：二叉树的最大深度、平衡二叉树、最长同值路径

## 💻 Java代码实现

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
    // 定义全局变量，记录最大直径
    private int maxDiameter = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        // 步骤1：从根节点开始深度优先搜索
        maxDepth(root);
        // 步骤2：返回找到的最大直径
        return maxDiameter;
    }
    
    /**
     * 计算二叉树节点的深度，同时更新最大直径
     * @param node 当前节点
     * @return 当前节点的深度
     */
    private int maxDepth(TreeNode node) {
        // 递归终止条件：空节点的深度为0
        if (node == null) {
            return 0;
        }
        
        // 步骤3：递归计算左子树的深度
        int leftDepth = maxDepth(node.left);
        // 步骤4：递归计算右子树的深度
        int rightDepth = maxDepth(node.right);
        
        // 步骤5：更新最大直径
        // 经过当前节点的路径长度 = 左子树深度 + 右子树深度
        // 直径是路径上的边数，所以直接相加即可
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        
        // 步骤6：返回当前节点的深度
        // 节点的深度 = 左右子树深度的最大值 + 1（当前节点自身）
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

## 📚 学习建议

### 1. 理解直径的定义

**直径**：二叉树中任意两个节点之间最长路径的**边数**

**关键点**：
- 直径不一定经过根节点
- 直径是路径上的**边数**，不是节点数
- 最长路径可能完全在左子树、右子树，或者跨越根节点

### 2. 算法核心思想

**为什么要在计算深度时更新直径？**
```java
// 对于每个节点，经过它的最长路径 = 左子树深度 + 右子树深度
maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
```

**深度计算**：
```java
// 节点深度 = max(左子树深度, 右子树深度) + 1
return Math.max(leftDepth, rightDepth) + 1;
```

### 3. 算法过程可视化

**示例二叉树**：
```
    1
   / \
  2   3
 / \
4   5
```

**计算过程**：
```
节点4: 深度=0, 直径=0
节点5: 深度=0, 直径=0
节点2: 左深度=1, 右深度=1, 直径=2, 深度=2
节点3: 深度=0, 直径=0
节点1: 左深度=2, 右深度=1, 直径=3, 深度=3

最大直径 = 3 (路径4->2->1->3 或 5->2->1->3)
```

### 4. 复杂度分析
- **时间复杂度**：O(n) - 每个节点访问一次
- **空间复杂度**：O(h) - 递归栈的深度，h是树的高度

### 5. 与其他解法对比

**不使用全局变量的解法**：
```java
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] result = new int[1];  // 使用数组引用传递结果
        maxDepth(root, result);
        return result[0];
    }
    
    private int maxDepth(TreeNode node, int[] result) {
        if (node == null) return 0;
        
        int leftDepth = maxDepth(node.left, result);
        int rightDepth = maxDepth(node.right, result);
        
        result[0] = Math.max(result[0], leftDepth + rightDepth);
        
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

### 6. 学习路径建议

1. **先理解深度计算**：掌握二叉树深度的递归计算
2. **理解直径概念**：明白直径是路径上的边数
3. **掌握后序遍历**：这是典型的后序遍历应用（先处理左右子树，再处理当前节点）
4. **练习相似题目**：
    - 104. 二叉树的最大深度
    - 110. 平衡二叉树
    - 687. 最长同值路径

### 7. 关键技巧详解

**后序遍历的顺序**：
```java
// 1. 处理左子树
int leftDepth = maxDepth(node.left);

// 2. 处理右子树
int rightDepth = maxDepth(node.right);

// 3. 处理当前节点（更新直径，返回深度）
maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
return Math.max(leftDepth, rightDepth) + 1;
```

**为什么直径是 leftDepth + rightDepth？**
- `leftDepth`：左子树的最深路径边数
- `rightDepth`：右子树的最深路径边数
- 经过当前节点的最长路径 = 从左子树最深处到右子树最深处的路径
- 路径边数 = `leftDepth + rightDepth`

### 8. 调试技巧

**添加调试信息**：
```java
private int maxDepth(TreeNode node) {
    if (node == null) {
        return 0;
    }
    
    int leftDepth = maxDepth(node.left);
    int rightDepth = maxDepth(node.right);
    
    int currentDiameter = leftDepth + rightDepth;
    System.out.println("节点 " + node.val + ": 左深度=" + leftDepth + 
                      ", 右深度=" + rightDepth + ", 当前直径=" + currentDiameter);
    
    maxDiameter = Math.max(maxDiameter, currentDiameter);
    
    int depth = Math.max(leftDepth, rightDepth) + 1;
    System.out.println("节点 " + node.val + " 的深度: " + depth);
    
    return depth;
}
```

### 9. 常见错误避免

```java
// 错误：直径计算错误（计算节点数而不是边数）
maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth + 1);  // 错误！

// 错误：忘记更新全局变量
// 必须每次计算当前节点直径时更新maxDiameter

// 错误：深度计算错误
return leftDepth + rightDepth + 1;  // 错误！应该是max(left,right)+1

// 错误：使用前序遍历
// 必须在得到左右子树深度后才能计算直径，所以要用后序遍历
```

### 10. 实际应用场景
- 网络拓扑中的最长路径
- 文件系统目录结构分析
- 社交网络中的最远关系距离
- 生物信息学中的进化树分析

## 🎯 核心思想总结

**二叉树直径问题的精髓**：

### 1. 问题转化
将找直径问题转化为**对于每个节点，计算经过该节点的最长路径**

### 2. 后序遍历的应用
- 必须先知道左右子树的深度，才能计算当前节点的贡献
- 典型的"自底向上"计算方式

### 3. 深度与直径的关系
- **深度**：从节点到最远叶子的边数
- **直径**：经过节点的最长路径边数 = 左深度 + 右深度

### 4. 全局状态维护
通过全局变量记录遍历过程中发现的最大直径

## 💡 更直观的理解

**把树想象成组织结构**：
- 每个节点的**深度** = 该节点到最底层下属的距离
- 经过节点的**直径** = 通过该节点连接的最远两个下属之间的距离
- **最大直径** = 整个组织中最远的两个成员之间的距离

**计算过程就像**：
1. 问左子树："你最深的路径有多深？"
2. 问右子树："你最深的路径有多深？"
3. 计算："通过我连接左右两边，能形成多长的路径？"
4. 报告："我的深度是左右两边较深的那边再加1"

通过这道题，你将掌握二叉树后序遍历的典型应用，这是解决许多树形结构问题的基础模式！建议多练习几个变种题目来加深理解。




# 深入理解二叉树直径算法的两个关键行

你的疑问非常好！这两行代码确实是整个算法的核心，让我用最详细的方式来解释。

## 🔍 第一行：`maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);`

### 作用：**记录全局最大直径**

**这行代码的作用是**：
- 对于**当前节点**，计算经过它的最长路径
- 更新全局最大值，确保记录整个树中的最大直径

### 为什么需要这个？

**关键理解**：直径可能出现在树的任何位置，不一定经过根节点！

**例子**：
```
    1
   / \
  2   3
 / \
4   5
   / \
  6   7
```

在这个树中：
- 经过节点2的直径：4→2→5→6→7（长度为4）
- 经过节点1的直径：4→2→1→3（长度为3）
- **最大直径是4**，出现在节点2处，而不是根节点1

所以我们需要：
```java
// 对于每个节点，都检查经过它的路径长度
maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
```

## 🔍 第二行：`return Math.max(leftDepth, rightDepth) + 1;`

### 作用：**返回当前节点的深度**

**这行代码的作用是**：
- 告诉父节点："我的深度是多少"
- 深度 = 从当前节点到最远叶子的边数

### 为什么不是返回直径？

**关键区别**：
- **直径**：经过当前节点的最长路径（左深度 + 右深度）
- **深度**：当前节点到最远叶子的距离

**在递归中，我们需要的是深度，不是直径！**

## 🎯 两个概念的详细对比

### 深度（Depth） vs 直径（Diameter）

| 概念 | 计算方式 | 用途 |
|------|----------|------|
| **深度** | `max(leftDepth, rightDepth) + 1` | 告诉父节点我的位置信息 |
| **直径** | `leftDepth + rightDepth` | 记录全局最大值 |

### 可视化理解

```
节点A:
   深度 = 到最远叶子的距离
   直径 = 经过A的最长路径

例子：
     A
    / \
   B   C
  / \   \
 D   E   F
    / \
   G   H

对于节点B：
深度 = max(到G的距离, 到H的距离) + 1 = 2 + 1 = 3
直径 = 左深度(到D=1) + 右深度(到G/H=2) = 3

对于节点A：
深度 = max(B的深度=3, C的深度=2) + 1 = 3 + 1 = 4  
直径 = B的深度(3) + C的深度(2) = 5
```

## 💡 递归过程的详细模拟

### 例子：简单的二叉树
```
    1
   / \
  2   3
```

**递归调用过程**：

1. **节点2**：
   ```java
   leftDepth = maxDepth(4) = 0  // 左子节点为空
   rightDepth = maxDepth(5) = 0 // 右子节点为空
   maxDiameter = max(0, 0+0) = 0  // 更新直径
   return max(0,0)+1 = 1         // 返回深度=1
   ```

2. **节点3**：
   ```java
   leftDepth = 0, rightDepth = 0
   maxDiameter = max(0, 0+0) = 0
   return 1
   ```

3. **节点1（根节点）**：
   ```java
   leftDepth = 1  // 节点2的深度
   rightDepth = 1 // 节点3的深度
   maxDiameter = max(0, 1+1) = 2  // 找到直径=2
   return max(1,1)+1 = 2          // 返回深度=2
   ```

## 🎯 为什么返回值是深度而不是直径？

### 从父节点的视角

当父节点调用 `maxDepth(child)` 时，它想知道：
- "我的子节点有多深？"
- 这样我才能计算："经过我的路径长度 = 左子节点深度 + 右子节点深度"

**父节点不关心子节点的直径，只关心子节点的深度！**

### 类比理解

**把树想象成组织架构**：
- 你问下属："你手下有多少层？"（深度）
- 你用这个信息计算："通过我连接的两个团队，最大距离是多少？"（直径）
- 你向上级报告："我负责的团队有多少层？"（深度）

## 🔄 完整的思维过程

### 对于每个节点，我们做两件事：

1. **作为路径的转折点**：
   ```java
   // 计算：如果最长路径经过我，长度是多少？
   int pathThroughMe = leftDepth + rightDepth;
   // 更新全局记录
   maxDiameter = Math.max(maxDiameter, pathThroughMe);
   ```

2. **作为父节点的子节点**：
   ```java
   // 告诉父节点：从我这里往下有多深？
   return Math.max(leftDepth, rightDepth) + 1;
   ```

### 这两个角色分开处理：
- **直径计算**：只用于更新全局最大值
- **深度返回**：只用于父节点的计算

## 🏆 总结

**你的疑问解答**：

1. **`maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);`**
    - 作用：**记录候选直径**
    - 原因：直径可能出现在任何节点，我们需要检查每个节点

2. **`return Math.max(leftDepth, rightDepth) + 1;`**
    - 作用：**返回深度给父节点**
    - 原因：父节点需要子节点的深度信息来计算它自己的直径

**关键洞察**：
- 每个节点在递归中扮演**两个角色**：
    - 对于自己：我是路径的**中心点**（计算直径）
    - 对于父节点：我是**子节点**（返回深度）
- 这两个角色需要不同的信息
- 算法巧妙地在一次遍历中同时完成这两件事

现在你应该明白了：**我们返回深度是为了递归计算，记录直径是为了最终答案**，这两个操作目的不同，但协同工作来解决整个问题！