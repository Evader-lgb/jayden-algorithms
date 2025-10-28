//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。 
//
// 
//
// 示例 1: 
//
// 
//输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 
//输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 200 
// num1 和 num2 只能由数字组成。 
// num1 和 num2 都不包含任何前导零，除了数字0本身。 
// 
//
// Related Topics 数学 字符串 模拟 👍 1451 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串相乘
 * @author Jayden
 * @date 2025-10-28 08:12:20
 */
public class P43_MultiplyStrings{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P43_MultiplyStrings().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String multiply(String num1, String num2) {
        if(num2.equals("0") || num1.equals("0")){
            return "0";
        }
        
        int num1Len = num1.length();
        int num2Len = num2.length();

        int[] result = new int[num2Len+num1Len];

        for (int i = num1Len - 1; i >=0 ; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = num2Len - 1;j>=0;j--){
                int digit2 = num2.charAt(j) - '0';

                int product = digit1 * digit2;

                int pos1 = i + j;
                int pos2 = i + j + 1;

                int sum = product + result[pos2];
                result[pos2] = sum % 10;
                result[pos1] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (start < result.length && result[start] == 0) {
            start++;
        }


        for (int i = start; i < result.length; i++) {
            sb.append(result[i]);
        }

        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
