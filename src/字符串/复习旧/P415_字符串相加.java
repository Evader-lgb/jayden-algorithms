package 字符串.复习旧;

/**
 * <p>Title: 字符串相加</p>
 * <a href="https://leetcode.cn/problems/add-strings/">字符串相加</a>
 * <p>Description: 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。</p>
 * <p>Difficulty: Easy</p>
 * <date>2025/09/10</date>
 * <p>解法：模拟加法过程（双指针），从个位开始逐位相加，考虑进位。</p>
 */
public class P415_字符串相加 {

    /**
     * // TODO 看题解了
     * 复习思路：最开始想用2层for循环，发现不对。2层是乘法，然后想着找最大的长度循环，发现也不太对，可能会越界拿不到值。然后看了题解
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        int line1 = num1.length() - 1;
        int line2 = num2.length() - 1;
        int carry = 0;

        while(line1 >= 0 || line2>=0){
            int n1 = line1 >=0 ? num1.charAt(line1) - '0' : 0;
            int n2 = line2 >=0 ? num2.charAt(line2) - '0' : 0;

            int curr = (n1 + n2 + carry)%10;
            int jw = (n1 + n2 + carry)/10;
            carry = jw;

            // 当前的肯定要加到结果里
            sb.append(curr);

            // 进入下一轮
            line1--;
            line2--;
        }

        // "1" "9" 这个case没跑过去，打断点加点这行代码
        if (carry == 1){
            sb.append("1");
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
//        int i = 4;
//        System.out.println(i / 10);//0
//        System.out.println(i % 10);//4
//
//        char c  = '9';
//        System.out.println(c - '0');//9


        String num1 = "1";
        String num2 = "9";
        addStrings(num1,num2);
    }
}
