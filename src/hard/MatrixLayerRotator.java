package hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by aux-146 on 12/4/18.
 */
public class MatrixLayerRotator {

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r, int m, int n) {

        int noOfLayers = Math.min(m, n) / 2;
        for(int currentLayerNumber = 0 ; currentLayerNumber < noOfLayers ; ++currentLayerNumber) {
            for(int x = 0; x < r % (2 * ((m - 1) + (n - 1) - 4 * currentLayerNumber)); x++) {
                int i = currentLayerNumber, j = currentLayerNumber;
                int temp = matrix.get(currentLayerNumber).get(currentLayerNumber);
                //Upper triangle
                while(i < m - 1 - currentLayerNumber) {
                    int temp2 = matrix.get(i + 1).get(j);
                    matrix.get(i + 1).set(j, temp);
                    temp = temp2;
                    i++;
                }
                //Left side triangle
                while(j < n - 1 - currentLayerNumber) {
                    int temp2 = matrix.get(i).get(j + 1);
                    matrix.get(i).set(j + 1, temp);
                    temp = temp2;
                    j++;
                }
                //Below triangle
                while(i > currentLayerNumber) {
                    int temp2 = matrix.get(i - 1).get(j);
                    matrix.get(i - 1).set(j, temp);
                    temp = temp2;
                    i--;
                }
                //Right side triangle
                while(j > currentLayerNumber) {
                    int temp2 = matrix.get(i).get(j - 1);
                    matrix.get(i).set(j - 1, temp);
                    temp = temp2;
                    j--;
                }
            }
        }

        for(int i = 0; i < matrix.size(); i++ ) {
            for(int j = 0; j < matrix.get(i).size(); j++ ) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r, m, n);

        bufferedReader.close();
    }

}
