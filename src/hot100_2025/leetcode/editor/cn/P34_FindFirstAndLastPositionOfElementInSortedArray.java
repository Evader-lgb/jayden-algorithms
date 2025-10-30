//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 👍 3132 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * @author Jayden
 * @date 2025-10-30 10:30:10
 */
public class P34_FindFirstAndLastPositionOfElementInSortedArray{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }

        // 找左边第一个
        int leftNum = getLeftTarget(nums,target);
        if (leftNum == -1){
            return new int[]{-1,-1};
        }

        // 找右边第一个
        int rightNum = getRightTarget(nums,target);

        return new int[]{leftNum,rightNum};
    }

    public int getLeftTarget(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int middle = left + (right - left) / 2;
            if (target == nums[middle]){
                if (middle == 0 || nums[middle - 1] != target){
                    return middle;
                }else {
                    right = middle - 1;
                }
            } else if (target > nums[middle]) {
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public int getRightTarget(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int middle = left + (right - left) / 2;
            if (target == nums[middle]){
                if (middle == nums.length - 1 || nums[middle + 1] != target){
                    return middle;
                }else {
                    left = middle + 1;
                }
            } else if (target > nums[middle]) {
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
