# 乘积最大子数组（Maximum Product Subarray）解题详解

## 解题思路

**总思路**：使用动态规划，同时维护当前最大乘积和当前最小乘积，因为负数乘以最小值可能变成最大值。

**分步骤**：
1. 维护两个变量：`maxProd`（当前最大乘积）和 `minProd`（当前最小乘积）
2. 遍历数组，对于每个元素：
    - 如果是负数，交换 `maxProd` 和 `minProd`
    - 更新 `maxProd = max(nums[i], maxProd * nums[i])`
    - 更新 `minProd = min(nums[i], minProd * nums[i])`
3. 记录遍历过程中的最大值

## 归类说明
- **主要归类**：动态规划、数组
- **算法技巧**：状态维护、负数处理
- **相关题型**：最大子数组和、子数组最大乘积

## Java代码实现

```java
public class MaximumProductSubarray {
    /**
     * 动态规划解法
     * @param nums 输入数组
     * @return 最大乘积
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 初始化当前最大乘积、当前最小乘积和全局最大乘积
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // 如果当前数是负数，交换最大和最小乘积
            // 因为负数会使大的变小，小的变大
            if (nums[i] < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }
            
            // 更新当前最大乘积和最小乘积
            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);
            
            // 更新全局最大乘积
            result = Math.max(result, maxProd);
        }
        
        return result;
    }
    
    /**
     * 另一种写法：不使用交换，直接计算三种可能
     */
    public int maxProductAlternative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // 同时计算三种可能：当前数本身、当前数乘最大、当前数乘最小
            int tempMax = Math.max(nums[i], Math.max(maxProd * nums[i], minProd * nums[i]));
            int tempMin = Math.min(nums[i], Math.min(maxProd * nums[i], minProd * nums[i]));
            
            maxProd = tempMax;
            minProd = tempMin;
            
            result = Math.max(result, maxProd);
        }
        
        return result;
    }
    
    /**
     * 暴力解法（用于理解问题）
     */
    public int maxProductBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxProduct = Integer.MIN_VALUE;
        
        // 遍历所有可能的子数组
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                maxProduct = Math.max(maxProduct, product);
            }
        }
        
        return maxProduct;
    }
    
    // 测试方法
    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();
        
        // 测试用例
        int[][] testCases = {
            {2, 3, -2, 4},        // 预期: 6 (子数组 [2,3])
            {-2, 0, -1},           // 预期: 0 (子数组 [0] 或 [])
            {-2, 3, -4},           // 预期: 24 (整个数组)
            {0, 2},                // 预期: 2 (子数组 [2])
            {-2},                  // 预期: -2 (整个数组)
            {2, -5, -2, -4, 3},   // 预期: 24 (子数组 [-5,-2,-4] 乘积为-40，但需要更多检查)
            {1, -2, 3, -4, 5}     // 预期: 120 (整个数组)
        };
        
        System.out.println("动态规划解法:");
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int result = solution.maxProduct(nums);
            System.out.println("输入: " + java.util.Arrays.toString(nums) + 
                             " -> 输出: " + result);
        }
        
        System.out.println("\n暴力解法:");
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int result = solution.maxProductBruteForce(nums);
            System.out.println("输入: " + java.util.Arrays.toString(nums) + 
                             " -> 输出: " + result);
        }
    }
}
```

## 关键点解析

### 动态规划核心逻辑

1. **为什么需要维护最小乘积？**
    - 负数乘以最小值可能变成最大值
    - 例如：`[-2, 3, -4]`，当遇到第二个-4时，最小乘积-6 × -4 = 24

2. **负数交换的原理**：
   ```java
   if (nums[i] < 0) {
       int temp = maxProd;
       maxProd = minProd;
       minProd = temp;
   }
   ```
    - 负数会反转大小关系
    - 交换后可以用相同的更新逻辑

### 执行过程示例（nums = [2, 3, -2, 4]）

```
初始: maxProd=2, minProd=2, result=2

i=1: num=3 (正数)
  不交换
  maxProd = max(3, 2×3=6) = 6
  minProd = min(3, 2×3=6) = 3
  result = max(2,6) = 6

i=2: num=-2 (负数)
  交换: maxProd=3, minProd=6
  maxProd = max(-2, 3×-2=-6) = -2
  minProd = min(-2, 6×-2=-12) = -12
  result = max(6,-2) = 6

i=3: num=4 (正数)
  不交换
  maxProd = max(4, -2×4=-8) = 4
  minProd = min(4, -12×4=-48) = -48
  result = max(6,4) = 6

最终结果: 6
```

### 另一个示例（nums = [-2, 3, -4]）

```
初始: maxProd=-2, minProd=-2, result=-2

i=1: num=3 (正数)
  不交换
  maxProd = max(3, -2×3=-6) = 3
  minProd = min(3, -2×3=-6) = -6
  result = max(-2,3) = 3

i=2: num=-4 (负数)
  交换: maxProd=-6, minProd=3
  maxProd = max(-4, -6×-4=24) = 24
  minProd = min(-4, 3×-4=-12) = -12
  result = max(3,24) = 24

最终结果: 24
```

## 学习建议

1. **理解负数处理**：
    - 掌握为什么需要维护最小乘积
    - 理解负数交换的原理

2. **掌握状态转移**：
    - 理解 `maxProd` 和 `minProd` 的更新逻辑
    - 明白为什么需要同时考虑三种情况

3. **处理边界情况**：
    - 空数组
    - 单元素数组
    - 包含0的数组
    - 全负数数组

4. **相关题目练习**：
    - 最大子数组和（Kadane算法）
    - 子数组最小乘积
    - 乘积小于K的子数组

5. **复杂度分析**：
    - 时间复杂度：O(n)
    - 空间复杂度：O(1)

6. **调试技巧**：
    - 打印每个步骤的 `maxProd` 和 `minProd`
    - 使用小例子手动验证
    - 对比暴力解法的结果

7. **算法选择**：
    - 动态规划：最优解，O(n)时间
    - 暴力法：理解问题，O(n²)时间

## 为什么这个算法正确？

### 数学证明
对于每个位置i，最大乘积子数组的结束位置可能是：
1. 当前元素本身 `nums[i]`
2. 前一个最大乘积 × 当前元素 `maxProd * nums[i]`
3. 前一个最小乘积 × 当前元素 `minProd * nums[i]`

通过维护这两个状态，我们可以覆盖所有可能的情况。

### 与最大子数组和的对比
- **最大子数组和**：只需要维护一个状态（当前最大和）
- **最大子数组乘积**：需要维护两个状态（当前最大乘积和当前最小乘积）

这是因为乘法具有符号敏感性，而加法没有。

## 常见错误

1. **只维护最大乘积**：
   ```java
   // 错误：无法处理负数情况
   maxProd = Math.max(nums[i], maxProd * nums[i]);
   ```

2. **忽略0的情况**：
   ```java
   // 错误：当遇到0时，乘积应该重新开始
   // 正确算法会自动处理，因为Math.max(nums[i], ...)会选择nums[i]
   ```

3. **初始化错误**：
   ```java
   // 错误：初始化为0
   int maxProd = 0, minProd = 0;
   // 正确：初始化为第一个元素
   int maxProd = nums[0], minProd = nums[0];
   ```

这道题很好地展示了动态规划中状态设计的重要性，掌握它对解决其他优化问题很有帮助！