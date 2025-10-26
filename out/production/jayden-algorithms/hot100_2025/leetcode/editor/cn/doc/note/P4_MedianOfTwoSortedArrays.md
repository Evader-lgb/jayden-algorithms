# 4. 寻找两个正序数组的中位数

## 📝 解题思路（总分结构）

### 总思路
使用二分查找在两个有序数组中找到合适的分割位置，使得左边元素个数等于右边（或差1），从而快速找到中位数。

### 分步骤详解
1. **保证第一个数组较短**：如果第一个数组较长，交换两个数组
2. **二分查找分割点**：在较短的数组中使用二分查找找到合适的分割位置
3. **确定分割条件**：确保左半部分所有元素 ≤ 右半部分所有元素
4. **计算中位数**：根据总长度的奇偶性返回相应的中位数

## 🏷️ 算法归类

**主要归类**：二分查找、数组、分治算法  
**相关知识点**：中位数定义、边界处理、复杂度优化  
**难度级别**：困难  
**相似题型**：寻找两个有序数组的第K小元素、数据流的中位数

## 💻 Java代码实现

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 步骤1：保证第一个数组是较短的数组，这样二分查找的效率更高
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        // 步骤2：在较短的数组nums1上进行二分查找
        int left = 0;
        int right = m;
        
        while (left <= right) {
            // 在nums1中选择分割点i
            int i = (left + right) / 2;
            // 在nums2中对应的分割点j，使得左半部分元素总数 = 右半部分元素总数（或差1）
            int j = (m + n + 1) / 2 - i;
            
            // 步骤3：处理边界情况，定义四个关键值
            // nums1左半部分的最大值（如果i=0，则没有左半部分，设为最小整数）
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            // nums1右半部分的最小值（如果i=m，则没有右半部分，设为最大整数）
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            // nums2左半部分的最大值（如果j=0，则没有左半部分，设为最小整数）
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            // nums2右半部分的最小值（如果j=n，则没有右半部分，设为最大整数）
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];
            
            // 步骤4：检查当前分割是否满足条件
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 找到了正确的分割位置
                if ((m + n) % 2 == 0) {
                    // 总长度为偶数，中位数是左半部分最大值和右半部分最小值的平均值
                    return (Math.max(nums1LeftMax, nums2LeftMax) + 
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                } else {
                    // 总长度为奇数，中位数是左半部分的最大值
                    return Math.max(nums1LeftMax, nums2LeftMax);
                }
            } else if (nums1LeftMax > nums2RightMin) {
                // nums1的左半部分太大，需要向左移动分割点
                right = i - 1;
            } else {
                // nums1的左半部分太小，需要向右移动分割点
                left = i + 1;
            }
        }
        
        // 理论上不会执行到这里
        return 0.0;
    }
}
```

## 📚 学习建议

### 1. 理解核心分割思想

**分割的目标**：
```
nums1: [a1, a2, ..., ai-1] | [ai, ai+1, ..., am]
nums2: [b1, b2, ..., bj-1] | [bj, bj+1, ..., bn]

需要满足：
1. 左半部分元素个数 = 右半部分元素个数（或差1）
2. 所有左半部分元素 ≤ 所有右半部分元素
```

**关键公式**：
```java
// 确保左右两部分元素数量平衡
j = (m + n + 1) / 2 - i;

// 当总长度为偶数时：(m+n)是偶数
// 左半部分元素数 = (m+n)/2
// 右半部分元素数 = (m+n)/2

// 当总长度为奇数时：(m+n)是奇数  
// 左半部分元素数 = (m+n+1)/2
// 右半部分元素数 = (m+n)/2
```

### 2. 算法过程可视化

**示例**：`nums1 = [1, 3]`, `nums2 = [2]`

```
初始：m=2, n=1
在nums1上二分查找：left=0, right=2

第一次迭代：i=1, j=(2+1+1)/2-1=1
nums1分割：[1] | [3]
nums2分割：[2] | []
检查：maxLeft = max(1,2)=2, minRight = min(3,∞)=3
满足条件：2 <= 3
总长度3是奇数，返回maxLeft=2
```

### 3. 复杂度分析
- **时间复杂度**：O(log(min(m,n))) - 在较短的数组上进行二分查找
- **空间复杂度**：O(1) - 只使用了常数级别的额外空间

### 4. 边界情况详解

**四种边界情况**：
```java
// 情况1：i=0（nums1全部在右边）
nums1LeftMax = Integer.MIN_VALUE
nums1RightMin = nums1[0]

// 情况2：i=m（nums1全部在左边）  
nums1LeftMax = nums1[m-1]
nums1RightMin = Integer.MAX_VALUE

// 情况3：j=0（nums2全部在右边）
nums2LeftMax = Integer.MIN_VALUE
nums2RightMin = nums2[0]

// 情况4：j=n（nums2全部在左边）
nums2LeftMax = nums2[n-1]
nums2RightMin = Integer.MAX_VALUE
```

### 5. 学习路径建议

1. **先理解暴力解法**：合并数组求中位数，理解问题本质
2. **掌握二分思想**：理解如何在两个数组上同时分割
3. **理解边界处理**：这是算法的难点和关键点
4. **练习相似题目**：
    - 寻找两个有序数组的第K小元素
    - 295. 数据流的中位数

### 6. 调试技巧

**添加调试信息**：
```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
        return findMedianSortedArrays(nums2, nums1);
    }
    
    int m = nums1.length, n = nums2.length;
    int left = 0, right = m;
    
    System.out.println("m=" + m + ", n=" + n);
    
    while (left <= right) {
        int i = (left + right) / 2;
        int j = (m + n + 1) / 2 - i;
        
        System.out.println("i=" + i + ", j=" + j);
        
        int nums1Left = (i == 0) ? Integer.MIN_VALUE : nums1[i-1];
        int nums1Right = (i == m) ? Integer.MAX_VALUE : nums1[i];
        int nums2Left = (j == 0) ? Integer.MIN_VALUE : nums2[j-1];
        int nums2Right = (j == n) ? Integer.MAX_VALUE : nums2[j];
        
        System.out.println("nums1: " + nums1Left + " | " + nums1Right);
        System.out.println("nums2: " + nums2Left + " | " + nums2Right);
        
        if (nums1Left <= nums2Right && nums2Left <= nums1Right) {
            double result = (m + n) % 2 == 0 ? 
                (Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) / 2.0 :
                Math.max(nums1Left, nums2Left);
            System.out.println("找到结果: " + result);
            return result;
        } else if (nums1Left > nums2Right) {
            System.out.println("向左移动");
            right = i - 1;
        } else {
            System.out.println("向右移动");
            left = i + 1;
        }
    }
    
    return 0.0;
}
```

### 7. 常见错误避免

```java
// 错误：没有保证第一个数组较短
// 这会导致二分查找效率降低，甚至出错

// 错误：边界条件处理不当
// 必须处理i=0, i=m, j=0, j=n的情况

// 错误：整数除法问题
// 计算平均值时要使用2.0而不是2

// 错误：分割点计算公式错误
// j = (m + n + 1) / 2 - i 不是 j = (m + n) / 2 - i
```

### 8. 实际应用场景
- 数据库查询优化中的中位数计算
- 大数据分析中的统计指标
- 机器学习中的特征工程
- 实时数据流的中位数监控

## 🎯 核心思想总结

### 1. 问题转化
将找中位数问题转化为**在两个有序数组中找到合适的分割点**，使得：
- 左半部分所有元素 ≤ 右半部分所有元素
- 左右两部分元素数量平衡

### 2. 二分查找的应用
在较短的数组上进行二分查找，利用有序性快速定位分割点。

### 3. 边界处理的技巧
使用极值（Integer.MIN_VALUE/MAX_VALUE）来处理数组边界情况，简化逻辑。

### 4. 复杂度优化
通过保证在较短的数组上二分，将时间复杂度优化到O(log(min(m,n)))。

## 💡 更直观的理解方式

**想象两个数组的合并**：
```
数组1: [1, 3, 5]
数组2: [2, 4, 6]

合并后: [1, 2, 3, 4, 5, 6]
中位数: (3+4)/2 = 3.5

我们的算法不需要实际合并，而是通过二分查找
找到虚拟的"合并数组"的中位数位置。
```

**分割的物理意义**：
```
在正确分割时：
- 左半部分的最大值 ≤ 右半部分的最小值
- 左半部分包含大约一半的元素
- 这个分割点就是中位数的位置
```

这道题是二分查找算法的高阶应用，理解后对掌握复杂的搜索问题有很大帮助！建议多用手动模拟几个例子来加深理解。