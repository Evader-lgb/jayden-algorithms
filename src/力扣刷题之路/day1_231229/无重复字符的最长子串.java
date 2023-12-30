package 力扣刷题之路.day1_231229;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 */
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        String str = "hfuaibvauwqb";
        System.out.println(str.charAt(0));
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        if (s.length() == 0){
            return max;
        }
        // 定义散列表，key为字符，value为字符对应的下标
        Map<Character,Integer> map = new HashMap<>();

        // 定义不重复字符串的开始位置与结束位置，使用结束位置控制循环结束
        for (int start = 0,end = 0; end < s.length(); end++) {
            // 判断当前字符在散列表中是否有，如果有就是重复了，需要调整start的位置
            if (map.containsKey(s.charAt(end))){
                start = Math.max(start,map.get(s.charAt(end))+1);
            }
            // 判断当前字符串长度与最大长度那个大
            map.put(s.charAt(end),end);
            max = Math.max(max,end - start + 1);
        }

        return max;
    }
}
