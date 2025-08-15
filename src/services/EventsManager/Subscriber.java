package services.EventsManager;

public interface Subscriber {
    void handleEvent(Event event);
}
