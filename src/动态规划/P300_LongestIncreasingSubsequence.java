//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶： 
//
// 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
//
// Related Topics 数组 二分查找 动态规划 👍 4085 👎 0


package 动态规划;

/**
 * 最长递增子序列
 * @author Jayden
 * @date 2025-10-16 18:37:38
 */
public class P300_LongestIncreasingSubsequence{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P300_LongestIncreasingSubsequence().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
        // 边界情况处理：如果为空或长度为0，直接返回0
        if (nums == null || nums.length == 0){
            return 0;
        }

        // 步骤一：创建dp数组，dp[i]表示以nums[i]结尾的最长递增子序列长度
        int[] dp = new int[nums.length];

        // 步骤二：初始化dp数组，每个位置至少可以包含自己，所以初始化为1
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        // 步骤三：初始化最大长度为1（至少有1个元素）
        int maxLength = 1;

        // 步骤四：遍历数组，计算每个位置的最长递增子序列长度
        for (int i = 1; i < nums.length; i++) {
            // 遍历i之前的所有元素
            for (int j = 0; j < i; j++){
                // 如果当前元素大于之前的某个元素，说明可以形成递增序列
                if (nums[i] > nums[j]){
                    // 状态转移： dp[i] = max(dp[i],dp[j] + 1)
                    // 意思是：以i结尾的LIS长度，要么保持原值，要么是j位置的LIS长度+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            // 步骤五：更新全局最大长度
            maxLength = Math.max(maxLength,dp[i]);
        }

        // 步骤六：返回最长递增子序列的长度
        return maxLength;
    }


    public int lengthOfLIS2(int[] nums) {
        // 边界情况处理：如果数组为空或长度为0，直接返回0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 步骤1：创建dp数组，dp[i]表示以nums[i]结尾的最长递增子序列长度
        int[] dp = new int[nums.length];

        // 步骤2：初始化dp数组，每个位置至少可以包含自己，所以初始化为1
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        // 步骤3：初始化最大长度为1（至少有一个元素）
        int maxLength = 1;

        // 步骤4：遍历数组，计算每个位置的最长递增子序列长度
        for (int i = 1; i < nums.length; i++) {
            // 遍历i之前的所有元素
            for (int j = 0; j < i; j++) {
                // 如果当前元素大于之前的某个元素，说明可以形成递增序列
                if (nums[i] > nums[j]) {
                    // 状态转移：dp[i] = max(dp[i], dp[j] + 1)
                    // 意思是：以i结尾的LIS长度，要么保持原值，要么是j位置的LIS长度+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 步骤5：更新全局最大长度
            maxLength = Math.max(maxLength, dp[i]);
        }

        // 步骤6：返回最长递增子序列的长度
        return maxLength;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
