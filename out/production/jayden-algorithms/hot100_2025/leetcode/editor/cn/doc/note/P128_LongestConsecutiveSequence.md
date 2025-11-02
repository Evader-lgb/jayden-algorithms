# 最长连续序列（Longest Consecutive Sequence）解题详解

## 解题思路

**总思路**：使用哈希集合存储所有数字，然后遍历集合，只从连续序列的起点开始计算长度。

**分步骤**：
1. 将所有数字存入哈希集合（去重且快速查找）
2. 遍历集合中的每个数字
3. 如果当前数字的前一个数字不在集合中，说明这是连续序列的起点
4. 从起点开始向后查找连续的数字，计算序列长度
5. 更新最大序列长度

## 归类说明
- **主要归类**：哈希表、数组
- **算法技巧**：集合操作、序列检测
- **相关题型**：存在重复元素、数组的度、寻找消失的数字

## Java代码实现

```java
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    /**
     * 哈希集合解法
     * @param nums 输入数组
     * @return 最长连续序列的长度
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 将数组元素存入集合，去重且提供O(1)查找
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int longestStreak = 0;
        
        // 遍历集合中的每个数字
        for (int num : numSet) {
            // 只有当num是序列的起点时才进行计算
            // 即num-1不在集合中
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                // 向后查找连续的数字
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                // 更新最长序列长度
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        
        return longestStreak;
    }
    
    /**
     * 另一种写法：使用数组遍历而非集合遍历
     */
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int longestStreak = 0;
        
        // 遍历原数组而非集合
        for (int num : nums) {
            // 只有当num是序列的起点时才进行计算
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        
        return longestStreak;
    }
    
    /**
     * 并查集解法（扩展思路）
     */
    public int longestConsecutiveUnionFind(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        UnionFind uf = new UnionFind(nums);
        
        for (int num : nums) {
            // 如果存在相邻数字，则合并
            if (uf.parent.containsKey(num + 1)) {
                uf.union(num, num + 1);
            }
        }
        
        return uf.maxSize;
    }
    
    // 并查集类
    class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> size;
        int maxSize;
        
        public UnionFind(int[] nums) {
            parent = new HashMap<>();
            size = new HashMap<>();
            maxSize = 0;
            
            for (int num : nums) {
                parent.put(num, num);
                size.put(num, 1);
                maxSize = Math.max(maxSize, 1);
            }
        }
        
        public int find(int x) {
            if (parent.get(x) != x) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (size.get(rootX) < size.get(rootY)) {
                    parent.put(rootX, rootY);
                    size.put(rootY, size.get(rootY) + size.get(rootX));
                    maxSize = Math.max(maxSize, size.get(rootY));
                } else {
                    parent.put(rootY, rootX);
                    size.put(rootX, size.get(rootX) + size.get(rootY));
                    maxSize = Math.max(maxSize, size.get(rootX));
                }
            }
        }
    }
    
    // 测试方法
    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
        
        // 测试用例
        int[][] testCases = {
            {100, 4, 200, 1, 3, 2},           // 预期: 4 (序列: 1,2,3,4)
            {0, 3, 7, 2, 5, 8, 4, 6, 0, 1},   // 预期: 9 (序列: 0,1,2,3,4,5,6,7,8)
            {},                                // 预期: 0
            {1},                               // 预期: 1
            {1, 2, 0, 1},                     // 预期: 3 (序列: 0,1,2)
            {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6} // 预期: 7 (序列: -1,0,1,2,3,4,5,6)
        };
        
        System.out.println("哈希集合解法:");
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int result = solution.longestConsecutive(nums);
            System.out.println("输入: " + java.util.Arrays.toString(nums) + 
                             " -> 输出: " + result);
        }
        
        System.out.println("\n并查集解法:");
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int result = solution.longestConsecutiveUnionFind(nums);
            System.out.println("输入: " + java.util.Arrays.toString(nums) + 
                             " -> 输出: " + result);
        }
    }
}
```

## 关键点解析

### 哈希集合解法核心逻辑

1. **去重处理**：
    - 使用集合自动去除重复数字
    - 避免重复计算相同数字

2. **起点检测**：
    - 只有当`num-1`不在集合中时，`num`才是连续序列的起点
    - 这个检查确保了每个序列只被计算一次

3. **序列扩展**：
    - 从起点开始，不断检查`currentNum + 1`是否在集合中
    - 计算序列长度

### 执行过程示例（nums = [100, 4, 200, 1, 3, 2]）

```
集合: {100, 4, 200, 1, 3, 2}

遍历集合:
- 100: 99不在集合中 → 起点
  向后扩展: 101不在集合中 → 序列长度=1

- 4: 3在集合中 → 不是起点，跳过

- 200: 199不在集合中 → 起点
  向后扩展: 201不在集合中 → 序列长度=1

- 1: 0不在集合中 → 起点
  向后扩展: 
    2在集合中 → 长度=2
    3在集合中 → 长度=3
    4在集合中 → 长度=4
    5不在集合中 → 停止
  序列长度=4

- 3: 2在集合中 → 不是起点，跳过

- 2: 1在集合中 → 不是起点，跳过

最长序列长度: 4
```

### 为什么时间复杂度是O(n)

- 每个数字最多被访问两次：
    1. 第一次：加入集合时
    2. 第二次：作为序列起点或被序列包含时
- 内层while循环的总次数是O(n)，因为每个数字最多被扩展一次

## 学习建议

1. **理解起点检测**：
    - 掌握为什么只从起点开始计算
    - 理解这样避免重复计算的原理

2. **掌握集合操作**：
    - 熟练使用HashSet进行快速查找
    - 理解集合去重的重要性

3. **处理边界情况**：
    - 空数组
    - 单个元素数组
    - 有重复元素的数组
    - 负数的情况

4. **相关题目练习**：
    - 存在重复元素
    - 数组的度
    - 寻找数组中消失的数字

5. **复杂度分析**：
    - 时间复杂度：O(n)，每个元素最多被访问两次
    - 空间复杂度：O(n)，存储哈希集合

6. **调试技巧**：
    - 打印每个序列的起点和长度
    - 使用小例子手动验证
    - 检查重复元素的处理

7. **算法扩展**：
    - 理解并查集解法的思路
    - 思考其他可能的解法（如排序）

## 常见错误

1. **直接排序**：
   ```java
   // 错误：时间复杂度O(n log n)，不符合要求
   Arrays.sort(nums);
   ```

2. **重复计算**：
   ```java
   // 错误：没有检查起点，会重复计算序列
   for (int num : nums) {
       // 直接向后扩展，会重复计算
   }
   ```

3. **忽略重复元素**：
   ```java
   // 错误：没有去重，可能重复计算
   for (int num : nums) {
       // 如果有重复数字，会重复计算
   }
   ```

这道题的关键在于利用哈希集合的O(1)查找特性，以及只从序列起点开始计算的优化策略！