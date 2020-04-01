package vanhack.cuisines;

/**
 * Created by danushka on 9/29/19.
 */
public class Japanese extends Cuisine {
    private String dish;

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    @Override
    public Cuisine serveFood(String dish) {
        this.setDish(dish);
        return this;
    }
}
