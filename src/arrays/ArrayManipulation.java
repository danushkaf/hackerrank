package arrays;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by danushkaf on 12/6/18.
 */
public class ArrayManipulation {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] queries = new int[m][3];

        long[] arr = new long[n];
        for(int i = 0 ; i < n ; i ++) {
            arr[i] = 0;
        }

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
            long sum = arr[queries[i][0] - 1] + queries[i][2];
            arr[queries[i][0] - 1] = sum;
            if(queries[i][1] < n) {
                long newEndValue = arr[queries[i][1]] - queries[i][2];
                arr[queries[i][1]] = newEndValue;
            }
        }

        long max = 0;
        long temp = 0;
        for (long item : arr) {
            temp += item;
            if(temp > max) {
                max = temp;
            }
        }
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println(max);
        scanner.close();
    }
}
