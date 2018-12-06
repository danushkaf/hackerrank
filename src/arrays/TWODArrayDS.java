package arrays;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by aux-146 on 12/5/18.
 */
public class TWODArrayDS {
    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int hourGlassSum = 0;
        boolean isMaxSet = false;
        for (int i = 1 ; i < arr.length - 1 ; i ++) {
            for (int j = 1 ; j < arr.length - 1 ; j ++) {
                int currentHourGlassSum = arr[i - 1][j - 1] + arr[i - 1][j] + arr[i - 1][j + 1] +
                    arr[i][j] + arr[i + 1][j - 1] + arr[i + 1][j] + arr[i + 1][j + 1];
                if (!isMaxSet || currentHourGlassSum > hourGlassSum) {
                    isMaxSet = true;
                    hourGlassSum = currentHourGlassSum;
                }
            }
        }
        return hourGlassSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        System.out.println(result);

        scanner.close();
    }
}
