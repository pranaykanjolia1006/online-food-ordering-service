package services.EventsManager.RestaurantEvents.eventObject;

import services.EventsManager.Event;
import services.RestaurantManager.domainObjects.ResOrderStatus;

public class RestaurantOrderEventDetails extends Event {
    private String eventId;
    private String orderId;
    private ResOrderStatus resOrderStatus;

    public ResOrderStatus getResOrderStatus() {
        return resOrderStatus;
    }
    public void setResOrderStatus(ResOrderStatus resOrderStatus) {
        this.resOrderStatus = resOrderStatus;
    }
    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
