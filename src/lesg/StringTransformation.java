package lesg;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by danushakf on 12/11/18.
 */
public class StringTransformation {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<Character, Integer> characterMap = new HashMap<>();
    private static final Map<Integer, Character> numberToCharacterMap = new HashMap<>();
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        fillCharacterMap();
        fillNumberToCharacterMap();
        int testCasesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] testCases = new String[testCasesCount];

        for (int i = 0; i < testCasesCount; i++) {
            String testCasesLength = scanner.nextLine();
            String testCaseString = scanner.nextLine();
            testCases[i] = testCaseString;
        }

        for (String testCase : testCases) {
            Map<Character, Integer> countMap = new HashMap<>();
            StringBuffer result = new StringBuffer();
            for (int i = 0 ; i < testCase.length() ; i ++) {
                char c = testCase.charAt(i);
                int countOfC;
                if (countMap.containsKey(c)) {
                    countOfC = countMap.get(c);
                    countMap.put(c, ++countOfC);
                } else {
                    countOfC = 1;
                    countMap.put(c, countOfC);
                }
                if (countOfC == 1) {
                    result.append(c);
                    continue;
                }
                Integer place = characterMap.get(c);
                place += countOfC - 1;
                char character = numberToCharacterMap.get(place % 26);
                result.append(character);
            }
            System.out.println(result);

        }
        
    }

    static void fillCharacterMap() {
        characterMap.put('a', 1);
        characterMap.put('b', 2);
        characterMap.put('c', 3);
        characterMap.put('d', 4);
        characterMap.put('e', 5);
        characterMap.put('f', 6);
        characterMap.put('g', 7);
        characterMap.put('h', 8);
        characterMap.put('i', 9);
        characterMap.put('j', 10);
        characterMap.put('k', 11);
        characterMap.put('l', 12);
        characterMap.put('m', 13);
        characterMap.put('n', 14);
        characterMap.put('o', 15);
        characterMap.put('p', 16);
        characterMap.put('q', 17);
        characterMap.put('r', 18);
        characterMap.put('s', 19);
        characterMap.put('t', 20);
        characterMap.put('u', 21);
        characterMap.put('v', 22);
        characterMap.put('w', 23);
        characterMap.put('x', 24);
        characterMap.put('y', 25);
        characterMap.put('z', 26);
    }

    static void fillNumberToCharacterMap() {
        numberToCharacterMap.put(1, 'a');
        numberToCharacterMap.put(2, 'b');
        numberToCharacterMap.put(3 , 'c');
        numberToCharacterMap.put(4 , 'd');
        numberToCharacterMap.put(5 , 'e');
        numberToCharacterMap.put(6 , 'f');
        numberToCharacterMap.put(7 , 'g');
        numberToCharacterMap.put(8 , 'h');
        numberToCharacterMap.put(9 , 'i');
        numberToCharacterMap.put(10 , 'j');
        numberToCharacterMap.put(11 , 'k');
        numberToCharacterMap.put(12 , 'l');
        numberToCharacterMap.put(13 , 'm');
        numberToCharacterMap.put(14 , 'n');
        numberToCharacterMap.put(15 , 'o');
        numberToCharacterMap.put(16 , 'p');
        numberToCharacterMap.put(17 , 'q');
        numberToCharacterMap.put(18 , 'r');
        numberToCharacterMap.put(19 , 's');
        numberToCharacterMap.put(20 , 't');
        numberToCharacterMap.put(21 , 'u');
        numberToCharacterMap.put(22 , 'v');
        numberToCharacterMap.put(23 , 'w');
        numberToCharacterMap.put(24 , 'x');
        numberToCharacterMap.put(25 , 'y');
        numberToCharacterMap.put(26 , 'z');
    }
}
