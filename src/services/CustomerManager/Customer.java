package services.CustomerManager;

import common.serviceObject.Person;
import common.serviceObject.PersonType;

public class Customer extends Person{
    private PersonType type;
    

    public Customer() {
        this.type = PersonType.CUSTOMER;
    }

    public PersonType getType() {
        return type;
    }
}
