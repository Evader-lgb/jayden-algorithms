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
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 2892 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 数组中的第K个最大元素
 * @author Jayden
 * @date 2026-01-31 23:12:14
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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }

        return quickSelect(list,k);
    }

    /**
     * 1. 随机一个基准数
     * 2. 分为小于，等于，大于来处理
     * @param list
     * @param k
     * @return
     */
    public int quickSelect(List<Integer> list,int k){
        Random random = new Random();
        int i = list.get(random.nextInt(list.size()));

        List<Integer> small = new ArrayList<>();
        List<Integer> big = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();

        for (Integer integer : list) {
            if (integer> i){
                big.add(integer);
            } else if (integer < i) {
                small.add(integer);
            }else {
                equal.add(integer);
            }
        }

        if (k <= big.size()){
            return quickSelect(big,k);
        }else if (list.size() - small.size() < k){
//            return quickSelect(small,k - list.size() + small.size());
            // TODO 感觉这个可读性更高一点
            return quickSelect(small,k - (big.size() + equal.size()));
        }

        return i;
    }



}
//leetcode submit region end(Prohibit modification and deletion)

}
