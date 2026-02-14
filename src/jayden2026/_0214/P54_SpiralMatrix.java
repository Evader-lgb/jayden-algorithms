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
// Related Topics 数组 矩阵 模拟 👍 2067 👎 0


package jayden2026._0214;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * @author Jayden
 * @date 2026-02-14 07:57:21
 */
public class P54_SpiralMatrix{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P54_SpiralMatrix().new Solution();
          System.out.println(solution.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
	 }

//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 初始化返回值
        ArrayList<Integer> res = new ArrayList<>();

        // 边界处理
        if (matrix == null){
            return res;
        }

        // 定义上下左右的界限
        int top = 0;
        int button = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        // 循环按右 下 左 上的顺序处理数据，循环终止条件上跟下重合或者左右重合
        while (top <= button && left <= right){
            // 处理右
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            // 处理下
            for (int i = top; i <= button; i++) {
                res.add(matrix[i][right]);
            }
            right--;

            // 处理左
            if(top <= button){
                for (int i = right; i >= left; i--) {
                    res.add(matrix[button][i]);
                }
                button--;
            }

            // 处理上
            if (left <= right){
                for (int i = button; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
