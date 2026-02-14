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
// Related Topics 数学 字符串 模拟 👍 1468 👎 0


package jayden2026._0214;

/**
 * 字符串相乘
 * @author Jayden
 * @date 2026-02-14 11:11:31
 */
public class P43_MultiplyStrings{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P43_MultiplyStrings().new Solution();
          String num1 = "123";
          String num2 = "456";
         System.out.println(solution.multiply(num1, num2));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String multiply(String num1, String num2) {
        // 初始化返回值
        Integer res = 0;

        // 边界条件判断
        if (num1.isBlank() || num2.isBlank()){
            return res.toString();
        }

        // 初始化当前位应该乘以的倍数，初始值为1
        int k = 1;
        int m = num1.length() - 1;
        int n = num2.length() - 1;

        // 模拟乘法的过程
        char[] charArray1 = num1.toCharArray();
        char[] charArray2 = num2.toCharArray();
        for (int i = m; i >= 0; i--) {
            // 当前计算的结果
            int currSum = 0;
            int iNum = charArray1[i] - '0';
            // 进位
            int innerK = 1;
            for (int j = n; j >= 0; j--) {
                int jNum = charArray2[j] - '0';
                int curr = jNum * iNum;
                currSum = currSum + curr * innerK;
                innerK*=10;
            }

            // 每次增加应该乘的倍数
            res = currSum * k + res;
            k*=10;
        }

        return res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
