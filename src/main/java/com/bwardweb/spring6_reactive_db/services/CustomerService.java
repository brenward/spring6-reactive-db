package com.bwardweb.spring6_reactive_db.services;

import com.bwardweb.spring6_reactive_db.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<CustomerDTO> listCustomers();

    Mono<CustomerDTO> getCustomerById(Integer id);

    Mono<CustomerDTO> saveCustomer(CustomerDTO customerDTO);

    Mono<Void> deleteCustomerById(Integer id);

    Mono<CustomerDTO> patchCustomer(Integer id, CustomerDTO customerDTO);

    Mono<CustomerDTO> updateCustomer(Integer id, CustomerDTO customerDTO);
}
