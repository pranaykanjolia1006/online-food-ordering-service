package services.DeliveryManager.AssignDeliveryPartnerStrategy;

import java.util.List;
import java.util.Random;

import common.serviceObject.DeliveryPartner;
import services.DeliveryManager.domainObjects.DeliveryPreparationDetails;

public class RandomDeliveryPartnerAssignStrategy implements IAssignDeliveryPartnerStrategy{
    
    public RandomDeliveryPartnerAssignStrategy() {
        
    }

    @Override
    public DeliveryPartner assignDeliveryPartner(DeliveryPreparationDetails deliveryPreparationDetails, List<DeliveryPartner> availableDeliveryPartners) {
        System.out.println("[assignDeliveryPartner] Assigning delivery partner");
        int randomIndex = Math.abs((new Random()).nextInt()) % availableDeliveryPartners.size();
        System.out.println("[assignDeliveryPartner] Delivery partner assigned");
        return availableDeliveryPartners.get(randomIndex);

    }


    




}
