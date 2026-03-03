package codetop100;

public class TestTree {

    public static void main(String[] args) {
        ListNode root = new ListNode(2);
        ListNode left = new ListNode(1);
        ListNode right = new ListNode(3);
        root.right = right;
        root.left = left;
        System.out.println(checkTree(root));
    }

    /**
     * 递归
     *
     * @param node
     * @return
     */
    public static boolean checkTree(ListNode node){

        // 递归处理
        if (node.right != null){
            checkTree(node.right);
        }

        if (node.left != null){
            checkTree(node.left);
        }

        if ((node.left!= null && node.left.value < node.value)
                && (node.right != null && node.right.value > node.value)){
            return true;
        }

        return false;
    }

    static class ListNode{
        ListNode left;
        ListNode right;
        Integer value;

        public ListNode(){}

        public ListNode(Integer value){
            this.value = value;
        }

        public ListNode(ListNode left,ListNode right,Integer value){
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}
