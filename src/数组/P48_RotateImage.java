//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。 
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
//
// 
//
// Related Topics 数组 数学 矩阵 👍 2146 👎 0


package 数组;

/**
 * 旋转图像
 * @author Jayden
 * @date 2025-11-02 14:34:54
 */
public class P48_RotateImage{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P48_RotateImage().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 想象矩阵是一个平面：
     *
     * 转置：相当于沿着主对角线（从左上到右下）进行镜像
     *
     * 水平翻转：相当于沿着垂直中轴线进行镜像
     *
     * 这两个镜像操作的组合正好等于旋转90度。
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j=i; j<n;j++){
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j=0;j<n/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n -1 -j];
                matrix[i][n-1-j] = temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
