package services.EventsManager.DeliveryEvents;

import java.util.ArrayList;
import java.util.List;

import services.EventsManager.Event;
import services.EventsManager.Publisher;
import services.EventsManager.Subscriber;

public class DeliveryEventsPublisher implements Publisher {

    private final List<Subscriber> deliveryEventsSubscriber;

    public DeliveryEventsPublisher() {
        deliveryEventsSubscriber = new ArrayList<>();
    }


    @Override
    public void addSubscriber(Subscriber subscriber) {
        Boolean alreadyExists = deliveryEventsSubscriber.stream().anyMatch(s -> s.equals(subscriber));
        if (!alreadyExists) {
            deliveryEventsSubscriber.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        int index = deliveryEventsSubscriber.indexOf(subscriber);
        if (index != -1) {
            deliveryEventsSubscriber.remove(subscriber);
        }

    }

    @Override
    public void notifySubscribers(Event event) {
        deliveryEventsSubscriber.stream().forEach(s -> s.handleEvent(event));
    }

}
