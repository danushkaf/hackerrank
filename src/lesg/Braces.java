package lesg;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by danushkaf on 12/11/18.
 */
public class Braces {

    public static final String ANSWER_NO = "NO";
    public static final String ANSWER_YES = "YES";

    // Complete the braces function below.
    static String[] braces(String[] values) {
        String[] result = new String[values.length];
        for (int i = 0 ; i < values.length ; i++) {
            String value = values[i];
            char[] braces = value.toCharArray();
            Stack<Character> stack = new Stack<Character>();
            for (char brace : braces) {
                if (brace == '{' || brace == '[' || brace == '(') {
                    stack.push(brace);
                } else {
                    if (stack.empty()) {
                        result[i] = ANSWER_NO;
                        break;
                    }
                    char popedBrace = stack.pop();
                    if ((brace == ']' && popedBrace == '[') || (brace == '}' && popedBrace == '{') || (brace == ')' && popedBrace == '(')) {
                        continue;
                    } else {
                        result[i] = ANSWER_NO;
                        break;
                    }
                }
            }
            if (stack.empty() && !(result[i] != null && ANSWER_NO.equals(result[i]))) {
                result[i] = ANSWER_YES;
            }
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int valuesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] values = new String[valuesCount];

        for (int i = 0; i < valuesCount; i++) {
            String valuesItem = scanner.nextLine();
            values[i] = valuesItem;
        }

        String[] res = braces(values);

        for (int i = 0; i < res.length; i++) {
//            bufferedWriter.write(res[i]);
            System.out.println(res[i]);

            if (i != res.length - 1) {
//                bufferedWriter.write("\n");
                System.out.println("\n");
            }
        }

//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}

