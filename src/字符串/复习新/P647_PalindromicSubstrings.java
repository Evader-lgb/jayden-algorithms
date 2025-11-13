//给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。 
//
// 回文字符串 是正着读和倒过来读一样的字符串。 
//
// 子字符串 是字符串中的由连续字符组成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 
//输入：s = "aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
//
// Related Topics 双指针 字符串 动态规划 👍 1474 👎 0


package 字符串.复习新;

/**
 * 回文子串
 * @author Jayden
 * @date 2025-11-13 15:13:04
 */
public class P647_PalindromicSubstrings{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P647_PalindromicSubstrings().new Solution();
         System.out.println(solution.countSubstrings("aaa"));
     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countSubstrings(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int count1 = getCenterCount(s, i, i);
            int count2 = getCenterCount(s, i, i+1);
            int count = count1 + count2;
            result += count;
        }

        return result;
    }


    public int getCenterCount(String s,Integer i,Integer j){
        int currCount = 0;

        while (i>=0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            currCount++;
            i--;
            j++;
        }

        return currCount;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
