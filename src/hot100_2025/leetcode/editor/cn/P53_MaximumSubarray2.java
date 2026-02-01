//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 子数组 是数组中的一个连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [5,4,-1,7,8]
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
//
// Related Topics 数组 分治 动态规划 👍 7251 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 最大子数组和
 * @author Jayden
 * @date 2026-02-01 10:22:26
 */
public class P53_MaximumSubarray2 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P53_MaximumSubarray2().new Solution();
          int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
         System.out.println(solution.maxSubArray(nums));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null){
            return 0;
        }
        if(nums.length <= 1){
            return nums[0];
        }

        int maxRes = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int sum = Math.max(dp[i-1]+nums[i],nums[i]);
            // TODO 自己写的，没按题解写，问题是dp的值没有赋值
            dp[i] = sum;
            maxRes = Math.max(maxRes,sum);
        }
        return maxRes;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
