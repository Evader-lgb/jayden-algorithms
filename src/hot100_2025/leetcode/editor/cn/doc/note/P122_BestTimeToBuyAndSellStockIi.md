# 买卖股票的最佳时机 II 解题详解

## 解题思路

**总思路**：使用贪心算法，只要第二天的价格比第一天高，就在第一天买入，第二天卖出，累计所有正收益。

**分步骤**：
1. 遍历价格数组，从第1天到倒数第2天
2. 如果第二天的价格高于今天，就计算利润并累加
3. 返回累计利润

## 归类说明
- **主要归类**：贪心算法、数组
- **算法技巧**：利润累加、局部最优解
- **相关题型**：买卖股票的最佳时机I、III、IV

## Java代码实现

```java
public class BestTimeToBuyAndSellStockII {
    /**
     * 贪心算法解法
     * @param prices 股票价格数组
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int maxProfit = 0;
        
        // 遍历每一天，从第1天到倒数第2天
        for (int i = 0; i < prices.length - 1; i++) {
            // 如果明天价格比今天高，就今天买入明天卖出
            if (prices[i + 1] > prices[i]) {
                maxProfit += prices[i + 1] - prices[i];
            }
        }
        
        return maxProfit;
    }
    
    /**
     * 另一种写法：使用while循环
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int maxProfit = 0;
        int i = 0;
        
        while (i < prices.length - 1) {
            // 找到价格开始上升的点
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int valley = prices[i]; // 谷底价格（买入点）
            
            // 找到价格开始下降的点
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int peak = prices[i]; // 峰顶价格（卖出点）
            
            maxProfit += peak - valley;
            i++;
        }
        
        return maxProfit;
    }
    
    /**
     * 动态规划解法（扩展思路）
     */
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int n = prices.length;
        // dp[i][0] 表示第i天结束时，不持有股票的最大利润
        // dp[i][1] 表示第i天结束时，持有股票的最大利润
        int[][] dp = new int[n][2];
        
        // 初始化
        dp[0][0] = 0;           // 第0天不持有股票，利润为0
        dp[0][1] = -prices[0];  // 第0天持有股票，利润为-prices[0]
        
        for (int i = 1; i < n; i++) {
            // 第i天不持有股票：要么是前一天就不持有，要么是前一天持有今天卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天持有股票：要么是前一天就持有，要么是前一天不持有今天买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        
        return dp[n - 1][0]; // 最后一天不持有股票的最大利润
    }
    
    // 测试方法
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockII solution = new BestTimeToBuyAndSellStockII();
        
        // 测试用例
        int[][] testCases = {
            {7, 1, 5, 3, 6, 4}, // 预期: 7 (1->5, 3->6)
            {1, 2, 3, 4, 5},     // 预期: 4 (1->2, 2->3, 3->4, 4->5)
            {7, 6, 4, 3, 1},     // 预期: 0 (没有交易)
            {1},                 // 预期: 0
            {2, 1, 2, 0, 1},     // 预期: 2 (1->2, 0->1)
            {3, 3, 5, 0, 0, 3, 1, 4} // 预期: 8 (3->5, 0->3, 1->4)
        };
        
        System.out.println("贪心算法解法:");
        for (int i = 0; i < testCases.length; i++) {
            int[] prices = testCases[i];
            int result = solution.maxProfit(prices);
            System.out.println("输入: " + java.util.Arrays.toString(prices) + 
                             " -> 输出: " + result);
        }
        
        System.out.println("\n峰谷法解法:");
        for (int i = 0; i < testCases.length; i++) {
            int[] prices = testCases[i];
            int result = solution.maxProfit2(prices);
            System.out.println("输入: " + java.util.Arrays.toString(prices) + 
                             " -> 输出: " + result);
        }
        
        System.out.println("\n动态规划解法:");
        for (int i = 0; i < testCases.length; i++) {
            int[] prices = testCases[i];
            int result = solution.maxProfitDP(prices);
            System.out.println("输入: " + java.util.Arrays.toString(prices) + 
                             " -> 输出: " + result);
        }
    }
}
```

## 关键点解析

### 贪心算法核心逻辑

1. **核心思想**：
    - 只要价格在上涨，就进行交易
    - 每次上涨都获利，累计所有正收益

2. **数学证明**：
    - 总利润 = 所有上涨区间的利润和
    - 例如：`[1,2,3,4]` 的利润 = `(2-1) + (3-2) + (4-3) = 3`，等同于 `4-1 = 3`

### 执行过程示例（prices = [7,1,5,3,6,4]）

```
遍历过程：
i=0: 7->1 (下跌，不交易)
i=1: 1->5 (上涨，利润=4) → total=4
i=2: 5->3 (下跌，不交易)  
i=3: 3->6 (上涨，利润=3) → total=7
i=4: 6->4 (下跌，不交易)

总利润: 4+3=7
```

### 峰谷法思路

1. **寻找谷底和峰顶**：
    - 谷底：价格开始上升的点
    - 峰顶：价格开始下降的点
    - 每次从谷底到峰顶都是一次完整的交易

2. **与贪心法的关系**：
    - 峰谷法是贪心法的一种实现方式
    - 结果相同，但思维方式不同

### 动态规划思路

1. **状态定义**：
    - `dp[i][0]`：第i天结束时不持有股票的最大利润
    - `dp[i][1]`：第i天结束时持有股票的最大利润

2. **状态转移**：
    - 不持有：`max(昨天就不持有, 昨天持有今天卖出)`
    - 持有：`max(昨天就持有, 昨天不持有今天买入)`

## 学习建议

1. **理解贪心思想**：
    - 掌握"所有上涨都获利"的核心思想
    - 理解为什么这样能得到最优解

2. **掌握多种解法**：
    - 贪心法：最简单直观
    - 峰谷法：另一种贪心实现
    - 动态规划：通用解法，可扩展到其他股票问题

3. **处理边界情况**：
    - 空数组或单元素数组
    - 单调递减数组
    - 单调递增数组

4. **相关题目练习**：
    - 买卖股票的最佳时机I（只能交易一次）
    - 买卖股票的最佳时机III（最多交易两次）
    - 买卖股票的最佳时机IV（最多交易k次）

5. **复杂度分析**：
    - 时间复杂度：O(n)，只需遍历一次数组
    - 空间复杂度：
        - 贪心法：O(1)
        - 动态规划：O(n)

6. **调试技巧**：
    - 打印每次交易的利润
    - 使用小例子手动验证
    - 对比不同解法的结果

7. **算法选择**：
    - 面试推荐贪心解法
    - 理解原理推荐动态规划
    - 实际应用根据需求选择

## 为什么贪心算法有效？

**数学证明**：
对于任意价格序列，总利润可以分解为：
```
总利润 = Σ(max(0, prices[i] - prices[i-1]))
```

这意味着只要相邻两天价格有上涨，就获利，最终结果与选择所有上涨区间获利相同。

这道题是贪心算法的经典应用，掌握它对理解局部最优解如何导致全局最优解很有帮助！