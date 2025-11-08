//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。 
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。 
//
// 假设每一种面额的硬币有无限个。 
//
// 题目数据保证结果符合 32 位带符号整数。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2： 
//
// 
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
// 
//
// 示例 3： 
//
// 
//输入：amount = 10, coins = [10] 
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 300 
// 1 <= coins[i] <= 5000 
// coins 中的所有值 互不相同 
// 0 <= amount <= 5000 
// 
//
// Related Topics 数组 动态规划 👍 1442 👎 0


package 数组;

/**
 * 零钱兑换 II
 * @author Jayden
 * @date 2025-11-02 13:45:59
 */
public class P518_CoinChangeIi {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P518_CoinChangeIi().new Solution();
          int[] coins = new int[]{1,2,5};
          solution.change(5,coins);
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * dp[j] += dp[j - coin] 的核心思想是：
     *
     * 使用当前硬币 coin 时，凑成金额 j 的新组合数等于凑成金额 j - coin 的组合数
     *
     * 这个简单的公式捕捉了动态规划的精髓：将大问题分解为小问题，利用已解决的子问题来构建原问题的解。
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
