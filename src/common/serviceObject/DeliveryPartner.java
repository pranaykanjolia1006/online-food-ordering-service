package common.serviceObject;

public class DeliveryPartner extends Person{
    private PersonType personType;

    public PersonType getPersonType() {
        return this.personType;
    }

    public DeliveryPartner() {
        this.personType = PersonType.DELIVERY_PARTNER;
    }
}
