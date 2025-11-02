//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
// 
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 2617 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 * @author Jayden
 * @date 2025-11-01 16:53:23
 */
public class P105_ConstructBinaryTreeFromPreorderAndInorderTraversal{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P105_ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
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

    // 存储中序遍历中值到索引的映射，用于快速查找根节点位置
    private Map<Integer,Integer> inorderMap = new HashMap<>();
    // 前序遍历数组
    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;

        // 构建中序遍历的值到索引的映射
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }

        // 调用递归函数构建整颗树
        return buildTreeRecursive(0,preorder.length-1,0,inorder.length-1);
    }

    private TreeNode buildTreeRecursive(int preLeft, int preRight, int inLeft, int inRight){
        // 递归终止条件：如果前序遍历区间为空，返回null
        if(preLeft > preRight){
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
        root.left = buildTreeRecursive(
                preLeft + 1,
                preLeft + leftSubtreeSize,
                inLeft,
                rootIndexInInorder - 1);

        // 递归构建右子树
        root.right = buildTreeRecursive(
                preLeft + leftSubtreeSize + 1,
                preRight,
          rootIndexInInorder + 1,
                inRight
        );

        return root;
    }

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
}
//leetcode submit region end(Prohibit modification and deletion)

}
