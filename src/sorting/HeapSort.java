package sorting;
import java.util.ArrayList;

public class HeapSort {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(5);arr.add(8);arr.add(3);arr.add(9);arr.add(4);arr.add(1);arr.add(7);
        System.out.println(arr);
        sort(arr);
        System.out.println(arr);
    }

    public static void sort(ArrayList<Integer> arr)
    {
        int N = arr.size();
 
        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);
 
        System.out.println("INITAL HEAPIFICATION COMPLETE " + arr);
        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr.get(0);
            arr.set(0,arr.get(i));
            arr.set(i, temp);
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    static void heapify(ArrayList<Integer> arr, int N, int i)
    {
        System.out.println("Heapify called with N:"+ N + " i:" + i);
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
 
        // If left child is larger than root
        if (l < N && arr.get(l) > arr.get(largest))
            largest = l;
 
        // If right child is larger than largest so far
        if (r < N && arr.get(r) > arr.get(largest))
            largest = r;
 
        // If largest is not root
        if (largest != i) {
            System.out.println("Swapping " + i + " and " + largest);
            int swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
 
            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }
 

}
