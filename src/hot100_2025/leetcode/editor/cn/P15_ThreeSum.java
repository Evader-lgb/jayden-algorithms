//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != 
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 7856 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * @author Jayden
 * @date 2026-02-01 09:04:24
 */
public class P15_ThreeSum{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P15_ThreeSum().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null){
            return res;
        }

        int length = nums.length;
        if (length < 3){
            return res;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            // 第一个数都大于0了。后续一定不可能小于0
            if (curr > 0){
                break;
            }
            // 如果前一个数跟现在数一样，防止重复，无需再遍历一次了
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            // 初始化双指针，这里排序是精髓
            int start = i + 1;
            int end = length -1;
            while (start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == 0){
                    res.add(Arrays.asList(nums[i],nums[start],nums[end]));
                    // TODO 去重没做、后面的边界也没做好l<r
                    while (start < end &&nums[start] == nums[start + 1]){
                        start ++;
                    }
                    while (start < end && nums[end] == nums[end-1]){
                        end--;
                    }

                    start++;
                    end--;
                } else if (sum > 0) {
                    end--;
                }else {
                    start++;
                }
            }

        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
