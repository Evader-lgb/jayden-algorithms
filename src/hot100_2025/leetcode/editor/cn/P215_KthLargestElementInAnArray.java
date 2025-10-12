//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4], k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6], k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 2831 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 数组中的第K个最大元素
 * @author Jayden
 * @date 2025-10-12 12:51:47
 */
public class P215_KthLargestElementInAnArray{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P215_KthLargestElementInAnArray().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        return quickSelect(numList,k);
    }

    public int quickSelect(List<Integer> nums,int k){
        // 设定基准值
        Random random = new Random();
        Integer rondom = nums.get(random.nextInt(nums.size()));

        // 对集合进行三路分区
        List<Integer> big = new ArrayList<>();
        List<Integer> dnegyu = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        for (Integer num : nums) {
            if (num > rondom){
                big.add(num);
            } else if (num.equals(rondom)) {
                dnegyu.add(num);
            } else {
                small.add(num);
            }
        }

        // 情况1：第k大元素在big列表中
        // 因为big中的元素都大于pivot，所以第k大元素如果在big中，就是big中的第k大元素
        if (k <= big.size()){
            return quickSelect(big,k);
        }

        // 情况2：第k大元素在small列表中
        // 如果k > (big.size() + equal.size())，说明第k大元素在small中
        // 在small中要找的位置是：k - (big.size() + equal.size())
        if (nums.size() - small.size() < k) {
            return quickSelect(small, k - nums.size() + small.size());
        }

        // 返回基准值
        return rondom;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
