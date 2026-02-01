//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 你必须在 不使用任何内置函数 的情况下解决问题，时间复杂度为 O(nlog(n))，并且空间复杂度尽可能小。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
//解释：数组排序后，某些数字的位置没有改变（例如，2 和 3），而其他数字的位置发生了改变（例如，1 和 5）。
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
//解释：请注意，nums 的值不一定唯一。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
// 
//
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 1192 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.Random;

/**
 * 排序数组
 * @author Jayden
 * @date 2026-02-01 10:57:24
 */
public class P912_SortAnArray{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P912_SortAnArray().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length -1);
        return nums;
    }

    public void quickSort(int[] nums,int left,int right){
        // 这里是递归的出口，如果只有一个数的时候，会相等。应该不会有大于的情况
        if (left >= right){
            return;
        }

        int randomIndex = left + (int)(Math.random() * (right - left + 1));
        swap(nums,randomIndex,right);
        int pivot = nums[right];

        int lt = left;
        int gt = right;
        int i = left;

        while (i <= gt){
            if (nums[i] < pivot){
                swap(nums,i,lt);
                lt++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums,i,gt);
                gt--;
            }else {
                i++;
            }
        }

        // TODO 这里写成了固定0不对
//        quickSort(nums,0,lt -1);
        quickSort(nums,left,lt -1);
        quickSort(nums,gt + 1, right);
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
