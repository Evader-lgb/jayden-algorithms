package 力扣刷题之路.day1_231229;

import java.util.HashMap;
import java.util.Map;

/**
 * 【简单】
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target  的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 */
public class 两数之和 {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        // 边界判断，数组中小于两个数字直接返回
        if (nums.length < 2){
            return res;
        }

        // 双层for循环
        for(int i = 0; i< nums.length; i++){
            for (int j = 0; j<nums.length;j++){
                if (i == j){
                    continue;
                }
                if (target == nums[i] + nums[j]) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }

        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        // 边界判断，数组中小于两个数字直接返回
        if (nums.length < 2){
            return res;
        }

        // 利用map特性降低时间复杂度
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i<nums.length;i++){
            Integer num = nums[i];
            // 判断map中是否存在值与当前值的和 等于target有则返回，没有将该数字放到map中
            if (map.containsKey(target - num)){
                res[0] = i;
                res[1] = map.get(target-num);
            }
            map.put(num,i);
        }

        return res;
    }
}
