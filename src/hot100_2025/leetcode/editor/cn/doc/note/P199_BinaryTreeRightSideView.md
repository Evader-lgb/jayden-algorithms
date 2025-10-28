# 二叉树的右视图（Binary Tree Right Side View）解题详解

## 解题思路

**总思路**：使用层序遍历（BFS），记录每一层最右边的节点值。

**分步骤**：
1. 使用队列进行二叉树的层序遍历
2. 对于每一层，记录最后一个节点的值
3. 将每一层的最后一个节点值加入结果列表
4. 返回结果列表

## 归类说明
- **主要归类**：二叉树、广度优先搜索
- **算法技巧**：层序遍历、队列
- **相关题型**：二叉树的层序遍历、左视图、锯齿形层序遍历

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

public class BinaryTreeRightSideView {
    /**
     * BFS解法 - 层序遍历
     * @param root 二叉树根节点
     * @return 右视图节点值列表
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // 遍历当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // 如果是当前层的最后一个节点，加入结果
                if (i == levelSize - 1) {
                    result.add(node.val);
                }
                
                // 将左右子节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return result;
    }
    
    /**
     * DFS解法 - 递归
     * @param root 二叉树根节点
     * @return 右视图节点值列表
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        // 如果当前深度等于结果列表大小，说明是第一次访问该深度
        // 将当前节点值加入结果（先访问右子树，所以第一个访问的就是最右节点）
        if (depth == result.size()) {
            result.add(node.val);
        }
        
        // 先递归右子树，再递归左子树
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }
    
    // 测试方法
    public static void main(String[] args) {
        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();
        
        // 测试用例1: [1,2,3,null,5,null,4]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);
        
        System.out.println("测试用例1 - BFS: " + solution.rightSideView(root1));
        System.out.println("测试用例1 - DFS: " + solution.rightSideViewDFS(root1));
        // 预期: [1, 3, 4]
        
        // 测试用例2: [1,null,3]
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(3);
        
        System.out.println("测试用例2 - BFS: " + solution.rightSideView(root2));
        System.out.println("测试用例2 - DFS: " + solution.rightSideViewDFS(root2));
        // 预期: [1, 3]
        
        // 测试用例3: []
        TreeNode root3 = null;
        
        System.out.println("测试用例3 - BFS: " + solution.rightSideView(root3));
        System.out.println("测试用例3 - DFS: " + solution.rightSideViewDFS(root3));
        // 预期: []
        
        // 测试用例4: [1,2]
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        
        System.out.println("测试用例4 - BFS: " + solution.rightSideView(root4));
        System.out.println("测试用例4 - DFS: " + solution.rightSideViewDFS(root4));
        // 预期: [1, 2]
        
        // 测试用例5: [1,2,3,4]
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);
        root5.left.left = new TreeNode(4);
        
        System.out.println("测试用例5 - BFS: " + solution.rightSideView(root5));
        System.out.println("测试用例5 - DFS: " + solution.rightSideViewDFS(root5));
        // 预期: [1, 3, 4]
    }
}
```

## 关键点解析

### BFS解法核心逻辑

1. **层序遍历**：
    - 使用队列实现广度优先搜索
    - 记录每一层的节点数量

2. **右节点选择**：
    - 每层遍历时，最后一个节点就是最右侧的节点
    - 将最后一个节点的值加入结果

### DFS解法核心逻辑

1. **递归顺序**：
    - 先递归右子树，再递归左子树
    - 保证每一层第一个访问的节点是最右侧节点

2. **深度记录**：
    - 使用深度参数跟踪当前节点所在层级
    - 当深度等于结果列表大小时，说明是第一次访问该层级

### 执行过程示例（以[1,2,3,null,5,null,4]为例）

**BFS过程：**
```
第0层: [1] → 最后一个节点: 1
第1层: [2, 3] → 最后一个节点: 3  
第2层: [5, 4] → 最后一个节点: 4
结果: [1, 3, 4]
```

**DFS过程：**
```
1. 访问节点1，深度0 → 结果加入1
2. 递归右子树: 节点3，深度1 → 结果加入3
3. 递归右子树: 节点4，深度2 → 结果加入4
4. 递归左子树: 无
5. 回到节点1，递归左子树: 节点2，深度1 → 结果已有深度1的值，跳过
6. 递归右子树: 节点5，深度2 → 结果已有深度2的值，跳过
结果: [1, 3, 4]
```

## 学习建议

1. **掌握两种解法**：
    - BFS：直观易懂，适合层序遍历相关问题
    - DFS：代码简洁，理解递归顺序的重要性

2. **理解层序遍历**：
    - 练习基本的二叉树层序遍历
    - 理解如何确定每一层的边界

3. **处理边界情况**：
    - 空树的情况
    - 只有左子树的情况
    - 单节点树

4. **相关题目练习**：
    - 二叉树的左视图
    - 二叉树的层序遍历
    - 二叉树的锯齿形层序遍历
    - 在每个树行中找最大值

5. **复杂度分析**：
    - 时间复杂度：O(n)，每个节点访问一次
    - 空间复杂度：
        - BFS：O(w)，w为树的最大宽度
        - DFS：O(h)，h为树的高度（递归栈）

6. **调试技巧**：
    - 打印每一层的节点值
    - 使用小例子手动验证
    - 对比BFS和DFS的结果

7. **算法选择**：
    - BFS：需要显式处理每一层时使用
    - DFS：代码更简洁，适合递归思维

这道题很好地展示了二叉树遍历的两种经典方法，掌握它对解决其他二叉树问题很有帮助！