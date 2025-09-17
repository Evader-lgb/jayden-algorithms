//给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果 target 存在返
//回下标，否则返回 -1。 
//
// 你必须编写一个具有 O(log n) 时间复杂度的算法。 
//
// 示例 1: 
//
// 
//输入: nums = [-1,0,3,5,9,12], target = 9
//输出: 4
//解释: 9 出现在 nums 中并且下标为 4
// 
//
// 示例 2: 
//
// 
//输入: nums = [-1,0,3,5,9,12], target = 2
//输出: -1
//解释: 2 不存在 nums 中因此返回 -1
// 
//
// 
//
// 提示： 
//
// 
// 你可以假设 nums 中的所有元素是不重复的。 
// n 将在 [1, 10000]之间。 
// nums 的每个元素都将在 [-9999, 9999]之间。 
// 
//
// Related Topics 数组 二分查找 👍 1792 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 二分查找
 * @author Jayden
 * @date 2025-09-17 08:50:13
 */
public class P704_BinarySearch{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P704_BinarySearch().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int middle = left + (right - left) /2;
            int result = nums[middle];
            if (target == result){
                return middle;
            } else if (target > result) {
                left = middle + 1;
            } else {
                right = middle -1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
