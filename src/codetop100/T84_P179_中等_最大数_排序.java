//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。 
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 10⁹ 
// 
//
// Related Topics 贪心 数组 字符串 排序 👍 1368 👎 0


package codetop100;

import java.util.Arrays;
import java.util.Objects;

/**
 * 最大数
 * @author Jayden
 * @date 2025-10-19 17:04:05
 */
public class T84_P179_中等_最大数_排序 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T84_P179_中等_最大数_排序().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String largestNumber(int[] nums) {
        String[] strResult = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strResult[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strResult,(a,b) -> {
            String sum1 = a + b;
            String sum2 = b + a;
            return sum2.compareTo(sum1);
        });

        if(Objects.equals(strResult[0], "0")){
            return "0";
        }

        StringBuilder result = new StringBuilder();
        for (String str : strResult) {
            result.append(str);
        }
        return result.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
