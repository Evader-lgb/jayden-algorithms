# 课程表 - 拓扑排序检测环

## 题目理解
给定课程总量 `numCourses` 和先修课程关系 `prerequisites`，判断是否可能完成所有课程的学习（即判断有向图中是否存在环）。

## 解题思路

### 总思路
使用拓扑排序的 Kahn 算法，通过统计每个课程的入度，逐步移除入度为0的课程，最后检查是否所有课程都被移除。

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
- **相关题目**：课程表II、序列重建、火星词典

## Java代码实现

```java
import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        
        // 5. 记录已学习的课程数量
        int count = 0;
        
        // 6. 进行拓扑排序
        while (!queue.isEmpty()) {
            int current = queue.poll();
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
        
        // 7. 检查是否所有课程都被学习（判断是否有环）
        return count == numCourses;
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
return count == numCourses;
```
- 如果存在环，环中的课程永远不会有入度为0的时候
- 因此这些课程永远不会被加入队列
- 最终已学习的课程数量会小于总课程数

## 示例分析

### 示例 1：无环情况
```java
numCourses = 2, prerequisites = [[1,0]]
```
图结构：`0 → 1`
- 课程0的入度：0
- 课程1的入度：1

排序过程：
1. 初始队列：[0]
2. 取出0，计数：1，减少课程1的入度（1→0）
3. 队列加入1：[1]
4. 取出1，计数：2

最终结果：`true`（可以完成）

### 示例 2：有环情况
```java
numCourses = 2, prerequisites = [[1,0],[0,1]]
```
图结构：`0 ⇄ 1`（形成环）
- 课程0的入度：1
- 课程1的入度：1

排序过程：
1. 初始队列：[]（没有入度为0的课程）
2. 计数：0

最终结果：`false`（无法完成）

## 替代方法：DFS检测环

除了BFS的拓扑排序，还可以使用DFS来检测环：

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        // 访问状态：0=未访问, 1=访问中, 2=已访问
        int[] visited = new int[numCourses];
        
        // 对每个未访问的节点进行DFS
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycleDFS(graph, visited, i)) {
                    return false; // 有环，无法完成
                }
            }
        }
        
        return true; // 无环，可以完成
    }
    
    private boolean hasCycleDFS(List<List<Integer>> graph, int[] visited, int node) {
        // 标记当前节点为访问中
        visited[node] = 1;
        
        // 遍历所有邻居节点
        for (int neighbor : graph.get(node)) {
            if (visited[neighbor] == 0) {
                // 未访问的节点，递归DFS
                if (hasCycleDFS(graph, visited, neighbor)) {
                    return true;
                }
            } else if (visited[neighbor] == 1) {
                // 遇到访问中的节点，说明有环
                return true;
            }
            // 如果邻居是已访问状态，继续检查下一个邻居
        }
        
        // 标记当前节点为已访问
        visited[node] = 2;
        return false;
    }
}
```

## 学习建议

### 1. 理解拓扑排序
- 拓扑排序只适用于有向无环图（DAG）
- 核心思想：逐步移除入度为0的节点
- BFS方法（Kahn算法）更直观易懂

### 2. 掌握图表示方法
- **邻接表**：适合稀疏图，节省空间
- **邻接矩阵**：适合稠密图，查找快

### 3. 相关题目练习
1. **课程表II**（LeetCode 210）
    - 需要输出学习顺序，而不仅仅是判断可行性

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

### 6. 实际应用
- 课程安排系统
- 任务调度系统
- 依赖关系解析（如软件包管理）

通过这道题，你可以深入理解拓扑排序算法和环检测技术，这是解决依赖关系问题的重要基础！