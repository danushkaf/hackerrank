package hasttables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by danushkaf on 12/7/18.
 */
public class RansomNotes {
    // Complete the checkMagazine function below.
    static void checkMagazine(int n, String[] magazine, String[] noteItems) {
        long start = System.currentTimeMillis();
        boolean check = true;
        List<String> magazineWords = new ArrayList<>(Arrays.asList(magazine));
        for (int i = 0; i < n; i++) {
            if (magazineWords.contains(noteItems[i])) {
                magazineWords.remove(noteItems[i]);
                continue;
            }
            check = false;
            break;
        }
        if (check) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);
        String[] magazine = new String[m];
        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        checkMagazine(n, magazine, noteItems);


        scanner.close();
    }
}
