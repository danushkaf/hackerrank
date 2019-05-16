package lesg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Created by danushkaf on 12/11/18.
 */

class Result {

    /*
     * Complete the 'findSchedules' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER workHours
     *  2. INTEGER dayHours
     *  3. STRING pattern
     */

    public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
        // Write your code here
        List<String> schedules = new ArrayList<>();
        List<Integer> toFill = new ArrayList<>();
        int totalScheduled = 0;
        for (int i = 0 ; i < pattern.length() ; i ++) {
            if (pattern.charAt(i) == '?') {
                toFill.add(i);
                continue;
            }
            int ithDayHours = Integer.parseInt(String.valueOf(pattern.charAt(i)));
            totalScheduled += ithDayHours;
        }
        int totalToFill = workHours - totalScheduled;
        int daysToFill = toFill.size();


        return schedules;
    }

}

public class FindScedules {

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int workHours = Integer.parseInt(bufferedReader.readLine().trim());

        int dayHours = Integer.parseInt(bufferedReader.readLine().trim());

        String pattern = bufferedReader.readLine();

        List<String> result = Result.findSchedules(workHours, dayHours, pattern);

        System.out.println(result.stream().collect(joining("\n")) + "\n");
        bufferedReader.close();
    }
}

