import java.util.ArrayList;

public class FourBillion {
    public static void main(String[] args) {
        ArrayList<Long> arr = new ArrayList<Long>();
        arr.add(0L);arr.add(65599L);
        System.out.println(find_integer(arr));
    }

    static final int TWO_POWER_SIXTEEN = (int) (Math.pow(2, 16));

    static Long find_integer(ArrayList<Long> arr) {
        long[] numbersInBucket = new long[TWO_POWER_SIXTEEN];
        for(long inputValue : arr) {
            int bucket = (int) (inputValue>>16);
            numbersInBucket[bucket]++;
        }
        for(int bucket = 0; bucket < TWO_POWER_SIXTEEN; bucket++) {
            if (numbersInBucket[bucket] >= TWO_POWER_SIXTEEN) {
                continue;
            }
            boolean[] presentInBucket = new boolean[TWO_POWER_SIXTEEN]; // Initialized by false by JVM.
            for(long inputValue : arr) {
                int bucketThisValueFallsInto = (int) (inputValue >> 16);
                if(bucketThisValueFallsInto == bucket) {
                    int indexWithinCurrentSubrange = (int) (inputValue % TWO_POWER_SIXTEEN);
                    presentInBucket[indexWithinCurrentSubrange] = true;
                }
            }
            for(int i = 0; i < TWO_POWER_SIXTEEN; i++) {
                if(!presentInBucket[i]) {
                    int startOfSubrange = bucket << 16;
                    return startOfSubrange + (long)i;
                }
            }
        }
        return -1L;
    }
    
}
