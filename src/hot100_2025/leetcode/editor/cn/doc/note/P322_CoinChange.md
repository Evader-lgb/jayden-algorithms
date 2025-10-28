# 零钱兑换（Coin Change）解题详解

## 解题思路

**总思路**：使用动态规划，计算从0到amount的每个金额所需的最少硬币数。

**分步骤**：
1. 定义dp数组，dp[i]表示凑成金额i所需的最少硬币数
2. 初始化dp[0] = 0，其他位置初始化为一个不可能的大值（amount+1）
3. 遍历每个金额，对于每个金额遍历所有硬币
4. 如果当前金额大于等于硬币面值，更新dp值
5. 最终检查dp[amount]是否被更新，返回相应结果

## 归类说明
- **主要归类**：动态规划、完全背包问题
- **算法技巧**：状态转移、最小值优化
- **相关题型**：零钱兑换II、完全平方数、组合总和IV

## Java代码实现

```java
import java.util.Arrays;

public class CoinChange {
    /**
     * 动态规划解法
     * @param coins 硬币面值数组
     * @param amount 目标金额
     * @return 凑成目标金额所需的最少硬币数，如果无法凑成返回-1
     */
    public int coinChange(int[] coins, int amount) {
        // 创建dp数组，dp[i]表示凑成金额i所需的最少硬币数
        int[] dp = new int[amount + 1];
        
        // 初始化dp数组，用amount+1表示无穷大（因为最多可能用amount个1元硬币）
        Arrays.fill(dp, amount + 1);
        
        // 基础情况：凑成金额0需要0个硬币
        dp[0] = 0;
        
        // 遍历所有金额从1到amount
        for (int i = 1; i <= amount; i++) {
            // 遍历所有硬币
            for (int coin : coins) {
                // 如果当前金额大于等于硬币面值，且使用该硬币后剩余金额有解
                if (i >= coin && dp[i - coin] != amount + 1) {
                    // 更新dp[i]：比较当前解和使用该硬币的解
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // 如果dp[amount]没有被更新，说明无法凑成
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
    
    /**
     * 动态规划解法（另一种写法，更清晰）
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * BFS解法（找到最短路径）
     */
    public int coinChangeBFS(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];
        
        queue.offer(0);
        visited[0] = true;
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                
                for (int coin : coins) {
                    int next = current + coin;
                    
                    if (next == amount) {
                        return level;
                    }
                    
                    if (next < amount && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        
        return -1;
    }
    
    // 测试方法
    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        
        // 测试用例
        int[][] testCases = {
            {1, 2, 5}, 11,     // 预期: 3 (5+5+1)
            {2}, 3,             // 预期: -1 (无法凑成)
            {1}, 0,             // 预期: 0
            {1}, 1,             // 预期: 1
            {1}, 2,             // 预期: 2
            {1, 2, 5}, 100,    // 预期: 20 (20个5元)
            {2, 5, 10}, 13     // 预期: 4 (10+2+1? 但1不存在) → 实际: -1
        };
        
        for (int i = 0; i < testCases.length; i += 2) {
            int[] coins = testCases[i];
            int amount = testCases[i + 1];
            int result = solution.coinChange(coins, amount);
            System.out.println("硬币: " + Arrays.toString(coins) + 
                             ", 金额: " + amount + " -> 最少硬币数: " + result);
        }
    }
}
```

## 关键点解析

### 动态规划核心逻辑

1. **状态定义**：
    - `dp[i]` = 凑成金额i所需的最少硬币数

2. **状态转移方程**：
    - `dp[i] = min(dp[i], dp[i - coin] + 1)` for coin in coins

3. **初始化**：
    - `dp[0] = 0`（金额0需要0个硬币）
    - 其他位置初始化为`amount + 1`（表示不可达）

### 执行过程示例（coins = [1,2,5], amount = 11）

```
初始化: dp = [0, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12]

i=1: 
  coin=1: dp[1] = min(12, dp[0]+1=1) = 1
  coin=2: 跳过
  coin=5: 跳过

i=2:
  coin=1: dp[2] = min(12, dp[1]+1=2) = 2
  coin=2: dp[2] = min(2, dp[0]+1=1) = 1
  coin=5: 跳过

i=3:
  coin=1: dp[3] = min(12, dp[2]+1=2) = 2
  coin=2: dp[3] = min(2, dp[1]+1=2) = 2
  coin=5: 跳过

...

i=11:
  coin=1: dp[11] = min(12, dp[10]+1=3) = 3
  coin=2: dp[11] = min(3, dp[9]+1=3) = 3
  coin=5: dp[11] = min(3, dp[6]+1=3) = 3

最终结果: dp[11] = 3
```

### BFS解法思路

将问题转化为图的最短路径问题：
- 节点：当前金额
- 边：使用一个硬币（从当前金额到当前金额+coin）
- 目标：从0到amount的最短路径

## 学习建议

1. **理解状态转移**：
    - 在纸上画出dp数组的填充过程
    - 理解为什么使用`min(dp[i], dp[i-coin] + 1)`

2. **掌握两种解法**：
    - 动态规划：通用解法，适用于大多数DP问题
    - BFS：更直观，适合理解问题本质

3. **处理边界情况**：
    - amount为0的情况
    - 无法凑成的情况
    - 硬币面值大于amount的情况

4. **相关题目练习**：
    - 零钱兑换II（求组合数）
    - 完全平方数（类似的DP思路）
    - 组合总和IV（排列数）

5. **复杂度分析**：
    - 时间复杂度：O(amount × n)，其中n是硬币种类数
    - 空间复杂度：O(amount)，dp数组的大小

6. **优化思考**：
    - 可以先对硬币排序进行剪枝
    - 对于大金额可以考虑贪心+DP的混合策略

7. **调试技巧**：
    - 打印dp数组的中间结果
    - 使用小例子手动验证
    - 检查边界条件的处理

这道题是动态规划的经典问题，掌握它对理解背包类问题和状态转移非常有帮助！