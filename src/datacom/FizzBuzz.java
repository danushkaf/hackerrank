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
public class FizzBuzz {
    /*
     * Complete the 'fizzBuzz' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    public static void fizzBuzz(int n) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/home/danushka/Data/Source/Personal/hackerrank/output/fizzbuzz"));
        // Write your code
        for (int i = 1 ; i <= n ; i ++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
                bufferedWriter.write("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
                bufferedWriter.write("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
                bufferedWriter.write("Buzz");
            } else {
                System.out.println(String.valueOf(i));
                bufferedWriter.write(String.valueOf(i));
            }

            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        FizzBuzz.fizzBuzz(n);

        bufferedReader.close();
    }
}
