package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	@Query("SELECT u FROM Customer u WHERE u.Fullname = ?1 AND u.Password = ?2")
    Customer findByFullnameAndPassword(String Fullname, String Password);
}
