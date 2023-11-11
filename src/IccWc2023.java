import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class IccWc2023 {
    public static void main(String[] args) {
        int[] scores = { 10, 10, 8, 6, 4, 4, 4, 2, 2, 2 };
        int[][] fixtures = { { 3, 4 }, { 8, 10 }, { 2, 9 }, { 7, 5 }, { 6, 8 }, { 3, 1 }, { 2, 5 }, { 7, 10 },
                { 3, 6 }, { 4, 9 }, { 2, 1 }, { 8, 5 }, { 4, 7 }, { 9, 10 }, { 3, 5 }, { 1, 7 }, { 4, 8 }, { 9, 6 },
                { 2, 10 } };
        ArrayList<int[]> res = new ArrayList<>();
        Set<String> top4 = new HashSet<>();
        dfs(fixtures, scores, res, top4, "",0);
        System.out.println("Result Combinations:" + res.size());
        System.out.println("Winner Combinations:" + top4.size());
        int[] won = new int[10];
        for(String resD : top4){
            for(char t : resD.toCharArray()){
                won[Integer.parseInt(t+"")]+=1;
            }
        }
        for(int i =0; i < won.length;i++){
            if(won[i]==0){
                System.out.println("Team " + i + " cannot qualify");
            }else{
                System.out.println("Team " + team(i) + " can qualify in " + won[i] + " combinations");
            }
        }
    }

    public static void dfs(int[][] fixtures, int[] scores, ArrayList<int[]> res, Set<String>  top4, String slate, int i) {
        if (i == fixtures.length) {
            res.add(scores.clone());
            String qual = top(scores);
            /*if(!qual.contains("1")){
                System.out.println("======");
                System.out.println(slate);
                for(int j:scores)
                System.out.print(j+",");
                System.out.println();
                System.out.println("======");
            }*/
            top4.add(qual);
            return;
        }
        int[] match = fixtures[i];
        int t1 = match[0] - 1;
        int t2 = match[1] - 1;
        scores[t1] += 2;
        dfs(fixtures, scores, res, top4, (slate + team(t1) + " beat " + team(t2)+","), i + 1);
        scores[t1] -= 2;
        scores[t2] += 2;
        dfs(fixtures, scores, res, top4, (slate + team(t2) + " beat " + team(t1)+","),i + 1);
        scores[t2] -= 2;
    }

    private static String top(int[] scores) {
        PriorityQueue<Team> pq = new PriorityQueue<>(4, new TeamComparator());
        for (int i = 0; i < scores.length; i++) {
            pq.add(new Team(i,scores[i], nrr(i)));
        }
        String res="";
        for (int i = 0; i < 4; i++) {
            Team a = pq.poll();
            res+=a.idx;
        }
        return res;
    }

    public static String team(int i){
        switch(i){
            case 0: return "SAF";case 1: return "IND";case 2: return "NZL";case 3: return "AUS";case 4: return "SLK";
            case 5: return "PAK";case 6: return "AFG";case 7: return "BGL";case 8: return "ENG";case 9: return "NDR";
        }
        return null;
    }

    public static double nrr(int i){
        switch(i){
            case 0: return 2.032;case 1: return 1.353;case 2: return 1.48;case 3: return 1.14;case 4: return -0.205;
            case 5: return -0.4;case 6: return -0.969;case 7: return -1.253;case 8: return -1.634;case 9: return -1.902;
        }
        return 0;
    }

    public static class Team {
        public int idx;
        public int points;
        public double nrr;

        public Team(int idx, int points, double nrr) {
            this.idx = idx;
            this.points = points;
            this.nrr = nrr;
        }
    }

    static class TeamComparator implements Comparator<Team> {
        public int compare(Team s1, Team s2) {
            if (s1.points != s2.points)
                return s2.points-s1.points;
            else
                return (int) (s2.nrr-s1.nrr);
        }
    }
}
