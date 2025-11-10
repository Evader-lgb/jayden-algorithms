//假定一段路径记作字符串 path，其中以 "." 作为分隔符。
// 现需将路径加密，加密方法为将 path 中的分隔符替换为空格 " "，
// 请返回加密后的字符串。

// 示例 1： 
//
// 
//输入：path = "a.aef.qerf.bb"
//
//输出："a aef qerf bb"

// 限制： 
//
// 0 <= path.length <= 10000 
//
// Related Topics 字符串 👍 588 👎 0


package 字符串.复习;

/**
 * 路径加密
 * @author Jayden
 * @date 2025-11-10 22:05:01
 */
public class PLCR122_TiHuanKongGeLcof{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new PLCR122_TiHuanKongGeLcof().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 第一遍直接写出来，题解只有一种方法
     *
     * 时间复杂度 O(N) ： 遍历使用 O(N) ，每轮添加（修改）字符操作使用 O(1) ；
     * 空间复杂度 O(N) ： Python 新建的 list 和 Java 新建的 StringBuilder 都使用了线性大小的额外空间。
     *
     * @param path
     * @return
     */
        public String pathEncryption(String path) {
            if (path == null || path.length() == 0){
                return path;
            }

            StringBuilder sb = new StringBuilder();
            char[] charArray = path.toCharArray();
            for (char c : charArray) {
                if (c == '.'){
                    sb.append(' ');
                }else{
                    sb.append(c);
                }
            }

            return sb.toString();
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
