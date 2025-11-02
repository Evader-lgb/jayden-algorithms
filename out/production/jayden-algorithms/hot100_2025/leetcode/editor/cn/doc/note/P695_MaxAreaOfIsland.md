# 岛屿的最大面积（Max Area of Island）解题详解

## 解题思路

**总思路**：使用深度优先搜索（DFS）或广度优先搜索（BFS）遍历网格，遇到陆地（1）时进行搜索并计算岛屿面积。

**分步骤**：
1. 遍历网格中的每个单元格
2. 当遇到陆地（1）时，开始DFS/BFS搜索相连的陆地
3. 在搜索过程中标记已访问的陆地（避免重复计算）
4. 计算当前岛屿的面积并更新最大值
5. 返回最大岛屿面积

## 归类说明
- **主要归类**：图论、深度优先搜索、广度优先搜索
- **算法技巧**：网格遍历、连通分量
- **相关题型**：岛屿数量、被围绕的区域、朋友圈

## Java代码实现

```java
public class MaxAreaOfIsland {
    /**
     * DFS解法
     * @param grid 二维网格
     * @return 最大岛屿面积
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        // 遍历每个单元格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当遇到陆地时，进行DFS计算面积
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        return maxArea;
    }
    
    /**
     * DFS辅助函数
     * @param grid 网格
     * @param i 当前行坐标
     * @param j 当前列坐标
     * @return 当前岛屿面积
     */
    private int dfs(int[][] grid, int i, int j) {
        // 边界检查
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        
        // 如果不是陆地或已经访问过，返回0
        if (grid[i][j] != 1) {
            return 0;
        }
        
        // 标记当前单元格为已访问（设置为0或其他非1值）
        grid[i][j] = 0;
        
        // 初始化面积为1（当前单元格）
        int area = 1;
        
        // 向四个方向递归搜索
        area += dfs(grid, i - 1, j); // 上
        area += dfs(grid, i + 1, j); // 下
        area += dfs(grid, i, j - 1); // 左
        area += dfs(grid, i, j + 1); // 右
        
        return area;
    }
    
    /**
     * BFS解法
     */
    public int maxAreaOfIslandBFS(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 0; // 标记为已访问
                    
                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        area++;
                        
                        // 检查四个方向
                        for (int[] dir : directions) {
                            int newI = cell[0] + dir[0];
                            int newJ = cell[1] + dir[1];
                            
                            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && 
                                grid[newI][newJ] == 1) {
                                queue.offer(new int[]{newI, newJ});
                                grid[newI][newJ] = 0; // 标记为已访问
                            }
                        }
                    }
                    
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        return maxArea;
    }
    
    /**
     * 不修改原网格的解法（使用visited数组）
     */
    public int maxAreaOfIslandWithVisited(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfsWithVisited(grid, visited, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        return maxArea;
    }
    
    private int dfsWithVisited(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || 
            grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        
        visited[i][j] = true;
        int area = 1;
        
        area += dfsWithVisited(grid, visited, i - 1, j);
        area += dfsWithVisited(grid, visited, i + 1, j);
        area += dfsWithVisited(grid, visited, i, j - 1);
        area += dfsWithVisited(grid, visited, i, j + 1);
        
        return area;
    }
    
    // 测试方法
    public static void main(String[] args) {
        MaxAreaOfIsland solution = new MaxAreaOfIsland();
        
        // 测试用例
        int[][][] testCases = {
            {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
            },
            {
                {0,0,0,0,0,0,0,0}
            },
            {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
            }
        };
        
        for (int i = 0; i < testCases.length; i++) {
            int[][] grid = testCases[i].clone();
            int result = solution.maxAreaOfIsland(grid);
            System.out.println("测试用例 " + (i + 1) + " 的最大岛屿面积: " + result);
        }
        
        // 测试BFS解法
        System.out.println("\nBFS解法测试:");
        int[][] grid2 = testCases[0].clone();
        System.out.println("最大岛屿面积: " + solution.maxAreaOfIslandBFS(grid2));
        
        // 测试不修改原网格的解法
        System.out.println("\n不修改原网格解法测试:");
        int[][] grid3 = testCases[0].clone();
        System.out.println("最大岛屿面积: " + solution.maxAreaOfIslandWithVisited(grid3));
    }
}
```

## 关键点解析

### DFS解法核心逻辑

1. **递归搜索**：
    - 从当前陆地单元格开始
    - 向四个方向（上下左右）递归搜索相连的陆地
    - 累计所有相连陆地的数量

2. **标记访问**：
    - 将访问过的陆地标记为0（水）
    - 避免重复计算同一块陆地

3. **边界处理**：
    - 检查数组索引是否越界
    - 确保只在有效范围内搜索

### 执行过程示例

对于网格：
```
[
  [1,1,0,0,0],
  [1,1,0,0,0],
  [0,0,0,1,1],
  [0,0,0,1,1]
]
```

**DFS过程**：
1. 访问(0,0)，面积=1
2. 递归上：越界，返回0
3. 递归下：(1,0)是陆地，面积+1=2
4. 继续从(1,0)递归...
5. 最终左上角岛屿面积=4
6. 右下角岛屿面积=4
7. 最大面积=4

### 方向数组技巧

使用方向数组可以简化代码：
```java
int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
for (int[] dir : directions) {
    int newI = i + dir[0];
    int newJ = j + dir[1];
    // 处理新坐标
}
```

## 学习建议

1. **理解DFS/BFS**：
    - 掌握递归和迭代两种实现方式
    - 理解为什么需要标记已访问的节点

2. **掌握网格遍历**：
    - 练习边界条件的处理
    - 熟悉方向数组的使用

3. **处理边界情况**：
    - 空网格
    - 全0网格（没有陆地）
    - 全1网格（整个网格是一个岛屿）

4. **相关题目练习**：
    - 岛屿数量（统计岛屿个数）
    - 被围绕的区域（边界处理）
    - 朋友圈（类似的连通分量问题）

5. **复杂度分析**：
    - 时间复杂度：O(m×n)，每个单元格访问一次
    - 空间复杂度：
        - DFS：O(m×n)（递归栈深度）
        - BFS：O(min(m,n))（队列大小）

6. **调试技巧**：
    - 打印每次DFS/BFS的搜索过程
    - 使用小网格手动验证
    - 检查边界条件的处理

7. **算法选择**：
    - DFS：代码简洁，适合递归思维
    - BFS：避免栈溢出，适合大网格
    - 不修改原网格：需要额外空间但保持输入不变

这道题是网格DFS/BFS的经典应用，掌握它对解决其他图论问题很有帮助！