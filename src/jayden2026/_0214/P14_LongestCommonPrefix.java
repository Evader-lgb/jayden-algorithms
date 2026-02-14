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


package jayden2026._0214;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长公共前缀
 * @author Jayden
 * @date 2026-02-14 17:47:23
 */
public class P14_LongestCommonPrefix{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P14_LongestCommonPrefix().new Solution();
          String[] strs = new String[]{"aaa","aa","aaa"};
         System.out.println(solution.longestCommonPrefix(strs));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        // 边界处理
        if(strs == null){
            return "";
        }

        // 用第一个数组当基准
        String str = strs[0];

        // 定义一个map用来统计字符出现的次数
        for (int i = 0; i < strs.length; i++) {
            int j = 0;
            for (;j<str.length() && j<strs[i].length();j++){
                // 判断是否相等
                if (str.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }

            str = str.substring(0,j);

            if (str.isEmpty()){
                return str;
            }
        }

        return str;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
