# 最长公共子序列（Longest Common Subsequence）解题详解

## 解题思路

**总思路**：使用动态规划，通过二维数组记录两个字符串在不同位置的最长公共子序列长度。

**分步骤**：
1. 定义二维DP数组，`dp[i][j]`表示`text1`前i个字符和`text2`前j个字符的LCS长度
2. 初始化边界条件（空字符串的情况）
3. 遍历两个字符串，根据字符是否相等更新DP数组
4. 根据状态转移方程填充DP表
5. 最终结果存储在`dp[m][n]`中

## 归类说明
- **主要归类**：动态规划、字符串处理
- **算法技巧**：二维动态规划、状态转移
- **相关题型**：编辑距离、最长递增子序列、最短公共超序列

## Java代码实现

```java
public class LongestCommonSubsequence {
    /**
     * 动态规划解法
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return 最长公共子序列的长度
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // 创建DP表，dp[i][j]表示text1前i个字符和text2前j个字符的LCS长度
        int[][] dp = new int[m + 1][n + 1];
        
        // 填充DP表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果当前字符相等
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 状态转移：LCS长度加1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 状态转移：取左边或上边的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * 空间优化版本 - 使用一维数组
     */
    public int longestCommonSubsequenceOptimized(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // 使用一维数组优化空间
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= m; i++) {
            int prev = 0; // 保存左上角的值
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // 保存当前值，用于下一轮作为prev
                
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                
                prev = temp; // 更新prev
            }
        }
        
        return dp[n];
    }
    
    /**
     * 扩展：返回最长公共子序列本身（不仅仅是长度）
     */
    public String getLCSString(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // 填充DP表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // 回溯构建LCS字符串
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                // 当前字符属于LCS，添加到结果中
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // 向上移动
                i--;
            } else {
                // 向左移动
                j--;
            }
        }
        
        return lcs.reverse().toString();
    }
    
    // 测试方法
    public static void main(String[] args) {
        LongestCommonSubsequence solution = new LongestCommonSubsequence();
        
        // 测试用例
        String[][] testCases = {
            {"abcde", "ace"},          // 预期: 3 (LCS: "ace")
            {"abc", "abc"},            // 预期: 3 (LCS: "abc")
            {"abc", "def"},            // 预期: 0 (没有公共子序列)
            {"abcdgh", "aedfhr"},      // 预期: 3 (LCS: "adh")
            {"aggtab", "gxtxayb"}      // 预期: 4 (LCS: "gtab")
        };
        
        System.out.println("基本解法 - 最长公共子序列长度:");
        for (String[] testCase : testCases) {
            String text1 = testCase[0];
            String text2 = testCase[1];
            int result = solution.longestCommonSubsequence(text1, text2);
            System.out.println("text1: \"" + text1 + "\", text2: \"" + text2 + 
                             "\" -> LCS长度: " + result);
        }
        
        System.out.println("\n空间优化版本:");
        for (String[] testCase : testCases) {
            String text1 = testCase[0];
            String text2 = testCase[1];
            int result = solution.longestCommonSubsequenceOptimized(text1, text2);
            System.out.println("text1: \"" + text1 + "\", text2: \"" + text2 + 
                             "\" -> LCS长度: " + result);
        }
        
        System.out.println("\n获取LCS字符串:");
        for (String[] testCase : testCases) {
            String text1 = testCase[0];
            String text2 = testCase[1];
            String lcs = solution.getLCSString(text1, text2);
            System.out.println("text1: \"" + text1 + "\", text2: \"" + text2 + 
                             "\" -> LCS: \"" + lcs + "\"");
        }
    }
}
```

## 关键点解析

### 动态规划状态转移

1. **状态定义**：
    - `dp[i][j]` = LCS长度 for `text1[0..i-1]` 和 `text2[0..j-1]`

2. **状态转移方程**：
    - 如果 `text1[i-1] == text2[j-1]`：`dp[i][j] = dp[i-1][j-1] + 1`
    - 否则：`dp[i][j] = max(dp[i-1][j], dp[i][j-1])`

3. **基础情况**：
    - `dp[0][j] = 0`（text1为空字符串）
    - `dp[i][0] = 0`（text2为空字符串）

### DP表填充示例（"abcde" vs "ace"）

```
    "" a c e
""   0 0 0 0
a    0 1 1 1  
b    0 1 1 1
c    0 1 2 2
d    0 1 2 2
e    0 1 2 3

最终结果：dp[5][3] = 3
```

### 回溯构建LCS过程

从`dp[5][3]`开始回溯：
- `'e' == 'e'` → 添加'e'，移动到`dp[4][2]`
- `'d' != 'c'` → 比较`dp[3][2]`和`dp[4][1]`，选择较大的`dp[3][2]`
- `'c' == 'c'` → 添加'c'，移动到`dp[2][1]`
- `'b' != 'a'` → 比较`dp[1][1]`和`dp[2][0]`，选择`dp[1][1]`
- `'a' == 'a'` → 添加'a'，移动到`dp[0][0]`

最终LCS："ace"（需要反转）

## 学习建议

1. **理解状态转移**：
    - 在纸上画出DP表的填充过程
    - 理解为什么字符相等时`+1`，不相等时取`max`

2. **掌握两种实现**：
    - 二维DP：直观易懂
    - 一维DP：空间优化

3. **学习回溯技巧**：
    - 掌握如何从DP表回溯得到实际的LCS
    - 理解回溯的方向选择

4. **相关题目练习**：
    - 编辑距离（类似的DP思路）
    - 最长递增子序列
    - 最短公共超序列
    - 不同的子序列

5. **复杂度分析**：
    - 时间复杂度：O(m × n)，需要填充m×n的DP表
    - 空间复杂度：
        - 二维DP：O(m × n)
        - 一维DP：O(min(m, n))

6. **实际应用**：
    - 文件差异比较（git diff）
    - DNA序列比对
    - 文本相似度计算

7. **调试技巧**：
    - 打印DP表观察填充过程
    - 使用小例子手动验证
    - 检查边界情况（空字符串、单字符等）

这道题是动态规划的经典问题，掌握它对理解更复杂的DP问题非常有帮助！