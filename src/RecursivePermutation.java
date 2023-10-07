import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecursivePermutation {

  public static void main(String[] args) {
    // List<Integer> permList = new ArrayList<>();
    // solve("", "900", "900", permList);
    // Collections.sort(permList);
    // System.out.println(permList);

    System.out.println(solution(new int[] { 1, 3, 900, 10 }));

  }

  public static void solve(String curr, String rem, String orig, List<Integer> list) {
    if (rem.length() == 0) {
      if (diffByOne(orig, curr)) {
        list.add(Integer.valueOf(curr));
      }
      return;
    }
    for (int i = 0; i < rem.length(); i++) {
      String ros = rem.substring(0, i) + rem.substring(i + 1);
      solve(curr + rem.charAt(i), ros, orig, list);
    }
  }

  private static boolean diffByOne(String orig, String curr) {
    int diff = 0;
    boolean result = false;
    for (int i = 0; i < orig.length(); i++) {
      if (orig.charAt(i) != curr.charAt(i)) {
        diff++;
      }
    }
    result = diff == 2;
    return result;
  }

  public static boolean solution(int[] numbers) {
    int currVal = -1;
    boolean strIncreasing = true;
    int swapCount = 0;
    for (int i = 0; i < numbers.length; i++) {
      System.out.println("Comparing " + numbers[i] + " and " + currVal);
      if (numbers[i] > currVal) {
        currVal = numbers[i];
        continue;
      } else {
        if (swapCount == 1) {
          strIncreasing = false;
          break;
        }
        List<Integer> permList = new ArrayList<Integer>();
        String numAsString = String.valueOf(currVal);
        solve("", numAsString, numAsString, permList);
        Collections.sort(permList);
        System.out.println("Sorted Swapped " + permList);
        boolean swapDone = false;
        for (int j = 0; j < permList.size(); j++) {
          boolean swapOk = i > 1 ? permList.get(j) > numbers[i - 2] : true;
          swapOk = swapOk && permList.get(j) < numbers[i];
          if (swapOk) {
            numbers[i - 1] = permList.get(j);
            currVal = numbers[i];
            swapDone = true;
            swapCount++;
          }
        }
        if (!swapDone) {
          strIncreasing = false;
          break;
        }

      }
    }
    return strIncreasing;
  }
}
