//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 2195 👎 0


package 二叉树.DFS_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * @author Jayden
 * @date 2025-10-16 18:16:55
 */
public class P102_BinaryTreeLevelOrderTraversal{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P102_BinaryTreeLevelOrderTraversal().new Solution();
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 创建结果列表，用于存储每一层的节点值
        List<List<Integer>> result = new ArrayList<>();

        // 边界情况处理：如果根节点为空，直接返回空列表
        if(root == null){
            return result;
        }

        // 创建队列，用于广度优先搜索（BFS）
        // 队列中存储待处理的树节点
        Queue <TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列，作为第一层
        queue.offer(root);

        // 当队列不为空时，说明还有节点需要处理
        while (!queue.isEmpty()){
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

                currentLevel.add(currentNode.val);

                if (currentNode.left != null){
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
        }

        // 返回按层级排序的结果
        return result;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
class TreeNode{
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
