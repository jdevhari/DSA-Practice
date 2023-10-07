package trees;

import java.util.Arrays;
import java.util.List;

public class BSTInsert {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(5,7,9);
        System.out.println(build_a_bst(values));
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

    static BinaryTreeNode build_a_bst(List<Integer> values) {
        BinaryTreeNode root = null;
        for (int i = 0; i < values.size(); i++) {
            root = insert(root, values.get(i));
        }
        return root;
    }

    static BinaryTreeNode insert(BinaryTreeNode root, int val) {
        BinaryTreeNode newNode = new BinaryTreeNode(val);
        if (root == null) {
            return newNode;
        }
        BinaryTreeNode head = root;
        BinaryTreeNode prev = null;
        while (head != null) {
            prev = head;
            if (val > head.value) {
                head = head.right;
            } else {
                head = head.left;
            }
        }
        if (val > prev.value) {
            prev.right = newNode;
        } else {
            prev.left = newNode;
        }
        return prev;
    }
}
