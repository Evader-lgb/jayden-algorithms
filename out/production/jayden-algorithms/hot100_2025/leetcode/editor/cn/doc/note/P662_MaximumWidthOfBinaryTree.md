# 二叉树的最大宽度

## 解题思路

**总思路**：使用层序遍历，给每个节点编号，通过计算每层最左和最右节点的编号差来得到宽度。

**分步骤**：
1. 使用队列进行层序遍历，同时存储节点和其编号
2. 对于每一层，记录第一个节点的编号作为左边界
3. 遍历该层所有节点，记录最后一个节点的编号作为右边界
4. 计算当前层宽度：右边界 - 左边界 + 1
5. 更新最大宽度

## 归类说明
- **主要归类**：二叉树、层序遍历
- **算法技巧**：节点编号、宽度计算
- **相关题型**：二叉树的层序遍历、二叉树的最大深度

## Java代码实现

```java
import java.util.*;

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

public class MaximumWidthOfBinaryTree {
    /**
     * 层序遍历解法
     * @param root 二叉树根节点
     * @return 二叉树的最大宽度
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // 使用队列存储节点和对应的编号
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // 当前层第一个节点的编号
            int left = queue.peek().getValue();
            int right = left;
            
            // 遍历当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> current = queue.poll();
                TreeNode node = current.getKey();
                right = current.getValue();
                
                // 将子节点加入队列，并计算它们的编号
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * right));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * right + 1));
                }
            }
            
            // 计算当前层的宽度
            int width = right - left + 1;
            maxWidth = Math.max(maxWidth, width);
        }
        
        return maxWidth;
    }
    
    /**
     * 防止整数溢出的优化版本
     */
    public int widthOfBinaryTreeOptimized(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int left = queue.peek().getValue();
            int right = left;
            
            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> current = queue.poll();
                TreeNode node = current.getKey();
                int position = current.getValue();
                right = position;
                
                // 关键优化：使用相对位置，防止整数溢出
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * (position - left)));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * (position - left) + 1));
                }
            }
            
            maxWidth = Math.max(maxWidth, right - left + 1);
        }
        
        return maxWidth;
    }
    
    /**
     * 使用两个队列的解法
     */
    public int widthOfBinaryTreeTwoQueues(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> positionQueue = new LinkedList<>();
        nodeQueue.offer(root);
        positionQueue.offer(0);
        int maxWidth = 0;
        
        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();
            int left = positionQueue.peek();
            int right = left;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = nodeQueue.poll();
                int position = positionQueue.poll();
                right = position;
                
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    positionQueue.offer(2 * position);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    positionQueue.offer(2 * position + 1);
                }
            }
            
            maxWidth = Math.max(maxWidth, right - left + 1);
        }
        
        return maxWidth;
    }
    
    // 辅助类：用于存储键值对
    class Pair<K, V> {
        private K key;
        private V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
    }
    
    // 测试方法
    public static void main(String[] args) {
        MaximumWidthOfBinaryTree solution = new MaximumWidthOfBinaryTree();
        
        // 测试用例1: [1,3,2,5,3,null,9]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(9);
        System.out.println("测试用例1: " + solution.widthOfBinaryTree(root1)); // 预期: 4
        
        // 测试用例2: [1,3,2,5,null,null,9,6,null,7]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(5);
        root2.right.right = new TreeNode(9);
        root2.left.left.left = new TreeNode(6);
        root2.right.right.right = new TreeNode(7);
        System.out.println("测试用例2: " + solution.widthOfBinaryTree(root2)); // 预期: 7
        
        // 测试用例3: [1,3,2,5]
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(3);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(5);
        System.out.println("测试用例3: " + solution.widthOfBinaryTree(root3)); // 预期: 2
        
        // 测试用例4: [1]
        TreeNode root4 = new TreeNode(1);
        System.out.println("测试用例4: " + solution.widthOfBinaryTree(root4)); // 预期: 1
        
        // 测试用例5: []
        TreeNode root5 = null;
        System.out.println("测试用例5: " + solution.widthOfBinaryTree(root5)); // 预期: 0
    }
}
```

## 关键点解析

### 核心逻辑

1. **节点编号规则**：
    - 根节点编号为0
    - 左子节点编号 = 父节点编号 × 2
    - 右子节点编号 = 父节点编号 × 2 + 1

2. **宽度计算**：
    - 每层的宽度 = 最右节点编号 - 最左节点编号 + 1
    - 最大宽度 = 所有层宽度的最大值

### 执行过程示例（树：[1,3,2,5,3,null,9]）

```
第0层: 节点1，编号0 → 宽度1
第1层: 节点3(编号0)，节点2(编号1) → 宽度1-0+1=2
第2层: 节点5(编号0)，节点3(编号1)，节点9(编号3) → 宽度3-0+1=4

最大宽度: 4
```

### 防止整数溢出的优化

在深度很大的树中，节点编号可能超过整数范围。优化方法：
```java
// 使用相对位置
queue.offer(new Pair<>(node.left, 2 * (position - left)));
```

这样确保每层的编号都从0开始，避免编号过大。

## 学习建议

1. **理解编号系统**：
    - 掌握二叉树节点编号的规律
    - 理解为什么这样编号能正确计算宽度

2. **掌握层序遍历**：
    - 熟练使用队列进行层次遍历
    - 理解如何同时处理节点和位置信息

3. **处理边界情况**：
    - 空树
    - 单节点树
    - 只有左子树或只有右子树

4. **相关题目练习**：
    - 二叉树的层序遍历
    - 二叉树的最大深度
    - 二叉树的右视图

5. **复杂度分析**：
    - 时间复杂度：O(n)，每个节点访问一次
    - 空间复杂度：O(n)，队列的大小

6. **调试技巧**：
    - 打印每层的节点编号
    - 使用小树手动验证
    - 检查边界条件的处理

7. **算法选择**：
    - 标准解法：简单直接
    - 优化版本：防止整数溢出
    - 双队列版本：逻辑更清晰

## 常见错误

1. **编号计算错误**：
   ```java
   // 错误：没有使用正确的编号规则
   queue.offer(new Pair<>(node.left, position + 1));
   // 正确：使用2倍关系
   queue.offer(new Pair<>(node.left, 2 * position));
   ```

2. **宽度计算错误**：
   ```java
   // 错误：直接使用最后一个位置
   int width = right + 1;
   // 正确：计算差值
   int width = right - left + 1;
   ```

3. **整数溢出**：
   ```java
   // 在深度大的树中可能溢出
   queue.offer(new Pair<>(node.left, 2 * position));
   // 使用优化版本避免溢出
   queue.offer(new Pair<>(node.left, 2 * (position - left)));
   ```

这道题很好地训练了二叉树的层序遍历和位置计算，掌握它对解决其他树形结构问题很有帮助！