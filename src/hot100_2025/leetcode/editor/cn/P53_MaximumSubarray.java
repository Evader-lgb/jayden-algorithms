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
// Related Topics 数组 分治 动态规划 👍 7234 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 最大子数组和
 * @author Jayden
 * @date 2026-01-09 00:13:09
 */
public class P53_MaximumSubarray{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P53_MaximumSubarray().new Solution();

          int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
          solution.maxSubArray(nums);
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 这道题产生了心得，不能光看题解，其实按模版答题是最稳的，什么省空间之类的其实不好记的
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null){
            return 0;
        }

        int result = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]);
            result = Math.max(dp[i],result);
        }

        return result;
    }

    // TODO 错误写法
    public int maxSubArrayError(int[] nums) {
        if (nums == null){
            return 0;
        }

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int currMax = Math.max(nums[i],result+nums[i]);
            result = Math.max(currMax,result);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
