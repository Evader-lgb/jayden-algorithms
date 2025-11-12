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


package 字符串.复习旧;

/**
 * 字符串相乘
 * <a href="https://leetcode.cn/problems/multiply-strings/">链接</a>
 *
 * @author Jayden
 * @date 2025-10-28 08:12:20
 */
public class P43_MultiplyStrings {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P43_MultiplyStrings().new Solution();
//          String num1 = "2";
//          String num2 = "3";
//         System.out.println(solution.multiply(num1, num2));

         String num3 = "123";
         String num4 = "456";
         System.out.println(solution.multiply(num3, num4));//56088

     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 这道题没有什么花里胡哨，就是模拟乘法的过程，慢慢调试应该也是没有问题的
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        // TODO 错题 边界条件没处理好
//        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0){
//            return "";
//        }
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        }

        int[] resultArray = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';

                // 模拟2数相乘
                int product = n1 * n2;

                // TODO 不知道用数组以及这2字段含义 获取当前位跟进位
                int pos1 = i + j;
                int pos2 = i + j + 1;

                // 进位跟当前位
                int sum = product + resultArray[pos2];

                resultArray[pos2] = sum%10;
                // TODO错题，这里最开始用的=
                resultArray[pos1] += sum/10;
            }
        }

        // 去除前导零
        int start = 0;
        while (start<resultArray.length && resultArray[start] == 0){
            start++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i < resultArray.length; i++) {
            sb.append(resultArray[i]);
        }

        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
