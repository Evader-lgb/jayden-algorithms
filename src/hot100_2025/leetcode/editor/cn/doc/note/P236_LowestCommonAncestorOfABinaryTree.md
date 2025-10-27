# 236. 二叉树的最近公共祖先

## 📝 解题思路（总分结构）

### 总思路
使用递归深度优先搜索，在二叉树中查找节点p和q，当在左右子树中分别找到p和q时，当前节点就是最近公共祖先。

### 分步骤详解
1. **递归终止条件**：如果当前节点为空或是p或q，直接返回当前节点
2. **递归左右子树**：在左子树和右子树中分别查找p和q
3. **判断结果**：
    - 如果左右子树都找到了节点（一个找到p，一个找到q），当前节点就是最近公共祖先
    - 如果只有一边找到，返回找到的那边的结果
    - 如果都没找到，返回null

## 🏷️ 算法归类

**主要归类**：二叉树、深度优先搜索、递归  
**相关知识点**：LCA问题、树形递归、后序遍历  
**难度级别**：中等  
**相似题型**：二叉搜索树的最近公共祖先、最深叶节点的最近公共祖先

## 💻 Java代码实现

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 步骤1：递归终止条件
        // 如果当前节点为空，或者当前节点就是p或q，直接返回当前节点
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // 步骤2：递归在左子树中查找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        
        // 步骤3：递归在右子树中查找p和q  
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // 步骤4：根据左右子树的查找结果进行判断
        if (left != null && right != null) {
            // 情况1：左右子树分别找到了p和q
            // 说明当前节点就是最近公共祖先
            return root;
        } else if (left != null) {
            // 情况2：只在左子树中找到了p或q
            // 返回左子树的结果
            return left;
        } else {
            // 情况3：只在右子树中找到了p或q，或者都没找到
            // 返回右子树的结果（如果都没找到，right为null）
            return right;
        }
    }
}
```

## 📚 学习建议

### 1. 理解递归的核心思想

**递归的三种情况**：
```java
// 1. 当前节点是p或q：直接返回
if (root == p || root == q) return root;

// 2. 左右子树分别包含p和q：当前节点是LCA
if (left != null && right != null) return root;

// 3. 只有一边找到：返回找到的那边
return left != null ? left : right;
```

### 2. 算法过程可视化

**示例二叉树**：
```
       3
      / \
     5   1
    / \ / \
   6  2 0  8
     / \
    7   4
```

**查找p=5, q=1**：
```
从节点3开始：
  左子树查找5 → 找到5
  右子树查找1 → 找到1
  左右都不为空 → 3是LCA
```

**查找p=5, q=4**：
```
从节点3开始：
  左子树查找5和4：
    从节点5开始：
      左子树查找 → 返回null
      右子树查找：
        从节点2开始：
          左子树查找 → 返回null
          右子树查找 → 找到4
        返回4
      节点5：左null右4 → 返回4？不对！
      实际上节点5自己就是p，所以返回5
  右子树查找 → 返回null
  节点3：左5右null → 返回5
```

### 3. 复杂度分析
- **时间复杂度**：O(n) - 每个节点最多访问一次
- **空间复杂度**：O(h) - 递归栈深度，h是树的高度

### 4. 关键技巧详解

**递归的巧妙之处**：
```java
// 这个递归函数有双重含义：
// 1. 查找p和q的最近公共祖先
// 2. 如果没找到LCA，就返回找到的p或q（如果存在）

// 这让我们可以在一次遍历中完成所有工作
```

**三种情况的物理意义**：
```java
// 情况1：左右都不为空
// 意味着p和q分别在当前节点的左右子树中
// 所以当前节点就是LCA

// 情况2：左不为空，右为空  
// 意味着p和q都在左子树中，LCA在左子树

// 情况3：左为空，右不为空
// 意味着p和q都在右子树中，LCA在右子树
```

### 5. 学习路径建议

1. **理解递归思维**：掌握树形问题的递归解决方法
2. **理解LCA概念**：明白最近公共祖先的定义
3. **练习相似题目**：
    - 235. 二叉搜索树的最近公共祖先
    - 1650. 二叉树的最近公共祖先 III
    - 1123. 最深叶节点的最近公共祖先
4. **手动模拟过程**：用具体例子跟踪递归调用

### 6. 调试技巧

**添加调试信息**：
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
        System.out.println("到达空节点，返回null");
        return null;
    }
    
    System.out.println("访问节点: " + root.val);
    
    if (root == p || root == q) {
        System.out.println("找到目标节点: " + root.val);
        return root;
    }
    
    System.out.println("递归左子树...");
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    System.out.println("左子树返回: " + (left == null ? "null" : left.val));
    
    System.out.println("递归右子树...");
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    System.out.println("右子树返回: " + (right == null ? "null" : right.val));
    
    if (left != null && right != null) {
        System.out.println("左右都不为空，LCA是: " + root.val);
        return root;
    }
    
    TreeNode result = left != null ? left : right;
    System.out.println("返回: " + (result == null ? "null" : result.val));
    return result;
}
```

### 7. 常见错误避免

```java
// 错误：忘记检查当前节点是否是p或q
// 必须首先检查 if (root == p || root == q)

// 错误：递归条件错误
// 应该递归左右子树，不是只递归一边

// 错误：结果判断逻辑错误
if (left == p && right == q)  // 错误！应该检查是否都不为null

// 错误：没有处理空指针
// 必须先检查root是否为null
```

## 🎯 核心思想总结

**最近公共祖先问题的精髓**：

### 1. 递归定义
- 如果当前节点是p或q，返回当前节点
- 在左右子树中递归查找
- 根据左右子树的结果判断LCA

### 2. 后序遍历思想
- 先处理左右子树，再处理当前节点
- 利用子树的结果推导当前节点的结果

### 3. 巧妙的结果传递
- 递归函数既返回LCA，也返回找到的节点
- 通过null值传递"未找到"的信息

### 4. 分治策略
- 将大问题分解为左右子树的子问题
- 合并子问题的结果得到最终解

## 💡 实际应用场景
- 家谱分析中的共同祖先查找
- 版本控制系统中的共同父提交
- 组织架构中的共同上级
- 网络路由中的共同节点

**重要提示**：这是二叉树递归问题的经典案例，理解后可以解决许多类似的树形结构问题。建议多练习几个变种题目来加深理解！

通过这道题，你将深入掌握树形递归的核心思想，这是解决复杂树形问题的重要基础！