import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.serviceObject.Location;
import services.CustomerManager.Customer;
import services.CustomerManager.CustomerManager;
import services.DeliveryManager.DeliveryManager;
import services.DeliveryManager.AssignDeliveryPartnerStrategy.IAssignDeliveryPartnerStrategy;
import services.DeliveryManager.AssignDeliveryPartnerStrategy.RandomDeliveryPartnerAssignStrategy;
import services.DeliveryManager.domainObjects.OrderDeliveryStatus;
import services.EventsManager.DeliveryEvents.DeliveryEventsPublisher;
import services.EventsManager.OrderEvents.OrderDeliveryEventsSubscriber;
import services.EventsManager.OrderEvents.OrderResEventsSubscriber;
import services.EventsManager.RestaurantEvents.ResEventsPublisher;
import services.OrderManager.Order;
import services.OrderManager.OrderManager;
import services.RestaurantManager.ResManager;
import services.RestaurantManager.Restaurant;
import services.RestaurantManager.FoodManager.Menu;
import services.RestaurantManager.FoodManager.domainObjects.FoodItem;
import services.RestaurantManager.domainObjects.ResOrderStatus;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Created customer Manager, Delivery Manager, ResManager, orderManager
        CustomerManager customerManager = new CustomerManager();
        DeliveryEventsPublisher deliveryEventsPublisher = new DeliveryEventsPublisher();
        IAssignDeliveryPartnerStrategy assignDeliveryStrattegy = new RandomDeliveryPartnerAssignStrategy();
        DeliveryManager deliveryManager = new DeliveryManager(assignDeliveryStrattegy, deliveryEventsPublisher);
        ResEventsPublisher resEventsPublisher = new ResEventsPublisher();
        ResManager resManager = new ResManager(resEventsPublisher);
        OrderManager orderManager = new OrderManager(customerManager, resManager, deliveryManager);
        // Creation complete 

        // Adding publisher and subscriber
        OrderDeliveryEventsSubscriber orderDeliveryEventsSubscriber = new OrderDeliveryEventsSubscriber(orderManager);
        deliveryEventsPublisher.addSubscriber(orderDeliveryEventsSubscriber);
        OrderResEventsSubscriber orderResEventsSubscriber = new OrderResEventsSubscriber(orderManager);
        resEventsPublisher.addSubscriber(orderResEventsSubscriber);
        
        //  Created a restaurant name r1 //
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setResId(String.valueOf(new Random().nextInt()));
        restaurant1.setResName("Restaurant-1");
        Location location1 = new Location();
        location1.setLatitude(String.valueOf(new Random().nextFloat()));
        location1.setLongitude(String.valueOf(new Random().nextFloat()));
        restaurant1.setLocation(location1);


        List<Menu> menus1 = new ArrayList<>();
        Menu menu1 = new Menu();
        menu1.setMenuId(String.valueOf(Math.abs(new Random().nextLong())));
        List<FoodItem> foodItems1 = new ArrayList<>();
        FoodItem f1 = new FoodItem();
        f1.setFoodDescription("Daal makhani");
        f1.setFoodItemId(String.valueOf(Math.abs(new Random().nextLong())));
        f1.setQuantity(1);
        f1.setUnitItemPrice(100.00);

        foodItems1.add(f1);
        FoodItem f2 = new FoodItem();
        f2.setFoodDescription("Paneer butter masala");
        f2.setFoodItemId(String.valueOf(Math.abs(new Random().nextLong())));
        f2.setQuantity(1);
        f2.setUnitItemPrice(200.00);
        foodItems1.add(f2);
        menu1.setFoodItems(foodItems1);
        menus1.add(menu1);

        Menu menu2 = new Menu();
        menu2.setMenuId(String.valueOf(Math.abs(new Random().nextLong())));
        List<FoodItem> foodItems2 = new ArrayList<>();
        FoodItem f3 = new FoodItem();
        f3.setFoodDescription("Butter tawa roti");
        f3.setFoodItemId(String.valueOf(Math.abs(new Random().nextLong())));
        f3.setQuantity(1);
        f3.setUnitItemPrice(25.00);

        foodItems2.add(f3);
        FoodItem f4 = new FoodItem();
        f4.setFoodDescription("Butter Naan");
        f4.setFoodItemId(String.valueOf(Math.abs(new Random().nextLong())));
        f4.setQuantity(1);
        f4.setUnitItemPrice(45.00);
        foodItems1.add(f4);
        menu2.setFoodItems(foodItems2);
        menus1.add(menu2);
        
        restaurant1.setMenus(menus1);

        resManager.addRestaurant(restaurant1);
        // Restaurant creation complete


        // Started customer1 customer2 customer3 creation //
        customerManager.addCustomer("Pranay Kanjolia", "9462564285");
        customerManager.addCustomer("Gunjita Kanjolia", "9413691998");
        customerManager.addCustomer("Krishna Kanjolia", "9462966869");
        // Customer creation complete

        // Started delivery partner creation //
        deliveryManager.addDeliveryPartner("DeliveryPartner-1","123456789");
        deliveryManager.addDeliveryPartner("DeliveryPartner-2", "12121212");
        deliveryManager.addDeliveryPartner("DeliveryPartner-3", "1231231231");
        // Delivery partner creation complete

        Customer customer1 = customerManager.getCustomerByPhoneNumber("9462564285");
        List<String> selectedFoodItems = new ArrayList<>();
        selectedFoodItems.add(f2.getFoodItemId());
        selectedFoodItems.add(f3.getFoodItemId());

        List<Integer> foodItemsQuantity = new ArrayList<>();
        foodItemsQuantity.add(1);
        foodItemsQuantity.add(4);

        Location dropLocation = new Location();
        dropLocation.setLatitude(String.valueOf(Math.abs(new Random().nextLong())));
        dropLocation.setLongitude(String.valueOf(Math.abs(new Random().nextLong())));
        Order order1 = orderManager.createOrder(customer1.getUserId(), restaurant1.getResId(), selectedFoodItems, foodItemsQuantity,dropLocation);


        // delivery partner reached the restaurant
        Thread.sleep(3000);
        deliveryManager.updateDeliveryAgentStatus(order1.getOrderId(), OrderDeliveryStatus.DELIVERY_AGENT_ARRIVED_AT_RES);

        // Restaurant is preparing the order
        Thread.sleep(3000);
        resManager.updateResOrderStatus(order1.getOrderId(), ResOrderStatus.ORDER_PREPARATION_IN_PROGRESS);

        // Restaurant has prepared the order
        Thread.sleep(3000);
        resManager.updateResOrderStatus(order1.getOrderId(), ResOrderStatus.ORDER_PREPARED);
        resManager.updateResOrderStatus(order1.getOrderId(), ResOrderStatus.ORDER_READY_FOR_DELIVERY);

        // Delivery partner has picked the order
        Thread.sleep(3000);
        deliveryManager.updateDeliveryAgentStatus(order1.getOrderId(), OrderDeliveryStatus.DELIVERY_AGENT_PICKED_ORDER_FROM_RES);
        resManager.updateResOrderStatus(order1.getOrderId(), ResOrderStatus.ORDER_PICKEY_BY_DELIVERY_PARTNER);
        deliveryManager.updateDeliveryAgentStatus(order1.getOrderId(), OrderDeliveryStatus.DELIVERY_AGENT_ON_THE_WAY_TO_DELIVERY_LOCATION);

        // Delivery partner at the customer location
        deliveryManager.updateDeliveryAgentStatus(order1.getOrderId(), OrderDeliveryStatus.DELIVERY_AGENT_ARRIVED_AT_CUSTOMER_LOCATION);

        // Delivery partner has delivered the order
        deliveryManager.updateDeliveryAgentStatus(order1.getOrderId(), OrderDeliveryStatus.DELIEVRY_AGENT_HAS_DELIVERED_THE_ORDER);



    }
}
