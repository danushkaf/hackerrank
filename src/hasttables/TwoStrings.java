package hasttables;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by aux-146 on 12/7/18.
 */
public class TwoStrings {
    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        Set<Character> charsFirst = s1.chars().parallel().mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> charsSecond = s2.chars().parallel().mapToObj(e->(char)e).collect(Collectors.toSet());
        charsFirst.retainAll(charsSecond);
        Character[] res = charsFirst.toArray(new Character[0]);
        String result = "";
        if (res.length != 0) {
            result = "YES";
        } else {
            result = "NO";
        }
        return result;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

//            bufferedWriter.write(result);
//            bufferedWriter.newLine();
            System.out.println(result);
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
