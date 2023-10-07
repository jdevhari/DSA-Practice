package recursion.permutation;

import java.util.ArrayList;

public class LetterCasePermutation {
    public static void main(String[] args) {
        System.out.println(letter_case_permutations("a1z"));
    }


    static ArrayList<String> letter_case_permutations(String s) {
        ArrayList<String> res = new ArrayList<String>();
        helper(s,res,0,"");
        return res;
    }

    static void helper(String s, ArrayList<String> res, int n, String slate) {
        System.out.println("Processing " + slate + " for " + n);
        if(slate.length()==s.length()){
            System.out.println("Adding " + slate);
            res.add(slate);
            return;
        }
        char c = s.charAt(n);
        
        slate +=c;
        helper(s,res,n+1, slate);            
        if(Character.isAlphabetic(c)){
            if(Character.isLowerCase(c)){
                    c = Character.toUpperCase(c);
            }else{
                c = Character.toLowerCase(c);
            }
            slate = slate.substring(0,slate.length()-1) + c;
            helper(s,res,n+1, slate);
        }
    }
}
