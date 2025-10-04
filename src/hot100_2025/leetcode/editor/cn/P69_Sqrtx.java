//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。 
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 4
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= x <= 2³¹ - 1 
// 
//
// Related Topics 数学 二分查找 👍 1718 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * x 的平方根 
 * @author Jayden
 * @date 2025-10-04 09:36:24
 * <a href="https://leetcode.cn/problems/sqrtx/">link</a>
 */
public class P69_Sqrtx{
	 public static void main(String[] args) {
	 	 //测试代码
         System.out.println((5 / 2));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int mySqrt(int x) {
        int result = 0;

        if (x == 0){
            return result;
        }

        int left = 0;
        int right = x;
        while (left <= right){
            int middle = left + (right - left)/2;
            if((long)middle * middle <= x){
                result = middle;
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
