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


package jayden2026._0215;

/**
 * 字符串相乘
 * @author Jayden
 * @date 2026-02-15 07:03:45
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
        // 边界判断
        if(num1.isBlank() || num2.isBlank() || num1.equals("0") || num2.equals("0")){
            return "0";
        }

        // 两数的边界
        int m = num1.length() - 1;
        int n = num2.length() - 1;

        // 用于暂存结果的数组
        int[] resultArray = new int[num1.length() + num2.length()];

        // 循环模拟相乘
        for (int i = m;i>=0;i--){
            int mValue = num1.charAt(i) - '0';
            for (int j = n;j>=0;j--){
                int nValue = num2.charAt(j) - '0';

                // 2数相乘
                int product = mValue * nValue;

                // 当前位跟进位
                int pos1 = i + j;
                int pos2 = i + j + 1;

                int sum = product + resultArray[pos2];

                resultArray[pos2] = sum % 10;
                resultArray[pos1] += sum / 10;
            }
        }

        // 排除前面的0
        int start = 0;
        while (start <= resultArray.length && resultArray[start] == 0){
            start++;
        }

        // 结果输出
        StringBuilder res = new StringBuilder();
        for (int i=start;i<resultArray.length;i++){
            res.append(resultArray[i]);
        }

        return res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
