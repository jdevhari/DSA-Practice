package sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort{
    
}
    static ArrayList<Integer> radix_sort(ArrayList<Integer> arr) {
        int[] pArr = new int[arr.size()];
        for(int i = 0; i < arr.size(); i++) pArr[i] = arr.get(i);
        radixsort(pArr, arr.size());
        ArrayList<Integer> list = Arrays.asList(pArr);
        return list;
    }
    
    static int getMax(int a[], int n) {  
        int max = a[0];  
        for(int i = 1; i<n; i++) {  
            if(a[i] > max)  
                max = a[i];  
        }  
        return max;
    }
  
    static void countingSort(int a[], int n, int place){  
        int[] output = new int[n+1];  
        int[] count = new int[10];  
        
        for (int i = 0; i < n; i++)  
            count[(a[i] / place) % 10]++;  
      
        for (int i = 1; i < 10; i++)  
            count[i] += count[i - 1];  
  
        for (int i = n - 1; i >= 0; i--) {  
            output[count[(a[i] / place) % 10] - 1] = a[i];  
            count[(a[i] / place) % 10]--;  
        }  
  
        for (int i = 0; i < n; i++)  
            a[i] = output[i];  
    }  
  

    static void radixsort(int a[], int n) {  
   
        // get maximum element from array  
        int max = getMax(a, n);  
  
        // Apply counting sort to sort elements based on place value  
        for (int place = 1; max / place > 0; place *= 10)  
            countingSort(a, n, place);  
    }  
    
