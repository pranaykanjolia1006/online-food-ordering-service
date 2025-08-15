package services.OrderManager;

import java.util.List;

import services.RestaurantManager.Restaurant;
import services.RestaurantManager.FoodManager.domainObjects.FoodItem;

public class RestaurantOrder {
    private String resOrderId;
    private Restaurant restaurant;
    private List<FoodItem> foodItems;
    private List<Integer> foodItemsQuantity;
    public String getResOrderId() {
        return resOrderId;
    }
    public void setResOrderId(String resOrderId) {
        this.resOrderId = resOrderId;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public List<FoodItem> getFoodItems() {
        return foodItems;
    }
    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
    public List<Integer> getFoodItemsQuantity() {
        return foodItemsQuantity;
    }
    public void setFoodItemsQuantity(List<Integer> foodItemsQuantity) {
        this.foodItemsQuantity = foodItemsQuantity;
    }
}
