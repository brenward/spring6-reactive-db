package com.bwardweb.spring6_reactive_db.repositories;

import com.bwardweb.spring6_reactive_db.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
