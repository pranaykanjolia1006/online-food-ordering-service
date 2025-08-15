package services.RestaurantManager;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import services.EventsManager.RestaurantEvents.ResEventsPublisher;
import services.EventsManager.RestaurantEvents.eventObject.RestaurantOrderEventDetails;
import services.RestaurantManager.FoodManager.domainObjects.FoodItem;
import services.RestaurantManager.FoodManager.domainObjects.FoodPreparationDetails;
import services.RestaurantManager.domainObjects.ResOrderStatus;
import services.RestaurantManager.domainObjects.RestaurantOrderDetails;

public class ResManager {
    
    private final Map<String, RestaurantOrderDetails> orderIdToResOrder;
    private final Map<String, Restaurant> resIdToRestaurants;
    private ResEventsPublisher resEventsPublisher;

    public ResManager(ResEventsPublisher resEventsPublisher) {
        orderIdToResOrder = new ConcurrentHashMap<>();
        resIdToRestaurants = new ConcurrentHashMap<>();
        this.resEventsPublisher = resEventsPublisher;
    }
    
    public void prepareFood(FoodPreparationDetails foodPreparationDetails) {
        System.out.println("[prepareFood] Preparing Food");
        RestaurantOrderDetails restaurantOrderDetails = new RestaurantOrderDetails();
        restaurantOrderDetails.setFoodItems(this.getResFoodItems(foodPreparationDetails.getResId(), foodPreparationDetails.getFoodItems()));
        restaurantOrderDetails.setOrderId(foodPreparationDetails.getOrderId());
        restaurantOrderDetails.setRestaurant(resIdToRestaurants.get(foodPreparationDetails.getResId()));
        restaurantOrderDetails.setFoodItemsQuantity(foodPreparationDetails.getQuantity());
        restaurantOrderDetails.setResOrderStatus(ResOrderStatus.ORDER_ACCEPTED);
        orderIdToResOrder.put(foodPreparationDetails.getOrderId(), restaurantOrderDetails);
        System.out.println("Order accepted, food is being prepared");

        publishEvent(restaurantOrderDetails);
    }

    public List<FoodItem> getResFoodItems(String resId, List<String> foodItems) {
        
        Restaurant restaurant = resIdToRestaurants.get(resId);

        return restaurant.getMenus().stream()
        .flatMap(menu -> menu.getFoodItems().stream())         // Flatten all food items across menus
        .filter(foodItem -> foodItems.contains(foodItem.getFoodItemId())) // Filter those matching the input list
        .collect(Collectors.toList());  
    }

    public void addRestaurant(Restaurant restaurant) {
        boolean alreadyExists = resIdToRestaurants.values().stream().anyMatch(r -> r.getResId().equals(restaurant.getResId()));
        if (alreadyExists) {
            return;
        }
        resIdToRestaurants.put(restaurant.getResId(), restaurant);
    }

    private void publishEvent(RestaurantOrderDetails restaurantOrderDetails) {
        System.out.println("[publishEvent] Pushing events from Res Manager");
        RestaurantOrderEventDetails restaurantOrderEventDetails = new RestaurantOrderEventDetails();
        restaurantOrderEventDetails.setEventId(String.valueOf(Math.abs(new Random().nextLong())));
        restaurantOrderEventDetails.setOrderId(restaurantOrderDetails.getOrderId());
        restaurantOrderEventDetails.setResOrderStatus(restaurantOrderDetails.getResOrderStatus());
        resEventsPublisher.notifySubscribers(restaurantOrderEventDetails);
    }

    public void updateResOrderStatus(String orderId, ResOrderStatus resOrderStatus) {
        System.out.println("[updateResOrderStatus] Changing res order status to:" + resOrderStatus.toString());
        RestaurantOrderDetails restaurantOrderDetails = orderIdToResOrder.get(orderId);
        restaurantOrderDetails.setResOrderStatus(resOrderStatus);
        publishEvent(restaurantOrderDetails);
    }

    public Restaurant getRestaurant(String resId) {
        return resIdToRestaurants.values().stream().filter(r -> r.getResId().equals(resId)).findFirst().get();
    }
}
