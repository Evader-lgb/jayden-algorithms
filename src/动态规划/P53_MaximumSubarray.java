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
// Related Topics 数组 分治 动态规划 👍 7164 👎 0


package 动态规划;

/**
 * 最大子数组和
 * @author Jayden
 * @date 2025-10-15 09:44:41
 */
public class P53_MaximumSubarray{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P53_MaximumSubarray().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        // 边界情况处理：如果数组为空，返回0
        if(nums == null || nums.length == 0){
            return 0;
        }

        // 初始化当前子数组和 和 全局最大和
        // 从第一个元素开始，避免空子数组的情况
        int currSum = nums[0];
        int maxSum = nums[0];

        // 从第二个元素开始遍历数据
        for (int i = 1; i < nums.length; i++) {
            // 步骤1:更新当前子数组和
            // 选择：要么将当前元素加入之前子数组，要么从当前元素重新开始
            currSum = Math.max(nums[i],currSum + nums[i]);

            // 步骤2:更新全局最大和
            // 比较当前子数组和与已知的最大全局和，取较大值
            maxSum = Math.max(maxSum,currSum);
        }

        // 返回全局最大子数组和
        return maxSum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
