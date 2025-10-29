# 接雨水（Trapping Rain Water）解题详解

## 解题思路

**总思路**：计算每个位置能接的雨水量，取决于该位置左右两侧的最大高度中的较小值。

**分步骤**：
1. 使用双指针法，从数组两端向中间遍历
2. 维护左右两侧的最大高度
3. 对于每个位置，能接的雨水量 = min(左侧最大高度, 右侧最大高度) - 当前高度
4. 根据左右最大高度的大小关系移动指针

## 归类说明
- **主要归类**：数组、双指针
- **算法技巧**：动态规划、单调栈、双指针
- **相关题型**：盛最多水的容器、柱状图中最大的矩形

## Java代码实现

```java
public class TrappingRainWater {
    /**
     * 双指针解法（最优解）
     * @param height 高度数组
     * @return 能接的雨水总量
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;    // 左侧最大高度
        int rightMax = 0;   // 右侧最大高度
        int water = 0;
        
        while (left < right) {
            // 更新左右最大高度
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            
            // 哪边最大高度小就处理哪边
            if (leftMax < rightMax) {
                // 左边能接的雨水由leftMax决定
                water += leftMax - height[left];
                left++;
            } else {
                // 右边能接的雨水由rightMax决定
                water += rightMax - height[right];
                right--;
            }
        }
        
        return water;
    }
    
    /**
     * 动态规划解法
     * 思路：预先计算每个位置的左右最大高度
     */
    public int trapDP(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        
        int n = height.length;
        int[] leftMax = new int[n];  // 每个位置左侧的最大高度
        int[] rightMax = new int[n]; // 每个位置右侧的最大高度
        
        // 计算左侧最大高度
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        
        // 计算右侧最大高度
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        
        // 计算总雨水量
        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        
        return water;
    }
    
    /**
     * 单调栈解法
     * 思路：使用栈存储递减的高度索引
     */
    public int trapMonotonicStack(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        
        int water = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < height.length; i++) {
            // 当当前高度大于栈顶高度时，说明可以形成凹槽接雨水
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottom = stack.pop(); // 凹槽底部
                
                if (stack.isEmpty()) {
                    break; // 没有左边界，无法接水
                }
                
                int left = stack.peek(); // 左边界
                int currentWidth = i - left - 1; // 宽度
                int currentHeight = Math.min(height[left], height[i]) - height[bottom]; // 高度
                
                water += currentWidth * currentHeight;
            }
            stack.push(i);
        }
        
        return water;
    }
    
    // 测试方法
    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        
        // 测试用例
        int[][] testCases = {
            {0,1,0,2,1,0,1,3,2,1,2,1}, // 预期: 6
            {4,2,0,3,2,5},               // 预期: 9
            {1,0,1},                     // 预期: 1
            {1,1,1},                     // 预期: 0
            {1,2,3},                     // 预期: 0
            {3,2,1},                     // 预期: 0
            {1},                          // 预期: 0
            {}                           // 预期: 0
        };
        
        System.out.println("双指针解法:");
        for (int i = 0; i < testCases.length; i++) {
            int result = solution.trap(testCases[i]);
            System.out.println("输入: " + java.util.Arrays.toString(testCases[i]) + 
                             " -> 输出: " + result);
        }
        
        System.out.println("\n动态规划解法:");
        for (int i = 0; i < testCases.length; i++) {
            int result = solution.trapDP(testCases[i]);
            System.out.println("输入: " + java.util.Arrays.toString(testCases[i]) + 
                             " -> 输出: " + result);
        }
        
        System.out.println("\n单调栈解法:");
        for (int i = 0; i < testCases.length; i++) {
            int result = solution.trapMonotonicStack(testCases[i]);
            System.out.println("输入: " + java.util.Arrays.toString(testCases[i]) + 
                             " -> 输出: " + result);
        }
    }
}
```

## 关键点解析

### 双指针解法核心逻辑

1. **指针移动策略**：
    - 比较左右最大高度，移动较小的一侧
    - 因为能接的雨水量由较小的高度决定

2. **雨水计算**：
    - 对于当前位置，能接的雨水 = 当前侧的最大高度 - 当前高度
    - 这个公式成立是因为另一侧有更高的屏障

### 执行过程示例（height = [0,1,0,2,1,0,1,3,2,1,2,1]）

```
初始: left=0, right=11, leftMax=0, rightMax=0, water=0

步骤1: leftMax=0, rightMax=1 → leftMax < rightMax
        water += 0-0=0, left=1

步骤2: leftMax=1, rightMax=1 → 相等，处理右边
        water += 1-1=0, right=10

步骤3: leftMax=1, rightMax=2 → leftMax < rightMax
        water += 1-1=0, left=2

步骤4: leftMax=1, rightMax=2 → leftMax < rightMax
        water += 1-0=1, left=3

... 继续直到指针相遇

最终water=6
```

### 动态规划解法思路

1. **预处理数组**：
    - `leftMax[i]`：从左边到i位置的最大高度
    - `rightMax[i]`：从右边到i位置的最大高度

2. **雨水计算**：
    - 每个位置能接的雨水 = min(leftMax[i], rightMax[i]) - height[i]

### 单调栈解法思路

1. **栈的性质**：
    - 维护一个递减的栈（存储索引）
    - 当遇到较高柱子时，计算凹槽面积

2. **面积计算**：
    - 宽度 = 当前索引 - 左边界索引 - 1
    - 高度 = min(左边界高度, 当前高度) - 底部高度

## 学习建议

1. **理解核心原理**：
    - 掌握"木桶原理"：能接的雨水量由左右最大高度中的较小值决定
    - 理解为什么双指针法中移动较小的一侧

2. **掌握三种解法**：
    - 双指针：最优解，O(n)时间，O(1)空间
    - 动态规划：直观易懂，O(n)时间，O(n)空间
    - 单调栈：另一种思维方式，适合理解凹槽概念

3. **处理边界情况**：
    - 数组长度小于3
    - 单调递增或递减的数组
    - 所有高度相同的情况

4. **相关题目练习**：
    - 盛最多水的容器（类似的双指针应用）
    - 柱状图中最大的矩形（类似的单调栈应用）
    - 接雨水II（三维情况）

5. **复杂度分析**：
    - 双指针：时间复杂度O(n)，空间复杂度O(1)
    - 动态规划：时间复杂度O(n)，空间复杂度O(n)
    - 单调栈：时间复杂度O(n)，空间复杂度O(n)

6. **调试技巧**：
    - 打印每个位置的左右最大高度
    - 使用小例子手动计算验证
    - 对比三种解法的结果

7. **算法选择**：
    - 面试推荐双指针解法
    - 理解原理推荐动态规划
    - 扩展思维推荐单调栈

## 可视化理解

对于数组 `[0,1,0,2,1,0,1,3,2,1,2,1]`：

```
高度图：
█
█   █
█ █ █ █
█ █ █ █ █
0 1 0 2 1 0 1 3 2 1 2 1

接雨水后：
█
█░░█
█░█░█░█
█░█░█░█░█
0 1 0 2 1 0 1 3 2 1 2 1

每个位置接水量：0,0,1,0,1,2,1,0,0,1,0,0
总水量：6
```

这道题是双指针和动态规划的经典应用，掌握它对解决其他数组相关问题很有帮助！