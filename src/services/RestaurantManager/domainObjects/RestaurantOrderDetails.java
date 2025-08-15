package services.RestaurantManager.domainObjects;

import java.util.List;

import services.RestaurantManager.Restaurant;
import services.RestaurantManager.FoodManager.domainObjects.FoodItem;

public class RestaurantOrderDetails {
    private String orderId;
    private Restaurant restaurant;
    private List<FoodItem> foodItems;
    private List<Integer> foodItemsQuantity;
    private ResOrderStatus resOrderStatus;
    

    public ResOrderStatus getResOrderStatus() {
        return resOrderStatus;
    }
    public void setResOrderStatus(ResOrderStatus resOrderStatus) {
        this.resOrderStatus = resOrderStatus;
    }
    public void setFoodItemsQuantity(List<Integer> foodItemsQuantity) {
        this.foodItemsQuantity = foodItemsQuantity;
    }
    public List<Integer> getFoodItemsQuantity() {
        return foodItemsQuantity;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

}