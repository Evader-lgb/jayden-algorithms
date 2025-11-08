package hot100_2025.leetcode.editor.cn;

/**
 * 题目链接：https://www.yuque.com/touguo/vfeoy3/afffr0
 */
public class 剑指offer05 {
    public static void main(String[] args) {
        String s = "1024 happy！ ";
        System.out.println(replaceSpace(s));
    }

    public static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
