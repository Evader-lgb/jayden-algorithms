//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 测试用例的答案是一个 32-位 整数。 
//
// 请注意，一个只包含一个元素的数组的乘积是这个元素的值。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -10 <= nums[i] <= 10 
// nums 的任何子数组的乘积都 保证 是一个 32-位 整数 
// 
//
// Related Topics 数组 动态规划 👍 2497 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 乘积最大子数组
 * @author Jayden
 * @date 2025-10-31 14:46:23
 */
public class P152_MaximumProductSubarray{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P152_MaximumProductSubarray().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0){
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            maxProd = Math.max(nums[i],nums[i] * maxProd);
            minProd = Math.min(nums[i],nums[i] * minProd);

            result = Math.max(result,maxProd);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
