# 长度最小的子数组（Minimum Size Subarray Sum）解题详解

## 解题思路

**总思路**：使用滑动窗口（双指针）技术，维护一个满足和≥target的窗口，寻找最小长度的窗口。

**分步骤**：
1. 使用左右指针维护滑动窗口
2. 右指针不断向右移动，扩大窗口直到窗口和≥target
3. 然后左指针向右移动，缩小窗口直到窗口和<target
4. 在每次满足条件时更新最小窗口长度
5. 返回最小长度

## 归类说明
- **主要归类**：滑动窗口、双指针
- **算法技巧**：窗口收缩、前缀和
- **相关题型**：最小覆盖子串、水果成篮、找到字符串中所有字母异位词

## Java代码实现

```java
public class MinimumSizeSubarraySum {
    /**
     * 滑动窗口解法
     * @param target 目标值
     * @param nums 数组
     * @return 最小子数组长度
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;           // 左指针
        int sum = 0;            // 当前窗口和
        int minLength = Integer.MAX_VALUE; // 最小长度
        
        // 右指针遍历数组
        for (int right = 0; right < nums.length; right++) {
            // 扩大窗口：右指针向右移动，增加当前元素
            sum += nums[right];
            
            // 当窗口和满足条件时，收缩窗口
            while (sum >= target) {
                // 更新最小长度
                minLength = Math.min(minLength, right - left + 1);
                // 收缩窗口：左指针向右移动，减去左边元素
                sum -= nums[left];
                left++;
            }
        }
        
        // 如果没找到满足条件的子数组，返回0
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    /**
     * 前缀和 + 二分查找解法
     */
    public int minSubArrayLenBinarySearch(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        
        // 构建前缀和数组
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        // 对每个起始位置，使用二分查找找到满足条件的最小结束位置
        for (int i = 0; i < n; i++) {
            int toFind = target + prefixSum[i];
            
            // 二分查找：找到第一个大于等于toFind的位置
            int left = i + 1, right = n;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (prefixSum[mid] < toFind) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
            // 如果找到了满足条件的位置
            if (left <= n) {
                minLength = Math.min(minLength, left - i);
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    /**
     * 暴力解法（用于理解问题）
     */
    public int minSubArrayLenBruteForce(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int minLength = Integer.MAX_VALUE;
        int n = nums.length;
        
        // 遍历所有可能的子数组起始位置
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // 遍历所有可能的结束位置
            for (int j = i; j < n; j++) {
                sum += nums[j];
                // 如果满足条件，更新最小长度
                if (sum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break; // 因为要找最短的，所以找到第一个满足的就可以跳出内层循环
                }
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    // 测试方法
    public static void main(String[] args) {
        MinimumSizeSubarraySum solution = new MinimumSizeSubarraySum();
        
        // 测试用例
        int[][] testCases = {
            {7, 2, 3, 1, 2, 4, 3},     // target=7, 预期: 2 ([4,3])
            {4, 1, 4, 4},               // target=4, 预期: 1 ([4])
            {11, 1, 1, 1, 1, 1, 1, 1}, // target=11, 预期: 0
            {15, 1, 2, 3, 4, 5},       // target=15, 预期: 5 ([1,2,3,4,5])
            {6, 10, 2, 3},              // target=6, 预期: 1 ([10])
            {3, 1, 1, 1}                // target=3, 预期: 3 ([1,1,1])
        };
        
        System.out.println("滑动窗口解法:");
        for (int[] testCase : testCases) {
            int target = testCase[0];
            int[] nums = new int[testCase.length - 1];
            System.arraycopy(testCase, 1, nums, 0, nums.length);
            
            int result = solution.minSubArrayLen(target, nums);
            System.out.println("target=" + target + ", nums=" + 
                             java.util.Arrays.toString(nums) + " -> " + result);
        }
        
        System.out.println("\n前缀和+二分查找解法:");
        for (int[] testCase : testCases) {
            int target = testCase[0];
            int[] nums = new int[testCase.length - 1];
            System.arraycopy(testCase, 1, nums, 0, nums.length);
            
            int result = solution.minSubArrayLenBinarySearch(target, nums);
            System.out.println("target=" + target + ", nums=" + 
                             java.util.Arrays.toString(nums) + " -> " + result);
        }
        
        System.out.println("\n暴力解法:");
        for (int[] testCase : testCases) {
            int target = testCase[0];
            int[] nums = new int[testCase.length - 1];
            System.arraycopy(testCase, 1, nums, 0, nums.length);
            
            int result = solution.minSubArrayLenBruteForce(target, nums);
            System.out.println("target=" + target + ", nums=" + 
                             java.util.Arrays.toString(nums) + " -> " + result);
        }
    }
}
```

## 关键点解析

### 滑动窗口核心逻辑

1. **窗口扩展**：
    - 右指针向右移动，增加元素到窗口和
    - 直到窗口和≥target

2. **窗口收缩**：
    - 当窗口和≥target时，左指针向右移动
    - 从窗口和中减去左边元素
    - 直到窗口和<target

3. **长度更新**：
    - 在每次窗口和≥target时更新最小长度
    - 确保找到的是最小窗口

### 执行过程示例（target=7, nums=[2,3,1,2,4,3]）

```
初始: left=0, sum=0, minLength=∞

right=0: sum=2 → 不满足
right=1: sum=5 → 不满足  
right=2: sum=6 → 不满足
right=3: sum=8 → 满足条件
    minLength=min(∞, 3-0+1)=4
    sum=8-2=6, left=1 → 不满足
right=4: sum=6+4=10 → 满足条件
    minLength=min(4, 4-1+1)=4
    sum=10-3=7 → 仍满足
    minLength=min(4, 4-2+1)=3
    sum=7-1=6, left=3 → 不满足
right=5: sum=6+3=9 → 满足条件
    minLength=min(3, 5-3+1)=3
    sum=9-2=7 → 仍满足
    minLength=min(3, 5-4+1)=2
    sum=7-4=3, left=5 → 不满足

最终结果: 2
```

### 前缀和+二分查找思路

1. **前缀和数组**：
    - `prefixSum[i]` 表示前i个元素的和
    - `prefixSum[j] - prefixSum[i]` 表示子数组`[i, j-1]`的和

2. **二分查找**：
    - 对于每个起始位置i，找最小的j使得`prefixSum[j] - prefixSum[i] ≥ target`
    - 即找第一个`prefixSum[j] ≥ target + prefixSum[i]`

## 学习建议

1. **理解滑动窗口**：
    - 掌握窗口扩展和收缩的时机
    - 理解为什么需要内层while循环

2. **掌握多种解法**：
    - 滑动窗口：最优解，O(n)时间
    - 前缀和+二分：O(n log n)时间，另一种思路
    - 暴力法：理解问题本质

3. **处理边界情况**：
    - 空数组
    - 不存在满足条件的子数组
    - 单个元素就满足条件
    - 需要整个数组才能满足条件

4. **相关题目练习**：
    - 最小覆盖子串
    - 水果成篮
    - 找到字符串中所有字母异位词

5. **复杂度分析**：
    - 滑动窗口：时间复杂度O(n)，空间复杂度O(1)
    - 前缀和+二分：时间复杂度O(n log n)，空间复杂度O(n)
    - 暴力法：时间复杂度O(n²)，空间复杂度O(1)

6. **调试技巧**：
    - 打印每次窗口移动后的状态
    - 使用小例子手动验证
    - 检查边界条件的处理

7. **算法选择**：
    - 面试推荐滑动窗口解法
    - 理解原理推荐多种实现
    - 实际应用根据数据规模选择

## 为什么滑动窗口有效？

### 正确性保证
1. **不会漏解**：右指针遍历所有可能的右边界
2. **找到最优**：对于每个右边界，都找到对应的最小窗口
3. **高效性**：每个元素最多被左右指针各访问一次

### 与暴力解法的对比
**暴力解法**：检查所有可能的子数组，O(n²)时间
**滑动窗口**：利用前一个窗口的信息，O(n)时间

## 常见错误

1. **忘记初始化minLength**：
   ```java
   // 错误：没有初始化
   int minLength;
   // 正确：初始化为最大值
   int minLength = Integer.MAX_VALUE;
   ```

2. **窗口收缩条件错误**：
   ```java
   // 错误：使用if而不是while
   if (sum >= target) {
       // 可能错过更小的窗口
   }
   // 正确：使用while循环持续收缩
   while (sum >= target) {
       // ...
   }
   ```

3. **返回结果处理错误**：
   ```java
   // 错误：直接返回minLength
   return minLength;
   // 正确：检查是否找到解
   return minLength == Integer.MAX_VALUE ? 0 : minLength;
   ```

这道题是滑动窗口技术的经典应用，掌握它对解决其他子数组问题很有帮助！