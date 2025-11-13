package 字符串.复习旧;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 */
public class P3_无重复字符的最长子串 {

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring2(str));

        String str2 = "bbbbb";
        System.out.println(lengthOfLongestSubstring2(str2));

        String str3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring2(str3));
    }

    /**
     * TODO：错题解法
     * 自己思路是用滑动窗口，左右指针都从0开始，没有重复的右指针右移动，有重复的左指针移动
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        // 边界判断
        if(s == null || s.length() == 0){
            return 0;
        }

        // 定义个数组，存下字符跟对应下标
        int[] result = new int[s.length()];

        // 定义左右指针，结果
        int left = 0;
        int right = 0;
        int max = 0;

        // 遍历字符串
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);

        }

        // 返回结果
        return max;
    }

    /**
     * 看完答案后的解法，想到了用map，自己想的是用map存每个字符的下标，发现会重复就没往下想
     * 这题算是哈希表 + 滑动窗口 + 字符串，但是字符串部分很少？
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        // 边界判断
        if(s == null || s.length() == 0){
            return 0;
        }

        // 定义哈希表，存字符及对应下标
        Map<Character,Integer> map = new HashMap<>();

        // 定义双指针
        int left = 0;
        int right = 0;

        // 定义返回值
        int max = 0;

        // 遍历数组
        for (; right < s.length(); right++) {
            char c = s.charAt(right);
            // 如果map中含有当前值就是重复了
            if (map.containsKey(c)){
                // TODO 这里刚开始没有想明白。
                // map.get(c) 获取的是当前重复字符上一次出现的位置
                // + 1 是为了移动到该位置的下一位
                // Math.max(left, ...) 是为了确保 left 指针不会回退
                left = Math.max(left,map.get(c) + 1);
            }

            map.put(c,right);
            max = Math.max(max,right - left + 1);
        }

        return max;
    }
}
