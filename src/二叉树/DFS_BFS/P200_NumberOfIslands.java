//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ['1','1','1','1','0'],
//  ['1','1','0','1','0'],
//  ['1','1','0','0','0'],
//  ['0','0','0','0','0']
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ['1','1','0','0','0'],
//  ['1','1','0','0','0'],
//  ['0','0','1','0','0'],
//  ['0','0','0','1','1']
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 2857 👎 0


package 二叉树.DFS_BFS;

/**
 * 岛屿数量
 * @author Jayden
 * @date 2025-10-19 16:17:03
 */
public class P200_NumberOfIslands {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P200_NumberOfIslands().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        // 边界情况：如果网格为空或者没有行，直接返回0
        if (grid == null || grid.length == 0){
            return 0;
        }

        // 获取网格的行数和列数
        int rows = grid.length;
        int clos = grid[0].length;

        // 初始化岛屿计数器
        int islandCount = 0;

        // 遍历网格中的每一个单元格
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
                if (grid[i][j] == '1'){
                    islandCount++;
                    fds(grid,i,j,rows,clos);
                }
            }
        }

        // 返回岛屿总数
        return islandCount;
    }

    /**
     *
     * @param grid 网格
     * @param i 当前行坐标
     * @param j 当前列坐标
     * @param rows 总行数
     * @param cols 总列数
     */
    private void fds(char[][] grid,int i,int j,int rows,int cols){
        // 递归终止条件，坐标越界或者当前单元格式水0
        if (i<0 || i>=rows || j < 0 || j>=cols || grid[i][j] == '0'){
            return;
        }

        // 将当前陆地标记为已访问0
        grid[i][j] = '0';

        // 向4个方向进行深度优先搜索
        fds(grid,i-1,j,rows,cols);
        fds(grid,i+1,j,rows,cols);
        fds(grid,i,j-1,rows,cols);
        fds(grid,i,j+1,rows,cols);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
