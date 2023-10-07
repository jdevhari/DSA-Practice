package dropbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DropBoxSyncer {
 
    public static void main(String[] args) {
        int [] [] files = {{20, 5}, 
         {10, 5},
         {3, 7}
         };
         System.out.println(Arrays.toString(solution(files, 100,1,40)));
    }

    static int[] solution(int[][] files, int storageLimit, int uploadSpeed, int duration) {
    int tick = Integer.MAX_VALUE;
    int[] result = new int[0];
    Map<Integer,List<Integer>> dataMap = new HashMap<>();
    for(int i = 0 ; i < files.length; i++){
        tick = Math.min (tick, files[i][1]);
        if(dataMap.get(files[i][1]) == null){
            dataMap.put(files[i][1], new ArrayList<Integer>());
        }
        dataMap.get(files[i][1]).add(i);
    }
    int     currFileId = -1;
    double  remainingChunk = 0;
    int     timeRemaining = duration - tick;
    boolean allDone = timeRemaining <= 0;
    int     bandwidth = 0;
    List<Integer> filesInQueue = new ArrayList<>();
    while(!allDone){
        List<Integer> pendingFiles = dataMap.get(tick);
        if(pendingFiles != null)
            filesInQueue.addAll(pendingFiles);
        if(currFileId == -1){
            int fileSize = Integer.MAX_VALUE;
            for(int fileId: filesInQueue){
                boolean bandWidthNotExceeded = (bandwidth + files[fileId][0] <= storageLimit);
                if( files[fileId][0] < fileSize && bandWidthNotExceeded){
                    fileSize = files[fileId][0];
                    currFileId = fileId;
                    remainingChunk = fileSize;
                }
            }
            if(currFileId!= -1){
                filesInQueue.remove(Integer.valueOf(currFileId));
            }
        }
        if(remainingChunk > 0){
            remainingChunk -= (double)uploadSpeed;
            if(remainingChunk <=0){
                result = append(result, currFileId);
                bandwidth += files[currFileId][0];
                //dataMap.get(tickOfFile).remove(currFileId);
                currFileId = -1;
                remainingChunk = 0;
            }
        }
        timeRemaining--;
        tick++;
        boolean allComplete = currFileId == -1 && filesInQueue.isEmpty() && dataMap.isEmpty();
        allDone = timeRemaining <= 0;
    }
    return result;
}

static public int[] append(int[] i, int newElement) {
    int[] copy = new int[i.length+1];
    System.arraycopy(i, 0, copy, 0, i.length);
    copy[i.length]= newElement;
    return copy;
}

}
