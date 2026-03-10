//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
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
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 990 👎 0


package codetop100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 难度：中等
 * 二叉树的锯齿形层序遍历
 *
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/
 * @author Jayden
 * @date 2025-10-27 08:39:27
 */
public class T19_P103_中等_二叉树的锯齿形层序遍历_BFS {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T19_P103_中等_二叉树的锯齿形层序遍历_BFS().new Solution();
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;

        while(!queue.isEmpty()){

            List<Integer> currList = new ArrayList<>();
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = queue.poll();

                if (level % 2 == 0){
                    currList.add(currNode.val);
                }else {
                    currList.add(0, currNode.val);
                }

                if(currNode.left != null){
                    queue.offer(currNode.left);
                }

                if (currNode.right != null){
                    queue.offer(currNode.right);
                }
            }

            level++;
            result.add(currList);
        }

        return result;
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

    public List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
        // 初始化返回值
        List<List<Integer>> res = new ArrayList<>();

        // 边界判断
        if (root == null){
            return res;
        }

        // 初始化层级用于实现锯齿形
        int level = 0;

        // 初始化队列，用于层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 遍历的终止条件，队列为空
        while (!queue.isEmpty()){
            List<Integer> cureList = new ArrayList<>();
            int size = queue.size();
            // 进行单层的遍历
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();

                // 数据放入当前层
                if (level % 2 == 0){
                    cureList.add(poll.val);
                }else {
                    cureList.add(0,poll.val);
                }

                if (poll.left != null){
                    queue.offer(poll.left);
                }

                if (poll.right != null){
                    queue.offer(poll.right);
                }
            }

            level++;
            res.add(cureList);
        }

        // 返回结果
        return res;
    }
//leetcode submit region end(Prohibit modification and deletion)

}
}
