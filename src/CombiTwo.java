import java.util.ArrayList;

public class CombiTwo {
    
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        arr.add(1);arr.add(2);arr.add(3);arr.add(4);
        generatePermutations(arr,0, res);
        System.out.println(res);
    }

    private static void generatePermutations(ArrayList<Integer> arr, int index, ArrayList<ArrayList<Integer>> res) {
        if(index == arr.size()){
            res.add(new ArrayList<Integer>(arr));
            return;
        }
        System.out.println("Looping from " + index + " on " + arr);
        for(int pick = index; pick <arr.size(); pick++){
            if(pick != index && arr.get(pick) == arr.get(index)){
                continue;
            }
            swap("",arr, pick, index);
            generatePermutations(arr, index+1, res);
            swap("rev", arr, index, pick);
        }

    }

    private static void swap(String str, ArrayList<Integer> arr, int index, int pick) {
        System.out.println(str+"Swapping " + index + " and " + pick);
        int tmp = arr.get(index);
        arr.set(index, arr.get(pick));
        arr.set(pick, tmp);
    }
}
