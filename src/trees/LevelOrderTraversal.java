package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal{
    public static void main(String[] args){
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.left = new BinaryTreeNode(5);
        root.right = new BinaryTreeNode(8);
        System.out.println(level_order_traversal(root));
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

     static ArrayList<ArrayList<Integer>> level_order_traversal(BinaryTreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root==null){
            return res;
        }
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        ArrayList<Integer> currLevelNodes = new ArrayList<Integer>();
        int count =1;
        while(!q.isEmpty()){
            BinaryTreeNode node = q.remove();
            currLevelNodes.add(node.value);
            count--;
            if(node.left != null){
                q.add(node.left);
            }
            if(node.right != null){
                q.add(node.right);
            }
            if(count==0){
                res.add(new ArrayList<Integer>(currLevelNodes));
                currLevelNodes = new ArrayList<Integer>();
                count = q.size();
            }            
        }
        return res;
    }
}