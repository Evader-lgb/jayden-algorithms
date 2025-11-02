# 反转字符串中的单词

## 解题思路

### 总思路
使用双指针法从后向前遍历字符串，逐个提取单词并构建结果字符串，避免使用语言内置的split和reverse函数。

### 分步骤
1. 去除字符串首尾空格
2. 从后向前遍历字符串，使用双指针定位每个单词的边界
3. 遇到空格时，将当前单词添加到结果中
4. 跳过连续的空格
5. 构建最终的反转单词字符串

## 归类说明
- **主要分类**：字符串操作
- **算法技巧**：双指针、字符串处理
- **数据结构**：字符串
- **相关题目**：反转字符串、反转字符串中的单词III、字符串中的单词数

## Java代码实现

```java
class Solution {
    public String reverseWords(String s) {
        // 去除字符串首尾的空格
        s = s.trim();
        
        // 初始化双指针：i和j都指向字符串末尾
        int i = s.length() - 1, j = i;
        
        // 使用StringBuilder构建结果字符串，提高效率
        StringBuilder res = new StringBuilder();
        
        // 从后向前遍历字符串
        while (i >= 0) {
            // 移动指针i，找到单词的起始位置（遇到空格或到达开头）
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            
            // 将找到的单词（从i+1到j）添加到结果中
            // 注意：此时i指向空格或-1，所以单词范围是[i+1, j]
            res.append(s.substring(i + 1, j + 1));
            
            // 添加单词间的空格（除了最后一个单词）
            if (i > 0) {
                res.append(" ");
            }
            
            // 跳过连续的空格，找到下一个单词的结尾
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            
            // 移动指针j到下一个单词的结尾位置
            j = i;
        }
        
        return res.toString();
    }
}
```

## 更简洁的实现方式

```java
class Solution {
    public String reverseWords(String s) {
        // 去除首尾空格
        s = s.trim();
        
        // 使用正则表达式分割字符串，\\s+表示一个或多个空格
        String[] words = s.split("\\s+");
        
        // 使用StringBuilder构建结果
        StringBuilder res = new StringBuilder();
        
        // 从后向前遍历单词数组
        for (int i = words.length - 1; i >= 0; i--) {
            res.append(words[i]);
            // 在单词之间添加空格（最后一个单词后不添加）
            if (i > 0) {
                res.append(" ");
            }
        }
        
        return res.toString();
    }
}
```

## 不使用trim和split的手动实现

```java
class Solution {
    public String reverseWords(String s) {
        // 将字符串转换为字符数组便于操作
        char[] chars = s.toCharArray();
        int n = chars.length;
        
        // 第一步：去除多余空格（包括首尾和中间连续空格）
        int len = removeExtraSpaces(chars, n);
        
        // 第二步：反转整个字符串
        reverse(chars, 0, len - 1);
        
        // 第三步：逐个反转每个单词
        reverseEachWord(chars, len);
        
        // 返回处理后的字符串
        return new String(chars, 0, len);
    }
    
    /**
     * 去除多余空格
     * @param chars 字符数组
     * @param n 数组长度
     * @return 去除空格后的新长度
     */
    private int removeExtraSpaces(char[] chars, int n) {
        int slow = 0; // 慢指针，指向有效字符的位置
        int fast = 0; // 快指针，用于遍历
        
        // 跳过前导空格
        while (fast < n && chars[fast] == ' ') {
            fast++;
        }
        
        // 处理中间部分
        while (fast < n) {
            // 如果不是空格，直接复制
            if (chars[fast] != ' ') {
                chars[slow++] = chars[fast++];
            } else {
                // 如果是空格，只保留一个空格
                chars[slow++] = ' ';
                // 跳过后续连续空格
                while (fast < n && chars[fast] == ' ') {
                    fast++;
                }
            }
        }
        
        // 如果最后有多余空格，需要去除
        if (slow > 0 && chars[slow - 1] == ' ') {
            slow--;
        }
        
        return slow;
    }
    
    /**
     * 反转字符数组中指定范围的字符
     * @param chars 字符数组
     * @param left 起始位置
     * @param right 结束位置
     */
    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
    
    /**
     * 逐个反转每个单词
     * @param chars 字符数组
     * @param len 有效长度
     */
    private void reverseEachWord(char[] chars, int len) {
        int start = 0; // 单词起始位置
        int end = 0;   // 单词结束位置
        
        while (end < len) {
            // 找到单词的结束位置（空格或字符串结尾）
            while (end < len && chars[end] != ' ') {
                end++;
            }
            
            // 反转当前单词
            reverse(chars, start, end - 1);
            
            // 移动到下一个单词
            start = end + 1;
            end = start;
        }
    }
}
```

## 关键点解析

### 双指针法的核心思想
- **快指针**：用于遍历原字符串，寻找单词边界
- **慢指针**：用于构建结果字符串，确定插入位置

### 边界情况处理
1. **前导空格**：`"  hello world"` → `"world hello"`
2. **尾随空格**：`"hello world  "` → `"world hello"`
3. **多个连续空格**：`"hello   world"` → `"world hello"`
4. **全空格字符串**：`"     "` → `""`
5. **单个单词**：`"hello"` → `"hello"`

### 时间复杂度分析
- **方法一（双指针）**：O(n)，每个字符只被处理一次
- **方法二（使用split）**：O(n)，但需要额外的数组空间
- **方法三（手动处理）**：O(n)，原地操作，空间复杂度O(1)

## 学习建议

### 1. 掌握双指针技巧
- 双指针是处理字符串和数组问题的常用技巧
- 理解快慢指针的不同作用
- 练习其他双指针题目：删除有序数组中的重复项、移动零等

### 2. 熟悉字符串操作
- `trim()`：去除首尾空格
- `split("\\s+")`：按空格分割字符串
- `substring()`：提取子字符串
- `StringBuilder`：高效构建字符串

### 3. 边界条件思维
- 始终考虑空字符串、全空格、单个字符等边界情况
- 测试各种可能的输入组合

### 4. 相关练习题目
1. **反转字符串中的单词III**（LeetCode 557）
    - 反转每个单词中的字符顺序
    - 输入："Let's take LeetCode contest"
    - 输出："s'teL ekat edoCteeL tsetnoc"

2. **字符串中的单词数**（LeetCode 434）
    - 统计字符串中的单词数量
    - 需要考虑各种空格情况

3. **翻转字符串里的单词**（进阶版）
    - 要求原地操作，不使用额外空间

### 5. 调试技巧
- 使用小测试用例手动模拟指针移动
- 打印中间结果观察字符串构建过程
- 特别注意指针的起始和结束位置

通过这道题，你可以深入理解字符串操作和双指针技巧，这是解决许多字符串相关问题的基础！