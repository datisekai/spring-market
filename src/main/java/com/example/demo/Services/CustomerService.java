package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Customer;
import com.example.demo.Repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	public void createCustomer(String Password, String FullName, String Address, String City) {
		Customer customer = new Customer();
		customer.setFullname(FullName);
		customer.setAddress(Address);
		customer.setCity(City);
		customer.setPassword(Password);
		customerRepo.save(customer);
	}
}
