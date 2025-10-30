# 求根节点到叶节点数字之和

## 解题思路

**总思路**：使用深度优先搜索（DFS）遍历所有从根节点到叶节点的路径，将路径上的数字组合成数值并累加。

**分步骤**：
1. 使用DFS遍历二叉树，从根节点开始
2. 在遍历过程中维护当前路径表示的数字
3. 当到达叶子节点时，将当前数字加入总和
4. 递归处理左右子树

## 归类说明
- **主要归类**：二叉树、深度优先搜索
- **算法技巧**：递归、路径遍历
- **相关题型**：二叉树的所有路径、路径总和、从根到叶的二进制数之和

## Java代码实现

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class SumRootToLeafNumbers {
    /**
     * DFS递归解法
     * @param root 二叉树根节点
     * @return 所有根到叶路径数字之和
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    
    /**
     * 深度优先搜索
     * @param node 当前节点
     * @param currentSum 当前路径表示的数字
     * @return 当前子树的所有路径数字之和
     */
    private int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }
        
        // 更新当前路径数字：currentSum * 10 + 当前节点值
        currentSum = currentSum * 10 + node.val;
        
        // 如果是叶子节点，返回当前路径数字
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        
        // 递归计算左右子树的和
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
    
    /**
     * BFS迭代解法
     */
    public int sumNumbersBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int totalSum = 0;
        // 使用两个队列：一个存储节点，一个存储到当前节点的路径值
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        
        nodeQueue.offer(root);
        sumQueue.offer(root.val);
        
        while (!nodeQueue.isEmpty()) {
            TreeNode currentNode = nodeQueue.poll();
            int currentSum = sumQueue.poll();
            
            // 如果是叶子节点，累加到总和
            if (currentNode.left == null && currentNode.right == null) {
                totalSum += currentSum;
            }
            
            // 处理左子节点
            if (currentNode.left != null) {
                nodeQueue.offer(currentNode.left);
                sumQueue.offer(currentSum * 10 + currentNode.left.val);
            }
            
            // 处理右子节点
            if (currentNode.right != null) {
                nodeQueue.offer(currentNode.right);
                sumQueue.offer(currentSum * 10 + currentNode.right.val);
            }
        }
        
        return totalSum;
    }
    
    /**
     * 另一种DFS写法：使用类成员变量
     */
    private int totalSum = 0;
    
    public int sumNumbersDFS2(TreeNode root) {
        totalSum = 0;
        if (root != null) {
            traverse(root, 0);
        }
        return totalSum;
    }
    
    private void traverse(TreeNode node, int currentSum) {
        if (node == null) return;
        
        currentSum = currentSum * 10 + node.val;
        
        // 到达叶子节点，累加结果
        if (node.left == null && node.right == null) {
            totalSum += currentSum;
            return;
        }
        
        traverse(node.left, currentSum);
        traverse(node.right, currentSum);
    }
    
    // 测试方法
    public static void main(String[] args) {
        SumRootToLeafNumbers solution = new SumRootToLeafNumbers();
        
        // 测试用例1: [1,2,3]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("测试用例1 - DFS: " + solution.sumNumbers(root1)); // 预期: 25 (12+13)
        System.out.println("测试用例1 - BFS: " + solution.sumNumbersBFS(root1)); // 预期: 25
        
        // 测试用例2: [4,9,0,5,1]
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(0);
        root2.left.left = new TreeNode(5);
        root2.left.right = new TreeNode(1);
        System.out.println("测试用例2 - DFS: " + solution.sumNumbers(root2)); // 预期: 1026 (495+491+40)
        System.out.println("测试用例2 - BFS: " + solution.sumNumbersBFS(root2)); // 预期: 1026
        
        // 测试用例3: [1,0]
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(0);
        System.out.println("测试用例3 - DFS: " + solution.sumNumbers(root3)); // 预期: 10
        System.out.println("测试用例3 - BFS: " + solution.sumNumbersBFS(root3)); // 预期: 10
        
        // 测试用例4: [0]
        TreeNode root4 = new TreeNode(0);
        System.out.println("测试用例4 - DFS: " + solution.sumNumbers(root4)); // 预期: 0
        System.out.println("测试用例4 - BFS: " + solution.sumNumbersBFS(root4)); // 预期: 0
        
        // 测试用例5: []
        TreeNode root5 = null;
        System.out.println("测试用例5 - DFS: " + solution.sumNumbers(root5)); // 预期: 0
        System.out.println("测试用例5 - BFS: " + solution.sumNumbersBFS(root5)); // 预期: 0
    }
}
```

## 关键点解析

### DFS解法核心逻辑

1. **路径数字构建**：
    - 使用 `currentSum * 10 + node.val` 来构建路径数字
    - 例如：路径 1→2→3 的数字 = ((0×10+1)×10+2)×10+3 = 123

2. **递归终止条件**：
    - 当节点为null时返回0
    - 当到达叶子节点时返回当前路径数字

3. **递归合并结果**：
    - 返回左子树和右子树结果的和

### 执行过程示例（树：[1,2,3]）

```
根节点: 1
currentSum = 0×10+1 = 1

左子树: 节点2
currentSum = 1×10+2 = 12
是叶子节点 → 返回12

右子树: 节点3  
currentSum = 1×10+3 = 13
是叶子节点 → 返回13

总和: 12 + 13 = 25
```

### BFS解法核心逻辑

1. **双队列设计**：
    - `nodeQueue`：存储待处理的节点
    - `sumQueue`：存储到当前节点的路径值

2. **层级遍历**：
    - 同时处理节点和对应的路径值
    - 当遇到叶子节点时累加结果

## 学习建议

1. **理解路径数字构建**：
    - 掌握 `currentSum * 10 + node.val` 的原理
    - 理解为什么这样能正确构建多位数

2. **掌握两种遍历方式**：
    - DFS：递归思维，代码简洁
    - BFS：迭代思维，避免递归栈溢出

3. **处理边界情况**：
    - 空树
    - 单节点树
    - 包含0的节点值

4. **相关题目练习**：
    - 二叉树的所有路径（输出路径字符串）
    - 路径总和（判断是否存在路径和）
    - 从根到叶的二进制数之和

5. **复杂度分析**：
    - 时间复杂度：O(n)，每个节点访问一次
    - 空间复杂度：
        - DFS：O(h)，递归栈深度，h为树高
        - BFS：O(n)，队列大小

6. **调试技巧**：
    - 打印每条路径的当前数字
    - 使用小树手动验证
    - 检查叶子节点的判断条件

7. **算法选择**：
    - 面试推荐DFS解法
    - 大树推荐BFS避免栈溢出
    - 理解原理推荐两种都实现

## 常见错误

1. **忘记处理空节点**：
   ```java
   // 错误：可能空指针
   if (node == null) return currentSum;
   // 正确：空节点返回0，不贡献任何值
   if (node == null) return 0;
   ```

2. **数字构建错误**：
   ```java
   // 错误：没有乘以10
   currentSum = currentSum + node.val;
   // 正确：每次都要乘以10
   currentSum = currentSum * 10 + node.val;
   ```

3. **叶子节点判断不完整**：
   ```java
   // 错误：只检查一边
   if (node.left == null) return currentSum;
   // 正确：检查两边都为空
   if (node.left == null && node.right == null) return currentSum;
   ```

这道题很好地训练了树的遍历和路径处理，掌握它对解决其他树形路径问题很有帮助！