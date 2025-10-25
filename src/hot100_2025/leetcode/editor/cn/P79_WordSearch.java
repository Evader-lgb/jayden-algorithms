//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
// 
// 
//输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 
//"ABCCED"
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 
//"SEE"
//输出：true
// 
//
// 示例 3： 
// 
// 
//输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 
//"ABCB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
//
// Related Topics 深度优先搜索 数组 字符串 回溯 矩阵 👍 2078 👎 0


package hot100_2025.leetcode.editor.cn;

/**
 * 单词搜索
 * @author Jayden
 * @date 2025-10-25 04:07:21
 */
public class P79_WordSearch{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P79_WordSearch().new Solution();
	 }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean exist(char[][] board, String word) {
        // 将字符串转换为字符串数组，便于按索引访问
        char[] words = word.toCharArray();

        // 遍历网格中的每一个单元格，作为搜索的起点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 从当前单元格开始深度优先搜索
                // 如果找到单词，立即返回true
                dfs(board,words,i,j,0);
            }
        }


        // 所有起点都尝试过但没有找到，返回false
        return false;
    }

    /**
     * 深度优先搜索
     * @param board
     * @param word 要搜的单词
     * @param i 行
     * @param j 列
     * @param k 单前要匹配的单词字符索引
     * @return 是否找到单词
     */
    boolean dfs(char[][] board, char[] word, int i, int j,int k){
        // 边界条件检查：越界或者不匹配
        if(i < 0 || i >board.length || j<0 || j>board[0].length
        || board[i][j] != word[k]){
            return false;
        }

        // 成功条件：已经匹配到单词的最后一个字符
        // 说明找到了完整的单词
        if (k == word.length -1){
            return true;
        }

        // 标记当前单元格为已访问
        // 使用空字符串标记，这样就不会与任何单词字符匹配，避免使用额外的visited数组
        board[i][j] = '\0';

        // 向4个方向深度优先搜索
        // 使用｜｜短路操作：如果任一方找到单词，后续方向不再搜索
        boolean res =
            // 向下搜索
            dfs(board,word,i+1,j,k+1) ||
            // 向上搜索
            dfs(board,word,i-1,j,k+1)||
            // 向右搜索
            dfs(board,word,i,j+1,k+1)||
            // 向左搜索
            dfs(board,word,i,j-1,k+1);

        // 回溯：恢复当前单元格的原始字符
        // 这样其他路径可以重复使用这个单元格
        board[i][j] = word[k];

        // 返回搜索结果
        // 如果任一方向找到了单词res为true，否则为false
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}
