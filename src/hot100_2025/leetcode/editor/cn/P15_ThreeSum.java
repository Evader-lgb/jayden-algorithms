//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != 
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 7699 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * @author Jayden
 * @date 2025-10-12 14:57:09
 */
public class P15_ThreeSum{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P15_ThreeSum().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 1. 理解核心思想
     * 为什么排序？
     *
     * 排序后可以方便地使用双指针
     *
     * 便于跳过重复元素，避免重复解
     *
     * 可以利用有序性优化搜索过程
     *
     * 双指针如何工作？
     *
     * 左指针向右移动增大和
     *
     * 右指针向左移动减小和
     *
     * 根据当前和与目标值的关系调整指针
     */
    class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 创建结果列表，用于存储所有满足条件的三元组
        List<List<Integer>> result = new ArrayList<>();

        // 边界情况处理：如果数组长度小于3，直接返回空结果
        if (nums.length < 3) {
            return result;
        }

        // 步骤1：对数组进行排序，这是双指针解法的基础
        // 排序后可以方便地使用双指针，并且便于去重
        Arrays.sort(nums);

        // 步骤2：遍历数组，将每个元素作为第一个固定数
        // 只需要遍历到倒数第三个元素，因为需要三个数
        for (int first = 0; first < nums.length - 2; first++) {
            // 步骤3：去重处理 - 如果当前数与上一个数相同，跳过以避免重复解
            // 注意：first > 0 确保不会越界
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            // 步骤4：初始化双指针
            // left指针从first+1开始，right指针从数组末尾开始
            int left = first + 1;
            int right = nums.length - 1;

            // 计算目标值：我们需要找到两个数，它们的和等于 -nums[first]
            // 因为 nums[first] + nums[left] + nums[right] = 0
            // 所以 nums[left] + nums[right] = -nums[first]
            int target = -nums[first];

            // 步骤5：使用双指针在剩余数组中寻找另外两个数
            while (left < right) {
                // 计算当前两个指针指向的数的和
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    // 步骤6：找到满足条件的三元组，加入结果列表
                    result.add(Arrays.asList(nums[first], nums[left], nums[right]));

                    // 步骤7：移动指针并跳过重复元素
                    // 移动left指针直到遇到不同的数，避免重复解
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 移动right指针直到遇到不同的数，避免重复解
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 步骤8：同时移动左右指针，继续寻找其他解
                    // 注意：这里必须在去重后移动，否则可能会跳过一些解
                    left++;
                    right--;
                } else if (sum < target) {
                    // 步骤9：如果和小于目标值，需要增大和
                    // 因为数组已排序，所以移动left指针向右可以增大和
                    left++;
                } else {
                    // 步骤10：如果和大于目标值，需要减小和
                    // 因为数组已排序，所以移动right指针向左可以减小和
                    right--;
                }
            }
        }

        // 返回所有找到的不重复的三元组
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
