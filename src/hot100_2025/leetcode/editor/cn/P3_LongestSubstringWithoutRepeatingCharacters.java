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
// Related Topics 哈希表 字符串 滑动窗口 👍 11261 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * @author Jayden
 * @date 2026-01-31 07:40:26
 */
public class P3_LongestSubstringWithoutRepeatingCharacters{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P3_LongestSubstringWithoutRepeatingCharacters().new Solution();
          String test = "abba";
          solution.lengthOfLongestSubstring(test);
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 边界判断
        if(s == null || s.isEmpty()){
            return 0;
        }

        // 定义返回值
        int maxLen = 0;

        // 定义哈希表用户记录index
        Map<Character,Integer> map = new HashMap<>();

        // 遍历字符串
        char[] charArray = s.toCharArray();
        for (int start = 0,end = 0; end < charArray.length;end ++) {
            // 判断当前start的字符跟end字符是否一致，一致则start移动，不一致end移动
            if (map.containsKey(charArray[end])){
                // 这里也是有问题的，要取最小值
//                start = map.get(charArray[end]) + 1;
                start = Math.max(map.get(charArray[end]) + 1,start);
            }else {
                // TODO 这里写错了
//                int currMax = end - start + 1;
//                maxLen = Math.max(currMax,maxLen);
            }
            System.out.println("当前start:" + start+ ",end:" + end);
            int currMax = end - start + 1;
            maxLen = Math.max(currMax,maxLen);

            map.put(charArray[end],end);
        }

        // 返回结果
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
