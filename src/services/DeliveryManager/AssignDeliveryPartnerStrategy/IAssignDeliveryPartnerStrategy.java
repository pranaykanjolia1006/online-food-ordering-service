package services.DeliveryManager.AssignDeliveryPartnerStrategy;

import java.util.List;

import common.serviceObject.DeliveryPartner;
import services.DeliveryManager.domainObjects.DeliveryPreparationDetails;

public interface IAssignDeliveryPartnerStrategy {
    DeliveryPartner assignDeliveryPartner(DeliveryPreparationDetails deliveryPreparationDetails, List<DeliveryPartner> DeliveryPartner);
}
