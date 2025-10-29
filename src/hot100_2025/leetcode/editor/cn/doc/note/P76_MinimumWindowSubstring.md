# 最小覆盖子串（Minimum Window Substring）解题详解

## 解题思路

**总思路**：使用滑动窗口技术，通过两个指针维护一个窗口，用哈希表记录字符需求，逐步扩展和收缩窗口来寻找最小覆盖子串。

**分步骤**：
1. 使用两个哈希表：`need` 记录t中每个字符的需求量，`window` 记录当前窗口中各字符的数量
2. 使用双指针`left`和`right`表示滑动窗口的左右边界
3. 移动右指针扩展窗口，直到窗口包含t的所有字符
4. 然后移动左指针收缩窗口，寻找最小满足条件的子串
5. 记录最小窗口的起始位置和长度

## 归类说明
- **主要归类**：滑动窗口、哈希表、双指针
- **算法技巧**：字符统计、窗口收缩
- **相关题型**：字符串的排列、找到字符串中所有字母异位词、无重复字符的最长子串

## Java代码实现

```java
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    /**
     * 滑动窗口解法
     * @param s 源字符串
     * @param t 目标字符串
     * @return 最小覆盖子串
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        // 记录t中每个字符的需求量
        Map<Character, Integer> need = new HashMap<>();
        // 记录当前窗口中各字符的数量
        Map<Character, Integer> window = new HashMap<>();
        
        // 初始化need表
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        
        int left = 0, right = 0;
        int valid = 0; // 记录窗口中满足need条件的字符个数
        int start = 0, len = Integer.MAX_VALUE; // 记录最小覆盖子串的起始位置和长度
        
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            
            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
    
    /**
     * 数组优化版本（当字符范围已知时）
     * 假设字符都是ASCII码，范围0-127
     */
    public String minWindowArray(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        // 使用数组代替哈希表
        int[] need = new int[128];
        int[] window = new int[128];
        
        // 初始化need数组
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            
            if (need[c] > 0) {
                window[c]++;
                if (window[c] <= need[c]) {
                    valid++;
                }
            }
            
            while (valid == t.length()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                
                char d = s.charAt(left);
                left++;
                
                if (need[d] > 0) {
                    if (window[d] <= need[d]) {
                        valid--;
                    }
                    window[d]--;
                }
            }
        }
        
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
    
    // 测试方法
    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        
        // 测试用例
        String[][] testCases = {
            {"ADOBECODEBANC", "ABC"},  // 预期: "BANC"
            {"a", "a"},                // 预期: "a"
            {"a", "aa"},               // 预期: ""
            {"aa", "aa"},              // 预期: "aa"
            {"abc", "d"},              // 预期: ""
            {"bdab", "ab"},            // 预期: "ab"
            {"cabwefgewcwaefgcf", "cae"} // 预期: "cwae"
        };
        
        System.out.println("哈希表解法:");
        for (String[] testCase : testCases) {
            String s = testCase[0];
            String t = testCase[1];
            String result = solution.minWindow(s, t);
            System.out.println("s: \"" + s + "\", t: \"" + t + "\" -> \"" + result + "\"");
        }
        
        System.out.println("\n数组优化解法:");
        for (String[] testCase : testCases) {
            String s = testCase[0];
            String t = testCase[1];
            String result = solution.minWindowArray(s, t);
            System.out.println("s: \"" + s + "\", t: \"" + t + "\" -> \"" + result + "\"");
        }
    }
}
```

## 关键点解析

### 滑动窗口核心逻辑

1. **窗口扩展**：
    - 移动右指针，将字符加入窗口
    - 更新窗口字符计数
    - 当字符计数满足需求时，增加`valid`计数

2. **窗口收缩**：
    - 当`valid == need.size()`时，窗口满足条件
    - 移动左指针，尝试找到更小的满足条件的窗口
    - 更新最小窗口记录

3. **有效性判断**：
    - `valid`表示当前窗口中满足`need`条件的字符种类数
    - 当`valid == need.size()`时，说明窗口包含了t的所有字符

### 执行过程示例（s = "ADOBECODEBANC", t = "ABC"）

```
初始化: need={A:1, B:1, C:1}, window={}, left=0, right=0, valid=0

步骤1: right=0, char='A'
  window={A:1}, valid=1 (A满足需求)
  
步骤2: right=1, char='D'
  window={A:1, D:1}, valid=1
  
步骤3: right=2, char='O'
  window={A:1, D:1, O:1}, valid=1
  
步骤4: right=3, char='B'
  window={A:1, B:1, D:1, O:1}, valid=2 (B满足需求)
  
步骤5: right=4, char='E'
  window={A:1, B:1, D:1, E:1, O:1}, valid=2
  
步骤6: right=5, char='C'
  window={A:1, B:1, C:1, D:1, E:1, O:1}, valid=3 (所有字符满足需求)
  
开始收缩窗口:
  当前窗口"ADOBEC"长度=6, 记录start=0, len=6
  移动left: char='A', window={B:1, C:1, D:1, E:1, O:1}, valid=2
  窗口不再满足条件，继续扩展...

... 继续扩展和收缩，最终找到最小窗口"BANC"
```

### 哈希表 vs 数组

**哈希表版本**：
- 通用性强，适用于任何字符集
- 代码清晰易懂
- 稍微慢于数组版本

**数组版本**：
- 假设字符是ASCII码（0-127）
- 性能更好，访问速度更快
- 代码更简洁

## 学习建议

1. **理解滑动窗口模板**：
    - 掌握右指针扩展和左指针收缩的时机
    - 理解`valid`变量的作用

2. **掌握字符统计**：
    - 熟练使用哈希表和数组进行字符计数
    - 理解何时增加/减少`valid`计数

3. **处理边界情况**：
    - s长度小于t长度
    - t中有重复字符
    - 找不到满足条件的子串

4. **相关题目练习**：
    - 无重复字符的最长子串
    - 字符串的排列
    - 找到字符串中所有字母异位词

5. **复杂度分析**：
    - 时间复杂度：O(m+n)，其中m是s长度，n是t长度
    - 空间复杂度：O(k)，k是字符集大小

6. **调试技巧**：
    - 打印每次窗口移动后的状态
    - 使用小例子手动验证
    - 检查边界条件的处理

7. **算法优化**：
    - 使用数组代替哈希表提高性能
    - 考虑字符集的范围选择合适的数据结构

## 常见错误

1. **valid计数错误**：
   ```java
   // 错误：每次增加都计数
   if (need.containsKey(c)) {
       window.put(c, window.getOrDefault(c, 0) + 1);
       valid++; // 应该只在window[c] == need[c]时计数
   }
   ```

2. **窗口收缩条件错误**：
   ```java
   // 错误：使用t.length()作为条件
   while (valid == t.length()) { // 应该用need.size()
   ```

3. **最小窗口更新时机错误**：
   ```java
   // 错误：在收缩循环外更新
   if (valid == need.size()) {
       // 更新最小窗口
   }
   // 应该在收缩循环内更新
   ```

这道题是滑动窗口技术的经典应用，掌握它对解决其他字符串搜索问题很有帮助！