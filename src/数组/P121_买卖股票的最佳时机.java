package 数组;

/**
 * <p>Title: 买卖股票的最佳时机</p>
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/">链接</a>
 * <p>Description: 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的的最大利润。返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。</p>
 * <pre>
 *     示例 1：
 *     输入：[7,1,5,3,6,4]
 *     输出：5
 *     解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * @date 2025/10/11
 * <p>Difficulty: 简单</p>
 */
public class P121_买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            // 算出当前最低的价格
            if (price < cost){
                cost = price;
            }
            // 当当天价格比成本价高的时候要算下利润
            else if(price - cost > profit){
                profit = price - cost;
            }

        }

        return profit;
    }
}
