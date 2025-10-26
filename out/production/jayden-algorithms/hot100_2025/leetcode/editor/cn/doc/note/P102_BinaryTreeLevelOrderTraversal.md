# 102. 二叉树的层序遍历

## 📝 解题思路（总分结构）

### 总思路
使用广度优先搜索（BFS）结合队列，按层级顺序遍历二叉树，将每一层的节点值收集到单独的列表中。

### 分步骤详解
1. **边界情况处理**：如果根节点为空，直接返回空列表
2. **初始化队列**：创建队列用于BFS遍历，初始加入根节点
3. **层级遍历**：当队列不为空时，循环处理每一层
4. **处理当前层**：记录当前层节点数量，遍历这些节点
5. **收集节点值**：将当前层所有节点的值收集到列表
6. **添加子节点**：将当前节点的左右子节点加入队列
7. **保存结果**：将当前层的结果加入最终结果列表

## 🏷️ 算法归类

**主要归类**：二叉树、广度优先搜索、队列  
**相关知识点**：层级遍历、BFS、队列操作  
**难度级别**：中等  
**相似题型**：二叉树的锯齿形层序遍历、二叉树的最大深度、二叉树的最小深度

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 创建结果列表，用于存储每一层的节点值
        List<List<Integer>> result = new ArrayList<>();
        
        // 边界情况处理：如果根节点为空，直接返回空结果
        if (root == null) {
            return result;
        }
        
        // 创建队列，用于广度优先搜索（BFS）
        // 队列中存储待处理的树节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列，作为第一层
        queue.offer(root);
        
        // 当队列不为空时，说明还有节点需要处理
        while (!queue.isEmpty()) {
            // 创建当前层的值列表，用于存储当前层所有节点的值
            List<Integer> currentLevel = new ArrayList<>();
            
            // 记录当前层的节点数量，这一步很关键！
            // 因为我们在处理当前层时，会不断将下一层的节点加入队列
            // 所以需要先记录当前层的节点数量，确保只处理当前层的节点
            int levelSize = queue.size();
            
            // 遍历当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                // 从队列中取出当前节点（先进先出）
                TreeNode currentNode = queue.poll();
                
                // 将当前节点的值加入当前层的值列表
                currentLevel.add(currentNode.val);
                
                // 如果当前节点有左子节点，将左子节点加入队列（下一层）
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                
                // 如果当前节点有右子节点，将右子节点加入队列（下一层）
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            // 将当前层的值列表加入最终结果
            result.add(currentLevel);
        }
        
        // 返回按层级排序的结果
        return result;
    }
}
```

## 📚 学习建议

### 1. 理解BFS的核心思想

**队列的作用**：
- 按照"先进先出"的原则处理节点
- 确保同一层的节点在一起处理
- 下一层的节点在队列中等待

**关键技巧**：`levelSize` 变量的重要性
```java
// 在处理当前层之前，先记录队列大小
int levelSize = queue.size();
// 这样即使我们在循环中加入了下一层的节点，也只会处理当前层的节点
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
队列: [3]
结果: []

第一层循环：
levelSize = 1
处理节点3 → currentLevel = [3]
加入子节点9,20 → 队列: [9, 20]
保存结果: result = [[3]]

第二层循环：
levelSize = 2
处理节点9 → currentLevel = [9]
节点9无子节点 → 队列: [20]
处理节点20 → currentLevel = [9, 20]  
加入子节点15,7 → 队列: [15, 7]
保存结果: result = [[3], [9,20]]

第三层循环：
levelSize = 2
处理节点15 → currentLevel = [15]
节点15无子节点 → 队列: [7]
处理节点7 → currentLevel = [15, 7]
节点7无子节点 → 队列: []
保存结果: result = [[3], [9,20], [15,7]]
```

### 3. 复杂度分析
- **时间复杂度**：O(n) - 每个节点被访问一次
- **空间复杂度**：O(n) - 队列最多存储n个节点

### 4. 与其他遍历方式对比

**深度优先搜索（DFS）解法**：
```java
// 使用DFS的递归解法
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) return;
        
        // 如果当前层级还没有创建列表，创建一个
        if (result.size() == level) {
            result.add(new ArrayList<>());
        }
        
        // 将当前节点值加入对应层级的列表
        result.get(level).add(node.val);
        
        // 递归处理左右子树，层级+1
        dfs(node.left, level + 1, result);
        dfs(node.right, level + 1, result);
    }
}
```

### 5. 学习路径建议

1. **先掌握BFS解法**：这是标准解法，思路清晰
2. **理解队列操作**：掌握offer、poll等队列方法
3. **练习相似题目**：
    - 103. 二叉树的锯齿形层序遍历
    - 107. 二叉树的层序遍历 II
    - 116. 填充每个节点的下一个右侧节点指针
4. **手动模拟过程**：在纸上画出队列的变化过程

### 6. 关键技巧详解

**为什么需要 `levelSize`？**
```java
// 如果没有levelSize，代码会变成这样：
while (!queue.isEmpty()) {
    TreeNode currentNode = queue.poll();
    currentLevel.add(currentNode.val);
    
    if (currentNode.left != null) queue.offer(currentNode.left);
    if (currentNode.right != null) queue.offer(currentNode.right);
    
    // 问题：无法区分层级边界！
}
```

**有 `levelSize` 的正确方式**：
```java
while (!queue.isEmpty()) {
    int levelSize = queue.size();  // 关键！
    List<Integer> currentLevel = new ArrayList<>();
    
    for (int i = 0; i < levelSize; i++) {
        // 只处理当前层的levelSize个节点
        TreeNode currentNode = queue.poll();
        currentLevel.add(currentNode.val);
        
        // 加入的节点属于下一层，不会在当前循环中处理
        if (currentNode.left != null) queue.offer(currentNode.left);
        if (currentNode.right != null) queue.offer(currentNode.right);
    }
    
    result.add(currentLevel);
}
```

### 7. 调试技巧

**添加调试信息**：
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int level = 0;
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        
        System.out.println("处理第 " + level + " 层，有 " + levelSize + " 个节点");
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode currentNode = queue.poll();
            currentLevel.add(currentNode.val);
            System.out.println("  处理节点: " + currentNode.val);
            
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

### 8. 常见错误避免

```java
// 错误：忘记检查空节点
queue.offer(currentNode.left);  // 可能加入null
// 正确：先检查是否为null
if (currentNode.left != null) queue.offer(currentNode.left);

// 错误：使用错误的队列方法
queue.add(root);  // 可能抛出异常
queue.offer(root); // 更安全，返回boolean

// 错误：没有使用levelSize区分层级
// 会导致所有节点混在一个列表中
```

### 9. 实际应用场景
- 文件系统的目录遍历
- 网络爬虫的网页抓取
- 社交网络的好友推荐
- 游戏中的路径寻找

## 🎯 核心思想总结

层序遍历的精髓在于：

1. **队列管理**：使用队列维护待处理的节点
2. **层级分离**：通过 `levelSize` 确保同一层的节点一起处理
3. **广度优先**：按层级顺序遍历，先访问离根节点近的节点
4. **结果收集**：为每一层创建独立的结果列表

**重要提示**：层序遍历是二叉树遍历的基础，很多二叉树问题都是基于这个思路的变种。掌握这个方法对解决二叉树相关问题至关重要！

通过这道题，你将掌握BFS在二叉树中的应用，这是解决许多树形结构问题的基础技能。