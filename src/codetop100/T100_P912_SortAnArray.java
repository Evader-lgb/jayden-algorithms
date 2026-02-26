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
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 1168 👎 0


package codetop100;

/**
 * 排序数组
 * @author Jayden
 * @date 2025-10-16 19:13:42
 */
public class T100_P912_SortAnArray {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T100_P912_SortAnArray().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        // 使用三路快速排序，对包含大量重复元素的数组更高效
        quickSortThreeWay(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 三路快速排序：将数组分为小于、等于、大于基准值的三部分
     * @param nums 数组
     * @param left 左边界
     * @param right 右边界
     */
    private void quickSortThreeWay(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        // 随机选择基准值，避免在已排序数组上出现最坏情况
        int randomIndex = left + (int)(Math.random() * (right - left + 1));
        swap(nums, randomIndex, right);
        int pivot = nums[right];

        // 初始化三个指针：
        int lt = left;      // lt指向小于pivot区域的末尾
        int gt = right;     // gt指向大于pivot区域的开始
        int i = left;       // i是当前遍历的指针

        while (i <= gt) {
            if (nums[i] < pivot) {
                // 当前元素小于pivot，交换到lt位置
                swap(nums, lt, i);
                lt++;
                i++;
            } else if (nums[i] > pivot) {
                // 当前元素大于pivot，交换到gt位置
                swap(nums, i, gt);
                gt--;
                // 注意：这里不移动i，因为交换过来的元素还没检查
            } else {
                // 当前元素等于pivot，直接移动i
                i++;
            }
        }

        // 递归排序小于pivot的部分
        quickSortThreeWay(nums, left, lt - 1);
        // 递归排序大于pivot的部分
        quickSortThreeWay(nums, gt + 1, right);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
