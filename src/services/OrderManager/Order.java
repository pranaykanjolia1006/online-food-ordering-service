package services.OrderManager;

import services.CustomerManager.Customer;

public class Order {
    private String orderId;
    private Customer customer;
    private Double totalPrice;
    
    private OrderStatus orderStatus;
    private RestaurantOrder restaurantOrder;
    private DeliveryOrder deliveryOrder;
    
    public RestaurantOrder getRestaurantOrder() {
        return restaurantOrder;
    }
    public void setRestaurantOrder(RestaurantOrder restaurantOrder) {
        this.restaurantOrder = restaurantOrder;
    }
    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }
    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
