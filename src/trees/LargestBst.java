package trees;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Deque;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

class BinaryTreeNode {
    Integer value;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class Solution {

    static Integer find_largest_bst(BinaryTreeNode root) {
        long[] max = new long[] { 0 };
        helper(root, max);
        return Integer.valueOf((int) max[0]);
    }

    static long helper(BinaryTreeNode root, long[] max) {
        if (root == null) {
            return 0L;
        }
        long ll = root.left == null ? 0 : helper(root.left, max);
        long rl = root.right == null ? 0 : helper(root.right, max);

        boolean isCurrentRootBst = root.left != null ? root.left.value <= root.value : true;
        isCurrentRootBst = isCurrentRootBst && (root.right != null ? root.right.value >= root.value : true);
        isCurrentRootBst = isCurrentRootBst && ((ll != -1 && rl != -1) || (root.left == null && root.right == null));
        if (isCurrentRootBst) {
            if ((ll + rl + 1) > max[0]) {
                System.out.println("Root of Largest Bst is " + root.value + " with size " + (ll + rl + 1));
            }
            max[0] = Math.max(max[0], ll + rl + 1);
            return ll + rl + 1;
        } else {
            return -1;
        }
    }

    public class LargestBst {

        static void output_int32(Integer argument) throws IOException {
            output_string.append(argument);
        }

        static Integer input_int32(Object data) {
            Integer argument = (Integer) data;
            return argument;
        }

        static BinaryTreeNode input_BinaryTreeNode_int32(Object data) {
            JSONArray json_array = (JSONArray) data;
            BinaryTreeNode argument = null; // Root of the tree that we return.
            Deque<BinaryTreeNode> nodes_with_uninitialized_children = new LinkedList<>();
            boolean next_child_is_left = true;
            for (Object json_array_item : json_array) {
                BinaryTreeNode new_node = null;
                if (!JSONObject.NULL.equals(json_array_item)) {
                    new_node = new BinaryTreeNode(input_int32(json_array_item));
                    if (argument == null) {
                        argument = new_node;
                    }
                }
                if (!nodes_with_uninitialized_children.isEmpty()) {
                    BinaryTreeNode parent_node = nodes_with_uninitialized_children.removeFirst();
                    if (next_child_is_left) {
                        parent_node.left = new_node;
                    } else {
                        parent_node.right = new_node;
                    }
                    next_child_is_left = !next_child_is_left;
                }
                if (new_node != null) {
                    nodes_with_uninitialized_children.addLast(new_node);
                    nodes_with_uninitialized_children.addLast(new_node);
                }
            }
            return argument;
        }

        private static final DecimalFormat float_formatter = new DecimalFormat("0.00");
        private static final StringBuilder output_string = new StringBuilder();

        public static void main(String[] args) throws Exception {

            BinaryTreeNode root;
            try {
                /*
                 * ByteArrayOutputStream json_string = new ByteArrayOutputStream();
                 * byte[] buffer = new byte[1024];
                 * for (int length; (length = System.in.read(buffer)) != -1;) {
                 * json_string.write(buffer, 0, length);
                 * }
                 * JSONObject data = new JSONObject(json_string.toString("UTF-8"));
                 */

                String text = new String(
                        Files.readAllBytes(Paths.get("C:\\dev\\Interview\\DSA-Practice\\src\\trees/input.json")),
                        StandardCharsets.UTF_8);
                JSONObject data = new JSONObject(text);

                root = input_BinaryTreeNode_int32(data.get("root"));
            } catch (Exception ex) {
                System.err.println("reading-input-failed-json");
                ex.printStackTrace();
                throw ex;
            }

            PrintStream original_out = System.out;
            System.setOut(System.err);
            Integer result = Solution.find_largest_bst(root);
            System.setOut(original_out);
            output_int32(result);
            output_string.append('\n');
            System.out.print(output_string.toString());
        }
    }
}
