package datacom;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Created by danushka on 9/8/19.
 */
public class CountAlphabet {
     /*
     * Complete the 'CountAlphabets' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING Input as parameter.
     */

    public static int CountAlphabets(String input) {
        int count = 0;
        if (input.equals(null) || input.isEmpty()) {
            return 0;
        }
        for (char c : input.toCharArray()) {
            if( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/home/danushka/Data/Source/Personal/hackerrank/output/alphabet"));

        String Input = bufferedReader.readLine();

        int result = CountAlphabet.CountAlphabets(Input);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}