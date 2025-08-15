package services.CustomerManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class CustomerManager {
    private final Map<String, Customer> customerIdToCustomerMap;

    
    public CustomerManager() {
        customerIdToCustomerMap = new HashMap<>();
    }
    
    public void addCustomer(String name, String phoneNumbner) {
        Customer customer = new Customer();

        boolean alreadyExists = customerIdToCustomerMap.values().stream().anyMatch(c -> c.getPhoneNumnber().equals(phoneNumbner));
        if (alreadyExists) {
            return;
        }

        customer.setUserId(String.valueOf(Math.abs(new Random().nextLong())));
        customer.setName(name);
        customer.setPhoneNumnber(phoneNumbner);
        customerIdToCustomerMap.put(customer.getUserId(), customer);
    }

    public Customer getCustomer(String userId) {
        Customer customer = customerIdToCustomerMap.get(userId);
        if ( customer == null ) {
            System.out.println("No customer exists with userId:" + userId);
        }
        return customer;
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        Optional<Customer> customer = customerIdToCustomerMap.values().stream().filter(c -> c.getPhoneNumnber().equals(phoneNumber)).findFirst();
        if (!customer.isPresent()) {
            System.out.println("No customer exists with phoneNumber:" + phoneNumber);
        }
        return customer.get();
    }




}
