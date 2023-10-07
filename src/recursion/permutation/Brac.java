package recursion.permutation;

import java.util.ArrayList;

public class Brac {
    public static void main(String[] args) {
        System.out.println(find_all_well_formed_brackets(6));
    }

    static ArrayList<String> find_all_well_formed_brackets(Integer n) {
        ArrayList<String> res = new ArrayList<String>();
        helper(0,0,n,"", res);
        return res;
    }

    static void helper(int opening, int closing, int n, String s, ArrayList<String> res) {
        if (closing == n) {
            res.add(s);
            return;
        }
        if (opening < n) {
            helper(opening + 1, closing, n, s + '(', res);
        }
        if (opening > closing) {
            helper(opening, closing + 1, n, s + ')', res);
        }
    }
}
