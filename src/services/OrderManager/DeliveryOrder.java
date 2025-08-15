package services.OrderManager;

import services.DeliveryManager.domainObjects.DeliveryDetails;

public class DeliveryOrder {

    private String deliveryOrderId;
    private DeliveryDetails DeliveryDetails;
    
    public DeliveryDetails getDeliveryDetails() {
        return DeliveryDetails;
    }
    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        DeliveryDetails = deliveryDetails;
    }
    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }
    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }
}
