package application.service;

import application.model.Customer;
import application.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomer(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    }
}
