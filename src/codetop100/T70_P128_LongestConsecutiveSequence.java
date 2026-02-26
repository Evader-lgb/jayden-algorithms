//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,0,1,2]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// Related Topics 并查集 数组 哈希表 👍 2665 👎 0


package codetop100;

import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列
 * @author Jayden
 * @date 2025-10-29 16:23:46
 */
public class T70_P128_LongestConsecutiveSequence {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T70_P128_LongestConsecutiveSequence().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int result = 0;

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        for (int num : numSet) {
            if(!numSet.contains(num -1)){
                int currNum = num;
                int currMax = 1;
                while (numSet.contains(currNum+1)){
                    currNum++;
                    currMax++;
                }

                result = Math.max(result,currMax);
            }
        }


        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
