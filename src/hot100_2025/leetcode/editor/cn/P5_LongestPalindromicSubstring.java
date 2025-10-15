//给你一个字符串 s，找到 s 中最长的 回文 子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 双指针 字符串 动态规划 👍 7845 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 最长回文子串
 * @author Jayden
 * @date 2025-10-14 20:03:13
 */
public class P5_LongestPalindromicSubstring{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P5_LongestPalindromicSubstring().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        // 边界情况处理：如果为空字符串或长度为0，直接返回空字符串
        if(s == null || s.isEmpty()){
            return "";
        }

        // 初始化变量，记录最长回文子串的起始位置和结束位置
        int start = 0;
        int end = 0;

        // 遍历字符串中的每个字符，作为中心点进行扩展
        for (int i = 0; i < s.length(); i++) {
            // 情况1:以当前字符为中心，向两边扩展（奇数长度回文）
            int len1 = expandAroundCenter(s,i,i);

            // 情况2:以当前字符和下一个字符为中心，向两边扩展（偶数长度回文）
            int len2 = expandAroundCenter(s,i,i+1);

            // 取2种方式中的较大长度
            int len = Math.max(len1,len2);

            // 如果当前找到的回文长度大于已知最大的回文长度
            if(len > end - start){
                start = i - (len -1) / 2;
                end = i + len / 2;
            }
        }

        // 根据起始位置和结束位置返回最长回文子串
        // substring方法截取从start到end+1的子串
        return s.substring(start, end + 1);
    }

    /**
     * 中心扩展函数：从给定左右中心向两边扩展，寻找回文子串
     * @param s 输入字符串
     * @param left 左中心位置
     * @param right 右中心位置
     * @return 回文子串的长度
     */
    private int expandAroundCenter(String s, int left, int right){
        // 当左右指针在有效范围内且指向的字符串相等时，继续向两边扩展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            // 向左移动左指针
            left--;
            // 向右移动右指针
            right++;
        }

        // 返回回文子串的长度
        // 注意：循环结束时，left和right指向的是不满足回文条件的位置
        // 所以回文子串的实际长度是（right - left - 1）
        return right - left - 1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
