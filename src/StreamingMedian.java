import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamingMedian {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(3,8,5,2);
        System.out.println(online_median(arr));
    }

    public static ArrayList<Integer> online_median(List<Integer> arr) {
        ArrayList<Integer> result = new ArrayList<>();
        DoublePq minHeap = new DoublePq();
        DoublePq maxHeap = new DoublePq();

        for (int i = 0; i < arr.size(); i++) {
            int val = arr.get(i);
            maxHeap.add(val);
            minHeap.add(maxHeap.pollFirst());

            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.pollLast());
            }

            int median = -1;
            if (minHeap.size() == maxHeap.size()) {
                median = (minHeap.last() + maxHeap.first()) / 2;
            } else {
                median = maxHeap.first();
            }
            result.add(median);
        }

        return result;
    }

    static class Node {
        public int data;
        public Node nextNode = null;

        public Node(int data) {
            this.data = data;
        }
    }

    static class DoublePq {
        public Node head = null;
        public Node tail = null;
        public int count = 0;
        public String str="";


        public int size() {
            return count;
        }

        public Node add(int val) {
            str+=val+",";
            count++;
            Node newNode = new Node(val);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else if(val< head.data){
                newNode.nextNode=head;
                head=newNode;
            } else {
                Node tmpHead = head;
                Node tmpNext = tmpHead.nextNode;
                while (tmpNext != null && tmpHead.data < val) {
                    tmpHead = tmpHead.nextNode;
                    tmpNext = tmpNext.nextNode;
                }
                tmpHead.nextNode = newNode;
                newNode.nextNode = tmpNext;
                if (tmpNext == null) {
                    tail = newNode;
                }
            }
            return head;
        }

        public int pollFirst() {
            int val = head.data;
            head = head.nextNode;
            if (count == 1) {
                tail = head;
            }
            count--;
            return val;
        }

        public int pollLast() {
            int val = tail.data;
            if (count == 1) {
                tail=null;
                head=null;
            } else {
                Node tmpHead = head;
                while (tmpHead.nextNode.nextNode != null) {
                    tmpHead = tmpHead.nextNode;
                }
                tail = tmpHead;
                tail.nextNode = null;
            }
            count--;
            return val;
        }

        public int first() {
            return head.data;
        }

        public int last() {
            return tail.data;
        }
    }

}
