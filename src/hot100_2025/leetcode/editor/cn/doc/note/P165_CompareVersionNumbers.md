# 版本号比较（Compare Version Numbers）解题详解

## 解题思路

**总思路**：将版本号按点号分割成修订号数组，逐个比较每个修订号的数值大小。

**分步骤**：
1. 使用点号分割两个版本字符串，得到修订号数组
2. 同时遍历两个数组，比较对应位置的修订号
3. 将每个修订号转换为整数进行比较（自动处理前导零）
4. 如果某个版本号有更多的修订号，多出的部分视为0
5. 根据比较结果返回-1、0或1

## 归类说明
- **主要归类**：字符串处理、数组比较
- **算法技巧**：双指针、字符串分割
- **相关题型**：字符串比较、版本排序、字符串解析

## Java代码实现

```java
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        // 使用点号分割版本字符串
        String[] revisions1 = version1.split("\\.");
        String[] revisions2 = version2.split("\\.");
        
        int len1 = revisions1.length;
        int len2 = revisions2.length;
        
        // 遍历两个版本号的所有修订号
        for (int i = 0; i < Math.max(len1, len2); i++) {
            // 获取当前修订号，如果超出范围则视为0
            int rev1 = i < len1 ? Integer.parseInt(revisions1[i]) : 0;
            int rev2 = i < len2 ? Integer.parseInt(revisions2[i]) : 0;
            
            // 比较当前修订号
            if (rev1 < rev2) {
                return -1;
            } else if (rev1 > rev2) {
                return 1;
            }
            // 如果相等，继续比较下一个修订号
        }
        
        // 所有修订号都相等
        return 0;
    }
    
    // 另一种解法：使用双指针，避免分割字符串
    public int compareVersionTwoPointers(String version1, String version2) {
        int i = 0, j = 0;
        int len1 = version1.length(), len2 = version2.length();
        
        while (i < len1 || j < len2) {
            // 提取version1的当前修订号
            int num1 = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            
            // 提取version2的当前修订号
            int num2 = 0;
            while (j < len2 && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            
            // 比较修订号
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
            
            // 跳过点号，准备处理下一个修订号
            i++;
            j++;
        }
        
        return 0;
    }
    
    // 测试方法
    public static void main(String[] args) {
        CompareVersionNumbers solution = new CompareVersionNumbers();
        
        // 测试用例
        String[][] testCases = {
            {"1.01", "1.001"},        // 预期: 0
            {"1.0", "1.0.0"},         // 预期: 0
            {"0.1", "1.1"},           // 预期: -1
            {"1.0.1", "1"},           // 预期: 1
            {"7.5.2.4", "7.5.3"},     // 预期: -1
            {"1.2", "1.10"},          // 预期: -1 (2 < 10)
            {"1.0.0", "1.0"},         // 预期: 0
            {"1", "1.0.0.0"}          // 预期: 0
        };
        
        System.out.println("分割字符串解法:");
        for (String[] testCase : testCases) {
            String v1 = testCase[0];
            String v2 = testCase[1];
            int result = solution.compareVersion(v1, v2);
            System.out.println("\"" + v1 + "\" vs \"" + v2 + "\" = " + result);
        }
        
        System.out.println("\n双指针解法:");
        for (String[] testCase : testCases) {
            String v1 = testCase[0];
            String v2 = testCase[1];
            int result = solution.compareVersionTwoPointers(v1, v2);
            System.out.println("\"" + v1 + "\" vs \"" + v2 + "\" = " + result);
        }
    }
}
```

## 关键点解析

### 算法核心逻辑

1. **修订号提取**：
    - 使用`split("\\.")`按点号分割版本字符串
    - 或者使用双指针逐个字符解析修订号

2. **数值比较**：
    - 将修订号字符串转换为整数，自动处理前导零
    - 例如："001" → 1, "010" → 10

3. **长度处理**：
    - 如果一个版本号有更多修订号，多出的部分视为0
    - 例如："1.0" vs "1.0.0" → 比较 [1,0,0] vs [1,0,0]

### 执行过程示例（以"1.2" vs "1.10"为例）

```
版本1: "1.2" → 修订号: [1, 2]
版本2: "1.10" → 修订号: [1, 10]

第一轮比较:
  rev1 = 1, rev2 = 1 → 相等，继续

第二轮比较:
  rev1 = 2, rev2 = 10 → 2 < 10
返回: -1
```

### 双指针解法详解

双指针解法的优势：
- 不需要分割字符串，节省空间
- 一次遍历完成比较

执行过程：
```
version1: "1.2.3"
version2: "1.10.0"

指针遍历:
i=0, j=0: 提取 1 vs 1 → 相等
i=2, j=2: 提取 2 vs 10 → 2 < 10 → 返回-1
```

## 学习建议

1. **理解修订号比较规则**：
    - 修订号按数值比较，不是按字符串字典序
    - "1.2" < "1.10" 因为 2 < 10

2. **掌握两种解法**：
    - 分割字符串：代码简洁，易于理解
    - 双指针：空间效率更高

3. **处理边界情况**：
    - 版本号长度不一致
    - 修订号包含前导零
    - 版本号末尾的零

4. **相关题目练习**：
    - 版本号排序
    - 字符串转整数（atoi）
    - 比较含退格的字符串

5. **复杂度分析**：
    - 时间复杂度：O(max(m, n))，其中m和n是两个版本字符串的长度
    - 空间复杂度：
        - 分割法：O(m + n)，存储分割后的数组
        - 双指针：O(1)，只使用常数空间

6. **调试技巧**：
    - 打印每个修订号的数值
    - 使用小例子手动验证比较逻辑
    - 测试各种边界情况

7. **实际应用**：
    - 软件版本管理
    - 依赖版本检查
    - 系统兼容性判断

这道题很好地展示了如何处理复杂字符串比较，掌握它对解决其他字符串解析问题很有帮助！