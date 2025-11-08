//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。 
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 1373 👎 0


package 字符串;

/**
 * 最长回文子序列
 * @author Jayden
 * @date 2025-10-25 14:52:09
 */
public class P516_LongestPalindromicSubsequence {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P516_LongestPalindromicSubsequence().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // 步骤1:创建dp数组，dp[i][j]表示s[i...j]的最长回文子序列长度
        int [][] dp = new int[n][n];

        // 步骤2:初始化对角线，单个字符串的最长回文子序列长度为1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 步骤3:从后往前遍历，因为需要用到后面的状态
        // i从最后一个字符开始往前遍历
        for(int i = n-1;i>=0;i--){
            // j从i+1开始往后遍历，确保i < j
            for (int j = i+1;j<n;j++){
                // 步骤4:状态转移
                if(s.charAt(i) == s.charAt(j)){
                    // 情况1:首尾字符串相等
                    // 最长回文子序列 = 中间部分的 + 2（首尾是2个字符）
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    // 情况2:首位字符不相等
                    // 最大回文子序列 = 舍弃i或者舍弃j的最大值
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }

            }
        }

        // 步骤5:返回整个字符串的最长回文子序列长度
        return dp[0][n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
