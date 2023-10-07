import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OddEven {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5,3,10,45,1);
        // System.out.println(segregate_evens_and_odds(numbers));
        System.out.println(pair_sum_sorted_array(numbers, 6));
    }

    static ArrayList<Integer> segregate_evens_and_odds(ArrayList<Integer> numbers) {
        int size = numbers.size();
        int right = size - 1, left = 0;

        while (left < right) {
            while (right > -1 && numbers.get(right) % 2 == 1) {
                right--;
            }
            while (left < size && numbers.get(left) % 2 == 0) {
                left++;
            }
            if (left < right - 1) {
                System.out.println("Swapping " + left + ":" + right);
                int tmp = numbers.get(left);
                numbers.set(left, numbers.get(right));
                numbers.set(right, tmp);
                System.out.println("After Swapping " + left + ":" + right + "->" + numbers);
            }
        }
        return numbers;
    }

    static ArrayList<Integer> pair_sum_sorted_array(List<Integer> numbers, Integer target) {
        // Write your code here.
        ArrayList<Integer> result = new ArrayList<>();
        result.add(-1);
        result.add(-1);

        int size = numbers.size() - 1;
        int i = 0, j = 0;
        while (i < size) {
            j = i + 1;
            while (j <= size && numbers.get(i) + numbers.get(j) < target) {
                j++;
            }
            System.out.println("FLAG" + (j <= size) + ":" + i + ":" + j + ":" + size);
            System.out.println("FLAG" + (numbers.get(i) + numbers.get(j)));

            if (j <= size && numbers.get(i) + numbers.get(j) == target) {
                result.set(0, i);
                result.set(1, j);
                i = size;
            }
            i++;
        }
        return result;
    }
}
