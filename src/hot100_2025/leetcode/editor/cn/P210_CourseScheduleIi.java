//现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 
//prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。 
//
// 
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。 
// 
//
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：[0,1]
//解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
// 
//
// 示例 2： 
//
// 
//输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//输出：[0,2,1,3]
//解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。 
//
// 示例 3： 
//
// 
//输入：numCourses = 1, prerequisites = []
//输出：[0]
// 
//
// 
//提示：
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// 所有[ai, bi] 互不相同 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1062 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 课程表 II
 * @author Jayden
 * @date 2025-11-03 20:10:15
 */
public class P210_CourseScheduleIi{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P210_CourseScheduleIi().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
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
            graph.get(prerequisite).add(course);
            inDegree[course]++;
        }

        // 4. 初始化队列，将所有入度为0的课程加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0){
                queue.offer(i);
            }
        }

        // 5. 进行拓扑排序
        int[] result = new int[numCourses];
        int index = 0;
        int count = 0;

        while (!queue.isEmpty()){
            Integer current = queue.poll();
            result[index++] = current;
            count++;

            // 遍历当前课程的所有后续课程
            for (Integer neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                // 如果入度变为0，加入队列
                if (inDegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }

        // 6. 检查是否所有课程都被排序（判断是否有环）
        if (count == numCourses){
            return result;
        }else {
            return new int[0];
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
