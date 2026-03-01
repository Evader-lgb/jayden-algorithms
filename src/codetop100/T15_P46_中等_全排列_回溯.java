//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
//
// Related Topics 数组 回溯 👍 3186 👎 0


package codetop100;

import java.util.ArrayList;
import java.util.List;

/**
 * 难度：中等
 * 全排列
 * https://leetcode.cn/problems/permutations/description/
 *
 * 思路：回溯
 *
 * @author Jayden
 * @date 2025-10-15 10:56:50
 */
public class T15_P46_中等_全排列_回溯 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new T15_P46_中等_全排列_回溯().new Solution();
    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            // 创建结果列表，用于存储所有的排列
            List<List<Integer>> result = new ArrayList<>();

            // 创建路径列表，用于记录当前正在构建的排列
            List<Integer> path = new ArrayList<>();

            // 创建标记数组，用于记录哪些数字已经被使用过
            boolean[] used = new boolean[nums.length];

            // 调用回溯函数，开始生成所有排列
            backtrack(nums,used,path,result);

            return result;
        }

        /**
         * 回溯函数：递归生成所有排列
         * @param nums 原始数字数组
         * @param used 标记数组，记录哪些数字已被使用
         * @param path 当前路径，记录已选择的数字
         * @param result 结果列表，存储所有完整的排列
         */
        private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result){
            // 终止条件：如果当前路径的长度等于原数组长度，说明已经形成了一个完整的排列
            if(path.size() == nums.length){
                // 将当前路径的副本添加到结果中（必须新建列表，避免后续修改影响）
                result.add(new ArrayList<>(path));
                return;
            }

            // 遍历所有数字，尝试每个可能的选择
            for (int i = 0; i < nums.length; i++) {
                // 如果当前数字已经被使用过，跳过
                if(used[i]){
                    continue;
                }

                // 做出选择：将当前数字加入路径
                path.add(nums[i]);
                // 标记当前数字为已使用
                used[i] = true;

                // 递归调用：继续选择下一个数字
                backtrack(nums,used,path,result);

                // 撤销选择：回溯的关键步骤
                // 从路径中移除最后加入的数字
                path.remove(path.size() - 1);
                // 标记当前数字为未使用，以便其他分支使用
                used[i] = false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
