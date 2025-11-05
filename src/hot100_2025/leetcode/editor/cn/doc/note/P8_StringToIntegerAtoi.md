# 字符串转换整数 (atoi)

## 题目理解
实现一个将字符串转换为整数的函数，功能类似于 C/C++ 中的 atoi 函数。需要处理各种边界情况和异常输入。

## 解题思路

### 总思路
使用有限状态机的思想，按顺序处理字符串的各个部分：前导空格、符号位、数字字符，并在处理过程中检查溢出。

### 分步骤
1. **处理前导空格**：跳过字符串开头的所有空格
2. **处理符号位**：检查正负号，默认为正
3. **处理数字字符**：逐个读取数字字符并转换为整数
4. **处理溢出**：在转换过程中检查是否超出 32 位有符号整数范围
5. **处理非数字字符**：遇到非数字字符立即停止转换

## 归类说明
- **主要分类**：字符串处理、数学运算
- **算法技巧**：有限状态机、边界处理
- **数据结构**：字符串
- **相关题目**：整数反转、有效数字、数字字符串转换为整数

## Java代码实现

```java
class Solution {
    public int myAtoi(String s) {
        // 边界情况：空字符串
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int index = 0;      // 字符串索引
        int sign = 1;       // 符号，1表示正数，-1表示负数
        int result = 0;     // 转换结果
        int n = s.length(); // 字符串长度
        
        // 1. 跳过前导空格
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }
        
        // 2. 处理符号位
        if (index < n && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }
        
        // 3. 处理数字字符
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';
            
            // 4. 检查溢出
            // 如果 result > Integer.MAX_VALUE / 10，那么 result * 10 一定会溢出
            // 如果 result == Integer.MAX_VALUE / 10 且 digit > 7，那么也会溢出
            // 因为 Integer.MAX_VALUE = 2147483647，最后一位是7
            // Integer.MIN_VALUE = -2147483648，最后一位是8
            if (result > Integer.MAX_VALUE / 10 || 
                (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            // 更新结果
            result = result * 10 + digit;
            index++;
        }
        
        return sign * result;
    }
}
```

## 关键点详细解析

### 1. 前导空格处理
```java
while (index < n && s.charAt(index) == ' ') {
    index++;
}
```
- 使用循环跳过所有前导空格
- 注意检查索引是否越界

### 2. 符号位处理
```java
if (index < n && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
    sign = (s.charAt(index) == '-') ? -1 : 1;
    index++;
}
```
- 只处理第一个遇到的 '+' 或 '-'
- 默认符号为正数
- 处理完符号位后索引加1

### 3. 数字转换与溢出检查
```java
if (result > Integer.MAX_VALUE / 10 || 
    (result == Integer.MAX_VALUE / 10 && digit > 7)) {
    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
}
```
**溢出检查逻辑**：
- `Integer.MAX_VALUE = 2147483647`
- `Integer.MIN_VALUE = -2147483648`

**正数溢出条件**：
- 如果 `result > 214748364`，那么 `result * 10` 至少是 `2147483650`，已经溢出
- 如果 `result == 214748364` 且 `digit > 7`，那么结果是 `2147483648` 到 `2147483649`，溢出

**负数溢出条件**：
- 负数的最小值是 `-2147483648`
- 当符号为负时，如果 `result > 214748364` 或者 (`result == 214748364` 且 `digit > 8`)，就会溢出
- 但由于我们统一用正数计算，检查条件相同，最后根据符号返回对应的极值

## 测试用例分析

### 正常情况：
- `"42"` → `42`
- `"   -42"` → `-42`
- `"4193 with words"` → `4193`

### 边界情况：
- `""` → `0`（空字符串）
- `"     "` → `0`（全空格）
- `"words and 987"` → `0`（数字前有非数字字符）
- `"-91283472332"` → `-2147483648`（下溢）
- `"91283472332"` → `2147483647`（上溢）
- `"3.14159"` → `3`（遇到非数字停止）
- `"+-12"` → `0`（多个符号）
- `"00000-42a1234"` → `0`（符号在数字后）
- `"   +0 123"` → `0`（数字后有空格）

## 更健壮的实现（处理更多边界情况）

```java
class Solution {
    public int myAtoi(String s) {
        if (s == null) return 0;
        
        s = s.trim(); // 去除前后空格
        if (s.length() == 0) return 0;
        
        int index = 0;
        int sign = 1;
        int result = 0;
        
        // 处理符号位
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }
        
        // 转换数字
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';
            
            // 检查溢出
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            result = result * 10 + digit;
            index++;
        }
        
        return sign * result;
    }
}
```

## 学习建议

### 1. 理解整数溢出
- 32位有符号整数范围：-2,147,483,648 到 2,147,483,647
- 掌握溢出检测的数学原理

### 2. 掌握字符串处理技巧
- `Character.isDigit()` 判断字符是否为数字
- 字符串索引的边界检查
- 前导空格的跳过处理

### 3. 相关题目练习
1. **整数反转**（LeetCode 7）
    - 类似的溢出检查逻辑
    - 数字的逆向处理

2. **有效数字**（LeetCode 65）
    - 更复杂的字符串验证
    - 多种格式的数字表示

3. **数字字符串转换为整数**（类似题目）
    - 巩固字符串转整数的技巧

### 4. 调试技巧
- 为每个测试用例画出处理流程图
- 打印中间变量观察处理过程
- 特别注意边界值的处理

### 5. 常见错误避免
- 忘记处理前导空格
- 溢出检查条件写错
- 符号位处理不当
- 索引越界访问

### 6. 扩展思考
- 如果要求支持其他进制怎么办？
- 如果要求更宽松的格式（如科学计数法）？
- 如何实现浮点数的字符串转换？

通过这道题，你可以深入理解字符串处理和边界情况处理的重要性，这是实际工程中经常遇到的问题！