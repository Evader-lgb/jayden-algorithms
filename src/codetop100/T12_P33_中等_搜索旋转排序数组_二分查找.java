//整数数组 nums 按升序排列，数组中的值 互不相同 。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], 
//nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1
//,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 二分查找 👍 3326 👎 0


package codetop100;

/**
 * 难度：中等
 * 搜索旋转排序数组
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
 *
 * @author Jayden
 * @date 2026-02-26 10:25:43
 */
public class T12_P33_中等_搜索旋转排序数组_二分查找 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T12_P33_中等_搜索旋转排序数组_二分查找().new Solution();
          int[] nums = new int[]{5,1,3};
         System.out.println(solution.search(nums, 3));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        // 边界判断
        if (nums == null || nums.length ==0){
            return -1;
        }

        // 定义左右指针
        int left = 0;
        int right = nums.length - 1;

        // 循环处理二分查找，循环停止的条件是？
        while (left <= right){
            // 找出中间的index
//            int middle = (left + right) / 2;
            int middle = left + (right - left) / 2;

            // 检查等于的情况
            int num = nums[middle];
            if (target == num){
                return middle;
            }

            // [4,5,6,7,0,1,2,3]
            // 处理二分的逻辑
            // TODO 判断左半部分是否有序？
//            if (target <= num){
            if (nums[left] <= num){
                if (target >= nums[left] && target <= num){
                    right = middle - 1;
                }else {
                    left = middle + 1;
                }
            }else {
                if (target >= num && target <= nums[right]){
                    left = middle + 1;
                }else {
                    right = middle - 1;
                }
            }
        }

        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
