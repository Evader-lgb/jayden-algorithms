package 字符串;

/**
 * <p>Title: 字符串相加</p>
 * <a href="https://leetcode.cn/problems/add-strings/">字符串相加</a>
 * <p>Description: 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。</p>
 * <p>Difficulty: Easy</p>
 * <date>2025/09/10</date>
 * <p>解法：模拟加法过程（双指针），从个位开始逐位相加，考虑进位。</p>
 */
public class P415_字符串相加 {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        StringBuffer result = new StringBuffer();
        while(i >= 0 || j >= 0 || carry > 0){
            int x = i >=0 ? num1.charAt(i) - '0' : 0;
            int y = j >=0 ? num2.charAt(j) - '0' : 0;
            int addResult = x + y + carry;
            result.append(addResult%10);
            carry = addResult/10;
            i--;
            j--;
        }

        result.reverse();
        return result.toString();
    }

    public static void main(String[] args) {
        int a = 13,b=10;
        System.out.println(a / b);
        System.out.println(a % b);
    }
}
