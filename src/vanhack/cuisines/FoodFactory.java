package vanhack.cuisines;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danushka on 9/29/19.
 */
public class FoodFactory {
    private static FoodFactory foodFactory = new FoodFactory();
    private Map<String, Cuisine> cuisines = new HashMap<>();

    private FoodFactory() {}

    public static FoodFactory getFactory() {
        return foodFactory;
    }

    public void registerCuisine(String name, Cuisine cuisine) {
        cuisines.put(name, cuisine);
    }

    public Cuisine serveCuisine(String cuisine, String dish) throws UnservableCuisineRequestException {
        Cuisine cuisineObj = cuisines.get(cuisine);
        if (cuisineObj == null) {
            throw new UnservableCuisineRequestException("Unservable cuisine " + cuisine + " for dish " + dish);
        }
        return cuisineObj.serveFood(dish);
    }
}
