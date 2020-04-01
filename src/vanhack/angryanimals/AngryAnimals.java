package vanhack.angryanimals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by danushka on 9/29/19.
 */
public class AngryAnimals {
    public static long angryAnimals(int n, List<Integer> a, List<Integer> b) {
        java.util.Map<Integer, List<Integer>> angryAnimals = new java.util.HashMap<>();
        long count = 0;
        for (int i = 0 ; i < a.size() ; i ++) {
            List<Integer> enemies = angryAnimals.get(a.get(i));
            if (enemies == null) {
                enemies = new ArrayList<>();
            }
            enemies.add(b.get(i));
            angryAnimals.put(a.get(i), enemies);
        }
        for (int i = 1 ; i <= n ; i++) {
            int noOfCombinations = 0;
            for (int j = i ; j <= n ; j++) {
                if (i == j) {
                    noOfCombinations ++;
                } else {
                    if (isEnemy(angryAnimals, i, j)) {
                        break;
                    }
                    noOfCombinations ++;
                }
            } 
            count += noOfCombinations;
        }
        return count;
    }

    public static boolean isEnemy(Map<Integer, List<Integer>> angryAnimals, int startAnimal, int endAnimal) {
        boolean isEnemy = false;
        for (int i = startAnimal ; i <= endAnimal ; i ++) {
            isEnemy = isEnemy(angryAnimals.get(i), endAnimal);
            if (isEnemy) {
                break;
            }
        }
        return isEnemy;
    }

    public static boolean isEnemy (List<Integer> enemies, int animal) {
        if (enemies == null) {
            return false;
        }
        for (Integer enemy : enemies) {
            if (animal >= enemy) {
                return true;
            }
        }
        return false;
    }
}
