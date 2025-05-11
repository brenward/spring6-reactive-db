package com.bwardweb.spring6_reactive_db.services;

import com.bwardweb.spring6_reactive_db.mappers.CustomerMapper;
import com.bwardweb.spring6_reactive_db.model.CustomerDTO;
import com.bwardweb.spring6_reactive_db.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Flux<CustomerDTO> listCustomers() {
        return customerRepository.findAll().map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> getCustomerById(Integer id) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> saveCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public Mono<Void> deleteCustomerById(Integer id) {
        return null;
    }

    @Override
    public Mono<CustomerDTO> patchCustomer(Integer id, CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public Mono<CustomerDTO> updateCustomer(Integer id, CustomerDTO customerDTO) {
        return null;
    }
}
