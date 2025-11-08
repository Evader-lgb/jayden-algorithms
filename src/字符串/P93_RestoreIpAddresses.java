//有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
// 
//
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新
//排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅由数字组成 
// 
//
// Related Topics 字符串 回溯 👍 1547 👎 0


package 字符串;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原 IP 地址
 * @author Jayden
 * @date 2025-10-27 19:51:53
 */
public class
P93_RestoreIpAddresses {
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P93_RestoreIpAddresses().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4 || s.length() >12){
            return result;
        }
        backtrack(s,0,new ArrayList<>(),result);

        return result;
    }

    /**
     * 回溯函数
     * @param s 原始字符串
     * @param start 当前处理的起始位置
     * @param path 当前已生成的IP段列表
     * @param result 存储所有有效IP地址的结果列表
     */
    private void backtrack(String s, int start, List<String> path, List<String> result) {
        if(path.size() == 4){
            if (s.length() == start){
                result.add(String.join(".",path));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {
            if (len + start > s.length()){
                break;
            }

            String segment = s.substring(start,start + len);
            if (isValidSegment(segment)){
                path.add(segment);
                backtrack(s,start+len,path,result);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 检查IP段是否合法
     * @param segment 待检查的IP段
     * @return 是否合法
     */
    private boolean isValidSegment(String segment) {
        // 不能超过3位
        if(segment.length() > 3){
            return false;
        }

        // 不能是长度大于等于1且0开头
        if(segment.length()>1 && segment.charAt(0) == '0'){
            return false;
        }

        // 数值范围在0-255
        int segmentInt = Integer.parseInt(segment);
        if (segmentInt<0 || segmentInt > 255){
            return false;
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
