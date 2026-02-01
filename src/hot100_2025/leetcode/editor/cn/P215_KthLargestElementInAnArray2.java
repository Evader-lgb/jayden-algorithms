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
import java.util.List;
import java.util.Random;

/**
 * 数组中的第K个最大元素
 * @author Jayden
 * @date 2026-02-01 21:13:21
 */
public class P215_KthLargestElementInAnArray2 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P215_KthLargestElementInAnArray2().new Solution();
          int[] nums= {3,2,1,5,6,4};
         System.out.println(solution.findKthLargest(nums, 2));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return quickSelect(list,k);
    }

    public int quickSelect(List<Integer> list,int k){

        int randomIndex = (int) (Math.random() * (list.size() - 1));
        int pivot = list.get(randomIndex);
//        Random random = new Random();
//        int i = list.get(random.nextInt(list.size()));

        List<Integer> small = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> big = new ArrayList<>();

        // 遍历list，填充集合
        for (Integer i : list) {
            if (i < pivot){
                small.add(i);
            } else if (i > pivot) {
                big.add(i);
            }else {
                equal.add(i);
            }
        }

        // TODO 原版本
//        if (k < big.size()){
//
//        } else if (k > list.size() - small.size()) {
//            return quickSelect(small,k - (list.size() - big.size() - equal.size()));
//        }else {
//            return pivot;
//        }
        if (k <= big.size()){
            return quickSelect(big,k);
        } else if (list.size() - small.size() < k) {
            // 这里相当减去大于跟等于的，就是小于里的第k个最大的元素
            return quickSelect(small,k - (big.size() + equal.size()));
        }

        return pivot;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
