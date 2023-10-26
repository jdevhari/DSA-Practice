import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class IccWc2023 {
    public static void main(String[] args) {
        int[] scores = { 10, 8, 8, 6, 4, 4, 4, 2, 2, 2 };
        int[][] fixtures = { { 2, 6 }, { 3, 4 }, { 8, 10 }, { 1, 9 }, { 7, 5 }, { 6, 8 }, { 3, 2 }, { 1, 5 }, { 7, 10 },
                { 3, 6 }, { 4, 9 }, { 1, 2 }, { 8, 5 }, { 4, 7 }, { 9, 10 }, { 3, 5 }, { 2, 7 }, { 4, 8 }, { 9, 6 },
                { 1, 10 } };
        ArrayList<int[]> res = new ArrayList<>();
        Set<String> top4 = new HashSet<>();
        dfs(fixtures, scores, res, top4, 0);
        System.out.println("Result Combinations:" + res.size());
        System.out.println("Winner Combinations:" + top4.size());
        int[] won = new int[10];
        for(String resD : top4){
            for(char t : resD.toCharArray()){
                won[Integer.parseInt(t+"")]=1;
            }
        }
        for(int i =0; i < won.length;i++){
            if(won[i]==0){
                System.out.println("Team " + i + " cannot qualify");
            }
        }
    }

    public static void dfs(int[][] fixtures, int[] scores, ArrayList<int[]> res, Set<String>  top4, int i) {
        if (i == fixtures.length) {
            res.add(scores.clone());
            top4.add(top(scores));
            return;
        }
        int[] match = fixtures[i];
        int t1 = match[0] - 1;
        int t2 = match[1] - 1;
        scores[t1] += 2;
        dfs(fixtures, scores, res, top4, i + 1);
        scores[t1] -= 2;
        scores[t2] += 2;
        dfs(fixtures, scores, res, top4, i + 1);
    }

    private static String top(int[] scores) {
        PriorityQueue<Team> pq = new PriorityQueue<>(4, new TeamComparator());
        for (int i = 0; i < scores.length; i++) {
            pq.add(new Team(i,scores[i]));
            if (pq.size() == 5) {
                pq.poll();
            }
        }
        String res="";
        for (int i = 0; i < 4; i++) {
            Team a = pq.poll();
            res+=a.idx;
        }
        return res;
    }

    public static class Team {
        public int idx;
        public int points;

        public Team(int idx, int points) {
            this.idx = idx;
            this.points = points;
        }

    }

    static class TeamComparator implements Comparator<Team> {
        public int compare(Team s1, Team s2) {
            if (s1.points < s2.points)
                return 1;
            else if (s1.points > s2.points)
                return -1;
            return 0;
        }
    }
}
