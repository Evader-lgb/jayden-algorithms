package 数组;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 */
public class 最大子数组和 {

    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        int max = 0;
        if (nums.length == 1){
            max = nums[0];
        }

        int temp = 0;
        // 遍历nums
        for (int i = 0; i < nums.length; i++) {
            temp = nums[i];
            max = Math.max(max,temp);
        }

        return max;
    }
}
