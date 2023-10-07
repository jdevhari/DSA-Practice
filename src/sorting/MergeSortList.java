package sorting;
import java.util.ArrayList;

public class MergeSortList {
    
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(5);arr.add(1);arr.add(6);arr.add(2);arr.add(3);arr.add(4);
        System.out.println("BEFORE:" + (arr));
        merge_sort(arr);
        System.out.println("AFTER: " + (arr));
    }

    
    static void merge_sort(ArrayList<Integer> arr) {
        process(arr, arr.size());
    }
    
    static  void process(ArrayList<Integer> arr, int size){
        if(size<2){
            return;
        }
        int mid = size/2;
        ArrayList<Integer> left = arraySplit(arr, 0, mid);
        ArrayList<Integer> right = arraySplit(arr, mid, size);
        
        process(left,mid);
        process(right,size-mid);
        
        System.out.println("Processed Left " + left + " for arr " + arr);
        System.out.println("Processed Right " + right + " for arr " + arr);
    
        int leftCounter=0, rightCounter=0, parentPos=0;
        while(leftCounter < mid && rightCounter < size-mid){
            if(left.get(leftCounter) <= right.get(rightCounter)){
                arr.set(parentPos++, left.get(leftCounter++));
                System.out.println("OL " + arr + " Size:" + arr.size() + ":" + parentPos + ":" + leftCounter+ ":"  + rightCounter );
            }else{
                arr.set(parentPos++, right.get(rightCounter++));
                System.out.println("OR " + arr + " Size:" + arr.size() + ":" + parentPos + ":" + leftCounter+ ":"  + rightCounter );
            }
        }
        while(leftCounter < mid){
            arr.set(parentPos++, left.get(leftCounter++));
            System.out.println("REML " + arr + " Size:" + arr.size() + ":" + parentPos + ":" + leftCounter+ ":"  + rightCounter );
        }
        while(rightCounter < size-mid){
            arr.set(parentPos++, right.get(rightCounter++));
            System.out.println("REMR " + arr + " Size:" + arr.size() + ":" + parentPos + ":" + leftCounter+ ":"  + rightCounter );
        }        
    }
    
    static ArrayList<Integer> arraySplit(ArrayList<Integer> arr, int start, int end){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=start; i < end; i++){
            result.add(arr.get(i));
        }
        return result;
    }

}
