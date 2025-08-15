package services.EventsManager.DeliveryEvents.eventsObject;

import services.DeliveryManager.domainObjects.OrderDeliveryStatus;
import services.EventsManager.Event;

public class DeliveryEventDetails extends Event {

    private String eventId;
    private String orderId;
    private OrderDeliveryStatus orderDeliveryStatus;

    public String getOrderId() {
        return orderId;
    }
    
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderDeliveryStatus getOrderDeliveryStatus() {
        return orderDeliveryStatus;
    }

    public void setOrderDeliveryStatus(OrderDeliveryStatus orderDeliveryStatus) {
        this.orderDeliveryStatus = orderDeliveryStatus;
    }

    public DeliveryEventDetails() {
        
    }
    
}
