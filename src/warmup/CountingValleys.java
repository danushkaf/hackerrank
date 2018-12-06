package warmup;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by aux-146 on 12/5/18.
 */
public class CountingValleys {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        int noOfValleys = 0;
        int altitude = 0;
        for (char c : s.toCharArray()) {
            if (c == 'U') {
                altitude += 1;
            } else if (c == 'D') {
                altitude -= 1;
            }
            if (altitude == -1 && c == 'D') {
                noOfValleys +=1;
            }
        }
        return noOfValleys;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println(result);

        scanner.close();
    }
}
