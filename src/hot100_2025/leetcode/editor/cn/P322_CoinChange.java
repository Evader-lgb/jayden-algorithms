//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
//
// Related Topics 广度优先搜索 数组 动态规划 👍 3178 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 零钱兑换
 * @author Jayden
 * @date 2026-01-17 16:24:44
 */
public class P322_CoinChange{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P322_CoinChange().new Solution();
//          int[] coins = new int[]{1,2,5};
          int[] coins = new int[]{474,83,404,3};
         System.out.println(solution.coinChange(coins, 264));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int coinChange(int[] coins, int amount) {
        // 状态定义:dp[i]代表凑成i所需要的硬币个数
        int[] dp = new int[amount + 1];

        // 状态转移方程
        // dp[i] = 各个最小值 + 1;

        // 初始化值，最小问题
        // Todo 这里有初始化为0有问题，取最小值会一直是0
//        Arrays.fill(dp,0);
        Arrays.fill(dp,amount + 1);
        dp[0] = 0;

        // 考虑怎么遍历
        // 应该算dp1、dp2、dp3
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                if(i < coin){
                    continue;
                }
                // todo 这里算的是dp[i]的值，用amount-coin不对
//                dp[i] = Math.min(dp[i],dp[amount-coin] + 1);
                dp[i] = Math.min(dp[i],dp[i-coin] + 1);
             }
        }

        // 返回结果
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
