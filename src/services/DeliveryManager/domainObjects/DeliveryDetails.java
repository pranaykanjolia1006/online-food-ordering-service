package services.DeliveryManager.domainObjects;

import common.serviceObject.DeliveryPartner;
import common.serviceObject.Location;

public class DeliveryDetails {
    private String orderId;
    private String resId;
    private Location pickupLocation;
    private Location dropLocation;
    private DeliveryPartner deliveryPartner;
    private CustomerInfo customerInfo;
    private OrderDeliveryStatus orderDeliveryStatus;
    
    public OrderDeliveryStatus getOrderDeliveryStatus() {
        return orderDeliveryStatus;
    }
    public void setOrderDeliveryStatus(OrderDeliveryStatus orderDeliveryStatus) {
        this.orderDeliveryStatus = orderDeliveryStatus;
    }
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
    public Location getPickupLocation() {
        return pickupLocation;
    }
    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    public Location getDropLocation() {
        return dropLocation;
    }
    public void setDropLocation(Location dropLocation) {
        this.dropLocation = dropLocation;
    }
    public DeliveryPartner getDeliveryPartner() {
        return deliveryPartner;
    }
    public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }


}
