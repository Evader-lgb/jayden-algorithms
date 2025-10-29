# 字符串解码（Decode String）解题详解

## 解题思路

**总思路**：使用栈来处理嵌套的编码字符串，遇到数字时解析完整数字，遇到左括号时压栈，遇到右括号时出栈并构建重复字符串。

**分步骤**：
1. 使用两个栈：一个存储数字（重复次数），一个存储字符串
2. 遍历字符串，处理四种情况：数字、左括号、右括号、普通字母
3. 遇到数字时解析完整的数字（可能有多位）
4. 遇到左括号时，将当前数字和字符串压栈，并重置状态
5. 遇到右括号时，从栈中弹出数字和字符串，构建重复后的新字符串
6. 遇到普通字母时，直接添加到当前字符串

## 归类说明
- **主要归类**：栈、字符串处理
- **算法技巧**：栈操作、字符串构建
- **相关题型**：基本计算器、字符串编码、括号匹配

## Java代码实现

```java
import java.util.Stack;

public class DecodeString {
    /**
     * 双栈解法
     * @param s 编码字符串
     * @return 解码后的字符串
     */
    public String decodeString(String s) {
        // 存储数字的栈
        Stack<Integer> countStack = new Stack<>();
        // 存储字符串的栈
        Stack<String> stringStack = new Stack<>();
        
        // 当前正在构建的字符串
        StringBuilder currentString = new StringBuilder();
        // 当前解析的数字
        int currentNum = 0;
        
        // 遍历字符串中的每个字符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                // 如果是数字，累加到当前数字（处理多位数字）
                currentNum = currentNum * 10 + (c - '0');
            } else if (c == '[') {
                // 遇到左括号，将当前状态压入栈
                countStack.push(currentNum);
                stringStack.push(currentString.toString());
                // 重置当前状态
                currentNum = 0;
                currentString = new StringBuilder();
            } else if (c == ']') {
                // 遇到右括号，从栈中弹出状态并构建重复字符串
                int count = countStack.pop();
                StringBuilder temp = new StringBuilder(stringStack.pop());
                
                // 将当前字符串重复count次
                for (int j = 0; j < count; j++) {
                    temp.append(currentString);
                }
                
                currentString = temp;
            } else {
                // 普通字母，直接添加到当前字符串
                currentString.append(c);
            }
        }
        
        return currentString.toString();
    }
    
    /**
     * 递归解法
     */
    private int index = 0; // 全局索引，用于递归
    
    public String decodeStringRecursive(String s) {
        index = 0; // 重置索引
        return decode(s);
    }
    
    private String decode(String s) {
        StringBuilder result = new StringBuilder();
        int num = 0;
        
        while (index < s.length()) {
            char c = s.charAt(index);
            
            if (Character.isDigit(c)) {
                // 解析数字
                num = num * 10 + (c - '0');
                index++;
            } else if (c == '[') {
                // 遇到左括号，递归解码子字符串
                index++; // 跳过'['
                String inner = decode(s);
                // 将子字符串重复num次
                for (int i = 0; i < num; i++) {
                    result.append(inner);
                }
                num = 0; // 重置数字
            } else if (c == ']') {
                // 遇到右括号，返回当前结果
                index++;
                return result.toString();
            } else {
                // 普通字符
                result.append(c);
                index++;
            }
        }
        
        return result.toString();
    }
    
    // 测试方法
    public static void main(String[] args) {
        DecodeString solution = new DecodeString();
        
        // 测试用例
        String[] testCases = {
            "3[a]2[bc]",           // 预期: "aaabcbc"
            "3[a2[c]]",            // 预期: "accaccacc"
            "2[abc]3[cd]ef",       // 预期: "abcabccdcdcdef"
            "abc3[cd]xyz",         // 预期: "abccdcdcdxyz"
            "10[a]",               // 预期: "aaaaaaaaaa"
            "2[3[a]b]",            // 预期: "aaabaaab"
            "1[]"                  // 预期: ""
        };
        
        System.out.println("双栈解法:");
        for (String test : testCases) {
            String result = solution.decodeString(test);
            System.out.println("输入: \"" + test + "\" -> 输出: \"" + result + "\"");
        }
        
        System.out.println("\n递归解法:");
        for (String test : testCases) {
            String result = solution.decodeStringRecursive(test);
            System.out.println("输入: \"" + test + "\" -> 输出: \"" + result + "\"");
        }
    }
}
```

## 关键点解析

### 双栈解法核心逻辑

1. **数字解析**：
    - 数字可能有多位，需要累加计算
    - `currentNum = currentNum * 10 + (c - '0')`

2. **状态保存**：
    - 遇到`[`时，保存当前数字和字符串状态
    - 遇到`]`时，恢复状态并构建重复字符串

3. **字符串构建**：
    - 使用`StringBuilder`提高字符串拼接效率
    - 重复操作：`temp.append(currentString)`重复count次

### 执行过程示例（s = "3[a2[c]]"）

```
初始: currentString="", currentNum=0

遍历过程:
'3': currentNum=3
'[': countStack=[3], stringStack=[""], currentString="", currentNum=0
'a': currentString="a"
'2': currentNum=2
'[': countStack=[3,2], stringStack=["","a"], currentString="", currentNum=0
'c': currentString="c"
']': count=2, prevStr="a" → currentString="a" + "c"*2 = "acc"
']': count=3, prevStr="" → currentString="" + "acc"*3 = "accaccacc"

最终结果: "accaccacc"
```

### 递归解法核心逻辑

1. **全局索引**：
    - 使用`index`跟踪当前处理位置
    - 递归调用时索引自动前进

2. **递归终止**：
    - 遇到`]`时返回当前层的结果
    - 字符串遍历完成时返回最终结果

3. **状态管理**：
    - 每层递归维护自己的`num`和`result`
    - 递归返回后使用上一层的`num`进行重复操作

## 学习建议

1. **理解栈的应用**：
    - 掌握何时压栈（遇到`[`）
    - 掌握何时出栈（遇到`]`）
    - 理解栈中保存的状态信息

2. **掌握两种解法**：
    - 双栈解法：迭代思维，容易理解
    - 递归解法：更简洁，体现分治思想

3. **处理边界情况**：
    - 空字符串或无效输入
    - 数字为0或1的情况
    - 多层嵌套的情况

4. **相关题目练习**：
    - 基本计算器（类似的栈应用）
    - 字符串编码（逆操作）
    - 括号的分数

5. **复杂度分析**：
    - 时间复杂度：O(n)，每个字符处理一次
    - 空间复杂度：O(n)，栈的深度取决于嵌套层数

6. **调试技巧**：
    - 打印栈的中间状态
    - 使用小例子手动验证
    - 检查数字解析是否正确

7. **算法选择**：
    - 面试推荐双栈解法
    - 实际应用可根据情况选择
    - 理解递归有助于解决其他嵌套问题

## 常见错误

1. **数字解析不完整**：
   ```java
   // 错误：只处理一位数字
   currentNum = c - '0';
   ```

2. **字符串重置错误**：
   ```java
   // 错误：没有正确重置状态
   currentString = ""; // 应该使用new StringBuilder()
   ```

3. **栈操作顺序错误**：
   ```java
   // 错误：弹出顺序不对
   String prevStr = stringStack.pop();
   int count = countStack.pop(); // 应该先弹出数字
   ```

这道题很好地展示了栈在处理嵌套结构时的应用，掌握它对解决其他类似问题很有帮助！