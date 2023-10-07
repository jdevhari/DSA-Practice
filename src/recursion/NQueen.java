package recursion;

import java.util.ArrayList;

public class NQueen {
    public static void main(String[] args) {
        System.out.println(find_all_arrangements(4));
    }

    
    static ArrayList<ArrayList<String>> find_all_arrangements(Integer n) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ArrayList<String> board = new ArrayList<String>();
        initBoard(board,n);
        
        helper(res,board,n,0);
        return res;
    }
    
    static void initBoard(ArrayList<String> board, int n){
        String row = "";
        for(int i = 0 ; i <n; i++){
            row+="_";
        }        
        for(int i = 0 ; i <n; i++){
            board.add(row);
        }
    }
    
    
    static void helper(ArrayList<ArrayList<String>> res, ArrayList<String> board, int n, int col){
        if(col > n-1){
            res.add((ArrayList<String>)board.clone());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isSafe(board, i, col, n)) {
                board.set(i, setCharAt(board.get(i), col,'q'));
                helper(res,board, n,col + 1);
                board.set(i, setCharAt(board.get(i) ,col,'_'));
            }
        }
    }
    
    
    static boolean isSafe(ArrayList<String> board, int row, int col, int n)
    {
        int i, j;
 
        for (i = 0; i < col; i++)
            if (board.get(row).charAt(i) == 'q')
                return false;
 
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board.get(i).charAt(j) == 'q')
                return false;
 
        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < n; i++, j--)
            if (board.get(i).charAt(j) == 'q')
                return false;
 
        return true;
    }
    
    static String setCharAt(String str, int col, char c){
        StringBuilder upd = new StringBuilder(str);
        upd.setCharAt(col, c);
        return upd.toString();
    }
}
