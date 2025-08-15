package services.RestaurantManager.FoodManager.domainObjects;

public class FoodItem {
    private String foodItemId;
    private String foodDescription;
    private Integer quantity;
    private Double unitItemPrice;
    
    public String getFoodItemId() {
        return foodItemId;
    }
    public void setFoodItemId(String foodItemId) {
        this.foodItemId = foodItemId;
    }
    public String getFoodDescription() {
        return foodDescription;
    }
    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getUnitItemPrice() {
        return unitItemPrice;
    }
    public void setUnitItemPrice(Double unitItemPrice) {
        this.unitItemPrice = unitItemPrice;
    }
}
