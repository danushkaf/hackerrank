package arrays;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by aux-146 on 12/5/18.
 */
public class NewYearChaos {
    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        final int[] noOfBribes = {0};
        final boolean[] isChaotic = {false};
        Arrays.stream(q).parallel().forEach(
            i -> {
                int noOfBribesDoneByI = 0;
                for (int j = i ; j < q.length ; j ++) {
                    if (q[i - 1] > q[j]) {
                        noOfBribesDoneByI ++;
                    }
                    if (noOfBribesDoneByI > 2) {
                        setVariable(isChaotic, true);
                        break;
                    }
                }
                addVariable(noOfBribes, noOfBribesDoneByI);

            }
        );
        if (isChaotic[0]) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(noOfBribes[0]);
        }
    }

    public static synchronized  void setVariable(boolean[] a, boolean value) {
        a[0] = value;
    }

    public static synchronized  void incrementVariable(int[] a) {
        a[0]++;
    }

    public static synchronized  void addVariable(int[] a, int value) {
        a[0] += value;
    }

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] inputLine, int n) {
        int[] outputFrequencyDistribution = new int[n + 1];
        boolean chaos = false;
        boolean finished = false;
        int count = 0;
        while (!finished) {
            finished = true;
            for (int a = 0 ; a < n - 1 ; a ++) {
                if (inputLine[a] > inputLine[a + 1]) {
                    outputFrequencyDistribution[inputLine[a]] ++;
                    if (outputFrequencyDistribution[inputLine[a]] > 2) {
                        finished = true;
                        chaos = true; // if it's too chaotic, break out
                        break;
                    }
                    count++;
                    int temp = inputLine[a];
                    inputLine[a] = inputLine[a + 1];
                    inputLine[a + 1] = temp;
                    finished = false;
                }
            }
        }
        if (chaos) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(count);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            long start = System.nanoTime();
            minimumBribes(q, n);
            long endFirst = System.nanoTime();
            minimumBribes(q);
            long end = System.nanoTime();
            System.out.println("Time for first method : " + (endFirst - start));
            System.out.println("Time for second method : " + (end - endFirst));
        }

        scanner.close();
    }
}
