//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 3782 👎 0


package 字符串;

/**
 * 编辑距离
 * @author Jayden
 * @date 2025-10-23 19:43:07
 */
public class P72_EditDistance {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P72_EditDistance().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 创建dp表，dp[i][j]表示word1前i个字符转成word2前j个字符的最小操作数
        int[][] dp = new int[m + 1][n + 1];

        // 初始化：word1为空字符串时，只能通过插入操作
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        // 初始化：word2为空字符串时，只能通过删除操作
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // 填充dp表
        for (int i = 1; i<=m; i++){
            for (int j = 1; j <= n; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    // 字符相等，不需要操作，直接继承左上角的值
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    // 字符不相等，考虑三种操作，取最小值 然后 +1
                    dp[i][j] = Math.min(
                            Math.min(
                                dp[i-1][j],//删除word1的第i个字符
                                dp[i][j-1]// 在word1插入word2的第j个字符
                            ),
                            dp[i-1][j-1]    // 替换word1的第i个字符为word2的第j个
                    )+1;//无论那种操作，都需要一步
                }
            }
        }

        return dp[m][n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
