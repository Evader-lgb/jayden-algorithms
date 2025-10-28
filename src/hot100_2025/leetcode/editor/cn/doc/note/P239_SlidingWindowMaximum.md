# 滑动窗口最大值（Sliding Window Maximum）解题详解

## 解题思路

**总思路**：使用双端队列（Deque）维护一个单调递减队列，队列头部始终是当前窗口的最大值。

**分步骤**：
1. 使用双端队列存储数组索引，保持队列中索引对应的值是递减的
2. 遍历数组，对于每个元素：
    - 移除队列中不在当前窗口范围内的索引
    - 从队列尾部移除所有小于当前元素的索引
    - 将当前元素索引加入队列
    - 当窗口形成时，将队列头部对应的值加入结果

## 归类说明
- **主要归类**：滑动窗口、单调队列
- **算法技巧**：双端队列、单调栈思想
- **相关题型**：滑动窗口中位数、滑动窗口平均值、最短无序连续子数组

## Java代码实现

```java
import java.util.*;

public class SlidingWindowMaximum {
    /**
     * 双端队列解法
     * @param nums 输入数组
     * @param k 窗口大小
     * @return 每个滑动窗口的最大值数组
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1]; // 结果数组
        int resultIndex = 0;
        
        // 双端队列，存储数组索引（保持索引对应的值单调递减）
        Deque<Integer> deque = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            // 1. 移除队列中不在当前窗口范围内的索引
            // 队列头部的索引如果已经超出窗口范围，则移除
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // 2. 从队列尾部移除所有小于当前元素的索引
            // 保持队列的单调递减性质
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            // 3. 将当前元素索引加入队列尾部
            deque.offerLast(i);
            
            // 4. 当窗口形成时（i >= k-1），将队列头部对应的值加入结果
            if (i >= k - 1) {
                result[resultIndex++] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * 动态规划解法（分块+预处理）
     */
    public int[] maxSlidingWindowDP(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        
        // 左数组：left[i] 表示从块开始到i的最大值
        int[] left = new int[n];
        // 右数组：right[i] 表示从i到块结束的最大值
        int[] right = new int[n];
        
        // 填充left数组
        left[0] = nums[0];
        for (int i = 1; i < n; i++) {
            if (i % k == 0) {
                // 当前是块的开始
                left[i] = nums[i];
            } else {
                // 延续前一个的最大值比较
                left[i] = Math.max(left[i - 1], nums[i]);
            }
        }
        
        // 填充right数组
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if ((i + 1) % k == 0) {
                // 下一个是块的开始，当前是块的结束
                right[i] = nums[i];
            } else {
                // 延续后一个的最大值比较
                right[i] = Math.max(right[i + 1], nums[i]);
            }
        }
        
        // 计算每个窗口的最大值
        for (int i = 0; i < n - k + 1; i++) {
            // 窗口[i, i+k-1]的最大值 = max(right[i], left[i+k-1])
            result[i] = Math.max(right[i], left[i + k - 1]);
        }
        
        return result;
    }
    
    // 测试方法
    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        
        // 测试用例
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("测试用例1:");
        System.out.println("输入: " + Arrays.toString(nums1) + ", k=" + k1);
        System.out.println("双端队列结果: " + Arrays.toString(solution.maxSlidingWindow(nums1, k1)));
        System.out.println("动态规划结果: " + Arrays.toString(solution.maxSlidingWindowDP(nums1, k1)));
        // 预期: [3, 3, 5, 5, 6, 7]
        
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("\n测试用例2:");
        System.out.println("输入: " + Arrays.toString(nums2) + ", k=" + k2);
        System.out.println("双端队列结果: " + Arrays.toString(solution.maxSlidingWindow(nums2, k2)));
        System.out.println("动态规划结果: " + Arrays.toString(solution.maxSlidingWindowDP(nums2, k2)));
        // 预期: [1]
        
        int[] nums3 = {1, -1};
        int k3 = 1;
        System.out.println("\n测试用例3:");
        System.out.println("输入: " + Arrays.toString(nums3) + ", k=" + k3);
        System.out.println("双端队列结果: " + Arrays.toString(solution.maxSlidingWindow(nums3, k3)));
        System.out.println("动态规划结果: " + Arrays.toString(solution.maxSlidingWindowDP(nums3, k3)));
        // 预期: [1, -1]
        
        int[] nums4 = {9, 11};
        int k4 = 2;
        System.out.println("\n测试用例4:");
        System.out.println("输入: " + Arrays.toString(nums4) + ", k=" + k4);
        System.out.println("双端队列结果: " + Arrays.toString(solution.maxSlidingWindow(nums4, k4)));
        System.out.println("动态规划结果: " + Arrays.toString(solution.maxSlidingWindowDP(nums4, k4)));
        // 预期: [11]
        
        int[] nums5 = {4, -2};
        int k5 = 2;
        System.out.println("\n测试用例5:");
        System.out.println("输入: " + Arrays.toString(nums5) + ", k=" + k5);
        System.out.println("双端队列结果: " + Arrays.toString(solution.maxSlidingWindow(nums5, k5)));
        System.out.println("动态规划结果: " + Arrays.toString(solution.maxSlidingWindowDP(nums5, k5)));
        // 预期: [4]
    }
}
```

## 关键点解析

### 双端队列解法核心逻辑

1. **队列性质**：
    - 队列中索引对应的值是单调递减的
    - 队列头部始终是当前窗口的最大值

2. **维护操作**：
    - **移除过期元素**：检查队列头部索引是否还在窗口内
    - **维护单调性**：从尾部移除所有小于当前元素的索引
    - **添加新元素**：当前元素索引加入队列尾部

### 执行过程示例（nums = [1,3,-1,-3,5,3,6,7], k = 3）

```
i=0: deque=[0]           -> 无输出
i=1: deque=[1]           -> 移除0(1<3), 无输出  
i=2: deque=[1,2]         -> 输出nums[1]=3
i=3: deque=[1,2,3]       -> 输出nums[1]=3
i=4: deque=[4]           -> 移除1,2,3(都<5), 输出nums[4]=5
i=5: deque=[4,5]         -> 输出nums[4]=5
i=6: deque=[6]           -> 移除4,5(6>5,3), 输出nums[6]=6
i=7: deque=[7]           -> 移除6(7>6), 输出nums[7]=7

结果: [3,3,5,5,6,7]
```

### 动态规划解法核心逻辑

1. **分块思想**：
    - 将数组分为大小为k的块
    - 预处理每个位置在块内的左右最大值

2. **数组定义**：
    - `left[i]`：从块开始到i的最大值
    - `right[i]`：从i到块结束的最大值

3. **窗口最大值**：
    - 窗口[i, i+k-1]的最大值 = max(right[i], left[i+k-1])

## 学习建议

1. **理解单调队列**：
    - 掌握双端队列的维护方法
    - 理解为什么队列是单调递减的

2. **掌握两种解法**：
    - 双端队列：通用解法，时间复杂度最优
    - 动态规划：空间换时间，适合理解分块思想

3. **处理边界情况**：
    - 数组为空或长度为1
    - 窗口大小为1
    - 数组元素为负数

4. **相关题目练习**：
    - 滑动窗口最小值
    - 滑动窗口中位数
    - 最短无序连续子数组

5. **复杂度分析**：
    - 时间复杂度：
        - 双端队列：O(n)，每个元素入队出队一次
        - 动态规划：O(n)，三次遍历数组
    - 空间复杂度：
        - 双端队列：O(k)，队列最大长度
        - 动态规划：O(n)，需要左右数组

6. **调试技巧**：
    - 打印队列的中间状态
    - 使用小例子手动验证
    - 检查边界索引的计算

7. **算法选择**：
    - 双端队列：通常作为首选解法
    - 动态规划：当需要避免使用队列时考虑

这道题很好地展示了单调队列的应用，掌握它对解决其他滑动窗口问题很有帮助！