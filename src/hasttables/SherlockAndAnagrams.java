package hasttables;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by danushkaf on 12/9/18.
 */
public class SherlockAndAnagrams {
    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        Map<List<Character>, Integer> substringMap = new HashMap<>();
        int count = 0;
        for (int i = 0 ; i < s.length() ; i++) {
            for (int j = i ; j < s.length() ; j++) {
                String substring = s.substring(i, j + 1);
                List<Character> collect = substring.chars().parallel()
                                                           .mapToObj(e -> (char) e)
                                                           .collect(Collectors.toList());
                Collections.sort(collect);
                if (substringMap.containsKey(collect)) {
                    int number = substringMap.get(collect);
                    substringMap.put(collect, ++ number);
                    continue;
                }
                substringMap.put(collect, 1);
            }
        }
        for (Integer localCount : substringMap.values()) {
            int triangular = (localCount) * (localCount - 1) / 2;
            count += triangular;
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
            System.out.println(result);
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
