package dropbox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoadEstimator {
    public static void main(String[] args) {
        /*
         * int[] sizes = {12, 17, 2, 27, 23};
         * int[] uploadingStart = {2, 5, 8, 6, 2};
         * int v = 9;
         */
        int[] sizes = { 21, 10 };
        int[] uploadingStart = { 100, 105 };
        int v = 2;
        for (int i : solution(sizes, uploadingStart, v))
            System.out.print(i + ",");
    }

    public static int[] solution(int[] sizes, int[] uploadingStart, int v) {
        int[] result = new int[sizes.length];
        Map<Integer, Map<Integer, Double>> trackingMap = new HashMap<>();
        int minStart = Integer.MAX_VALUE;
        for (int i = 0; i < sizes.length; i++) {
            minStart = Math.min(minStart, uploadingStart[i]);
            Map<Integer, Double> dataMap = trackingMap.get(uploadingStart[i]);
            if (dataMap == null) {
                dataMap = new HashMap<>();
                trackingMap.put(uploadingStart[i], dataMap);
            }
            dataMap.put(i, (double) sizes[i]);
        }
        ;
        int tick = minStart;
        boolean allDone = false;
        Map<Integer, Double> processMap = new HashMap<>();
        while (!allDone) {
            System.out.println("Tick:" + tick + " Processing " + processMap.values());
            Map<Integer, Double> dataMap = trackingMap.get(tick);
            if (dataMap != null) {
                processMap.putAll(dataMap);
                trackingMap.remove(tick);
            }
            int coUploaderCount = processMap.keySet().size();
            Iterator<Integer> it = processMap.keySet().iterator();
            while (it.hasNext()) {
                Integer uploader = it.next();
                double remain = processMap.get(uploader) - ((double) v / coUploaderCount);
                System.out.println("\t" + uploader + ":" + processMap.get(uploader) + ":"
                        + ((double) v / coUploaderCount) + ":" + remain);
                if (remain <= 0) {
                    it.remove();
                    result[uploader] = tick + 1;
                    System.out.println("\t\t" + uploader + " started at " + uploadingStart[uploader] + " finished at "
                            + result[uploader]);
                } else {
                    processMap.put(uploader, remain);
                }
            }
            allDone = trackingMap.isEmpty() && processMap.isEmpty();
            // allDone = tick>10;
            tick++;
        }

        return result;
    }

}
