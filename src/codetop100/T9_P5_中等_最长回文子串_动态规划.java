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


package codetop100;

/**
 * 难度：中等
 * 最长回文子串
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 *
 * @author Jayden
 * @date 2025-10-14 20:03:13
 */
public class T9_P5_中等_最长回文子串_动态规划 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T9_P5_中等_最长回文子串_动态规划().new Solution();
          // 53、5、121、300、72、42、1143、70、322、64、152、516
         // 46、93、22、79、37

         String s = "ac";
         solution.longestPalindrome(s);
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();

        // 定义状态，dp为i-j是否是回文串
        boolean[][] dp = new boolean[n][n];

        // 初始化边界值
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 定义起始位置与最大长度
        int start = 0;
        // TODO 这里初始化为0还是1有区别吗？
        int maxLen = 1;

        // 遍历
        // 状态转移方程。dp[i-1][j-1]是回文串，并且s[i] == s[j]
        char[] charArray = s.toCharArray();
        // 1个字符串的肯定是回文，从2个字符串开始判断
        // TODO 这里代表字符的长度，所以需要等于
//        for (int i = 2; i < n; i++) {
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < n; j++) {
//                int end = i - j - 1;
                int end = i + j - 1;

                if (end >= n){
//                    continue;
                    break;
                }

                if(charArray[j] != charArray[end]){
                    dp[j][end] = false;
                }else {
                    // 如果长度小于3，那么就是回文串，
//  TODO                  if (end - j + 1 < 3){
                    if (end - j < 3){
                        dp[j][end] = true;
                    }else {
                        dp[j][end] = dp[j-1][end -1];
                    }
                }

                // TODO 这里要是dp是回文串
//                if (end - j + 1 > maxLen){
                if (dp[j][end] && end - j + 1 > maxLen){
                    start = j;
                    maxLen = end-j+1;
                }
            }
        }

        return s.substring(start,start+maxLen);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
