package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IslandCount {
    static int xs=0;
    static int ys=0;
    public static void main(String[] args) {
        ArrayList<List<Integer>> matrix = new ArrayList<List<Integer>> ();
        matrix.add(Arrays.asList(1,1,0));
        matrix.add(Arrays.asList(1,1,0));
        matrix.add(Arrays.asList(0,0,1));
        System.out.println("Output =" + max_island_size(matrix));
    }

    
    static Integer max_island_size(ArrayList<List<Integer>> grid) {
        int x=0;
        int y=0;        
        xs = grid.size();
        ys = grid.get(0).size();
        int max=0;
        while(x<xs){
            if(grid.get(x).get(y)==1){
                max=Math.max(max,navigate(grid,x,y,0));
            }
            y+=1;
            if(y==ys){
                y=0;x+=1;
            }
        }
        return max;
    }
    
    static int navigate(ArrayList<List<Integer>> matrix, int i, int j, int size){
        if(i>=xs || j>=ys || i <0 || j<0){
            return 0;
        }
        if(matrix.get(i).get(j)==0){
            return 0 ;
        }
        matrix.get(i).set(j,0);
        size+=navigate(matrix,i-1,j,size+1);
        size+=navigate(matrix,i-1,j-1,size+1);
        size+=navigate(matrix,i-1,j+1,size+1);
        size+=navigate(matrix,i,j-1,size+1);
        size+=navigate(matrix,i,j+1,size+1);
        size+=navigate(matrix,i+1,j-1,size+1);
        size+=navigate(matrix,i+1,j,size+1);
        size+=navigate(matrix,i+1,j+1,size+1);
        return size;
    }
}
