package trees;

public class RootToLeafSum {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(50000);
        /*root.left = new BinaryTreeNode(50000);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(0);
        root.left.right = new BinaryTreeNode(7);
        root.right.left = new BinaryTreeNode(4);
        root.left.left.left = new BinaryTreeNode(-1);*/
        BinaryTreeNode tmp = root;
        for(int i=1; i < 100000; i++){
            tmp.left = null;
            tmp.right = new BinaryTreeNode(50000);
            tmp = tmp.right;
        }

        System.out.println(path_sum(root,705032704));
    }

    static Boolean path_sum(BinaryTreeNode root, Integer k) {
        if(root == null)
            return false;
        return helper(root,k, 0);
    }
    
    static boolean helper(BinaryTreeNode root, Integer k, int sum){
        if(root.left != null){
            boolean ret= helper(root.left, k, sum+root.value);
            if(ret){
                return true;
            }
        }
        if(root.right != null){
            boolean ret=  helper(root.right, k, sum+root.value);
            if(ret){
                return true;
            }
        }
        if(root.left == null && root.right==null){
            sum+= root.value;
            if(sum == k.intValue()){
                return true;
            }
        }
        return false;
    }

    static class BinaryTreeNode {
        Integer value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(Integer value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
