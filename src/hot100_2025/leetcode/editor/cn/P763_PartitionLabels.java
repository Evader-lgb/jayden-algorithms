//给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", 
//"cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。 
//
// 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。 
//
// 返回一个表示每个字符串片段的长度的列表。 
//
// 
//示例 1：
//
// 
//输入：s = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。 
//
// 示例 2： 
//
// 
//输入：s = "eccbbbbdec"
//输出：[10]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 500 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 贪心 哈希表 双指针 字符串 👍 1347 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 划分字母区间
 * @author Jayden
 * @date 2025-11-04 23:13:04
 */
public class P763_PartitionLabels{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P763_PartitionLabels().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 贪心算法的特点
     * 局部最优选择：在每个决策点上，根据某种规则做出局部最优的选择
     * 无后效性：一旦做出选择，就不会改变，也不考虑之前的状态
     * 简单高效：通常实现简单，时间复杂度较低
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        // 记录字符最后一次出现的位置
        int[] charArray = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charArray[c-'a'] = i;
        }

        // 声明结果列表
        List<Integer> result = new ArrayList<>();

        // 声明左右指针
        int left = 0;
        int right = 0;
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right,charArray[s.charAt(i) - 'a']);

            if (i == right){
                result.add(right - left + 1);
                left = right + 1;
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
