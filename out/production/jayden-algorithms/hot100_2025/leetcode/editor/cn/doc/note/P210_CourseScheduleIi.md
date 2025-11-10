# 课程表 II - 拓扑排序

## 题目理解
给定课程总量 `numCourses` 和先修课程关系 `prerequisites`，返回学习所有课程的顺序。如果无法完成所有课程，返回空数组。

## 解题思路

### 总思路
使用拓扑排序算法，通过 Kahn 算法（基于入度）来检测图中是否有环并给出拓扑序列。

### 分步骤
1. **构建有向图**：使用邻接表表示课程之间的依赖关系
2. **计算入度**：统计每个课程的先修课程数量
3. **初始化队列**：将所有入度为0的课程加入队列
4. **拓扑排序**：从队列中取出课程，减少依赖该课程的后续课程的入度
5. **检测环**：如果排序后的课程数量不等于总课程数，说明存在环

## 归类说明
- **主要分类**：图论、拓扑排序
- **算法技巧**：BFS、入度统计
- **数据结构**：邻接表、队列、数组
- **相关题目**：课程表、序列重建、火星词典

## Java代码实现

```java
import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 2. 计算每个课程的入度
        int[] inDegree = new int[numCourses];
        
        // 3. 填充邻接表和入度数组
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(prerequisite).add(course); // 先修课程指向后续课程
            inDegree[course]++; // 后续课程的入度增加
        }
        
        // 4. 初始化队列，将所有入度为0的课程加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // 5. 进行拓扑排序
        int[] result = new int[numCourses];
        int index = 0;
        int count = 0; // 记录已排序的课程数量
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[index++] = current;
            count++;
            
            // 遍历当前课程的所有后续课程
            for (int neighbor : graph.get(current)) {
                // 减少后续课程的入度
                inDegree[neighbor]--;
                // 如果入度变为0，加入队列
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // 6. 检查是否所有课程都被排序（判断是否有环）
        if (count == numCourses) {
            return result;
        } else {
            return new int[0];
        }
    }
}
```

## 关键点详细解析

### 1. 图构建的理解
```java
for (int[] prereq : prerequisites) {
    int course = prereq[0];
    int prerequisite = prereq[1];
    graph.get(prerequisite).add(course);
    inDegree[course]++;
}
```
- `prerequisites` 数组中的每个元素 `[a, b]` 表示：要学习课程 `a`，必须先学习课程 `b`
- 所以在图中，我们建立边 `b → a`（从先修课程指向后续课程）
- 课程 `a` 的入度增加，因为它依赖于其他课程

### 2. 拓扑排序过程
```java
while (!queue.isEmpty()) {
    int current = queue.poll();
    result[index++] = current;
    count++;
    
    for (int neighbor : graph.get(current)) {
        inDegree[neighbor]--;
        if (inDegree[neighbor] == 0) {
            queue.offer(neighbor);
        }
    }
}
```
- 从入度为0的课程开始（没有先修要求的课程）
- 每学完一门课程，就减少所有依赖该课程的后续课程的入度
- 当后续课程的入度变为0时，说明它的所有先修课程都已学完，可以开始学习

### 3. 环检测原理
```java
if (count == numCourses) {
    return result;
} else {
    return new int[0];
}
```
- 如果存在环，环中的课程永远不会有入度为0的时候
- 因此这些课程永远不会被加入队列
- 最终已排序的课程数量会小于总课程数

## 示例分析

### 示例 1：
```java
numCourses = 2, prerequisites = [[1,0]]
```
图结构：`0 → 1`
- 课程0的入度：0
- 课程1的入度：1

排序过程：
1. 初始队列：[0]
2. 取出0，结果：[0]，减少课程1的入度（1→0）
3. 队列加入1：[1]
4. 取出1，结果：[0,1]

最终结果：`[0,1]`

### 示例 2：
```java
numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
```
图结构：
```
0 → 1
0 → 2
1 → 3
2 → 3
```
排序过程：
1. 初始队列：[0]
2. 取出0，结果：[0]，减少课程1和2的入度
3. 队列加入1和2：[1,2]
4. 取出1，结果：[0,1]，减少课程3的入度（2→1）
5. 取出2，结果：[0,1,2]，减少课程3的入度（1→0）
6. 队列加入3：[3]
7. 取出3，结果：[0,1,2,3]

最终结果：`[0,1,2,3]` 或 `[0,2,1,3]`（两种都正确）

## 学习建议

### 1. 理解拓扑排序
- 拓扑排序只适用于有向无环图（DAG）
- 排序结果可能不唯一
- 核心思想：逐步移除入度为0的节点

### 2. 掌握图表示方法
- **邻接表**：适合稀疏图，节省空间
- **邻接矩阵**：适合稠密图，查找快

### 3. 相关题目练习
1. **课程表**（LeetCode 207）
    - 只判断是否可以完成，不需要输出顺序
    - 同样的拓扑排序思路

2. **序列重建**（LeetCode 444）
    - 验证序列是否唯一符合拓扑排序

3. **火星词典**（LeetCode 269）
    - 根据单词顺序构建字母的拓扑排序

### 4. 调试技巧
- 使用小例子手动模拟队列操作
- 打印每个步骤后的入度数组状态
- 画图理解课程之间的依赖关系

### 5. 复杂度分析
- **时间复杂度**：O(V + E)，其中V是课程数，E是先修关系数
- **空间复杂度**：O(V + E)，用于存储图和入度数组

### 6. 扩展思考
- 如何找到所有的拓扑排序序列？
- 如果要求字典序最小的拓扑排序？
- 如何检测图中所有的环？

通过这道题，你可以深入理解拓扑排序算法，这是解决依赖关系问题的重要工具！