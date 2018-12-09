package warmup;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by danushkaf on 12/5/18.
 */
public class JumpingCloud {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        int noOfMoves = 0;
        if (c.length == 2) {
            return 1;
        }
        for (int i = 0 ; i < c.length ; i ++) {
            if (i == c.length - 1) {
                noOfMoves += 1;
                break;
            } else if (i == c.length - 2) {
                noOfMoves += 1;
                continue;
            } else if (c[i + 2] == 0) {
                if (i != 0) {
                    noOfMoves += 1;
                }
                i ++;
                continue;
            } else if (c[i + 2] == 1) {
                if (i != 0) {
                    noOfMoves += 1;
                }
                continue;
            }
        }
        return noOfMoves;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        System.out.println(result);

        scanner.close();
    }
}
