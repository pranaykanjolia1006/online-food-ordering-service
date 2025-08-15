package services.DeliveryManager;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import common.serviceObject.DeliveryPartner;
import services.DeliveryManager.AssignDeliveryPartnerStrategy.IAssignDeliveryPartnerStrategy;
import services.DeliveryManager.domainObjects.DeliveryDetails;
import services.DeliveryManager.domainObjects.DeliveryPreparationDetails;
import services.DeliveryManager.domainObjects.OrderDeliveryStatus;
import services.EventsManager.DeliveryEvents.DeliveryEventsPublisher;
import services.EventsManager.DeliveryEvents.eventsObject.DeliveryEventDetails;

public class DeliveryManager {
    
    private final Map<String, DeliveryDetails> orderIdToDeliveryDetails;
    private final Map<String, DeliveryPartner> userIdToDeliveryPartners;
    private final Set<String> availableDeliveryAgents;
    private DeliveryEventsPublisher deliveryEventsPublisher;
    private IAssignDeliveryPartnerStrategy assignDeliveryPartnerStrategy;
    
    public DeliveryManager(IAssignDeliveryPartnerStrategy assignDeliveryAgentStrategy, DeliveryEventsPublisher deliveryEventsPublisher) {
        orderIdToDeliveryDetails = new ConcurrentHashMap<>();
        userIdToDeliveryPartners = new ConcurrentHashMap<>();
        availableDeliveryAgents = new HashSet<>();
        this.assignDeliveryPartnerStrategy = assignDeliveryAgentStrategy;
        this.deliveryEventsPublisher = deliveryEventsPublisher;
    }
    
    public void prepareDelivery(DeliveryPreparationDetails deliveryPreparationDetails) {
        System.out.println("[prepareDelivery] Preparing for order delivery");
        DeliveryPartner deliveryPartner = assignDeliveryPartnerStrategy.assignDeliveryPartner(deliveryPreparationDetails, this.getAvailableDeliveryPartners());
        DeliveryDetails deliveryDetails = new DeliveryDetails();
        deliveryDetails.setOrderId(deliveryPreparationDetails.getOrderId());
        deliveryDetails.setResId(deliveryPreparationDetails.getResId());
        deliveryDetails.setDeliveryPartner(deliveryPartner);
        deliveryDetails.setCustomerInfo(deliveryPreparationDetails.getCustomerInfo());
        deliveryDetails.setDropLocation(deliveryPreparationDetails.getDroplocation());
        deliveryDetails.setPickupLocation(deliveryPreparationDetails.getPickupLocation());
        deliveryDetails.setOrderDeliveryStatus(OrderDeliveryStatus.DELIVERY_AGENT_ASSIGNED);
        orderIdToDeliveryDetails.put(deliveryDetails.getOrderId(), deliveryDetails);
        System.out.println("[prepareDelivery] Delivery agent assigned for the order");

        publishEvent(deliveryDetails);
        availableDeliveryAgents.remove(deliveryPartner.getUserId());
        System.out.println("[prepareDelivery] Delivery preparation successful");
    }

    private void publishEvent(DeliveryDetails deliveryDetails) {
        System.out.println("[publishEvent] Publishing delivery events"); 
        DeliveryEventDetails deliveryEventDetails = new DeliveryEventDetails();
        deliveryEventDetails.setEventId(String.valueOf(Math.abs(new Random().nextLong())));
        deliveryEventDetails.setOrderDeliveryStatus(deliveryDetails.getOrderDeliveryStatus());
        deliveryEventDetails.setOrderId(deliveryDetails.getOrderId());
        deliveryEventsPublisher.notifySubscribers(deliveryEventDetails);
        System.out.println("[publishEvent] events for delivery published");
    }

    public void updateDeliveryAgentStatus(String orderId, OrderDeliveryStatus orderDeliveryStatus) {
        System.out.println("[updateDeliveryAgentStatus] Changing Order delivery status to:" + orderDeliveryStatus.toString());
        DeliveryDetails deliveryDetails = orderIdToDeliveryDetails.get(orderId);
        deliveryDetails.setOrderDeliveryStatus(orderDeliveryStatus);
        
        if (orderDeliveryStatus == OrderDeliveryStatus.DELIEVRY_AGENT_HAS_DELIVERED_THE_ORDER) {
            availableDeliveryAgents.remove(deliveryDetails.getDeliveryPartner().getUserId());
        }
        publishEvent(deliveryDetails);
    }

    public void addDeliveryPartner(String name, String phoneNumber) {
        boolean alreadyExists = userIdToDeliveryPartners.values().stream().map(d -> d.getPhoneNumnber()).anyMatch(pn -> pn.equals(phoneNumber));
        if (alreadyExists) {
            return;
        }
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName(name);
        deliveryPartner.setPhoneNumnber(phoneNumber);
        deliveryPartner.setUserId(String.valueOf(Math.abs(new Random().nextLong())));
        userIdToDeliveryPartners.putIfAbsent(deliveryPartner.getUserId(), deliveryPartner);
        availableDeliveryAgents.add(deliveryPartner.getUserId());
    }

    public void removeDeliveryPartner(DeliveryPartner deliveryPartner) {
        if (!availableDeliveryAgents.contains(deliveryPartner.getUserId())) {
            System.out.println("Partner already involved in some delivery work");
            return;
        }
        userIdToDeliveryPartners.remove(deliveryPartner.getUserId());
        availableDeliveryAgents.remove(deliveryPartner.getUserId());
    }

    public List<DeliveryPartner> getAvailableDeliveryPartners() {
        return availableDeliveryAgents.stream().map(a -> userIdToDeliveryPartners.get(a)).collect(Collectors.toList());
    }
}
