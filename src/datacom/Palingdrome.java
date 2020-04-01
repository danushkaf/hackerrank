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
public class Palingdrome {

    /*
     * Complete the 'IsPalindrome' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts STRING Input as parameter.
     */

    public static boolean IsPalindrome(String Input) {
        if (Input.equals(null) || Input.isEmpty()) {
            return false;
        }
        Input = Input.toLowerCase();
        for (int i = 0; i < Input.length() / 2; i++) {
            if (Input.charAt(i) == Input.charAt(Input.length() - i - 1)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/home/danushka/Data/Source/Personal/hackerrank/output/pali"));

        String Input = bufferedReader.readLine();

        boolean result = Palingdrome.IsPalindrome(Input);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
