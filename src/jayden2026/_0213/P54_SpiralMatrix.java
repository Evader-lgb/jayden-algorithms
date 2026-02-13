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


package jayden2026._0213;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 螺旋矩阵
 * @author Jayden
 * @date 2026-02-13 15:41:08
 */
public class P54_SpiralMatrix{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P54_SpiralMatrix().new Solution();
          // [[1,2,3],[4,5,6],[7,8,9]]
          int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
          System.out.println(solution.spiralOrder(matrix));
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 初始化结果集
        List<Integer> res = new ArrayList<>();

        // 边界判断
        if(matrix == null){
            return res;
        }

        // 框定上下左右 4个边界
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int button = matrix.length - 1;

        // 处理二维数组，按照
//        while (true){
        while (top<=button&&left<=right ){
            // 从左到右处理
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            // 向下
            for (int i = top;i <= button; i++){
                res.add(matrix[i][right]);
            }
            right--;

            // 向左，需要判断边界
//            if(left<right){
            if(top<=button){
                for (int i = right; i >= left; i--){
//                    res.add(matrix[button][right]);
                    res.add(matrix[button][i]);
                }
                button--;
            }

            // 向上
//            if(top < button){
            if(left <= right){
                for (int i= button;i>=top;i--){
//                    res.add(matrix[button][left]);
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
