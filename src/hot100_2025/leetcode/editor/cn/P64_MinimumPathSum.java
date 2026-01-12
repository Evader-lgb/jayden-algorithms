//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
// 
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 200 
// 
//
// Related Topics 数组 动态规划 矩阵 👍 1893 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 最小路径和
 * @author Jayden
 * @date 2026-01-12 21:06:11
 */
public class P64_MinimumPathSum{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P64_MinimumPathSum().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid[0].length;
        int m = grid.length;

        // 定义状态，dp[m][n] 表示从i到j的最小值
        int[][] dp = new int[m][n];

        // TODO 错题：这里实际初始化的值不对，比如[0][n]应该是[0][n]之前所有值加起来的和
        //  临界处理,横排跟竖排的0要处理掉
        for (int i = 0; i < m; i++) {
            dp[i][0] = grid[i][0];
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }

        // 状态转移方程 dp[m][n] = dp[m-1][n] + grid[m][n],dp[m][n-1] + grid[m][n]的最大值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j] + grid[i][j],dp[i][j-1] + grid[i][j]);
            }
        }


        // 返回结果
        return dp[m][n];
    }

    public int minPathSum2(int[][] grid) {
        int n = grid[0].length;
        int m = grid.length;

        // 定义状态，dp[m][n] 表示从i到j的最小值
        int[][] dp = new int[m][n];

        // TODO 少了这行代码。用节省空间的写法默认就是这个值
        dp[0][0] = grid[0][0];

        // 状态转移方程 dp[m][n] = dp[m-1][n] + grid[m][n],dp[m][n-1] + grid[m][n]的最大值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0 && j==0){
                    continue;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j] + grid[i][j],dp[i][j-1] + grid[i][j]);
                }
            }
        }

        // 返回结果
        return dp[m-1][n-1];
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
