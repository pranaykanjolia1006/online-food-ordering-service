package services.RestaurantManager.FoodManager;

import java.util.List;

import services.RestaurantManager.FoodManager.domainObjects.FoodItem;

public class Menu {
    private String menuId;
    private List<FoodItem> foodItems;
    
    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }
    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
