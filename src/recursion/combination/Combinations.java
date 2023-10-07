package recursion.combination;
import java.util.ArrayList;

public class Combinations {

    static ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> current = new ArrayList<Integer>();

    public static void main(String[] args) {
        System.out.println(find_combinations(5,2));
    }
    

    static ArrayList<ArrayList<Integer>> find_combinations(Integer n, Integer k) {
        combinations_recursive(1, n, k);
        return result;
    }
    
    static void combinations_recursive(int current_number, int n, int k) {
        if (k == current.size()) {
            result.add(new ArrayList<Integer>(current));    
            return;
        }
        if (current_number == n + 1) {
            return;
        }
    
        current.add(current_number);    
        combinations_recursive(current_number + 1, n, k);
        current.remove(Integer.valueOf(current_number));    
        combinations_recursive(current_number + 1, n, k);
    }
}
