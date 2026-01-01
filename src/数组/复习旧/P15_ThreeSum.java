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


package 数组.复习旧;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * @author Jayden
 * @date 2025-10-12 14:57:09
 */
public class P15_ThreeSum {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P15_ThreeSum().new Solution();

          // [-1,0,1,2,-1,-4]
         int[] nums = new int[]{
                 -1,0,1,2,-1,-4
         };
         solution.threeSum(nums);
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        /**
         * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
         * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
         *
         * 分析：
         * 1. 不能重复用
         * 2. 三个数的和为0
         *
         * @param nums
         * @return
         */
            public List<List<Integer>> threeSum(int[] nums) {
            // 初始化返回值
            List<List<Integer>> result = new ArrayList<>();

            // 边界值处理
            if (nums.length < 3){
                return result;
            }

            // 数组排序，配合双指针求和
            Arrays.sort(nums);

            // 遍历数组开始处理
            for (int first = 0; first < nums.length - 2; first++) {
                // TODO 这里排序后会变成-4,-1,-1,0,1,2所以会导致重复
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }

                // 算出第一个数的值
                int firstNum = nums[first];

                // 算出剩下2个数需要的和，通过双指针来处理
                int otherSum = -firstNum;

                // 定义当前的双指针
                int start = first + 1;
                int end = nums.length - 1;
                while (start < end){
                    if (nums[start] + nums[end] == otherSum){
                        result.add(Arrays.asList(nums[start],nums[end],firstNum));
                        // TODO 这里不加start < end会指针越界
                        while (start < end && nums[start] == nums[start + 1]){
                            start++;
                        }
                        while (start < end && nums[end] == nums[end -1]){
                            end--;
                        }
                        start++;
                        end--;
                    } else if (nums[start] + nums[end] > otherSum) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }

            return result;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
