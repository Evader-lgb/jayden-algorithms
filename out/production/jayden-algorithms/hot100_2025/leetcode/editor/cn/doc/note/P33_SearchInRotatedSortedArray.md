# 33. 搜索旋转排序数组

## 📝 解题思路（总分结构）

### 总思路
使用二分查找的变种，通过判断哪部分数组是有序的，然后确定目标值在哪一半，逐步缩小搜索范围。

### 分步骤详解
1. **初始化指针**：设置左右指针指向数组两端
2. **二分查找**：在左右指针范围内进行搜索
3. **判断有序部分**：通过比较中间元素与两端元素，确定哪半边是有序的
4. **确定搜索方向**：根据目标值是否在有序的那半边来决定搜索方向
5. **调整指针**：根据判断结果移动左右指针
6. **返回结果**：找到目标值返回索引，否则返回-1

## 🏷️ 算法归类

**主要归类**：二分查找、数组搜索  
**相关知识点**：旋转数组、边界判断、有序性判断  
**难度级别**：中等  
**相似题型**：搜索旋转排序数组II、寻找旋转排序数组中的最小值

## 💻 Java代码实现

```java
class Solution {
    public int search(int[] nums, int target) {
        // 初始化左右指针
        int left = 0;
        int right = nums.length - 1;
        
        // 二分查找循环
        while (left <= right) {
            // 计算中间位置，避免整数溢出
            int mid = left + (right - left) / 2;
            
            // 如果中间元素正好是目标值，直接返回索引
            if (nums[mid] == target) {
                return mid;
            }
            
            // 判断左半部分是否有序（nums[left] <= nums[mid]）
            if (nums[left] <= nums[mid]) {
                // 左半部分是有序的
                
                // 判断目标值是否在左半部分的有序区间内
                if (target >= nums[left] && target < nums[mid]) {
                    // 目标值在左半部分的有序区间内，在左半部分继续搜索
                    right = mid - 1;
                } else {
                    // 目标值不在左半部分，在右半部分继续搜索
                    left = mid + 1;
                }
            } else {
                // 左半部分无序，说明右半部分是有序的
                
                // 判断目标值是否在右半部分的有序区间内
                if (target > nums[mid] && target <= nums[right]) {
                    // 目标值在右半部分的有序区间内，在右半部分继续搜索
                    left = mid + 1;
                } else {
                    // 目标值不在右半部分，在左半部分继续搜索
                    right = mid - 1;
                }
            }
        }
        
        // 没有找到目标值，返回-1
        return -1;
    }
}
```

## 📚 学习建议

### 1. 理解旋转数组的特点

**旋转数组示例**：
```
原始数组: [0,1,2,4,5,6,7]
旋转后: [4,5,6,7,0,1,2] 或 [6,7,0,1,2,4,5]
```

**关键观察**：
- 旋转后的数组至少有一半是有序的
- 可以通过比较 `nums[left]` 和 `nums[mid]` 来判断哪边有序

### 2. 核心判断逻辑详解

**判断左半部分是否有序**：
```java
if (nums[left] <= nums[mid]) {
    // 左半部分有序的情况
    // 示例: [4,5,6,7,0,1,2]中，左半部分[4,5,6,7]有序
} else {
    // 右半部分有序的情况  
    // 示例: [6,7,0,1,2,4,5]中，右半部分[1,2,4,5]有序
}
```

**在有序部分中判断目标值位置**：
```java
// 在有序的左半部分判断
if (target >= nums[left] && target < nums[mid]) {
    // 目标值在有序的左半部分内
    right = mid - 1;
} else {
    // 目标值在无序的右半部分
    left = mid + 1;
}
```

### 3. 算法过程可视化

**示例**：在 `[4,5,6,7,0,1,2]` 中搜索 `0`

```
初始: left=0, right=6, mid=3 → nums[3]=7
左半部分[4,5,6,7]有序，但0不在[4,7]范围内
→ 搜索右半部分: left=4, right=6

第二轮: left=4, right=6, mid=5 → nums[5]=1
左半部分[0,1]有序，但0不在[0,1]范围内？等等，这里需要仔细判断...

实际执行：
left=4, right=6, mid=5
nums[left]=0, nums[mid]=1 → 0<=1，左半部分有序
但target=0 >= nums[left]=0 且 target=0 < nums[mid]=1
→ 在左半部分搜索: right=4

第三轮: left=4, right=4, mid=4 → nums[4]=0 == target
找到目标，返回4
```

### 4. 复杂度分析
- **时间复杂度**：O(log n) - 标准的二分查找复杂度
- **空间复杂度**：O(1) - 只使用了常数级别的额外空间

### 5. 与其他解法对比

**线性搜索（不推荐）**：
```java
// 时间复杂度O(n)，不符合题目要求
for (int i = 0; i < nums.length; i++) {
    if (nums[i] == target) return i;
}
return -1;
```

**先找旋转点再二分**：
```java
// 分两步：先找到旋转点，再在相应的有序部分二分查找
// 时间复杂度也是O(log n)，但实现更复杂
```

### 6. 学习路径建议

1. **先掌握标准二分查找**：理解基本的二分思想
2. **理解旋转数组特性**：掌握如何判断有序部分
3. **练习相似题目**：
    - 81. 搜索旋转排序数组 II（有重复元素）
    - 153. 寻找旋转排序数组中的最小值
    - 154. 寻找旋转排序数组中的最小值 II
4. **手动模拟过程**：用具体例子跟踪算法执行

### 7. 边界情况处理

**代码自动处理的边界情况**：
- 数组完全有序（未旋转）
- 数组完全逆序
- 目标值在旋转点
- 目标值不存在
- 数组只有一个元素

### 8. 关键点调试技巧

**添加调试信息**：
```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        System.out.println("left=" + left + "(" + nums[left] + "), " +
                         "mid=" + mid + "(" + nums[mid] + "), " +
                         "right=" + right + "(" + nums[right] + ")");
        
        if (nums[mid] == target) {
            return mid;
        }
        
        if (nums[left] <= nums[mid]) {
            System.out.println("左半部分有序");
            if (target >= nums[left] && target < nums[mid]) {
                System.out.println("目标在左半部分");
                right = mid - 1;
            } else {
                System.out.println("目标在右半部分");
                left = mid + 1;
            }
        } else {
            System.out.println("右半部分有序");
            if (target > nums[mid] && target <= nums[right]) {
                System.out.println("目标在右半部分");
                left = mid + 1;
            } else {
                System.out.println("目标在左半部分");
                right = mid - 1;
            }
        }
    }
    return -1;
}
```

### 9. 常见错误避免

```java
// 错误：边界条件写反
if (target >= nums[left] && target < nums[mid]) 
// 不要写成 target > nums[left]，会漏掉等于的情况

// 错误：整数溢出
int mid = (left + right) / 2;  // 可能溢出
// 应该用：left + (right - left) / 2

// 错误：忘记处理相等情况
if (nums[left] < nums[mid])  // 应该用 <=
```

### 10. 实际应用场景
- 数据库索引搜索
- 日志时间戳搜索
- 循环缓冲区数据查找
- 时间序列数据分析

## 🎯 核心思想总结

这个算法的精妙之处在于：

1. **利用有序性**：虽然整体无序，但至少一半是有序的
2. **二分思想**：每次排除一半的搜索空间
3. **条件判断**：通过比较确定目标值在有序部分还是无序部分
4. **边界处理**：仔细处理等于情况，避免漏解

通过这道题，你将掌握处理部分有序数组搜索问题的核心技巧，这是二分查找算法的重要变种！