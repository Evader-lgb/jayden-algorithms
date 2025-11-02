# 从前序与中序遍历序列构造二叉树

## 解题思路

**总思路**：利用前序遍历确定根节点，在中序遍历中定位根节点位置，从而划分左右子树，递归构建整棵二叉树。

**分步骤**：
1. 前序遍历的第一个元素就是根节点
2. 在中序遍历中找到根节点的位置，将中序数组分为左子树和右子树
3. 根据左子树的节点数量，将前序数组也分为左子树和右子树
4. 递归构建左子树和右子树
5. 将左右子树连接到根节点上

## 归类说明
- **主要分类**：树、二叉树
- **算法技巧**：递归、分治
- **数据结构**：数组、哈希表、二叉树
- **相关题目**：从中序与后序遍历构造二叉树、根据前序和后序遍历构造二叉树

## Java代码实现

```java
import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树节点定义
 */
class TreeNode {
    int val;            // 节点值
    TreeNode left;      // 左子节点
    TreeNode right;     // 右子节点
    
    TreeNode() {}
    TreeNode(int val) { 
        this.val = val; // 带值的构造函数
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    
    // 存储中序遍历中值到索引的映射，用于快速查找根节点位置
    private Map<Integer, Integer> inorderMap = new HashMap<>();
    // 前序遍历数组
    private int[] preorder;
    
    /**
     * 根据前序和中序遍历构建二叉树
     * @param preorder 前序遍历数组
     * @param inorder 中序遍历数组
     * @return 构建完成的二叉树根节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        
        // 构建中序遍历的值到索引的映射
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        // 调用递归函数构建整棵树
        return buildTreeRecursive(0, preorder.length - 1, 0, inorder.length - 1);
    }
    
    /**
     * 递归构建二叉树的辅助函数
     * @param preLeft 前序遍历数组的左边界
     * @param preRight 前序遍历数组的右边界
     * @param inLeft 中序遍历数组的左边界
     * @param inRight 中序遍历数组的右边界
     * @return 当前子树根节点
     */
    private TreeNode buildTreeRecursive(int preLeft, int preRight, int inLeft, int inRight) {
        // 递归终止条件：如果前序遍历区间为空，返回null
        if (preLeft > preRight) {
            return null;
        }
        
        // 前序遍历的第一个元素就是当前子树的根节点
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        
        // 在中序遍历中找到根节点的位置
        int rootIndexInInorder = inorderMap.get(rootVal);
        
        // 计算左子树的节点数量
        int leftSubtreeSize = rootIndexInInorder - inLeft;
        
        // 递归构建左子树
        // 左子树在前序遍历中的区间：[preLeft + 1, preLeft + leftSubtreeSize]
        // 左子树在中序遍历中的区间：[inLeft, rootIndexInInorder - 1]
        root.left = buildTreeRecursive(
            preLeft + 1, 
            preLeft + leftSubtreeSize, 
            inLeft, 
            rootIndexInInorder - 1
        );
        
        // 递归构建右子树
        // 右子树在前序遍历中的区间：[preLeft + leftSubtreeSize + 1, preRight]
        // 右子树在中序遍历中的区间：[rootIndexInInorder + 1, inRight]
        root.right = buildTreeRecursive(
            preLeft + leftSubtreeSize + 1, 
            preRight, 
            rootIndexInInorder + 1, 
            inRight
        );
        
        return root;
    }
}

/**
 * 测试用例示例
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // 测试用例
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        
        TreeNode root = solution.buildTree(preorder, inorder);
        
        // 可以添加遍历函数来验证结果
        System.out.println("构建完成！");
    }
}
```

## 关键点解析

### 1. 核心思想
- **前序遍历**：根节点 → 左子树 → 右子树
- **中序遍历**：左子树 → 根节点 → 右子树

### 2. 索引计算技巧
```
左子树节点数量 = 根节点在中序位置 - 中序左边界

前序左子树区间：[preLeft + 1, preLeft + leftSubtreeSize]
前序右子树区间：[preLeft + leftSubtreeSize + 1, preRight]

中序左子树区间：[inLeft, rootIndex - 1]
中序右子树区间：[rootIndex + 1, inRight]
```

## 学习建议

### 1. 理解递归过程
- 每次递归处理一个子树
- 明确递归函数的参数含义（各个边界）
- 理解递归终止条件

### 2. 掌握遍历特性
- **前序遍历**：第一个元素永远是根节点
- **中序遍历**：根节点左边是左子树，右边是右子树

### 3. 调试技巧
- 用小的测试用例手动模拟递归过程
- 打印每次递归的参数值，观察区间变化
- 画图理解数组索引的关系

### 4. 相关练习题目
1. **从中序与后序遍历构造二叉树**（LeetCode 106）
    - 后序遍历的最后一个元素是根节点
    - 其他思路类似

2. **根据前序和后序遍历构造二叉树**（LeetCode 889）
    - 这道题结果可能不唯一
    - 需要更多的条件判断

3. **序列化和反序列化二叉树**（LeetCode 297）
    - 应用类似的树重建思想

### 5. 常见错误避免
- 数组索引越界：仔细检查边界条件
- 忘记递归终止条件
- 左右子树区间计算错误
- 哈希表未初始化或键不存在

通过这道题，你可以深入理解二叉树遍历的性质和递归分治的思想，这是解决树相关问题的重要基础！