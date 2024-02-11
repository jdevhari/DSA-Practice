package recursion.combination;

import java.util.ArrayList;
import java.util.HashMap;

public class TwoSets {
    static int count =0;

    static HashMap<Integer, ArrayList<Integer>> memo = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        arr.add(1);arr.add(2);arr.add(3);arr.add(4);arr.add(7);arr.add(5);
        int target = 11;
        for(int i =0;i < arr.size()/2;i++){
            int currVal = arr.get(i);
            arr.remove(Integer.valueOf(currVal));
            generateCombinations(arr, target, res,"");
            generateCombinations(arr, target-currVal, res,""+currVal+",");
        }
        System.out.println(count);
        System.out.println(res);

    }

    private static void generateCombinations(ArrayList<Integer> arr, int target, 
        ArrayList<ArrayList<Integer>> res, String slate) {
            count++;
            if(0==target){
                //memoize(arr, target);
                res.add(convert(slate));
                return;
            }
            if(target < 0){
                return;
            }
            if(arr.size()==0){
                return;
            }
            ArrayList<Integer> larr = new ArrayList<>(arr);
            int currVal = larr.get(0);
            larr.remove(Integer.valueOf(currVal));
            generateCombinations(larr, target, res,slate);
            generateCombinations(larr, target-currVal, res,slate+currVal+",");
    }

    public static void memoize(ArrayList<Integer> arr, int target) {
        if(memo.get(target) == null){
            memo.put(target, new ArrayList<>());
        }
    }

    private static ArrayList<Integer> convert(String slate) {
        ArrayList<Integer> res = new ArrayList<>();
        String[] arr = slate.split(",");
        for(String s : arr){
            res.add(Integer.valueOf(s));
        }
        return res;
    }

}
