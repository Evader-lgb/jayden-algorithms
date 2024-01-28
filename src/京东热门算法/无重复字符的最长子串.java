package 京东热门算法;

import java.util.HashMap;
import java.util.Map;

public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        String str = "abcabcbb";
        int i = lengthOfLongestSubstring(str);
        System.out.println(i);

    }

    public static int lengthOfLongestSubstring(String str) {
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
