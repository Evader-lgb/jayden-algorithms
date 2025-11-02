# 最小路径和（Minimum Path Sum）解题详解

## 解题思路

**总思路**：使用动态规划，计算从左上角到每个位置的最小路径和，每个位置的最小路径和等于当前值加上左边或上边的最小值。

**分步骤**：
1. 创建二维DP数组，`dp[i][j]`表示从`(0,0)`到`(i,j)`的最小路径和
2. 初始化第一行和第一列（只能从一个方向来）
3. 对于其他位置，`dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]`
4. 返回右下角的值

## 归类说明
- **主要归类**：动态规划、网格遍历
- **算法技巧**：状态转移、路径优化
- **相关题型**：不同路径、不同路径II、三角形最小路径和

## Java代码实现

```java
public class MinimumPathSum {
    /**
     * 动态规划解法
     * @param grid 二维网格
     * @return 从左上角到右下角的最小路径和
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;    // 行数
        int n = grid[0].length; // 列数
        
        // 创建DP表
        int[][] dp = new int[m][n];
        
        // 初始化起点
        dp[0][0] = grid[0][0];
        
        // 初始化第一行：只能从左边来
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        
        // 初始化第一列：只能从上边来
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        
        // 填充其他位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        
        return dp[m - 1][n - 1];
    }
    
    /**
     * 空间优化版本（使用一维数组）
     */
    public int minPathSumOptimized(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        // 使用一维数组存储当前行的DP值
        int[] dp = new int[n];
        
        // 初始化第一行
        dp[0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        
        // 逐行处理
        for (int i = 1; i < m; i++) {
            // 更新当前行的第一个元素
            dp[0] = dp[0] + grid[i][0];
            
            for (int j = 1; j < n; j++) {
                // dp[j] 表示上一行的当前列值（即上边的值）
                // dp[j-1] 表示当前行的前一列值（即左边的值）
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * 直接在原数组上修改（不推荐，但可以节省空间）
     */
    public int minPathSumInPlace(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        // 修改第一行
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        
        // 修改第一列
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        
        // 修改其他位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        
        return grid[m - 1][n - 1];
    }
    
    // 测试方法
    public static void main(String[] args) {
        MinimumPathSum solution = new MinimumPathSum();
        
        // 测试用例
        int[][][] testCases = {
            {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
            },  // 预期: 7 (1→3→1→1→1)
            {
                {1, 2, 3},
                {4, 5, 6}
            },  // 预期: 12 (1→2→3→6)
            {
                {1}
            },  // 预期: 1
            {
                {1, 2},
                {1, 1}
            }   // 预期: 3 (1→1→1)
        };
        
        System.out.println("标准动态规划解法:");
        for (int[][] grid : testCases) {
            int result = solution.minPathSum(grid);
            System.out.println("网格: " + java.util.Arrays.deepToString(grid));
            System.out.println("最小路径和: " + result);
            System.out.println();
        }
        
        System.out.println("空间优化解法:");
        for (int[][] grid : testCases) {
            // 创建副本，因为原数组可能被修改
            int[][] gridCopy = copyGrid(grid);
            int result = solution.minPathSumOptimized(gridCopy);
            System.out.println("最小路径和: " + result);
        }
    }
    
    // 辅助方法：复制网格
    private static int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i].clone();
        }
        return copy;
    }
}
```

## 关键点解析

### 动态规划核心逻辑

1. **状态定义**：
    - `dp[i][j]` = 从`(0,0)`到`(i,j)`的最小路径和

2. **状态转移方程**：
    - `dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]`

3. **初始化**：
    - `dp[0][0] = grid[0][0]`
    - 第一行：`dp[0][j] = dp[0][j-1] + grid[0][j]`
    - 第一列：`dp[i][0] = dp[i-1][0] + grid[i][0]`

### 执行过程示例（grid = [[1,3,1],[1,5,1],[4,2,1]]）

**初始化DP表**：
```
初始网格:
[1, 3, 1]
[1, 5, 1]  
[4, 2, 1]

初始化后:
[1, 4, 5]
[2, 0, 0]
[6, 0, 0]
```

**填充过程**：
```
位置(1,1): min(4,2) + 5 = 2 + 5 = 7
位置(1,2): min(5,7) + 1 = 5 + 1 = 6
位置(2,1): min(7,6) + 2 = 6 + 2 = 8
位置(2,2): min(6,8) + 1 = 6 + 1 = 7

最终DP表:
[1, 4, 5]
[2, 7, 6]
[6, 8, 7]
```

### 空间优化思路

**为什么可以优化？**
- 计算第i行时，只需要第i-1行的数据
- 可以使用一维数组，按行更新

**更新过程**：
```
初始: dp = [1, 4, 5]

处理第1行:
dp[0] = 2 (1+1)
dp[1] = min(4,2) + 5 = 7
dp[2] = min(5,7) + 1 = 6
dp = [2, 7, 6]

处理第2行:
dp[0] = 6 (2+4)
dp[1] = min(7,6) + 2 = 8  
dp[2] = min(6,8) + 1 = 7
dp = [6, 8, 7]
```

## 学习建议

1. **理解状态转移**：
    - 掌握为什么取左边和上边的最小值
    - 理解边界条件的处理

2. **掌握优化技巧**：
    - 二维DP到一维DP的转换
    - 理解空间优化的原理

3. **处理边界情况**：
    - 1×1网格
    - 1×n网格
    - n×1网格

4. **相关题目练习**：
    - 不同路径（计数问题）
    - 不同路径II（有障碍物）
    - 三角形最小路径和

5. **复杂度分析**：
    - 时间复杂度：O(m×n)
    - 空间复杂度：
        - 二维DP：O(m×n)
        - 一维DP：O(n)

6. **调试技巧**：
    - 打印DP表的中间状态
    - 使用小网格手动验证
    - 检查边界条件的处理

7. **算法选择**：
    - 面试推荐标准DP解法
    - 实际应用推荐空间优化版本
    - 理解原理后再考虑原数组修改

## 常见错误

1. **边界初始化错误**：
   ```java
   // 错误：忘记初始化第一行第一列
   for (int i = 1; i < m; i++) {
       for (int j = 1; j < n; j++) {
           // 这样会漏掉边界
       }
   }
   ```

2. **状态转移错误**：
   ```java
   // 错误：取最大值而不是最小值
   dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
   ```

3. **空间优化更新顺序错误**：
   ```java
   // 错误：先更新后面的元素
   for (int j = n-1; j >= 0; j--) {
       // 这样会使用错误的上方值
   }
   ```

这道题是动态规划的经典应用，掌握它对解决其他路径优化问题很有帮助！