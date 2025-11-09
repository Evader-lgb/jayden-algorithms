//给你一个大小为 m x n 的二进制矩阵 grid 。 
//
// 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都
//被 0（代表水）包围着。 
//
// 岛屿的面积是岛上值为 1 的单元格的数目。 
//
// 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,
//0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,
//0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出：6
//解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[0,0,0,0,0,0,0,0]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// grid[i][j] 为 0 或 1 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1167 👎 0


package 二叉树.DFS_BFS;

/**
 * 岛屿的最大面积
 * @author Jayden
 * @date 2025-10-29 07:27:54
 */
public class P695_MaxAreaOfIsland {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P695_MaxAreaOfIsland().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length ==0){
            return 0;
        }
        Integer maxSum = 0;
        int heng = grid.length;
        int shu = grid[0].length;

        for (int i = 0; i < heng; i++) {
            for (int i1 = 0; i1 < shu; i1++) {
                if (grid[i][i1] == 1){
                    int currSum = dfs(grid, i, i1);
                    maxSum = Math.max(currSum,maxSum);
                }
            }
        }
        return maxSum;
    }

    private int dfs(int[][] grid, int i, int i1){
        if(i<0 || i1<0 || i>=grid.length || i1>=grid[0].length){
            return 0;
        }

        // TODO 这里第一次忘写了 如果不是陆地或已经访问过，返回0
        if (grid[i][i1] != 1) {
            return 0;
        }

        grid[i][i1] = 0;

        int max = 1;

        max += dfs(grid,i-1,i1);
        max += dfs(grid,i+1,i1);
        max += dfs(grid,i,i1-1);
        max += dfs(grid,i,i1+1);

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
