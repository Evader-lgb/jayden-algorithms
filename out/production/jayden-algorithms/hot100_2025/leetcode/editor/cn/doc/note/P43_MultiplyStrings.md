# 字符串相乘（Multiply Strings）解题详解

## 解题思路

**总思路**：模拟手算乘法的过程，使用数组来存储中间结果，避免直接处理大数。

**分步骤**：
1. 处理特殊情况：如果任意一个数字为"0"，直接返回"0"
2. 创建结果数组，长度为两个字符串长度之和
3. 从右向左遍历两个数字的每一位，进行乘法计算
4. 处理进位，将数组转换为字符串
5. 去除前导零

## 归类说明
- **主要归类**：数学、字符串处理
- **算法技巧**：模拟、数组操作
- **相关题型**：字符串相加、大数运算、数组形式的整数加法

## Java代码实现

```java
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        // 处理特殊情况：如果任意一个数字为0，结果直接为0
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        int m = num1.length(); // 第一个数字的长度
        int n = num2.length(); // 第二个数字的长度
        
        // 结果数组，长度最多为 m+n
        int[] result = new int[m + n];
        
        // 从右向左遍历 num1 的每一位
        for (int i = m - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0'; // 获取当前位的数字值
            
            // 从右向左遍历 num2 的每一位
            for (int j = n - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0'; // 获取当前位的数字值
                
                // 计算乘积
                int product = digit1 * digit2;
                
                // 计算在结果数组中的位置
                int pos1 = i + j;      // 进位位置
                int pos2 = i + j + 1;  // 当前位位置
                
                // 将乘积加到对应位置
                int sum = product + result[pos2];
                
                // 更新当前位和进位
                result[pos2] = sum % 10;        // 当前位取模
                result[pos1] += sum / 10;       // 进位加到前一位
            }
        }
        
        // 将结果数组转换为字符串
        StringBuilder sb = new StringBuilder();
        
        // 跳过前导零
        int start = 0;
        while (start < result.length && result[start] == 0) {
            start++;
        }
        
        // 将剩余数字转换为字符串
        for (int i = start; i < result.length; i++) {
            sb.append(result[i]);
        }
        
        return sb.toString();
    }
    
    // 测试方法
    public static void main(String[] args) {
        MultiplyStrings solution = new MultiplyStrings();
        
        // 测试用例
        String[][] testCases = {
            {"2", "3"},           // 预期: "6"
            {"123", "456"},       // 预期: "56088"
            {"0", "123"},         // 预期: "0"
            {"999", "999"},       // 预期: "998001"
            {"123456789", "987654321"} // 预期: "121932631112635269"
        };
        
        for (String[] testCase : testCases) {
            String num1 = testCase[0];
            String num2 = testCase[1];
            String result = solution.multiply(num1, num2);
            System.out.println(num1 + " * " + num2 + " = " + result);
        }
    }
}
```

## 关键点解析

### 算法核心逻辑

1. **位置计算**：
    - 对于 `num1[i]` 和 `num2[j]`，它们的乘积会影响结果中的两个位置：
        - `i + j + 1`：当前位
        - `i + j`：进位位

2. **进位处理**：
    - 每次计算后立即处理当前位的进位
    - 进位会自动传递到前一位

3. **前导零处理**：
    - 结果数组可能前面有零，需要跳过

### 执行过程示例（以"123" × "456"为例）

```
初始结果数组: [0, 0, 0, 0, 0, 0]

第一步: 处理 num1[2]=3 和 num2[2]=6
  3 × 6 = 18
  位置: pos1=4, pos2=5
  result[5] = 18 % 10 = 8
  result[4] += 18 / 10 = 1
  结果: [0, 0, 0, 0, 1, 8]

第二步: 处理 num1[2]=3 和 num2[1]=5
  3 × 5 = 15
  位置: pos1=3, pos2=4
  sum = 15 + result[4]=1 = 16
  result[4] = 16 % 10 = 6
  result[3] += 16 / 10 = 1
  结果: [0, 0, 0, 1, 6, 8]

... 继续所有组合 ...

最终结果数组: [0, 5, 6, 0, 8, 8]
跳过前导零后: "56088"
```

## 学习建议

1. **理解位置计算**：
    - 在纸上画出位置对应关系
    - 理解为什么 `i + j` 和 `i + j + 1` 是正确的位置

2. **掌握进位处理**：
    - 练习手动计算乘法，观察进位如何传递
    - 理解立即处理进位的优势

3. **处理边界情况**：
    - 零的处理
    - 前导零的去除
    - 大数运算的溢出问题

4. **相关题目练习**：
    - 字符串相加（Add Strings）
    - 数组形式的整数加法（Add to Array-Form of Integer）
    - 大数加法

5. **复杂度分析**：
    - 时间复杂度：O(m × n)，其中 m 和 n 是两个字符串的长度
    - 空间复杂度：O(m + n)，用于存储结果数组

6. **调试技巧**：
    - 打印中间结果数组
    - 使用小例子手动验证
    - 检查进位是否正确处理

7. **优化思考**：
    - 可以使用 Karatsuba 算法进行优化
    - 考虑分治策略处理超大数

这道题很好地展示了如何用数组来模拟数学运算，掌握它对处理大数运算问题很有帮助！