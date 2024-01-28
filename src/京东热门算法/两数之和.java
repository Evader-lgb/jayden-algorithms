package 京东热门算法;

import java.util.HashMap;
import java.util.Map;

public class 两数之和 {
    public int[] twoSum2(int[] nums, int target) {
        // 初始化返回值
        int[] result = new int[2];

        // 临界值判断
        if (nums.length < 2){
            return result;
        }

        // 初始化散列表，key-nums里的值，value-下标
        Map<Integer,Integer> map = new HashMap<>();

        // 遍历数组
        for (int i = 0;i < nums.length;i++){
            int num = nums[i];
            boolean isEqual =  map.containsKey(target - num);
            if (isEqual){
                result[0] = i;
                result[1] = map.get(target - num);
            }

        }

        // 返回结果
        return result;
    }
}
