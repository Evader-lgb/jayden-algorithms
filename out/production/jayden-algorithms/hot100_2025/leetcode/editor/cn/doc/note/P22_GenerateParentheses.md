# 括号生成（Generate Parentheses）解题详解

## 解题思路

**总思路**：使用回溯算法（DFS）生成所有有效的括号组合，通过控制左括号和右括号的数量来确保有效性。

**分步骤**：
1. 使用深度优先搜索（DFS）递归生成所有可能的括号组合
2. 维护两个计数器：当前左括号数量`left`和右括号数量`right`
3. 只有当左括号数量小于n时，可以添加左括号
4. 只有当右括号数量小于左括号数量时，可以添加右括号
5. 当字符串长度达到2n时，将当前组合加入结果列表

## 归类说明
- **主要归类**：回溯算法、深度优先搜索
- **算法技巧**：递归、剪枝
- **相关题型**：有效的括号、括号的分数、不同的二叉搜索树

## Java代码实现

```java
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    /**
     * 生成所有有效的括号组合
     * @param n 括号对数
     * @return 所有有效的括号组合列表
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // 特殊情况处理
        if (n <= 0) {
            return result;
        }
        // 开始回溯
        backtrack("", 0, 0, n, result);
        return result;
    }
    
    /**
     * 回溯函数
     * @param current 当前生成的字符串
     * @param left 已使用的左括号数量
     * @param right 已使用的右括号数量
     * @param n 括号对数
     * @param result 结果列表
     */
    private void backtrack(String current, int left, int right, int n, List<String> result) {
        // 终止条件：当前字符串长度达到2n
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        
        // 如果左括号数量小于n，可以添加左括号
        if (left < n) {
            backtrack(current + "(", left + 1, right, n, result);
        }
        
        // 如果右括号数量小于左括号数量，可以添加右括号
        // 这个条件确保了括号的有效性（不会出现无效的右括号）
        if (right < left) {
            backtrack(current + ")", left, right + 1, n, result);
        }
    }
    
    /**
     * 使用StringBuilder的优化版本（避免字符串拼接开销）
     */
    public List<String> generateParenthesisOptimized(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        
        backtrackOptimized(new StringBuilder(), 0, 0, n, result);
        return result;
    }
    
    private void backtrackOptimized(StringBuilder sb, int left, int right, int n, List<String> result) {
        // 终止条件
        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }
        
        // 添加左括号
        if (left < n) {
            sb.append('(');
            backtrackOptimized(sb, left + 1, right, n, result);
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
        
        // 添加右括号
        if (right < left) {
            sb.append(')');
            backtrackOptimized(sb, left, right + 1, n, result);
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
    }
    
    // 测试方法
    public static void main(String[] args) {
        GenerateParentheses solution = new GenerateParentheses();
        
        // 测试不同n值
        for (int n = 1; n <= 4; n++) {
            List<String> result = solution.generateParenthesis(n);
            System.out.println("n = " + n + ", 结果数量: " + result.size());
            System.out.println("结果: " + result);
            System.out.println();
        }
        
        // 测试优化版本
        System.out.println("优化版本测试 (n=3):");
        List<String> optimizedResult = solution.generateParenthesisOptimized(3);
        System.out.println(optimizedResult);
    }
}
```

## 关键点解析

### 回溯算法核心逻辑

1. **有效性保证**：
    - 左括号数量不能超过n
    - 右括号数量不能超过左括号数量
    - 这两个条件确保了生成的括号字符串总是有效的

2. **递归树结构**：
   ```
   n=2时的递归树：
   
   开始: ""
   ├── "("
   │   ├── "(("
   │   │   └── "(()"
   │   │       └── "(())" ✓
   │   └── "()"
   │       ├── "()("
   │       │   └── "()()" ✓
   │       └── "())" ✗ (无效，跳过)
   └── ")" ✗ (无效，跳过)
   ```

### 执行过程示例（n=2）

```
初始调用: backtrack("", 0, 0, 2, [])
├── 左括号分支: backtrack("(", 1, 0, 2, [])
│   ├── 左括号分支: backtrack("((", 2, 0, 2, [])
│   │   └── 右括号分支: backtrack("(()", 2, 1, 2, [])
│   │       └── 右括号分支: backtrack("(())", 2, 2, 2, []) → 加入结果
│   └── 右括号分支: backtrack("()", 1, 1, 2, [])
│       └── 左括号分支: backtrack("()(", 2, 1, 2, [])
│           └── 右括号分支: backtrack("()()", 2, 2, 2, []) → 加入结果
└── 右括号分支: 跳过 (right < left 不满足)

最终结果: ["(())", "()()"]
```

## 学习建议

1. **理解回溯模板**：
    - 掌握"选择-递归-回溯"的标准模式
    - 理解为什么需要撤销选择（在StringBuilder版本中）

2. **掌握有效性条件**：
    - 理解`right < left`条件的重要性
    - 思考如果没有这个条件会出现什么情况

3. **处理边界情况**：
    - n=0的情况
    - n=1的情况（只有"()"）

4. **相关题目练习**：
    - 有效的括号（验证括号有效性）
    - 括号的分数（计算括号字符串的分数）
    - 不同的二叉搜索树（类似的递归结构）

5. **复杂度分析**：
    - 时间复杂度：O(4^n/√n)，这是第n个卡特兰数的渐进复杂度
    - 空间复杂度：O(n)，递归栈的深度

6. **卡特兰数关系**：
    - 有效的括号组合数是第n个卡特兰数
    - 卡特兰数公式：C(n) = (2n)! / ((n+1)! * n!)

7. **调试技巧**：
    - 打印递归树的路径
    - 使用小n值手动验证
    - 检查每个分支的条件判断

8. **算法优化**：
    - 使用StringBuilder减少字符串拼接开销
    - 考虑迭代解法或动态规划解法

## 扩展解法

```java
/**
 * 动态规划解法
 * 思路：dp[i]表示i对括号的所有有效组合
 * dp[i] = "(" + dp[j] + ")" + dp[i-j-1] 对于所有j从0到i-1
 */
public List<String> generateParenthesisDP(int n) {
    List<List<String>> dp = new ArrayList<>();
    
    // 基础情况：0对括号只有空字符串
    List<String> dp0 = new ArrayList<>();
    dp0.add("");
    dp.add(dp0);
    
    for (int i = 1; i <= n; i++) {
        List<String> current = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            List<String> inside = dp.get(j);
            List<String> outside = dp.get(i - j - 1);
            for (String in : inside) {
                for (String out : outside) {
                    current.add("(" + in + ")" + out);
                }
            }
        }
        dp.add(current);
    }
    
    return dp.get(n);
}
```

这道题是回溯算法的经典应用，掌握它对理解递归和组合生成问题非常有帮助！