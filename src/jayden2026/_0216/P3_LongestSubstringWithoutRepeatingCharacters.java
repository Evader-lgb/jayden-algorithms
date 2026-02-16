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
// Related Topics 哈希表 字符串 滑动窗口 👍 11286 👎 0


package jayden2026._0216;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * @author Jayden
 * @date 2026-02-16 08:07:08
 */
public class P3_LongestSubstringWithoutRepeatingCharacters{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P3_LongestSubstringWithoutRepeatingCharacters().new Solution();
          String str = " ";
         System.out.println(solution.lengthOfLongestSubstring(str));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 边界判断
        if (s.isEmpty()){
            return 0;
        }

        // 哈希表用于记录字符下标
        Map<Character,Integer> map = new HashMap<>();

        // 初始化双指针
        int left = 0;
        int right = 0;

        // 初始化返回值
        int maxLen = 0;

        // 循环处理数据
        while (right < s.length()){
            // 判断是否有重复字符
            if (map.containsKey(s.charAt(right))){
                left = Math.max(left,map.get(s.charAt(right)) + 1);
            }

            // 计算最大值
            int count = right - left + 1;

            maxLen = Math.max(maxLen,count);

            map.put(s.charAt(right),right);
            right++;
        }

        // 返回结果
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
