import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class FrequentKItems {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);arr.add(2);arr.add(3);arr.add(4);arr.add(5);arr.add(2);
        arr.add(1);arr.add(3);arr.add(3);arr.add(2);arr.add(2);arr.add(2);
        System.out.println(find_top_k_frequent_elements(arr,2));
    }

    static class pair implements Comparable<pair> {
        int element, frequency;

        public pair(int element, int frequency) {
            this.element = element;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(pair o) {
            return this.frequency - o.frequency;
        }
    }

    static ArrayList<Integer> find_top_k_frequent_elements(ArrayList<Integer> arr, Integer k) {
        if (k == arr.size()) {
            return arr;
        }

        // Calculate and store frequency of all elements.
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int i : arr) {
            frequency.put(i, frequency.getOrDefault(i, 0) + 1);
        }

        // Creating a min-heap of size k.
        PriorityQueue<pair> pq = new PriorityQueue<>();
        for (int element : frequency.keySet()) {
            int freq_of_element = frequency.get(element);
            pq.add(new pair(element, freq_of_element));

            // Keeping top k elements in priority queue.
            // Popping lower (extra) elements.
            if (pq.size() > k) {
                pq.poll();
            }
        }

        ArrayList<Integer> result = new ArrayList<>(k);

        for (int i = 0; i < k; i++) {
            result.add(pq.peek().element);
            pq.poll();
        }
        return result;
    }

}
