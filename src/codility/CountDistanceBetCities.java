package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Distance between cities.
 */
public class CountDistanceBetCities {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] T = new int[10];
        T[0] = 9;
        T[1] = 1;
        T[2] = 4;
        T[3] = 9;
        T[4] = 0;
        T[5] = 4;
        T[6] = 8;
        T[7] = 9;
        T[8] = 0;
        T[9] = 1;
        int[] counts = solution.solution(T);
        System.out.println(Arrays.toString(counts));
    }
}

class Solution {
    public int[] solution(int[] T){
        int[] counts = new int[T.length - 1];
        Arrays.fill(counts, 0);
        int[] distances = new int[T.length - 1];
        int capital = 0;
        for ( int i = 0 ; i < T.length ; i ++ ) {
            if (T[i] == i) {
                capital = i;
                break;
            }
        }
        int currentDistance = 0;
        this.count(T, capital, distances, capital, currentDistance);
        for (int distance : distances) {
            if (distance == 0) {
                continue;
            }
            counts[distance - 1] = counts[distance - 1] + 1;
        }
        return counts;
    }

    private void count(int[] T, int fromCity, int[] distances, int capital, int currentDistance){
        List<Integer> citiesNext = new ArrayList<>();
        for ( int i = 0 ; i < T.length ; i++ ) {
            if (fromCity == T[i]) {
                citiesNext.add(i);
            }
        }
        currentDistance++;
        for (Integer cityNext : citiesNext) {
            if (cityNext == capital) {
                continue;
            }
            int distanceArrLoc = 0;
            if ( cityNext > capital ) {
                distanceArrLoc = cityNext - 1;
            }
            distances[distanceArrLoc] = distances[distanceArrLoc] + currentDistance;
            count(T, cityNext, distances, capital, currentDistance);
        }
    }
}
