package hasttables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FrequencyQueries {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Long> collect = new HashMap<>();
        Map<Long, Set<Integer>> countToNumbers = new HashMap<>();
        for (List<Integer> query : queries) {
            int queryType = query.get(0);
            int queryValue = query.get(1);
            if (queryType == 1) {
                boolean contains = collect.containsKey(queryValue);
                long count = 0;
                if (!contains) {
                    count = 0;
                } else {
                    count = collect.get(queryValue);
                }
                boolean containsCountOrig = countToNumbers.containsKey(count);
                Set<Integer> countToNumSet;
                if (!containsCountOrig) {
                    countToNumSet = new HashSet<>();
                } else {
                    countToNumSet = countToNumbers.get(count);
                }
                countToNumSet.remove(queryValue);
                countToNumbers.put(count, countToNumSet);
                collect.put(queryValue, ++count);
                boolean containsCount = countToNumbers.containsKey(count);
                if (!containsCount) {
                    countToNumSet = new HashSet<>();
                } else {
                    countToNumSet = countToNumbers.get(count);
                }
                countToNumSet.add(queryValue);
                countToNumbers.put(count, countToNumSet);
            } else if (queryType == 2) {
                boolean contains = collect.containsKey(queryValue);
                long count = 0;
                if (contains) {
                    count = collect.get(queryValue);
                    boolean containsCountOrig = countToNumbers.containsKey(count);
                    Set<Integer> countToNumSet;
                    if (!containsCountOrig) {
                        countToNumSet = new HashSet<>();
                    } else {
                        countToNumSet = countToNumbers.get(count);
                    }
                    countToNumSet.remove(queryValue);
                    countToNumbers.put(count, countToNumSet);
                    --count;
                    if (count == 0) {
                        collect.remove(queryValue);
                    } else {
                        collect.put(queryValue, count);
                    }
                    boolean containsCount = countToNumbers.containsKey(count);
                    if (!containsCount) {
                        countToNumSet = new HashSet<>();
                    } else {
                        countToNumSet = countToNumbers.get(count);
                    }
                    countToNumSet.add(queryValue);
                    countToNumbers.put(count, countToNumSet);
                }
            } else if (queryType == 3) {
                ans.add(countToNumbers.getOrDefault(Long.valueOf(queryValue), Collections.emptySet()).isEmpty() ? 0 : 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        long start = System.currentTimeMillis();
        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);
        long end = System.currentTimeMillis();

//        bufferedWriter.write(
//            ans.stream()
//                .map(Object::toString)
//                .collect(joining("\n"))
//                + "\n"
//        );
        System.out.println(ans.stream()
            .map(Object::toString)
            .collect(joining("\n"))
            + "\n");
        System.out.println(end - start);

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
