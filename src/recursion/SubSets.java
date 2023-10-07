package recursion;

import java.util.ArrayList;

public class SubSets {

    public static void main(String[] args) {
        char[] arr = {'a','b','1'};
        System.out.println(generateAllSubsets(arr));
    }

    static ArrayList<String> generateAllSubsets(char[] arr){
        ArrayList<String> res = new ArrayList<>();
        helper(arr,0,"", res);
        return res;
        
    }

    private static void helper(char[] arr, int ind, String slate, ArrayList<String> res) {
        //if(ind == arr.length){
            res.add(slate);
        //}
        for(int j = ind; j < arr.length; j++) {
            //if(j!=ind && arr[j]==arr[j-1]){
            //    continue;
            //}
            slate += arr[j];
            helper(arr,j+1, slate, res);
            slate = slate.substring(0,slate.length()-1);
        }
    }

    
}
