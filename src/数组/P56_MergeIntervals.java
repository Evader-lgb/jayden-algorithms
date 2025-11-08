//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 示例 3： 
//
// 
//输入：intervals = [[4,7],[1,4]]
//输出：[[1,7]]
//解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
//
// Related Topics 数组 排序 👍 2667 👎 0


package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并区间
 * @author Jayden
 * @date 2025-10-26 17:31:54
 */
public class P56_MergeIntervals{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P56_MergeIntervals().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        // 步骤1:边界情况处理
        // 如果区间数组为空或只有一个区间，直接返回
        if(intervals == null || intervals.length <= 1){
            return intervals;
        }

        // 步骤2:按照区间的起始位置进行排序
        // 使用lambda表达式定义比较器，按每个区间的第一个元素排序
        Arrays.sort(intervals,(a,b) -> a[0] -b[0]);

        // 步骤3:创建结果列表，用于存储合并后的区间
        List<int[]> result = new ArrayList<>();
        // 将第一个区间加入结果列表作为起始
        result.add(intervals[0]);

        // 步骤4:遍历剩余的区间，进行合并操作
        for (int i = 1; i < intervals.length; i++) {
            // 获取当前区间
            int[] currentInterval = intervals[i];

            // 获取结果列表中最后一个区间（最近添加的区间）
            int[] lastInterval = result.get(result.size() - 1);

            // 步骤5:检查当前区间是否与最后一个区间重叠
            // 如果当前区间的起始位置 <= 最后一个区间的结束位置，说明有重叠
            // TODO 合并区间每个数组只有2个元素，所以下标为1是结束位置
            if (currentInterval[0] <= lastInterval[1]){
                lastInterval[1] = Math.max(lastInterval[1],currentInterval[1]);
            }else {
                result.add(currentInterval);
            }
        }

        // 步骤8：将结果列表转换为二维数组并返回
        return result.toArray(new int[result.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
