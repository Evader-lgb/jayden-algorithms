# 二叉树展开为链表（Flatten Binary Tree to Linked List）

## 解题思路

**总思路**：使用后序遍历的递归方法，将二叉树展开为"右-左-根"顺序的链表。

**分步骤**：
1. 使用递归后序遍历二叉树
2. 在遍历过程中维护一个全局指针，指向当前链表的末尾
3. 按照"右-左-根"的顺序处理节点
4. 将当前节点的右指针指向前一个节点，左指针设为null

## 归类说明
- **主要归类**：二叉树、链表、递归
- **算法技巧**：后序遍历、链表操作
- **相关题型**：二叉树的前序遍历、二叉树的中序遍历

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

public class FlattenBinaryTreeToLinkedList {
    /**
     * 递归解法（后序遍历）
     */
    private TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        // 后序遍历：右 -> 左 -> 根
        flatten(root.right);
        flatten(root.left);
        
        // 处理当前节点
        root.right = prev;
        root.left = null;
        prev = root;
    }
    
    /**
     * 迭代解法（前序遍历 + 栈）
     */
    public void flattenIterative(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null;
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            
            // 将右子节点、左子节点压入栈中
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
            
            // 构建链表
            if (prev != null) {
                prev.right = current;
                prev.left = null;
            }
            prev = current;
        }
    }
    
    /**
     * Morris遍历解法（O(1)空间）
     */
    public void flattenMorris(TreeNode root) {
        TreeNode current = root;
        
        while (current != null) {
            if (current.left != null) {
                // 找到左子树的最右节点
                TreeNode predecessor = current.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                
                // 将当前节点的右子树接到前驱节点的右指针
                predecessor.right = current.right;
                // 将左子树移到右子树
                current.right = current.left;
                current.left = null;
            }
            // 移动到下一个节点
            current = current.right;
        }
    }
    
    /**
     * 另一种递归写法（返回链表尾部）
     */
    public void flattenWithReturn(TreeNode root) {
        flattenHelper(root);
    }
    
    private TreeNode flattenHelper(TreeNode node) {
        if (node == null) {
            return null;
        }
        
        // 叶子节点，直接返回
        if (node.left == null && node.right == null) {
            return node;
        }
        
        TreeNode leftTail = flattenHelper(node.left);
        TreeNode rightTail = flattenHelper(node.right);
        
        // 如果有左子树，需要重新连接
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        
        // 返回右子树的尾部，如果右子树为空则返回左子树的尾部
        return rightTail != null ? rightTail : leftTail;
    }
    
    // 测试方法
    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList solution = new FlattenBinaryTreeToLinkedList();
        
        // 测试用例1: [1,2,5,3,4,null,6]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(6);
        
        System.out.println("原始树:");
        printTree(root1);
        
        solution.flatten(root1);
        System.out.println("展开后:");
        printList(root1);
        
        // 测试用例2: []
        TreeNode root2 = null;
        solution.flatten(root2);
        System.out.println("空树展开: " + (root2 == null));
        
        // 测试用例3: [0]
        TreeNode root3 = new TreeNode(0);
        solution.flatten(root3);
        System.out.println("单节点展开:");
        printList(root3);
    }
    
    // 辅助方法：打印树（前序遍历）
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
    
    // 辅助方法：打印链表
    private static void printList(TreeNode head) {
        TreeNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.right != null) {
                System.out.print(" -> ");
            }
            current = current.right;
        }
        System.out.println();
    }
}
```

## 关键点解析

### 递归解法核心逻辑

1. **遍历顺序**：右 → 左 → 根（后序遍历的变种）
2. **链表构建**：
    - 从最后一个节点开始构建
    - 每个节点的右指针指向前一个节点
    - 左指针设为null

3. **全局指针**：`prev` 指向当前链表的最后一个节点

### 执行过程示例（树：[1,2,5,3,4,null,6]）

```
原始树:
    1
   / \
  2   5
 / \   \
3   4   6

递归过程：
1. 处理节点6: prev=6
2. 处理节点4: 4.right=6, prev=4
3. 处理节点3: 3.right=4, prev=3  
4. 处理节点2: 2.right=3, 2.left=null, prev=2
5. 处理节点5: 5.right=6, 5.left=null, prev=5
6. 处理节点1: 1.right=2, 1.left=null, prev=1

最终链表: 1→2→3→4→5→6
```

### Morris遍历解法思路

1. **核心思想**：利用树中空闲的指针，实现O(1)空间复杂度
2. **关键操作**：
    - 找到当前节点左子树的最右节点（前驱节点）
    - 将当前节点的右子树接到前驱节点的右边
    - 将左子树移到右子树位置

## 学习建议

1. **理解遍历顺序**：
    - 掌握为什么使用"右-左-根"的后序遍历
    - 理解递归的调用栈过程

2. **掌握多种解法**：
    - 递归：思维直观，易于理解
    - 迭代：使用栈模拟递归
    - Morris：空间最优，理解指针操作

3. **处理边界情况**：
    - 空树
    - 单节点树
    - 只有左子树或只有右子树

4. **相关题目练习**：
    - 二叉树的前序遍历
    - 二叉树的中序遍历
    - 有序链表转换二叉搜索树

5. **复杂度分析**：
    - 时间复杂度：O(n)，每个节点访问一次
    - 空间复杂度：
        - 递归：O(h)，递归栈深度
        - 迭代：O(h)，栈的大小
        - Morris：O(1)

6. **调试技巧**：
    - 打印每个步骤后的树结构
    - 使用小树手动验证
    - 检查指针是否正确设置

7. **算法选择**：
    - 面试推荐递归解法
    - 实际应用根据空间需求选择
    - 理解原理推荐多种实现

## 常见错误

1. **遍历顺序错误**：
   ```java
   // 错误：前序遍历会导致信息丢失
   flatten(root.left);
   flatten(root.right);
   // 正确：后序遍历，先处理子节点
   flatten(root.right);
   flatten(root.left);
   ```

2. **指针设置错误**：
   ```java
   // 错误：忘记设置左指针为null
   root.right = prev;
   // 正确：必须设置左指针为null
   root.right = prev;
   root.left = null;
   ```

3. **全局变量重置**：
   ```java
   // 错误：多次调用时prev未重置
   public void flatten(TreeNode root) {
       prev = null; // 应该在开始时重置
       // ...
   }
   ```

## 可视化理解

```
原始树结构:
    1
   / \
  2   5
 / \   \
3   4   6

展开过程:
步骤1: 处理右子树5,6
步骤2: 处理左子树2,3,4  
步骤3: 连接: 4→5, 3→4, 2→3, 1→2

最终链表:
1 → 2 → 3 → 4 → 5 → 6
```

这道题很好地训练了树的遍历和链表操作，掌握它对解决其他树形结构问题很有帮助！