package recursion;

import java.util.ArrayList;

public class PDComposition {
    public static void main(String[] args) {
        System.out.println(generate_palindromic_decompositions("abracadabra"));
    }

    static ArrayList<String> generate_palindromic_decompositions(String s) {
        ArrayList<String> result = new ArrayList<String>() ;
        String newStr=""+s.charAt(0);
        for(int i = 1 ;i < s.length(); i ++){
            newStr += "_"+ s.charAt(i);
        }
        System.out.println("New string " + newStr);
        helper(newStr, result);
        return result;
    }

    private static void helper(String newStr, ArrayList<String> result) {
        int pos = -1;
        for(int i = 0; i < newStr.length(); i++){
            if(newStr.charAt(i) == '_'){
                pos = i;
                break;
            }
        }
        if(pos == -1){
            String[] subStrings = newStr.split("\\|");
            boolean isValid = true;
            for(int i = 0 ; i < subStrings.length;i++){
                if(!checkPD(subStrings[i])){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                result.add(newStr);
            }
        }else{
            String pre = newStr.substring(0, pos);
            String suf = newStr.substring(pos+1);
            helper( pre+suf , result);
            helper(pre + "|" +  suf, result);
        }
    }

    private static boolean checkPD(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
