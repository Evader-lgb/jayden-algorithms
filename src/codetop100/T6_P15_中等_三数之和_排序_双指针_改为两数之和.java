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
// Related Topics 数组 双指针 排序 👍 7882 👎 0


package codetop100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 难度：中等
 * 三数之和
 * https://leetcode.cn/problems/3sum/description/
 *
 * 解题思路：
 * 1. 排序 + 双指针 + 转化为两数之和
 * 2. 排重在2层循环都需要做
 * 3. 排序非常的重要
 *
 *
 * @author Jayden
 * @date 2026-02-16 11:08:46
 */
public class T6_P15_中等_三数之和_排序_双指针_改为两数之和 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T6_P15_中等_三数之和_排序_双指针_改为两数之和().new Solution();
          int[] nums = new int[]{-1,0,1,2,-1,-4};
//          solution.threeSum(nums);
         int[] nums2 = new int[]{-100,-70,-60,110,120,130,160};
          solution.threeSum2(nums2);
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 初始化返回值
        List<List<Integer>> res = new ArrayList<>();

        // 边界条件判断
        if (nums == null || nums.length == 0){
            return res;
        }

        // 对数组进行排序，简化处理逻辑
        Arrays.sort(nums);

        // 2层循环处理数据
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 因为经过排序，如果第一位都是正数，相加的和一定不等于0
            if (num > 0){
                break;
            }

            // TODO 这里少处理了重复的逻辑
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            // 转化为2数之和
            int target = -num;
//            int left = 0;
            // TODO 这里逻辑不懂
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right && left < nums.length){
                int sum = nums[left] + nums[right];
                if (sum > target){
                    right--;
                } else if (sum < target) {
                    left++;
                }else {
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    // TODO 去重的逻辑
                    while (left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    // TODO在这里忘记了
                    left++;
                    right--;
                }


            }
        }

        // 返回结果
        return res;
    }

    /**
     核心思路：改成2数之和处理
     每个元素都需要做下去重、两数之和用双指针
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        // 返回结果初始化
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return res;
        }

        // 对数组进行排序
        Arrays.sort(nums);

        // 外层遍历
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            if(num > 0){
                break;
            }

            // 外层判重复
            if(i> 0 && nums[i] == nums[i-1]){
                continue;
            }

            // 双指针初始化
            int left = i + 1;
            int right = nums.length -1;

            // 内层遍历
            while(left < right){
                // 移动左右指针移动求符合条件的组合
                if(-num == nums[left] + nums[right]){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    // 内层判重
                    if(left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    if(left<right && nums[right] == nums[right-1]){
                        right--;
                    }

                    // 左右指针移动
                    right--;
                    left++;
                }else if(-num > nums[left] + nums[right]){
                    left++;
                }else {
                    right--;
                }
            }

        }

        // 返回结果
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
