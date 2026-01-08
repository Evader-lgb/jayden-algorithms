//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
//
// Related Topics 记忆化搜索 数学 动态规划 👍 3966 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 爬楼梯
 * @author Jayden
 * @date 2026-01-08 23:57:05
 */
public class P70_ClimbingStairs{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P70_ClimbingStairs().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution{

    /**
     * 错题记录：dp的思路是对的，没有卡点。错误的地方是
      * @param n
     * @return
     */
    public int climbStairs(int n) {
        // 边界处理：如果n < 2 直接返回n
        if (n<=2){
            return n;
        }

        // 定义状态
        int[] dp = new int[n+1];

        // 最小问题（边界）
//        dp[0] = 0; TODO 这里写错了，实际可以从dp[1]开始，循环从3开始
        dp[0] = 1;
        dp[1] = 1;

        // 遍历
//        for (int i = 2; i < n; i++) {  TODO这里习惯性的用<号了
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        // 返回结果
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
