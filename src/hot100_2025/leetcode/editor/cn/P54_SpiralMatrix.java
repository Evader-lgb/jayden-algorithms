//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
//
// Related Topics 数组 矩阵 模拟 👍 2008 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * @author Jayden
 * @date 2025-10-27 11:41:16
 */
public class P54_SpiralMatrix{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P54_SpiralMatrix().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if(matrix == null || matrix.length ==0 || matrix[0].length == 0){
            return result;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while(top <= bottom && left <= right){
            // 往右走
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // 往下走
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // 检查是否还有行需要遍历（防止重复遍历）
            if (top <= bottom) {
                // 3. 从右到左遍历下边界
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]); // 添加当前元素到结果
                }
                bottom--; // 下边界上移
            }

            // 检查是否还有列需要遍历（防止重复遍历）
            if (left <= right) {
                // 4. 从下到上遍历左边界
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]); // 添加当前元素到结果
                }
                left++; // 左边界右移
            }
        }


        return result;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
