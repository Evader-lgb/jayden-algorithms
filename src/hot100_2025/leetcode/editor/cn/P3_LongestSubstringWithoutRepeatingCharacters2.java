//给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 11265 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * @author Jayden
 * @date 2026-02-01 15:23:15
 */
public class P3_LongestSubstringWithoutRepeatingCharacters2 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P3_LongestSubstringWithoutRepeatingCharacters2().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 边界处理
        if(s.isEmpty()){
            return 0;
        }

        // 定义返回结果变量
        int res = 1;

        // 定义哈希表
        Map<Character,Integer> map = new HashMap<>();

        // 定义双指针
        int start = 0;
        int end = 0;

        // 遍历字符数组处理
        char[] charArray = s.toCharArray();
        for (; end < charArray.length; end++) {
            Character c = charArray[end];
            if (map.containsKey(c)){
                start = Math.max(start,map.get(charArray[end]));
            }

            map.put(c,end);
            int currLen = end - start + 1;
            res = Math.max(currLen,res);
        }

        // 返回结果
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
