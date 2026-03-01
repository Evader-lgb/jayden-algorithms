package codetop100;

import java.util.HashMap;
import java.util.Map;

/**
 * 难度：简单
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target  的那两个整数，并返回它们的数组下标。
 * https://leetcode.cn/problems/two-sum/description/
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 数据结构：单次遍历+哈希表
 * 解法思路：哈希表+单次遍历处理
 */
public class T14_P1_简单_两数之和_遍历_哈希表 {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        // 初始化result
        int[] res = new int[2];
        // 边界判断
        if (nums.length < 2){
            return res;
        }

        // 初始化哈希表
        Map<Integer,Integer> hash = new HashMap<>();

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(target - nums[i])){
                res[0] = i;
                res[1] = hash.get(target - nums[i]);
            } else {
                hash.put(nums[i],i);
            }
        }

        // 返回结果
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        return null;
    }
}
