//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。 
//
// 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。 
//
// 
//
// 示例 1： 
//
// 
//输入：num1 = "11", num2 = "123"
//输出："134"
// 
//
// 示例 2： 
//
// 
//输入：num1 = "456", num2 = "77"
//输出："533"
// 
//
// 示例 3： 
//
// 
//输入：num1 = "0", num2 = "0"
//输出："0"
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 10⁴ 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 
//
// Related Topics 数学 字符串 模拟 👍 903 👎 0


package jayden2026._0214;

/**
 * 字符串相加
 * @author Jayden
 * @date 2026-02-14 08:38:35
 */
public class P415_AddStrings{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P415_AddStrings().new Solution();
          String num1 = "1";
          String num2 = "9";
         System.out.println(solution.addStrings(num1, num2));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addStrings(String num1, String num2) {
        // 初始化返回值
        StringBuilder res = new StringBuilder();

        // 初始化当前进位
        int num1Index = num1.length() - 1;
        int num2Index = num2.length() - 1;

        // 初始化进位
        int k = 0;

        // 模拟加法：当2个字符串都到index为0的时候就不处理了
        while (num1Index >= 0 || num2Index >= 0){
            // 当前要相加的两位数
//            int int1 = num1Index < num1.length() ? 0 : num1.charAt(num1Index);
//            int int2 = num2Index < num2.length() ? 0 : num2.charAt(num2Index);
            int int1 = num1Index < 0 ? 0 : num1.charAt(num1Index) - '0';
            int int2 = num2Index < 0 ? 0 : num2.charAt(num2Index) - '0';

            // 计算当前位的数字 0-9
            int currSum = int1 + int2 + k;

            // 计算当前位跟进位
            int curr = currSum % 10;
            k = currSum / 10;
            res.append(curr);

            // 继续往前加
            num1Index--;
            num2Index--;
        }

        // 如果循环后进位还有值，说明是刚好加到10
        if(k == 1){
            res.append(k);
        }

        return res.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
