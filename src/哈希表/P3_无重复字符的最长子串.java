package 哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 */
public class P3_无重复字符的最长子串 {

    public static void main(String[] args) {
        String str = "hfuaibvauwqb";
        lengthOfLongestSubstring(str);
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        if (s.length() == 0){
            return max;
        }
        // 定义散列表，key为字符，value为字符对应的下标
        Map<Character,Integer> map = new HashMap<>();

        // 定义不重复字符串的开始位置与结束位置，使用结束位置控制循环结束
        for (int start = 0,end = 0; end < s.length(); end++) {
            // 判断当前字符在散列表中是否有，如果有就是重复了，需要调整start的位置
            char c = s.charAt(end);
            if (map.containsKey(c)){
                start = Math.max(start,map.get(s.charAt(end))+1);
            }
            // 判断当前字符串长度与最大长度那个大
            map.put(s.charAt(end),end);
            max = Math.max(max,end - start + 1);
        }

        return max;
    }

    public static int lengthOfLongestSubstring2(String str) {
        int max = 0;
        // 临界值判断，如果长度为0，直接返回
        if (str.length() == 0){
            return max;
        }

        // 定义一个散列表，key为字符的值，value是字符所在的下标
        Map<Character,Integer> dis = new HashMap<>();

        // 设置开始，结束位置。遍历str的char数组
        for (int start = 0,end = 0; end < str.length(); end++) {
            if(dis.containsKey(str.charAt(end))){
                // 如果当前字符在map中，做更新
                start = Math.max(start,dis.get(str.charAt(end)) + 1);
            }
            dis.put(str.charAt(end),end);
            max = Math.max(max,end - start + 1);
        }

        return max;
    }
}
