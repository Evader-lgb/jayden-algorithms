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


package codetop100;

import java.util.HashMap;
import java.util.Map;

/**
 * 难度：中等
 * 无重复字符的最长子串
 * <p ></p>
 *
 * <a href ="https://leetcode.cn/problems/longest-substring-without-repeating-characters/submissions/695665498/" >链接</a>
 * <p ></p>
 * 解题思路：双指针+哈希表
 * 1. 双指针用于移动窗口，左右指针都从0开始移动
 * 2. 哈希表用于记录字符出现的位置，用于判断是否重复
 *
 *
 * @author Jayden
 * @date 2026-02-16 08:07:08
 */
public class T1_P3_中等_无重复字符的最长子串_双指针_哈希表 {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T1_P3_中等_无重复字符的最长子串_双指针_哈希表().new Solution();
          String str = "abcabcbb";
         System.out.println(solution.lengthOfLongestSubstring2(str));
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

    /**
     * 3月1日
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {


        int res = 0;
        // 边界判断 为空的时候直接返回空字符串
        if(s.isBlank()){
            return res;
        }

        // 初始化双指针，从同一个起点开始
        int length = s.length();
        int left = 0;
        int right = 0;

        // 初始化哈希表，key是字符，value是下标
        Map<Character,Integer> map = new HashMap<>();

        // 循环处理，right小于字符串长度
        while(right < length){

            // 左 = 当前左 跟map中的最大值比较
            if (map.containsKey(s.charAt(right))){
                left = Math.max(left,map.getOrDefault(s.charAt(right),-1)+1);
            }

            // 当前最大值 = 右 - 左 + 1
            int currLen = right - left + 1;

            // 最大值 = 当前指针区间的最大值跟当前最大值比较
            res = Math.max(res,currLen);
            // 把字符放到map中
            map.put(s.charAt(right),right);
            right++;
        }


        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
