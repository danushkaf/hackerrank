package hasttables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by aux-146 on 12/9/18.
 */
public class CountTriplets {
    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long count = 0;
        Map<Long, List<Integer>> countMap = new HashMap<>();
        for (int i = 0 ; i < arr.size() ; i ++) {
            Long item = arr.get(i);
            List<Integer> indices = countMap.get(item);
            if (indices == null) {
                indices = new ArrayList<>();
            }
            indices.add(i);
            countMap.put(item, indices);
        }
        for (int i = 0 ; i < arr.size() ; i ++) {
            Long item = arr.get(i);
            if (item % r != 0) {
                continue;
            }
            List<Integer> indicesVal1 = countMap.get(item / r);
            List<Integer> indicesVal3 = countMap.get(item * r);
            if (indicesVal1 != null && indicesVal3 != null) {
                long position1 = Collections.binarySearch(indicesVal1, i);
                long position3 = Collections.binarySearch(indicesVal3, i);
                if (position1 < 0) {
                    position1 = (-1 - position1);
                }
                if (position3 < 0) {
                    position3  = (-1 - position3 - 1);
                }
                position3 = indicesVal3.size() - 1 - position3;
                count += position1 * position3;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(Collectors.toList());

        long ans = countTriplets(arr, r);

//        bufferedWriter.write(String.valueOf(ans));
//        bufferedWriter.newLine();
        System.out.println(ans);

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
