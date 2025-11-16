//请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
// 函数 myAtoi(string s) 的算法如下： 

// 空格：读入字符串并丢弃无用的前导空格（" "） 
// 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。 
// 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。 
// 舍入：如果整数数超过 32 位有符号整数范围 [−2³¹, 231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2³¹ 的整数应
//该被舍入为 −2³¹ ，大于 231 − 1 的整数应该被舍入为 231 − 1 。 

// 返回整数作为最终结果。
// 示例 1：
// 输入：s = "42" 
//
// 输出：42
// 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。 
//
//带下划线线的字符是所读的内容，插入符号是当前读入位置。
//第 1 步："42"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："42"（读入 "42"）
//           ^
//
// 示例 2： 
//
// 输入：s = " -042"
// 
// 输出：-42
//
// 解释： 
//
//第 1 步："   -042"（读入前导空格，但忽视掉）
//            ^
//第 2 步："   -042"（读入 '-' 字符，所以结果应该是负数）
//             ^
//第 3 步："   -042"（读入 "042"，在结果中忽略前导零）
//               ^
// 示例 3： 
//
// 输入：s = "1337c0d3"
//
// 输出：1337 
//
// 解释： 
//
//第 1 步："1337c0d3"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："1337c0d3"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："1337c0d3"（读入 "1337"；由于下一个字符不是一个数字，所以读入停止）
//             ^
//
// 示例 4： 
//
// 输入：s = "0-1"
// 
// 输出：0
//
// 解释： 
//
//第 1 步："0-1" (当前没有读入字符，因为没有前导空格)
//         ^
//第 2 步："0-1" (当前没有读入字符，因为这里不存在 '-' 或者 '+')
//         ^
//第 3 步："0-1" (读入 "0"；由于下一个字符不是一个数字，所以读入停止)
//          ^
// 示例 5： 
//
// 输入：s = "words and 987"
//
// 输出：0
// 解释： 
//
//
//
// 0 <= s.length <= 200
// s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成 
// 
// Related Topics 字符串 👍 1925 👎 0

package 字符串.复习旧;

/**
 * 字符串转换整数 (atoi)
 * @author Jayden
 * @date 2025-11-05 18:46:56
 */
public class P8_StringToIntegerAtoi {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P8_StringToIntegerAtoi().new Solution();
//         System.out.println(solution.myAtoi2("   -042"));
         System.out.println(solution.myAtoi3("-+12"));
         System.out.println(solution.myAtoi3(" "));
         System.out.println(solution.myAtoi3("2147483646"));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * TODO 错题
     * // 空格：读入字符串并丢弃无用的前导空格（" "） 
     * // 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
     * // 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
     * // 舍入：如果整数数超过 32 位有符号整数范围 [−2³¹, 231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2³¹ 的整数应
     * //该被舍入为 −2³¹ ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        
        // 处理空格
        int start = 0;
        while (start<s.length() && s.charAt(start) == ' '){
            start++;
        }
        
        // 处理符号位
        int fh = 0;
        if (s.charAt(start+1) == '-'){
            fh = 1;
            start++;
        }
        
        // 结果
        StringBuilder result = new StringBuilder();
        
        // 处理转换
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)){
                break;
            }
            result.append(c);
        }
        
        // 判断舍入
        String string = result.toString();
        int i = Integer.parseInt(string);
        return fh * i;
    }

    // TODO 错题2
    public int myAtoi2(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        // 处理空格
        int start = 0;
        while (start<s.length() && s.charAt(start) == ' '){
            start++;
        }

        // 处理符号位
        int fh = 1;
        if (s.charAt(start) == '-'){
            fh = -1;
            start++;
        }
        if(s.charAt(start) == '+'){
            start++;
        }

        // 结果
        int result = 0;

        // 处理转换
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)){
                break;
            }

            // 检查溢出
            if(result > (Integer.MAX_VALUE - c) / 10){
                return fh == 1 ? Integer.MAX_VALUE:Integer.MIN_VALUE;
            }

            result = result * 10 + (c - '0');
        }

        // 判断舍入
        return fh * result;
    }

    public int myAtoi3(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        s = s.trim();
        if(s.isEmpty()){
            return 0;
        }

        // 处理空格
        int start = 0;
        while (start<s.length() && s.charAt(start) == ' '){
            start++;
        }

        // 处理符号位
        int fh = 1;
        if (s.charAt(start) == '-' || s.charAt(start) == '+'){
            fh = s.charAt(start) == '-' ? -1 : 1;
            start++;
        }

        // 结果
        int result = 0;

        // 处理转换
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)){
                break;
            }

            // 检查溢出
            if(result >= (Integer.MAX_VALUE - (c - '0')) / 10){
                return fh == 1 ? Integer.MAX_VALUE:Integer.MIN_VALUE;
            }

            result = result * 10 + (c - '0');
        }

        // 判断舍入
        return fh * result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
