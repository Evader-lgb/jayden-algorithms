package 哈希表.复习旧;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 */
public class P3_无重复字符的最长子串 {

    public static void main(String[] args) {
        String str = "hfuaibvauwqb";
        System.out.println(lengthOfLongestSubstring(str));

        // 2026-01-01的case pwwkew
        String case1 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(case1));
    }

    public static int lengthOfLongestSubstring(String s) {
        // 滑动窗口 + 哈希表

        // 1. 初始化返回结果
        int result = 0;

        // 2. 边界判断
        if (s.length() <= 1){
            return s.length();
        }

        // 3. 初始化哈希表，用于记录字符出现的下标
        Map<Character,Integer> hash = new HashMap<>();

        // 4. 初始化窗口

        // 5. 遍历字符串中的每一个字符
        for (int start = 0, end = 0; end < s.length(); end++) {
            // 获取当前右侧字符
            Character c = s.charAt(end);

            // 判断哈希表里有没有当前字符，有即代表有重复
            if (hash.containsKey(c)){
                start = Math.max(start,hash.get(c) + 1);
            }

            hash.put(c,end);
            int current = end - start + 1;
            result = Math.max(current,result);
        }

        return result;
    }

    public static int lengthOfLongestSubstring2(String str) {
        return 0;
    }
}
