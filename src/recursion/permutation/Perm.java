package recursion.permutation;

import java.util.ArrayList;

public class Perm {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);arr.add(2);arr.add(2);
        System.out.println(get_permutations(arr));
    }

    
static ArrayList<ArrayList<Integer>> get_permutations(ArrayList<Integer> arr) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        helper(arr, 0, ans);
        return ans;
    }

    static void helper(ArrayList<Integer> arr, int fixed_index, ArrayList<ArrayList<Integer>> ans) {
        if (fixed_index == arr.size()) {
            ans.add(new ArrayList<>(arr));
            return;
        }

        for (int i = fixed_index; i < arr.size(); i++) {
            swap(arr, fixed_index, i);
            helper(arr, fixed_index + 1, ans);
            swap(arr, fixed_index, i);
        }
    }

    static void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
