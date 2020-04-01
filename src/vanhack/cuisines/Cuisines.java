package vanhack.cuisines;

import java.util.Scanner;

/**
 * Created by danushka on 9/29/19.
 */
public class Cuisines {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final FoodFactory FOOD_FACTORY = FoodFactory.getFactory();

    static  {
        FoodFactory.getFactory().registerCuisine("Chinese", new Chinese());
        FoodFactory.getFactory().registerCuisine("Italian", new Italian());
        FoodFactory.getFactory().registerCuisine("Japanese", new Japanese());
        FoodFactory.getFactory().registerCuisine("Mexican", new Mexican());
    }

    public static void main(String[] args) {
        int totalNumberOfOrders = Integer.parseInt(INPUT_READER.nextLine());
        while (totalNumberOfOrders-- > 0) {
            String[] food = INPUT_READER.nextLine().split(" ");
            String cuisine = food[0];
            String dish = food[1];

            try {
                if (FOOD_FACTORY.equals(FoodFactory.getFactory())) {
                    Cuisine servedFood = FOOD_FACTORY.serveCuisine(cuisine, dish);

                    switch (cuisine) {
                        case "Chinese":
                            Chinese chineseDish = (Chinese) servedFood;
                            System.out.println("Serving " + chineseDish.getDish() + "(Chinese)");
                            break;
                        case "Italian":
                            Italian italianDish = (Italian) servedFood;
                            System.out.println("Serving " + italianDish.getDish() + "(Italian)");
                            break;
                        case "Japanese":
                            Japanese japaneseDish = (Japanese) servedFood;
                            System.out.println("Serving " + japaneseDish.getDish() + "(Japanese)");
                            break;
                        case "Mexican":
                            Mexican mexicanDish = (Mexican) servedFood;
                            System.out.println("Serving " + mexicanDish.getDish() + "(Mexican)");
                            break;
                        default:
                            break;
                    }
                }
            } catch (UnservableCuisineRequestException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
