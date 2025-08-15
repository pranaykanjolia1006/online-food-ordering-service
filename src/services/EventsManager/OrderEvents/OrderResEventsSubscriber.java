package services.EventsManager.OrderEvents;

import services.EventsManager.Event;
import services.EventsManager.Subscriber;
import services.EventsManager.RestaurantEvents.eventObject.RestaurantOrderEventDetails;
import services.OrderManager.OrderManager;
import services.OrderManager.OrderStatus;

public class OrderResEventsSubscriber implements Subscriber{

    private OrderManager orderManager;

    public OrderResEventsSubscriber(OrderManager orderManager) {
        this.orderManager = orderManager;
    }
    
    @Override
    public void handleEvent(Event event) {
        if (!(event instanceof RestaurantOrderEventDetails)) {
            return;
        }

        RestaurantOrderEventDetails restaurantOrderEventDetails = (RestaurantOrderEventDetails) event;  
        switch (restaurantOrderEventDetails.getResOrderStatus()) {
            case ORDER_ACCEPTED:
                orderManager.handleRestaurantEvents(restaurantOrderEventDetails.getOrderId(), OrderStatus.ACCEPTED);
                break;
            case ORDER_PREPARATION_IN_PROGRESS:
                orderManager.handleRestaurantEvents(restaurantOrderEventDetails.getOrderId(), OrderStatus.PREPARING_ORDER);
                break;
            case ORDER_PREPARED:
                orderManager.handleRestaurantEvents(restaurantOrderEventDetails.getOrderId(), OrderStatus.PREPARED);
                break;
            case ORDER_READY_FOR_DELIVERY:
                orderManager.handleRestaurantEvents(restaurantOrderEventDetails.getOrderId(), OrderStatus.READY_FOR_PICKUP);
                break;
            case ORDER_PICKEY_BY_DELIVERY_PARTNER:
                orderManager.handleRestaurantEvents(restaurantOrderEventDetails.getOrderId(), OrderStatus.ORDER_PICKED_BY_DELIVERY_PARTNER);
                break;
            default:
                break;
        }
    }

   
    
    
}
