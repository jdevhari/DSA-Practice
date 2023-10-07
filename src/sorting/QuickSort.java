package sorting;
import java.util.ArrayList;
import java.util.Random;
public class QuickSort {
    
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);arr.add(5);arr.add(4);arr.add(2);arr.add(6);arr.add(3);
        System.out.println("BEFORE:" + (arr));
        quick_sort(arr);
        System.out.println("AFTER: " + (arr));
    }

    
    static void quick_sort(ArrayList<Integer> arr) {
        process(arr, 0, arr.size() - 1);
    }
    
    static void process(ArrayList<Integer> arr, int start, int end) {
        if(start>=end){
            return;
        }
        int smaller =  partition(arr, start, end);
        process(arr, start, smaller-1);
        process(arr, smaller+1, end);

    }

    static int partition(ArrayList<Integer> arr, int start, int end) {
        System.out.println("Partition start with " + start + ":" + end);


        int random_index =  new Random().nextInt((end - start + 1)) + start;
        int swapTemp = arr.get(random_index);
        arr.set(random_index,  arr.get(end));
        arr.set(end, swapTemp);
        int pivot_element = arr.get(end);

        int i = (start-1);
        for (int j = start; j < end; j++) {
            if (arr.get(j) < pivot_element) {
                i++;
                if(i!=j){
                    System.out.println("Swapping " + arr.get(i) + ":" + arr.get(j));
                    swapTemp = arr.get(i);
                    arr.set(i,  arr.get(j));
                    arr.set(j, swapTemp);
                }
            }
        }
        System.out.println("ESwapping " + arr.get((i+1)) + ":" + arr.get(end));
        swapTemp = arr.get(i+1);
        arr.set(i+1, arr.get(end));
        arr.set(end, swapTemp);

        System.out.println("After Partitioning " + arr + " :" + start + ":"+  end + ":"+  (i+1));
        return i+1;
    }
}
