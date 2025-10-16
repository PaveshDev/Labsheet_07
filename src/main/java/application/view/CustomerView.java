package application.view;

import application.model.Customer;

import java.util.List;

public class CustomerView {
    public void showCustomerRegistered(Customer customer) {
        System.out.println("[View] Registered customer: " + customer);
    }

    public void showCustomers(List<Customer> customers) {
        System.out.println("[View] Customers:");
        customers.forEach(customer -> System.out.println("  - " + customer));
    }
}
