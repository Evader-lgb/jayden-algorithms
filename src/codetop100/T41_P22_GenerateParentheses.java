//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 3953 👎 0


package codetop100;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * @author Jayden
 * @date 2025-10-29 06:46:19
 */
public class T41_P22_GenerateParentheses {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new T41_P22_GenerateParentheses().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if(n<1){
            return result;
        }
        backtrack(result,"",0,0,n);
        return result;
    }


    private void backtrack(List<String> result,String str,Integer leftNum,Integer rightNum,Integer n){
        if(str.length() == 2 * n){
            result.add(str);
            return;
        }

        if (leftNum < n){
            backtrack(result,str+"(",leftNum+1,rightNum,n);
        }

        if (rightNum < leftNum){
            backtrack(result,str+")",leftNum,rightNum+1,n);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
