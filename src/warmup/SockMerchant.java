package warmup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SockMerchant {

    // Complete the sockMerchant function below.
    static int sockMerchant(int noOfSocks, int[] sockArr) {
        Map<Integer, Double> socksMap = new HashMap<>();
        for (int sock : sockArr) {
            double noOfSockOfType = 0;
            if (socksMap.get(sock) != null) {
                noOfSockOfType = socksMap.get(sock);
            }
            noOfSockOfType += 0.5;
            socksMap.put(sock, noOfSockOfType);
        }
        int noOfPairs = 0;
        for (double noOfSocksOfType : socksMap.values()) {
            noOfPairs += noOfSocksOfType;
        }
        return noOfPairs;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println(result);

        scanner.close();
    }
}
