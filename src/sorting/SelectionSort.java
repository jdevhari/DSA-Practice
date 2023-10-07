package sorting;

import java.util.ArrayList;
import java.util.Arrays;
public class SelectionSort {
    
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);arr.add(5);arr.add(4);arr.add(2);arr.add(6);arr.add(3);
        System.out.println("BEFORE:" + (arr));
        System.out.println("AFTER: " + selection_sort(arr));
    }

    
    static ArrayList<Integer> selection_sort(ArrayList<Integer> arr) {
        // Write your code here.
        int max = -1;
        for(int i = 0 ; i < arr.size()-1; i++){
            max = Math.max(max, arr.get(i));
        }
        int[] idxArray = new int[max+1];
        for(int i = 0 ; i < arr.size()-1; i++){
            idxArray[arr.get(i)]++;
        }
        System.out.println(Arrays.toString(idxArray));
        ArrayList<Integer> newA = new ArrayList<>();
        for(int i = 0 ; i < max+1; i++){
            for(int j = 0; j< idxArray[i]; j++){
                newA.add(i);
            }
        }
        return newA;
    }

    
}
