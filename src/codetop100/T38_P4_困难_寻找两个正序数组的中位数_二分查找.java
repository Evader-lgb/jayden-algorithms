//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 7727 👎 0


package codetop100;

/**
 * 寻找两个正序数组的中位数
 * @author Jayden
 * @date 2025-10-22 19:19:58
 */
public class T38_P4_困难_寻找两个正序数组的中位数_二分查找 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T38_P4_困难_寻找两个正序数组的中位数_二分查找().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 步骤一：保证第一个数组是较短的数组，这样二分查找的效率更高
        if(nums1.length > nums2.length){
           return findMedianSortedArrays(nums2,nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        // 步骤二：在较短的数组nums1上进行二分查找
        int left = 0;
        int right = m;
        while(left<=right){
            // 在nums1中选择分割点i
            int i = (left + right) / 2;
            // 在nums2中对应的分割点j，使得左半部分元素 = 右半部分元素总和（或差1）
            int j = (m+n+1) /2 - i;

            // 步骤三：处理边界情况，定义4个关键值
            // nums1左半部分的最大值：如果i=0，则没有左半部分，设为最小整数
            int nums1LeftMax = (i == 0) ?Integer.MIN_VALUE : nums1[i-1];
            // nums1右半部分的最小值（如果i=m，则没有右半部分，设为最大整数）
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            // nums2左半部分的最大值（如果j=0，则没有左半部分，设为最小整数）
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j-1];
            // nums2右半部分的最小值（如果j=n，则没有右半部分，设为最大整数）
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 步骤4：检查当前分割是否满足条件
            if(nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin){
                // 找到了正确的分割位置
                if ((m + n) % 2 == 0) {
                    // 偶数情况
                    return (Math.max(nums1LeftMax, nums2LeftMax) +
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                } else {
                    // 奇数情况
                    return Math.max(nums1LeftMax, nums2LeftMax);
                }
            } else if (nums1LeftMax > nums2RightMin) {
                // nums1的左半部分大，需要向左移动分割点
                right = i -1;
            }else {
                // nums1的左半部分小，需要向左移动分割点
                left = i + 1;
            }
        }

        return 0.0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
