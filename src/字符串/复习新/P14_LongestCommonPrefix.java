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
// Related Topics 字典树 数组 字符串 👍 3418 👎 0


package 字符串.复习新;

/**
 * 最长公共前缀
 * @author Jayden
 * @date 2025-11-10 23:53:11
 */
public class P14_LongestCommonPrefix{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P14_LongestCommonPrefix().new Solution();
          String[] strings = new String[3];
          strings[0] = "flower";
          strings[1] = "flow";
          strings[2] = "flight";
          solution.longestCommonPrefix(strings);
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * TODO 这道题看题解了
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }

        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j=0;
            for(; j<ans.length()&&j<strs[i].length();j++){
                // TODO：这里j写成了i，导致错误
                if (ans.charAt(j) != strs[i].charAt(j)){
                    // 只要有一个不一样就可以退出循环了
                    break;
                }
            }

            // TODO错题：这里最早写到了内层循环了，打断点发现的
            // 每次比较的结果
            ans = ans.substring(0,j);
            if (ans.equals("")){
                return ans;
            }
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
