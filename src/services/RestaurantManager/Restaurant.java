package services.RestaurantManager;

import java.util.List;

import common.serviceObject.Location;
import services.RestaurantManager.FoodManager.Menu;

public class Restaurant {
    private String resId;
    private String resName;
    private List<Menu> menus;
    private Location location;

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public String getResId() {
        return resId;
    }
    public void setResId(String resId) {
        this.resId = resId;
    }
    public String getResName() {
        return resName;
    }
    public void setResName(String resName) {
        this.resName = resName;
    }
    public List<Menu> getMenus() {
        return menus;
    }
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
