# 56. 合并区间

## 📝 解题思路（总分结构）

### 总思路
先对区间按照起始位置排序，然后遍历区间列表，合并重叠的区间。

### 分步骤详解
1. **边界处理**：如果区间数组为空或只有一个区间，直接返回
2. **排序区间**：按照每个区间的起始位置进行排序
3. **初始化结果**：将第一个区间加入结果列表
4. **遍历合并**：从第二个区间开始，与结果列表最后一个区间比较
    - 如果当前区间与最后一个区间重叠，合并它们
    - 如果不重叠，将当前区间加入结果列表
5. **返回结果**：将结果列表转换为数组返回

## 🏷️ 算法归类

**主要归类**：区间问题、排序、贪心算法  
**相关知识点**：区间合并、自定义排序、数组遍历  
**难度级别**：中等  
**相似题型**：插入区间、无重叠区间、用最少数量的箭引爆气球

## 💻 Java代码实现

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        // 步骤1：边界情况处理
        // 如果区间数组为空或只有一个区间，直接返回
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        
        // 步骤2：按照区间的起始位置进行排序
        // 使用Lambda表达式定义比较器，按每个区间的第一个元素（起始位置）排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // 步骤3：创建结果列表，用于存储合并后的区间
        List<int[]> result = new ArrayList<>();
        // 将第一个区间加入结果列表作为起始
        result.add(intervals[0]);
        
        // 步骤4：遍历剩余的区间，进行合并操作
        for (int i = 1; i < intervals.length; i++) {
            // 获取当前区间
            int[] currentInterval = intervals[i];
            // 获取结果列表中最后一个区间（最近添加的区间）
            int[] lastInterval = result.get(result.size() - 1);
            
            // 步骤5：检查当前区间是否与最后一个区间重叠
            // 如果当前区间的起始位置 <= 最后一个区间的结束位置，说明有重叠
            if (currentInterval[0] <= lastInterval[1]) {
                // 步骤6：合并区间 - 更新最后一个区间的结束位置
                // 取两个区间结束位置的较大值作为新的结束位置
                lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
            } else {
                // 步骤7：没有重叠，将当前区间添加到结果列表
                result.add(currentInterval);
            }
        }
        
        // 步骤8：将结果列表转换为二维数组并返回
        return result.toArray(new int[result.size()][]);
    }
}
```

## 📚 学习建议

### 1. 理解核心合并逻辑

**关键合并条件**：
```java
if (currentInterval[0] <= lastInterval[1]) {
    // 有重叠，合并区间
    lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
}
```

**为什么这样判断重叠？**
- 因为区间已经按起始位置排序
- 如果 `current.start <= last.end`，说明两个区间有重叠部分
- 需要合并，取两个区间结束位置的较大值

### 2. 算法过程可视化

**示例**：`intervals = [[1,3],[2,6],[8,10],[15,18]]`

**执行过程**：
```
排序后：[[1,3],[2,6],[8,10],[15,18]]

步骤：
1. 初始：result = [[1,3]]
2. 处理[2,6]：2 <= 3 → 重叠 → 合并为[1,6]
3. 处理[8,10]：8 > 6 → 不重叠 → 添加[8,10]
4. 处理[15,18]：15 > 10 → 不重叠 → 添加[15,18]

最终结果：[[1,6],[8,10],[15,18]]
```

### 3. 复杂度分析
- **时间复杂度**：O(n log n) - 排序需要O(n log n)，遍历需要O(n)
- **空间复杂度**：O(n) - 存储结果需要O(n)空间

### 4. 与其他解法对比

**使用栈的解法**：
```java
// 也可以使用栈，但思路类似，排序后入栈出栈判断合并
```

### 5. 学习路径建议

1. **理解排序的重要性**：排序让重叠判断变得简单
2. **掌握合并条件**：理解重叠的判断逻辑
3. **练习相似题目**：
    - 57. 插入区间
    - 435. 无重叠区间
    - 252. 会议室
4. **掌握列表转数组**：`list.toArray(new int[0][])`

### 6. 关键技巧详解

**自定义排序的多种写法**：
```java
// 方式1：Lambda表达式（最简洁）
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

// 方式2：Comparator.comparingInt
Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

// 方式3：匿名内部类
Arrays.sort(intervals, new Comparator<int[]>() {
    public int compare(int[] a, int[] b) {
        return a[0] - b[0];
    }
});
```

**合并的数学原理**：
```
区间A: [a1, a2]
区间B: [b1, b2]

重叠条件：b1 <= a2
合并后：[a1, max(a2, b2)]
```

### 7. 调试技巧

**添加调试信息**：
```java
public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length <= 1) {
        return intervals;
    }
    
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    
    System.out.println("排序后的区间:");
    for (int[] interval : intervals) {
        System.out.println(Arrays.toString(interval));
    }
    
    List<int[]> result = new ArrayList<>();
    result.add(intervals[0]);
    
    for (int i = 1; i < intervals.length; i++) {
        int[] current = intervals[i];
        int[] last = result.get(result.size() - 1);
        
        System.out.printf("比较: 最后一个区间=%s, 当前区间=%s", 
            Arrays.toString(last), Arrays.toString(current));
        
        if (current[0] <= last[1]) {
            int newEnd = Math.max(last[1], current[1]);
            System.out.printf(" → 重叠，合并为[%d,%d]\n", last[0], newEnd);
            last[1] = newEnd;
        } else {
            System.out.println(" → 不重叠，直接添加");
            result.add(current);
        }
    }
    
    return result.toArray(new int[result.size()][]);
}
```

### 8. 常见错误避免

```java
// 错误：忘记排序
// 不排序会导致合并逻辑错误

// 错误：重叠条件判断错误
if (current[0] < last[1])  // 应该是 <=

// 错误：合并时取最小值
last[1] = Math.min(last[1], current[1]);  // 错误！应该取最大值

// 错误：数组转换错误
return result.toArray();  // 应该指定类型
```

### 9. 边界情况处理

**特殊测试用例**：
```java
// 完全包含的区间
[[1,4],[2,3]] → [[1,4]]

// 端点相接的区间  
[[1,2],[2,3]] → [[1,3]]  // 2=2，应该合并

// 单个区间
[[1,2]] → [[1,2]]

// 空数组
[] → []
```

## 🎯 核心思想总结

**合并区间问题的精髓**：

### 1. 排序是关键
- 按起始位置排序后，重叠判断变得简单
- 只需要比较当前区间和最后一个区间

### 2. 贪心思想
- 每次只考虑当前区间和已合并的最后一个区间
- 局部最优导致全局最优

### 3. 合并条件
- `current.start <= last.end` 表示重叠
- 合并时取结束位置的较大值

### 4. 列表操作
- 使用动态数组方便添加和修改
- 最后转换为要求的数组格式

## 💡 实际应用场景
- 日历日程安排
- 资源时间分配
- 项目时间线管理
- 数据库查询优化

**重要提示**：这是区间类问题的经典题目，掌握后可以解决许多类似的区间合并、区间调度问题。建议多练习几个变种题目来加深理解！

通过这道题，你将掌握排序和贪心算法在区间问题中的应用，这是解决许多实际调度问题的基础技能！