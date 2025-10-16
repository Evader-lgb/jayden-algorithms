# 912. 排序数组

## 📝 解题思路（总分结构）

### 总思路
使用快速排序算法，通过分治思想将数组分为较小和较大的两部分，递归地对这两部分进行排序。

### 分步骤详解
1. **边界情况处理**：如果数组为空或只有一个元素，直接返回
2. **选择基准值**：从数组中选择一个元素作为基准（pivot）
3. **分区操作**：将数组重新排列，所有小于基准的元素放在左边，大于基准的放在右边
4. **递归排序**：对基准左右两边的子数组递归地进行快速排序
5. **合并结果**：由于是原地排序，不需要显式合并

## 🏷️ 算法归类

**主要归类**：排序算法、分治算法  
**相关知识点**：快速排序、递归、数组分区  
**难度级别**：中等  
**相似题型**：数组中的第K个最大元素、颜色分类、摆动排序

## 💻 Java代码实现

### 解法一：快速排序（基础版本）

```java
class Solution {
    public int[] sortArray(int[] nums) {
        // 边界情况处理：如果数组为空或只有一个元素，直接返回
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        
        // 调用快速排序函数，对整个数组进行排序
        quickSort(nums, 0, nums.length - 1);
        
        // 返回排序后的数组
        return nums;
    }
    
    /**
     * 快速排序递归函数
     * @param nums 要排序的数组
     * @param left 当前子数组的左边界
     * @param right 当前子数组的右边界
     */
    private void quickSort(int[] nums, int left, int right) {
        // 递归终止条件：当左边界大于等于右边界时，说明子数组已经有序
        if (left >= right) {
            return;
        }
        
        // 步骤1：进行分区操作，返回基准值的最终位置
        int pivotIndex = partition(nums, left, right);
        
        // 步骤2：递归排序基准值左边的子数组
        quickSort(nums, left, pivotIndex - 1);
        
        // 步骤3：递归排序基准值右边的子数组
        quickSort(nums, pivotIndex + 1, right);
    }
    
    /**
     * 分区函数：将数组重新排列，所有小于基准的元素放在左边，大于基准的放在右边
     * @param nums 数组
     * @param left 左边界
     * @param right 右边界
     * @return 基准值的最终位置
     */
    private int partition(int[] nums, int left, int right) {
        // 选择最右边的元素作为基准值
        int pivot = nums[right];
        
        // i指针指向小于基准值的区域的最后一个位置
        int i = left - 1;
        
        // 遍历从left到right-1的所有元素
        for (int j = left; j < right; j++) {
            // 如果当前元素小于等于基准值
            if (nums[j] <= pivot) {
                // 将i指针向右移动一位
                i++;
                // 交换nums[i]和nums[j]，将较小的元素移到左边
                swap(nums, i, j);
            }
        }
        
        // 将基准值放到正确的位置（i+1的位置）
        swap(nums, i + 1, right);
        
        // 返回基准值的最终位置
        return i + 1;
    }
    
    /**
     * 交换数组中两个元素的位置
     * @param nums 数组
     * @param i 第一个元素的索引
     * @param j 第二个元素的索引
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### 解法二：快速排序（优化版本 - 三路快排）

```java
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        
        // 使用三路快速排序，对包含大量重复元素的数组更高效
        quickSortThreeWay(nums, 0, nums.length - 1);
        return nums;
    }
    
    /**
     * 三路快速排序：将数组分为小于、等于、大于基准值的三部分
     * @param nums 数组
     * @param left 左边界
     * @param right 右边界
     */
    private void quickSortThreeWay(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        
        // 随机选择基准值，避免在已排序数组上出现最坏情况
        int randomIndex = left + (int)(Math.random() * (right - left + 1));
        swap(nums, randomIndex, right);
        int pivot = nums[right];
        
        // 初始化三个指针：
        int lt = left;      // lt指向小于pivot区域的末尾
        int gt = right;     // gt指向大于pivot区域的开始
        int i = left;       // i是当前遍历的指针
        
        while (i <= gt) {
            if (nums[i] < pivot) {
                // 当前元素小于pivot，交换到lt位置
                swap(nums, lt, i);
                lt++;
                i++;
            } else if (nums[i] > pivot) {
                // 当前元素大于pivot，交换到gt位置
                swap(nums, i, gt);
                gt--;
                // 注意：这里不移动i，因为交换过来的元素还没检查
            } else {
                // 当前元素等于pivot，直接移动i
                i++;
            }
        }
        
        // 递归排序小于pivot的部分
        quickSortThreeWay(nums, left, lt - 1);
        // 递归排序大于pivot的部分
        quickSortThreeWay(nums, gt + 1, right);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### 解法三：归并排序（稳定排序）

```java
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        
        // 创建临时数组，用于归并操作
        int[] temp = new int[nums.length];
        
        // 调用归并排序
        mergeSort(nums, 0, nums.length - 1, temp);
        
        return nums;
    }
    
    /**
     * 归并排序递归函数
     * @param nums 原数组
     * @param left 左边界
     * @param right 右边界
     * @param temp 临时数组
     */
    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        // 递归终止条件
        if (left >= right) {
            return;
        }
        
        // 计算中间位置
        int mid = left + (right - left) / 2;
        
        // 递归排序左半部分
        mergeSort(nums, left, mid, temp);
        // 递归排序右半部分
        mergeSort(nums, mid + 1, right, temp);
        
        // 合并两个有序子数组
        merge(nums, left, mid, right, temp);
    }
    
    /**
     * 合并两个有序子数组
     * @param nums 原数组
     * @param left 左边界
     * @param mid 中间位置
     * @param right 右边界
     * @param temp 临时数组
     */
    private void merge(int[] nums, int left, int mid, int right, int[] temp) {
        // 复制要合并的区域到临时数组
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }
        
        // 初始化两个指针，分别指向两个子数组的起始位置
        int i = left;       // 左子数组指针
        int j = mid + 1;    // 右子数组指针
        int k = left;       // 原数组指针
        
        // 比较两个子数组的元素，将较小的放入原数组
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
            }
            k++;
        }
        
        // 如果左子数组还有剩余元素，直接复制到原数组
        while (i <= mid) {
            nums[k] = temp[i];
            i++;
            k++;
        }
        
        // 如果右子数组还有剩余元素，直接复制到原数组
        // 注意：右子数组的剩余元素已经在正确位置，不需要处理
    }
}
```

## 📚 学习建议

### 1. 理解快速排序的核心思想

**分治思想**：
- **分**：选择基准值将数组分成两个子数组
- **治**：递归地对子数组进行排序
- **合**：由于是原地排序，不需要显式合并

**分区过程可视化**：
```
数组: [3, 1, 4, 1, 5, 9, 2, 6], pivot = 6

分区过程：
i = -1, j从0到6:
j=0: 3<=6 → i=0, 交换(0,0) → [3,1,4,1,5,9,2,6]
j=1: 1<=6 → i=1, 交换(1,1) → [3,1,4,1,5,9,2,6]  
j=2: 4<=6 → i=2, 交换(2,2) → [3,1,4,1,5,9,2,6]
j=3: 1<=6 → i=3, 交换(3,3) → [3,1,4,1,5,9,2,6]
j=4: 5<=6 → i=4, 交换(4,4) → [3,1,4,1,5,9,2,6]
j=5: 9>6 → 跳过
j=6: 2<=6 → i=5, 交换(5,6) → [3,1,4,1,5,2,9,6]

最后交换pivot: 交换(5,7) → [3,1,4,1,5,2,6,9]
```

### 2. 复杂度分析

**快速排序**：
- **平均时间复杂度**：O(n log n)
- **最坏时间复杂度**：O(n²) - 当数组已排序且选择最值作为基准时
- **空间复杂度**：O(log n) - 递归栈的深度

**归并排序**：
- **时间复杂度**：O(n log n)
- **空间复杂度**：O(n) - 需要临时数组

### 3. 不同排序算法的选择

| 算法 | 平均时间复杂度 | 最坏时间复杂度 | 空间复杂度 | 稳定性 | 适用场景 |
|------|----------------|----------------|------------|--------|----------|
| 快速排序 | O(n log n) | O(n²) | O(log n) | 不稳定 | 通用排序 |
| 归并排序 | O(n log n) | O(n log n) | O(n) | 稳定 | 需要稳定性时 |
| 堆排序 | O(n log n) | O(n log n) | O(1) | 不稳定 | 空间受限时 |

### 4. 学习路径建议

1. **先掌握快速排序**：理解分治思想的基础
2. **理解分区过程**：这是快速排序的核心
3. **学习优化技巧**：随机化基准、三路快排
4. **掌握归并排序**：理解稳定排序的应用场景
5. **练习相似题目**：
    - 215. 数组中的第K个最大元素
    - 75. 颜色分类
    - 148. 排序链表

### 5. 关键技巧详解

**快速排序的优化**：
```java
// 1. 随机选择基准值，避免最坏情况
int randomIndex = left + (int)(Math.random() * (right - left + 1));
swap(nums, randomIndex, right);

// 2. 三路快排处理重复元素
// 将数组分为 <pivot, =pivot, >pivot 三部分
```

**归并排序的合并技巧**：
```java
// 使用双指针合并两个有序数组
while (i <= mid && j <= right) {
    if (temp[i] <= temp[j]) {
        nums[k++] = temp[i++];
    } else {
        nums[k++] = temp[j++];
    }
}
```

### 6. 调试技巧

**添加调试信息**：
```java
private void quickSort(int[] nums, int left, int right) {
    if (left >= right) return;
    
    System.out.println("排序区间: [" + left + ", " + right + "]");
    System.out.println("当前数组: " + Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));
    
    int pivotIndex = partition(nums, left, right);
    System.out.println("基准值位置: " + pivotIndex + ", 基准值: " + nums[pivotIndex]);
    System.out.println("分区后数组: " + Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));
    System.out.println();
    
    quickSort(nums, left, pivotIndex - 1);
    quickSort(nums, pivotIndex + 1, right);
}
```

### 7. 常见错误避免

```java
// 错误：递归终止条件错误
if (left > right) return;  // 应该是 left >= right

// 错误：分区循环边界错误
for (int j = left; j <= right; j++)  // 应该是 j < right

// 错误：交换基准值位置错误
swap(nums, i, right);  // 应该是 swap(nums, i + 1, right)

// 错误：归并排序中临时数组使用不当
// 必须复制数据到临时数组，不能直接修改原数组
```

### 8. 实际应用场景
- 数据库查询优化
- 大数据处理
- 游戏中的排行榜
- 数据分析

## 🎯 核心思想总结

**快速排序的精髓**：
1. **分治思想**：将大问题分解为小问题解决
2. **原地排序**：不需要额外空间，空间效率高
3. **随机化**：通过随机选择基准避免最坏情况
4. **适应性**：对部分有序数组表现良好

**归并排序的优势**：
1. **稳定性**：相等元素的相对位置不变
2. **可预测性**：始终是O(n log n)时间复杂度
3. **外部排序**：适合处理大数据集

**推荐使用快速排序的优化版本**，它在大多数情况下性能最好，而且通过随机化避免了最坏情况。

通过这道题，你将深入理解排序算法的核心思想，这是计算机科学的基础！建议多实现几种不同的排序算法来加深理解。