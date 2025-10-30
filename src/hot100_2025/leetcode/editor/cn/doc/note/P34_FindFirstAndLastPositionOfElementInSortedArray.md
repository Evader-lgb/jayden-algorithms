# 在排序数组中查找元素的第一个和最后一个位置

## 解题思路

**总思路**：使用两次二分查找，分别查找目标值的起始位置和结束位置。

**分步骤**：
1. 使用二分查找找到目标值的第一个出现位置（左边界）
2. 使用二分查找找到目标值的最后一个出现位置（右边界）
3. 如果找不到目标值，返回[-1, -1]

## 归类说明
- **主要归类**：二分查找、数组
- **算法技巧**：边界查找、二分查找变种
- **相关题型**：搜索插入位置、在排序数组中查找元素

## Java代码实现

```java
public class FindFirstAndLastPosition {
    /**
     * 二分查找解法
     * @param nums 排序数组
     * @param target 目标值
     * @return 目标值的起始和结束位置
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        // 查找第一个位置（左边界）
        int first = findFirstPosition(nums, target);
        // 如果第一个位置都找不到，直接返回[-1, -1]
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        // 查找最后一个位置（右边界）
        int last = findLastPosition(nums, target);
        
        return new int[]{first, last};
    }
    
    /**
     * 查找目标值的第一个出现位置
     */
    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                // 关键：检查是否是第一个出现的位置
                // 如果mid是第一个元素，或者前一个元素不等于target，说明找到了第一个位置
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                } else {
                    // 否则继续在左半部分查找
                    right = mid - 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * 查找目标值的最后一个出现位置
     */
    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                // 关键：检查是否是最后一个出现的位置
                // 如果mid是最后一个元素，或者后一个元素不等于target，说明找到了最后一个位置
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                } else {
                    // 否则继续在右半部分查找
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * 另一种写法：统一的二分查找框架
     */
    public int[] searchRangeUnified(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // 查找左边界
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 检查左边界是否有效
        if (left < nums.length && nums[left] == target) {
            result[0] = left;
        } else {
            return result; // 没找到，直接返回
        }
        
        // 查找右边界
        right = nums.length - 1; // 重置right
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        result[1] = right;
        
        return result;
    }
    
    // 测试方法
    public static void main(String[] args) {
        FindFirstAndLastPosition solution = new FindFirstAndLastPosition();
        
        // 测试用例
        int[][] testCases = {
            {5, 7, 7, 8, 8, 10}, 8,  // 预期: [3, 4]
            {5, 7, 7, 8, 8, 10}, 6,  // 预期: [-1, -1]
            {}, 0,                    // 预期: [-1, -1]
            {1}, 1,                   // 预期: [0, 0]
            {1}, 0,                   // 预期: [-1, -1]
            {2, 2}, 2,                // 预期: [0, 1]
            {1, 2, 3}, 2              // 预期: [1, 1]
        };
        
        System.out.println("分开查找解法:");
        for (int i = 0; i < testCases.length; i += 2) {
            int[] nums = testCases[i];
            int target = (int) testCases[i + 1];
            int[] result = solution.searchRange(nums, target);
            System.out.println("数组: " + java.util.Arrays.toString(nums) + 
                             ", 目标: " + target + " -> 结果: " + 
                             java.util.Arrays.toString(result));
        }
        
        System.out.println("\n统一框架解法:");
        for (int i = 0; i < testCases.length; i += 2) {
            int[] nums = testCases[i];
            int target = (int) testCases[i + 1];
            int[] result = solution.searchRangeUnified(nums, target);
            System.out.println("数组: " + java.util.Arrays.toString(nums) + 
                             ", 目标: " + target + " -> 结果: " + 
                             java.util.Arrays.toString(result));
        }
    }
}
```

## 关键点解析

### 二分查找变种的核心逻辑

1. **查找第一个位置（左边界）**：
    - 当`nums[mid] == target`时，不立即返回
    - 检查`mid`是否是第一个元素，或者前一个元素是否不等于`target`
    - 如果是，则找到左边界；否则继续在左半部分查找

2. **查找最后一个位置（右边界）**：
    - 当`nums[mid] == target`时，不立即返回
    - 检查`mid`是否是最后一个元素，或者后一个元素是否不等于`target`
    - 如果是，则找到右边界；否则继续在右半部分查找

### 执行过程示例（nums = [5,7,7,8,8,10], target = 8）

**查找第一个位置**：
```
初始: left=0, right=5
mid=2: nums[2]=7 < 8 → left=3
mid=4: nums[4]=8 == 8 → 检查nums[3]=8 == 8 → 继续左半部分
mid=3: nums[3]=8 == 8 → 检查nums[2]=7 != 8 → 找到第一个位置3
```

**查找最后一个位置**：
```
初始: left=0, right=5
mid=2: nums[2]=7 < 8 → left=3
mid=4: nums[4]=8 == 8 → 检查nums[5]=10 != 8 → 找到最后一个位置4
```

### 统一框架解法思路

1. **查找左边界**：
    - 使用`nums[mid] >= target`的条件，不断向左压缩
    - 最终`left`指向第一个等于`target`的位置

2. **查找右边界**：
    - 使用`nums[mid] <= target`的条件，不断向右压缩
    - 最终`right`指向最后一个等于`target`的位置

## 学习建议

1. **理解二分查找变种**：
    - 掌握标准二分查找与边界查找的区别
    - 理解为什么找到目标值后不立即返回

2. **掌握两种写法**：
    - 分开查找：逻辑清晰，易于理解
    - 统一框架：代码简洁，体现二分查找的本质

3. **处理边界情况**：
    - 空数组
    - 单元素数组
    - 目标值不存在
    - 目标值在数组边界

4. **相关题目练习**：
    - 搜索插入位置
    - 在排序数组中查找元素的第一个和最后一个位置
    - x的平方根

5. **复杂度分析**：
    - 时间复杂度：O(log n)，两次二分查找
    - 空间复杂度：O(1)

6. **调试技巧**：
    - 打印每次二分查找的中间结果
    - 使用小例子手动验证
    - 检查边界条件的处理

7. **常见错误**：
    - 数组越界（检查`mid-1`或`mid+1`时）
    - 无限循环（边界条件处理不当）
    - 找不到时的返回处理

## 二分查找模板总结

对于边界查找问题，可以记住这个通用模板：

```java
// 查找左边界
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] >= target) {
        right = mid - 1;
    } else {
        left = mid + 1;
    }
}

// 查找右边界  
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] <= target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
```

这道题很好地展示了二分查找的变种应用，掌握它对解决其他搜索问题很有帮助！