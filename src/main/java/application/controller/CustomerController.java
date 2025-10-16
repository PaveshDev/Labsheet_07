package application.controller;

import application.model.Customer;
import application.service.CustomerService;
import application.view.CustomerView;

public class CustomerController {
    private final CustomerService customerService;
    private final CustomerView customerView;

    public CustomerController(CustomerService customerService, CustomerView customerView) {
        this.customerService = customerService;
        this.customerView = customerView;
    }

    public void registerCustomer(Customer customer) {
        customerService.registerCustomer(customer);
        customerView.showCustomerRegistered(customer);
    }

    public void displayCustomers() {
        customerView.showCustomers(customerService.listCustomers());
    }
}
