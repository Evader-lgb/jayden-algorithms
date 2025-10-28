非常好！我们来学习**复原IP地址（Restore IP Addresses）**这道题。这是一道经典的回溯算法问题，能很好地训练字符串处理和递归思维。

## 复原IP地址解题详解

### 解题思路

**总思路**：使用回溯算法，在字符串中尝试所有可能的分割点，生成合法的IP地址段，当生成4个合法段且用完所有字符时，保存结果。

**分步骤**：
1. 使用深度优先搜索（DFS）遍历所有可能的分割方式
2. 每次尝试截取1-3个字符作为一个IP段
3. 检查截取的段是否合法：
    - 数值在0-255之间
    - 不能有前导零（除非本身就是0）
4. 当生成4个合法段且用完所有字符时，将结果加入答案列表
5. 回溯尝试其他可能的分割方式

### 归类说明
- **主要归类**：回溯算法、字符串处理
- **算法技巧**：深度优先搜索（DFS）、剪枝优化
- **相关题型**：分割回文串、组合总和、括号生成

### Java代码实现

```java
import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        // 存储所有有效IP地址的结果列表
        List<String> result = new ArrayList<>();
        // 如果字符串长度不在4-12之间，直接返回空结果（IP地址最少4位，最多12位）
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        // 开始回溯搜索
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * 回溯函数
     * @param s 原始字符串
     * @param start 当前处理的起始位置
     * @param path 当前已生成的IP段列表
     * @param result 存储所有有效IP地址的结果列表
     */
    private void backtrack(String s, int start, List<String> path, List<String> result) {
        // 终止条件1：已经生成4个段
        if (path.size() == 4) {
            // 如果已经用完所有字符，将路径转换为IP地址字符串
            if (start == s.length()) {
                result.add(String.join(".", path));
            }
            return; // 无论是否用完字符，只要满4段就返回
        }

        // 终止条件2：剩余字符太多或太少，无法形成有效IP
        int remainingSegments = 4 - path.size(); // 剩余需要生成的段数
        int remainingChars = s.length() - start; // 剩余字符数
        
        // 剪枝：如果剩余字符数不在合理范围内，提前返回
        if (remainingChars < remainingSegments || remainingChars > remainingSegments * 3) {
            return;
        }

        // 尝试截取1-3个字符作为当前段
        for (int len = 1; len <= 3; len++) {
            // 检查是否越界
            if (start + len > s.length()) {
                break;
            }
            
            // 截取当前段
            String segment = s.substring(start, start + len);
            
            // 检查当前段是否合法
            if (isValidSegment(segment)) {
                // 选择：将合法段加入路径
                path.add(segment);
                // 递归处理剩余字符串
                backtrack(s, start + len, path, result);
                // 回溯：移除当前段，尝试其他可能
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 检查IP段是否合法
     * @param segment 待检查的IP段
     * @return 是否合法
     */
    private boolean isValidSegment(String segment) {
        // 检查长度：不能超过3位
        if (segment.length() > 3) {
            return false;
        }
        
        // 检查前导零：如果长度大于1且以0开头，不合法
        if (segment.length() > 1 && segment.charAt(0) == '0') {
            return false;
        }
        
        // 检查数值范围：必须在0-255之间
        int value = Integer.parseInt(segment);
        return value >= 0 && value <= 255;
    }

    // 测试方法
    public static void main(String[] args) {
        RestoreIPAddresses solution = new RestoreIPAddresses();
        
        // 测试用例1
        String s1 = "25525511135";
        System.out.println("输入: " + s1);
        System.out.println("输出: " + solution.restoreIpAddresses(s1));
        // 预期输出: ["255.255.11.135", "255.255.111.35"]
        
        // 测试用例2
        String s2 = "0000";
        System.out.println("输入: " + s2);
        System.out.println("输出: " + solution.restoreIpAddresses(s2));
        // 预期输出: ["0.0.0.0"]
        
        // 测试用例3
        String s3 = "101023";
        System.out.println("输入: " + s3);
        System.out.println("输出: " + solution.restoreIpAddresses(s3));
        // 预期输出: ["1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"]
    }
}
```

### 关键点解析

1. **回溯框架**：标准的"选择-递归-回溯"模式
2. **剪枝优化**：
    - 提前检查剩余字符数是否在合理范围内
    - 避免无效的递归调用
3. **合法性检查**：
    - 数值范围：0-255
    - 前导零限制：除了"0"本身，不能有"01"、"001"这样的段
4. **终止条件**：
    - 成功：生成4个段且用完所有字符
    - 失败：剩余字符数不合理

### 执行过程示例（以"25525511135"为例）

```
初始调用：backtrack("25525511135", 0, [], [])
├── 尝试段"2" → backtrack("5525511135", 1, ["2"], [])
│   ├── 尝试段"55" → backtrack("25511135", 3, ["2","55"], [])
│   │   ├── 尝试段"2" → backtrack("5511135", 4, ["2","55","2"], [])
│   │   │   └── 尝试段"55" → 失败（剩余字符太多）
│   │   ├── 尝试段"25" → backtrack("511135", 5, ["2","55","25"], [])
│   │   │   └── 尝试段"5" → 失败（剩余字符太多）
│   │   └── 尝试段"255" → 数值255合法，继续...
│   └── 尝试段"255" → 数值255合法，继续...
└── 尝试段"25" → 继续探索其他分支...
```

最终找到两个有效IP：`"255.255.11.135"` 和 `"255.255.111.35"`

### 学习建议

1. **理解回溯模板**：掌握"选择-递归-回溯"的标准模式
2. **练习剪枝技巧**：思考如何提前排除无效分支，提高算法效率
3. **处理边界情况**：
    - 前导零的处理
    - 字符串长度限制
    - 数值范围检查
4. **相关题目练习**：
    - 分割回文串（类似的字符串分割问题）
    - 组合总和（类似的回溯问题）
    - 括号生成（状态管理的回溯问题）
5. **复杂度分析**：
    - 时间复杂度：O(3^4)，每个段最多3种选择，共4段
    - 空间复杂度：O(4)，递归栈深度为4

6. **调试技巧**：可以在递归开始时打印当前状态，帮助理解执行流程

这道题很好地展示了回溯算法在字符串处理中的应用，掌握它对你解决其他回溯问题很有帮助！