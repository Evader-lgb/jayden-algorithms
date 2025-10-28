# 二叉树中的最大路径和（Binary Tree Maximum Path Sum）解题详解

## 解题思路

**总思路**：使用后序遍历（DFS）计算每个节点的最大贡献值，同时在遍历过程中更新全局最大路径和。

**分步骤**：
1. 使用深度优先搜索（DFS）遍历二叉树
2. 对于每个节点，计算其左右子树的最大贡献值（如果为负则取0）
3. 当前节点的最大路径和 = 节点值 + 左子树贡献 + 右子树贡献
4. 更新全局最大路径和
5. 返回当前节点的最大贡献值（节点值 + max(左贡献, 右贡献)）

## 归类说明
- **主要归类**：二叉树、深度优先搜索、动态规划
- **算法技巧**：后序遍历、递归、全局变量
- **相关题型**：二叉树直径、路径总和、子树最大和

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

public class BinaryTreeMaximumPathSum {
    private int maxSum = Integer.MIN_VALUE; // 全局变量，记录最大路径和
    
    public int maxPathSum(TreeNode root) {
        // 初始化最大路径和为最小整数
        maxSum = Integer.MIN_VALUE;
        // 开始DFS遍历
        maxGain(root);
        return maxSum;
    }
    
    /**
     * 计算以node为根节点的子树的最大贡献值
     * @param node 当前节点
     * @return 该节点的最大贡献值
     */
    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0; // 空节点的贡献值为0
        }
        
        // 递归计算左右子树的最大贡献值
        // 如果贡献值为负，则选择0（不选择该子树）
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        
        // 当前节点的路径和 = 节点值 + 左子树贡献 + 右子树贡献
        int currentPathSum = node.val + leftGain + rightGain;
        
        // 更新全局最大路径和
        maxSum = Math.max(maxSum, currentPathSum);
        
        // 返回当前节点的最大贡献值
        // 注意：只能选择左子树或右子树的一条路径，不能同时选择两条
        return node.val + Math.max(leftGain, rightGain);
    }
    
    // 测试方法
    public static void main(String[] args) {
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();
        
        // 测试用例1: [1,2,3]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("测试用例1: " + solution.maxPathSum(root1)); // 预期: 6
        
        // 测试用例2: [-10,9,20,null,null,15,7]
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println("测试用例2: " + solution.maxPathSum(root2)); // 预期: 42
        
        // 测试用例3: [-3]
        TreeNode root3 = new TreeNode(-3);
        System.out.println("测试用例3: " + solution.maxPathSum(root3)); // 预期: -3
        
        // 测试用例4: [2,-1]
        TreeNode root4 = new TreeNode(2);
        root4.left = new TreeNode(-1);
        System.out.println("测试用例4: " + solution.maxPathSum(root4)); // 预期: 2
        
        // 测试用例5: [1,-2,3]
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(-2);
        root5.right = new TreeNode(3);
        System.out.println("测试用例5: " + solution.maxPathSum(root5)); // 预期: 4
    }
}
```

## 关键点解析

### 算法核心逻辑

1. **最大贡献值计算**：
    - 每个节点的最大贡献值 = 节点值 + max(左子树贡献, 右子树贡献)
    - 如果贡献值为负，则取0（表示不选择该路径）

2. **路径和计算**：
    - 当前节点的路径和 = 节点值 + 左子树贡献 + 右子树贡献
    - 这个路径和表示以当前节点为"转折点"的路径

3. **全局最大值更新**：
    - 在计算每个节点时，都尝试更新全局最大路径和

### 执行过程示例（以[-10,9,20,null,null,15,7]为例）

```
树结构：
    -10
    / \
   9  20
     /  \
    15   7

执行过程：
1. 节点15: leftGain=0, rightGain=0, currentPathSum=15, maxSum=15
   返回15

2. 节点7: leftGain=0, rightGain=0, currentPathSum=7, maxSum=15
   返回7

3. 节点20: leftGain=15, rightGain=7, currentPathSum=20+15+7=42, maxSum=42
   返回20+max(15,7)=35

4. 节点9: leftGain=0, rightGain=0, currentPathSum=9, maxSum=42
   返回9

5. 节点-10: leftGain=9, rightGain=35, currentPathSum=-10+9+35=34, maxSum=42
   返回-10+max(9,35)=25

最终结果：42
```

### 重要概念解释

1. **节点贡献值**：从该节点出发向下延伸的最大路径和（只能选择左或右一条路径）
2. **路径和**：以该节点为转折点的完整路径和（可以同时包含左右子树）
3. **负值处理**：如果子树贡献为负，则选择0（不选择该子树）

## 学习建议

1. **理解递归过程**：
    - 在纸上画出递归调用栈
    - 理解后序遍历的执行顺序

2. **掌握贡献值概念**：
    - 区分"节点贡献值"和"路径和"的不同
    - 理解为什么返回贡献值时只能选择一条路径

3. **处理边界情况**：
    - 单个节点
    - 所有节点为负值
    - 不平衡树

4. **相关题目练习**：
    - 二叉树直径（类似的路径计算）
    - 路径总和III（路径统计）
    - 子树的最大平均值

5. **复杂度分析**：
    - 时间复杂度：O(n)，每个节点访问一次
    - 空间复杂度：O(h)，递归栈深度，h为树的高度

6. **调试技巧**：
    - 打印每个节点的贡献值和路径和
    - 使用小例子手动验证
    - 检查负值的处理是否正确

7. **算法优化**：
    - 使用全局变量避免重复计算
    - 及时剪枝（如果已经知道不可能更大）

这道题很好地展示了如何在二叉树中使用DFS和动态规划思想，掌握它对解决其他树形DP问题很有帮助！