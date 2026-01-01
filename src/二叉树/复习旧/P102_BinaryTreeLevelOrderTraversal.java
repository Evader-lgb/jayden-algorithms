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


package 二叉树.复习旧;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * @author Jayden
 * @date 2025-10-16 18:16:55
 */
public class P102_BinaryTreeLevelOrderTraversal {
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

    /**
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     *
     * 感觉可以用队列来做。先把root入队，然后只要队列不为空就一直循环。
     * 循环里的逻辑是弹出第一个元素，如果有左右子树加入到队列里
     * 每次循环都有有个列表来记录
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }

        // 初始化队列并将root节点入队
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 遍历进行层序遍历
        while (!queue.isEmpty()){
            List<Integer> current = new ArrayList<>();

            // TODO 不这么写，队列的长度一直是变化的没办法处理
            int size = queue.size();

            // TODO:没有想到这个来控制每一层
//            for (int i = 0; i < queue.size(); i++) {
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                current.add(poll.val);

                // 这里感觉会有问题，分不了层，会一直循环下去
                if (poll.left != null){
                    queue.offer(poll.left);
                }

                if(poll.right != null){
                    queue.offer(poll.right);
                }
            }

            res.add(current);
        }

        return res;
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
