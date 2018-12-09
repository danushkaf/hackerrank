package warmup;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by danushkaf on 12/5/18.
 */
public class RepeatedString {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        long noOfaLetters = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == 'a') {
                noOfaLetters ++;
            }
        }
        long noOfRepeatsToString = n / s.length();
        long remainder = n % s.length();
        long remainderACount = 0;
        for (int i = 0 ; i < remainder ; i ++) {
            if (chars[i] == 'a') {
                remainderACount ++;
            }
        }
        noOfaLetters = noOfaLetters * noOfRepeatsToString + remainderACount;
        return noOfaLetters;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println(result);

        scanner.close();
    }
}
