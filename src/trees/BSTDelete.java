package trees;

import java.util.Arrays;
import java.util.List;

public class BSTDelete {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        BinaryTreeNode head = root;
        List<Integer> values_to_be_deleted = Arrays.asList(5);
        delete_from_bst(root, values_to_be_deleted);
        System.out.println(head);
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

        public String toString(){
            return value+"";
        }
    }

    static BinaryTreeNode delete_from_bst(BinaryTreeNode root, List<Integer> values_to_be_deleted) {
        for(int val: values_to_be_deleted){
            deleteNode(root,val);
        }
        return root;
    }

    static BinaryTreeNode deleteNode(BinaryTreeNode root, int val) {
        if (root == null)
            return root;
 
        if(root.value.intValue() == val && root.left == null && root.right == null){
            root.value = null;
            root=null;
            return root;
        }
        if (root.value > val) {
            root.left = deleteNode(root.left, val);
            return root;
        } else if (root.value < val) {
            root.right = deleteNode(root.right, val);
            return root;
        }
 
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }
        else {
            BinaryTreeNode succParent = root;
            BinaryTreeNode succ = root.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }
            if (succParent.value.intValue() != root.value.intValue())
                succParent.left = succ.right;
            else
                succParent.right = succ.right;
 
            root.value = succ.value;
            return root;
        }
    }
}
