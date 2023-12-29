package 左神入门;

public class BitwiseOperation {
    public static void main(String[] args) {
        int intMax = Integer.MAX_VALUE;
        print(intMax);
        int intMin = Integer.MIN_VALUE;
        print(intMin);
        int one = 1;
        print(one);
        int two = 2;
        print(two);
        int three = 3;
        print(three);
    }

    /**
     * 打印int类型2进制的方法
     */
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
