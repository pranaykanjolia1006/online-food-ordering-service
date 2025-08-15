package services.RestaurantManager.FoodManager.domainObjects;

import java.util.List;

public class FoodPreparationDetails {
    private String orderId;
    private String resId;
    private List<String> foodItems;
    public List<String> getFoodItems() {
        return foodItems;
    }
    public void setFoodItems(List<String> foodItems) {
        this.foodItems = foodItems;
    }
    public List<Integer> getQuantity() {
        return quantity;
    }
    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
    private List<Integer> quantity;


    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getResId() {
        return resId;
    }
    public void setResId(String resId) {
        this.resId = resId;
    }
}
