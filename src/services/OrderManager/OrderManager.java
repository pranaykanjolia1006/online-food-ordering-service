package services.OrderManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import common.serviceObject.Location;
import services.CustomerManager.Customer;
import services.CustomerManager.CustomerManager;
import services.DeliveryManager.DeliveryManager;
import services.DeliveryManager.domainObjects.CustomerInfo;
import services.DeliveryManager.domainObjects.DeliveryDetails;
import services.DeliveryManager.domainObjects.DeliveryPreparationDetails;
import services.DeliveryManager.domainObjects.OrderDeliveryStatus;
import services.RestaurantManager.ResManager;
import services.RestaurantManager.FoodManager.domainObjects.FoodItem;
import services.RestaurantManager.FoodManager.domainObjects.FoodPreparationDetails;

public class OrderManager {
    
    private final Map<String, Order> orderIdToOrder;
    private final Map<String, List<Order>> customerIdToOrder;

    private CustomerManager customerManager;
    private ResManager restaurantManager;
    private DeliveryManager deliveryManager;


    public OrderManager(CustomerManager cm, ResManager rm, DeliveryManager dm) {
        this.customerManager = cm;
        this.restaurantManager = rm;
        this.deliveryManager = dm;
        
        orderIdToOrder = new ConcurrentHashMap<>();
        customerIdToOrder = new ConcurrentHashMap<>();
    }

    public Order createOrder(String customerId, String resId, List<String> foodItemsId, List<Integer> foodItemsQuantity, Location dropLocation) {
        System.out.println("[createOrder] Creating order");
        Order order = new Order();

        order.setOrderId(String.valueOf(Math.abs((new Random()).nextLong())));
        order.setCustomer(customerManager.getCustomer(customerId));
        order.setOrderStatus(OrderStatus.INITIATED);
        
        List<FoodItem> foodItems = restaurantManager.getResFoodItems(resId, foodItemsId);
        order.setTotalPrice(this.getTotalPrice(foodItems, foodItemsQuantity));

        RestaurantOrder restaurantOrder = new RestaurantOrder();
        restaurantOrder.setFoodItems(foodItems);
        restaurantOrder.setResOrderId(resId);
        restaurantOrder.setRestaurant(restaurantManager.getRestaurant(resId));
        restaurantOrder.setResOrderId(String.valueOf(Math.abs(new Random().nextLong())));
        order.setRestaurantOrder(restaurantOrder);

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setDeliveryOrderId(String.valueOf(Math.abs(new Random().nextLong())));
        DeliveryDetails deliveryDetails = new DeliveryDetails();
        deliveryDetails.setOrderId(order.getOrderId());
        deliveryDetails.setResId(resId);
        deliveryDetails.setPickupLocation(restaurantOrder.getRestaurant().getLocation());
        deliveryDetails.setDropLocation(dropLocation);
        deliveryDetails.setOrderDeliveryStatus(OrderDeliveryStatus.ASSIGN_DELIVERY_AGENT_IN_PROGRESS);
        CustomerInfo customerInfo = new CustomerInfo();
        Customer customer = customerManager.getCustomer(customerId);
        customerInfo.setCustomerName(customer.getName());
        customerInfo.setPhoneNumber(customer.getPhoneNumnber());
        deliveryDetails.setCustomerInfo(customerInfo);
        deliveryOrder.setDeliveryDetails(deliveryDetails);
        order.setDeliveryOrder(deliveryOrder);

        orderIdToOrder.put(order.getOrderId(), order);
        if (customerIdToOrder.get(order.getCustomer().getUserId()) == null) {
            customerIdToOrder.put(order.getCustomer().getUserId(), new ArrayList<>());
        }
        customerIdToOrder.get(order.getCustomer().getUserId()).add(order);
        
        this.performOrderCreationActions(order);
        return order;
    }

    private Double getTotalPrice(List<FoodItem> foodItems, List<Integer> foodItemsQuantity) {
        Double total = 0.0;
        for (int i=0; i < foodItems.size(); i++) {
            total += foodItems.get(i).getUnitItemPrice() * foodItemsQuantity.get(i);
        }
        
        return total;
    }   


    private void performOrderCreationActions(Order order) {
        System.out.println("[performOrderCreationActions] Performing actions for order creation");
        FoodPreparationDetails foodPreparationDetails = new FoodPreparationDetails();
        foodPreparationDetails.setFoodItems(order.getRestaurantOrder().getFoodItems().stream().map(FoodItem::getFoodItemId).collect(Collectors.toList()));
        foodPreparationDetails.setOrderId(order.getOrderId());
        foodPreparationDetails.setQuantity(order.getRestaurantOrder().getFoodItemsQuantity());
        foodPreparationDetails.setResId(order.getRestaurantOrder().getRestaurant().getResId());

        DeliveryPreparationDetails deliveryPreparationDetails = new DeliveryPreparationDetails();
        deliveryPreparationDetails.setOrderId(order.getOrderId());
        deliveryPreparationDetails.setResId(order.getRestaurantOrder().getRestaurant().getResId());
        deliveryPreparationDetails.setResName(order.getRestaurantOrder().getRestaurant().getResName());
        deliveryPreparationDetails.setPickupLocation(order.getDeliveryOrder().getDeliveryDetails().getPickupLocation());
        deliveryPreparationDetails.setDroplocation(order.getDeliveryOrder().getDeliveryDetails().getDropLocation());

        restaurantManager.prepareFood(foodPreparationDetails);
        deliveryManager.prepareDelivery(deliveryPreparationDetails);   
        System.out.println("[performOrderCreationActions] order creation action successful");
    }

    public Order getOrderdetailsByOrderId(String orderId) {
       return orderIdToOrder.get(orderId);
    }

    public void printOrderStatus(String orderId) {
        System.out.println("Order Status for orderId:" + orderId + " is " + orderIdToOrder.get(orderId).getOrderStatus().toString());
    }

    public void handleRestaurantEvents(String orderId, OrderStatus orderStatus) {        
        switch (orderStatus) {
            case ACCEPTED, PREPARING_ORDER, PREPARED:
                this.getOrderdetailsByOrderId(orderId).setOrderStatus(orderStatus);
                this.printOrderStatus(orderId);
                break;
            default:
                return;
        }
        return;
    }

    public void handleOrderDeliveryEvents(String orderId, OrderStatus orderStatus) {
        switch (orderStatus) {
            case DELIVERED, 
            ORDER_PICKED_BY_DELIVERY_PARTNER, 
            DELIVERY_PARTNER_ON_THE_WAY_TO_CUSTOMER_LOCATION,
            DELIVERY_PARTNER_ON_THE_WAY_TO_RESTAURANT,
            DELIVER_PARTNER_AT_CUSTOMER_LOCATION,
            DELIVERY_PARTNER_AT_RESTAURANT:
                this.getOrderdetailsByOrderId(orderId).setOrderStatus(orderStatus);
                this.printOrderStatus(orderId);
                break;
            default:
                return;
        }
        return;
    }
}
