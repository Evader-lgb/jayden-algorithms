//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 如果非空，则仅由小写英文字母组成 
// 
//
// Related Topics 字典树 数组 字符串 👍 3460 👎 0


package jayden2026._0215;

/**
 * 最长公共前缀
 * @author Jayden
 * @date 2026-02-15 09:41:16
 */
public class P14_LongestCommonPrefix{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P14_LongestCommonPrefix().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        // 边界判断
        if (strs == null){
            return "";
        }

        // 获取第一个字符串作为基准
        String first = strs[0];

        // 双层遍历查找
        for (int i = 0; i < strs.length; i++) {
            int start = 0;
//            for (;start<first.length();start++){
            for (;start<first.length() && start<strs[i].length();start++){
                // 如果不一样直接就break
                if (first.charAt(start) != strs[i].charAt(start)){
                    break;
                }
            }

            first = first.substring(0,start);
        }

        // 返回结果
        return first;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
