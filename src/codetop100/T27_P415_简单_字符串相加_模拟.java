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


package codetop100;

/**
 * 字符串相加
 * @author Jayden
 * @date 2026-02-15 06:49:36
 */
public class T27_P415_简单_字符串相加_模拟 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T27_P415_简单_字符串相加_模拟().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addStrings(String num1, String num2) {
        // 定义返回值
        StringBuilder res = new StringBuilder();

        // 计算两数的长度
        int m = num1.length() - 1;
        int n = num2.length() - 1;

        // 进位数字
        int k = 0;

        // 模拟两数相加
        while (m>=0 || n>=0){
            int mValue = m >=0 ? num1.charAt(m) - '0' : 0;
            int nValue = n >=0 ? num2.charAt(n) - '0' : 0;

            int sum = mValue + nValue + k;

            int pos1 = sum % 10;
            k = sum / 10;
            res.append(pos1);

            m--;
            n--;
        }

        // 如果刚好最后一次相加为1
        if (k == 1){
            res.append(1);
        }

        // 返回结果
        return res.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
