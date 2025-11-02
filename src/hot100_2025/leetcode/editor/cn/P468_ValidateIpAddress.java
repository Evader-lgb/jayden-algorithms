//给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 
//IP 地址，返回 "Neither" 。 
//
// 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.1
//68.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “1
//92.168@1.1” 为无效IPv4地址。 
//
// 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中: 
//
// 
// 1 <= xi.length <= 4 
// xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。 
// 在 xi 中允许前导零。 
// 
//
// 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7
//334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:000
//0:8a2e:0370:7334" 是无效的 IPv6 地址。 
//
// 
//
// 示例 1： 
//
// 
//输入：queryIP = "172.16.254.1"
//输出："IPv4"
//解释：有效的 IPv4 地址，返回 "IPv4"
// 
//
// 示例 2： 
//
// 
//输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
//输出："IPv6"
//解释：有效的 IPv6 地址，返回 "IPv6"
// 
//
// 示例 3： 
//
// 
//输入：queryIP = "256.256.256.256"
//输出："Neither"
//解释：既不是 IPv4 地址，又不是 IPv6 地址
// 
//
// 
//
// 提示： 
//
// 
// queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。 
// 
//
// Related Topics 字符串 👍 273 👎 0


package hot100_2025.leetcode.editor.cn;

import org.w3c.dom.xpath.XPathResult;

/**
 * 验证IP地址
 * @author Jayden
 * @date 2025-11-02 10:10:42
 */
public class P468_ValidateIpAddress{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P468_ValidateIpAddress().new Solution();

//          solution.validIPAddress("172.16.254.1");
          solution.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334");
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 占位符很重要，要不"1.1.1.1."这个case过不去
     * @param queryIP
     * @return
     */
    public String validIPAddress(String queryIP) {
        String result = "Neither";

        if (!queryIP.contains(".") && !queryIP.contains(":")){
            return result;
        }

        if (queryIP.contains(".")){
            String[] split = queryIP.split("\\.",-1);
            if (split.length != 4){
                return result;
            }

            for (String s : split) {
                if (s.isEmpty()){
                    return result;
                }

                if (s.length() > 3){
                    return result;
                }

                if (s.length() > 1 && s.charAt(0) == '0') {
                    return result;
                }

                for (char c : s.toCharArray()){
                    if (!Character.isDigit(c)){
                        return result;
                    }
                }

                int num  = Integer.parseInt(s);
                if (num < 0 || num > 255){
                    return result;
                }
            }
            result = "IPv4";
        }

        if (queryIP.contains(":")){
            String[] split = queryIP.split(":",-1);
            if (split.length != 8){
                return result;
            }

            // 定义合法的十六进制字符
            String hexdigits = "0123456789abcdefABCDEF";
            for (String s : split) {
                if (s.length()==0 || s.length() >4){
                    return result;
                }

                for (char c : s.toCharArray()) {
                    if(hexdigits.indexOf(c) == -1){
                        return result;
                    }
                }
            }
            result = "IPv6";
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
