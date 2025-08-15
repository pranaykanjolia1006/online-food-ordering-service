package services.DeliveryManager.domainObjects;

import common.serviceObject.Location;

public class DeliveryPreparationDetails {
    private String orderId;
    private String resId;
    private String resName;
    private Location droplocation;
    private Location pickupLocation;
    private CustomerInfo customerInfo;

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
    public Location getDroplocation() {
        return droplocation;
    }
    public void setDroplocation(Location droplocation) {
        this.droplocation = droplocation;
    }
    public Location getPickupLocation() {
        return pickupLocation;
    }
    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    public String getOrderId() {
        return orderId;
    }
    public String getResName() {
        return resName;
    }
    public void setResName(String resName) {
        this.resName = resName;
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
