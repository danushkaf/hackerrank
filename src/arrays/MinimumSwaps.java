package arrays;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by danushkaf on 12/5/18.
 */
public class MinimumSwaps {
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int minimumSwaps = 0;
        Map<Integer, Integer> numberToIndex = IntStream
                                        .range(0, arr.length)
                                        .boxed()
                                        .collect(Collectors.toMap(i -> arr[i], Function.identity()));
        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] != i + 1) {
                int locationToMove = numberToIndex.get(i + 1);
                numberToIndex.put(i + 1, i);
                numberToIndex.put(arr[i], locationToMove);
                int temp = arr[i];
                arr[i] = arr[locationToMove];
                arr[locationToMove] = temp;
                minimumSwaps ++;
            }
        }
        return minimumSwaps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

//        bufferedWriter.write(String.valueOf(res));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println(res);

        scanner.close();
    }
}
