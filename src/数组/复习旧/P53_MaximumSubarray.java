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


package 数组.复习旧;

/**
 * 最大子数组和
 * @author Jayden
 * @date 2025-10-15 09:44:41
 */
public class P53_MaximumSubarray {
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
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 子数组是数组中的一个连续部分。
     *
     *
     * 考察的应该是双指针，有一个变量存当前的最大值即可
     * // TODO：思路就错了，实际解法用的是动态规划。Kadane算法是解决最大子数组的算法，卡内基梅隆大学教授提出的一种算法思想
     * // Kadane算法不仅是解决最大子数组和问题的最优算法，更是动态规划空间优化的经典范例。它体现了算法设计中"用时间换空间"的思想
     * ，通过巧妙的变量设计，在保持O(n)时间复杂度的同时，将空间复杂度从O(n)降低到O(1)。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            // TODO:这里其实是不理解的，随便写了个num>0
            // TODO：这是Kadane算法的状态转移方程实现：
            //
            //当sum > 0时：继续累加当前元素，因为正数加上新元素可能会变得更大
            //
            //当sum <= 0时：从当前元素重新开始，因为负数/零加上当前元素不会比当前元素本身更大
            if (sum > 0){
                sum += num;
            }else {
                sum = num;
            }
            ans = Math.max(ans,sum);
        }
        return ans;
    }
}

}
