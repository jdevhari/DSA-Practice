import java.util.ArrayList;

public class OddEven {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);numbers.add(3);numbers.add(10);numbers.add(45);numbers.add(1);
        //System.out.println(segregate_evens_and_odds(numbers));
        System.out.println(pair_sum_sorted_array(numbers,6));
    }


    static ArrayList<Integer> segregate_evens_and_odds(ArrayList<Integer> numbers) {
        System.out.println(numbers);
        // Write your code here.
        int size = numbers.size();
        int right=size-1, left = 0;
        
        while(left < right){
            while(right > -1 && numbers.get(right)%2==1){
                right--;
            }
            while(left < size && numbers.get(left)%2==0){
                left++;
            }
            if(left< right-1){
                System.out.println("Swapping " + left + ":" + right);
                int tmp = numbers.get(left);
                numbers.set(left, numbers.get(right));
                numbers.set(right, tmp);
                System.out.println("After Swapping " + left + ":" + right + "->" + numbers);
            }
        }
        return numbers;
    }


    static ArrayList<Integer> pair_sum_sorted_array(ArrayList<Integer> numbers, Integer target) {
        // Write your code here.
        ArrayList<Integer> result =  new ArrayList<>();
        result.add(-1);result.add(-1);
        
        int size = numbers.size()-1;
        int i = 0,j = 0;
        while(i<size){
            j=i+1;
            while(j<=size && numbers.get(i)+numbers.get(j) <target){
                j++;
            }
            System.out.println("FLAG" + (j<=size) + ":" + i + ":" + j + ":" + size);
            System.out.println("FLAG" + (numbers.get(i)+numbers.get(j) ));

            if(j<=size && numbers.get(i)+numbers.get(j) == target){
                result.set(0,i);
                result.set(1,j);
                i=size;
            }
            i++;
        }
        return result;
    }
}
