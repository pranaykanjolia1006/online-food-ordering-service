package services.EventsManager.RestaurantEvents;

import java.util.ArrayList;
import java.util.List;

import services.EventsManager.Event;
import services.EventsManager.Publisher;
import services.EventsManager.Subscriber;


public class ResEventsPublisher implements Publisher{

    private final List<Subscriber> subscribers;

    public ResEventsPublisher() {
        subscribers = new ArrayList<>();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        boolean alreadyExists = subscribers.stream().anyMatch(s -> s.equals(subscriber));
        if (!alreadyExists) {
            subscribers.add(subscriber);
        }
        return;
    }
    @Override
    public void removeSubscriber(Subscriber subscriber) {
        Integer index = subscribers.indexOf(subscriber);
        if (index != -1){
            subscribers.remove(subscriber);
        }
        return;
    }
    @Override
    public void notifySubscribers(Event event) {
        subscribers.stream().forEach(sb -> sb.handleEvent(event));
    }
    
}
