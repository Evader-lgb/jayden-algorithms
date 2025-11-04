# 组合总和

## 题目理解
给定一个无重复元素的整数数组 `candidates` 和一个目标整数 `target`，找出所有可以使数字和为 `target` 的组合。`candidates` 中的数字可以无限制重复被选取。

## 解题思路

### 总思路
使用回溯算法（深度优先搜索），通过递归遍历所有可能的组合，在遍历过程中进行剪枝优化。

### 分步骤
1. **排序数组**：便于后续剪枝操作
2. **回溯搜索**：从起始位置开始，逐个尝试每个候选数字
3. **剪枝优化**：当当前和超过目标值时提前终止
4. **避免重复组合**：通过控制搜索起始位置来避免重复

## 归类说明
- **主要分类**：回溯算法、组合问题
- **算法技巧**：深度优先搜索、剪枝
- **数据结构**：数组、列表
- **相关题目**：组合总和II、组合总和III、子集

## Java代码实现

```java
import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 排序数组，便于后续剪枝
        Arrays.sort(candidates);
        // 开始回溯搜索
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    /**
     * 回溯函数
     * @param candidates 候选数字数组
     * @param remaining 剩余需要凑齐的目标值
     * @param start 当前搜索的起始位置（避免重复组合）
     * @param current 当前组合
     * @param result 存储所有有效组合的结果列表
     */
    private void backtrack(int[] candidates, int remaining, int start, 
                          List<Integer> current, List<List<Integer>> result) {
        // 如果剩余目标值为0，说明找到了一个有效组合
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // 从start位置开始遍历候选数字
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            
            // 剪枝：如果当前数字已经大于剩余目标值，由于数组已排序，后续数字都会更大，直接退出循环
            if (num > remaining) {
                break;
            }
            
            // 选择当前数字
            current.add(num);
            // 递归搜索：注意start参数为i，因为数字可以重复使用
            backtrack(candidates, remaining - num, i, current, result);
            // 撤销选择（回溯）
            current.remove(current.size() - 1);
        }
    }
}
```

## 关键点详细解析

### 1. 回溯算法框架
```java
private void backtrack(int[] candidates, int remaining, int start, 
                      List<Integer> current, List<List<Integer>> result) {
    // 终止条件
    if (remaining == 0) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    // 遍历选择
    for (int i = start; i < candidates.length; i++) {
        // 剪枝条件
        if (candidates[i] > remaining) break;
        
        // 做出选择
        current.add(candidates[i]);
        // 递归
        backtrack(candidates, remaining - candidates[i], i, current, result);
        // 撤销选择
        current.remove(current.size() - 1);
    }
}
```
这是标准的回溯算法模板，包含：
- **选择**：将当前数字加入组合
- **约束**：剩余目标值必须非负
- **目标**：剩余目标值为0时记录结果

### 2. 避免重复组合的关键
```java
backtrack(candidates, remaining - num, i, current, result);
```
注意这里的 `start` 参数传递的是 `i` 而不是 `i + 1`，这允许数字重复使用。同时，通过从 `start` 开始遍历，避免了生成 `[2,3]` 和 `[3,2]` 这样的重复组合。

### 3. 剪枝优化
```java
if (num > remaining) {
    break;
}
```
由于数组已经排序，当当前数字大于剩余目标值时，后面的所有数字都会更大，可以直接终止循环，大幅减少不必要的搜索。

## 示例分析

### 示例：`candidates = [2,3,6,7], target = 7`

**搜索过程**：
```
从start=0开始：
- 选择2: [2], 剩余5
  - 从start=0继续: 
    - 选择2: [2,2], 剩余3
      - 从start=0继续:
        - 选择2: [2,2,2], 剩余1 → 失败
        - 选择3: [2,2,3], 剩余0 → 成功，记录[2,2,3]
    - 选择3: [2,3], 剩余2
      - 从start=1继续:
        - 选择3: [2,3,3], 剩余-1 → 失败
- 选择3: [3], 剩余4
  - 从start=1继续:
    - 选择3: [3,3], 剩余1 → 失败
- 选择6: [6], 剩余1 → 失败
- 选择7: [7], 剩余0 → 成功，记录[7]
```

**最终结果**：`[[2,2,3], [7]]`

## 学习建议

### 1. 理解回溯算法
- 回溯算法本质是深度优先搜索
- 通过"选择-探索-撤销"的模式遍历所有可能性
- 掌握回溯算法的三要素：路径、选择列表、结束条件

### 2. 掌握剪枝技巧
- **排序剪枝**：先排序，当当前元素不满足条件时，后面元素都不满足
- **去重剪枝**：通过控制搜索起点避免重复组合
- **可行性剪枝**：提前终止不可能达到目标的搜索路径

### 3. 相关题目练习
1. **组合总和II**（LeetCode 40）
    - 每个数字只能使用一次
    - 需要额外的去重处理

2. **组合总和III**（LeetCode 216）
    - 只使用1-9的数字，且数量固定为k

3. **子集**（LeetCode 78）
    - 找出所有可能的子集
    - 类似的回溯思路

### 4. 调试技巧
- 使用小输入手动模拟递归过程
- 打印递归树，观察选择与撤销的过程
- 特别注意递归参数的传递

### 5. 复杂度分析
- **时间复杂度**：O(2^n) 在最坏情况下，但剪枝会大幅优化实际运行
- **空间复杂度**：O(target/min(candidates)) 递归栈的深度

### 6. 扩展思考
- 如果要求组合不能重复怎么办？（组合总和II）
- 如果要求组合中元素个数固定怎么办？（组合总和III）
- 如果数组中有负数怎么办？

通过这道题，你可以深入理解回溯算法的核心思想，这是解决许多组合、排列、子集问题的基础！