package services.EventsManager.OrderEvents;

import services.EventsManager.Event;
import services.EventsManager.Subscriber;
import services.EventsManager.DeliveryEvents.eventsObject.DeliveryEventDetails;
import services.OrderManager.OrderManager;
import services.OrderManager.OrderStatus;

public class OrderDeliveryEventsSubscriber implements Subscriber{
    
    private OrderManager orderManager;
    public OrderDeliveryEventsSubscriber(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @Override
    public void handleEvent(Event event) {
        DeliveryEventDetails deliveryEventDetails = (DeliveryEventDetails)event;        
        
        switch (deliveryEventDetails.getOrderDeliveryStatus()) {
            case DELIVERY_AGENT_ASSIGNED, DELIVERY_AGENT_ON_THE_WAY_TO_RES:
                orderManager.handleOrderDeliveryEvents(deliveryEventDetails.getOrderId(), OrderStatus.DELIVERY_PARTNER_ON_THE_WAY_TO_RESTAURANT);
                break;
            case DELIVERY_AGENT_ON_THE_WAY_TO_DELIVERY_LOCATION:
                orderManager.handleOrderDeliveryEvents(deliveryEventDetails.getOrderId(), OrderStatus.DELIVERY_PARTNER_ON_THE_WAY_TO_CUSTOMER_LOCATION);
                break;
            case DELIEVRY_AGENT_HAS_DELIVERED_THE_ORDER:
                orderManager.handleOrderDeliveryEvents(deliveryEventDetails.getOrderId(), OrderStatus.DELIVERED);
                break;
            case DELIVERY_AGENT_PICKED_ORDER_FROM_RES:
                orderManager.handleOrderDeliveryEvents(deliveryEventDetails.getOrderId(), OrderStatus.ORDER_PICKED_BY_DELIVERY_PARTNER);
                break;
            case DELIVERY_AGENT_ARRIVED_AT_RES:
                orderManager.handleOrderDeliveryEvents(deliveryEventDetails.getOrderId(), OrderStatus.DELIVERY_PARTNER_AT_RESTAURANT);
                break;
            case DELIVERY_AGENT_ARRIVED_AT_CUSTOMER_LOCATION:
                orderManager.handleOrderDeliveryEvents(deliveryEventDetails.getOrderId(), OrderStatus.DELIVER_PARTNER_AT_CUSTOMER_LOCATION);
                break;
            default:
                break;
        }

        
    }
}